package com.fiap.challenge.domain.usuario;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuario", schema = "app")
public class Usuario {

    @Id
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 80)
    private String login;

    @Column(name = "senha_hash", nullable = false, length = 255)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false, length = 30)
    private TipoUsuario tipoUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private StatusUsuario status;

    @Column(name = "data_ultima_alteracao", nullable = false)
    private LocalDateTime dataUltimaAlteracao;

    @Column(nullable = false)
    private Boolean ativo;

    public Usuario() {
    }

    public Usuario(
            UUID id,
            String nome,
            String email,
            String login,
            String senhaHash,
            TipoUsuario tipoUsuario,
            StatusUsuario status,
            Boolean ativo
    ) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senhaHash = senhaHash;
        this.tipoUsuario = tipoUsuario;
        this.status = status;
        this.ativo = ativo;
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (dataUltimaAlteracao == null) {
            dataUltimaAlteracao = LocalDateTime.now();
        }
        if (ativo == null) {
            ativo = true;
        }
    }

    @PreUpdate
    public void preUpdate() {
        dataUltimaAlteracao = LocalDateTime.now();
    }

    public void atualizarDados(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    public void alterarSenha(String novaSenhaHash) {
        this.senhaHash = novaSenhaHash;
        this.dataUltimaAlteracao = LocalDateTime.now();
    }

    public boolean podeAutenticar() {
        return Boolean.TRUE.equals(this.ativo) && this.status == StatusUsuario.ATIVO;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getLogin() { return login; }
    public String getSenhaHash() { return senhaHash; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public StatusUsuario getStatus() { return status; }
    public LocalDateTime getDataUltimaAlteracao() { return dataUltimaAlteracao; }
    public Boolean getAtivo() { return ativo; }

    public void setId(UUID id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setLogin(String login) { this.login = login; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    public void setStatus(StatusUsuario status) { this.status = status; }
    public void setDataUltimaAlteracao(LocalDateTime dataUltimaAlteracao) { this.dataUltimaAlteracao = dataUltimaAlteracao; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}