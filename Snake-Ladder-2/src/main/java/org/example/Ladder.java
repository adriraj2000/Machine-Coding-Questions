package org.example;

class Ladder extends Cell {
    private int top;

    public Ladder(int bottom, int top) {
        super(bottom);
        this.top = top;
    }

    @Override
    public int getNextPosition(int currentPos) {
        return top;
    }
}