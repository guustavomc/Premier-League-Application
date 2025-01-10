package org.example;

public class Fixture {

   Club homeTeam;
   Club awayTeam;
   int homeTeamGoals;
   int awayTeamGoals;
   int matchDay;

   public Fixture(Club home, Club away, int homeGoals,int awayGoals, int matchDay){
      this.homeTeam = home;
      this.awayTeam = away;
      this.homeTeamGoals = homeGoals;
      this.awayTeamGoals = awayGoals;
      this.matchDay = matchDay;
   }

   public Club getHomeTeam() {
      return homeTeam;
   }

   public void setHomeTeam(Club homeTeam) {
      this.homeTeam = homeTeam;
   }

   public Club getAwayTeam() {
      return awayTeam;
   }

   public void setAwayTeam(Club awayTeam) {
      this.awayTeam = awayTeam;
   }

   public int getHomeTeamGoals() {
      return homeTeamGoals;
   }

   public void setAwayTeamGoals(int awayTeamGoals) {
      this.awayTeamGoals = awayTeamGoals;
   }

   public int getAwayTeamGoals() {
      return awayTeamGoals;
   }

   public void setHomeTeamGoals(int homeTeamGoals) {
      this.homeTeamGoals = homeTeamGoals;
   }

   public int getMatchDay() {
      return matchDay;
   }

   public void setMatchDay(int matchDay) {
      this.matchDay = matchDay;
   }

   public void printFixture(){
      System.out.println(getHomeTeam().getClubCode() + " VS " + getAwayTeam().getClubCode() );
   }
}
