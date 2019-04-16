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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import sample.Modelos.OrdenDAO;
import sample.Modelos.PlatilloDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class Platillo {
    private VBox bodyPlatillos;
    private ArrayList<PlatilloDAO> platilloDAOS;
    private OrdenDAO objODAO;
    private ObservableList<OrdenDAO> ordenes;
    public static EventType<PlatilloEvent> ITEM_ADD = new EventType("item_add");


    public Platillo(ObservableList<OrdenDAO> ordenes){
        this.ordenes = ordenes;
    }

    public VBox CPlatillo(int categoria) {
        ScrollPane scrollPlatillos = new ScrollPane();
        bodyPlatillos = new VBox();
        HBox platillosHbox = new HBox();
        platillosHbox.getStyleClass().add("platillos");
        platilloDAOS = new PlatilloDAO().seleccionar(categoria);

        for (int i=0; i < platilloDAOS.size(); i++) {
            VBox platillo = new VBox();
            VBox contenedorPlatillo = new VBox();
            Pane platilloImagen = new Pane();
            Label platilloTitulo = new Label(platilloDAOS.get(i).getNombrePlatillo());
            Button btnAgregarPlatillo = new Button("Agregar");
            String ruta = platilloDAOS.get(i).getImagen();

            platillo.getStyleClass().add("vbox-platillo");
            contenedorPlatillo.getStyleClass().add("platillo-container");
            platilloTitulo.getStyleClass().add("platillo-titulo");
            platilloImagen.getStyleClass().add("platillo-imagen");
            platilloImagen.setStyle("-fx-background-image: url(" + ruta +  ")");
            btnAgregarPlatillo.getStyleClass().addAll("button", "primary", "btn-anadir");

            btnAgregarPlatillo.setOnAction(event -> AgregarPedido());

            contenedorPlatillo.getChildren().addAll(platilloImagen, platilloTitulo, btnAgregarPlatillo);
            platillo.getChildren().add(contenedorPlatillo);
            platillosHbox.getChildren().add(platillo);
        }
        scrollPlatillos.setFitToWidth(true);
        scrollPlatillos.setFitToHeight(true);
        scrollPlatillos.setContent(platillosHbox);
        bodyPlatillos.getChildren().addAll(scrollPlatillos);
        return bodyPlatillos;
    }

    private void AgregarPedido() {
        objODAO = new OrdenDAO();
        objODAO.setIdPlatillo(2);
        objODAO.setIdMesa(1);
        objODAO.setEstado("Abierto");
        objODAO.setFecha(LocalDate.now()+"");
        objODAO.setTotal(10);
        objODAO.setIdMesero(1);
        objODAO.insertar();
        bodyPlatillos.fireEvent(new PlatilloEvent(ITEM_ADD));
    }
}

class PlatilloEvent extends Event{

    public PlatilloEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}