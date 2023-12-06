package controller;

import model.GameModel;

/**
 * The controller interface. Star a single game model and control its way.
 */
public interface GameControllerNew {
  /**
   * Starts the game using the provided {@code GameModel}. This method initiates
   * the game and sets up the necessary components based on the provided game
   * model.Every time it runs, it means a new start of game.
   *
   * @param model The {@code GameModel} containing the initial configuration and
   *              state of the game.
   */
  void start(GameModel model);

  /**
   * @param worldSource
   */
  void setWorldResource(Readable worldSource);

  /**
   * @param turnLimit
   */
  boolean setMaxTurn(int turnLimit);

  /**
   * This is the game runner.
   */
  void executeGmae();

  /**
   * Loads the game map data from a specified file path. And let the view connect
   * with the model's world data.
   *
   * @param filePath The file path from which to load the game map data.
   */
  void loadWorldFile(String filePath);

  /**
   * 
   */
  void exitGame();

  /**
   * @param playerName
   * @param initialLocation
   * @param itemCapacity
   * @param controlMode
   * @return if the set is successfully.
   */
  boolean setNewPlayer(String playerName, int initialLocation, int itemCapacity,
      String controlMode);

  void restartGame();

}
