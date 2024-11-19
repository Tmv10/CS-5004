package view;

/**
 * ConsoleView is a concrete implementation of the TTTView interface for displaying
 * the Tic-Tac-Toe game in the console. This class handles the visual output, such as
 * updating the board, displaying the current player, and notifying the user of game events.
 */
public class ConsoleView implements TTTView {

  /**
   * Updates the display of the game board on the console.
   * Each position on the board is represented by either 'X', 'O', or '.' for empty spots.
   *
   * @param board a 2D array representing the current state of the game board
   *              where each element is either Player.X, Player.O, or null (empty).
   */
  @Override
  public void updateBoard(Player[][] board) {
    System.out.println("Current board:");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // Print 'X', 'O', or '.' (empty) based on the board's state
        System.out.print(board[i][j] == null ? "." : board[i][j]);
      }
      System.out.println();
    }
  }

  /**
   * Displays the winner of the game or announces a draw.
   * If the winner is not null, it shows the winning player's symbol; otherwise, it declares a draw.
   *
   * @param winner the Player object representing the winner (either Player.X or Player.O),
   *               or null if the game ended in a draw.
   */
  @Override
  public void displayWinner(Player winner) {
    if (winner != null) {
      System.out.println("Winner: " + winner);
    } else {
      System.out.println("The game is a draw!");
    }
  }

  /**
   * Displays an error message when an invalid move is attempted.
   * The error message specifies the row and column of the invalid move.
   *
   * @param row the row index of the invalid move (0, 1, or 2)
   * @param col the column index of the invalid move (0, 1, or 2)
   */
  @Override
  public void displayInvalidMoveMessage(int row, int col) {
    System.out.println("Invalid move at row " + row + ", column " + col);
  }

  /**
   * Displays the current player whose turn it is.
   * The player is represented as either Player.X or Player.O.
   *
   * @param player the Player object representing the current player (either Player.X or Player.O)
   */
  @Override
  public void displayCurrentPlayer(Player player) {
    System.out.println("Current player: " + player);
  }

  /**
   * Displays a welcome message when the game starts.
   * This message is shown to inform the player that the game is starting.
   */
  @Override
  public void displayWelcomeMessage() {
    System.out.println("Welcome to Tic-Tac-Toe!");
  }

  /**
   * Displays a generic error message when something unexpected happens.
   * This method is used for non-specific error messages, such as invalid input or game state issues.
   *
   * @param message a string describing the error that occurred
   */
  @Override
  public void displayError(String message) {
    System.out.println("Error: " + message);
  }
}

