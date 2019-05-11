package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.Statement;

public class dashboardDAO {

    public static ObservableList<XYChart.Series<String, Number>> compararSemanaAnterior() {
        ObservableList<XYChart.Series<String, Number>> dataOL = FXCollections.observableArrayList();

        XYChart.Series<String, Number> pedidosSemana = new XYChart.Series<String, Number>();
        pedidosSemana.setName("Pedidos por semana");

        String consulta = "SELECT WEEK(fecha) as semana, count(*) as totalSemana FROM orden\n" +
                "WHERE WEEK(fecha) BETWEEN WEEK(NOW())-1 AND WEEK(NOW())\n" +
                "GROUP BY (WEEK(fecha))\n" +
                "ORDER BY (WEEK(fecha)) DESC";
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                pedidosSemana.getData().add(new XYChart.Data("Semana " + res.getInt("semana"), res.getInt("totalSemana")));
            }
        } catch (Exception e) {
            System.out.println("Error dashboard comparativa semana anterior y actual BARCHART data query: " + e);
        }
        dataOL.add(pedidosSemana);
        return dataOL;
    }

    public static ObservableList<PieChart.Data> platilloPerMes() {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        String consulta = "SELECT nombrePlatillo, count(*) as perPlatillo " +
                "FROM orden o JOIN platillo p ON p.idPlatillo=o.idPlatillo " +
                "WHERE fecha >= LAST_DAY(CURRENT_DATE) + INTERVAL 1 DAY - INTERVAL 1 MONTH AND fecha < LAST_DAY(CURRENT_DATE) + INTERVAL 1 DAY " +
                "GROUP BY 1";
        try {
            Statement stmt = Conexion.con.createStatement();
            ResultSet res = stmt.executeQuery(consulta);
            while (res.next()) {
                pieChartData.add(new PieChart.Data(
                        res.getString("nombrePlatillo"),
                        res.getInt("perPlatillo")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error dashboard platillos pedidos en el mes PIECHART data query: " + e);
        }
        return pieChartData;
    }
}
