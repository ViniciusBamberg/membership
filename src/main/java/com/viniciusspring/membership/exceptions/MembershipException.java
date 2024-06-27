package com.viniciusspring.membership.exceptions;

import com.viniciusspring.membership.entities.Membership;

public class MembershipException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Membership membership;
    
    public MembershipException() {
    }

    public MembershipException(String message, Membership membership) {
        super(message);
        this.membership = membership;
    }

	public Membership getMembership() {
        return membership;
    }

    @Override
    public String getMessage() {
        return "Usuário não encontrado! Id: " + (membership != null ? membership.getId() : "Desconhecido");
    }
}
