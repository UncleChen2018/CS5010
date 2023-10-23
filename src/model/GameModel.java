package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The game model interface. Including setup a brand new world, (including
 * rooms, target, character, player) and all methods that are used to get the
 * game process, as well as reveal details of the current states.
 * 
 */
public interface GameModel {
  /**
   * Set up the new world before it can be used.
   * 
   * @param source the data from which the world is built.
   */
  void setupNewWorld(Readable source);

  /**
   * Based on rooms, generate the map.
   * 
   * @return image data to draw the map.
   */
  BufferedImage drawWorld();

  /**
   * Get a world summary in String.
   * 
   * @return the summary.
   */
  String getDetails();

  /**
   * Give the world's name.
   * 
   * @return name of the world.
   */
  String getName();

  /**
   * How many players in this game.
   * 
   * @return players number.
   */
  int getPlayerCount();

  /**
   * Set player to certain space.
   * 
   * @param playerId player's unique id.
   * @param location room's index.
   */
  void setPlayerLocation(int playerId, int location);

  /**
   * Returns the current location of the player with the specified ID.
   * 
   * @param playerId The ID of the player.
   * @return The index of the space where the player is located.
   */
  int getPlayerLocation(int playerId);

  /**
   * Check if two room are neighbors.
   * 
   * @param quest which room index to check.
   * @param base  which room is the check based.
   * @return true if these two are neighbors.
   */
  boolean isNeighbor(int quest, int base);

  /**
   * Decides which player should take action on the turn.
   * 
   * @param turn The current turn.
   * @return The ID of the player that should take action.
   */

  public int getCurrentPlayer(int turn);

  /**
   * Add a new player to the game.
   * 
   * @param name           player's name.
   * @param initLocation   where the player should be on its first act.
   * @param capacity       how many items the player can take. No less than 1.
   * @param isHumanControl true if controlled by human, else by computer.
   * @throws IllegalArgumentException if location, capacity is not valid in the
   *                                  game.
   */
  void addNewPlayer(String name, int initLocation, int capacity, boolean isHumanControl)
      throws IllegalArgumentException;

  /**
   * How many rooms exit in the game.
   * 
   * @return the room number.
   */
  int getRoomCount();

  /**
   * Move target to the next room. Target will move according to the index of
   * room.
   */
  void moveTargetNextRoom();

  /**
   * Given room location, query its item information.
   * 
   * @param location the room index.
   * @return A string contains the item info (No, name, damage)
   */
  String queryRoomItem(int location);

  void pickUpitem(int playerId, int itemId);

  /**
   * Gets the location index of the item if it is in a room.
   * 
   * @param itemId The ID of the item.
   * @return The location index. If the item is not in a room, returns -1.
   */

  int getItemLocation(int itemId);

  /**
   * Get String represent the information INSIDE the room.
   * 
   * @param location index of the room.
   * @return String contains room's index, name; items in, characters in and if
   *         target in;
   */
  String queryRoomDetails(int location);

  String queryPlayerDetails(int playerId);

  CharSequence queryTargetDetails();

  String queryRoomNeighbors(int playerLocation);

  ArrayList<Integer> getRoomNeighbors(int location);

  boolean playerReachCapacity(int playerId);

  int getRoomItemCount(int location);

  boolean isHumanPlayer(int playerId);

  ArrayList<Integer> getRoomItems(int location);

  String getPlayerString(int playerId);

  String getRoomString(int location);

  String getTargetString();

  String getItemString(int itemId);

  ArrayList<Integer> getRoomCharater(int location);

}
