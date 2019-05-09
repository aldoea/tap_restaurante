package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.OrdenDAO;

public class Lista extends Stage {
    private Scene escena;
    private TableView<OrdenDAO> tablaDia;
    private ObservableList<OrdenDAO> dia = FXCollections.observableArrayList();

    public Lista() {
        CrearGUI();
        this.setTitle("Ordenes del Dia");
        setScene(escena);
        show();
    }

    private void CrearGUI() {
        VBox vBox = new VBox();
        tablaDia = new TableView<>();
        vBox.getChildren().addAll(pDia());
        escena = new Scene(vBox, 700, 200);
    }

    private TableView<OrdenDAO> pDia() {
        dia.addAll(new OrdenDAO().OrdenDia());
        tablaDia = new TableView<>();

        TableColumn<OrdenDAO, Integer> tbcPlatillo = new TableColumn<>("Platillo");
        tbcPlatillo.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<OrdenDAO, Integer> tbcIdMesa = new TableColumn<>("Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));

        TableColumn<OrdenDAO, Integer> tbcIdOrden = new TableColumn<>("ID");
        tbcIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));


        TableColumn<OrdenDAO, Integer> tbcIdMesero = new TableColumn<>("Mesero");
        tbcIdMesero.setCellValueFactory(new PropertyValueFactory<>("idMesero"));


        TableColumn<OrdenDAO, Double> tbcTotal = new TableColumn<>("Total");
        tbcTotal.setCellValueFactory(new PropertyValueFactory<>("precio"));


        tablaDia.getColumns().addAll(tbcIdOrden, tbcPlatillo, tbcIdMesa, tbcTotal, tbcIdMesero);
        tablaDia.setItems(dia);

        return tablaDia;
    }
}
