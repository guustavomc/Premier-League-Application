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

        try{

            // Read the JSON file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

            JsonNode clubsNode = rootNode.get("clubs");

            List<Club> clubsArray = new ArrayList<>();

            for(JsonNode club: clubsNode){
                String name = club.get("name").asText();
                String short_name = club.get("short_name").asText();
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
    }
}