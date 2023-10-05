package world;

import java.util.ArrayList;
import java.util.List;

public class RoomSpace implements Space {

  private int spaceIndex;
  private int x1;
  private int y1;
  private int x2;
  private int y2;

  private String name;
  private ArrayList<Item> itemList;

  public RoomSpace(int spaceIndex, int x1, int y1, int x2, int y2, String name) {
    this.spaceIndex = spaceIndex;
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.name = name;
    this.itemList = new ArrayList<Item>();
  }

  public void addItem(Item item) {
    itemList.add(item);
  }

  public String getSpaceName() {
    return name;
  }

  public int getSpaceIndex() {
    return spaceIndex;
  }

  public ArrayList<Item> getSpaceItem() {
    return itemList;
  }
  
  
  public int[] getRoomRect() {
    int []rect = {x1,y1,x2,y2};
    return rect;
  }

  @Override
  public String toString() {

    return String.format("No.%2d %s, coordinate(%d, %d, %d, %d), with items: %s", spaceIndex, name,
        x1, y1, x2, y2, itemList);
  }

  @Override
  public Space[] getNeighbors() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String displaySpaceInfo() {
    System.out.println(String.format("%s, with items: %s", name, itemList));
    return String.format("%s, with items: %s.", name, itemList);
  }

}
