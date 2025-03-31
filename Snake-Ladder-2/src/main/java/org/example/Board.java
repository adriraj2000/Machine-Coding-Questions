package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static volatile Board instance;
    private int size;
    private Map<Integer, Cell> cells;

    private Board(int size, List<Cell> specialCells) {
        this.size = size;
        this.cells = new HashMap<>();

        for (int i = 1; i <= size; i++) {
            cells.put(i, new NormalCell(i));
        }

        for (Cell cell : specialCells) {
            cells.put(cell.position, cell);
        }
    }

    public static Board getInstance(int size, List<Cell> specialCells) {
        if (instance == null) {
            synchronized (Board.class) {
                if (instance == null) {
                    instance = new Board(size, specialCells);
                }
            }
        }
        return instance;
    }

    public int getSize(){
        return this.size;
    }

    public int getNextPosition(int currentPos) {
        return cells.get(currentPos).getNextPosition(currentPos);
    }
}
