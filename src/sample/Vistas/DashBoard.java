package sample.Vistas;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DashBoard extends Parent {
    private GridPane dashBoard;
    private Label label;

    public DashBoard() {
    }

    public GridPane CrearDB(){
        dashBoard = new GridPane();
        label = new Label("Hola");
        dashBoard.add(label,0,1);
        return dashBoard;
    }
}
