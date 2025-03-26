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

            System.out.println(
                    "Print Clubs by Position\n"+
                    "Print Clubs by Wins\n"+
                    "Print Clubs by Losses\n"+
                    "Print Clubs by Goals Scored\n"+
                    "Print Fixtures\n"+
                    "End operations");
            System.out.println("Your Choice:");
            String valueSelected = scanner.nextLine();
            String valueSelectedCorrectly = valueSelected.toUpperCase().replaceAll("\\s+", "").trim();

            selectedAction(valueSelectedCorrectly);

        }
    }

    private void selectedAction(String valueSelectedCorrectly) {
        switch(valueSelectedCorrectly){
            case "PRINTCLUBSBYPOSITION":
                printClubsByPosition();
                break;
            case "PRINTCLUBSBYWINS":
                printClubsByWins();
                break;
            case "PRINTCLUBSBYLOSSES":
                printClubsByLosses();
                break;
            case "PRINTCLUBSBYGOALSSCORED":
                printClubsByGoalsScored();
                break;
            case "PRINTFIXTURES":
                printFixtures();
                break;
            case "ENDOPERATIONS":
                System.out.println("\nEnding operations");
                selectorStillInUse=false;

                break;
        }
    }

    private void printFixtures() {
        List<Club> clubsArray = getClubsArray();
        List<Fixture> fixtureArray = new readFixtures(filePath, clubsArray).getFixturesList();
        System.out.println("\nPrint Fixtures");
        for (Fixture fixture: fixtureArray){
            fixture.printFixture();
        }
    }

    private List<Club> getClubsArray() {
        List<Club> clubsArray =  new readClubs(filePath).getClubList();
        return clubsArray;
    }

    private void printClubsByGoalsScored() {
        List<Club> clubsArrayGoalsScored =  getClubsArray();
        clubsArrayGoalsScored.sort(Comparator.comparingInt(Club::getGoalsScored).reversed());
        System.out.println("\nPrint Clubs by Goals Scored");
        for(Club club: clubsArrayGoalsScored){
            club.printName();
        }
    }

    private void printClubsByLosses() {
        List<Club> clubsArrayLosses =  getClubsArray();
        clubsArrayLosses.sort(Comparator.comparingInt(Club::getLosses).reversed());
        System.out.println("\nPrint Clubs by Losses");
        for(Club club: clubsArrayLosses){
            club.printName();
        }
    }

    private void printClubsByWins() {
        List<Club> clubsArrayWins =  getClubsArray();
        clubsArrayWins.sort(Comparator.comparingInt(Club::getWins).reversed());
        System.out.println("\nPrint Clubs by Wins");
        for(Club club: clubsArrayWins){
            club.printName();
        }
    }

    private void printClubsByPosition() {
        List<Club> clubsArrayPosition =  getClubsArray();
        clubsArrayPosition.sort(Comparator.comparingInt(Club::getPosition));
        System.out.println("Print Clubs by Position");
        for(Club club: clubsArrayPosition){
            club.printName();
        }
    }

}
