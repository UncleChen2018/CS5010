package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
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
  private BufferedImage image;
  private JFrame frame;

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
      // The initializing phase before game.
      out.append("Initializing the world map and drawing...");
      model.setupNewWorld(worldData);
      image = model.drawWorld();
      out.append("finished\n");
      displayGameInfo();

      // show the map and ask for save.
      String line;
      displayMap();
      out.append("Save the map to png file?(y|n), default n").append("\n");
      line = scan.nextLine().trim();
      if (line.equalsIgnoreCase("y") || line.equalsIgnoreCase("yes")) {
        saveMap();
      }
      // add at least one player before game begin
      line = "y";
      while (line.equalsIgnoreCase("y") || model.getPlayerCount() < 1) {
        if (model.getPlayerCount() < 1) {
          out.append("Set at least one player before the game begins:").append("\n");
        }
        addPlayer();
        out.append(String.format("Totally %d player, add more?(y|n)", model.getPlayerCount()));
        line = scan.nextLine().trim();
      }
      out.append("Settting finished, game started.\n");

      while (currentTurn < MAX_TURN) {
        out.append(String.format("[TURN %d]\n", currentTurn + 1));

        int activePlayer = model.getCurrentPlayer(currentTurn);
        if (model.isHumanPlayer(activePlayer)) {
          int location = model.getPlayerLocation(activePlayer);
          SimpleCommand cmd = null;
          displayGameMenu(activePlayer);
          switch (line = scan.nextLine().trim()) {
            case "1":
              while (true) {
                int playerLocation = model.getPlayerLocation(activePlayer);
                out.append(model.queryRoomNeighbors(playerLocation));
                out.append("Enter the room index to move to\n");
                line = scan.nextLine().trim();
                try {
                  int destLocation = Integer.parseInt(line);

                  if (model.isNeighbor(destLocation, playerLocation)) {
                    cmd = new MoveToNeighbor(activePlayer, destLocation);

                    break;
                  } else {
                    out.append("Not a valid neighbor, move failed,\n");
                  }

                } catch (NumberFormatException e) {
                  out.append("Wrong format for an integer, try gain.\n");
                } catch (IndexOutOfBoundsException e) {
                  out.append("Room index not valid, try gain.\n").append("\n");
                }
              }
              break;
            case "2":
              if (model.playerReachCapacity(activePlayer)) {
                out.append("Item capacity reached, choose other option.").append("\n");

              }
              int curLocation = model.getPlayerLocation(activePlayer);
              if (model.getRoomItemCount(curLocation) == 0) {
                out.append("Room has not item, choose other option.\n");
                break;
              }
              while (true) {
                out.append(model.queryRoomItem(curLocation));
                out.append("Enter the item  you want to pick up\n");
                line = scan.nextLine().trim();
                try {
                  int itemId = Integer.parseInt(line);
                  int playerLocation = model.getPlayerLocation(activePlayer);
                  if (model.getItemLocation(itemId) == playerLocation) {
                    cmd = new PickUpItem(activePlayer, itemId);
                    break;
                  } else {
                    out.append("No such item in this room, pick up failed,\n");
                  }

                } catch (NumberFormatException e) {
                  out.append("Wrong format for an integer, try gain.\n");
                } catch (IndexOutOfBoundsException e) {
                  out.append(e.getMessage()).append("\n");
                }
              }
              break;
            case "3":
              cmd = new LookAround(activePlayer);
              break;
            case "4":
              displayPlayerInfo(activePlayer);
              break;
            case "5":
              displayRoomInfo(location);
              break;
            case "6":
              displayTargetInfo();
              break;
            case "7":
              displayItemInfo(0);
              break;
            default:
              out.append("Invalid choice, try again").append("\n");
              break;
          }
          if (cmd != null) {
            try {
              // TODO: add more display info for each command after execution.
              out.append(cmd.execute(model));
              model.moveTargetNextRoom();
              currentTurn += 1;
            } catch (IllegalArgumentException | IllegalStateException e) {
              out.append(e.getMessage()).append("\n");
            }
            cmd = null;
          }
        }
        else {
          // TODO: computer random choice
        }
      }

      frame.dispose();

    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }

  }

  // help method, display information for certain player.
  private void displayGameMenu(int playerId) throws IOException {
    out.append(String.format("Player %d's turn, please select one of the option below", playerId))
        .append("\n");
    out.append("-------------------GAME MENU-------------------").append("\n");
    out.append("1. Move to neighbor space.(Cost one turn)\n");
    out.append("2. Pick up items from this space.(Cost one turn)\n");
    out.append("3. Look around.(Cost one turn)\n");
    out.append("4. Display player info.\n");
    out.append("5. Display current room.\n");
    out.append("6. Display target info.\n");
    out.append("7. Display item info.\n");
    out.append("-------------------MENU END-------------------").append("\n");
  }

  private void displayMap() {
    frame = new JFrame();
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  private void saveMap() throws IOException {
    out.append(
        "Enter the name of the file (not include .png), other wise will save to WorldMap.png as default)")
        .append("\n");
    String fileString = "WorldMap.png";
    String line = scan.nextLine().trim();
    if (line.isEmpty()) {
      ImageIO.write(image, "png", new File(fileString));
    } else {
      fileString = line + ".png";
      ImageIO.write(image, "png", new File(fileString));
    }
    out.append(String.format("%s succssfully saved", fileString)).append("\n");
  }

  // Add player.
  private void addPlayer() throws IOException {
    String line;
    int playerId = model.getPlayerCount();
    // Looping until a non-empty name is provided.
    String playerName = "";
    while (playerName.isEmpty()) {
      out.append(String.format("Enter name for player %d:", playerId)).append("\n");
      line = scan.nextLine();
      playerName = line.trim();
      if (playerName.isEmpty()) {
        out.append("Name can not be blank, try again.\n");
      }
    }
    int defaultLocation = 0;
    int location = defaultLocation;
    while (true) {
      int maxRoomIndex = model.getRoomCount() - 1;
      out.append(String.format(
          "Set location for player %d, enter number between %d and %d, or press enter to set default value %d",
          playerId, 0, maxRoomIndex, defaultLocation)).append("\n");
      line = scan.nextLine().trim();
      // line = line.trim();
      if (line.isEmpty()) {
        break;
      }
      try {
        location = Integer.parseInt(line);
        if (location < 0 || location > maxRoomIndex) {
          out.append(String.format("Number %d is not between %d and %d, try again.\n", location, 0,
              maxRoomIndex));
          continue;
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        out.append("Invalid input, try again.\n");
      }
    }
    int defaultCapacity = 2;
    int capacity = defaultCapacity;
    while (true) {
      out.append(String.format(
          "Set item capacity for player %d, enter number greater than %d, or press enter to set default value %d",
          playerId, 0, defaultCapacity)).append("\n");
      line = scan.nextLine().trim();
      if (line.isEmpty()) {
        break;
      }
      try {
        capacity = Integer.parseInt(line);
        if (capacity <= 0) {
          out.append(String.format("Number %d is no greater than %d, try again.\n", capacity, 0));
          continue;
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        out.append("Invalid input, try again.\n");
      }
    }
    // set control type
    boolean isHumanControl = true;
    out.append(String.format("Set control for player %d to Computer? (y|n, default no)", playerId))
        .append("\n");
    line = scan.nextLine().trim();
    if (line.equalsIgnoreCase("y") || line.equalsIgnoreCase("yes")) {
      isHumanControl = false;
    }

    // Last check
    out.append(String.format(
        "What you will add: Player %d, with name %s, initial location at room %d, item capacity = %d, controlled by %s",
        playerId, playerName, location, capacity, isHumanControl ? "HUMAN" : "COMPUTER"))
        .append("\n");
    out.append("Add this player to game(y) or abort(n), default y").append("\n");
    line = scan.nextLine().trim();
    if (line.isEmpty() || line.equalsIgnoreCase("y") || line.equalsIgnoreCase("yes")) {
      model.addNewPlayer(playerName, location, capacity, isHumanControl);
      out.append(String.format("Player %d successfully added.\n", playerId));
    } else {
      out.append("Player adding cancelled.\n");
    }
  }

  private void displayGameInfo() throws IOException {
    out.append(model.getDetails()).append("\n");
  }

  private void displayPlayerInfo(int playerId) throws IOException {
    out.append(model.queryPlayerDetails(playerId)).append("\n");
  }

  private void displayRoomInfo(int roomId) throws IOException {
    out.append(model.queryRoomDetails(roomId)).append("\n");
  }

  private void displayTargetInfo() throws IOException {
    out.append(model.queryTargetDetails()).append("\n");
  }

  private void displayItemInfo(int itemId) throws IOException {
    out.append(model.queryItemDetails(itemId)).append("\n");
  }

}
