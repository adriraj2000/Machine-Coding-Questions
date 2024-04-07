package org.example;

import java.util.Random;

public class NormalDice implements Dice{
    Random random;

    public NormalDice(){
        random = new Random();
    }
    @Override
    public int rollDice() {
        return random.nextInt(6) + 1;
    }
}
