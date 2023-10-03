package com.lades.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lades.login.modelo.Usuario;
import com.lades.login.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/index")
    public String usuarioIndex(Model model) {
        return "/index";
    }

    @GetMapping("/novo")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/CadastrarUsuario";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@Valid Usuario usuario, BindingResult result,
            RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return "/CadastrarUsuario";
        }

        Usuario usr = usuarioService.buscarUsuarioPorLogin(usuario.getLogin());
        if (usr != null) {
            return "/CadastrarUsuario";
        }

        usuarioService.gravarUsuario(usuario);

        return "/login";
    }

}
