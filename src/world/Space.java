package world;


/**
 * Spaces in the game world. Every space knows its neighbor and 
 * can display its information.
 */
public interface Space {
  
  /**
   * @return array of Space, that are the neighbors of this space.
   */
  Space[] getNeighbors();
  
  /**
   * @return the space information as a String
   */
  String displaySpaceInfo();
}
