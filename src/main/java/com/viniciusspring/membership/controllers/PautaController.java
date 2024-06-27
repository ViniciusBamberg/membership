package com.viniciusspring.membership.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viniciusspring.membership.entities.Pauta;
import com.viniciusspring.membership.services.PautaService;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@GetMapping
	public List<Pauta> findAll(){
		List<Pauta> pautaObj = pautaService.findAll();
		return pautaObj;
	}
	
	@GetMapping(value = "/{id}")
	public Pauta findById(@PathVariable Long id) {
		Pauta pautaObj = pautaService.findById(id).get();
		return pautaObj;
	}
	
	@PostMapping
	public Pauta insert(@RequestBody Pauta pauta) {
		Pauta pautaObj = pautaService.create(pauta);
		return pautaObj;	
	}
	
	@DeleteMapping(value = "/{id}")
	public Pauta delete(@PathVariable Long id) {
		Pauta pautaObj = pautaService.findById(id).orElseThrow();
		pautaService.delete(id);
		return pautaObj;
	}
	
	@PutMapping(value = "/{id}")
	public Pauta update(@PathVariable Long id, @RequestBody Pauta newPauta) {
		Pauta updatePautaObj = pautaService.findById(id).orElseThrow();
		updatePautaObj.setPauta(newPauta.getPauta());
		updatePautaObj.setLocalDateTime(newPauta.getLocalDateTime());
		return pautaService.update(updatePautaObj);		
	}
	
    @PostMapping("/{id}/openVoting")
    public void openVoting(@PathVariable Long id, @RequestParam(required = false) Long duration) {
        Pauta pautaObj = findById(id);

        if (duration != null) {
        	pautaService.openVotingSession(pautaObj, duration);
        } 
        else {
        	pautaService.openVotingSession(pautaObj);
        }
    } 
}
