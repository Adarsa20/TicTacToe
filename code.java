import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            char[][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
            };
            char currentPlayer = 'X';
            boolean gameWon = false;

            System.out.println("Welcome to Tic Tac Toe!");
            printBoard(board);

            while (!isBoardFull(board) && !gameWon) {
                System.out.println("Player " + currentPlayer + ", it's your turn.");
                int row, col;

                while (true) {
                    System.out.print("Enter row (1-3): ");
                    row = scanner.nextInt() - 1;
                    System.out.print("Enter column (1-3): ");
                    col = scanner.nextInt() - 1;

                    if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                        board[row][col] = currentPlayer;
                        break;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                }

                printBoard(board);
                gameWon = checkWin(board, currentPlayer);

                if (gameWon) {
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (isBoardFull(board)) {
                    System.out.println("It's a draw!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch players
                }
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! Goodbye.");
        scanner.close();
    }

    // Print the current board
    private static void printBoard(char[][] board) {
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    // Check if the board is full
    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

    // Check if the current player has won
    private static boolean checkWin(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) || // Row
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) { // Column
                return true;
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) || // Diagonal
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);   // Reverse diagonal
    }
}
