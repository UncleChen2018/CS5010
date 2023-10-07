package world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Drivers to display how the world looks like.
 */
public class Driver {

  /**
   * The driver's entry.
   * 
   * @param args -f filename to read from files. -s string to read from string.
   */
  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Need to input source");
      System.out.println("Usage: java - jar Driver.jar -f [filename] to read from file");
      System.out.println("or: java - jar Driver.jar -s [worlddata] to read from string");
      return;
    }

    World myWorld = new World();
    if (args[0].equals("-f")) {
      try {
        FileReader fileReader = new FileReader(args[1]);
        System.out.print("Initializing the world ...");
        myWorld.setupNewWorld(fileReader);
        System.out.println("finished");
      } catch (IOException e) {
        System.out.println(e);
        return;
      }
    }
    if (args[0].equals("-s")) {
      String multiLineString = args[1].replace("^n", "\n");
      StringReader stringReader = new StringReader(multiLineString);
      System.out.print("Initializing the world ...");
      myWorld.setupNewWorld(stringReader);
      System.out.println("finished");
    }

    // print something to show the world works.
    // show the world

    BufferedImage image = myWorld.drawWorld(20, 5, 5);

    JFrame frame = new JFrame();
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    System.out
        .println("Save the map to png file? Press Enter to continue, any other key to pass... ");
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();

    if (userInput.isEmpty()) {
      try {
        System.out.println("Enter the name of the file (not include .png). "
            + "Press Enter will save to WorldMap.png as default");
        String fileString = "WorldMap.png";
        userInput = scanner.nextLine();
        if (userInput.isEmpty()) {
          ImageIO.write(image, "png", new File(fileString));
        } else {
          fileString = userInput + ".png";
          ImageIO.write(image, "png", new File(fileString));
        }
        System.out.println(fileString + " succssfully saved");
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Saving failed. Try to restart the program. Enter any key to quit...");
        scanner.nextLine();
        scanner.close();
        frame.dispose();
        return;
      }
    }

    int roomNumber = myWorld.getWorldSpace().size() - 1;
    int itemNumer = myWorld.getItems().size() - 1;
    StringBuilder sb = new StringBuilder();
    sb.append("Enter number to display information\n").append("1. Display the world info\n")
        .append("2. Move target character random moves\n")
        .append("3. Display random room info (0 to ").append(roomNumber).append(")\n")
        .append("4. Display random item info (0 to ").append(itemNumer).append(")\n")
        .append("5. Exit program");

    while (true) {
      System.out.println(sb.toString());
      userInput = scanner.nextLine();
      if (userInput.isEmpty()) {
        continue;
      }

      if ("1".equals(userInput)) {
        System.out.println("[Word Info]");
        System.out.println(myWorld);
      }
      if ("2".equals(userInput)) {
        while (true) {
          System.out.println("Enter non negative moves, other input to go back:");
          userInput = scanner.nextLine();
          try {
            int moves = Integer.parseInt(userInput);
            if (moves < 0) {
              System.out.println("Input out of range, return to rop menu.");
              break;
            }
            System.out.println("[Target Info]");
            System.out.println(myWorld.getTarget());
            for (int i = 0; i < moves; i++) {
              myWorld.moveTargetNextRoom();
            }
            System.out.println(String.format("After %d move, %s", moves, myWorld.getTarget()));
          } catch (NumberFormatException e) {
            System.out.println("Invalid input, return to top menu.");
            break;
          }
        }
      }

      if ("3".equals(userInput)) {
        while (true) {
          System.out.println(String.format(
              "Enter room number between %d and %d, other input to go back:", 0, roomNumber));
          userInput = scanner.nextLine();
          try {
            int roomNo = Integer.parseInt(userInput);
            if (roomNo < 0 || roomNo > roomNumber) {
              System.out.println("Input out of range, return to rop menu.");
              break;
            }
            System.out.println("[Room Info]");
            myWorld.printRoomInfo(roomNo);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input, return to top menu");
            break;
          }
        }
      }
      if ("4".equals(userInput)) {
        while (true) {
          System.out.println(String.format(
              "Enter item number between %d and %d, other input to go back:", 0, itemNumer));
          userInput = scanner.nextLine();
          try {
            int itemNo = Integer.parseInt(userInput);
            if (itemNo < 0 || itemNo > itemNumer) {
              System.out.println("Input out of range, return to rop menu.");
              break;
            }
            System.out.println("[Item Info]");
            myWorld.printItemInfo(itemNo);
          } catch (NumberFormatException e) {
            System.out.println("Invalid input, return to top menu.");
            break;
          }
        }
      }

      if ("5".equals(userInput)) {
        System.out.println("Hope you have fun in this world, any key to exit...");
        System.out.println("Remember to close the map window to return to command line...");
        break;
      }

      System.out.println("=====================================");
    }
    scanner.close();

  }

}
