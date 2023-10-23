package controller;

/**
 * Abstract class representing a turn-based command in the game. Subclasses
 * should implement the logic for executing the command.
 * 
 * @param playerId The ID of the player executing the command.
 */

public abstract class TurnBaseCommand implements SimpleCommand {
  protected int playerId;

  protected TurnBaseCommand(int playerId) {
    this.playerId = playerId;
  }
}
