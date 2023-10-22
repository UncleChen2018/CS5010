package controller;

import model.GameModel;

public class MoveToNeighbor extends TurnBaseCommand {

  private int location;

  public MoveToNeighbor(int playerId, int location) {
    super(playerId);
    this.location = location;
  }

  @Override
  public String execute(GameModel model) throws IllegalArgumentException {
    model.setPlayerLocation(playerId, location);
    return "Move to neighbor successfully.\n";

  }

}
