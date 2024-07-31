/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gustavo.trabajotitulacion.utils;

/**
 *
 * @author gustavo
 */

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/trabajotitulacion";

    private static Connection conn = null;

    public static Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            try {
                // Set Values from .env configuration file
                // Properties enviromentsVariables = ConnectionDB.getProperties();
                // final String USER_DB = (String) enviromentsVariables.get("POSTGRES_USER");
                // final String PASS_DB = (String)
                // enviromentsVariables.get("POSTGRES_PASSWORD");
                // final String DB_NAME = (String) enviromentsVariables.get("POSTGRES_DB");
                final String USER_DB = "postgres";
                // final String DB_NAME = "gestion";
                final String PASS_DB = "admin";
                // Set Driver to Database
                Class.forName(JDBC_DRIVER);
                // STEP 3: Open a connection
                conn = DriverManager.getConnection(DB_URL, USER_DB, PASS_DB);
            } catch (SQLException ex) {
                System.out.println("fallo intentando ingresar credenciales");
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public static void cerrar() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public static Properties getProperties() throws Exception {
        FileReader reader = new FileReader(".env");
        Properties p = new Properties();
        p.load(reader);
        return p;
    }

}
