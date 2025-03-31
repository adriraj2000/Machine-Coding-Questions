package org.example;

public class NormalCell extends Cell{
    public NormalCell(int position) {
        super(position);
    }
    @Override
    public int getNextPosition(int currentPos) {
        return currentPos;
    }
}
