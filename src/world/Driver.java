package world;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Drivers to display how the world looks like.
 */
public class Driver {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("Need to input source");
      System.out.println("Usage: javac - jar Driver.jar -f [filename] to read from file");
      System.out.println("or: javac - jar Driver.jar -s [worlddata] to read from string");
      return;
    }

    World myWorld = new World();
    if (args[0].equals("-f")) {
      try {
        FileReader fileReader = new FileReader(args[1]);
        myWorld.setupNewWorld(fileReader);
      } catch (IOException e) {
        System.out.println(e);
        return;
      }
    }
    if (args[0].equals("-s")) {
      String multiLineString = args[1].replace("^n", "\n");
      StringReader stringReader = new StringReader(multiLineString);
      myWorld.setupNewWorld(stringReader);
    }

    // print something to show the world works.
    // show the world
    System.out.print("Initializing the world ...");
    System.out.println("finished");
    System.out.println("[Word Info]");
    System.out.println(myWorld);
    System.out.println();

    // show the target and its move
    System.out.println("[Target Info]");
    System.out.println(myWorld.getTarget());
    for (int i = 0; i <= myWorld.getWorldSpace().size(); i++) {
      myWorld.moveTargetNextRoom();
      if (i == 0 || i == 1 || i == 18 || i == 19 || i == 21) {
        System.out.println(String.format("After %d move, %s", i + 1, myWorld.getTarget()));
      }
    }
    System.out.println();

    // show the room
    System.out.println("[Room Info]");
    System.out.println("--room list--");
    System.out.println(myWorld.getWorldSpace());
    // show certain room
    myWorld.printRoomInfo(0);
    myWorld.printRoomInfo(1);
    myWorld.printRoomInfo(2);
    myWorld.printRoomInfo(3);
    System.out.println();

    // show the item
    System.out.println("[Item Info]");
    myWorld.printItemInfo();
    
    // Set scaling = 20, left-padding = 5, top-padding
    BufferedImage image = myWorld.drawWorld(20, 5, 5);

    try {
      ImageIO.write(image, "png", new File("WorldMap.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    JFrame frame = new JFrame();
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }


}
