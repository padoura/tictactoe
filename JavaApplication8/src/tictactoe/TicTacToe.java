/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author padoura
 */
public class TicTacToe {
    
    private static String board;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicTacToe.play();
    }
    
    
    private static boolean regexTicTacToeWinChecker() {
        String winRegexp = ".*(\\w)(..(\\1|.\\1.)..\\1.*|.\\1.\\1..|\\1\\1(...)*)";
        return board.matches(winRegexp);
    }

    private static void displayBoard() {
        char[][] board2D = new char[3][3];
        System.out.println("Board:      Keys:");
        for (int i=0;i<9;i++){
            System.out.print(board.charAt(i) + " ");
            if (i%3==2)
                displayKeys(i);
        }
    }

    private static void play() {
        initialBoard();
        int i = 1;
        int playerNum = i%2+1;
        while (!regexTicTacToeWinChecker() && !noMovesLeft()){
            i++;
            playerNum = i%2+1;
            char mark = getMark(playerNum);
            System.out.println("Player " + playerNum + "! Choose where to put " + mark + " using the corresponding key [1-9]:");
            int index = TicTacToe.validMove();
            updateBoard(index, mark);
        }
        
        if (regexTicTacToeWinChecker())
            System.out.println("Player " + playerNum + " won!");
        else
            System.out.println("It's a draw!");
            
    }

    private static int validInt() {
                Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                int num = scanner.nextInt();
                if (num < 10 && num >= 0)
                    return num;
                else
                   System.out.println("Give a valid integer [1-9]"); 
            }
            catch (InputMismatchException ime){
                System.out.println("Give a valid integer [1-9]");
                String newlineC = scanner.nextLine();
            }
        }
    }

    private static String initialBoard() {
        System.out.println("Let's play Tic Tac Toe!");
        board = "---------";
        TicTacToe.displayBoard();
        return board;
    }

    private static int validMove() {        
        int index = TicTacToe.validInt();
        while(board.charAt(index-1)!='-'){
          TicTacToe.displayBoard();
          System.out.println("This box is already filled. Please choose an empty one.");
          index = TicTacToe.validInt();
        }
        return index;
    }

    private static void updateBoard(int index, char mark) {
        char[] boardChar = board.toCharArray();
        boardChar[index-1] = mark;
        board = String.valueOf(boardChar);
        displayBoard();
    }

    private static char getMark(int playerNum) {
        if (playerNum == 1)
            return 'X';
        else
            return 'O';
    }

    private static boolean noMovesLeft() {
        return board.indexOf('-') < 0;
    }

    private static void displayKeys(int i) {
        if(i/3==0)
            System.out.println("      1 2 3");
        else if (i/3==1)
            System.out.println("      4 5 6");
        else
            System.out.println("      7 8 9");
    }
}
