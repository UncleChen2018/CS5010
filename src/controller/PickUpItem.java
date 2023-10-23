package controller;

import model.GameModel;
/**
 * A command to pick up an item.
 */

public class PickUpItem extends TurnBaseCommand {
  private int itemId;

  public PickUpItem(int playerId, int itemId) {
    super(playerId);
    this.itemId = itemId;
  }

  @Override
  public String execute(GameModel model) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("Player %s try to pick up %s\n",
        model.getPlayerString(playerId), model.getItemString(itemId)));
    model.pickUpitem(playerId, itemId);
    stringBuilder.append("Pick up successfully.\n");
    return stringBuilder.toString();
  }

}
