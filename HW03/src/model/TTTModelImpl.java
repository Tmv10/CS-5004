package model;

/**
 * Concrete implementation of the TTTModel interface.
 * This class implements the logic for making moves, switching players, and determining the game state.
 */
public class TTTModelImpl implements TTTModel {
  private Player[][] board;
  private Player currentPlayer;
  private int movesCount;

  public TTTModelImpl() {
    this.board = new Player[3][3];
    this.currentPlayer = Player.X;
    this.movesCount = 0;
  }

  @Override
  public void makeMove(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != null) {
      throw new IllegalArgumentException("Invalid move.");
    }
    board[row][col] = currentPlayer;
    movesCount++;
    switchPlayer();
  }

  @Override
  public Player[][] getBoardState() {
    return board;
  }

  @Override
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public void switchPlayer() {
    currentPlayer = currentPlayer.switchPlayer();
  }

  @Override
  public void resetGame() {
    board = new Player[3][3];
    currentPlayer = Player.X;
    movesCount = 0;
  }

  @Override
  public boolean isGameOver() {
    // Check for a winner
    if (getWinner() != null) {
      return true;
    }
    // Check for a draw (if board is full and there's no winner)
    return movesCount == 9;
  }

  @Override
  public Player getWinner() {
    // Check rows, columns, and diagonals for a winner
    for (int i = 0; i < 3; i++) {
      if (board[i][0] != null && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
        return board[i][0];
      }
      if (board[0][i] != null && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
        return board[0][i];
      }
    }
    // Check diagonals
    if (board[0][0] != null && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
      return board[0][0];
    }
    if (board[0][2] != null && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
      return board[0][2];
    }
    return null; // No winner
  }
}

