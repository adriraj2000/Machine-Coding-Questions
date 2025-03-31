package org.example;

public abstract class Cell {
    protected int position;
    public Cell(int position){
        this.position = position;
    }
    public abstract int getNextPosition(int currentPos);
}
