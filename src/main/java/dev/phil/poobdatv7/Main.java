package dev.phil.poobdatv7;

import dev.phil.poobdatv7.dao.UsuarioDAO;
import dev.phil.poobdatv7.view.MainView;
import javafx.application.Application;

import java.sql.SQLException;

public class Main extends MainView {
    // Iniciar o banco de dados e criar a tabela usuario
    private static void initialize() throws SQLException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.criarTabela();
    }

    public static void main(String[] args) throws SQLException {
        initialize();

        // Iniciar a aplicação
        Application.launch(MainView.class, args);
    }
}
