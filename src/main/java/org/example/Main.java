package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        String filePath = "src/main/java/data/data.json";

        // Create ObjectMapper instance
        List<Club> clubsArray =  new readClubs(filePath).getClubList();
        Collections.sort(clubsArray, Comparator.comparingInt(Club::getPosition));
        System.out.println("Print Clubs by Position");
        for(Club club: clubsArray){

            club.printName();
        }

        List<Fixture> fixtureArray = new readFixtures(filePath, clubsArray).getFixturesList();
        System.out.println("Print Fixtures");
        for (Fixture fixture: fixtureArray){
            fixture.printFixture();
        }

    }
}