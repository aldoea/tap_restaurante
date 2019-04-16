package sample.Vistas;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Modelos.OrdenDAO;
import sample.Modelos.PlatilloDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Platillo {
    private VBox vBox, vBox1;
    private Label titulo;
    private Button agregar;
    private ArrayList<PlatilloDAO> platilloDAOS;
    private OrdenDAO objODAO;
    private TableView<OrdenDAO> tbvOrden;
    private ScrollPane scroll;
    private ObservableList<OrdenDAO> ordenes;
    public static EventType<PlatilloEvent> ITEM_ADD = new EventType("item_add");


    public Platillo(ObservableList<OrdenDAO> ordenes){
        this.ordenes = ordenes;
    }

    public VBox CPlatillo(int categoria) {
        scroll = new ScrollPane();
        vBox1 = new VBox();
        HBox hBox = new HBox();
        //int i;
        platilloDAOS = new PlatilloDAO().seleccionar(categoria);
        for (int i=0; i < platilloDAOS.size(); i++) {
            titulo = new Label(platilloDAOS.get(i).getNombrePlatillo());
            String ruta = platilloDAOS.get(i).getImagen();
            Image imagen = new Image(getClass().getResourceAsStream(ruta));
            ImageView imageView = new ImageView(imagen);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            agregar = new Button("Agregar");
            agregar.setOnAction(event -> AgregarPedido());
            vBox = new VBox();
            vBox.getChildren().addAll(titulo,imageView, agregar);
            vBox.setPadding(new Insets(5, 5, 5, 5));
            hBox.getChildren().add(vBox);
            hBox.setPadding(new Insets(5, 5, 5, 5));
        }
        scroll.setContent(hBox);
        vBox1.getChildren().addAll(scroll, CrearTabla());
        return vBox1;
    }

    private void AgregarPedido() {
        System.out.println(agregar.getId());
        objODAO = new OrdenDAO();
        objODAO.setIdMesa(1);
        objODAO.setEstado("Abierto");
        objODAO.setFecha(LocalDate.now()+"");
        objODAO.setTotal(10);
        objODAO.setIdMesero(1);
        objODAO.insertar();
        vBox1.fireEvent(new PlatilloEvent(ITEM_ADD));
        tbvOrden.refresh();
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

class PlatilloEvent extends Event{

    public PlatilloEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}