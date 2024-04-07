package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(62, 5);
        snakes.put(33, 6);
        snakes.put(49, 9);
        snakes.put(88, 16);
        snakes.put(41, 20);
        snakes.put(56, 53);
        snakes.put(98, 64);
        snakes.put(93, 73);
        snakes.put(95, 75);

        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(2, 37);
        ladders.put(27, 46);
        ladders.put(10, 32);
        ladders.put(51, 68);
        ladders.put(61, 79);
        ladders.put(65, 84);
        ladders.put(71, 91);
        ladders.put(81, 100);


        Board board = Board.getInstance(10, snakes, ladders);
        NormalDice dice = new NormalDice();
        Game game = new Game(board, dice);
        game.addPlayer(new Player("Gaurav",0));
        game.addPlayer(new Player("Sagar",0));
        game.start();
    }
}