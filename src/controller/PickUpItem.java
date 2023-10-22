package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public class PickUpItem extends TurnBaseCommand {
  private int itemId;

  public PickUpItem(int playerId, int itemId) {
    super(playerId);
    this.itemId = itemId;
  }

  @Override
  public String execute(GameModel model) throws IOException, IllegalStateException {
    model.pickUpitem(playerId, itemId);
    return "Pick up successfully.\n";   

  }

  @Override
  protected void turnBegin() {
    // TODO Auto-generated method stub

  }

  @Override
  protected void turnEnd() {
    // TODO Auto-generated method stub

  }

}
