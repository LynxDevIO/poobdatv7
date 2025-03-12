package dev.phil.poobdatv7.dto;

import dev.phil.poobdatv7.model.Usuario;

public class UsuarioDTO {
    private long id;
    private String nome;
    private String senha;

    public UsuarioDTO() {
        //
    }

    public UsuarioDTO(long id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public static UsuarioDTO fromEntity(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getUsuarioId(),
            usuario.getUsuarioNome(),
            usuario.getUsuarioSenha()
        );
    }

    public Usuario toEntity() {
        return new Usuario(id, nome, senha);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
