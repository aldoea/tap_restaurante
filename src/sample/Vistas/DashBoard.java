package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DashBoard {
    private BorderPane dashbParentContainer;
    private Stage dashbStage;
    private Scene mainScene;
    private MenuBar menuBar;
    private Menu administradorMenu;
    private MenuItem listadoOrdenesMenuIt, crudMenuIt;
    private Button listadoBtn;
    private Button crudBtn;

    public DashBoard(Stage stage) {
        dashbStage = stage;
        CrearUI();
    }

    public void CrearUI() {
        dashbParentContainer = new BorderPane();
        dashbParentContainer.setTop(crearMenuBar());
        mainScene = new Scene(dashbParentContainer);
        mainScene.getStylesheets().add(getClass().getResource("../CSS/bootstrap3.css").toExternalForm());
        mainScene.getStylesheets().add(getClass().getResource("../CSS/dashboard.css").toExternalForm());

        dashbStage.setScene(mainScene);
        dashbStage.setMaximized(true);
        dashbStage.show();
    }

    public MenuBar crearMenuBar() {
        menuBar =  new MenuBar();
        administradorMenu = new Menu("Administrador");
        listadoOrdenesMenuIt = new MenuItem("Listar ordenes del día");
        crudMenuIt = new MenuItem("Administrar Catálogos");
        listadoOrdenesMenuIt.setOnAction(event -> listarDia());
        crudMenuIt.setOnAction(event -> crudTrigger());

        administradorMenu.getItems().addAll(listadoOrdenesMenuIt, crudMenuIt);
        menuBar.getMenus().add(administradorMenu);
        return menuBar;
    }

    private void crudTrigger() {
        new CRUD();
    }

    private void listarDia() {
        new Lista();
    }
}
