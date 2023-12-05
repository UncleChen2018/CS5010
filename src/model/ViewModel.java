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

}
