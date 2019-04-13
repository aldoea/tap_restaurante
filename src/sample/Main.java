package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Modelos.Conexion;
import sample.Modelos.UsuarioDAO;
import sample.Vistas.DashBoard;
import sample.Vistas.Menu;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main extends Application implements EventHandler {
    private Label usuarioL, contraseñaL;
    private TextField usuarioT;
    private PasswordField contraseñaT;
    private Stage stage;
    private Scene scene,dashboard,menu;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        scene = new Scene(grid, 300, 275);
        scene.getStylesheets().add(getClass().getResource("CSS/login.css").toExternalForm());
        stage.setScene(scene);

        usuarioL = new Label("Usuario");
        grid.add(usuarioL, 0, 1);

        usuarioT = new TextField();
        grid.add(usuarioT, 1, 1);

        contraseñaL = new Label("Contraseña:");
        grid.add(contraseñaL, 0, 2);

        contraseñaT = new PasswordField();
        grid.add(contraseñaT, 1, 2);

        Button accederA = new Button("Entrar");
        grid.add(accederA, 1, 4);

        Button accederSU = new Button("Sin usuario");
        grid.add(accederSU, 1, 5);

        stage.addEventHandler(WindowEvent.WINDOW_SHOWN,this);
        accederA.setOnAction(event->AccederA(usuarioT.getText(),contraseñaT.getText()));
        accederSU.setOnAction(event -> AccederSU());
        stage.show();
    }

    private void AccederSU() {
        menu = new Scene(new Menu().CrearMenu());
        stage.setScene(menu);
    }

    private void AccederA(String usuario, String contraseña) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.iniciar(usuario, encryptThisString(contraseña))) {
            dashboard = new Scene(new DashBoard().CrearDB(),200,200);
            stage.setScene(dashboard);
        } else {
            System.out.println("Error al ingresar");
        }
    }

    public static String encryptThisString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(Event event) {
        Conexion.crearConexion();
        if(Conexion.con != null){
            System.out.println(" Conexión Exitosa");
        }
    }
}
