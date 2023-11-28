package view;

import java.io.InputStreamReader;
import controller.GameController;

/**
 * The text-based view.
 */
public class ConsoleView implements GameView {

  private Readable input;
  private Appendable output;

  public ConsoleView(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
  }

  @Override
  public void configureView(GameController controller) {
    // TODO Auto-generated method stub

  }

  @Override
  public void display() {
    // TODO Auto-generated method stub

  }

  @Override
  public void refresh() {
    // TODO Auto-generated method stub

  }

}
