package view;

import controller.GameController;

/**
 * The game view interface, which contain the view's method for the controller
 * to use.
 */
public interface GameView {
  // use the controller to configure the view so it can work properly. 
  void configureView(GameController controller);

  void display();

  void refresh();

}
