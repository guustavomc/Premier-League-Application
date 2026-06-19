package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.exception.DataLoadException;
import org.example.model.Club;
import org.example.model.Fixture;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureService {

    private final ClubService clubs;

    public FixtureService(ClubService clubs) {
        this.clubs = clubs;
    }

    public List<Fixture> getFixturesList(){

        List<Fixture> fixtureArray = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Club> clubsList= clubs.findClubsArray();

        try{
            ClassPathResource resource = new ClassPathResource("data.json");

            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(resource.getInputStream());

            JsonNode seasonFixtureNode = rootNode.get("season_fixtures");


            for(JsonNode matchDays: seasonFixtureNode){
                int matchDay = matchDays.get("matchday").asInt();

                JsonNode fixtureNode = matchDays.get("fixtures");
                for(JsonNode fixture: fixtureNode){
                    String homeTeamCode = fixture.get("home_team_code").asText();
                    String awayTeamCode = fixture.get("away_team_code").asText();
                    int homeTeamGoals = fixture.get("home_team_goals").asInt();
                    int awayTeamGoals = fixture.get("away_team_goals").asInt();


                    Club homeTeam = null;
                    Club awayTeam = null;

                    for(Club club: clubsList){
                        if(club.getClubCode().equals(homeTeamCode)){
                            homeTeam= club;
                        }
                        if(club.getClubCode().equals(awayTeamCode)){
                            awayTeam= club;
                        }
                        if(homeTeam!=null && awayTeam!=null){
                            break;
                        }
                    }
                    fixtureArray.add(new Fixture(homeTeam, awayTeam, homeTeamGoals, awayTeamGoals, matchDay));
                }
            }
        }
        catch (IOException e) {
            throw new DataLoadException("Failed to load fixture data from data.json", e);
        }

        return fixtureArray;
    }
}
