package com.mohamedosman.onlinepajak.onlinepajakscorekeeper.model;

public class Team {
    private String name;
    private int wins;
    private int games;

    public Team() {

    }

    public Team(String name, int wins, int games) {
        super();
        this.name = name;
        this.wins = wins;
        this.games = games;
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        this.wins += 1;
    }

    public int getGames() {
        return games;
    }

    public void incrementGames() {
        this.games += 1;
    }

    public String getName() {
        return name;
    }

    public void addName(String name) {
        this.name = name;
    }

    //public Team getTeam() { return this; };
}
