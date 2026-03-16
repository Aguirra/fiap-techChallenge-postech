package com.fiap.challenge.interfaces.api.usuario;

import com.fiap.challenge.application.usuario.UsuarioService;
import com.fiap.challenge.application.usuario.dto.AlterarSenhaCommand;
import com.fiap.challenge.application.usuario.dto.AtualizarUsuarioCommand;
import com.fiap.challenge.application.usuario.dto.CriarUsuarioCommand;
import com.fiap.challenge.domain.usuario.Usuario;
import com.fiap.challenge.interfaces.api.usuario.dto.AlterarSenhaRequest;
import com.fiap.challenge.interfaces.api.usuario.dto.AtualizarUsuarioRequest;
import com.fiap.challenge.interfaces.api.usuario.dto.CriarUsuarioRequest;
import com.fiap.challenge.interfaces.api.usuario.dto.UsuarioResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @GetMapping("/painel")
    public String painelUsuario() {
        return "Painel do usuário acessado com sucesso.";
    }

    @PostMapping("/criar-usuario")
    public ResponseEntity<UsuarioResponse> criar(@RequestBody @Valid CriarUsuarioRequest request) {
        Usuario usuario = usuarioService.criar(
                new CriarUsuarioCommand(
                        request.nome(),
                        request.email(),
                        request.login(),
                        request.senha()
                )
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.from(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid AtualizarUsuarioRequest request
    ) {
        Usuario usuario = usuarioService.atualizar(
                id,
                new AtualizarUsuarioCommand(
                        request.nome(),
                        request.email()
                )
        );

        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @PutMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(
            @PathVariable UUID id,
            @RequestBody @Valid AlterarSenhaRequest request
    ) {
        usuarioService.alterarSenha(
                id,
                new AlterarSenhaCommand(
                        request.senhaAtual(),
                        request.novaSenha()
                )
        );
    }
}