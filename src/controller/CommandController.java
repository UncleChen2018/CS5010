package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.GameModel;

/**
 * The implementation of the controller using the command pattern. This
 * controller manages the game flow and interactions with the user or computer
 * players.
 */
public class CommandController implements GameController {

  private GameModel model;
  private final Appendable out;
  private final Scanner scan;
  private int maxTurn;
  private int currentTurn;
  private Readable worldData;
  private BufferedImage image;
  private JFrame frame;
  private NumberGenerator generator;

  // Build a controller, so the in, out, and MaxTurn is set.
  // This is the default constructor, which use random to generate choice of
  // computer controlling.
  /**
   * Constructs a CommandController with the specified input, output, world
   * source, and turn limit.
   *
   * @param in          The input source for the controller.
   * @param out         The output destination for the controller.
   * @param worldSource The source of world data.
   * @param turnLimit   The maximum number of turns allowed.
   * @throws IllegalArgumentException if any of the parameters (in, out,
   *                                  worldSource) is null or if turnLimit is not
   *                                  positive.
   */

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

    maxTurn = turnLimit;
    currentTurn = 0;
    generator = new NumberGenerator();

  }

  /**
   * Constructs a CommandController with the specified input, output, world
   * source, turn limit, and custom number generator.
   *
   * @param in          The input source for the controller.
   * @param out         The output destination for the controller.
   * @param worldSource The source of world data.
   * @param turnLimit   The maximum number of turns allowed.
   * @param numbers     Custom numbers to be used by the generator.
   * @throws IllegalArgumentException if any of the parameters (in, out,
   *                                  worldSource) is null or if turnLimit is not
   *                                  positive.
   */

  public CommandController(Readable in, Appendable out, Readable worldSource, int turnLimit,
      int... numbers) {
    this(in, out, worldSource, turnLimit);
    generator = new NumberGenerator(numbers);

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
      if ("y".equalsIgnoreCase(line) || "yes".equalsIgnoreCase("line")) {
        saveMap();
      }
      // add at least one player before game begin
      line = "y";
      while ("y".equalsIgnoreCase(line) || model.getPlayerCount() < 1) {
        if (model.getPlayerCount() < 1) {
          out.append("Set at least one player before the game begins:").append("\n");
        }
        addPlayer();
        out.append(String.format("Totally %d player, add more?(y|n)", model.getPlayerCount()));
        line = scan.nextLine().trim();
      }
      out.append("Settting finished, game started.\n\n\n");

      // begin to take turn.
      while (currentTurn < maxTurn && !model.isGameOver()) {
        out.append(String.format("[TURN %d]\n", currentTurn + 1));
        int activePlayer = model.getCurrentPlayer(currentTurn);
        out.append(String.format("Player %s's turn", model.getPlayerString(activePlayer)))
            .append("\n");

        int location = model.getPlayerLocation(activePlayer);
        SimpleCommand cmd = null;

        // Through interaction, get human command.
        if (model.isHumanPlayer(activePlayer)) {
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
                    out.append("Not a valid neighbor, try gain.\n");
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
                break;
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
                    out.append(String.format("No such item %d in this room, try again.\n", itemId));
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
              cmd = new AttackTarget(activePlayer);
              break;
            case "5":
              while (true) {
                int playerLocation = model.getPlayerLocation(activePlayer);
                out.append(model.queryRoomNeighbors(playerLocation));
                displayTargetInfo();
                out.append(
                    String.format("Enter the room index to move the pet to (between %d and %d):\n",
                        0, model.getRoomCount() - 1));
                line = scan.nextLine().trim();
                try {
                  int destLocation = Integer.parseInt(line);
                  if (model.isLocationValid(destLocation)) {
                    cmd = new MovePet(activePlayer, destLocation);
                    break;
                  } else {
                    out.append("Not a valid room location, try gain.\n");
                  }

                } catch (NumberFormatException e) {
                  out.append("Wrong format for an integer, try gain.\n");
                } catch (IndexOutOfBoundsException e) {
                  out.append("Room index not valid, try gain.\n").append("\n");
                }
              }

              break;
            case "6":
              displayTargetInfo();
              displayRoomInfo(location);
              displayPlayerInfo(activePlayer);
              break;
            case "7":
              out.append("Player end game in the process, now quiting.\n");
              frame.dispose();
              return;

            default:
              out.append("Invalid choice, try again").append("\n");
              break;
          }
        } else {
          int nextInt = generator.getNextNumber();
          int curLocation = model.getPlayerLocation(activePlayer);
          // let computer choose which action to take
          int actionChoice = -1;
          // make computer smart, if not able to pickup, omit this option, not choose 1.
          if (model.playerReachCapacity(activePlayer) || model.getRoomItemCount(curLocation) == 0) {
            if (nextInt % 2 == 0) {
              actionChoice = 0;
            } else {
              actionChoice = 2;
            }
          } else {
            actionChoice = nextInt % 3;
          }

          switch (actionChoice) {
            case 0:
              nextInt = generator.getNextNumber();
              ArrayList<Integer> neighbors = model.getRoomNeighbors(curLocation);
              int destLocation = nextInt % neighbors.size();
              cmd = new MoveToNeighbor(activePlayer, neighbors.get(destLocation));
              break;
            case 1:
              nextInt = generator.getNextNumber();
              ArrayList<Integer> items = model.getRoomItems(curLocation);
              int pickId = nextInt % items.size();
              cmd = new PickUpItem(activePlayer, pickId);
              break;
            case 2:
              cmd = new LookAround(activePlayer);
              break;
            default:
              throw new IllegalStateException("Computer made invaild choice");
          }
        }
        // Execute command.
        if (cmd != null) {
          try {

            out.append(cmd.execute(model));
            out.append(model.queryPlayerDetails(activePlayer));
            model.moveTargetNextRoom();
            currentTurn += 1;
          } catch (IllegalArgumentException | IllegalStateException e) {
            out.append(e.getMessage()).append("\n");
          }
          out.append("~~~~~~~~~~~~~~~~~~~Turn End~~~~~~~~~~~~~~~~~~~\n");
          cmd = null;
        }
      }
      if (currentTurn == maxTurn) {
        out.append("Max turn reached, game exit\n");
      }
      frame.dispose();

    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }

  }

  // help method, display information for certain player.
  private void displayGameMenu(int playerId) throws IOException {
    out.append("Please select one of the option below\n");
    out.append("MENU|1.Move|2.Pickup|3.LookAround|4.Attack|5.MovePet|6.ChekcInfo|7.Exit")
        .append("\n");
  }

  private void displayMap() {
    frame = new JFrame();
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  private void saveMap() throws IOException {
    out.append("Enter the name of the file (not include .png), ")
        .append("other wise will save to WorldMap.png as default)").append("\n");
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
          "Set location for player %d, enter number between %d and %d, "
              + "or press enter to set default value %d",
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
        out.append(String
            .format("Invalid location input %s, must be valid integer." + " try again.\n", line));
      }
    }
    int defaultCapacity = 2;
    int capacity = defaultCapacity;
    while (true) {
      out.append(String.format("Set item capacity for player %d, enter number greater than %d,"
          + " or press enter to set default value %d", playerId, 0, defaultCapacity)).append("\n");
      line = scan.nextLine().trim();
      if (line.isEmpty()) {
        break;
      }
      try {
        capacity = Integer.parseInt(line);
        if (capacity <= 0) {
          out.append(String.format("Capacity %d is no greater than %d, try again.\n", capacity, 0));
          continue;
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        out.append(String
            .format("Invalid capacity input %s, must be valid integer." + " try again.\n", line));
      }
    }
    // set control type
    boolean isHumanControl = true;
    out.append(String.format("Set control for player %d to Computer? (y|n, default no)", playerId))
        .append("\n");
    line = scan.nextLine().trim();
    if ("y".equalsIgnoreCase(line) || "yes".equalsIgnoreCase(line)) {
      isHumanControl = false;
    }

    // Last check
    out.append(String.format(
        "What you will add: Player %d, with name %s, "
            + "initial location at room %d, item capacity = %d, controlled by %s",
        playerId, playerName, location, capacity, isHumanControl ? "HUMAN" : "COMPUTER"))
        .append("\n");
    out.append("Add this player to game(y) or abort(n), default y").append("\n");
    line = scan.nextLine().trim();
    if (line.isEmpty() || "y".equalsIgnoreCase(line) || "yes".equalsIgnoreCase(line)) {
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

  private class NumberGenerator {
    private ArrayList<Integer> numbers;
    private int currentIndex;
    private Random random;

    public NumberGenerator(int... numbers) {
      this.numbers = new ArrayList<>();
      for (int num : numbers) {
        this.numbers.add(num);
      }
      this.currentIndex = 0;
      this.random = null; // not use random
    }

    public NumberGenerator() {
      this.random = new Random();
      this.currentIndex = -1;
      this.numbers = null; // not use arrayList
    }

    public int getNextNumber() {
      if (numbers != null) {
        return numbers.get(currentIndex++ % numbers.size());
      } else {
        return random.nextInt(256);
      }
    }
  }

}
