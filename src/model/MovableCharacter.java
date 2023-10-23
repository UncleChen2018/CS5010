package model;

/**
 * Abstract class representing a movable character in the game.
 */
public abstract class MovableCharacter implements Movable {
  protected final String name;
  protected int location;

  public MovableCharacter(String name, int location) {
    this.name = name;
    this.location = location;
  }

  @Override
  public void setLocation(int spaceIndex) {
    this.location = spaceIndex;
  }

  @Override
  public String getName() {
    return this.name;
  }

  public int getLocation() {
    return location;
  }


  public String getDetails() {
    return String.format("MovableCharacter [name = %s, location = %d]", name, location);
  }

}
