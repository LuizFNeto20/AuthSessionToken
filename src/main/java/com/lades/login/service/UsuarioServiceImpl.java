package com.lades.login.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lades.login.exceptions.ResourceNotFound;
import com.lades.login.modelo.Papel;
import com.lades.login.modelo.Usuario;
import com.lades.login.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PapelService papelService;

    @Autowired
    private BCryptPasswordEncoder criptografia;

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ResourceNotFound(id));
    }

    @Override
    public Usuario buscarUsuarioPorLogin(String Login) {
        Usuario usuario = usuarioRepository.findByLogin(Login);
        return usuario;
    }

    @Override
    public Usuario gravarUsuario(Usuario usuario) {
        Papel papel = papelService.buscarPapel("USER");
        List<Papel> papeis = new ArrayList<>();
        papeis.add(papel);
        usuario.setPapeis(papeis);

        String senhaCriptografada = criptografia.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }
}
