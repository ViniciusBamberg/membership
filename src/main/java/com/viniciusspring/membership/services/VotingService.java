package com.viniciusspring.membership.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viniciusspring.membership.entities.Membership;
import com.viniciusspring.membership.entities.Pauta;
import com.viniciusspring.membership.entities.Voting;
import com.viniciusspring.membership.enums.VoteEnum;
import com.viniciusspring.membership.exceptions.Membership2Exception;
import com.viniciusspring.membership.exceptions.VotingException;
import com.viniciusspring.membership.repositories.MembershipRepository;
import com.viniciusspring.membership.repositories.PautaRepository;
import com.viniciusspring.membership.repositories.VotingRepository;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private PautaRepository pautaRepository;
    
    @Transactional
    public void vote(Long pautaId, Long membershipId, VoteEnum voteEnum)throws Membership2Exception,VotingException{
        
    	Optional<Membership> membershipOpt = membershipRepository.findById(membershipId);
        if (!membershipOpt.isPresent()) {
            throw new Membership2Exception("Inválido id de associado!", membershipId);
        }
        
    	Optional<Pauta> pautaOpt = pautaRepository.findById(pautaId);
        if (!pautaOpt.isPresent()) {
            throw new VotingException("Inválido id de pauta!", pautaId);
        }

        Pauta pauta = pautaOpt.get();

        Voting voting = votingRepository.findByPauta(pauta).orElse(new Voting(pauta));

        voting.incrementVote(voteEnum, membershipId);
        votingRepository.save(voting);
    }

    public Voting getVoting(Long id) {
        return votingRepository.findById(id)
                .orElseThrow(() -> new VotingException("Inválido id de votação!", id));
    }

    public List<Voting> findAll() {
        return votingRepository.findAll();
    }
    
    public String getVotingResult(Long id) {
        Voting voting = getVoting(id);
        return voting.getResult();
    }
}

