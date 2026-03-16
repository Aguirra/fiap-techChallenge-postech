package com.fiap.challenge.interfaces.api.usuario.dto;

import com.fiap.challenge.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        String login,
        String tipoUsuario,
        String status,
        Boolean ativo,
        LocalDateTime dataUltimaAlteracao
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getTipoUsuario().name(),
                usuario.getStatus().name(),
                usuario.getAtivo(),
                usuario.getDataUltimaAlteracao()
        );
    }
}