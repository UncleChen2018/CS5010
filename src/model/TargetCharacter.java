package model;

/**
 * The target person, with name, health, and current location. Can move to other
 * locations.
 */
public class TargetCharacter extends MovableCharacter{
  private int health;
  static int INITIAL_LOCATION = 0;

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
    return String.format("Target [name = \"%s\", location = %d, health = %d]", name, location, health);
  }
}
