package org.example;

import java.util.HashMap;
import java.util.Map;

public class SnakeLadder {
    Map<String, Integer> playerLatestPosition;
    Board board;
    Dice dice;

    public SnakeLadder(int N){
        playerLatestPosition = new HashMap<>();
        board = board.getInstance();
        dice = new Dice(N);
    }

    public void playGame(){
        initializeGame();
        int playerIndex = -1;
        do{
            playerIndex++;
            if(playerIndex >= board.getPlayers().size())playerIndex=0;
            String playerName = board.getPlayers().get(playerIndex);
            int diceRoll = dice.rollDice();
            int endPosition = playerLatestPosition.get(playerName) + diceRoll;
            if(withinRange(endPosition)){
                if(board.getSnakes().containsKey(endPosition)){
                    playerLatestPosition.put(playerName, board.getSnakes().get(endPosition));
                }
                else if(board.getLadders().containsKey(endPosition)){
                    playerLatestPosition.put(playerName, board.getLadders().get(endPosition));
                }
                else{
                    playerLatestPosition.put(playerName, endPosition);
                }
            }
        }
        while(!isPlayerWon(board.getPlayers().get(playerIndex)));
        System.out.println("Player " + board.getPlayers().get(playerIndex) + " won");
    }

    private boolean isPlayerWon(String player){
        return playerLatestPosition.get(player) == 100;
    }

    private boolean withinRange(int endPosition){
        return endPosition <= 100;
    }

    private void initializeGame() {
        for(int i = 0;i<board.getPlayers().size();i++){
            playerLatestPosition.put(board.getPlayers().get(i),0);
        }
    }
}