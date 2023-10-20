package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.GameModel;

/**
 * The implementation of controller using command pattern.
 * 
 */
public class CommandController implements GameController {

  private GameModel model;
  private final Appendable out;
  private final Scanner scan;
  private int MAX_TURN;
  private int currentTurn;
  private Readable worldData;

  // Build a controller, so the in, out, and MaxTurn is set.
  public CommandController(Readable in, Appendable out, Readable worldSource, int turnLimit) {
    if (in == null || out == null || worldSource == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    if (turnLimit <= 0) {
      throw new IllegalArgumentException("Max turn must be positive");
    }
    this.out = out;
    scan = new Scanner(in);

    this.worldData = worldSource;

    MAX_TURN = turnLimit;
    currentTurn = 0;
  }

  @Override
  public void start(GameModel model) {
    this.model = model;
    try {
      System.out.print("Initializing the world map...");
      model.setupNewWorld(worldData);
      out.append("finished\n");
      displayMap();

      out.append("Welcome to the world of ").append(model.getName()).append("\n");
      displayGameInfo();

      out.append("Set at least one player before the game begins:").append("\n");

      // TODO: Add Player once, Player should have unique ID.
      String line;
      while (model.getPlayerCount() == 0) {
        int playerId = model.getPlayerCount();

        // Looping until a non-empty name is provided.
        String playerName = "";
        while (playerName.isEmpty()) {
          out.append(String.format("Enter name for player %d:", playerId)).append("\n");
          line = scan.nextLine();
          playerName = line.trim();
          if (playerName.isEmpty()) {
            out.append("Name can not be blank, try again.");
          }
        }

        int location = 0;
        while (true) {
          int maxRoomIndex = model.getRoomCount() - 1;
          out.append(String.format(
              "Set location for player %d, enter number between %d and %d, or press enter to set default 0",
              playerId, 0, maxRoomIndex)).append("\n");
          line = scan.nextLine().trim();
          if (line.isEmpty()) {
            break;
          }
          try {
            location = Integer.parseInt(line);
            if (location < 0 || location > maxRoomIndex) {
              out.append(String.format("Number %d is not between %d and %d, try again.\n", location,
                  0, maxRoomIndex));
              continue;
            }
          } catch (NumberFormatException e) {
            out.append("Invalid input, try again.\n");
            scan.nextLine();
          }

        }
        //TODO continue get capacity and computer type.

      }

      out.append("[Game Info]\n");

      // TODO add Player

      // TODO build a menu to show the world and set player

//    try {
//      String element = scan.next();
//      out.append("Hello world, " + element);
//    } catch (IOException ioe) {
//      throw new IllegalStateException("Append failed", ioe);
//    }
//        
//    while(currentTurn<MAX_TURN) {
//      FFFmakeTurns();
//    }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  // TODO other method for information display

  private void addPlayer(String name) {
    // Already exist player with same name.
    if (model.getPlayerTurn(name) != -1) {
      return;
    }
  }

  private void displayMap() {
    BufferedImage image = model.drawWorld();
    JFrame frame = new JFrame();
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

  }

  private void displayGameInfo() throws IOException {
    out.append(model.getDetails()).append("\n");
  }

  private void FFFmakeTurns() {
    // TODO
  }

}
