package model;

import java.util.ArrayList;

/**
 * Rooms in the world, with index, rectangle coordinate it can be represented,
 * room name, arrays of containing item, neighbor rooms and other visible rooms.
 * Also contain the pet's infomation.
 */
public class RoomSpace implements RoomRect {

  private int spaceIndex;
  private int[] rectCordinate; // the coordinate of the room rectangle
  private String name;

  // Not final here, later may need to change
  private ArrayList<Item> itemList;
  private ArrayList<RoomRect> neighboRoomSpaces;
  private ArrayList<RoomRect> visbleRoomSpaces;
  private ArrayList<Player> characterList;
  private boolean isTargetIn;
  private boolean isPetIn;

  /**
   * Initialize room in the world.
   * 
   * @param spaceIndex the index of the room
   * @param rowStart   the row where the room start.
   * @param colStart   the column where the room start.
   * @param rowEnd     the row where the room end.
   * @param colEnd     the column where the room end.
   * @param name       the room's name.
   */
  public RoomSpace(int spaceIndex, int rowStart, int colStart, int rowEnd, int colEnd,
      String name) {
    this.spaceIndex = spaceIndex;
    rectCordinate = new int[4];
    rectCordinate[0] = rowStart;
    rectCordinate[1] = colStart;
    rectCordinate[2] = rowEnd + 1; //
    rectCordinate[3] = colEnd + 1;
    this.name = name;
    this.itemList = new ArrayList<Item>();
    this.neighboRoomSpaces = new ArrayList<RoomRect>();
    this.visbleRoomSpaces = new ArrayList<RoomRect>();
    this.characterList = new ArrayList<Player>();
    isTargetIn = false;
    isPetIn = false;
  }

  /**
   * Marks the room as having the pet inside.
   */
  public void setPetIn() {
    isPetIn = true;
  }

  /**
   * Marks the room as having no pet inside.
   */
  public void setPetOut() {
    isPetIn = false;
  }

  /**
   * Adds a neighbor room to the current room.
   *
   * @param neighbor The neighboring room to add.
   */
  public void addNeighbor(RoomSpace neighbor) {
    neighboRoomSpaces.add(neighbor);
  }

  /**
   * Adds a visible room to the current room.
   *
   * @param visible The visible room to add.
   */
  public void addVisible(RoomSpace visible) {
    visbleRoomSpaces.add(visible);
  }

  /**
   * Retrieves the name of the room.
   *
   * @return The name of the room.
   */
  public String getSpaceName() {
    return name;
  }

  /**
   * Retrieves the index of the room.
   *
   * @return The index of the room.
   */
  public int getSpaceIndex() {
    return spaceIndex;
  }

  /**
   * Adds an item to the room and sets its stored location to the room's index.
   *
   * @param item The item to be added to the room.
   */
  public void addItem(Item item) {
    itemList.add(item);
    item.setStoredLoacation(spaceIndex);
  }

  /**
   * Retrieves the list of items in the room.
   *
   * @return An ArrayList of Item objects in the room.
   */
  public ArrayList<Item> getSpaceItem() {
    return itemList;
  }

  /**
   * Retrieves the coordinates of the room's rectangle.
   *
   * @return An array containing the coordinates of the room's rectangle.
   */
  public int[] getRoomRect() {
    return rectCordinate;
  }

  @Override
  public String toString() {
    return String.format("No.%d \"%s\"", spaceIndex, name);
  }

  /**
   * Retrieves the list of neighbor rooms.
   *
   * @return An ArrayList of Room objects representing the neighbor rooms.
   */
  public ArrayList<RoomRect> getNeighbors() {
    return neighboRoomSpaces;
  }

  /**
   * Retrieves the list of visible rooms.
   *
   * @return An ArrayList of Room objects representing the visible rooms.
   */
  public ArrayList<RoomRect> getVisibles() {
    return visbleRoomSpaces;
  }

  /**
   * Retrieves the list of items in the room.
   *
   * @return An ArrayList of Item objects representing the items in the room.
   */
  public ArrayList<Item> getItems() {
    return itemList;
  }

  /**
   * Give a string that describes the name, items and visible spaces from the
   * room.
   * 
   * @return a formated String containing the information as mentioned.
   */
  public String getSpaceInfo() {
    String retString = String.format("%s, with items: %s. visible space: %s", name, itemList,
        visbleRoomSpaces);
    return retString;
  }

  /**
   * Retrieves the list of characters in the room.
   *
   * @return An ArrayList of Player objects representing the characters in the
   *         room.
   */
  public ArrayList<Player> getCharacterList() {
    return this.characterList;
  }

  /**
   * Adds a character to the room.
   *
   * @param character The Player object to be added to the room.
   */
  public void addCharacer(Player character) {
    characterList.add(character);
  }

  /**
   * Removes a character from the room.
   *
   * @param character The Movable object to be removed from the room.
   */
  public void removeCharacter(Movable character) {
    characterList.remove(character);
  }

  /**
   * Sets the room's target state to "in."
   */
  public void setTargetIn() {
    this.isTargetIn = true;
  }

  /**
   * Sets the room's target state to "out."
   */
  public void setTargetOut() {
    this.isTargetIn = false;
  }

  /**
   * Checks if the room has a target.
   *
   * @return True if there is a target in the room, false otherwise.
   */
  public boolean hasTarget() {
    return isTargetIn;
  }

  /**
   * Removes an item from the room.
   *
   * @param item The Item object to be removed from the room.
   */
  public void removeItem(Item item) {
    this.itemList.remove(item);
  }

  /**
   * Returns a detailed description of the items, players, and target in the room.
   *
   * @return A formatted string representing the room details.
   */

  public String queryDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("-------------------ROOM DETAILS-------------------\n");
    stringBuilder.append("Room: ").append(this).append("\n").append("Items: ")
        .append(itemList.size() > 0 ? itemList : "No Item").append("\n").append("Player: ")
        .append(characterList.size() > 0 ? characterList : "No Player").append("\n")
        .append("Target: ").append(isTargetIn ? "Found" : "Not Found").append("\n").append("Pet: ")
        .append(isPetIn ? "Found" : "Not Found").append("\n");

    return stringBuilder.toString();
  }

  /**
   * Returns information about neighboring rooms.
   *
   * @return A formatted string representing the neighbor information.
   */

  public String queryRoomNeighbors() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("-------------------Neighbor Info-------------------\n");
    for (RoomRect room : neighboRoomSpaces) {
      stringBuilder.append(room).append("\n");
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean isPetIn() {
    return isPetIn;
  }

  @Override
  public boolean isRoomInvisible() {
    if (characterList.size() > 1) {
      return false;
    }
    if (isPetIn) {
      return true;
    }
    for (RoomRect neighbor : neighboRoomSpaces) {
      if (neighbor.getCharacterList().size() >= 1) {
        return false;
      }
    }
    return true;
  }
  

}
