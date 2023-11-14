package controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import model.GameModel;
import model.World;

/**
 * New program runner to receive argument and give control to controller.
 */
public class NewDriver {

  /**
   * The main function.
   * 
   * @param args readable way source and max turn.
   */
  public static void main(String[] args) {
    //
    if (args.length != 3) {
      System.out.println("Usage: java - jar Driver.jar -f filename MAXTURN to read from file");
      System.out.println("or: java - jar Driver.jar -s worlddata MAXTURN to read from string");
      return;
    }

    try {

      Readable worldDataSource = parseSource(args);
      int maxTurn = Integer.parseInt(args[2]);

      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;

      GameModel model = new World();
      GameController controller = new CommandController(input, output, worldDataSource, maxTurn);
      controller.start(model);
    } catch (IOException e) {
      System.out.println(e);
      return;
    }

    System.out.println("==================================================");
    System.out.println("New driver successfully run. Goodbye!.");

  }

  private static Readable parseSource(String[] args) throws IOException {
    if (args[0].equals("-f")) {
      return new FileReader(args[1]);
    } else if (args[0].equals("-s")) {
      String multiLineString = args[1].replace("^n", "\n");
      return new StringReader(multiLineString);
    }
    return null;
  }
}
