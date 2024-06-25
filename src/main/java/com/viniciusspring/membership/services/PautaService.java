package com.viniciusspring.membership.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viniciusspring.membership.entities.Pauta;
import com.viniciusspring.membership.exceptions.PautaException;
import com.viniciusspring.membership.repositories.PautaRepository;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;

    public Optional<Pauta> findById(Long id) throws PautaException {
        Optional<Pauta> pauta = repository.findById(id);
        if (pauta.isEmpty()) {
            throw new PautaException("Pauta não encontrada!", id);
        }
        return pauta;
    }

    @Transactional
    public List<Pauta> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Pauta create(Pauta pauta) {
        return repository.save(pauta);
    }

    @Transactional
    public Pauta update(Pauta pauta) throws PautaException {
        Optional<Pauta> existingPautaOpt = findById(pauta.getId());

        if (existingPautaOpt.isEmpty()) {
            throw new PautaException("Pauta não encontrada!", pauta.getId());
        }

        Pauta existingPauta = existingPautaOpt.get();
        existingPauta.setPauta(pauta.getPauta());
        existingPauta.setLocalDateTime(pauta.getLocalDateTime());

        return repository.save(existingPauta);
    }

    public void delete(Long id) throws PautaException {
        Optional<Pauta> pautaOpt = findById(id);

        if (pautaOpt.isEmpty()) {
            throw new PautaException("Pauta não encontrada!", id);
        }

        try {
            repository.deleteById(id);
        } 
        catch (Exception e) {
            throw new DataIntegrityViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}

