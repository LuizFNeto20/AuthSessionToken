package com.lades.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lades.login.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}
