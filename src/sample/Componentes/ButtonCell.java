package sample.Componentes;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import sample.Login;
import sample.Modelos.OrdenDAO;
import sample.Modelos.UsuarioDAO;

import java.util.Optional;

public class ButtonCell extends TableCell<OrdenDAO, String> {
    private Button celdaBoton;
    private ObservableList<OrdenDAO> nordenes = FXCollections.observableArrayList();
    private int opc;
    private String[] datos = new String[2];

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
        Login login = new Login();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AA();

        if (usuarioDAO.iniciar(datos[0], login.encryptThisString(datos[1]))) {
            objO = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            objO.eliminar();
            nordenes.clear();
            nordenes.addAll(new OrdenDAO().seleccionar());
            ButtonCell.this.getTableView().refresh();
        } else {
            System.out.println("Error al ingresar");
        }
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

    public void AA() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Autenticación requerida");
        dialog.setHeaderText("Acción requiere autenticación");

        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> username.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return new Pair<>(username.getText(), password.getText());
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            datos[0] = usernamePassword.getKey();
            datos[1] = usernamePassword.getValue();

            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }
}
