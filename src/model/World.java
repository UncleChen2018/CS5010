package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The world contains all room, target character and items. It can create those
 * parts and initialize their status, as well as manipulate them later. It also
 * give representation of the world map in the buffered image for view models.
 */
public class World implements GameModel {
  private String worldName;
  private int rowSize;
  private int colSize;

  private TargetCharacter targetCharacter;
  private ArrayList<Room> roomList;
  private ArrayList<Item> itemList;
  private ArrayList<Player> playerList;

  private Pet pet;

  public World() {
  }

  // initialize the world using source.
  public World(Readable source) {
    setupNewWorld(source);
  }

  private void addItemToRoom(Item item, Room room) {
    room.addItem(item);
  }

  /**
   * Set up new world using data from a readable source.
   * 
   * @param source file or string match certain format.
   */
  public void setupNewWorld(Readable source) throws InputMismatchException {
    roomList = new ArrayList<Room>();
    itemList = new ArrayList<Item>();
    playerList = new ArrayList<Player>();

    Scanner scanner = new Scanner(source);

    // parse the World
    this.rowSize = scanner.nextInt();
    this.colSize = scanner.nextInt();
    this.worldName = scanner.nextLine().trim();

    // parse the target character
    int fullHealth = scanner.nextInt();
    String roleName = scanner.nextLine().trim();
    this.targetCharacter = new TargetCharacter(roleName, fullHealth);

    // parse the pet
    String petName = scanner.nextLine().trim();
    pet = new Pet(petName, 0);

    // parse the space number;
    int spaceNumber = scanner.nextInt();

    // Fill the space array list
    for (int i = 0; i < spaceNumber; i++) {
      int row1 = scanner.nextInt();
      int col1 = scanner.nextInt();
      int row2 = scanner.nextInt();
      int col2 = scanner.nextInt();
      String roomName = scanner.nextLine().trim();
      roomList.add(new RoomSpace(i, row1, col1, row2, col2, roomName));
    }

    // New: add target to room.
    roomList.get(targetCharacter.getLocation()).setTargetIn();

    // parse the item number and put into room
    int itemNumber = scanner.nextInt();
    for (int i = 0; i < itemNumber; i++) {
      int spaceIndex = scanner.nextInt();
      int damage = scanner.nextInt();
      String itemName = scanner.nextLine().trim();
      Weapon newItem = new Weapon(i, itemName, damage, spaceIndex);
      itemList.add(newItem);
      addItemToRoom(newItem, getRoomSpace(spaceIndex));
    }
    scanner.close();

    // fill the neighbors and visible room list
    for (int i = 0; i < roomList.size(); i++) {
      Room thisSpace = roomList.get(i);
      for (int j = 0; j < roomList.size(); j++) {
        if (i == j) {
          continue;
        }
        Room otherSpace = roomList.get(j);
        if (isNeighborRect(thisSpace, otherSpace)) {
          thisSpace.getNeighbors().add(otherSpace);
        }
        if (isVisible(thisSpace, otherSpace)) {
          thisSpace.getVisibles().add(otherSpace);
        }
      }
    }
  }

  // test if [beg1,end1] and [beg2, end2] has over lap
  private static boolean isOverlap(int beg1, int end1, int beg2, int end2) {
    // test if either beg2 or end2 fall out of range
    if (beg2 >= end1 || end2 <= beg1) {
      return false;
    } else {
      return true;
    }
  }

  private static boolean isNeighborRect(Room thisSpace, Room otherSpace) {
    boolean result = false;
    // if two room share the same wall,then they are neighbor
    // every room has 4 wall, top: (x1, y1--> y2); right: (x1->x2, y2); bottom:
    // (x2,y1->y2); left: (x1->x2, y1)
    // overlap can happen, 1) top-bottom: x1a = x2b or x2a = x1b, then test if Y
    // overlap
    // 2) right-left: y2a = y1b or y1a = y2b, then test if X overlap

    int[] rectOne = thisSpace.getRoomRect();
    int[] rectTwo = otherSpace.getRoomRect();
    int x1a = rectOne[0];
    int y1a = rectOne[1];
    int x2a = rectOne[2];
    int y2a = rectOne[3];
    int x1b = rectTwo[0];
    int y1b = rectTwo[1];
    int x2b = rectTwo[2];
    int y2b = rectTwo[3];

    if (x1a == x2b || x2a == x1b) {

      return isOverlap(y1a, y2a, y1b, y2b);
    }

    if (y1a == y2b || y2a == y1b) {
      return isOverlap(x1a, x2a, x1b, x2b);
    }

    return result;
  }

  /**
   * Judge if two room two can be visible from room one. Being visible means they
   * have overlap on X or Y axis.
   * 
   * @param thisSpace  vision start from room one.
   * @param otherSpace the other room to be judged if is visible.
   * @return if it's true that from room one, we can see room two.
   */
  private static boolean isVisible(Room thisSpace, Room otherSpace) {
    // if two room has X or Y overlap, consider them visible to each other
    int[] rectOne = thisSpace.getRoomRect();
    int[] rectTwo = otherSpace.getRoomRect();
    int x1a = rectOne[0];
    int y1a = rectOne[1];
    int x2a = rectOne[2] + 1;
    int y2a = rectOne[3] + 1;
    int x1b = rectTwo[0];
    int y1b = rectTwo[1];
    int x2b = rectTwo[2] + 1;
    int y2b = rectTwo[3] + 1;

    return isOverlap(y1a, y2a, y1b, y2b) || isOverlap(x1a, x2a, x1b, x2b);
  }

  // rerun {row, col} of the world.

  public ArrayList<Room> getWorldSpace() {
    return roomList;
  }

  public String getWorldName() {
    return worldName;
  }

  /**
   * Draw the map to image, according to the scaling and padding argument.
   * 
   * @return buffered image later can be used to output.
   */

  public BufferedImage drawWorld() {
    return drawWorld(20, 5, 5);
  }

  private BufferedImage drawWorld(int scale, int leftPadding, int topPadding) {
    int width = colSize;
    int height = rowSize;

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

    for (Room room : roomList) {
      // apply paddings and scaling to draw room's rectangel
      int x1 = room.getRoomRect()[1] + leftPadding;
      int y1 = room.getRoomRect()[0] + topPadding;
      int x2 = room.getRoomRect()[3] + leftPadding;
      int y2 = room.getRoomRect()[2] + topPadding;
      int rectX = x1 * scale;
      int rectY = y1 * scale;
      int rectWidth = (x2 - x1) * scale;
      int rectHeight = (y2 - y1) * scale;
      graph.drawRect(rectX, rectY, rectWidth, rectHeight);
      // set the font to the middle of the rectangle and draw it
      FontMetrics fontMetrics = graph.getFontMetrics();
      // int textWidth = fontMetrics.stringWidth(room.getSpaceName());
      int textHeight = fontMetrics.getHeight();
      // 5 is a proper offset to guarantee the text not over lap with tht
      int textX = rectX + 5;
      int textY = rectY + (rectHeight - textHeight) / 2 + fontMetrics.getAscent();
      graph.drawString(String.format("%d %s", room.getSpaceIndex(), room.getSpaceName()), textX,
          textY);
    }

    // At last, draw the world name on top

    // Use another font to draw the world title
    Font fontTitle = new Font("SansSerif", Font.ITALIC, 40); // Font name, style, size
    graph.setFont(fontTitle);
    FontMetrics fontMetrics = graph.getFontMetrics();
    int textWidth = fontMetrics.stringWidth(worldName);

    int textX = (graphWidth * scale - textWidth) / 2;

    graph.drawString(worldName, textX, topPadding * scale - fontMetrics.getDescent());

    graph.dispose();
    return image;
  }

  @Override
  public String toString() {
    return worldName;
  }

  @Override
  public String getName() {
    return worldName;
  }

  @Override
  public String getDetails() {
    String worldInfo = String.format(
        "World [World name = %s, room number =  %d, item number = %d, "
            + "target charater = %s, pet = %s, player number = %d].",
        worldName, roomList.size(), itemList.size(), targetCharacter.getName(), pet.getName(),
        playerList.size());
    return worldInfo;
  }

  private boolean isPlayerIdValid(int id) {
    return id >= 0 && id < playerList.size();
  }

  /**
   * Sets the location of a player to the specified destination location. with the
   * room info updated too.
   *
   * @param playerIndex  The index of the player whose location is to be set.
   * @param destLocation The index of the destination location to set for the
   *                     player.
   * @throws IllegalArgumentException if playerIndex is out of bounds or
   *                                  destLocation is not a valid location.
   */
  public void setPlayerLocation(int playerIndex, int destLocation) {
    if (!isPlayerIdValid(playerIndex) || !isLocationValid(destLocation)) {
      throw new IllegalArgumentException(
          String.format("Illegal argument: player %d, location %d", playerIndex, destLocation));
    }
    Player player = playerList.get(playerIndex);
    int originLocaion = player.getLocation();
    // change player position to new
    player.setLocation(destLocation);
    // remove from the origin room
    roomList.get(originLocaion).removeCharacter(player);
    // add to the dest room
    roomList.get(destLocation).addCharacer(player);
  }

  @Override
  public void moveTargetNextRoom() {
    int curLocation = targetCharacter.getLocation();
    int nextLocation = (curLocation + 1) % roomList.size();
    targetCharacter.setLocation(nextLocation);
    // move character from previous room
    roomList.get(curLocation).setTargetOut();
    // also put character into the room
    roomList.get(nextLocation).setTargetIn();
  }

  public TargetCharacter getTarget() {
    return targetCharacter;
  }

  public Room getRoomSpace(int index) {
    return roomList.get(index);
  }

  @Override
  public int getTargetLocation() {
    return targetCharacter.getLocation();
  }

  /**
   * Print out the room info in a formatted way.
   * 
   * @param index the index of the room array
   */
  public void printRoomInfo(int index) {
    Room room = getRoomSpace(index);
    System.out.println(String.format("[Room No.%d: %s's information]", index, room.getSpaceName()));
    System.out.println(String.format("Neighbors: %s", room.getNeighbors()));
    System.out.println(String.format("Visible: %s", room.getVisibles()));
    System.out.println(String.format("Items: %s", room.getSpaceItem()));
  }

  public ArrayList<Item> getItems() {
    return itemList;
  }

  public void printItemInfo(int index) {
    System.out.println(itemList.get(index).queryDetails());
  }

  @Override
  public int getPlayerCount() {
    return playerList.size();
  }

  /**
   * Add a new player to the queue of the model, if the name already exist, throws
   * IllegalArgumentException.
   */

  @Override
  public void addNewPlayer(String name, int initLocation, int capacity, boolean isHumanControl)
      throws IllegalArgumentException {
    if (initLocation < 0 || initLocation >= roomList.size()) {
      throw new IllegalArgumentException(String.format("Wrong location index %d", initLocation));
    }
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be at least one");
    }
    // put player in list
    Player toAddPlayer = new Player(name, initLocation, capacity, isHumanControl,
        playerList.size());
    playerList.add(toAddPlayer);
    // add player to room
    roomList.get(initLocation).addCharacer(toAddPlayer);
  }

  @Override
  public int getRoomCount() {
    return roomList.size();
  }

  @Override
  public int getPlayerLocation(int playerId) {
    Player player = playerList.get(playerId);
    return player.getLocation();
  }

  private boolean isLocationValid(int roomIndex) {
    return roomIndex >= 0 && roomIndex < roomList.size();

  }

  /**
   * Checks if a given room is a neighbor of another room.
   *
   * @param quest The index of the room being checked as a neighbor.
   * @param base  The index of the base room.
   * @return true if the rooms are neighbors, false otherwise.
   * @throws IndexOutOfBoundsException if quest or base are not valid room
   *                                   indices.
   */
  @Override
  public boolean isNeighbor(int quest, int base) {
    if (isLocationValid(base) && isLocationValid(quest)) {
      return roomList.get(base).getNeighbors().contains(roomList.get(quest));
    } else {
      throw new IndexOutOfBoundsException("Room index not valid");
    }
  }

  @Override
  public int getCurrentPlayer(int turn) {
    return turn % getPlayerCount();
  }

  /**
   * Queries the items available in the specified room.
   *
   * @param location The index of the room to query.
   * @return A formatted string containing information about the items in the
   *         room. If there are no items in the room, it returns "No item."
   *         followed by a newline character.
   * @throws IndexOutOfBoundsException if the location is not a valid room index.
   */

  public String queryRoomItem(int location) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Item item : roomList.get(location).getSpaceItem()) {
      stringBuilder.append(item.queryDetails()).append("\n");
    }
    if (stringBuilder.length() == 0) {
      stringBuilder.append("No item.\n");
    }
    return stringBuilder.toString();
  }

  /**
   * Gets the location of the specified item.
   *
   * @param itemId The unique identifier of the item.
   * @return The index of the room where the item is stored.
   * @throws IndexOutOfBoundsException if the itemId is not a valid item id.
   */

  public int getItemLocation(int itemId) {
    if (itemId < 0 || itemId > itemList.size()) {
      throw new IndexOutOfBoundsException(String.format("Invalid item id %d.", itemId));
    }
    return itemList.get(itemId).getStoredLoacation();
  }

  /**
   * Allows a player to pick up an item from the current room.
   *
   * @param playerId The ID of the player picking up the item.
   * @param itemId   The ID of the item to be picked up.
   * @throws IndexOutOfBoundsException if the playerId or itemId is not a valid
   *                                   ID.
   */

  public void pickUpitem(int playerId, int itemId) {
    Player player = playerList.get(playerId);
    Room roomSpace = roomList.get(player.getLocation());
    Item item = itemList.get(itemId);
    // player got the item
    player.addItem(item);
    // room remove the item
    roomSpace.removeItem(item);
    // item location to -1
    item.setStoredLoacation(-1);
  }

  public String queryRoomDetails(int location) {
    return roomList.get(location).queryDetails();
  }

  @Override
  public String queryPlayerDetails(int playerId) {
    return playerList.get(playerId).querryDetails();
  }

  @Override
  public String queryTargetDetails() {
    return targetCharacter.querryDetails();
  }

  @Override
  public String queryRoomNeighbors(int playerLocation) {

    return roomList.get(playerLocation).queryRoomNeighbors();
  }

  @Override
  public boolean playerReachCapacity(int playerId) {
    return playerList.get(playerId).reachItemCapacity();
  }

  @Override
  public int getRoomItemCount(int location) {
    return roomList.get(location).getItems().size();
  }

  @Override
  public ArrayList<Integer> getRoomNeighbors(int location) {
    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Room room : roomList.get(location).getNeighbors()) {
      retList.add(room.getSpaceIndex());
    }
    return retList;
  }

  @Override
  public boolean isHumanPlayer(int playerId) {
    return playerList.get(playerId).isHumanPlayer();
  }

  @Override
  public ArrayList<Integer> getRoomItems(int location) {
    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Item item : roomList.get(location).getItems()) {
      retList.add(item.getItemId());
    }
    return retList;
  }

  @Override
  public ArrayList<Integer> getPlayerItems(int playerId) {
    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Item item : playerList.get(playerId).getItemList()) {
      retList.add(item.getItemId());
    }
    return retList;
  }

  @Override
  public String getPlayerString(int playerId) {
    return playerList.get(playerId).toString();
  }

  @Override
  public String getRoomString(int location) {
    return roomList.get(location).toString();
  }

  @Override
  public String getTargetString() {
    return targetCharacter.toString();
  }

  @Override
  public String getItemString(int itemId) {
    return itemList.get(itemId).toString();
  }

  @Override
  public ArrayList<Integer> getRoomCharacter(int location) {
    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Player player : roomList.get(location).getCharacterList()) {
      retList.add(player.getPlayerId());
    }
    return retList;
  }
}
