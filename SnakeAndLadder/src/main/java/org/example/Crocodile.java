package org.example;

public class Crocodile implements SpecialObject{
    @Override
    public int applyEffect(int currentPosition) {
        return currentPosition - 5;
    }
}
