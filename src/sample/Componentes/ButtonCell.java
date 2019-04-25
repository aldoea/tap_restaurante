package sample.Componentes;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import sample.Modelos.OrdenDAO;

public class ButtonCell extends TableCell<OrdenDAO, String> {
    private Button celdaBoton;
    private int opc;

    OrdenDAO objO;

    public ButtonCell(int opc) {
        this.opc = opc;
        if (this.opc == 1) {
            celdaBoton = new Button("Eliminar");
            celdaBoton.setOnAction(event -> Eliminar());
        }
    }

    public void Eliminar() {
        objO = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
        objO.eliminar();
        //ButtonCell.this.getTableView().setItems(objO.seleccionar());
        ButtonCell.this.getTableView().refresh();
    }

    @Override
    protected void updateItem(String s, boolean b) {
        super.updateItem(s, b);
        if (!b)
            setGraphic(celdaBoton);
    }
}
