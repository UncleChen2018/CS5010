package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public class PickUpItem extends TurnBaseCommand {
  private int playerId;

  public PickUpItem(int playerId) {
    super(playerId);
  }

  @Override
  public void execute(GameModel model, Scanner scan, Appendable out) throws IOException, IllegalStateException {
    if(model.playerReachCapacity(playerId)) {
      throw new IllegalStateException("Item capacity reached, choose other option.");
    }
    int curLocation = model.getPlayerLocation(playerId);
    if(model.getRoomItemCount(curLocation)==0) {
      throw new IllegalStateException("Room has not item, choose other option.");
    }
    while (true) {      
      out.append(model.queryRoomItem(curLocation));
      out.append("Enter the item  you want to pick up\n");
      String line = scan.nextLine().trim();
      try {
        int itemId = Integer.parseInt(line);
        int playerLocation = model.getPlayerLocation(playerId);
        if (model.getItemLocation(itemId) == playerLocation) {
          model.pickUpitem(playerId, itemId);
          out.append("Pick up successfully.\n");
          turnEnd();
          break;
        } else {
          out.append("No such item in this room, pick up failed,\n");
        }

      } catch (NumberFormatException e) {
        out.append("Wrong format for an integer, try gain.\n");
      } catch (IndexOutOfBoundsException e) {
        out.append(e.getMessage()).append("\n");
      }
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
