package view;

/**
 * Interface representing the View in the MVC pattern for a Tic-Tac-Toe game.
 * It handles the display of the game state and user interaction output.
 */
public interface TTTView {

  /**
   * Updates the game board display with the current state.
   * This method is called after every move or game reset.
   *
   * @param board A 2D array representing the current state of the game board,
   *              where each cell is filled with a Player or null.
   */
  void updateBoard(Player[][] board);

  /**
   * Displays a message showing the winner of the game.
   * This method is called when the game ends and a winner has been determined.
   *
   * @param winner The Player who won the game (either Player.X or Player.O).
   *               If there is no winner, it might display a draw or not be called.
   */
  void displayWinner(Player winner);

  /**
   * Displays a message indicating that an invalid move was attempted.
   * This could occur when a player tries to place a mark on an already occupied spot
   * or if the move is made after the game is over.
   *
   * @param row The row where the invalid move was attempted.
   * @param col The column where the invalid move was attempted.
   */
  void displayInvalidMoveMessage(int row, int col);

  /**
   * Displays the current player to the user, indicating whose turn it is.
   * This method is called whenever it's the next player's turn.
   *
   * @param player The current Player (either Player.X or Player.O).
   */
  void displayCurrentPlayer(Player player);

  /**
   * Displays a welcome message at the beginning of the game.
   * This method is called when the game is first started or reset.
   */
  void displayWelcomeMessage();

  /**
   * Displays an error message to the user.
   * This can be used for any general errors, such as invalid game states or exceptions.
   *
   * @param message The error message to display to the user.
   */
  void displayError(String message);
}

