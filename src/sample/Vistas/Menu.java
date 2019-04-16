package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import sample.Modelos.CategoriaDAO;
import sample.Modelos.MesaDAO;
import sample.Modelos.MeseroDAO;
import sample.Modelos.OrdenDAO;

import java.util.ArrayList;

public class Menu extends Parent {
    private HBox headerHbox;
    private BorderPane panel;
    private TabPane tabPane;
    private ArrayList<CategoriaDAO> rCategoriaDAOS;
    private CategoriaDAO rCategoriaDAO;
    private ObservableList<OrdenDAO> ordenes = FXCollections.observableArrayList();
    private ObservableList<MesaDAO> mesas = FXCollections.observableArrayList();
    private ObservableList<MeseroDAO> meseros = FXCollections.observableArrayList();
    private Button regresarBtn = new Button("<--");
    private Label tituloMain;
    private Label meseroLbl, mesaLbl;
    private ComboBox<ObservableList<MeseroDAO>> meserosCbox;
    private ComboBox<ObservableList<MesaDAO>> mesasCbox;
    private TableView<OrdenDAO> tbvOrden;

    public BorderPane CrearMenu() {
        panel = new BorderPane();
        tabPane = new TabPane();
        CreatTabs();
        panel.setTop(crearHeader());
        panel.setCenter(tabPane);
        panel.setBottom(CrearTabla());
        panel.addEventHandler(Platillo.ITEM_ADD, new EventHandler<PlatilloEvent>() {
            @Override
            public void handle(PlatilloEvent event) {
                ordenes.clear();
                ordenes.addAll(new OrdenDAO().seleccionar());
            }
        });

        return panel;
    }

    private HBox crearHeader() {
        headerHbox = new HBox();
        tituloMain = new Label("Agregue los platillos");
        mesaLbl = new Label("Seleccionar mesa: ");
        meseroLbl = new Label("Seleccionar mesero: ");
        meserosCbox = new ComboBox<ObservableList<MeseroDAO>>();
        mesasCbox = new ComboBox<ObservableList<MesaDAO>>();
        headerHbox.getChildren().addAll(regresarBtn, tituloMain, meseroLbl, meserosCbox, mesaLbl, mesasCbox);
        return headerHbox;
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
            tab.setContent(new Platillo(ordenes).CPlatillo(i + 1));
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

        TableColumn<OrdenDAO, String > tbcEstado = new TableColumn<>("Estado");
        tbcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<OrdenDAO, String> tbcfecha = new TableColumn<>("Fecha");
        tbcfecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<OrdenDAO, Double> tbcTotal = new TableColumn<>("Total");
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<OrdenDAO, Integer> tbcIdMesero = new TableColumn<>("Mesero");
        tbcIdMesero.setCellValueFactory(new PropertyValueFactory<>("idMesero"));

        tbvOrden.getColumns().addAll(tbcIdOrden, tbcIdMesa, tbcEstado, tbcfecha, tbcTotal, tbcIdMesero);
        tbvOrden.setItems(ordenes);

        return tbvOrden;
    }


}
