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

            // Access the "clubs" array
            JsonNode clubsArray = rootNode.get("clubs");

            // Create a list to hold Club objects
            List<Club> clubs = new ArrayList<>();

            // Iterate through the array and extract the required fields
            for (JsonNode clubNode : clubsArray) {
                String name = clubNode.get("name").asText();
                String shortName = clubNode.get("short_name").asText();
                int position = clubNode.get("position").asInt();


                // Create a Club object and add it to the list
                clubs.add(new Club(name, shortName,position));
            }

            // Print the list of clubs
            for (Club club : clubs) {
                club.printName();
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}