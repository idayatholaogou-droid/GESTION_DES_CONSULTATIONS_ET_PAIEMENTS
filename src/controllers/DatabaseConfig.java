/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Idayath
 */
public class DatabaseConfig {

    private static final String URL      = "jdbc:postgresql://localhost:5433/GESTION_DE_CONSULTATIONS_ET_DE PAIEMENTS";
    private static final String USER     = "postgres";
    private static final String PASSWORD = "1412";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver introuvable : " + e.getMessage());
        }
    }
}
    

