package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class readClubs {


    public List<Club> clubList(){

        List<Club> clubsArray = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            ClassPathResource resource = new ClassPathResource("data.json");
            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(resource.getInputStream());

            JsonNode clubsNode = rootNode.get("clubs");

            //List<Club> clubsArray = new ArrayList<>();

            for(JsonNode club: clubsNode){
                String name = club.get("name").asText();
                String short_name = club.get("club_code").asText();
                int position = club.get("position").asInt();
                int wins = club.get("wins").asInt();
                int losses = club.get("losses").asInt();
                int goalsScored = club.get("goals_scored").asInt();


                clubsArray.add(new Club(name, short_name, position,wins,losses,goalsScored));
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return clubsArray;
    }

    public List<Club> findClubsArray() {
        List<Club> clubsArray =  new readClubs().clubList();
        return clubsArray;
    }

    public List<Club> findClubsByGoalsScored() {
        List<Club> clubs = findClubsArray();
        clubs.sort(Comparator.comparingInt(Club::getGoalsScored).reversed());
        return clubs;
    }

    public List<Club> findClubsByLosses() {
        List<Club> clubs = findClubsArray();
        clubs.sort(Comparator.comparingInt(Club::getLosses).reversed());
        return clubs;
    }

    public List<Club> findClubsByWins() {
        List<Club> clubs = findClubsArray();
        clubs.sort(Comparator.comparingInt(Club::getWins).reversed());
        return clubs;
    }

    public List<Club> findClubsByPosition() {
        List<Club> clubs = findClubsArray();
        clubs.sort(Comparator.comparingInt(Club::getPosition));
        return clubs;
    }
}
