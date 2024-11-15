/**
 * Interface representing the Tic-Tac-Toe game model.
 * This interface provides all methods required by the Controller
 * to manage the game's state, without exposing the Model's internal representations.
 */
public interface TTTModel {

  /**
   * Attempts to make a move at the specified position for the current player.
   *
   * @param row The row where the move is to be made (0-indexed).
   * @param col The column where the move is to be made (0-indexed).
   * @throws IllegalArgumentException If the position is out of bounds or already occupied.
   * @throws IllegalStateException If the game is already over or it is not the current player's turn.
   */
  void makeMove(int row, int col) throws IllegalArgumentException, IllegalStateException;

  /**
   * Returns the player at the specified board position.
   *
   * @param row The row of the position (0-indexed).
   * @param col The column of the position (0-indexed).
   * @return The player at the specified position (X, O, or NONE if unoccupied).
   * @throws IllegalArgumentException If the position is out of bounds.
   */
  Player getPlayerAt(int row, int col) throws IllegalArgumentException;

  /**
   * Gets the current player whose turn it is to make a move.
   *
   * @return The current player (X or O).
   */
  Player getCurrentPlayer();

  /**
   * Checks if the game is over, either by a win or a draw.
   *
   * @return true if the game is over, false otherwise.
   */
  boolean isGameOver();

  /**
   * Returns the winner of the game.
   *
   * @return The player who won the game (X, O, or NONE if it's a draw or the game isn't over).
   */
  Player getWinner();


  /**
   * Determines if the game ended in a tie.
   * A tie occurs when all positions are filled and there is no winner.
   *
   * @return true if the game is a tie, false otherwise
   */
  boolean isTie();

  /**
   * Resets the game to its initial state, with an empty board and Player X starting.
   */
  void resetGame();

  /**
   * Checks if the specified move is valid (i.e., within bounds and on an empty cell).
   * This method is useful for the Controller to validate a move before attempting it.
   *
   * @param row The row of the move (0-indexed).
   * @param col The column of the move (0-indexed).
   * @return true if the move is valid, false otherwise.
   */
  boolean isValidMove(int row, int col);

  /**
   * Gets the size of the game board.
   * This is useful for views that need to render the board or validate input ranges.
   * The board is always square (same number of rows and columns).
   *
   * @return the size of the board (traditionally 3 for Tic Tac Toe)
   */
  int getBoardSize();

}

