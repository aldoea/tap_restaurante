package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrdenDAO {
    private int idOrden;
    private int idMesa;
    private String estado;
    private String fecha;
    private double precio;
    private int idMesero;
    private int idPlatillo;

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(int idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

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

    public double getPrecio() {
        return precio;
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
                objODAO.idPlatillo = res.getInt("idPlatillo");
                objODAO.idOrden   = res.getInt("idOrden");
                objODAO.idMesa  = res.getInt("idMesa");
                objODAO.estado     = res.getString("estado");
                objODAO.fecha = res.getString("fecha");
                objODAO.precio        = res.getDouble("precio");
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
                "precio," +
                "idMesero," +
                "idPlatillo)" +
                " values("+idMesa+",'"+estado+"',"+"'"+fecha+"',"+precio+","+idMesero+","+idPlatillo+")";
        try{
            Statement stmt = Conexion.con.createStatement();
            stmt.execute(query);
        }catch (Exception e) {
            System.err.println("An error happens " + e.toString());
        }
    }

    public void eliminar() {
        String query = "DELETE FROM orden WHERE idOrden = "+idOrden;
        try{
            Statement stmt = Conexion.con.createStatement();
            stmt.execute(query);
        }catch (Exception e) {
            System.err.println("An error happens" + e.toString());
        }

        seleccionar();
    }

    public double total(int mesa) {
        double total = 0;
        String consulta = "SELECT SUM(precio) as precio FROM orden WHERE idMesa = " + mesa;
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            total = res.getDouble("precio");
        } catch (Exception e) {
            System.out.println("Error PlatilloDAO");
        }

        return total;
    }

    public String ticket(int mesa) {
        String consulta = "SELECT *  FROM orden WHERE idMesa = " + mesa;
        String ticket = "";
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            PlatilloDAO platilloDAO = new PlatilloDAO();
            ticket = "Ticket Mesa:" + res.getInt("idMesa" + "\n");
            ticket = ticket + "---------------------------------- \n";
            while (res.next()) {
                ticket = ticket + res.getInt("idPlatillo") + " ";
                ticket = ticket + platilloDAO.nombre(res.getInt("idPlatillo")) + "   ";
                ticket = ticket + res.getDouble("precio");
            }

        } catch (Exception e) {
            System.out.println("Error ticket");
        }
        return ticket;
    }

    public void Cobrar(int mesa) {
        String query = "UPDATE orden set estado = " + "\"Pagado\"" + " WHERE idMesa = " + mesa;
        try{
            Statement stmt = Conexion.con.createStatement();
            stmt.execute(query);
        }catch (Exception e) {
            System.err.println("An error happens" + e.toString());
        }
    }
}
