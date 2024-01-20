
import java.util.Arrays;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    public TicTacToe() {
        board = new char[3][3];
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
        currentPlayer = 'X';
    }
    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false; 
        }

        board[row][col] = currentPlayer;
        switchPlayer();
        return true;
    }
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
    public char getCurrentPlayer() {
        return currentPlayer;
    }
    public char[][] getBoard() {
        return board;
    }
    public boolean checkForWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2]) ||
                checkLine(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return checkLine(board[0][0], board[1][1], board[2][2]) ||
               checkLine(board[0][2], board[1][1], board[2][0]);
    }
    private boolean checkLine(char a, char b, char c) {
        return a != ' ' && a == b && b == c;
    }
}
