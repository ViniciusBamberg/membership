package com.viniciusspring.membership.exceptions;

public class PautaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Long pautaId;

    public PautaException(String message, Long pautaId) {
        super(message);
        this.pautaId = pautaId;
    }

    public Long getVotingId() {
        return pautaId;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + (pautaId != null ? " Id: " + pautaId : "");
    }
}
