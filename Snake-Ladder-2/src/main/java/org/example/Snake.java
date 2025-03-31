package org.example;

public class Snake extends Cell{
    private int tail;
    public Snake(int head, int tail) {
        super(head);
        this.tail = tail;
    }

    @Override
    public int getNextPosition(int currentPos) {
        return tail;
    }
}
