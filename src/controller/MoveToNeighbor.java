package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public class MoveToNeighbor extends TurnBaseCommand {

  private int playerId;
  private int destinationIndex;
  public MoveToNeighbor(int playerId, int roomIndex, Scanner scan, Appendable out) {
    super(scan, out);
    this.playerId = playerId;
    this.destinationIndex = roomIndex;
  }


  @Override
  public void execute(GameModel model) throws IllegalArgumentException {
    int playerLocation = model.getPlayerLocation(playerId);
    //Must check the roomIndex is legal such the destination is in the player's neighbor.
    if(model.isNeighbor(destinationIndex, playerLocation)) {
        model.setPlayerLocation(playerId, destinationIndex);
    }
    else {
      throw new IllegalArgumentException("Not a valid neighbor, move failed");
    }

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
