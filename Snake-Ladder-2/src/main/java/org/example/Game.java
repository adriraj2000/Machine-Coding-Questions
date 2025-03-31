package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private Board board;
    private Queue<Player> playerQueue;
    private Dice dice;

    public Game(Board board, List<Player> playerList, Dice dice){
        this.board = board;
        this.playerQueue = new LinkedList<>(playerList);
        this.dice = dice;
    }

    public void start(){
        while(true){
            Player currentPlayer = playerQueue.poll();
            int steps = dice.roll();
            int cellNumber = currentPlayer.getPosition() + steps;

            if(cellNumber > board.getSize()){
                System.out.println(currentPlayer.getName() + " cannot move this much");
                playerQueue.offer(currentPlayer);
                continue;
            }

            int newPosition = board.getNextPosition(cellNumber);
            if(newPosition == board.getSize()){
                System.out.println(currentPlayer.getName() + " Wins");
                break;
            }
            System.out.println(currentPlayer.getName() + " moved to " + cellNumber);
            currentPlayer.setPosition(newPosition);
            playerQueue.offer(currentPlayer);
        }
    }
}
