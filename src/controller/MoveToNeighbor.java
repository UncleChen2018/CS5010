package controller;

import java.io.IOException;
import java.util.Scanner;

import javax.xml.stream.events.EndDocument;

import model.GameModel;

public class MoveToNeighbor extends TurnBaseCommand {

  private int playerId;
  
  public MoveToNeighbor(int playerId, Scanner scan, Appendable out) {
    super(scan, out);
    this.playerId = playerId;
  }


  @Override
  public void execute(GameModel model) throws IllegalArgumentException, IOException {
   
    while(true)
    {
    out.append("Enter the room index to move to\n");
    String line = scan.nextLine().trim();
    try {
      int location = Integer.parseInt(line);
      int playerLocation = model.getPlayerLocation(playerId);
      if(model.isNeighbor(location, playerLocation)) {
          model.setPlayerLocation(playerId, location);
          out.append("Move to neighbor successfully.\n");
          turnEnd();
          break;
      }
      else {
        out.append("Not a valid neighbor, move failed,\n");
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
  
  private int scanUntilInt() throws IOException{
    int ret;
    
    while(true) {
      String line = scan.nextLine().trim();
      try {
        ret = Integer.parseInt(line);
        break;
      } catch (NumberFormatException e) {
        out.append("Wrong input for an integer, try gain.\n");
    }
  }
    return ret;
  }

}
