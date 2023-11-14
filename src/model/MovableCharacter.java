package model;

/**
 * Abstract class representing a movable character in the game.
 */
public abstract class MovableCharacter implements Movable {
  protected final String name;
  protected int location;

  /**
   * Constructs a movable character with the specified name and initial location.
   *
   * @param name     The name of the movable character.
   * @param location The initial location of the movable character.
   */
  public MovableCharacter(String name, int location) {
    this.name = name;
    this.location = location;
  }

  /**
   * Sets the location of the movable character to the specified space index.
   *
   * @param spaceIndex The index of the space to set as the new location.
   */
  @Override
  public void setLocation(int spaceIndex) {
    this.location = spaceIndex;
  }

  /**
   * Retrieves the name of the movable character.
   *
   * @return A string representing the name of the character.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Retrieves the current location index of the movable character.
   *
   * @return The index of the current location.
   */
  @Override
  public int getLocation() {
    return location;
  }

  /**
   * Retrieves details about the movable character.
   *
   * @return A string containing details about the character.
   */
  public String getDetails() {
    return String.format("MovableCharacter [name = %s, location = %d]", name, location);
  }

  /**
   * Returns a string representation of the movable character.
   *
   * @return A string representation of the character.
   */
  @Override
  public String toString() {
    return String.format("\"%s\"", name);
  }
}
