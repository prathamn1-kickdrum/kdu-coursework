package org.example.model;

public class Player {
    private String name;
    private PlayerRole role;
    private int matches;
    private int runs;
    private float average;
    private float strikeRate;
    private int wickets;
    private Team team;

    public Player(String name, PlayerRole role, int matches, int runs, float average, float strikeRate, int wickets, Team team) {
        this.name = name;
        this.average=average;
        this.matches=matches;
        this.role=role;
        this.strikeRate=strikeRate;
        this.wickets=wickets;
        this.team=team;
        this.runs=runs;
    }

    public String getPlayerName() {
        return this.name;
    }

    public PlayerRole getPlayerRole() {
        return this.role;
    }

    public int getMatchesPlayed() {
        return this.matches;
    }

    public int getPlayerRuns() {
        return this.runs;
    }

    public int getPlayerWickets() {
        return this.wickets;
    }

    public float getPlayerAverage() {
        return this.average;
    }

    public float getPlayerStrikeRate() {
        return this.strikeRate;
    }

    public Team getTeam() {
        return team;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setStrikeRate(float strikeRate) {
        this.strikeRate = strikeRate;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }
    @Override
    public String toString() {
            return "\nName : "+name+"\nTeam : "+team.toString()+"\nWickets : "+wickets+"\nRole : "+role.toString()+"\nruns : "+runs+"\n";
    }
}




