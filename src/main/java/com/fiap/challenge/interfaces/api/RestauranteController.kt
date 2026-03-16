package com.fiap.challenge.interfaces.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/restaurantes")
class RestauranteController {
    @GetMapping("/painel")
    fun painelRestaurante(): String {
        return "Painel do restaurante acessado com sucesso."
    }
}