package org.example;


public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();

        board.setPlayer(0,"Adriraj");
        board.setPlayer(1,"Naman");
        board.setPlayer(2,"Souvik");

        board.addSnakes(62,5);
        board.addSnakes(33, 6);
        board.addSnakes(49, 9);
        board.addSnakes(88, 16);
        board.addSnakes(41, 20);
        board.addSnakes(56, 53);
        board.addSnakes(98, 64);
        board.addSnakes(93, 73);
        board.addSnakes(95, 75);

        board.addLadder(2, 37);
        board.addLadder(27, 46);
        board.addLadder(10, 32);
        board.addLadder(51, 68);
        board.addLadder(61, 79);
        board.addLadder(65, 84);
        board.addLadder(71, 91);
        board.addLadder(81, 100);

        SnakeLadder snakeLadder = new SnakeLadder(6);
        snakeLadder.playGame();
    }
}