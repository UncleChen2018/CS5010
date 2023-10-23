package controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import model.GameModel;
import model.World;

/**
 * Another driver with variable arguments.
 */
public class NewDriverVarargs {

  /**
   * The driver's entry.
   * 
   * @param args -f filename to read from files. -s string to read from string.
   *             And a predefined integer array.
   */

  public static void main(String[] args) {
    //
    if (args.length != 3) {
      System.out.println("Need to input source");
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
      int random[] = { 90, 220, 16, 167, 12, 95, 41, 198, 101, 65, 93, 39, 185, 85, 15, 118, 56, 61,
          239, 197, 178, 36, 80, 56, 177, 92, 142, 246, 138, 221, 150, 188, 194, 17, 10, 177, 23,
          91, 82, 10, 113, 180, 117, 64, 18, 220, 159, 192, 225, 204, 43, 172, 112, 244, 48, 159, 2,
          250, 135, 122, 244, 154, 191, 143, 149, 46, 95, 25, 239, 124, 98, 170, 82, 80, 235, 74,
          190, 189, 115, 207, 41, 77, 19, 6, 90, 136, 103, 151, 153, 42, 163, 21, 18, 167, 248, 212,
          230, 49, 226, 194, 159, 112, 179, 156, 149, 115, 185, 21, 86, 158, 145, 198, 66, 174, 90,
          64, 168, 56, 66, 245, 49, 153, 157, 217, 5, 245, 79, 196, 113, 120, 193, 67, 175, 9, 149,
          131, 66, 109, 85, 63, 3, 61, 42, 177, 105, 173, 194, 150, 77, 72, 245, 200, 208, 6, 86,
          34, 52, 179, 169, 135, 235, 225, 128, 29, 18, 222, 3, 54, 70, 17, 47, 98, 220, 157, 12,
          92, 148, 62, 11, 6, 85, 249, 104, 12, 222, 145, 190, 170, 176, 193, 147, 165, 62, 111,
          148, 33, 250, 118, 142, 227, 167, 63, 118, 106, 18, 180, 124, 92, 9, 233, 221, 128, 226,
          38, 167, 21, 83, 78, 188, 138, 108, 177, 35, 139, 45, 39, 178, 110, 174, 194, 57, 53, 95,
          76, 135, 131, 226, 81, 196, 28, 138, 165, 120, 151, 73, 101, 45, 187, 253, 221, 70, 15,
          181, 167, 220, 101, 44, 152, 98, 33, 234, 135, 232, 70, 80, 225, 157, 80, 79, 110, 7, 68,
          97, 225, 36, 67, 93, 32, 132, 215, 122, 172, 134, 65, 120, 220, 41, 186, 146, 221, 118,
          91, 77, 189, 232, 134, 151, 42, 18, 27, 18, 22, 107, 119, 2, 169, 63, 40, 106, 185, 32,
          208, 13, 93, 178, 200, 142, 245, 92, 104, 107, 107, 92, 175, 222, 208, 157, 194, 71, 33,
          106, 194, 46, 178, 234, 158, 180, 78, 139, 16, 66, 234, 39, 64, 185, 160, 187, 239, 159,
          46, 195, 163, 95, 146, 61, 21, 4, 43, 36, 109, 230, 201, 44, 187, 162, 157, 23, 36, 2,
          232, 78, 212, 122, 14, 243, 234, 177, 8, 161, 185, 1, 85, 185, 221, 201, 194, 59, 179,
          131, 101, 101, 4, 141, 198, 82, 134, 207, 181, 174, 166, 139, 5, 131, 171, 174, 102, 24,
          128, 184, 55, 186, 22, 99, 152, 86, 84, 100, 232, 188, 69, 144, 9, 207, 109, 154, 180,
          226, 79, 175, 62, 145, 86, 81, 5, 30, 167, 189, 194, 29, 119, 62, 80, 129, 46, 34, 99,
          102, 60, 66, 6, 55, 52, 128, 69, 185, 50, 113, 53, 216, 126, 59, 3, 2, 181, 50, 226, 152,
          122, 139, 93, 8, 109, 45, 80, 121, 147, 146, 60, 109, 7, 85, 233, 41, 20, 237, 105, 83,
          63, 216, 197, 54, 85, 220, 234, 1, 81, 85, 117, 96, 26, 119, 37, 118 };
      GameController controller = new CommandController(input, output, worldDataSource, maxTurn,
          random);
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
