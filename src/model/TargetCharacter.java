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
   * @return String
   */
  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

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
