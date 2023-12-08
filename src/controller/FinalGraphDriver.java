package controller;

import model.GameModel;
import model.World;
import view.GameView;
import view.GraphView;

/**
 * The driver for graphic game.
 */
public class FinalGraphDriver {
  /**
   * The main method.
   * @param args the arguments
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      GameModel model = new World();
      GameView graphView = new GraphView(model);
      GameControllerNew controllerNew = new CommandControllerNew(model, graphView);
      // GameController controller = new CommandController(input, output,
      // worldDataSource, maxTurn);
      controllerNew.executeGame();
    }

  }

}
