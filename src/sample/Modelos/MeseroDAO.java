package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MeseroDAO {
    private int idMesero;
    private int idUsuario;
    private final ObservableList<String> meseros = FXCollections.observableArrayList();

    public int getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(int idMesero) {
        this.idMesero = idMesero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public ObservableList<String> getMeseros() {
        String query = "SELECT * from mesero";
        try {
            PreparedStatement preparedStatement = Conexion.con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                meseros.add(String.valueOf(resultSet.getInt("idMesero")));
            }
            preparedStatement.close();
            resultSet.close();

        } catch (Exception e) {
            System.err.println("An error happens " + e.toString());
        }

        return meseros;
    }
}
