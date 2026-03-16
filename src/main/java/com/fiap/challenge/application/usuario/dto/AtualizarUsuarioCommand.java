package com.fiap.challenge.application.usuario.dto;

public record AtualizarUsuarioCommand(
        String nome,
        String email
) {
}