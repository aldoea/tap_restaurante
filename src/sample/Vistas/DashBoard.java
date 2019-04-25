package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DashBoard {
    private GridPane dashBoard;
    private Label label;
    private Stage nStage;
    private Scene scene;

    public DashBoard(Stage stage) {
        nStage = stage;
        CrearD();
    }

    public void CrearD() {
        dashBoard = new GridPane();
        label = new Label("Hola");
        dashBoard.add(label,0,1);

        scene = new Scene(dashBoard, 500, 500);
        nStage.setScene(scene);
        nStage.show();
    }
}
