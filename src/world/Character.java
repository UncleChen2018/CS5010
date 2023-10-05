package world;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Character {
  private String name;
  private int health;
  private Space locatedSpaceIndex;
  

  /**
   * @param locatedSpaceIndex the locatedSpaceIndex to set
   */
  public void setLocatedSpaceIndex(Space locatedSpaceIndex) {
    this.locatedSpaceIndex = locatedSpaceIndex;
  }

  
  // TODO how to make the character in space 0
  public Character(String roleName, int fullHealth) {
    // TODO Auto-generated constructor stub
    name = roleName;
    health = fullHealth;
  }
  
  public void moveToSpace(Space toSpace) {
    locatedSpaceIndex = toSpace;
  }


  /**
   * @return the name
   */
  public String getName() {
    return name;
  }


  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * @return the health
   */
  public int getHealth() {
    return health;
  }


  /**
   * @param health the health to set
   */
  public void setHealth(int health) {
    this.health = health;
  }


  /**
   * @return the locatedSpaceIndex
   */
  public Space getLocatedSpaceIndex() {
    return locatedSpaceIndex;
  }
  
}
