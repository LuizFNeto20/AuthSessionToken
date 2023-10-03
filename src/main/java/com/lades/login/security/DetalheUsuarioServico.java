package com.lades.login.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lades.login.modelo.Papel;
import com.lades.login.modelo.Usuario;
import com.lades.login.repository.UsuarioRepository;

@Component
public class DetalheUsuarioServico implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DetalheUsuarioServico(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(username);

        if (usuario != null && usuario.isAtivo()) {
            Set<GrantedAuthority> papeisDoUsuario = new HashSet<>();
            for (Papel papel : usuario.getPapeis()) {
                GrantedAuthority pp = new SimpleGrantedAuthority(papel.getPapel());
                papeisDoUsuario.add(pp);
            }

            return new Usuario(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getCpf(),
                    usuario.getDataNascimento(),
                    usuario.getEmail(),
                    usuario.getPassword(),
                    usuario.getLogin(),
                    usuario.isAtivo(),
                    usuario.getCreateAt(),
                    papeisDoUsuario);
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}