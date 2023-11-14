package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * The mock model based on the tested World. Will additionally add every method
 * name called and its input and out put to a log.
 */
public class MockModel implements GameModel {
  private StringBuilder log;

  private String worldName;
  private int rowSize;
  private int colSize;

  private TargetCharacter targetCharacter;
  private ArrayList<Room> roomList;
  private ArrayList<Item> itemList;
  private ArrayList<Player> playerList;

  private Pet pet;
  // Keep track of the location that is visited by pet.
  private Set<Room> petVisitedRoom;
  private Room petNextRoom;
  private boolean petNeedTraceback = false;
  private Stack<Room> petTrace = new Stack<>();

  private int winnerId;

  public MockModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Set up new world using data from a readable source.
   * 
   * @param source file or string match certain format.
   * @param log    the log to store the inner processing result.
   */
  public MockModel(Readable source, StringBuilder log) {
    // this.world = new World(source);
    this.log = log;
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
  @Override
  public void setupNewWorld(Readable source) throws InputMismatchException {
    log.append("setupNewWorld called\n");
    roomList = new ArrayList<Room>();
    itemList = new ArrayList<Item>();
    playerList = new ArrayList<Player>();
    winnerId = -1;
    Scanner scanner = new Scanner(source);

    // parse the World
    this.rowSize = scanner.nextInt();
    this.colSize = scanner.nextInt();
    this.worldName = scanner.nextLine().trim();

    // parse the target character
    int fullHealth = scanner.nextInt();
    String roleName = scanner.nextLine().trim();
    this.targetCharacter = new TargetCharacter(roleName, fullHealth);

    // parse the pet and give it the same place as target.
    String petName = scanner.nextLine().trim();
    pet = new Pet(petName, targetCharacter.getLocation());

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

    // New: add pet to the same room as target.
    roomList.get(targetCharacter.getLocation()).setPetIn();

    // New: set the visitedByPet to null
    petVisitedRoom = new HashSet<>();
    // add the first location to pet visited.
    Room petInitialRoom = roomList.get(pet.getLocation());
    resetPetTrace(petInitialRoom);

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
    log.append("drawWorld called\n");
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
    log.append("getName called\n");
    return worldName;
  }

  @Override
  public String getDetails() {
    log.append("getDetails called\n");
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
    log.append(String.format("setPlayerLocation called, playerIndex = %d, destLocation = %d",
        playerIndex, destLocation)).append("\n");
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

  /**
   * Sets the location of a pet to the specified destination location. with the
   * room info updated too.
   *
   * @param destLocation The index of the destination location to set for the
   *                     player.
   * @throws IllegalArgumentException if destLocation is not a valid location.
   */
  public void setPetLocation(int destLocation) {
    if (!isLocationValid(destLocation)) {
      throw new IllegalArgumentException(
          String.format("Illegal argument: location %d", destLocation));
    }
    int originLocaion = pet.getLocation();
    // change pet position to new
    pet.setLocation(destLocation);
    // remove from the origin room
    roomList.get(originLocaion).setPetOut();
    // add to the dest room
    roomList.get(destLocation).setPetIn();
  }

  
  @Override
  public void moveTargetNextRoom() {
    log.append("moveTargetNextRoom called\n");
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
    log.append("getTargetLocation called\n");
    return targetCharacter.getLocation();
  }


  public ArrayList<Item> getItems() {
    return itemList;
  }


  @Override
  public int getPlayerCount() {
    log.append("getPlayerCount called\n");
    return playerList.size();
  }

  /**
   * Add a new player to the queue of the model, if the name already exist, throws
   * IllegalArgumentException.
   */

  @Override
  public void addNewPlayer(String name, int initLocation, int capacity, boolean isHumanControl)
      throws IllegalArgumentException {
    log.append(
        String.format(
            "addNewPlayer called, name = %s, initLocation = %d, "
                + "capacity = %d, isHumanControl = %b ",
            name, initLocation, capacity, isHumanControl))
        .append("\n");

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
    log.append("getRoomCount called\n");

    return roomList.size();
  }

  @Override
  public int getPlayerLocation(int playerId) {
    log.append(String.format("getPlayerLocation called, playerId = %d", playerId)).append("\n");
    Player player = playerList.get(playerId);
    return player.getLocation();
  }

  public boolean isLocationValid(int roomIndex) {
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
    log.append(String.format("isNeighbor called, quest = %d, base = %d", quest, base)).append("\n");
    if (isLocationValid(base) && isLocationValid(quest)) {
      return roomList.get(base).getNeighbors().contains(roomList.get(quest));
    } else {
      throw new IndexOutOfBoundsException("Room index not valid");
    }
  }

  @Override
  public int getCurrentPlayer(int turn) {
    log.append(String.format("getCurrentPlayer called, turn = %d", turn)).append("\n");
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
    log.append(String.format("queryRoomItem called, location = %d", location)).append("\n");
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
    log.append(String.format("getItemLocation called, itemId = %d", itemId)).append("\n");
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
    log.append(
        String.format("pickUpitem called, playerId = %d, " + "itemId = %d", playerId, itemId))
        .append("\n");
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

  @Override
  public String queryRoomDetails(int location) {
    log.append(String.format("queryRoomDetails called, location = %d", location)).append("\n");
    return roomList.get(location).queryDetails();
  }

  @Override
  public String queryPlayerDetails(int playerId) {
    log.append(String.format("queryPlayerDetails called, playerId = %d", playerId)).append("\n");

    return playerList.get(playerId).querryDetails();
  }

  @Override
  public String queryTargetDetails() {
    log.append("queryTargetDetails called").append("\n");

    return targetCharacter.querryDetails();
  }

  @Override
  public String queryRoomNeighbors(int playerLocation) {
    log.append(String.format("queryPlayerDetails called, playerLocation = %d", playerLocation))
        .append("\n");

    return roomList.get(playerLocation).queryRoomNeighbors();
  }

  @Override
  public boolean playerReachCapacity(int playerId) {
    log.append(String.format("playerReachCapacity called, playerId = %d", playerId)).append("\n");

    return playerList.get(playerId).reachItemCapacity();
  }

  @Override
  public int getRoomItemCount(int location) {
    log.append(String.format("getRoomItemCount called, location = %d", location)).append("\n");

    return roomList.get(location).getItems().size();
  }

  @Override
  public ArrayList<Integer> getRoomNeighbors(int location) {
    log.append(String.format("getRoomNeighbors called, location = %d", location)).append("\n");

    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Room room : roomList.get(location).getNeighbors()) {
      retList.add(room.getSpaceIndex());
    }
    return retList;
  }

  @Override
  public boolean isHumanPlayer(int playerId) {
    log.append(String.format("isHumanPlayer called, playerId = %d", playerId)).append("\n");

    return playerList.get(playerId).isHumanPlayer();
  }

  @Override
  public ArrayList<Integer> getRoomItems(int location) {
    log.append(String.format("getRoomItems called, location = %d", location)).append("\n");

    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Item item : roomList.get(location).getItems()) {
      retList.add(item.getItemId());
    }
    return retList;
  }

  @Override
  public ArrayList<Integer> getPlayerItems(int playerId) {
    log.append(String.format("getPlayerItems called, playerId = %d", playerId)).append("\n");
    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Item item : playerList.get(playerId).getItemList()) {
      retList.add(item.getItemId());
    }
    return retList;
  }

  @Override
  public String getPlayerString(int playerId) {
    log.append(String.format("getPlayerString called, playerId = %d", playerId)).append("\n");

    return playerList.get(playerId).toString();
  }

  @Override
  public String getRoomString(int location) {
    log.append(String.format("getRoomString called, location = %d", location)).append("\n");

    return roomList.get(location).toString();
  }

  @Override
  public String getTargetString() {
    log.append("getTargetString called").append("\n");
    return targetCharacter.toString();
  }

  @Override
  public String getItemString(int itemId) {
    log.append(String.format("getItemString called, itemId = %d", itemId)).append("\n");

    return itemList.get(itemId).toString();
  }

  @Override
  public ArrayList<Integer> getRoomCharacter(int location) {
    log.append(String.format("getRoomCharacter called, location = %d", location)).append("\n");

    ArrayList<Integer> retList = new ArrayList<Integer>();
    for (Player player : roomList.get(location).getCharacterList()) {
      retList.add(player.getPlayerId());
    }
    return retList;
  }



  @Override
  public String getPetString() {
    log.append(String.format("getPetString called")).append("\n");
    return pet.toString();
  }

  @Override
  public int getTargetHealth() {
    log.append(String.format("getTargetHealth called")).append("\n");
    return targetCharacter.getHealth();
  }

  @Override
  public int getItemDamage(int itemId) {
    log.append(String.format("getItemDamage called, itemid = %d", itemId)).append("\n");
    return itemList.get(itemId).getItemDamage();
  }

  @Override
  public String getItemName(int itemId) {
    log.append(String.format("getItemName called, itemid = %d", itemId)).append("\n");
    return itemList.get(itemId).getItemName();
  }

  @Override
  public void attackTarget(int damage) {
    log.append(String.format("attackTarget called, damage = %d", damage)).append("\n");
    targetCharacter.setHealth(targetCharacter.getHealth() - damage);
  }

  @Override
  public void removePlayerItem(int playerId, int itemId) {
    log.append(
        String.format("removePlayerItem called, playerId = %d, itemId = %d", playerId, itemId))
        .append("\n");
    playerList.get(playerId).removeItem(itemList.get(itemId));
  }

  @Override
  public void setWinner(int playerId) {
    log.append(String.format("setWinner called, playerId = %d", playerId)).append("\n");
    winnerId = playerId;
  }

  @Override
  public int getWinner() {
    log.append(String.format("gettWinner called")).append("\n");
    return winnerId;
  }

  @Override
  public boolean isGameOver() {
    log.append(String.format("gameOver called")).append("\n");
    return winnerId != -1;
  }

  @Override
  public boolean isAttackInvisible(int playerId) {
    log.append(String.format("isAttackInvisible called, playerId = %d", playerId)).append("\n");
    int location = playerList.get(playerId).getLocation();
    return roomList.get(location).isRoomInvisible();
  }

  @Override
  public String queryPlayerItems(int playerId) {
    log.append(String.format("queryPlayerItems called, playerId = %d", playerId)).append("\n");
    return playerList.get(playerId).getItemList().toString();
  }
  
  
  @Override
  public int getPetLocation() {
    log.append(String.format("getPetLocation called")).append("\n");
    return pet.getLocation();
  }

  

  @Override
  public void movePetNextRoom() {
    log.append(String.format("movePetNextRoom called")).append("\n");
    if (pet.isStunned()) {
      pet.wakeUp();
      return;
    }

    getPetNextRoom();

    // which next room the pet should move
    Room nextRoom;
    if (!petNeedTraceback) {
      nextRoom = petNextRoom;
    } else {
      if (!petTrace.empty()) {
        nextRoom = petTrace.peek();
      } else {
        throw new IllegalStateException("Wrong pet trace");
      }
    }


    // deal with the move
    setPetLocation(nextRoom.getSpaceIndex());

    // deal with the visited and trace if not a trace back visit.
    if (!petNeedTraceback) {
      petVisitedRoom.add(nextRoom);
      // check if all visited, begin next turn.
      petTrace.push(nextRoom);
    }


  }

  private void getPetNextRoom() {
    Room curRoom = roomList.get(pet.getLocation());
    // see if all room is visited.
    if (petVisitedRoom.size() == roomList.size()) {
      resetPetTrace(curRoom);
    }

    // in case the pet is in the room with no neighbor
    if (curRoom.getNeighbors().size() == 0) {
      petNextRoom = curRoom;
      petNeedTraceback = false;
      return;
    }

    for (Room neighboRoom : curRoom.getNeighbors()) {
      if (!petVisitedRoom.contains(neighboRoom)) {
        petNeedTraceback = false;
        petNextRoom = neighboRoom;
        return;
      }
    }
    // means the current room has no more to be visited, get it out

    petNeedTraceback = true;
    petTrace.pop();
  }

  private void resetPetTrace(Room initialRoom) {
    // clear the pet visited record in this world.
    petVisitedRoom.clear();
    // also clear the trace of the cat.
    petTrace.clear();
    petNeedTraceback = false;
    petNextRoom = null;
    petVisitedRoom.add(initialRoom);
    petTrace.push(initialRoom);
  }
  
  @Override
  public void teleportPetLocation(int location) {
    log.append(String.format("teleportPetLocation called, location = %d", location)).append("\n");
    setPetLocation(location);
    resetPetTrace(roomList.get(location)); 
    pet.setStunned();
  }

}
