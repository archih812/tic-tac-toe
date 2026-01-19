package com.archihalder;

public class Board {
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private final char[][] cells;

    public Board() {
        this.cells = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.cells[i][j] = EMPTY;
            }
        }
    }

    /**
     * Places a move on the board at the given position (1-9).
     * @param player The player making the move ('X' or 'O')
     * @param position The position (1-9, where 1 is top-left, 9 is bottom-right)
     * @return true if move was successful, false if position is invalid or occupied
     */
    public boolean placeMove(char player, int position) {
        if (position < 1 || position > SIZE * SIZE) {
            return false;
        }

        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;

        if (cells[row][col] != EMPTY) {
            return false;
        }

        cells[row][col] = player;
        return true;
    }

    /**
     * Checks the current game status.
     * @return GameStatus indicating if game is in progress, draw, or winner
     */
    public GameStatus getStatus() {
        // Check rows
        for (int i = 0; i < SIZE; i++) {
            if (cells[i][0] != EMPTY && 
                cells[i][0] == cells[i][1] && 
                cells[i][1] == cells[i][2]) {
                return cells[i][0] == 'X' ? GameStatus.X_WON : GameStatus.O_WON;
            }
        }

        // Check columns
        for (int j = 0; j < SIZE; j++) {
            if (cells[0][j] != EMPTY && 
                cells[0][j] == cells[1][j] && 
                cells[1][j] == cells[2][j]) {
                return cells[0][j] == 'X' ? GameStatus.X_WON : GameStatus.O_WON;
            }
        }

        // Check main diagonal (top-left to bottom-right)
        if (cells[0][0] != EMPTY && 
            cells[0][0] == cells[1][1] && 
            cells[1][1] == cells[2][2]) {
            return cells[0][0] == 'X' ? GameStatus.X_WON : GameStatus.O_WON;
        }

        // Check anti-diagonal (top-right to bottom-left)
        if (cells[0][2] != EMPTY && 
            cells[0][2] == cells[1][1] && 
            cells[1][1] == cells[2][0]) {
            return cells[0][2] == 'X' ? GameStatus.X_WON : GameStatus.O_WON;
        }

        // Check for draw (no empty cells and no winner)
        boolean hasEmpty = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j] == EMPTY) {
                    hasEmpty = true;
                    break;
                }
            }
            if (hasEmpty) break;
        }

        return hasEmpty ? GameStatus.IN_PROGRESS : GameStatus.DRAW;
    }

    /**
     * Gets a copy of the board cells for display purposes.
     * @return 2D array representing the board state
     */
    public char[][] getCells() {
        char[][] copy = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(cells[i], 0, copy[i], 0, SIZE);
        }
        return copy;
    }

    public int getSize() {
        return SIZE;
    }
}
