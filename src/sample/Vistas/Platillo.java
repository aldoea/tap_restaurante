package sample.Vistas;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.control.*;
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
    private ComboBox mesaOrden, meseroOrden;


    public Platillo(ObservableList<OrdenDAO> ordenes, ComboBox mesas, ComboBox meseros) {
        this.ordenes = ordenes;
        this.mesaOrden = mesas;
        this.meseroOrden = meseros;
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
            btnAgregarPlatillo.setId(String.valueOf(platilloDAOS.get(i).getIdPlatillo()));
            String ruta = platilloDAOS.get(i).getImagen();
            platillo.getStyleClass().add("vbox-platillo");
            contenedorPlatillo.getStyleClass().add("platillo-container");
            platilloTitulo.getStyleClass().add("platillo-titulo");
            platilloImagen.getStyleClass().add("platillo-imagen");
            platilloImagen.setStyle("-fx-background-image: url(" + ruta + ");");
            btnAgregarPlatillo.getStyleClass().addAll("button", "primary", "btn-anadir");

            btnAgregarPlatillo.setOnAction(event -> AgregarPedido(btnAgregarPlatillo.getId()));

            contenedorPlatillo.getChildren().addAll(platilloImagen, platilloTitulo, btnAgregarPlatillo);
            platillo.getChildren().add(contenedorPlatillo);
            platillosHbox.getChildren().add(platillo);
        }
        scrollPlatillos.setFitToWidth(true);
        scrollPlatillos.setFitToHeight(true);
        scrollPlatillos.setContent(platillosHbox);
        bodyPlatillos.getChildren().addAll(scrollPlatillos);
        scrollPlatillos.setVmax(0);
        scrollPlatillos.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return bodyPlatillos;
    }

    private void AgregarPedido(String id) {
        objODAO = new OrdenDAO();
        objODAO.setIdPlatillo(platilloDAOS.get(1).sPlatillo(Integer.parseInt(id)).getIdPlatillo());
        objODAO.setIdMesa(meseroOrden.getSelectionModel().getSelectedIndex() + 1);
        objODAO.setEstado("Abierto");
        objODAO.setFecha(LocalDate.now()+"");
        objODAO.setPrecio(platilloDAOS.get(1).sPlatillo(Integer.parseInt(id)).getPrecio());
        objODAO.setIdMesero(mesaOrden.getSelectionModel().getSelectedIndex() + 1);
        objODAO.insertar();
        bodyPlatillos.fireEvent(new PlatilloEvent(ITEM_ADD));
    }
}

class PlatilloEvent extends Event{

    public PlatilloEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}