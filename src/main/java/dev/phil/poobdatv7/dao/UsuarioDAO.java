package dev.phil.poobdatv7.dao;

import dev.phil.poobdatv7.database.DbConnection;
import dev.phil.poobdatv7.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO implements IUsuarioDAO {
    private Connection connection;
    public UsuarioDAO() {
        connection = DbConnection.getInstance().getConnection();
    }

    public void criarTabela() throws SQLException {
        if (connection != null) {
            String sql = """
            CREATE TABLE IF NOT EXISTS usuarios (
                usuario_id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                usuario_nome VARCHAR(100) NOT NULL UNIQUE,
                usuario_senha VARCHAR(255) NOT NULL
            )
        """;
            Statement st = connection.createStatement();
            st.execute(sql);
            st.close();
        }
    }

    @Override
    public void inserir(Usuario usuario) throws SQLException {
        if (connection != null) {
            String sql = "INSERT INTO usuarios (usuario_nome, usuario_senha) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getUsuarioNome());
            ps.setString(2, usuario.getUsuarioSenha());
            ps.execute();
            ps.close();
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(long id) throws SQLException {
        if (connection != null) {
            String sql = "SELECT * FROM usuarios WHERE usuario_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Usuario usuario = null;
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getLong("usuario_id"),
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_senha")
                );
            }
            rs.close();
            ps.close();
            return Optional.ofNullable(usuario);
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        if (connection != null) {
            String sql = "SELECT * FROM usuarios";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("usuario_id"),
                        rs.getString("usuario_nome"),
                        rs.getString("usuario_senha")
                );
                usuarios.add(usuario);
            }
            rs.close();
            st.close();
        }
        return usuarios;
    }

    @Override
    public void atualizar(Usuario usuario) throws SQLException {
        if (connection != null) {
            String sql = "UPDATE usuarios SET usuario_nome = ?, usuario_senha = ? WHERE usuario_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getUsuarioNome());
            ps.setString(2, usuario.getUsuarioSenha());
            ps.setLong(3, usuario.getUsuarioId());
            ps.executeUpdate();
            ps.close();
        }
    }

    @Override
    public void deletar(long id) throws SQLException {
        if (connection != null) {
            String sql = "DELETE FROM usuarios WHERE usuario_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        }
    }
} 