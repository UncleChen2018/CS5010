package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public class MoveToNeighbor extends TurnBaseCommand {

  private int location;

  public MoveToNeighbor(int playerId, int location) {
    super(playerId);
    this.location = location;
  }

  @Override
  public String execute(GameModel model)
      throws IllegalArgumentException, IOException {
      model.setPlayerLocation(playerId, location);
      return "Move to neighbor successfully.\n";
  
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
