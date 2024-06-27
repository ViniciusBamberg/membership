package com.viniciusspring.membership.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viniciusspring.membership.entities.VoteRequest;
import com.viniciusspring.membership.entities.Voting;
import com.viniciusspring.membership.exceptions.Membership2Exception;
import com.viniciusspring.membership.exceptions.VotingException;
import com.viniciusspring.membership.services.VotingService;

@RestController
@RequestMapping(value = "/voting")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @GetMapping
    public List<Voting> findAll() {
        return votingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voting> getVoting(@PathVariable Long id) {
        Voting voting = votingService.getVoting(id);
        return ResponseEntity.ok(voting);
    }

    @PostMapping("/{pautaId}/vote")
    public ResponseEntity<Void> vote(@PathVariable Long pautaId, @RequestBody VoteRequest voteRequest) {
    	try {
    		votingService.vote(pautaId, voteRequest.getMembershipId(), voteRequest.getVote());
            return ResponseEntity.ok().build();
    	}catch(Membership2Exception | VotingException ex) {
    		throw ex;
    	}
    }
}

