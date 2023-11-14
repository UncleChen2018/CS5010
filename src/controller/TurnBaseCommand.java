package controller;

/**
 * Abstract class representing a turn-based command in the game. Subclasses
 * should implement the logic for executing the command.
 * 
 * @param playerId The ID of the player executing the command.
 */

public abstract class TurnBaseCommand implements SimpleCommand {
  protected int playerId;

  /**
   * Constructs a new turn-based command with the specified player ID.
   *
   * @param playerId The unique identifier of the player associated with the
   *                 command.
   */
  protected TurnBaseCommand(int playerId) {
    this.playerId = playerId;
  }
}
