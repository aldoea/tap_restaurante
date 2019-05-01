package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MesaDAO {
    private int idMesa;
    private String nombreMesa;
    private String estado;
    private final ObservableList<String> mesas = FXCollections.observableArrayList();


    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getNombreMesa() {
        return nombreMesa;
    }

    public void setNombreMesa(String nombreMesa) {
        this.nombreMesa = nombreMesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ObservableList<String> getMesas() {
        String query = "SELECT * from mesa";
        try {
            PreparedStatement preparedStatement = Conexion.con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                mesas.add(String.valueOf(resultSet.getString("nombreMesa")));
            }
            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            System.err.println("An error happens " + e.toString());
        }

        return mesas;
    }
}
