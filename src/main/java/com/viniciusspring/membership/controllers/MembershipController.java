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

import com.viniciusspring.membership.entities.Membership;
import com.viniciusspring.membership.services.MembershipService;

@RestController
@RequestMapping(value = "/membership")
public class MembershipController {

	@Autowired
	private MembershipService service;

	@GetMapping
	public List<Membership> findAll(){
		List<Membership> membership = service.findAll();
		return membership;
	}
	
	@GetMapping(value = "/{id}")
	public Membership findById(@PathVariable Long id) {
		Membership membership = service.findById(id).get();
		return membership;
	}
	
	@PostMapping
	public Membership insert(@RequestBody Membership membership) {
		Membership result = service.create(membership);
		return result;	
	}
	
	@DeleteMapping(value = "/{id}")
	public Membership delete(@PathVariable Long id) {
		Membership membership = service.findById(id).orElseThrow();
		service.delete(id);
		return membership;
	}
	
	@PutMapping(value = "/{id}")
	public Membership update(@PathVariable Long id, @RequestBody Membership newMembership) {
		Membership updateMembership = service.findById(id).orElseThrow();
		updateMembership.setName(newMembership.getName());
		updateMembership.setBirthday(newMembership.getBirthday());
		updateMembership.setDocument(newMembership.getDocument());
		return service.create(updateMembership);		
	}
}
