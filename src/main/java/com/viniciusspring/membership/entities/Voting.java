package com.viniciusspring.membership.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
	private int sim;
	private int nao;
	private int totalVotes;

	@OneToMany
	public Set<Membership> membership = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name = "pauta")
	public Pauta pauta;
	
	public Voting() {
	}

	public Voting(Long id, int sim, int nao, int totalVotes) {
		this.id = id;
		this.sim = sim;
		this.nao = nao;
		this.totalVotes = totalVotes;
	}

	public Long getId() {
		return id;
	}

	public int getSim() {
		return sim;
	}

	public int getNao() {
		return nao;
	}

	public int getTotalVotes() {
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
