package controller;

import model.GameModel;

/**
 * Look around make a player has the ability to get its neighbors detail.
 */
public class LookAround extends TurnBaseCommand {
  /**
   * Creates a new "Look Around" command for the specified player.
   *
   * @param playerId The unique identifier of the player initiating the command.
   */
  public LookAround(int playerId) {
    super(playerId);
  }

  @Override
  public String execute(GameModel model) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("Player %s try to look around from %s\n",
        model.getPlayerString(playerId), model.getRoomString(model.getPlayerLocation(playerId))));
    if (model.isHumanPlayer(playerId)) {
      stringBuilder.append("Look Aroud result\n");
      int playerLocation = model.getPlayerLocation(playerId);
      stringBuilder.append(model.queryRoomDetails(playerLocation)).append("\n");
      for (int i : model.getRoomNeighbors(playerLocation)) {
        stringBuilder.append(model.queryRoomDetails(i)).append("\n");
      }
    } else {
      stringBuilder.append("Computer look around result ommitted.\n");
    }
    return stringBuilder.toString();

  }

}
