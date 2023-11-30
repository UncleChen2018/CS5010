package controller;

import model.GameModel;
import model.World;
import view.GameView;
import view.GraphView;

public class FinalGraphDriver {
  public static void main(String[] args) {
    if (args.length == 0) {
      GameModel model = new World();
      GameView graphView = new GraphView(model);
      GameControllerNew controllerNew = new CommandControllerNew(model, graphView);
      // GameController controller = new CommandController(input, output,
      // worldDataSource, maxTurn);
      controllerNew.executeGmae();
    }

  }

}
