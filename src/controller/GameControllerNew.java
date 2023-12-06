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
   * Exits the game.
   */
  void exitGame();

  /**
   * This method is used to set the player's name, initial location, item
   * capacity.
   * 
   * @param playerName      the name of the player.
   * @param initialLocation the initial location of the player.
   * @param itemCapacity    the item capacity of the player.
   * @param controlMode     the control mode of the player.
   * @return if the set is successfully.
   */
  boolean setNewPlayer(String playerName, int initialLocation, int itemCapacity,
      String controlMode);

  /**
   * This method is used to restart the game.
   */
  void restartGame();

  /**
   * the method to process the player's command.
   * 
   * @param command the command from the player.
   * @param extraId extra id the command needs.
   * @return the result of the command.
   */
  String processPlayerCommand(String command, int extraId);

}
