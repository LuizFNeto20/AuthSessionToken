package com.lades.login.dto;

import java.time.Instant;

public record UsuarioDTO(Long id, String nome, String cpf, Instant dataNascimento, String email,
        String password, String login, boolean ativo, Instant createAt) {

}