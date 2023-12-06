package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * The game model interface. Including setup a brand new world, (including
 * rooms, target, character, player) and all methods that are used to get the
 * game process, as well as reveal details of the current states.
 * 
 */
public interface GameModel extends ViewModel {
  /**
   * Set up the new world before it can be used.
   * 
   * @param source the data from which the world is built.
   */
  void setupNewWorld(Readable source) throws InputMismatchException;

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
   * Set player to certain space.
   * 
   * @param playerId player's unique id.
   * @param location room's index.
   */
  void setPlayerLocation(int playerId, int location);

  /**
   * Set pet to certain space.
   * 
   * @param location room's index.
   */
  void setPetLocation(int location);

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

  /**
   * Picks up an item with the specified ID and associates it with the player with
   * the given ID.
   *
   * @param playerId the ID of the player picking up the item
   * @param itemId   the ID of the item to be picked up
   */
  void pickUpitem(int playerId, int itemId);

  /**
   * Gets a list of item IDs associated with the specified player.
   *
   * @param playerId the ID of the player
   * @return a list of item IDs
   */
  ArrayList<Integer> getPlayerItems(int playerId);

  /**
   * Gets the location index of the item if it is in a room.
   * 
   * @param itemId The ID of the item.
   * @return The location index. If the item is not in a room, returns -1.
   */

  int getItemLocation(int itemId);







  /**
   * Queries and retrieves information about the neighboring rooms of the
   * specified player location.
   *
   * @param playerLocation the location of the player
   * @return a formatted string containing information about neighboring rooms
   */

  String queryRoomNeighbors(int playerLocation);

  /**
   * Gets a list of neighboring room IDs for the specified location.
   *
   * @param location the location for which to retrieve neighboring rooms
   * @return a list of neighboring room IDs
   */

  ArrayList<Integer> getRoomNeighbors(int location);

  /**
   * Checks if the player with the specified ID has reached their item capacity.
   *
   * @param playerId the ID of the player
   * @return true if the player has reached their item capacity, otherwise false
   */

  boolean playerReachCapacity(int playerId);

  /**
   * Gets the number of items in the room at the specified location.
   *
   * @param location the location of the room
   * @return the number of items in the room
   */

  int getRoomItemCount(int location);

  /**
   * Checks if the player with the specified ID is a human player.
   *
   * @param playerId the ID of the player
   * @return true if the player is a human player, otherwise false
   */

  boolean isHumanPlayer(int playerId);

  /**
   * Gets a list of item IDs present in the room at the specified location.
   *
   * @param location the location of the room
   * @return a list of item IDs in the room
   */

  ArrayList<Integer> getRoomItems(int location);


  /**
   * Gets a formatted string representation of the room at the specified location.
   *
   * @param location the location of the room
   * @return a formatted string containing room information
   */

  String getRoomString(int location);

  /**
   * Gets a formatted string representation of the target.
   *
   * @return a formatted string containing target information
   */
  String getTargetString();

  /**
   * Gets a formatted string representation of the item with the specified ID.
   *
   * @param itemId the ID of the item
   * @return a formatted string containing item information
   */

  String getItemString(int itemId);



  /**
   * Gets a formatted string representation of the pet.
   *
   * @return a formatted string containing pet information
   */
  String getPetString();

  /**
   * Check if the location index is valid.
   * 
   * @param location the room index.
   * @return if the index is valid in this world.
   */
  boolean isLocationValid(int location);

  /**
   * Retrieves the health value of the target.
   *
   * @return An integer representing the health of the target.
   */
  int getTargetHealth();

  /**
   * Retrieves the damage value of the specified item.
   *
   * @param itemId The ID of the item to retrieve the damage value for.
   * @return An integer representing the damage of the item.
   */
  int getItemDamage(int itemId);

  /**
   * Retrieves the name of the item with the specified ID.
   *
   * @param itemId The ID of the item to retrieve the name for.
   * @return A String representing the name of the item.
   */
  String getItemName(int itemId);

  /**
   * Performs an attack on the target with the specified amount of damage.
   *
   * @param damage The amount of damage to inflict on the target.
   */
  void attackTarget(int damage);

  /**
   * Removes the specified item from the inventory of the player.
   *
   * @param playerId The ID of the player from whose inventory the item will be
   *                 removed.
   * @param itemId   The ID of the item to be removed.
   */
  void removePlayerItem(int playerId, int itemId);

  /**
   * Sets the player with the specified ID as the winner of the game.
   *
   * @param playerId The ID of the player to be set as the winner.
   */
  void setWinner(int playerId);



  /**
   * Checks if the game is over.
   *
   * @return {@code true} if the game is over, {@code false} otherwise.
   */
  boolean isGameOver();

  /**
   * Checks if the player is currently visible by other player. A room is visible
   * if other player in the same or neighboring room. If pet exits, then the room
   * is invisible to player in neighboring room.
   *
   * @param playerId the player id to be judged if visible.
   * @return {@code true} if the room is visible, {@code false} otherwise.
   */
  boolean isAttackInvisible(int playerId);

  /**
   * Queries and retrieves detailed information about the player's item with the
   * specified ID.
   *
   * @param playerId the ID of the player
   * @return a formatted string containing player's item details
   */
  String queryPlayerItems(int playerId);

  /**
   * Move pet to the next room. Pet will move according to the Depth First Search
   * to different Room.
   */
  void movePetNextRoom();

  /**
   * Returns the current location of the pet.
   * 
   * @return The index of the space where the pet is located.
   */
  int getPetLocation();

  /**
   * Teleport pet to the next room. Pet will lose all its memory, and begin a new
   * start to move according to the Depth First Search to different Room.
   * 
   * @param location room's index.
   */
  void teleportPetLocation(int location);

  
  /**
   * Make the next turn.
   */
  void moveNextTurn();

  /**
   * Get the max turn for the game.
   * @param maxTurn the max turn for the game.
   */
  void setMaxTurn(int maxTurn);

}
