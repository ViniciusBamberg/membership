package com.viniciusspring.membership.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viniciusspring.membership.entities.Voting;
import com.viniciusspring.membership.enums.VoteEnum;
import com.viniciusspring.membership.exceptions.VotingException;
import com.viniciusspring.membership.repositories.VotingRepository;

@Service
public class VotingService {

	@Autowired
	private VotingRepository repository;

	@Transactional
	public Voting vote(Long id, VoteEnum vote) {
		Optional<Voting> votingOpt = repository.findById(id);
		if (votingOpt.isEmpty()) {
			throw new VotingException("Resultado não encontrado!", id);
		}

		Voting voting = votingOpt.get();
		voting.incrementVote(vote);
		return repository.save(voting);
	}

	public Voting getVoting(Long id) {
		return repository.findById(id).orElseThrow(() -> new VotingException("Resultado não encontrado!", id));
	}

	public List<Voting> findAll() {
		return repository.findAll();
	}
}
