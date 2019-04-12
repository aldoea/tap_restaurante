package sample.Modelos;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PlatilloDAO {
    private int idPlatillo;
    private String nombrePlatillo;
    private String descripcionPlatillo;
    private int idCategoria;
    private String imagen;

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public void setNombrePlatillo(String nombrePlatillo) {
        this.nombrePlatillo = nombrePlatillo;
    }

    public String getDescripcionPlatillo() {
        return descripcionPlatillo;
    }

    public void setDescripcionPlatillo(String descripcionPlatillo) {
        this.descripcionPlatillo = descripcionPlatillo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImange(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList<PlatilloDAO> seleccionar(int categoria){

        ArrayList<PlatilloDAO> lista = new ArrayList<PlatilloDAO>();
        PlatilloDAO objPDAO = null;
        String consulta = "SELECT idPlatillo, nombrePlatillo, descripcionPlatillo, idCategoria, imagen " +
                "FROM platillo WHERE idCategoria = "+categoria;
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            while(res.next()){
                objPDAO = new PlatilloDAO();
                objPDAO.idPlatillo   = res.getInt("idPlatillo");
                objPDAO.nombrePlatillo  = res.getString("nombrePlatillo");
                objPDAO.descripcionPlatillo     = res.getString("descripcionPlatillo");
                objPDAO.idCategoria = res.getInt("idCategoria");
                objPDAO.imagen        = res.getString("imagen");
                lista.add(objPDAO);
            }
        }
        catch (Exception e){
            System.out.println("Error PlatilloDAO");
        }

        return lista;
    }
}

