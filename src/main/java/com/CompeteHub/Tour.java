package com.CompeteHub;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tour {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int numOfTeams;
    private String type;
    private String participationType;
    private String sport;
    private String startDate;
    private String endDate;
    private String teams;
    private String supervisor;
    private byte[] rounds;

    public Tour() {
    }

    public Tour(
            String name, String type, String participationType, String sport,
            String startDate, String endDate, String teams, int numOfTeams,
            String supervisor, byte[] rounds) {
        this.name = name;
        this.type = type;
        this.participationType = participationType;
        this.sport = sport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teams = teams;
        this.numOfTeams = numOfTeams;
        this.supervisor = supervisor;
        this.rounds = rounds;
    }

}
