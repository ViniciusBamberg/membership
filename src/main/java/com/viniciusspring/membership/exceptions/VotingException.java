package com.viniciusspring.membership.exceptions;

public class VotingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Long votingId;

	public VotingException(String message, Long votingId) {
		super(message);
		this.votingId = votingId;
	}

	public Long getVotingId() {
		return votingId;
	}

	@Override
	public String getMessage() {
		return super.getMessage() + (votingId != null ? " Id: " + votingId : "");
	}
}
