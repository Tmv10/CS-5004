package view;

// Importing the Player class from the model package
/**
 * The {@code Player} class is imported from the model package to represent
 * the players of the Tic-Tac-Toe game. This class is essential for identifying
 * the current player in various methods, such as in {@code getPlayerMove()}
 * where we use the current player to prompt them for their move and display the outcome.
 * It is assumed that the Player class contains the player's name, symbol (X or O), and
 * potentially other game-specific data.
 */

import model.Player;

/**
 * Represents the View component for a Tic-Tac-Toe game, responsible for
 * user interactions and game state presentation.
 *
 * Design Principles:
 * - Supports multiple input/output mechanisms (console, GUI, mock testing)
 * - Minimal coupling with Model and Controller
 * - Clear separation of responsibilities
 */
public interface TTTView {



  /**
   * Prompts the current player to enter their move and returns the chosen move.
   *
   * @param currentPlayer the player whose turn it is; must not be null
   * Ensures that the current player is valid, preventing errors in gameplay.
   * @return an array of two integers representing the row and column of the chosen move,
   *         both zero-based and within the valid bounds of the game board
   * @throws IllegalStateException if the move cannot be obtained due to the game
   *         being in an invalid state (e.g., the game has ended or the input mechanism is unavailable)
   * @throws IllegalArgumentException if the game outcome is incomplete or if the winner is not a valid player
   *         (i.e., a non-existent player object), this exception will be thrown.
   *
   * Semantics:
   * - Prompts the current player to provide their move
   * - Supports different input mechanisms based on the view implementation (e.g., console, GUI, or test stubs)
   * - Ensures the returned move is within the valid bounds of the game board
   * - Validates the input to maintain game state integrity
   */
  // Inline comment:
  // Move input mechanism varies by view implementation:
  // - Console view uses text input
  // - GUI view uses mouse clicks or button interactions
  // - Test view provides predefined or programmatic moves
  // Move validation occurs before returning to ensure game integrity
  int[] getPlayerMove(Player currentPlayer) throws IllegalStateException;




  /**
   * Displays a message indicating the outcome of the game.
   *
   * @param winner the player who won the game, or null if the game resulted in a draw
   * @throws IllegalArgumentException if the game is not yet completed or winner
   *         is neither null nor a valid Player
   *
   * Semantics:
   * - Communicates the final result of the game to the user
   * - Displays a win message if a valid player wins
   * - Declares a draw if winner is null
   * - Prevents invalid game state inputs to maintain clarity and integrity
   */
  // Inline comment:
  // Outcome display can vary by implementation:
  // - Console view might print a text message
  // - GUI view could show a dialog or update the interface
  // - Supports both win and draw scenarios
  void displayGameOutcome(Player winner) throws IllegalArgumentException;



  /**
   * Displays an error or informational message to the user.
   *
   * @param message The message to display
   * @param isError Indicates whether this is an error message
   * @throws IllegalArgumentException if message is null or empty
   *
   * Semantics:
   * - Provides flexible messaging mechanism
   * - Distinguishes between error and informational messages
   * - Supports various communication needs during the game
   */
  // Inline comment:
  // Messaging can be used for:
  // - Invalid move notifications
  // - Game status updates
  // - Error condition communication
  // Differentiates between error and informational messages
  void displayMessage(String message, boolean isError) throws IllegalArgumentException;




  /**
   * Determines if the user wants to play another game.
   *
   * @return true if user wants to play again, false otherwise
   * @throws IllegalStateException if replay decision cannot be obtained
   *
   * Semantics:
   * - Allows for game replay after completion
   * - Supports different interaction models for replay decision
   * - Provides a mechanism to restart or end the game session
   */
  // Inline comment:
  // Replay prompt can be implemented differently:
  // - Console view might use text input
  // - GUI view could use a button or menu option
  // - Supports multiple ways of expressing game continuation
  boolean promptPlayAgain() throws IllegalStateException;




  /**
   * Closes and releases any resources used by the view.
   *
   * Semantics:
   * - Ensures proper cleanup of view-specific resources
   * - Supports different types of resource management
   * - Provides a standard method for view termination
   */
  // Inline comment:
  // Cleanup can include:
  // - Closing input/output streams
  // - Disposing of GUI components
  // - Releasing any held resources
  void close();



  /**
   * Displays a message to the user indicating whose turn it is.
   *
   * @param currentPlayer the player whose turn it is; must not be null
   * @throws IllegalArgumentException if the {@code currentPlayer} is null
   *
   * Semantics:
   * - Displays which player's turn it is, typically used for text-based or GUI games
   * - Provides a clear indication of the game flow and whose action is next
   */
//  Inline comment:
//  This method is essential for communicating the current turn to the user. In a console-based view,
//  it might print a text message like "Player X, it's your turn," while in a GUI, it might display
//  a label or notification to that effect. This feedback is necessary to ensure smooth game flow
// and prevent confusion during gameplay.
//
  void displayCurrentPlayerTurn(Player currentPlayer) throws IllegalArgumentException;



  /**
   * Displays a prompt to the user for an invalid move (e.g., if the user tries to place a mark on a full cell).
   *
   * @param message the error message to display
   * @throws IllegalArgumentException if the message is null or empty
   *
   * Semantics:
   * - Provides feedback when the user makes an invalid move
   * - Ensures the game state is protected by preventing illegal moves
   * - Provides user-friendly prompts that can help guide gameplay
   */
  //Inline comment:
  // This method is used to inform the user when their move is invalid. It could be triggered by
  //scenarios like trying to place a mark on a cell that is already occupied. The message should be
  // clear and instructive to help the player understand what went wrong. This method improves the
  // overall user experience by preventing errors and offering guidance.
  //
  void displayInvalidMoveMessage(String message) throws IllegalArgumentException;



  /**
   * Requests confirmation from the user to proceed with the current game state (e.g., confirming a game reset).
   *
   * @return true if the user confirms, false if the user cancels
   * @throws IllegalStateException if the confirmation cannot be obtained due to the game being in an invalid state
   *
   * Semantics:
   * - Allows the game to proceed with user confirmation (e.g., restarting or making a significant move)
   * - Can be used to prevent accidental game changes
   * - Supports flexible user interfaces (e.g., yes/no prompt or button click)
   */
//  Inline comment:
// This method is used for important game actions that require user confirmation, such as restarting
// the game or making irreversible decisions. In a console-based view, it could prompt the user with a
// "yes/no" text input. In a GUI, it could display a confirmation dialog with options. This confirmation
// mechanism adds an extra layer of safety and clarity to the gameplay experience.
//
  boolean confirmAction(String actionDescription) throws IllegalStateException;



  /**
   * Displays a welcome or start message to the user when a game session begins.
   *
   * @throws IllegalStateException if the message cannot be displayed
   *
   * Semantics:
   * - Introduces the game to the user, providing an initial greeting or instructions
   * - Enhances user experience by preparing the user for gameplay
   */
//  Inline comment:
// The welcome message provides context for the game, introducing the rules or objectives to the player.
// This is especially useful for new users or those playing the game for the first time. It also sets the
// tone for the experience and prepares the user for the first move. In a GUI, this could be displayed
// in a splash screen or an initial dialog box.
  void displayWelcomeMessage() throws IllegalStateException;
}

