package com.fiap.challenge.application.usuario.dto;

public record CriarUsuarioCommand(
        String nome,
        String email,
        String login,
        String senha
) {
}