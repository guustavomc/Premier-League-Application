package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readClubs {

    String filePath;
    public readClubs(String filePath) {
        this.filePath=filePath;
    }

    public List<Club> getClubList(){

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
}
