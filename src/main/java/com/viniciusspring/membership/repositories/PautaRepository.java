package com.viniciusspring.membership.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniciusspring.membership.entities.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>{

}
