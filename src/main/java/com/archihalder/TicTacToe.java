package com.archihalder;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[] moves = { 'O', 'X' };
        Board board = new Board();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (true) {
            board.displayBoard();
            System.out.println("Player with move: " + moves[choice]);
            System.out.print("Enter your position: ");

            int pos = 0;
            try {
                pos = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid position.");
                continue;
            }

            if (!board.insertMove(moves[choice], pos))
                continue;
            else {
                if (board.checkStatus() == 'D') {
                    System.out.println("The game ended in draw");
                    break;
                } else if (board.checkStatus() != 'C') {
                    System.out.println("Player: " + moves[choice] + " won the game");
                    break;
                }
                choice = choice == 0 ? 1 : 0;
            }

        }
        sc.close();
    }
}
