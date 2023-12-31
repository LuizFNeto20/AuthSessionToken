package com.lades.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lades.login.dto.LoginResponseDTO;
import com.lades.login.modelo.Usuario;
import com.lades.login.security.TokenService;

@Controller
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponseDTO> loginToken(@RequestParam String username, @RequestParam String password) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(username, password);
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    /*@PostMapping("/api/login")
    public ResponseEntity<LoginResponseDTO> loginToken(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }*/

}