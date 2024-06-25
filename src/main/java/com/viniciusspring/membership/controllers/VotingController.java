package com.viniciusspring.membership.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viniciusspring.membership.entities.Voting;
import com.viniciusspring.membership.enums.VoteEnum;
import com.viniciusspring.membership.services.VotingService;

@RestController
@RequestMapping(value = "/voting")
public class VotingController {

    @Autowired
    private VotingService service;

    @GetMapping
    public List<Voting> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voting> getVoting(@PathVariable Long id) {
        Voting voting = service.getVoting(id);
        return ResponseEntity.ok(voting);
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<Voting> vote(@PathVariable Long id, @RequestParam VoteEnum vote) {
        Voting voting = service.vote(id, vote);
        return ResponseEntity.ok(voting);
    }
}
