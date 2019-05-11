package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.dashboardDAO;

import java.util.Calendar;

public class DashBoard {
    private BorderPane dashbParentContainer;
    private Stage dashbStage;
    private Scene mainScene;
    private MenuBar menuBar;
    private Menu administradorMenu;
    private MenuItem listadoOrdenesMenuIt, crudMenuIt;

    public DashBoard(Stage stage) {
        dashbStage = stage;
        CrearUI();
    }

    public void CrearUI() {
        dashbParentContainer = new BorderPane();
        dashbParentContainer.setTop(crearMenuBar());
        dashbParentContainer.setCenter(crearCharts());

        mainScene = new Scene(dashbParentContainer);
        mainScene.getStylesheets().add(getClass().getResource("../CSS/bootstrap3.css").toExternalForm());
        mainScene.getStylesheets().add(getClass().getResource("../CSS/dashboard.css").toExternalForm());

        dashbStage.setScene(mainScene);
        dashbStage.setMaximized(true);
        dashbStage.show();
    }

    public MenuBar crearMenuBar() {
        menuBar =  new MenuBar();
        administradorMenu = new Menu("Administrador");
        listadoOrdenesMenuIt = new MenuItem("Listar ordenes del día");
        crudMenuIt = new MenuItem("Administrar Catálogos");
        listadoOrdenesMenuIt.setOnAction(event -> listarDia());
        crudMenuIt.setOnAction(event -> crudTrigger());

        administradorMenu.getItems().addAll(listadoOrdenesMenuIt, crudMenuIt);
        menuBar.getMenus().add(administradorMenu);
        return menuBar;
    }

    public HBox crearCharts() {
        HBox chartsContainer = new HBox();
        chartsContainer.setId("charts-container");
        chartsContainer.getChildren().addAll(crearSemanasChart(), crearPlatillosChart());
        return chartsContainer;
    }

    private HBox crearSemanasChart() {
        HBox semanaChartContainer = new HBox();
        semanaChartContainer.setId("semana-chart-container");

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> compararSemanaBarChart = new BarChart<String,Number>(xAxis,yAxis);
        compararSemanaBarChart.setTitle("Comparativa semana actual VS. semana anterior");
        yAxis.setLabel("Total Pedidos");
        compararSemanaBarChart.setData(dashboardDAO.compararSemanaAnterior());
        semanaChartContainer.getChildren().add(compararSemanaBarChart);

        return semanaChartContainer;

    }

    private HBox crearPlatillosChart() {
        HBox platilloChartContainer = new HBox();

        platilloChartContainer.setId("platillo-chart-container");
        PieChart platillosPieChart = new PieChart(dashboardDAO.platilloPerMes());
        platillosPieChart.setTitle("Platillos pedidos en el mes de " + mesActual().toUpperCase());
        platilloChartContainer.getChildren().add(platillosPieChart);

        return platilloChartContainer;
    }

    private void crudTrigger() {
        new CRUD();
    }

    private void listarDia() {
        new Lista();
    }

    public String mesActual() {
        Calendar now = Calendar.getInstance();
        // Array con los meses del año
        String[] strMonths = new String[]{
                "Enero",
                "Febebro",
                "Marzo",
                "Abril",
                "Mayo",
                "Junio",
                "Julio",
                "Agosto",
                "Septiembre",
                "Octubre",
                "Noviembre",
                "Diciembre"};
        return strMonths[now.get(Calendar.MONTH)];
    }
}
