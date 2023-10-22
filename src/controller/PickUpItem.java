package controller;

import model.GameModel;

public class PickUpItem extends TurnBaseCommand {
  private int itemId;

  public PickUpItem(int playerId, int itemId) {
    super(playerId);
    this.itemId = itemId;
  }

  @Override
  public String execute(GameModel model) {
    model.pickUpitem(playerId, itemId);
    return "Pick up successfully.\n";

  }

}
