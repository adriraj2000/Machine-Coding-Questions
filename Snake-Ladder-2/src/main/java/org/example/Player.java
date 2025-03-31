package org.example;

public class Player {
    private String name;
    private int position;

    public Player(String name){
        this.name = name;
        this.position = 1;
    }

    public int getPosition(){
        return this.position;
    }

    public String getName(){
        return this.name;
    }

    public void setPosition(int pos){
        this.position = pos;
    }
}
