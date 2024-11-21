package model;


/**
 * Interface representing the Tic-Tac-Toe game model.
 *
 * This interface provides methods required by a Controller to manage the game's state
 * while abstracting away internal representations of the board and logic.
 *
 * Design Principles:
 * - No internal details (e.g., how the board is stored) are exposed.
 * - Player logic and turn-taking are encapsulated within the Model.
 * - Robust exception handling ensures the Model remains in a valid state.
 */
public interface TTTModel {


  /**
   * Attempts to make a move for the current player at the specified position.
   *
   * @param row The row index of the position (0-indexed).
   * @param col The column index of the position (0-indexed).
   * @throws IllegalArgumentException If the position is out of bounds or already occupied.
   *         This ensures only valid moves are accepted.
   * @throws IllegalStateException If the game is over or it is not the current player's turn.
   *         Prevents illegal actions like moving after the game ends or skipping turns.
   * Semantics:
   * - A valid move updates the board for the current player and switches the turn.
   * - Internally, the board may be stored as a 2D array of `Player` values.
   *  - If the move is invalid, no board state changes occur.
   *
   **/
  // Inline comment:
  // Exceptions are handled by validating the move before making any changes to the board.
  // - IllegalArgumentException is thrown if the move is out of bounds or the position is already occupied.
  // - IllegalStateException is thrown if the game is over or if it's not the current player's turn.
  // These exceptions are thrown before any state changes occur, ensuring the board remains in a valid state and
  // preventing any invalid actions or game-breaking moves.

  void makeMove(int row, int col) throws IllegalArgumentException, IllegalStateException;




  /**
   * Returns the player at the specified board position.
   *
   * @param row The row index of the position (0-indexed).
   * @param col The column index of the position (0-indexed).
   * @return The {@link Player} at the specified position (X, O, or NONE if unoccupied).
   * @throws IllegalArgumentException If the position is out of bounds.
   *         Prevents access to invalid board locations.
   *
   * Semantics:
   * - NONE is returned for unoccupied cells instead of null to ensure null safety.
   * - This abstracts the internal board representation (e.g., a 2D array).
   */
  Player getPlayerAt(int row, int col) throws IllegalArgumentException;




  /**
   * Gets the current player whose turn it is to make a move.
   *
   * @return The {@link Player} (X or O) whose turn it is currently.
   *
   * Semantics:
   * - The turn alternates only after a valid move is made.
   * - Ensures the game's turn-taking rules are enforced without exposing state directly.
   */
   // Inline comment:
   // The current player is tracked and updated after each valid move, ensuring the
   //   game alternates turns correctly.

  Player getCurrentPlayer();



  /**
   * Checks if the game is over, either by a win or a draw.
   *
   * @return true if the game is over, false otherwise.
   *
   * Semantics:
   * - The game ends if there is a winner or if the board is completely filled with no winner.
   * - This method allows the Controller to determine when to stop accepting moves.
   **/
   //Inline comment:
   // Internally, the game checks whether all conditions for ending the game have been met
   //       (win or draw).
  //  Typically, this would check if the board is full or if a player has three-in-a-row.


  boolean isGameOver();



  /**
   * Returns the winner of the game.
   *
   * @return The player who won the game (X, O, or NONE if it's a draw or the game isn't over).
   *
   * Semantics:
   * - Returns `Player.NONE` if no winner exists (either because the game isn't over or it's a draw).
   * - Ensures the caller gets a consistent result even if the game is incomplete.
   */
    //Inline comment:
   //This method will return NONE if no player has won yet, or if the game is still ongoing.
   // In cases of a tie, NONE also represents the outcome.


  Player getWinner();



  /**
   * Determines if the game ended in a tie.
   * A tie occurs when all positions are filled and there is no winner.
   *
   * @return true if the game is a tie, false otherwise.
   *
   * Semantics:
   * - A tie occurs only when there are no winners and the board is full.
   * - This method provides an explicit way to check if the game resulted in a draw.
   */
  //Inline comment:
   //A tie is checked by confirming if the board is filled and no player has won.
   // This ensures that the game ends in a draw when no player meets the victory conditions.


  boolean isTie();



  /**
   * Resets the game to its initial state, with an empty board and Player X starting.
   *
   * Semantics:
   * - Internally, this may involve resetting the board and setting the starting player to X.
   * - Ensures that the game can be replayed after completion.
   */
   // Inline comment:
   // Resetting the game will return the board to its empty state and reset the player turn
   //  back to Player X.
   // This can be done by clearing the internal board structure and setting the current player
   //  to X.


  void resetGame();


  /**
   * Checks if the specified move is valid (i.e., within bounds and on an empty cell).
   * This method is useful for the Controller to validate a move before attempting it.
   *
   * @param row The row index of the move (0-indexed).
   * @param col The column index of the move (0-indexed).
   * @return true if the move is valid, false otherwise.
   *
   * Semantics:
   * - A valid move requires the position to be within bounds (0 <= row, col < board size)
   *   and the corresponding cell to be empty (Player.NONE).
   * - This validation helps avoid exceptions and allows for pre-checking before executing moves.
   */
   //Inline comment:
   // This method checks the position for both boundary conditions and whether the cell
   //  is unoccupied, ensuring that only legal moves are made.
   // Internally, a check would be performed on the board to see if the move is valid.

  boolean isValidMove(int row, int col);


  /**
   * Gets the size of the game board.
   * This is useful for views that need to render the board or validate input ranges.
   * The board is always square (same number of rows and columns).
   *
   * @return the size of the board (traditionally 3 for Tic Tac Toe).
   *
   * Semantics:
   * - The board is assumed to be square, and the size defines the grid's dimensions.
   * - Typically, for Tic-Tac-Toe, this is 3, but the model could be extended for larger boards.
   */
   //Inline comment:
   //The board size is a constant, and it is typically 3 for a traditional Tic-Tac-Toe game.
   // For flexibility, it could be extended to handle boards of different sizes.

  int getBoardSize();
}
