package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 */
public class TicTacToeGame {

    private Board board;
    private int ties;
    private int NumOfplayerXWins;
    private int NumOfplayerOWins;

    public TicTacToeGame(){
        board = new Board();
        ties = 0;
        NumOfplayerXWins = 0;
        NumOfplayerOWins = 0;
    }

    public void promptNextPlayer(){
        switch(board.getCurrentPlayer()){
            case X:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;
            case O:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;

        }
    }

    public void printScoreBoard(){
        System.out.println("\n-------------------------------------------");
        System.out.println("S C O R E B O A R D");
        System.out.println("-------------------------------------------");
        System.out.println(String.format("X Wins: %d", this.NumOfplayerXWins));
        System.out.println(String.format("O Wins: %d", this.NumOfplayerOWins));
        System.out.println(String.format("Ties: %d", this.ties));
        System.out.println("-------------------------------------------");
    }

    public void resetGame(){
        board = new Board();
    }

    public void playGame(){
        Scanner keyboardScanner = new Scanner(System.in);

        while (board.getWinner() == null){
        	
            if(board.isFull()) {
                break;
            }
            
            board.printBoard();
            promptNextPlayer();
            String line = keyboardScanner.nextLine();
            String input[] = line.split(",");
            try {
                board.playMove(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            } catch (InvalidMoveException e) {
                System.out.println("Invalid coordinates. Try again");
                promptNextPlayer();
            }
        }

        board.printBoard();

        Player winner = board.getWinner();
        if(winner == null) {
            this.ties += 1;
            System.out.println("Tie Game!");
        }else {
            if (winner == Player.X) this.NumOfplayerXWins += 1;
            else if (winner == Player.O) this.NumOfplayerOWins += 1;
            System.out.println("Player " + winner + " has won the game!");
        }

    }

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();
        boolean play = true;

        while (play){
            game.playGame();

            String response = "";
            while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                Scanner input = new Scanner(System.in);
                System.out.print("Would you like to play again (y/n): ");
                response = input.nextLine();
            }

            if (response.equalsIgnoreCase("n")){
                play = false;
                game.printScoreBoard();
            }
            else{
                game.resetGame();
            }

        }

    }
}
