package com.fiap.challenge.interfaces.api;

import com.fiap.challenge.infrastructure.security.SecurityUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/me")
public class MeController {

    @GetMapping
    public Map<String, Object> me(@AuthenticationPrincipal SecurityUserDetails userDetails) {
        return Map.of(
                "id", userDetails.getId(),
                "login", userDetails.getUsername(),
                "perfil", userDetails.getUsuario().getTipoUsuario().name()
        );
    }
}