package com.viniciusspring.membership.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniciusspring.membership.entities.Pauta;
import com.viniciusspring.membership.entities.Voting;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Long> {
	Optional<Voting> findByPauta(Pauta pauta);
}
