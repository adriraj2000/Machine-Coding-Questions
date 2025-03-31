package org.example;

import java.util.Random;

public class RegularDice implements Dice{
    Random random = new Random();
    @Override
    public int roll() {
        int steps = random.nextInt(6) +1;
        return steps;
    }
}
