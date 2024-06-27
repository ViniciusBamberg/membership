package com.viniciusspring.membership.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.viniciusspring.membership.enums.VoteEnum;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "votacao")
public class Voting implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer totalVotes;
    private Integer simVotes;
    private Integer naoVotes;

    @ElementCollection
    private Set<Long> votedMembershipIds = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    public Voting() {
    }

    public Voting(Pauta pauta) {
        this.pauta = pauta;
    }

    public Long getId() {
        return id;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public Integer getSimVotes() {
        return simVotes;
    }

    public Integer getNaoVotes() {
        return naoVotes;
    }

    public void incrementVote(VoteEnum voteEnum, Long membershipId) {
        if (votedMembershipIds.contains(membershipId)) {
            throw new RuntimeException("Associado já votou.");
        }

        votedMembershipIds.add(membershipId);

        if (voteEnum == VoteEnum.SIM) {
            this.simVotes++;
        } 
        else if (voteEnum == VoteEnum.NAO) {
            this.naoVotes++;
        }
        this.totalVotes++;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Voting other = (Voting) obj;
        return Objects.equals(id, other.id);
    }
}
