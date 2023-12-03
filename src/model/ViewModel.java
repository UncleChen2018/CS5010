package model;

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

}
