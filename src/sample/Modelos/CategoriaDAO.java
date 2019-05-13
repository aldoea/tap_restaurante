package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDAO {
    private int idCategoria;
    private String nombreCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public ArrayList<CategoriaDAO> categoriaDAOS(){

        ArrayList<CategoriaDAO> lista = new ArrayList<CategoriaDAO>();
        CategoriaDAO objRCDAO = null;
        String consulta = "SELECT idCategoria, nombreCategoria " +
                "FROM categoria ";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            while(res.next()){
                objRCDAO = new CategoriaDAO();
                objRCDAO.idCategoria   = res.getInt("idCategoria");
                objRCDAO.nombreCategoria  = res.getString("nombreCategoria");
                lista.add(objRCDAO);
            }
        }
        catch (Exception e){
            System.out.println("Error CategoriaDAO");
        }

        return lista;
    }

    public ObservableList<CategoriaDAO> seleccionar() {
        String query = "SELECT * FROM categoria ORDER BY idCategoria ASC ";
        ObservableList<CategoriaDAO> lista = FXCollections.observableArrayList();
        CategoriaDAO objCate = null;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()){
                objCate = new CategoriaDAO();
                objCate.idCategoria = res.getInt("idCategoria");
                objCate.nombreCategoria = res.getString("nombreCategoria");
                lista.add(objCate);
            }
        }catch (Exception e) {
            System.err.println("An error happens" + e.toString());
        }
        return lista;
    }

    @Override
    public String toString() {
        return this.nombreCategoria;
    }
}