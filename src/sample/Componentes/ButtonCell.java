package sample.Componentes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import sample.Modelos.OrdenDAO;

public class ButtonCell extends TableCell<OrdenDAO, String> {
    private Button celdaBoton;
    private ObservableList<OrdenDAO> nordenes = FXCollections.observableArrayList();
    private int opc;

    OrdenDAO objO;

    public ButtonCell(int opc, ObservableList<OrdenDAO> ordenes) {
        this.opc = opc;
        if (this.opc == 1) {
            nordenes = ordenes;
            celdaBoton = new Button("Eliminar");
            celdaBoton.getStyleClass().add("danger");
            celdaBoton.setOnAction(event -> Eliminar());
        }
    }

    public void Eliminar() {
        objO = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
        objO.eliminar();
        nordenes.clear();
        nordenes.addAll(new OrdenDAO().seleccionar());
        ButtonCell.this.getTableView().refresh();
        //ButtonCell.this.getTableView().setItems(nordenes);
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (b) {
            setGraphic(null);
        } else {
            setGraphic(celdaBoton);
        }
    }
}
