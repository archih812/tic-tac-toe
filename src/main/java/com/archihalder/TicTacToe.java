package com.archihalder;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Players: O and X");
        System.out.println();

        while (true) {
            // Display board
            System.out.println(game.render());
            System.out.println("Player with move: " + game.getCurrentPlayer());
            System.out.print("Enter your position: ");

            // Read and validate input
            int position;
            try {
                String input = scanner.nextLine().trim();
                position = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid position (1-9).");
                System.out.println();
                continue;
            }

            // Attempt to play the move
            Game.MoveResult result = game.playTurn(position);
            
            if (!result.isSuccess()) {
                System.out.println(result.getErrorMessage());
                System.out.println();
                continue;
            }

            // Check game status
            GameStatus status = result.getStatus();
            
            if (status == GameStatus.DRAW) {
                System.out.println(game.render());
                System.out.println("The game ended in a draw!");
                break;
            } else if (status == GameStatus.X_WON || status == GameStatus.O_WON) {
                System.out.println(game.render());
                char winner = (status == GameStatus.X_WON) ? 'X' : 'O';
                System.out.println("Player " + winner + " won the game!");
                break;
            }

            System.out.println();
        }

        scanner.close();
    }
}
