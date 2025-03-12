package dev.phil.poobdatv7.controller;

import dev.phil.poobdatv7.database.DbConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


public class MainController {
    @FXML
    private TextField tfNome;
    @FXML
    private PasswordField pfSenha;
    @FXML
    private Label lbStatusConexaoBD;
    @FXML
    private Tooltip ttStatusConexaoBD;
    @FXML
    private Button btnEntrar;

    @FXML
    private void initialize() {
        indicarStatusDeConexao();
    }

    private void mostrarAviso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.WARNING, mensagem, ButtonType.CLOSE);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void mostrarConfirmacao(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, mensagem, ButtonType.OK);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void indicarStatusDeConexao() {
        if (DbConnection.isConectado()) {
            lbStatusConexaoBD.setText("conectado");
            lbStatusConexaoBD.setTextFill(Color.color(0, 1,0));
            ttStatusConexaoBD.setText("Conexão com o banco de dados estabelecida com sucesso.");
            mostrarConfirmacao("Conectado ao banco de dados.");
        } else {
            lbStatusConexaoBD.setText("desconectado");
            lbStatusConexaoBD.setTextFill(Color.color(1, 0,0));
            ttStatusConexaoBD.setText("Banco de dados ausente. Por favor, contate o administrador do sistema.");
            mostrarAviso("Não foi possível conectar ao banco de dados.");
        }
    }

    @FXML
    private void btnReloadDBOnAction() {
        DbConnection.getInstance();
        indicarStatusDeConexao();
    }

    @FXML
    private void btnEntrarOnAction() {
        String nome = tfNome.getText();
        String senha = pfSenha.getText();
        if (nome.isEmpty() || senha.isEmpty()) {
            mostrarAviso("Campo(s) vazio(s)!");
        }
    }
}