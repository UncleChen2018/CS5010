/**
 * 
 */
package model;

/**
 * 
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


}
