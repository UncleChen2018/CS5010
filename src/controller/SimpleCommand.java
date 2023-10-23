package controller;

import model.GameModel;

/**
 * Interface for executing simple commands in the game model. Implementations
 * should provide logic to execute a command.
 * 
 * @param model The game model on which the command will be executed.
 * @return A string representing the result or feedback of the command
 *         execution.
 */

public interface SimpleCommand {
  String execute(GameModel model);
}
