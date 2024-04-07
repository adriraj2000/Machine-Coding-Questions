package org.example;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
    private Board board;
    private Deque<Player> players;
    private Dice dice;

    public Game(Board board, Dice dice) {
        this.board = board;
        this.players = new LinkedList<>();
        this.dice = dice;
    }

    public void addPlayer(Player player){
        players.addLast(player);
    }

    public void start(){
        while (true){
            Player currentPlayer = players.poll();
            int steps = dice.rollDice();
            board.movePlayer(currentPlayer, steps);
            if(currentPlayer.getPosition() == board.getSize()*board.getSize())break;
            checkCollision(currentPlayer);
            players.addLast(currentPlayer);
        }
    }

    public void checkCollision(Player currentPlayer){
        for (Player player : players){
            if(currentPlayer.getPosition() == player.getPosition()){
                System.out.println(player.getName() + " collided with " + currentPlayer.getName() + "!");
                System.out.println(player.getName() + " restarts from position 1.");
                player.setPosition(1);
            }
        }
    }
}
