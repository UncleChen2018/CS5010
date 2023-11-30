package view;

import controller.GameController;
import controller.GameControllerNew;

/**
 * The game view interface, which contain the view's method for the controller
 * to use.
 */
public interface GameView {
  // use the controller to configure the view so it can work properly.

  //Method to get the input source of the view (e.g., InputStream or Scanner)
  Readable getInputSource();

  //Method to get the output destination of the view (e.g., PrintStream or Appendable)
  Appendable getOutputDestination();

  void configureView(GameControllerNew controller);

  void display();

  void refresh();
  
  boolean requiresGuiOutput();

  
}