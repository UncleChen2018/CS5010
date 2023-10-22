/**
 * 
 */
package controller;

import model.GameModel;

/**
 * Look around make a player has the ability to get its neighbors detail.
 */
public class LookAround extends TurnBaseCommand {

  /**
   * 
   */
  public LookAround(int playerId) {
    super(playerId);
  }

  @Override
  public String execute(GameModel model) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Look Aroud result\n");
    int playerLocation = model.getPlayerLocation(playerId);
    stringBuilder.append(model.queryRoomDetails(playerLocation)).append("\n");
    
    // out.append(model.queryRoomDetails(playerLocation)).append("\n");
    for (int i : model.getRoomNeighbors(playerLocation)) {
      stringBuilder.append(model.queryRoomDetails(i)).append("\n");
    }
    return stringBuilder.toString();

  }

}
