package model;

/**
 * Enum representing the two possible players in a Tic-Tac-Toe game.
 * Players are either X or O.
 */
public enum Player {
  X, O;

  /**
   * Switches the current player to the other player.
   *
   * @return The other player (e.g., if X, returns O; if O, returns X).
   */
  public Player switchPlayer() {
    return this == X ? O : X;
  }
}
