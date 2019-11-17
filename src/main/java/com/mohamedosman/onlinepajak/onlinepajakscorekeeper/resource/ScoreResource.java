package com.mohamedosman.onlinepajak.onlinepajakscorekeeper.resource;

import com.mohamedosman.onlinepajak.onlinepajakscorekeeper.model.Score;
import com.mohamedosman.onlinepajak.onlinepajakscorekeeper.model.Team;
import com.mohamedosman.onlinepajak.onlinepajakscorekeeper.service.ScoresHardcodedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class ScoreResource {

    @Autowired
    private ScoresHardcodedService scoresService;

    @GetMapping("/scores")
    public List<Score> getAllScores() {
        return scoresService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScore(@PathVariable long id) {
        Score score = scoresService.deleteById(id);

        if (score != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/details/{id}")
    public Score getScore(@PathVariable long id) {
        return scoresService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Void> createScore(@RequestBody Score score) {
        Score createdScore = scoresService.save(score);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdScore.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/rates/{teamName}")
    public Team getWinRates(@PathVariable String teamName) {
        return scoresService.getWinRates(teamName);
    }

    @GetMapping("/compare")
    public HashMap<String, Integer> compareTeams(@RequestParam(value = "firstTeam") String firstTeam, @RequestParam(value = "secondTeam") String secondTeam) {
        return scoresService.getComparison(firstTeam, secondTeam);
    }
}
