package com.archihalder;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final char[][] board;

    Board() {
        // Initialize the board with empty spaces
        this.board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = ' ';
            }
        }
    }
    
    public void displayCurrentBoardStatus() {
        // Display the board in a 3x3 matrix with borders
        System.out.println("┌───┬───┬───┐");
        for(int i = 0; i < 3; i++) {
            System.out.print("│ ");
            for(int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {  
                    System.out.print(i * 3 + j + 1);
                } else {
                    System.out.print(board[i][j]);
                }
                if (j < 2) {
                    System.out.print(" │ ");
                } else {
                    System.out.print(" │");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("├───┼───┼───┤");
            }
        }
        System.out.println("└───┴───┴───┘");
    }

    public boolean insertMove(char move, int pos) {
        if (pos < 1 || pos > 9) {
            System.out.println("Invalid positon. Choose a position between 1 and 9.");
            return false;
        }

        int col = pos % 3 == 0 ? 2 : pos % 3 - 1;
        int row = pos % 3 == 0 ? pos / 3 - 1 : pos / 3;
        if (board[row][col] == '0') {
            board[row][col] = move;
            return true;
        } else {
            System.out.println("Position already occupied. Choose a different position.");
            return false;
        }
    }

    public char checkStatus() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '0' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }

        // check cols
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != '0' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }

        // check diagonals
        if ((board[0][0] != '0' && board[0][0] == board[1][1] && board[1][1] == board[2][2]))
            return board[0][0];
        if ((board[0][2] != '0' && board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
            return board[0][2];
        }

        // check for draw
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                freq.put(board[i][j], freq.getOrDefault(board[i][j], 0) + 1);
            }
        }

        int emptyCount = freq.getOrDefault('0', 0);
        if (emptyCount == 0) return 'D';

        // otherwise continue with the game
        return 'C';
    }
}
