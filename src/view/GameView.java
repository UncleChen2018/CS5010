package view;

import controller.GameControllerNew;

/**
 * The game view interface, which contains methods for the controller to use.
 */
public interface GameView {

  /**
   * Returns the input source of the view (e.g., InputStream or Scanner).
   *
   * @return The input source of the view.
   */
  Readable getInputSource();

  /**
   * Returns the output destination of the view (e.g., PrintStream or Appendable).
   *
   * @return The output destination of the view.
   */
  Appendable getOutputDestination();

  /**
   * Configures the view with the provided GameControllerNew.
   *
   * @param controller The GameControllerNew to configure the view.
   */
  void configureView(GameControllerNew controller);

  /**
   * Displays the view.
   */
  void display();

  /**
   * Refreshes the view.
   */
  void refresh();

  /**
   * Checks if the view requires GUI output.
   *
   * @return True if GUI output is required, false otherwise.
   */
  boolean requiresGuiOutput();

  /**
   * Shows the welcome message.
   */
  void showWelcomeMessage();

  /**
   * Shows the farewell message.
   */
  void showFarewellMessage();

  /**
   * Draws the map based on the provided GameControllerNew.
   *
   * @param controller The GameControllerNew containing the game state.
   */
  void drawMap(GameControllerNew controller);

  /**
   * Displays the UI for adding a player based on the provided GameControllerNew.
   *
   * @param controller The GameControllerNew containing the game state.
   */
  void displayAddPlayer(GameControllerNew controller);

  /**
   * Updates the status label.
   */
  void updateStatusLabel();

  /**
   * Displays the UI for setting the maximum game turn based on the provided
   * GameControllerNew.
   *
   * @param controller The GameControllerNew containing the game state.
   */
  void disPlaySetGameMaxTurn(GameControllerNew controller);

  /**
   * Resets the view.
   */
  void rest();

  /**
   * Shows the game end message based on the provided GameControllerNew.
   *
   * @param controller The GameControllerNew containing the game state.
   */
  void showGameEnd(GameControllerNew controller);

  /**
   * Updates the result string in the view.
   *
   * @param resultString The result string to be displayed.
   */
  void upateResult(String resultString);
}
