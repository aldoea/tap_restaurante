package sample.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion{
    private static String host = "localhost";
    private static String BD = "restaurante";
    private static String user = "topicos";
    private static String pass = "elbarto123";
    public static Connection con;

    public static void crearConexion() {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mariadb://"+host+"/"+BD, user, pass);
        }catch (Exception e) {
            System.err.println("An error happens" + e.toString());
        }
    }
}