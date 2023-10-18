package model;

/**
 * The target person, with name, health, and current location. Can move to other
 * locations.
 */
public class TargetCharacter {
  private final String name;
  private int health;
  private int locatedRoomIndex;

  /**
   * Initialize the target character.
   * 
   * @param roleName   name of the character.
   * @param fullHealth the character's max health.
   */
  public TargetCharacter(String roleName, int fullHealth) {
    name = roleName;
    health = fullHealth;
    locatedRoomIndex = 0;
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

  public int getLocatedRoomIndex() {
    return locatedRoomIndex;
  }

  public void setLocatedRoomIndex(int locatedSpaceIndex) {
    this.locatedRoomIndex = locatedSpaceIndex;
  }

  @Override
  public String toString() {
    String targerInfo = String.format("%s: with health point: %d, room index: %d.", name, health,
        locatedRoomIndex);
    return targerInfo;
  }

}
