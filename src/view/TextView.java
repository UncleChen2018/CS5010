package view;

import java.io.InputStreamReader;
import controller.GameController;
import controller.GameControllerNew;

/**
 * The text-based view.
 */
/**
 * 
 */
public class TextView implements GameView {

  private Readable input;
  private Appendable output;

  public TextView(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
  }

  @Override
  public void configureView(GameControllerNew controller) {

  }

  @Override
  public void display() {
  }

  @Override
  public void refresh() {
  }

  @Override
  public Readable getInputSource() {
    // TODO Auto-generated method stub
    return this.input;
  }

  @Override
  public Appendable getOutputDestination() {
    // TODO Auto-generated method stub
    return this.output;
  }
  
  @Override
  public boolean requiresGuiOutput() {
    return false;
  }

  
  
  @Override
  public void showWelcomeMessage() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void showFarewellMessage() {
    // TODO Auto-generated method stub 
  }

  @Override
  public void drawMap(GameControllerNew controller) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void displayAddPlayer(GameControllerNew controller) {
    // TODO Auto-generated method stub
    
  }



  @Override
  public void updateStatusLabel() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void disPlaySetGameMaxTurn(GameControllerNew controller) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void rest() {
    // TODO Auto-generated method stub
    
  }
  
  
  
  
  
  
  
  
  

}
