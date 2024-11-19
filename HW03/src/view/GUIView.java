package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIView extends Application implements TTTView {

  private TTTController controller;
  private Button[][] buttons;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    buttons = new Button[3][3];
    GridPane grid = new GridPane();

    // Create buttons for each cell in the Tic-Tac-Toe board
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        buttons[i][j] = new Button("");
        buttons[i][j].setMinSize(100, 100);
        buttons[i][j].setStyle("-fx-font-size: 24;");
        final int row = i;
        final int col = j;
        buttons[i][j].setOnAction(e -> controller.makeMove(row, col));
        grid.add(buttons[i][j], j, i);
      }
    }

    Scene scene = new Scene(grid, 300, 300);
    primaryStage.setTitle("Tic-Tac-Toe");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  @Override
  public void updateBoard(Player[][] board) {
    // Update the buttons on the GUI with the current state of the board
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        String label = board[i][j] == null ? "" : board[i][j].toString();
        buttons[i][j].setText(label);
      }
    }
  }

  @Override
  public void displayWinner(Player winner) {
    String winnerMessage = winner != null ? "Winner: " + winner : "The game is a draw!";
    // Display winner in a dialog box or message area
    System.out.println(winnerMessage);  // For now, just print in the console
  }

  @Override
  public void displayInvalidMoveMessage(int row, int col) {
    // Display an alert for an invalid move (this can be done via a pop-up in JavaFX)
    System.out.println("Invalid move at row " + row + ", column " + col);
  }

  @Override
  public void displayCurrentPlayer(Player player) {
    // Update the title bar or display current player in a label
    System.out.println("Current player: " + player);
  }

  @Override
  public void displayWelcomeMessage() {
    // Optionally, display a welcome message
    System.out.println("Welcome to Tic-Tac-Toe!");
  }

  @Override
  public void displayError(String message) {
    // Show an error message using a JavaFX Alert or dialog
    System.out.println("Error: " + message);
  }

  @Override
  public void setController(TTTController controller) {
    this.controller = controller;
  }
}

