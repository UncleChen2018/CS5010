/**
 * 
 */
package model;

/**
 * 
 */
public abstract class MovableCharacter implements Movable {
  protected String name;
  protected int location;
  
  
  public MovableCharacter(String name, int location) {
    this.name = name;
    this.location = location;
  }

  /**
   * 
   */
  public MovableCharacter() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void setToSpace(int spaceIndex) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public RoomSpace getCurrentSpace() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void moveNext() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return this.name;
  }

  public int getLocation() {
    return location;
  }

  public void setLocation(int location) {
    this.location = location;
  }

  public void setName(String name) {
    this.name = name;
  }

}
