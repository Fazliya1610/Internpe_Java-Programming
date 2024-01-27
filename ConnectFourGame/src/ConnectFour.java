import java.util.Scanner;

public class ConnectFour {
	
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static char[][] board = new char[ROWS][COLUMNS];
    private static char currentPlayer = 'X';
    
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn. Enter column (1-" + COLUMNS + "): ");
            int column;
            while (true) {
                try {
                    column = Integer.parseInt(scanner.nextLine()) - 1;
                    if (isValidMove(column)) {
                        break;
                    } 
                    else {
                        System.out.println("Invalid move. Try again.");
                    }
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Enter a number.");
                }
            }
            dropPiece(column);
            boolean winner = checkWin();
            if (winner) {
                printWinner(currentPlayer);
                break;
            }

            if (isBoardFull()) {
                printWinner(' '); 
                System.out.println("It's a draw!");
                break;
            }
            printBoard();
            switchPlayer();
        }
        scanner.close();
    }
    
    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    private static void printBoard() {
        System.out.println("-----------------------------");
        for (int i = 0; i < ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("-----------------------------");
        }
        System.out.println("  1   2   3   4   5   6   7");
    }

    private static boolean isValidMove(int column) {
        return column >= 0 && column < COLUMNS && board[0][column] == ' ';
    }

    private static void dropPiece(int column) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = currentPlayer;
                break;
            }
        }
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    
    private static boolean checkWin() {
        return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
    }

    private static boolean checkHorizontalWin() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (checkRowForWin(row, col, 1, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkVerticalWin() {
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (checkRowForWin(row, col, 0, 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkDiagonalWin() {
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (checkRowForWin(row, col, 1, 1)) {
                    return true;
                }
            }
        }
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 3; col < COLUMNS; col++) {
                if (checkRowForWin(row, col, 1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean checkRowForWin(int startRow, int startCol, int rowIncrement, int colIncrement) {
        char player = board[startRow][startCol];
        if (player == ' ') {
            return false;
        }
        for (int i = 1; i < 4; i++) {
            int newRow = startRow + i * rowIncrement;
            int newCol = startCol + i * colIncrement;
            if (newRow < 0 || newRow >= ROWS || newCol < 0 || newCol >= COLUMNS) {
                return false;
            }
            if (board[newRow][newCol] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < COLUMNS; i++) {
            if (board[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }
    
    private static void printWinner(char winner) {
        if (winner != ' ') {
            System.out.println("Player " + winner + " wins!");
            System.out.println(" +\"\"\"\"\"+ ");
            System.out.println("[| o o |]");
            System.out.println(" |  ^  | ");
            System.out.println(" | '-' | ");
            System.out.println(" +-----+ ");
        } 
        else {
            System.out.println("It's a draw!");
        }
    }
}
