package org.example;

public class Club {

    private String name;
    private String clubCode;
    private int position;
    public Club(String name, String clubCode, int position ){
        this.name=name;
        this.clubCode=clubCode;
        this.position=position;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getClubCode() {
        return clubCode;
    }

    public void setClubCode(String clubCode) {
        this.clubCode = clubCode;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void printName(){
        System.out.println("Name: " + getName()
                            +", Short Name: " + getClubCode()
                            +", Position: " + getPosition());
    }


}
