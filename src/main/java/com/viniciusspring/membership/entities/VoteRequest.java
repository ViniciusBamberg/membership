package com.viniciusspring.membership.entities;

import com.viniciusspring.membership.enums.VoteEnum;

public class VoteRequest {
    private Long membershipId;
    private VoteEnum vote;
    private Long pautaId;

    public Long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(Long membershipId) {
        this.membershipId = membershipId;
    }

    public VoteEnum getVote() {
        return vote;
    }

    public void setVote(VoteEnum vote) {
        this.vote = vote;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }
}

