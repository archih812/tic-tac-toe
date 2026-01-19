package com.archihalder;

public class Game {
    private final Board board;
    private char currentPlayer;
    private static final char[] PLAYERS = {'O', 'X'};

    public Game() {
        this.board = new Board();
        this.currentPlayer = PLAYERS[0]; // Start with player O
    }

    /**
     * Attempts to play a turn at the given position.
     * @param position The position (1-9) where the current player wants to move
     * @return MoveResult indicating success/failure and game status
     */
    public MoveResult playTurn(int position) {
        if (position < 1 || position > 9) {
            return new MoveResult(false, "Invalid position. Choose a position between 1 and 9.", board.getStatus());
        }

        if (!board.placeMove(currentPlayer, position)) {
            return new MoveResult(false, "Position already occupied. Choose a different position.", board.getStatus());
        }

        GameStatus status = board.getStatus();
        
        // Switch player if game is still in progress
        if (status == GameStatus.IN_PROGRESS) {
            currentPlayer = (currentPlayer == PLAYERS[0]) ? PLAYERS[1] : PLAYERS[0];
        }

        return new MoveResult(true, null, status);
    }

    /**
     * Gets the current player whose turn it is.
     * @return The current player ('O' or 'X')
     */
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the board instance.
     * @return The game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Gets the current game status.
     * @return The current game status
     */
    public GameStatus getStatus() {
        return board.getStatus();
    }

    /**
     * Renders the board as a formatted string with borders.
     * Empty cells show their position number (1-9).
     * @return Formatted string representation of the board
     */
    public String render() {
        char[][] cells = board.getCells();
        int size = board.getSize();
        StringBuilder sb = new StringBuilder();
        
        // Top border
        sb.append("┌───┬───┬───┐\n");
        
        for (int i = 0; i < size; i++) {
            sb.append("│ ");
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == ' ') {
                    // Show position number for empty cells
                    sb.append(i * size + j + 1);
                } else {
                    sb.append(cells[i][j]);
                }
                if (j < size - 1) {
                    sb.append(" │ ");
                } else {
                    sb.append(" │");
                }
            }
            sb.append("\n");
            
            // Middle separator (not after last row)
            if (i < size - 1) {
                sb.append("├───┼───┼───┤\n");
            }
        }
        
        // Bottom border
        sb.append("└───┴───┴───┘");
        
        return sb.toString();
    }

    /**
     * Result of a move attempt.
     */
    public static class MoveResult {
        private final boolean success;
        private final String errorMessage;
        private final GameStatus status;

        public MoveResult(boolean success, String errorMessage, GameStatus status) {
            this.success = success;
            this.errorMessage = errorMessage;
            this.status = status;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public GameStatus getStatus() {
            return status;
        }
    }
}
