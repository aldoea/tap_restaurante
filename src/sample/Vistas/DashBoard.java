package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DashBoard {
    private BorderPane dashbParentContainer;
    private Stage dashbStage;
    private Scene mainScene;
    private MenuBar menuBar;
    private Menu administradorMenu;
    private MenuItem listadoOrdenesMenuIt, crudMenuIt;
    private Button listadoBtn;
    private Button crudBtn;

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
        Text dummyText = new Text("Soy el chart de semanas");
        semanaChartContainer.setId("semana-chart-container");
        semanaChartContainer.getChildren().add(dummyText);
        return semanaChartContainer;

    }

    private HBox crearPlatillosChart() {
        HBox platilloChartContainer = new HBox();
        Text dummyText = new Text("Soy el chart de platillos");
        platilloChartContainer.setId("platillo-chart-container");
        platilloChartContainer.getChildren().add(dummyText);
        return platilloChartContainer;
    }

    private void crudTrigger() {
        new CRUD();
    }

    private void listarDia() {
        new Lista();
    }
}
