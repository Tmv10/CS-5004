package controller;

/**
 * Implementation of the TTTController for a Tic-Tac-Toe game.
 * This class processes player moves, updates the view, and communicates with the model.
 */
public class TTTControllerImpl implements TTTController {
  private final TTTModel model;
  private final TTTView view;

  /**
   * Constructor for the TTTControllerImpl.
   *
   * @param model The Tic-Tac-Toe game model.
   * @param view The view to display the game state.
   */
  public TTTControllerImpl(TTTModel model, TTTView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Processes a move by the current player at the specified board position.
   * Updates the view based on the move's success and the resulting game state.
   *
   * @param row The row where the player wants to move.
   * @param col The column where the player wants to move.
   */
  @Override
  public void processMove(int row, int col) {
    try {
      if (model.isValidMove(row, col)) {
        model.makeMove(row, col);
        view.updateBoard(model.getBoardState());

        if (model.isGameOver()) {
          view.displayWinner(model.getWinner());
        } else {
          view.displayCurrentPlayer(model.getCurrentPlayer());
        }
      } else {
        view.displayInvalidMoveMessage(row, col);
      }
    } catch (IllegalArgumentException | IllegalStateException e) {
      view.displayError(e.getMessage());
    }
  }

  /**
   * Resets the game by clearing the board and starting a new game.
   */
  @Override
  public void resetGame() {
    model.resetGame();
    view.updateBoard(model.getBoardState());
    view.displayCurrentPlayer(model.getCurrentPlayer());
  }

  /**
   * Starts the game loop or any setup necessary to begin playing.
   */
  @Override
  public void startGame() {
    view.displayWelcomeMessage();
    view.updateBoard(model.getBoardState());
    view.displayCurrentPlayer(model.getCurrentPlayer());
  }
}

