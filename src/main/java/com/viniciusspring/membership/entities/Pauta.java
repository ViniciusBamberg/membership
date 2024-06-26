package com.viniciusspring.membership.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Timer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String pauta;
	private String localDateTime;
	@Transient
	private Timer votingTimer;

	public Pauta() {
	}

	public Pauta(Long id, String pauta, String localDateTime) {
		super();
		this.id = id;
		this.pauta = pauta;
		this.localDateTime = localDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public String getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, pauta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pauta other = (Pauta) obj;
		return Objects.equals(id, other.id) && Objects.equals(pauta, other.pauta);
	}
}
