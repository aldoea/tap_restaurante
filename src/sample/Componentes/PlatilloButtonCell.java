package sample.Componentes;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import sample.Modelos.PlatilloDAO;
import sample.Vistas.PlatilloForm;

public class PlatilloButtonCell extends  TableCell<PlatilloDAO, String> {
        private Button celdaBoton;
        private int opc;
        PlatilloDAO objPlatillo;

    public PlatilloButtonCell(int opc){
        this.opc = opc;
        //objPeli = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
        if(this.opc == 1) {
            celdaBoton = new Button("Editar");
            celdaBoton.getStyleClass().add("warning");
            celdaBoton.setOnAction(event -> Editar());
        }else {
            celdaBoton = new Button("Eliminar");
            celdaBoton.getStyleClass().add("danger");
            celdaBoton.setOnAction(event -> Eliminar());
        }

    }

    private void Editar(){
        objPlatillo = PlatilloButtonCell.this.getTableView().getItems().get(PlatilloButtonCell.this.getIndex());
        new PlatilloForm(PlatilloButtonCell.this.getTableView()).setPlatilloDAO(objPlatillo);
    }

    private void Eliminar(){
        objPlatillo = PlatilloButtonCell.this.getTableView().getItems().get(PlatilloButtonCell.this.getIndex());
        objPlatillo.eliminar();
        PlatilloButtonCell.this.getTableView().setItems(objPlatillo.seleccionar());
        PlatilloButtonCell.this.getTableView().refresh();
    }

    @Override
    protected void updateItem(String s, boolean empty) {
        super.updateItem(s, empty);
        if(!empty) {
            setGraphic(celdaBoton);
        }
    }
}
