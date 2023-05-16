package com.gft.sistema_programa_starter.services;


import com.gft.sistema_programa_starter.entities.Starter;
import com.gft.sistema_programa_starter.config.exception.EntityNotFoundException;
import com.gft.sistema_programa_starter.repositories.StarterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StarterService {


    private final StarterRepository starterRepository;

    public StarterService(StarterRepository starterRepository) {
        this.starterRepository = starterRepository;
    }

    //--------------------------------------------------------------------
    public Starter salvarStarter(Starter starter) {
        return starterRepository.save(starter);
    }
    //--------------------------------------------------------------------

    public Page<Starter> listarTodosOsStarters(Pageable pageable) {
        return starterRepository.findAll(pageable);
    }

    //--------------------------------------------------------------------
    public Starter buscarStarter(Long id) {
        Optional<Starter> optional = starterRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Starter não encontrado"));
    }

    //--------------------------------------------------------------------
    public Starter atualizarStarter(Starter starter, Long id) {
        Starter starterOriginal = this.buscarStarter(id);
        starter.setId(starterOriginal.getId());
        return starterRepository.save(starter);
    }

    //--------------------------------------------------------------------
    public void excluirStarter(Long id) {
        Starter starterOriginal = this.buscarStarter(id);
        starterRepository.delete(starterOriginal);
    }

 //--------------------------------------------------------------------

    public Starter buscarStarterNome(String nome) {
        Optional<Starter> optional = starterRepository.findByNome(nome);
        return optional.orElseThrow(() -> new EntityNotFoundException("Starter não encontrado"));
    }
}
