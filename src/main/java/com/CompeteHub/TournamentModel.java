package com.CompeteHub;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentModel {
    @JsonProperty("val")
    private int val;

    public TournamentModel() {
    }

    public TournamentModel(int val) {
        this.val = val;
    }

}
