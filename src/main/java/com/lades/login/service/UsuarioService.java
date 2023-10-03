package com.lades.login.service;

import com.lades.login.modelo.Usuario;

public interface UsuarioService {

    public Usuario buscarUsuarioPorId(Long id);

    public Usuario buscarUsuarioPorLogin(String login);

    public Usuario gravarUsuario(Usuario usuario);
}
