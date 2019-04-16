package sample.Vistas;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import sample.Modelos.CategoriaDAO;
import sample.Modelos.OrdenDAO;

import java.util.ArrayList;

public class Menu extends Parent {
    private BorderPane panel;
    private TabPane tabPane;
    private ArrayList<CategoriaDAO> rCategoriaDAOS;
    private CategoriaDAO rCategoriaDAO;
    private ObservableList<OrdenDAO> ordenes = FXCollections.observableArrayList();

    public BorderPane CrearMenu() {
        panel = new BorderPane();
        tabPane = new TabPane();
        CreatTabs2();
        panel.setTop(tabPane);
        panel.addEventHandler(Platillo.ITEM_ADD, new EventHandler<PlatilloEvent>() {
            @Override
            public void handle(PlatilloEvent event) {
                ordenes.clear();
                ordenes.addAll(new OrdenDAO().seleccionar());
            }
        });
        return panel;
    }

    public void CreatTabs2() {
        rCategoriaDAOS = new ArrayList<CategoriaDAO>();
        rCategoriaDAO = new CategoriaDAO();
        rCategoriaDAOS = rCategoriaDAO.categoriaDAOS();
        for (int i = 0; i < rCategoriaDAOS.size(); i++) {
            Tab tab = new Tab();
            tab.setId(String.valueOf(i));
            tab.setStyle("-fx-border-color: darkgray;");
            tab.setText(rCategoriaDAOS.get(i).getNombreCategoria());
            tab.closableProperty().setValue(false);
            tab.setContent(new Platillo(ordenes).CPlatillo(i + 1));
            tabPane.getTabs().add(tab);
        }
        ordenes.addAll(new OrdenDAO().seleccionar());
    }


}
