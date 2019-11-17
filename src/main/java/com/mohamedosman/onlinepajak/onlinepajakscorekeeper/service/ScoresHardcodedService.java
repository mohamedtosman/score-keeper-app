package com.mohamedosman.onlinepajak.onlinepajakscorekeeper.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mohamedosman.onlinepajak.onlinepajakscorekeeper.model.Score;
import com.mohamedosman.onlinepajak.onlinepajakscorekeeper.model.Team;
import com.mohamedosman.onlinepajak.onlinepajakscorekeeper.enums.TeamNames;
import org.springframework.stereotype.Service;

@Service
public class ScoresHardcodedService {

    private static List<Score> scores = new ArrayList<>();
    private static long idCounter = 0;
    private static List<Team> teams = new ArrayList<>();

    static {
        scores.add(new Score(++idCounter, "3-1", TeamNames.TEAM1.name(), TeamNames.TEAM1.name(), TeamNames.TEAM2.name(), LocalDate.now().toString()));
        scores.add(new Score(++idCounter, "1-4", TeamNames.TEAM2.name(), TeamNames.TEAM1.name(), TeamNames.TEAM2.name(), LocalDate.now().toString()));

        teams.add(new Team(TeamNames.TEAM1.name(), 1, 2));
        teams.add(new Team(TeamNames.TEAM2.name(), 1, 2));
        teams.add(new Team(TeamNames.TEAM3.name(), 0, 0));
        teams.add(new Team(TeamNames.TEAM4.name(), 0, 0));
    }

    public static List<Score> findAll() {
        return scores;
    }

    public Score save(Score score) {
        score.setId(++idCounter);
        scores.add(score);

        for (Team team : teams) {

            if (score.getFirstTeam().equals(team.getName())
                    || score.getSecondTeam().equals(team.getName())) {
                team.incrementGames();
            }

            if (score.getWinner().equals(team.getName())) {
                team.incrementWins();
            }
        }

//        teams.add(new Team(score.getFirstTeam(), 0, 1));
//        teams.add(new Team(score.getSecondTeam(), 0, 1));
        return score;
    }

    public Score deleteById(long id) {
        Score score = findById(id);

        if (score == null)
            return null;

        if (scores.remove(score)) {
            return score;
        }

        return null;
    }

    public Score findById(long id) {
        for (Score score : scores) {
            if (score.getId() == id) {
                return score;
            }
        }

        return null;
    }

    public Team getWinRates(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }

        return null;
    }

    public HashMap<String, Integer> getComparison(String firstTeam, String secondTeam) {
        List<Score> finalScores = new ArrayList<>();
        HashMap<String, Integer> finalRates = new HashMap<>();
        int firstWins = 0;
        int secondWins = 0;
        finalRates.put("firstTeam", firstWins);
        finalRates.put("secondTeam", secondWins);

        for (Score score : scores) {
            if ( (score.getFirstTeam().equals(firstTeam) && score.getSecondTeam().equals(secondTeam))
                    || score.getFirstTeam().equals(secondTeam) && score.getSecondTeam().equals(firstTeam)) {
                finalScores.add(score);
            }
        }

        for (Score score : finalScores) {
            if (score.getWinner().equals(firstTeam)) {
                firstWins+=1;
                finalRates.put("firstTeam", firstWins);
            }
            else {
                secondWins+=1;
                finalRates.put("secondTeam", secondWins);
            }
        }

        return finalRates;
    }
}
