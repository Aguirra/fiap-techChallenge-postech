package com.fiap.challenge.interfaces.api.usuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterarSenhaRequest(
        @NotBlank
        String senhaAtual,

        @NotBlank
        @Size(min = 6, max = 50)
        String novaSenha
) {
}