package com.fiap.challenge.infrastructure.security

import com.fiap.challenge.domain.usuario.Usuario
import com.fiap.challenge.infrastructure.persistence.usuario.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.function.Function
import java.util.function.Supplier

@Service
class CustomUserDetailsService(private val usuarioRepository: UsuarioRepository) : UserDetailsService {
    @kotlin.Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        return usuarioRepository.findByLogin(username)
            .map<SecurityUserDetails?>(Function { usuario: Usuario? -> SecurityUserDetails(usuario) })
            .orElseThrow<UsernameNotFoundException?>(Supplier { UsernameNotFoundException("Usuário não encontrado para login: " + username) })
    }
}