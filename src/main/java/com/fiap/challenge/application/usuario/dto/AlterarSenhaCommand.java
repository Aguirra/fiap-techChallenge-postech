package com.fiap.challenge.application.usuario.dto;

public record AlterarSenhaCommand(
        String senhaAtual,
        String novaSenha
) {
}