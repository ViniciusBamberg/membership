package com.viniciusspring.membership.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.viniciusspring.membership.enums.VoteEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "votação")
public class Voting implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer sim; 
    private Integer nao;
	private Integer totalVotes;
	
	@Autowired
	public VoteEnum vote; 

	@OneToMany
	public Set<Membership> membership = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "pauta")
	public Pauta pauta;
	
	public Voting() {
	}

	public Voting(Long id, Integer sim, Integer nao, Integer totalVotes) {
		this.id = id;
		this.sim = sim;
		this.nao = nao;
		this.totalVotes = totalVotes;
	}

	public Long getId() {
		return id;
	}

	public Integer getSim() {
		return sim;
	}

	public Integer getNao() {
		return nao;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void incrementVote(VoteEnum vote) {
		if (vote == VoteEnum.SIM) {
			this.sim++;
		} else if (vote == VoteEnum.NAO) {
			this.nao++;
		}
		this.totalVotes++;
	}

	/*public List<Membership> getMembership() {
		return membership;
	}

	public void setMembership(List<Membership> membership) {
		this.membership = membership;
	}*/

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Voting other = (Voting) obj;
		return Objects.equals(id, other.id);
	}
}
