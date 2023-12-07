package model;

import java.util.ArrayList;

/**
 * The read only model use by the view.
 */
public interface ViewModel {

  /**
   * Returns the name of the world.
   *
   * @return The name of the world.
   */
  public String getWorldName();

  /**
   * How many rooms exit in the game.
   * 
   * @return the room number.
   */
  int getRoomCount();

  /**
   * Get the room grid based representation data.
   * 
   * @param index the index of the room.
   * @return the room's upper left and bottom right as integer array.
   */
  int[] getRoomRect(int index);

  /**
   * Get the world size of grids.
   * 
   * @return the and integer array of rows and cols
   */
  int[] getWorldSize();

  /**
   * By index, get the name of a room.
   * 
   * @param index the index of the room
   * @return the name of the room
   */
  String getRoomName(int index);

  /**
   * Get target location.
   * 
   * @return the room index target is in.
   */
  int getTargetLocation();

  /**
   * How many players in this game.
   * 
   * @return players number.
   */
  int getPlayerCount();

  /**
   * Gets a list of character IDs present in the room at the specified location.
   *
   * @param location the location of the room
   * @return a list of character IDs in the room
   */

  ArrayList<Integer> getRoomCharacter(int location);

  /**
   * Queries and retrieves detailed information about the target.
   *
   * @return a formatted string containing target details
   */

  String queryTargetDetails();

  /**
   * Queries and retrieves detailed information about the player with the
   * specified ID.
   *
   * @param playerId the ID of the player
   * @return a formatted string containing player details
   */

  String queryPlayerDetails(int playerId);

  /**
   * Get String represent the information INSIDE the room.
   * 
   * @param location index of the room.
   * @return String contains room's index, name; items in, characters in and if
   *         target in;
   */
  String queryRoomDetails(int location);

  /**
   * Get the current turn for the game.If 0, the game has not started yet.
   * 
   * @return the current turn for the game.
   */
  int getCurrentTurn();

  /**
   * Get the max turn for the game.
   * 
   * @return the max turn for the game.
   */
  int getMaxTurn();

  /**
   * Retrieves the ID of the player who is the winner of the game.
   *
   * @return An integer representing the ID of the winning player.
   */
  int getWinner();

  /**
   * Gets a formatted string representation of the player with the specified ID.
   *
   * @param playerId the ID of the player
   * @return a formatted string containing player information
   */

  String getPlayerString(int playerId);

  /**
   * Get the current playe's id from the model.
   * 
   * @return the current turn player's id.
   */
  int getCurrentPlayer();

  /**
   * Checks if the game is over with some winner.
   *
   * @return {@code true} if the game is over, {@code false} otherwise.
   */
  boolean isGameOverWithWinner();

  /**
   * Checks if the game is over with the max turn.
   * 
   * @return {@code true} if the game is over, {@code false} otherwise.
   */
  boolean isGameOverWithMaxTurn();

  /**
   * Check if two room are neighbors.
   * 
   * @param quest which room index to check.
   * @param base  which room is the check based.
   * @return true if these two are neighbors.
   */
  boolean isNeighbor(int quest, int base);

  /**
   * Returns the current location of the player with the specified ID.
   * 
   * @param playerId The ID of the player.
   * @return The index of the space where the player is located.
   */
  int getPlayerLocation(int playerId);

  /**
   * Gets a list of item IDs present in the room at the specified location.
   *
   * @param location the location of the room
   * @return a list of item IDs in the room
   */

  ArrayList<Integer> getRoomItems(int location);

  /**
   * Queries and retrieves detailed information about the item with the specified.
   * 
   * @param itemId the ID of the item
   * @return a formatted string containing item details.
   */
  String querryItemInfo(int itemId);

  /**
   * Gets a list of item IDs associated with the specified player.
   *
   * @param playerId the ID of the player
   * @return a list of item IDs
   */
  ArrayList<Integer> getPlayerItems(int playerId);

  /**
   * Retrieves the name of the item with the specified ID.
   *
   * @param itemId The ID of the item to retrieve the name for.
   * @return A String representing the name of the item.
   */
  String getItemName(int itemId);

}
