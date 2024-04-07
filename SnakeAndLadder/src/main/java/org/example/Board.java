package org.example;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private static volatile Board instance;
    private int size;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private Map<Integer, SpecialObject> specialObjects;

    private Board(int size, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
        this.specialObjects = new HashMap<>();
    }

    public static Board getInstance(int size, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders){
        if(instance == null){
            synchronized (Board.class){
                if(instance == null){
                    instance = new Board(size, snakes, ladders);
                }
            }
        }
        return instance;
    }

    public void addSpecialObject(int position, SpecialObject specialObject) {
        specialObjects.put(position, specialObject);
    }

    public synchronized void movePlayer(Player player, int steps) {
        int newPosition = player.getPosition() + steps;
        if (newPosition == size * size) {
            System.out.println(player.getName() + " wins!");
            return;
        }
        else if(newPosition > size* size){
            System.out.println(player.getName() + " cannot move!");
            return;
        }
        if (snakes.containsKey(newPosition)) {
            newPosition = snakes.get(newPosition);
            System.out.println(player.getName() + " rolled a " + steps + " and got bitten by a snake! Moving to position " + newPosition);
        } else if (ladders.containsKey(newPosition)) {
            newPosition = ladders.get(newPosition);
            System.out.println(player.getName() + " rolled a " + steps + " and found a ladder! Moving to position " + newPosition);
        }
        else if (specialObjects.containsKey(newPosition)) {
            SpecialObject specialObject = specialObjects.get(newPosition);
            newPosition = specialObject.applyEffect(newPosition);
            System.out.println(player.getName() + " encountered a special object!");
        }
        else {
            System.out.println(player.getName() + " rolled a " + steps + " and moved to position " + newPosition);
        }
        player.setPosition(newPosition);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
