package model;

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

}
