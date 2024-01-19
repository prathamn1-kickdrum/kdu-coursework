package org.example.model;

public class Match {
    private String matchDate;
    private int matchNo;
    private Team teamAway;
    private Team teamHome;
    private String ground;

    public Match(String matchDate, int matchNo, Team teamAway, Team teamHome, String ground) {
        this.matchDate=matchDate;
        this.matchNo=matchNo;
        this.teamAway=teamAway;
        this.ground=ground;
        this.teamHome=teamHome;
    }

}
