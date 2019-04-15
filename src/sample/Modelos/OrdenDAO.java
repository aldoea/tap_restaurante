package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenDAO {
    private int idOrden;
    private int idMesa;
    private String estado;
    private String fecha;
    private double total;
    private int idMesero;

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(int idMesero) {
        this.idMesero = idMesero;
    }

    public ObservableList<OrdenDAO> seleccionar(){

        ObservableList<OrdenDAO> lista = FXCollections.observableArrayList();
        OrdenDAO objODAO = null;

        String consulta = "SELECT * FROM orden";
        try{
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            while(res.next()){
                objODAO = new OrdenDAO();
                objODAO.idOrden   = res.getInt("idOrden");
                objODAO.idMesa  = res.getInt("idMesa");
                objODAO.estado     = res.getString("estado");
                objODAO.fecha = res.getString("fecha");
                objODAO.total        = res.getDouble("total");
                objODAO.idMesero  = res.getInt("idMesero");
                lista.add(objODAO);
            }
        }
        catch (Exception e){
            System.out.println("Error");
        }

        return lista;
    }

    public void insertar() {

        String query = "INSERT INTO orden(" +
                "idMesa," +
                "estado," +
                "fecha," +
                "total," +
                "idMesero)" +
                " values("+idMesa+",'"+estado+"',"+"'"+fecha+"',"+total+","+idMesero+")";
        System.out.println(query);
        try{
            Statement stmt = Conexion.con.createStatement();
            stmt.execute(query);
        }catch (Exception e) {
            System.err.println("An error happens " + e.toString());
        }
    }
}
