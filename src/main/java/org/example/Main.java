package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        String filePath = "src/main/java/data/data.json";

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        List<Club> clubsArray = new ArrayList<>();

        try{

            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            JsonNode clubsNode = rootNode.get("clubs");

            //List<Club> clubsArray = new ArrayList<>();

            for(JsonNode club: clubsNode){
                String name = club.get("name").asText();
                String short_name = club.get("club_code").asText();
                int position = club.get("position").asInt();

                clubsArray.add(new Club(name, short_name, position));
            }
            // Print the list of clubs
            for (Club club : clubsArray) {
                club.printName();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try{

            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            JsonNode seasonFixtureNode = rootNode.get("season_fixtures");

            List<Fixture> fixtureArray = new ArrayList<>();

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
            // Print the list of clubs
            for (Fixture fixture : fixtureArray) {
                fixture.printFixture();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}