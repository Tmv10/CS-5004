package controller;
/**
 * Interface representing the Controller in the MVC pattern for a Tic-Tac-Toe game.
 * It handles the interaction between the Model (game logic) and the View (user interface).
 */
public interface TTTController {

  /**
   * Processes a move for the current player at the specified board position.
   *
   * @param row The row where the player wants to move (0-indexed).
   * @param col The column where the player wants to move (0-indexed).
   */
  void processMove(int row, int col);

  /**
   * Resets the game, clearing the board and starting a new game.
   */
  void resetGame();

  /**
   * Runs the game loop or setup needed to start the game.
   */
  void startGame();
}

