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


        List<Club> clubsArrayPosition =  new readClubs(filePath).getClubList();
        Collections.sort(clubsArrayPosition, Comparator.comparingInt(Club::getPosition));
        System.out.println("Print Clubs by Position");
        for(Club club: clubsArrayPosition){
            club.printName();
        }

        List<Club> clubsArrayWins =  new readClubs(filePath).getClubList();
        clubsArrayWins.sort(Comparator.comparingInt(Club::getWins).reversed());
        System.out.println("\nPrint Clubs by Wins");
        for(Club club: clubsArrayWins){
            club.printName();
        }

        List<Club> clubsArrayLosses =  new readClubs(filePath).getClubList();
        clubsArrayLosses.sort(Comparator.comparingInt(Club::getLosses).reversed());
        System.out.println("\nPrint Clubs by Losses");
        for(Club club: clubsArrayLosses){
            club.printName();
        }

        List<Club> clubsArrayGoalsScored =  new readClubs(filePath).getClubList();
        clubsArrayGoalsScored.sort(Comparator.comparingInt(Club::getGoalsScored).reversed());
        System.out.println("\nPrint Clubs by Goals Scored");
        for(Club club: clubsArrayGoalsScored){
            club.printName();
        }

        List<Fixture> fixtureArray = new readFixtures(filePath, clubsArrayPosition).getFixturesList();
        System.out.println("\nPrint Fixtures");
        for (Fixture fixture: fixtureArray){
            fixture.printFixture();
        }

    }
}