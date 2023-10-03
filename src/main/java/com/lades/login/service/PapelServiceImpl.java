package com.lades.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lades.login.exceptions.ResourceNotFound;
import com.lades.login.modelo.Papel;
import com.lades.login.repository.PapelRepository;

@Service
public class PapelServiceImpl implements PapelService {

    @Autowired
    private PapelRepository papelRepository;

    public PapelServiceImpl(PapelRepository papelRepository) {
        this.papelRepository = papelRepository;
    }

    @Override
    public Papel buscarPapelPorId(Long id) {
        Optional<Papel> papel = papelRepository.findById(id);
        return papel.orElseThrow(() -> new ResourceNotFound(id));
    }

    @Override
    public Papel buscarPapel(String papel) {
        Papel pp = papelRepository.findByPapel(papel);
        return pp;
    }

}
