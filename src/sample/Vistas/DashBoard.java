package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Modelos.OrdenDAO;

public class DashBoard {
    private GridPane dashBoard;
    private Button listado;
    private Stage nStage;
    private Scene scene;

    public DashBoard(Stage stage) {
        nStage = stage;
        CrearD();
    }

    public void CrearD() {
        dashBoard = new GridPane();
        listado = new Button("Listar pedidos del dia");
        listado.setOnAction(event -> listarDia());
        dashBoard.add(listado, 0, 1);

        scene = new Scene(dashBoard, 500, 500);
        nStage.setScene(scene);
        nStage.show();
    }

    private void listarDia() {
        new Lista();

    }
}
