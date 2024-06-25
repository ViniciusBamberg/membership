package com.viniciusspring.membership.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viniciusspring.membership.entities.Membership;
import com.viniciusspring.membership.exceptions.MembershipException;
import com.viniciusspring.membership.repositories.MembershipRepository;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository repository;

    public Optional<Membership> findById(Long id) {
        Optional<Membership> membership = repository.findById(id);
        if (membership.isEmpty()) {
            throw new MembershipException("Usuário não encontrado!", null);
        }
        return membership;
    }
    
    @Transactional
    public List<Membership> findAll() {
    	List<Membership> list = repository.findAll();
    	return list;
    }

    @Transactional
    public Membership create(Membership membership) {
        return repository.save(membership);
    }

    @Transactional
    public Membership update(Membership membership) {
        Optional<Membership> existingMembershipOpt = findById(membership.getId());

        if (existingMembershipOpt.isEmpty()) {
            throw new MembershipException("Usuário não encontrado!", membership);
        }

        Membership existingMembership = existingMembershipOpt.get();
        existingMembership.setName(membership.getName());
        existingMembership.setBirthday(membership.getBirthday());
        existingMembership.setDocument(membership.getDocument());

        return repository.save(existingMembership);
    }

    public void delete(Long id) {
        Optional<Membership> membershipOpt = findById(id);

        if (membershipOpt.isEmpty()) {
            throw new MembershipException("Usuário não encontrado!", null);
        }

        try {
            repository.deleteById(id);
        } 
        catch (Exception e) {
            throw new DataIntegrityViolationException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}
