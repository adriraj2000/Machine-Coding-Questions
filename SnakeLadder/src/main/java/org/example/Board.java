package org.example;

import java.util.HashMap;

public class Board{
    static HashMap<Integer,Integer> snakes;
    static HashMap<Integer,Integer> ladders;
    static HashMap<Integer,String> players;
    static Board instance = null;

    private Board(){
        snakes = new HashMap<>();
        ladders = new HashMap<>();
        players = new HashMap<>();
    }

    public HashMap<Integer, Integer> getSnakes() {
        return snakes;
    }

    public void addSnakes(int head,int tail){
        snakes.put(head,tail);
    }

    public HashMap<Integer, Integer> getLadders() {
        return ladders;
    }

    public void addLadder(int head,int tail){
        ladders.put(head,tail);
    }

    public void setPlayer(int index,String playerName) {
        players.put(index,playerName);
    }

    public HashMap<Integer, String> getPlayers() {
        return players;
    }

    public static Board getInstance(){
        if( instance == null){
            instance = new Board();
        }
        return instance;
    }
}