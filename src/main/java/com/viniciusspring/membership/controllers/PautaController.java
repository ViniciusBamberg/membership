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
import org.springframework.web.bind.annotation.RestController;

import com.viniciusspring.membership.entities.Pauta;
import com.viniciusspring.membership.services.PautaService;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {

	@Autowired
	private PautaService service;

	@GetMapping
	public List<Pauta> findAll(){
		List<Pauta> result = service.findAll();
		return result;
	}
	
	@GetMapping(value = "/{id}")
	public Pauta findById(@PathVariable Long id) {
		Pauta result = service.findById(id).get();
		return result;
	}
	
	@PostMapping
	public Pauta insert(@RequestBody Pauta pauta) {
		Pauta result = service.create(pauta);
		return result;	
	}
	
	@DeleteMapping(value = "/{id}")
	public Pauta delete(@PathVariable Long id) {
		Pauta pauta = service.findById(id).orElseThrow();
		service.delete(id);
		return pauta;
	}
	
	@PutMapping(value = "/{id}")
	public Pauta update(@PathVariable Long id, @RequestBody Pauta newPauta) {
		Pauta updatePauta = service.findById(id).orElseThrow();
		updatePauta.setPauta(newPauta.getPauta());
		updatePauta.setLocalDateTime(newPauta.getLocalDateTime());
		return service.update(updatePauta);		
	}
}
