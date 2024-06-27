package com.viniciusspring.membership.exceptions;

public class Membership2Exception extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private Long membershipId;

    public Membership2Exception(String message, Long membershipId) {
        super(message);
        this.membershipId = membershipId;
    }

    public Long getMembershipId() {
        return membershipId;
    }
}
