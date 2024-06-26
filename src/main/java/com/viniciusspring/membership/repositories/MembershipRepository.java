package com.viniciusspring.membership.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viniciusspring.membership.entities.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long>{

}
