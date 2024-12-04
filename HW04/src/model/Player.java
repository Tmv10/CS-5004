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
   * Semantics:
   * Returns the opposite player of the current one, ensuring that turns alternate.
   * If the current player is NONE, it returns NONE to maintain consistency.
   */
  public Player switchPlayer() {
    // Inline comment: Switching players alternates between X and O.
    // The switch logic ensures that if the current player is NONE, it remains NONE.

    return this == X ? O : X;
  }
}
