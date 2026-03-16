package com.fiap.challenge.application.usuario;

import com.fiap.challenge.application.usuario.dto.AlterarSenhaCommand;
import com.fiap.challenge.application.usuario.dto.AtualizarUsuarioCommand;
import com.fiap.challenge.application.usuario.dto.CriarUsuarioCommand;
import com.fiap.challenge.domain.usuario.StatusUsuario;
import com.fiap.challenge.domain.usuario.TipoUsuario;
import com.fiap.challenge.domain.usuario.Usuario;
import com.fiap.challenge.infrastructure.persistence.usuario.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Usuario criar(CriarUsuarioCommand command) {
        if (usuarioRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (usuarioRepository.existsByLogin(command.login())) {
            throw new IllegalArgumentException("Login já cadastrado.");
        }

        Usuario usuario = new Usuario(
                UUID.randomUUID(),
                command.nome(),
                command.email(),
                command.login(),
                passwordEncoder.encode(command.senha()),
                TipoUsuario.USUARIO,
                StatusUsuario.ATIVO,
                true
        );

        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    }

    @Transactional
    public Usuario atualizar(UUID id, AtualizarUsuarioCommand command) {
        Usuario usuario = buscarPorId(id);

        if (usuarioRepository.existsByEmailAndIdNot(command.email(), id)) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        usuario.atualizarDados(command.nome(), command.email());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(UUID id, AlterarSenhaCommand command) {
        Usuario usuario = buscarPorId(id);

        if (!passwordEncoder.matches(command.senhaAtual(), usuario.getSenhaHash())) {
            throw new IllegalArgumentException("Senha atual inválida.");
        }

        usuario.alterarSenha(passwordEncoder.encode(command.novaSenha()));
        usuarioRepository.save(usuario);
    }
}