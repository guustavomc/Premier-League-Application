package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readFixtures {

    String filePath;
    List<Club> clubsArray;

    public readFixtures(String filePath, List<Club> clubsArray){
        this.filePath=filePath;
        this.clubsArray=clubsArray;
    }

    public List<Fixture> getFixturesList(){
        ObjectMapper objectMapper = new ObjectMapper();

        List<Fixture> fixtureArray = new ArrayList<>();

        try{

            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

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

                    for(Club club: clubsArray){
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
            e.printStackTrace();
        }

        return fixtureArray;
    }
}
