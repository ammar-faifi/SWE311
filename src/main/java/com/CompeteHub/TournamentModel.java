package com.CompeteHub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("numOfTeams")
    private int numOfTeams;
    @JsonProperty("type")
    private String type;
    @JsonProperty("participationType")
    private String participationType;
    @JsonProperty("sport")
    private String sport;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("teams")
    private String teams;
    @JsonProperty("supervisor")
    private String supervisor;

    public TournamentModel() {
    }

    public TournamentModel(
            String name, String type, String participationType, String sport,
            String startDate, String endDate, String teams, int numOfTeams,
            String supervisor) {
        this.name = name;
        this.type = type;
        this.participationType = participationType;
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = teams;
        this.numOfTeams = numOfTeams;
        this.supervisor = supervisor;
    }

}
