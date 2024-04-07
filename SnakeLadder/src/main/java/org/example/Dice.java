package org.example;

import java.util.Random;

public class Dice {
    Random random;
    int diceNumberLimit;

    public Dice(int n){
        diceNumberLimit = n;
        random = new Random();
    }

    public int rollDice(){
        return random.nextInt(diceNumberLimit)+1;
    }
}
