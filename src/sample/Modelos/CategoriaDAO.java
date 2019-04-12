package sample.Modelos;

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
}