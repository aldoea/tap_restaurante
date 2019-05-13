package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.PlatilloButtonCell;
import sample.Modelos.PlatilloDAO;

public class CRUD extends Stage {
    private Scene escena;
    private BorderPane mainPane;
    private VBox tbvContainer;
    private TableView<PlatilloDAO> tbvPlatillo;
    private Button btnAgregar;

    public CRUD() {
        CrearGUI();
        this.setTitle("Ordenes del Dia");
        setScene(escena);
        escena.getStylesheets().add(getClass().getResource("../CSS/bootstrap3.css").toExternalForm());
        escena.getStylesheets().add(getClass().getResource("../CSS/dashboard.css").toExternalForm());
        show();
    }

    private void CrearGUI() {
        mainPane = new BorderPane();
        tbvContainer = new VBox();
        HBox agregarContainer = new HBox();
        tbvPlatillo = new TableView<>();
        tbvContainer.getChildren().add(tbvPlatillo);
        tbvContainer.setId("tbv-container");
        CrearTabla();

        btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(event -> AgregarPlatillo());
        btnAgregar.getStyleClass().addAll("button", "success", "lg");
        agregarContainer.getChildren().add(btnAgregar);
        agregarContainer.setId("agregar-container");

        mainPane.setCenter(tbvContainer);
        mainPane.setBottom(agregarContainer);
        mainPane.getBottom().getStyleClass().add("crud-footer");
        escena = new Scene(mainPane, 1000, 500);
    }

    private void AgregarPlatillo() {
        new PlatilloForm(tbvPlatillo);
    }

    private void CrearTabla() {
        // 1- Crear columna
        //       <tipo de objecto, tipo de dato>                       "Nombre de encabezado"
        TableColumn<PlatilloDAO, Integer> tbcIdPlatillo = new TableColumn<>("ID");
        tbcIdPlatillo.setCellValueFactory(new PropertyValueFactory<>("idPlatillo"));

        TableColumn<PlatilloDAO, String> tbcNomPlatillo = new TableColumn<>("Platillo");
        tbcNomPlatillo.setCellValueFactory(new PropertyValueFactory<>("nombrePlatillo"));

        TableColumn<PlatilloDAO, Integer> tbcPrecio= new TableColumn<>("Precio");
        tbcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<PlatilloDAO, String> tbcImagen = new TableColumn<>("Imagen");
        tbcImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));

        TableColumn<PlatilloDAO, Integer> tbcDescPlatillo = new TableColumn<>("Descripcion");
        tbcDescPlatillo.setCellValueFactory(new PropertyValueFactory<>("descripcionPlatillo"));

        TableColumn<PlatilloDAO, Integer> tbcIdCategoria = new TableColumn<>("Categoria");
        tbcIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<PlatilloDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<PlatilloDAO, String>, TableCell<PlatilloDAO, String>>() {
            @Override
            public TableCell<PlatilloDAO, String> call(TableColumn<PlatilloDAO, String> PlatilloDAOStringTableColumn) {
                return new PlatilloButtonCell(1);
            }
        });

        TableColumn<PlatilloDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<PlatilloDAO, String>, TableCell<PlatilloDAO, String>>() {
            @Override
            public TableCell<PlatilloDAO, String> call(TableColumn<PlatilloDAO, String> param) {
                return new PlatilloButtonCell(2);
            }
        });

        tbvPlatillo.getColumns().addAll(tbcIdPlatillo, tbcNomPlatillo, tbcPrecio, tbcImagen, tbcDescPlatillo, tbcIdCategoria, tbcEditar, tbcEliminar);
        tbvPlatillo.setItems(new PlatilloDAO().seleccionar());
    }
}
