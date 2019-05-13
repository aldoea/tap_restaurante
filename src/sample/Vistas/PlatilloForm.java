package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.CategoriaDAO;
import sample.Modelos.PlatilloDAO;

public class PlatilloForm extends Stage {
    /*
    *
    private int idPlatillo;
    private String nombrePlatillo;
    private String descripcionPlatillo;
    private int idCategoria;
    private String imagen;
    private float precio;
    * */
    private TableView<PlatilloDAO> tbvPeliculas;
    private Scene escena;
    private VBox vBox;
    private Label lblTitulo;
    private TextField
            txtNomPlatillo,
            txtPrecioPlatillo,
            txtDescPlatillo,
            txtImagenPath;
    private ComboBox<CategoriaDAO> cbCate;
    private Button btnGuardar;
    private PlatilloDAO objPDAO = null;

    public PlatilloForm(TableView<PlatilloDAO> tbvPlatillo){
        tbvPeliculas = tbvPlatillo;
        CrearGUI();
        this.setScene(escena);
        this.setTitle("Altas y modificaciones de Platillos");
        this.show();
    }

    public void CrearGUI() {
        vBox = new VBox();
        lblTitulo = new Label("Platillo");
        lblTitulo.setId("main-header-label");
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> GuardarPlatillo());
        btnGuardar.getStyleClass().addAll( "success", "lg");
        txtNomPlatillo = new TextField();
        txtNomPlatillo.setPromptText("Introduce el nombre del platillo");
        txtPrecioPlatillo = new TextField();
        txtPrecioPlatillo.setPromptText("Introduce el precio");
        txtDescPlatillo = new TextField();
        txtDescPlatillo.setPromptText("Descripción (Opcional)");
        txtImagenPath = new TextField();
        txtImagenPath.setPromptText("Introduce la ruta de la imagen");
        cbCate = new ComboBox<CategoriaDAO>(new CategoriaDAO().seleccionar());
        cbCate.getStyleClass().addAll("primary", "sm");
        vBox.getChildren().addAll(lblTitulo, txtNomPlatillo, txtPrecioPlatillo, txtDescPlatillo, txtImagenPath, cbCate, btnGuardar);
        vBox.setId("form-container");
        escena = new Scene(vBox, 500, 500);
        escena.getStylesheets().add(getClass().getResource("../CSS/bootstrap3.css").toExternalForm());
        escena.getStylesheets().add(getClass().getResource("../CSS/dashboard.css").toExternalForm());
    }

    public void GuardarPlatillo() {
        String nomb = txtNomPlatillo.getText();
        int precio = Integer.parseInt(txtPrecioPlatillo.getText()) ;
        String desc = txtDescPlatillo.getText();
        String imagePath = txtImagenPath.getText();
        int cate = cbCate.getSelectionModel().getSelectedIndex() + 1;

        if(objPDAO == null) {
            objPDAO = new PlatilloDAO();
            objPDAO.setNombrePlatillo(nomb);
            objPDAO.setPrecio(precio);
            objPDAO.setDescripcionPlatillo(desc);
            objPDAO.setImagen(imagePath);
            objPDAO.setIdCategoria(cate);
            objPDAO.insertar();
        }else {
            objPDAO.setNombrePlatillo(nomb);
            objPDAO.setPrecio(precio);
            objPDAO.setDescripcionPlatillo(desc);
            objPDAO.setImagen(imagePath);
            objPDAO.setIdCategoria(cate);
            objPDAO.actualizar();
        }
        tbvPeliculas.setItems(objPDAO.seleccionar());
        tbvPeliculas.refresh();
        this.close();
    }

    public void setPlatilloDAO(PlatilloDAO objPDAO) {
        this.objPDAO = objPDAO;
        txtNomPlatillo.setText(objPDAO.getNombrePlatillo());
        txtPrecioPlatillo.setText(String.valueOf(objPDAO.getPrecio()));
        txtDescPlatillo.setText(objPDAO.getDescripcionPlatillo());
        txtImagenPath.setText(objPDAO.getImagen());
        cbCate.getSelectionModel().select(objPDAO.getIdCategoria()-1);
    }
}
