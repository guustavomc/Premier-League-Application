package org.example;

public class Club {

    private String name;
    private String short_name;
    public Club(String name, String short_name){
        this.name=name;
        this.short_name=short_name;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getShort_name() {
        return short_name;
    }

    public void setShort_name(java.lang.String short_name) {
        this.short_name = short_name;
    }

    public void printName(){
        System.out.println("Name: " + getName()
                            +", Short Name: " + getShort_name());
    }


}
