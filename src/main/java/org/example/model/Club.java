package org.example.model;

public class Club {

    private String name;
    private String clubCode;
    private int position;
    private int wins;
    private int losses;
    private int goalsScored;

    public Club(String name, String clubCode, int position, int wins, int losses, int goalsScored ){
        this.name=name;
        this.clubCode=clubCode;
        this.position=position;
        this.wins=wins;
        this.losses=losses;
        this.goalsScored=goalsScored;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClubCode() {
        return clubCode;
    }

    public void setClubCode(String clubCode) {
        this.clubCode = clubCode;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }
}
