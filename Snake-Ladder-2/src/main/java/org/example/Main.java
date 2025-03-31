package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cell> specialCells = Arrays.asList(
                new Snake(62, 5),
                new Snake(33, 6),
                new Snake(49, 9),
                new Snake(88, 16),
                new Snake(41, 20),
                new Snake(56, 53),
                new Snake(98, 64),
                new Snake(93, 73),
                new Snake(95, 75),

                new Ladder(2, 37),
                new Ladder(10, 32),
                new Ladder(27, 46),
                new Ladder(51, 68),
                new Ladder(61, 79),
                new Ladder(65, 84),
                new Ladder(71, 91),
                new Ladder(81, 100)
        );

        Board board = Board.getInstance(100,specialCells);
        List<Player> players = Arrays.asList(new Player("Alice"), new Player("Bob"));

        Dice dice = new RegularDice();
        Game game = new Game(board, players, dice);
        game.start();
    }
}