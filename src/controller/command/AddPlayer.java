/**
 * 
 */
package controller.command;

import controller.SimpleCommand;
import model.GameModel;

/**
 * 
 */
public class AddPlayer implements SimpleCommand {
  private int name;
  private int initialLoacation;

  
  @Override
  public void execute(GameModel model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    
  }
  
}
