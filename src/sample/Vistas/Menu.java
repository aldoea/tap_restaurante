package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCell;
import sample.Componentes.CrearPDF;
import sample.Login;
import sample.Modelos.CategoriaDAO;
import sample.Modelos.MesaDAO;
import sample.Modelos.MeseroDAO;
import sample.Modelos.OrdenDAO;
import java.util.ArrayList;

public class Menu implements EventHandler {
    private HBox headerHbox, abajo;
    private BorderPane panel;
    private TabPane tabPane;
    private ArrayList<CategoriaDAO> rCategoriaDAOS;
    private CategoriaDAO rCategoriaDAO;
    private ObservableList<OrdenDAO> ordenes = FXCollections.observableArrayList();
    private ObservableList<MesaDAO> mesas = FXCollections.observableArrayList();
    private ObservableList<MeseroDAO> meseros = FXCollections.observableArrayList();
    private Button regresarBtn = new Button();
    private Label tituloMain;
    private Label meseroLbl, mesaLbl;
    public ComboBox meserosCbox, mesasCbox, mesasCCbox;
    private TableView<OrdenDAO> tbvOrden;
    private Stage nStage;
    private Scene scene;
    private static int contador = 1;

    public Menu(Stage stage) {
        this.nStage = stage;
        llenarCombos();
        panel = new BorderPane();
        tabPane = new TabPane();
        abajo = new HBox();
        CreatTabs();
        panel.setTop(crearHeader());
        panel.setCenter(tabPane);

        panel.setBottom(CrearTabla());

        panel.getTop().getStyleClass().add("my-header");
        panel.getCenter().getStyleClass().add("my-body");
        panel.getBottom().getStyleClass().add("my-footer");

        HBox opciones = new HBox();
        VBox selectMesa = new VBox();
        Button cobrar = new Button("Terminar Servicio");
        Button ticket = new Button("Consumo Actual");
        Label lCobrar = new Label("Seleccionar mesa");

        selectMesa.getChildren().addAll(lCobrar, mesasCCbox);
        opciones.getChildren().addAll(selectMesa, cobrar, ticket);
        cobrar.setOnAction(event -> Cobrar());
        ticket.setOnAction(event -> ConsumoA());

        abajo.getChildren().addAll(CrearTabla(),opciones);
        abajo.setPrefHeight(300);
        abajo.setMaxHeight(300);
        panel.setBottom(abajo);
        panel.addEventHandler(Platillo.ITEM_ADD, new EventHandler<PlatilloEvent>() {
            @Override
            public void handle(PlatilloEvent event) {
                ordenes.clear();
                ordenes.addAll(new OrdenDAO().seleccionar());
            }
        });

        scene = new Scene(panel);
        nStage.setScene(scene);
        nStage.setFullScreen(true);
        scene.getStylesheets().add(getClass().getResource("../CSS/bootstrap3.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("../CSS/menu.css").toExternalForm());
        nStage.show();
    }

    private void ConsumoA() {
        OrdenDAO objO = new OrdenDAO();
        CrearPDF pdf = new CrearPDF();
        String nombre = "ticket_" + contador + ".pdf";
        contador++;
        pdf.crearPDF(nombre, objO.ticket(mesasCCbox.getSelectionModel().getSelectedIndex() + 1));
    }

    private void Cobrar() {
        OrdenDAO objO = new OrdenDAO();
        objO.Cobrar(mesasCCbox.getSelectionModel().getSelectedIndex() + 1);
        ordenes.clear();
        ordenes.addAll(new OrdenDAO().seleccionar());
    }

    private HBox crearHeader() {
        headerHbox = new HBox();
        headerHbox.setId("header-content");
        HBox regresarBox, tituloBox, servicioBox;
        VBox meseroVBox = new VBox(), mesaVBox = new VBox();
        // Boton regregresar
        regresarBtn.getStyleClass().add("btn-regresar");
        regresarBtn.setOnAction(event -> Regresar());
        regresarBox = new HBox();
        regresarBox.getChildren().addAll(regresarBtn);
        regresarBox.setId("back-button-box");
        // Titulo
        tituloMain = new Label("Agregue los platillos");
        tituloMain.setId("main-header-label");
        tituloBox = new HBox();
        tituloBox.getChildren().add(tituloMain);
        tituloBox.setId("title-box");
        // Servicio
        mesaLbl = new Label("Seleccionar mesa");
        meseroLbl = new Label("Seleccionar mesero");
        mesaLbl.getStyleClass().add("servicio-label");
        meseroLbl.getStyleClass().add("servicio-label");
        meseroVBox.getChildren().addAll(meseroLbl, meserosCbox);
        mesaVBox.getChildren().addAll(mesaLbl, mesasCbox);
        meseroVBox.getStyleClass().add("servicio-cb-cont");
        mesaVBox.getStyleClass().add("servicio-cb-cont");
        servicioBox = new HBox();
        servicioBox.getChildren().addAll(meseroVBox, mesaVBox);
        servicioBox.setId("servicio-box");

        headerHbox.getChildren().addAll(regresarBox, tituloBox, servicioBox);
        return headerHbox;
    }

    private void Regresar() {
        new Login(nStage);
    }

    public void CreatTabs() {
        rCategoriaDAOS = new ArrayList<CategoriaDAO>();
        rCategoriaDAO = new CategoriaDAO();
        rCategoriaDAOS = rCategoriaDAO.categoriaDAOS();
        for (int i = 0; i < rCategoriaDAOS.size(); i++) {
            Tab tab = new Tab();
            tab.setId(String.valueOf(i));
            tab.setStyle("-fx-border-color: darkgray;");
            tab.setText(rCategoriaDAOS.get(i).getNombreCategoria());
            tab.closableProperty().setValue(false);
            tab.setContent(new Platillo(ordenes, meserosCbox, mesasCbox).CPlatillo(i + 1));
            tabPane.getTabs().add(tab);
        }
        ordenes.addAll(new OrdenDAO().seleccionar());
    }

    private TableView<OrdenDAO> CrearTabla() {
        tbvOrden = new TableView<>();

        TableColumn<OrdenDAO, Integer> tbcIdOrden = new TableColumn<>("ID");
        tbcIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));

        TableColumn<OrdenDAO, Integer> tbcIdMesa = new TableColumn<>("Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));

        TableColumn<OrdenDAO, Integer> tbcPlatillo = new TableColumn<>("Platillo");
        tbcPlatillo.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<OrdenDAO, String > tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<OrdenDAO, String> tbcfecha = new TableColumn<>("Fecha");
        tbcfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<OrdenDAO, Double> tbcTotal = new TableColumn<>("Total");
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<OrdenDAO, Integer> tbcIdMesero = new TableColumn<>("Mesero");
        tbcIdMesero.setCellValueFactory(new PropertyValueFactory<>("idMesero"));

        TableColumn<OrdenDAO, String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<OrdenDAO, String>, TableCell<OrdenDAO, String>>() {
            @Override
            public TableCell<OrdenDAO, String> call(TableColumn<OrdenDAO, String> param) {
                return new ButtonCell(1, ordenes);
            }
        });

        tbvOrden.getColumns().addAll(tbcIdOrden, tbcPlatillo, tbcIdMesa, tbcEstado, tbcfecha, tbcTotal, tbcIdMesero, tbcEliminar);
        tbvOrden.setItems(ordenes);

        return tbvOrden;
    }

    @Override
    public void handle(Event event) {

    }

    private void llenarCombos() {
        meserosCbox = new ComboBox();
        meserosCbox.setItems(new MeseroDAO().getMeseros());
        meserosCbox.getSelectionModel().select(0);
        mesasCbox = new ComboBox();
        mesasCbox.setItems(new MesaDAO().getMesas());
        mesasCbox.getSelectionModel().select(0);
        mesasCCbox = new ComboBox();
        mesasCCbox.setItems(new MesaDAO().getMesas());
        mesasCCbox.getSelectionModel().select(0);
    }
}
