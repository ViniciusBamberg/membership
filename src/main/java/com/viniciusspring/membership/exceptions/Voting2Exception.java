package com.viniciusspring.membership.exceptions;

public class Voting2Exception extends RuntimeException {
  	private static final long serialVersionUID = 1L;
	
  	private Long pautaId;

    public Voting2Exception(String message, Long pautaId) {
        super(message);
        this.pautaId = pautaId;
    }

    public Long getPautaId() {
        return pautaId;
    }
}
