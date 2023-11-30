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
  void setMaxTurn(int turnLimit);

  /**
   * This is the game runner.
   */
  void executeGmae();

}
