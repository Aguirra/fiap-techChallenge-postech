package com.fiap.challenge.interfaces.api;

import com.fiap.challenge.infrastructure.security.SecurityUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal SecurityUserDetails userDetails) {
        if (userDetails == null) {
            return Map.of("autenticado", false);
        }

        return Map.of(
                "autenticado", true,
                "id", userDetails.getId(),
                "login", userDetails.getUsername(),
                "perfil", userDetails.getUsuario().getTipoUsuario().name()
        );
    }
}