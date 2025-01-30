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
        boolean inProgress = true;

        while(inProgress){
            Scanner scanner = new Scanner(System.in);

            System.out.println("1 - Print Clubs by Position");
            System.out.println("2 - Print Clubs by Wins");
            System.out.println("3 - Print Clubs by Losses");
            System.out.println("4 - Print Clubs by Goals Scored");
            System.out.println("5 - Print Fixtures");
            System.out.println("6 - End operations");

            Integer value = scanner.nextInt();

            switch(value){
                case 1:
                    List<Club> clubsArrayPosition =  new readClubs(filePath).getClubList();
                    clubsArrayPosition.sort(Comparator.comparingInt(Club::getPosition));
                    System.out.println("Print Clubs by Position");
                    for(Club club: clubsArrayPosition){
                        club.printName();
                    }
                    break;
                case 2:
                    List<Club> clubsArrayWins =  new readClubs(filePath).getClubList();
                    clubsArrayWins.sort(Comparator.comparingInt(Club::getWins).reversed());
                    System.out.println("\nPrint Clubs by Wins");
                    for(Club club: clubsArrayWins){
                        club.printName();
                    }
                    break;
                case 3:
                    List<Club> clubsArrayLosses =  new readClubs(filePath).getClubList();
                    clubsArrayLosses.sort(Comparator.comparingInt(Club::getLosses).reversed());
                    System.out.println("\nPrint Clubs by Losses");
                    for(Club club: clubsArrayLosses){
                        club.printName();
                    }
                    break;
                case 4:
                    List<Club> clubsArrayGoalsScored =  new readClubs(filePath).getClubList();
                    clubsArrayGoalsScored.sort(Comparator.comparingInt(Club::getGoalsScored).reversed());
                    System.out.println("\nPrint Clubs by Goals Scored");
                    for(Club club: clubsArrayGoalsScored){
                        club.printName();
                    }
                    break;
                case 5:
                    List<Club> clubsArray =  new readClubs(filePath).getClubList();
                    List<Fixture> fixtureArray = new readFixtures(filePath, clubsArray).getFixturesList();
                    System.out.println("\nPrint Fixtures");
                    for (Fixture fixture: fixtureArray){
                        fixture.printFixture();
                    }
                    break;
                case 6:
                    System.out.println("\nEnding operations");
                    inProgress=false;

                    break;
            }


        }










    }
}