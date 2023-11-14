package model;

/**
 * Represents objects that can move between spaces in the game, existing
 * temporarily in only one place.
 */
public interface Movable {

  /**
   * Sets the location of the movable object to the specified space index.
   *
   * @param spaceIndex The index of the space to set as the new location.
   */
  void setLocation(int spaceIndex);

  /**
   * Retrieves the current location index of the movable object.
   *
   * @return The index of the current location.
   */
  int getLocation();

  /**
   * Retrieves the name of the movable object.
   *
   * @return A string representing the name of the object.
   */
  String getName();
}