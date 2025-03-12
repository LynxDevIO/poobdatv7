package dev.phil.poobdatv7.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbConnection {
    private static volatile DbConnection instance;
    private static Connection connection;
    private static boolean conectado = false;

    private DbConnection() {
        conectar();
    }

    private void conectar() {
        try {
            String URL = "jdbc:postgresql://localhost/formulario";
            String USER = "postgres";
            String PASS = "postgres";

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
            conectado = true;
        } catch (ClassNotFoundException | SQLException e) {
            conectado = false;
        }
    }

    private static boolean verificarConexao() {
        if (connection != null) {
            try {
                if (connection.isValid(1)) {
                    conectado = true;
                    return true;
                }
            } catch (SQLException e) {
                conectado = false;
            }
        }
        return reconectar();
    }

    private static synchronized boolean reconectar() {
        if (connection != null) {
            fecharConexao();
        }

        try {
            instance.conectar();
            return conectado;
        } catch (Exception e) {
            conectado = false;
            return false;
        }
    }

    public static void fecharConexao() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
                conectado = false;
            }
        }
    }

    public static DbConnection getInstance() {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static boolean isConectado() {
        return verificarConexao();
    }
}