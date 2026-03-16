package com.fiap.challenge.interfaces.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/health")
    public String healthAdmin() {
        return "Área administrativa acessada com sucesso.";
    }
}