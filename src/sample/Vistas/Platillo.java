package sample.Vistas;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sample.Modelos.OrdenDAO;
import sample.Modelos.PlatilloDAO;

import java.util.ArrayList;

public class Platillo {
    private VBox vBox;
    private Label titulo;
    private Button agregar;
    private ArrayList<PlatilloDAO> platilloDAOS;
    private OrdenDAO objODAO;
    public TableView<OrdenDAO> tbvOrden;
    private ScrollPane scroll;

    public VBox CPlatillo(int categoria) {
        scroll = new ScrollPane();
        VBox vBox1 = new VBox();
        HBox hBox = new HBox();
        platilloDAOS = new PlatilloDAO().seleccionar(categoria);
        for (int i = 0; i < platilloDAOS.size(); i++) {
            titulo = new Label(platilloDAOS.get(i).getNombrePlatillo());
            String ruta = platilloDAOS.get(i).getImagen();
            Image imagen = new Image(getClass().getResourceAsStream(ruta));
            ImageView imageView = new ImageView(imagen);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            agregar = new Button("Agregar");
            agregar.setOnAction(event -> AgregarPedido());
            vBox = new VBox();
            vBox.getChildren().addAll(titulo, imageView, agregar);
            vBox.setPadding(new Insets(5, 5, 5, 5));
            hBox.getChildren().add(vBox);
            hBox.setPadding(new Insets(5, 5, 5, 5));
        }
        scroll.setContent(hBox);
        vBox1.getChildren().addAll(scroll, CrearTabla());
        return vBox1;
    }

    private void AgregarPedido() {

        objODAO = new OrdenDAO();
        objODAO.setIdMesa(1);
        objODAO.setEstado("Abierto");
        objODAO.setFecha("2011-03-11");
        objODAO.setTotal(10);
        objODAO.setIdMesero(1);
        objODAO.insertar();
        tbvOrden.setItems(objODAO.seleccionar());
        tbvOrden.refresh();
    }

    public TableView<OrdenDAO> CrearTabla() {
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
        tbvOrden.setItems(new OrdenDAO().seleccionar());

        return tbvOrden;
    }
}