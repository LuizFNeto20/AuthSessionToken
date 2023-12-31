package com.lades.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lades.login.modelo.Papel;

public interface PapelRepository extends JpaRepository< Papel, Long>{
    Papel findByPapel(String papel);
}
