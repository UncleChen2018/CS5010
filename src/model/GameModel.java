package model;

import java.awt.image.BufferedImage;

/**
 * The game model interface. Including setup a brand new world, (including
 * rooms, target, character, player) and all methods that are used to get the
 * game process, as well as reveal details of the current states.
 * 
 */
public interface GameModel {
  void setupNewWorld(Readable source);

  BufferedImage drawWorld();

  String getDetails();

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
   * Return the player's current location.
   * 
   * @param playerId.
   * @return space index.
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
   * Decide which player should act on the turn.
   * 
   * @param turn
   * @return the player id that should act.
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
  
  
  /** Given room location, query its item information.
   * @param location the room index.
   * @return A string contains the item info (No, name, damage)
   */
  String queryRoomItem(int location) ;
}
