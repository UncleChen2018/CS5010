package model;

/**
 * The target person, with name, health, and current location. Can move to other
 * locations.
 */
public class TargetCharacter extends MovableCharacter {
  static int INITIAL_LOCATION = 0;
  private int health;

  /**
   * Initialize the target character.
   * 
   * @param roleName   name of the character.
   * @param fullHealth the character's max health.
   */
  public TargetCharacter(String roleName, int fullHealth) {
    super(roleName, INITIAL_LOCATION);
    health = fullHealth;
  }

  /**
   * Retrieves the name of the room.
   *
   * @return A String representing the name of the room.
   */
  public String getName() {
    return name;
  }

  /**
   * Retrieves the health of the room.
   *
   * @return An integer representing the health of the room.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Sets the health of the room.
   *
   * @param health The integer value representing the health to be set for the
   *               room.
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Retrieves details about the room, including name, location, and health.
   *
   * @return A formatted String containing details about the room.
   */
  public String getDetails() {
    return String.format("Target [name = \"%s\", location = %d, health = %d]", name, location,
        health);
  }

  /**
   * Generates a formatted string containing the details of the target.
   *
   * @return The details of the target.
   */

  public String querryDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("-------------------Target DETAILS-------------------\n");
    stringBuilder.append("Target: ").append(this).append("\n").append("Health: ").append(health)
        .append("\n").append("Location: ").append(location).append("\n");
    return stringBuilder.toString();
  }
}
