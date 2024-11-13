import java.util.Scanner;
public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private boolean gameWon;
    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameWon = false;
        initializeBoard();
    }

    // Initialize the game board
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Display the game rules
    private void displayRules() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Players take turns marking a space in a 3x3 grid.");
        System.out.println("The first player to get 3 in a row (horizontally, vertically, or diagonally) wins.");
        System.out.println("Enter your move as row and column numbers (0, 1, or 2).");
        System.out.println("For example, to mark the top-left corner, enter '0 0'.");
    }

    // Display the current state of the board
    private void displayBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            System.out.print(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("-----------");
        }
    }

    // Check for a win
    private boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
               (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    // Check for a tie
    private boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Play the game
    public void play() {
        Scanner scanner = new Scanner(System.in);
        displayRules();
        
        while (!gameWon) {
            displayBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
                System.out.println("This move is not valid. Try again.");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkWin()) {
                displayBoard();
                System.out.println("Congratulation You are perfect player" + currentPlayer + " WINNER!!!!!");
                gameWon = true;
            } else if (checkTie()) {
                displayBoard();
                System.out.println("It's a tie!");
                gameWon = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch player
            }
        }

        System.out.println("Game over! Would you like to play again? (yes/no)");
        String response = scanner.next();
        if (response.equalsIgnoreCase("yes")) {
            initializeBoard();
            currentPlayer = 'X';
            gameWon = false;
            play();
        } else {
            System.out.println("Thank you for playing!");
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}

