package com.mohamedosman.onlinepajak.onlinepajakscorekeeper.model;

public class Score {
    private Long id;
    private String finalScore;
    private String winner;
    private String firstTeam;
    private String secondTeam;
    private String date;

    public Score() {

    }

    public Score(long id, String finalScore, String winner, String firstTeam, String secondTeam, String date) {
        super();
        this.id = id;
        this.finalScore = finalScore;
        this.winner = winner;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) { this.firstTeam = firstTeam; }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getDate() { return this.date; }

    public void setDate(String date) { this.date = date; }
}
