package org.example.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.example.model.Club;
import org.example.model.Fixture;
import org.example.service.ClubService;
import org.example.service.FixtureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clubs")
public class PremierLeagueController {

    private ClubService clubs;
    private FixtureService fixtures;

    public  PremierLeagueController(ClubService clubs, FixtureService fixtures){
        this.clubs=clubs;
        this.fixtures=fixtures;
    }

    @GetMapping
    public List<Club> getAllClubs(){
        return clubs.findClubsArray();
    }

    @GetMapping("goals")
    public List<Club> getClubsByGoalsScored(){
        return clubs.findClubsByGoalsScored();
    }

    @GetMapping("losses")
    public List<Club> getClubsByLosses(){
        return clubs.findClubsByLosses();
    }

    @GetMapping("wins")
    public List<Club> getClubsByWins(){
        return clubs.findClubsByWins();
    }

    @GetMapping("position")
    public List<Club> getClubsByPosition(){
        return clubs.findClubsByPosition();
    }
    @GetMapping("fixtures")
    public List<Fixture> getAllFixtures(){
        return fixtures.getFixturesList();
    }
}
