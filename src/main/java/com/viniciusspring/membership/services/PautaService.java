package com.viniciusspring.membership.services;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viniciusspring.membership.entities.Pauta;
import com.viniciusspring.membership.exceptions.PautaException;
import com.viniciusspring.membership.repositories.PautaRepository;

@Service
public class PautaService {
	
	private Timer votingTimer;
	
    @Autowired
    private PautaRepository pautaRepository;

    public Optional<Pauta> findById(Long id) throws PautaException {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        if (pauta.isEmpty()) {
            throw new PautaException("Pauta não encontrada!", id);
        }
        return pauta;
    }

    @Transactional
    public List<Pauta> findAll() {
        return pautaRepository.findAll();
    }

    @Transactional
    public Pauta create(Pauta pauta) {
        return pautaRepository.save(pauta);
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

        return pautaRepository.save(existingPauta);
    }

    public void delete(Long id) throws PautaException {
        Optional<Pauta> pautaOpt = findById(id);

        if (pautaOpt.isEmpty()) {
            throw new PautaException("Pauta não encontrada!", id);
        }

        try {
        	pautaRepository.deleteById(id);
        } 
        catch (Exception e) {
            throw new DataIntegrityViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
    
    public void openVotingSession(Pauta pauta, Long votingDurationInSeconds) {
        if (votingTimer != null) {
            votingTimer.cancel();
        }
        votingTimer = new Timer();
        TimerTask endVotingTask = new TimerTask() {
            @Override
            public void run() {
                closeVotingSession(pauta);
            }
        };

        votingTimer.schedule(endVotingTask, votingDurationInSeconds * 1000);
        System.out.println("Sessão de votação aberta por " + votingDurationInSeconds + " segundos para a pauta: " + pauta.getPauta());
    }

    public void openVotingSession(Pauta pauta) {
    	openVotingSession(pauta, 60L);
    }

    public void closeVotingSession(Pauta pauta) {
    	if (votingTimer != null) {
    		votingTimer.cancel();
    		votingTimer = null;
    		System.out.println("Sessão de votação fechada para a pauta: " + pauta.getPauta());
    	}
    }
}

