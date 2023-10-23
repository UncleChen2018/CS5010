package controller;

import model.GameModel;
/**
 * Represents a command to move the player to a neighboring room.
 */

public class MoveToNeighbor extends TurnBaseCommand {

  private int location;

  public MoveToNeighbor(int playerId, int location) {
    super(playerId);
    this.location = location;
  }

  @Override
  public String execute(GameModel model) throws IllegalArgumentException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("Player %s try to move to %s%n",
        model.getPlayerString(playerId), model.getRoomString(location)));
    model.setPlayerLocation(playerId, location);
    stringBuilder.append("Move to neighbor successfully.\n");
    return stringBuilder.toString();

  }

}
