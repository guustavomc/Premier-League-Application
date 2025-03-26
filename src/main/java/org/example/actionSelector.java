package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class actionSelector {

    private String filePath = "src/main/java/data/data.json";
    private boolean selectorStillInUse=true;
    public void actionSelector() {


        while (selectorStillInUse) {
            Scanner scanner = new Scanner(System.in);

            /*System.out.println("1 - Print Clubs by Position");
            System.out.println("2 - Print Clubs by Wins");
            System.out.println("3 - Print Clubs by Losses");
            System.out.println("4 - Print Clubs by Goals Scored");
            System.out.println("5 - Print Fixtures");
            System.out.println("6 - End operations");*/

            System.out.println("Print Clubs by Position");
            System.out.println("Print Clubs by Wins");
            System.out.println("Print Clubs by Losses");
            System.out.println("Print Clubs by Goals Scored");
            System.out.println("Print Fixtures");
            System.out.println("End operations");

            System.out.println("Your Choice:");
            String valueSelected = scanner.nextLine();
            String valueSelectedCorrectly = valueSelected.toUpperCase().replaceAll("\\s+", "").trim();

            selectedAction(valueSelectedCorrectly);

        }
    }

    private void selectedAction(String valueSelectedCorrectly) {
        switch(valueSelectedCorrectly){
            case "PRINTCLUBSBYPOSITION":
                List<Club> clubsArrayPosition =  new readClubs(filePath).getClubList();
                clubsArrayPosition.sort(Comparator.comparingInt(Club::getPosition));
                System.out.println("Print Clubs by Position");
                for(Club club: clubsArrayPosition){
                    club.printName();
                }
                break;
            case "PRINTCLUBSBYWINS":
                List<Club> clubsArrayWins =  new readClubs(filePath).getClubList();
                clubsArrayWins.sort(Comparator.comparingInt(Club::getWins).reversed());
                System.out.println("\nPrint Clubs by Wins");
                for(Club club: clubsArrayWins){
                    club.printName();
                }
                break;
            case "PRINTCLUBSBYLOSSES":
                List<Club> clubsArrayLosses =  new readClubs(filePath).getClubList();
                clubsArrayLosses.sort(Comparator.comparingInt(Club::getLosses).reversed());
                System.out.println("\nPrint Clubs by Losses");
                for(Club club: clubsArrayLosses){
                    club.printName();
                }
                break;
            case "PRINTCLUBSBYGOALSSCORED":
                List<Club> clubsArrayGoalsScored =  new readClubs(filePath).getClubList();
                clubsArrayGoalsScored.sort(Comparator.comparingInt(Club::getGoalsScored).reversed());
                System.out.println("\nPrint Clubs by Goals Scored");
                for(Club club: clubsArrayGoalsScored){
                    club.printName();
                }
                break;
            case "PRINTFIXTURES":
                List<Club> clubsArray =  new readClubs(filePath).getClubList();
                List<Fixture> fixtureArray = new readFixtures(filePath, clubsArray).getFixturesList();
                System.out.println("\nPrint Fixtures");
                for (Fixture fixture: fixtureArray){
                    fixture.printFixture();
                }
                break;
            case "ENDOPERATIONS":
                System.out.println("\nEnding operations");
                selectorStillInUse=false;

                break;
        }
    }

}
