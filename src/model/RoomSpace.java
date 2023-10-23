package model;

import java.util.ArrayList;

/**
 * Rooms in the world, with index, rectangle coordinate it can be represented,
 * room name, arrays of containing item, neighbor rooms and other visible rooms.
 */
public class RoomSpace implements Room {

  private int spaceIndex;
  private int[] rectCordinate; // the coordinate of the room rectangle
  private String name;

  // Not final here, later may need to change
  private ArrayList<Item> itemList;
  private ArrayList<Room> neighboRoomSpaces;
  private ArrayList<Room> visbleRoomSpaces;
  private ArrayList<Movable> characterList;
  private boolean isTargetIn;

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
    this.neighboRoomSpaces = new ArrayList<Room>();
    this.visbleRoomSpaces = new ArrayList<Room>();
    this.characterList = new ArrayList<Movable>();
    isTargetIn = false;
  }

  public void addNeighbor(RoomSpace neighbor) {
    neighboRoomSpaces.add(neighbor);
  }

  public void addVisible(RoomSpace visible) {
    visbleRoomSpaces.add(visible);
  }

  public String getSpaceName() {
    return name;
  }

  public int getSpaceIndex() {
    return spaceIndex;
  }

  public void addItem(Item item) {
    itemList.add(item);
    item.setStoredLoacation(spaceIndex);
  }

  public ArrayList<Item> getSpaceItem() {
    return itemList;
  }

  public int[] getRoomRect() {
    return rectCordinate;
  }

  @Override
  public String toString() {
    return String.format("No.%d \"%s\"", spaceIndex, name);
  }

  // return the neighbors list
  public ArrayList<Room> getNeighbors() {
    return neighboRoomSpaces;
  }

  // return the visible list
  public ArrayList<Room> getVisibles() {
    return visbleRoomSpaces;
  }

  // return the neighbors list
  public ArrayList<Item> getItems() {
    return itemList;
  }

  public ArrayList<Movable> getCharacters() {
    return characterList;
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

  public ArrayList<Movable> getCharacterList() {
    return this.characterList;
  }

  public void addCharacer(Movable character) {
    characterList.add(character);
  }

  public void removeCharacter(Movable character) {
    characterList.remove(character);
  }

  public void setTargetIn() {
    this.isTargetIn = true;
  }

  public void setTargetOut() {
    this.isTargetIn = false;
  }

  public boolean hasTarget() {
    return isTargetIn;
  }

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
        .append("Target: ").append(isTargetIn ? "Found" : "Not Found").append("\n");

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
    for (Room room : neighboRoomSpaces) {
      stringBuilder.append(room).append("\n");
    }
    return stringBuilder.toString();
  }

}
