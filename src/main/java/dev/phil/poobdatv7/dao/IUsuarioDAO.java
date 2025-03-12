package dev.phil.poobdatv7.dao;

import dev.phil.poobdatv7.model.Usuario;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IUsuarioDAO {
    void criarTabela() throws SQLException;
    void inserir(Usuario usuario) throws SQLException;
    Optional<Usuario> buscarPorId(long id) throws SQLException;
    List<Usuario> listarTodos() throws SQLException;
    void atualizar(Usuario usuario) throws SQLException;
    void deletar(long id) throws SQLException;
}