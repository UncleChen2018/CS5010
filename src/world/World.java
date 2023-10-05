package world;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.AbstractDocument.Content;

public class World {
  private String worldName;
  private static World theWorld;
  private int rowSize;
  private int colSize;

  public Character targetCharacter;
  public ArrayList<RoomSpace> spaceList;
  public ArrayList<Item> itemList;
  
  
  // TODO set neighbors to all rooms 
  public void setNeighbors() {
    // if two room share the same wall,then they are neighbor
    // every room has 4 wall, top: (x1, y1--> y2); right: (x1->x2, y2); bottom: (x2,y1->y2); left: (x1->x2, y1)
    // overlay can only happen: one's top with 
    // a. must have at lest one of same element in  (x1,y1,x2,y2)
    
    
    
  
  }

  public static World getWorldInstance() {
    if (theWorld == null) {
      theWorld = new World();
    }
    return theWorld;
  }

  // implement readable

  // setup world
  public void setupWorld(Readable source) {
    spaceList = new ArrayList<RoomSpace>();
    itemList = new ArrayList<Item>();
    
    Scanner scanner = new Scanner(source);
    // parse the World
    System.out.println("enter");
    this.rowSize = scanner.nextInt();
    
    this.colSize = scanner.nextInt();
    this.worldName = scanner.nextLine().trim();
    
    System.out.println(worldName);

    // parse the target character
    int fullHealth = scanner.nextInt();
    String roleName = scanner.nextLine().trim();
    this.targetCharacter = new Character(roleName, fullHealth);

    // parse the space number;
    int spaceNumber = scanner.nextInt();
    System.out.println(spaceNumber);
    // Fill the space array list
    for (int i = 0; i < spaceNumber; i++) {
      int row1 = scanner.nextInt();
      int col1 = scanner.nextInt();
      int row2 = scanner.nextInt();
      int col2 = scanner.nextInt();
      String roomName = scanner.nextLine().trim();
      spaceList.add(new RoomSpace(i, row1, col1, row2, col2, roomName));
      System.out.println(spaceList.get(i));
    }
    System.out.println(spaceList.get(0));

    // parse the item number;
    int itemNumber = scanner.nextInt();
    for (int i = 0; i < itemNumber; i++) {
      int spaceIndex = scanner.nextInt();
      int damage = scanner.nextInt();
      String itemName = scanner.nextLine().trim();
      Item newItem = new Item(itemName, damage);
      //System.out.println(String.format("Item: %s, damage: %d, roomIndex: %d", itemName, damage, spaceIndex));
      itemList.add(newItem);
      //System.out.println(itemList.get(i));  
      newItem.addToRoom(spaceList.get(spaceIndex));
    }

    System.out.println(spaceList.get(0).displaySpaceInfo());
    System.out.println(spaceList.toString().replace("~", "\n"));
    scanner.close();

  }
  
  // rerun {row, col} of the world.
  public int[] getWorldSize() {
    int[] sizeArray = {rowSize,colSize} ;
    return sizeArray;
  }
  
  public ArrayList<RoomSpace> getWorldSpace() {
    return spaceList;
  }

  public void setWorldName() {

  }

  public String getWorldName() {
    return worldName;
  }

}
