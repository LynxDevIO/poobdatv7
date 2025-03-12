package dev.phil.poobdatv7.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbConnection {
    private static volatile DbConnection instance;
    private static Connection connection;
    public static boolean conectado;

    private DbConnection()  {
        try {
            conectar();
            conectado = true;
        } catch (ClassNotFoundException | SQLException e) {
            conectado = false;
        }
    }

    private void conectar() throws ClassNotFoundException, SQLException {
        String URL = "jdbc:postgresql://localhost/formulario";
        String USER = "postgres";
        String PASS = "postgres";
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USER, PASS);
    }

    public static DbConnection getInstance() {
        DbConnection i = instance;
        if (i != null) {
            return i;
        }

        synchronized (DbConnection.class) {
            if (instance == null) {
                instance = new DbConnection();
            }
            return instance;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static boolean isConectado() {
        return conectado;
    }
}
