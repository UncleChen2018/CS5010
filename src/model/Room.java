package model;

import java.util.ArrayList;

/**
 * This interface represents a room in the world, defining its properties and
 * behaviors.
 */
public interface Room {

  /**
   * Get the index of the room in the world.
   *
   * @return The index of the room.
   */
  int getSpaceIndex();

  /**
   * Get the rectangle coordinates of the room.
   *
   * @return An array containing the coordinates [rowStart, colStart, rowEnd,
   *         colEnd].
   */
  int[] getRoomRect();

  /**
   * Get the name of the room.
   *
   * @return The name of the room.
   */
  String getSpaceName();

  /**
   * Add an item to the room.
   *
   * @param item The item to be added.
   */
  void addItem(Item item);

  /**
   * Get a list of items in the room.
   *
   * @return The list of items.
   */
  ArrayList<Item> getSpaceItem();

  /**
   * Get a list of neighboring rooms.
   *
   * @return The list of neighboring rooms.
   */
  ArrayList<Room> getNeighbors();

  /**
   * Get a list of visible rooms from this room.
   *
   * @return The list of visible rooms.
   */
  ArrayList<Room> getVisibles();

  /**
   * Get a list of items in the room.
   *
   * @return The list of items.
   */
  ArrayList<Item> getItems();

  /**
   * Get information about the room.
   *
   * @return A formatted string containing room information.
   */
  String getSpaceInfo();

  /**
   * Get a list of characters in the room.
   *
   * @return The list of characters.
   */
  ArrayList<Player> getCharacterList();

  /**
   * Add a character to the room.
   *
   * @param character The character to be added.
   */
  void addCharacer(Player character);

  /**
   * Remove a character from the room.
   *
   * @param character The character to be removed.
   */
  void removeCharacter(Movable character);

  /**
   * Set that the target is in this room.
   */
  void setTargetIn();

  /**
   * Set that the target is not in this room.
   */
  void setTargetOut();

  /**
   * Check if the target is in this room.
   *
   * @return True if the target is in this room, false otherwise.
   */
  boolean hasTarget();

  /**
   * Remove an item from the room.
   *
   * @param item The item to be removed.
   */
  void removeItem(Item item);

  /**
   * Get detailed information about the room.
   *
   * @return A formatted string containing room details.
   */
  String queryDetails();

  /**
   * Get information about neighboring rooms.
   *
   * @return A formatted string containing neighbor information.
   */
  String queryRoomNeighbors();

  /**
   * Sets the pet as inside the specified room.
   */
  void setPetIn();

  /**
   * Sets the pet as outside the specified room.
   */
  void setPetOut();

  /**
   * Checks if the pet is currently inside the room.
   *
   * @return {@code true} if the pet is inside, {@code false} otherwise.
   */
  boolean isPetIn();

  /**
   * Checks if the room is marked as invisible.
   *
   * @return {@code true} if the room is marked as invisible, {@code false}
   *         otherwise.
   */
  boolean isRoomInvisible();

}
