package world;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Driver {

  public static void main(String[] args) {
    World myWorld = World.getWorldInstance();
    
    
    if (args.length != 1) {
      return;
    }
    FileReader fileReader;
    try {
      // the args[0] is "./res/map.txt"
      fileReader = new FileReader(args[0] + "");
      myWorld.setupWorld(fileReader);
    } catch (Exception e) {
      System.out.println(e);
      return;
    }
    
    // another way to setup the world
    StringReader stringReader = new StringReader(getLonString());
    myWorld.setupWorld(stringReader);

    System.out.println(String.format("Welcome to the world of %s", myWorld.getWorldName()));
    System.out.println(String.format("Target character of this world is %s, with initail helth %d.",
        myWorld.targetCharacter.getName(), myWorld.targetCharacter.getHealth()));
    System.out.println();

    BufferedImage image = drawWorld(myWorld, 20, 5, 5);

    try {
      ImageIO.write(image, "png", new File("rectangle.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    JFrame frame = new JFrame();
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setVisible(true);

  }
  
  
  public static BufferedImage drawWorld(World world, int scale, int leftPadding, int topPadding) {
    int width = world.getWorldSize()[1];
    int height = world.getWorldSize()[0];

    int graphWidth = width + leftPadding * 2;
    int graphHeight = height + topPadding * 2;
    BufferedImage image = new BufferedImage(graphWidth * scale, graphHeight * scale,
        BufferedImage.TYPE_INT_ARGB);

    Graphics graph = image.getGraphics();
    graph.setColor(Color.WHITE);
    graph.fillRect(0, 0, graphWidth * scale, graphHeight * scale);

    graph.setColor(Color.BLACK);
    Font font = new Font("SansSerif", Font.BOLD, 12); // Font name, style, size
    graph.setFont(font);

    graph.drawRect(leftPadding * scale, topPadding * scale, width * scale, height * scale);

    ArrayList<RoomSpace> worldSpace = world.getWorldSpace();
    for (RoomSpace room : worldSpace) {
      int x1 = room.getRoomRect()[1] + leftPadding, y1 = room.getRoomRect()[0] + topPadding,
          x2 = room.getRoomRect()[3] + leftPadding, y2 = room.getRoomRect()[2] + topPadding;
      int rectX = x1 * scale, rectY = y1 * scale, rectWidth = (x2 - x1 + 1) * scale,
          rectHeight = (y2 - y1 + 1) * scale;
      graph.drawRect(rectX, rectY, rectWidth, rectHeight);
      // set the font to the middle
      FontMetrics fontMetrics = graph.getFontMetrics();
      int textWidth = fontMetrics.stringWidth(room.getSpaceName());
      int textHeight = fontMetrics.getHeight();
      int textX = rectX + 5; // + (rectWidth - textWidth) / 2;
      int textY = rectY + (rectHeight - textHeight) / 2 + fontMetrics.getAscent();
      // graph.setColor(Color.WHITE);
      graph.drawString(room.getSpaceName(), textX, textY);
    }

    // At last, draw the world name on top

    Font fontTitle = new Font("SansSerif", Font.ITALIC, 40); // Font name, style, size
    graph.setFont(fontTitle);
    String worldName = world.getWorldName();
    FontMetrics fontMetrics = graph.getFontMetrics();
    int textWidth = fontMetrics.stringWidth(worldName);
    int textHeight = fontMetrics.getHeight();
    int textX = (graphWidth * scale - textWidth) / 2;
    int textY = textHeight;
    graph.drawString(worldName, textX, topPadding * scale - fontMetrics.getDescent());
    System.out.println(String.format("name = %s,text width = %d, textHeight = %d, X= %d, Y =%d",
        worldName, textWidth, textHeight, textY, fontMetrics.getAscent()));

    graph.dispose();
    return image;
  }

  
  
  private static String getLonString()
  {
    String worldString ="36 30 Doctor Lucky's Mansion\n"
        +"50 Doctor Lucky\n"
        +"21\n"
        +"22 19 23 26 Armory\n"
        +"16 21 21 28 Billiard Room\n"
        +"28 0 35 5 Carriage House\n"
        +"12 11 21 20 Dining Hall\n"
        +"22 13 25 18 Drawing Room\n"
        +"26 13 27 18 Foyer\n"
        +"28 26 35 29 Green House\n"
        +"30 20 35 25 Hedge Maze\n"
        +"16 3 21 10 Kitchen\n"
        +"0 3 5 8 Lancaster Room\n"
        +"4 23 9 28 Library\n"
        +"2 9 7 14 Lilac Room\n"
        +"2 15 7 22 Master Suite\n"
        +"0 23 3 28 Nursery\n"
        +"10 5 15 10 Parlor\n"
        +"28 12 35 19 Piazza\n"
        +"6 3 9 8 Servants' Quarters\n"
        +"8 11 11 20 Tennessee Room\n"
        +"10 21 15 26 Trophy Room\n"
        +"22 5 23 12 Wine Cellar\n"
        +"30 6 35 11 Winter Garden\n"
        +"20\n"
        +"8 3 Crepe Pan\n"
        +"4 2 Letter Opener\n"
        +"12 2 Shoe Horn\n"
        +"8 3 Sharp Knife\n"
        +"0 3 Revolver\n"
        +"15 3 Civil War Cannon\n"
        +"2 4 Chain Saw\n"
        +"16 2 Broom Stick\n"
        +"1 2 Billiard Cue\n"
        +"19 2 Rat Poison\n"
        +"6 2 Trowel\n"
        +"2 4 Big Red Hammer\n"
        +"6 2 Pinking Shears\n"
        +"18 3 Duck Decoy\n"
        +"13 2 Bad Cream\n"
        +"18 2 Monkey Hand\n"
        +"11 2 Tight Hat\n"
        +"19 2 Piece of Rope\n"
        +"9 3 Silken Cord\n"
        +"7 2 Loud Noise";
    return worldString;
  }
}
