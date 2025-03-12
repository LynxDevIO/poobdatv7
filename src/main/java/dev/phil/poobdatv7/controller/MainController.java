package dev.phil.poobdatv7.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MainController {
    @FXML
    private TextField tfNome;
    @FXML
    private PasswordField pfSenha;
    @FXML
    private Button btnEntrar;

    @FXML
    private void btnEntrarOnAction() {
        String nome = tfNome.getText();
        String senha = pfSenha.getText();
        if (nome.isEmpty() || senha.isEmpty()) {
            mostrarAviso("Campo(s) vazio(s)!");
        }
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
}