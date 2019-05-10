package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CRUD extends Stage {
    private Scene escena;

    public CRUD() {
        CrearGUI();
        this.setTitle("Ordenes del Dia");
        setScene(escena);
        show();
    }

    private void CrearGUI() {
        HBox testBox = new HBox();
        testBox.getChildren().add(new Text("Hello CRUD"));
        escena = new Scene(testBox, 700, 200);
    }
}
