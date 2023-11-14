package controller;

import model.GameModel;

/**
 * Represents a command to move the pet to a valid room.
 */

public class MovePet extends TurnBaseCommand {

  private int location;

  /**
   * Creates a new "Move Pet" command for the specified player and target
   * location.
   *
   * @param playerId The unique identifier of the player initiating the move.
   * @param location The target location to move the pet to.
   */
  public MovePet(int playerId, int location) {
    super(playerId);
    this.location = location;
  }

  @Override
  public String execute(GameModel model) throws IllegalArgumentException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("Player %s try to move pet %s to %s\n",
        model.getPlayerString(playerId), model.getPetString(), model.getRoomString(location)));
    model.teleportPetLocation(location);
    stringBuilder
        .append(String.format("Move pet to %s successfully.\n", model.getRoomString(location)));
    return stringBuilder.toString();

  }

}
