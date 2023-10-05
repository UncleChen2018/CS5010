package world;

public class Item {
  private int itemDamage;
  private String itemName;
  private int itemLocationIndex;
  

  public Item(String itemName, int itemDamage) {
    this.itemDamage = itemDamage;
    this.itemName = itemName;
  }
  
  public void addToRoom(RoomSpace toSpace) {
    toSpace.addItem(this);
  }

  public String toString() {
    return itemName;
  }

  public int getItemDamage() {
    return itemDamage;
  }


  public void setItemDamage(int itemDamage) {
    this.itemDamage = itemDamage;
  }


  public String getItemName() {
    return itemName;
  }


  public void setItemName(String itemName) {
    this.itemName = itemName;
  }


  public int getItemLocationIndex() {
    return itemLocationIndex;
  }


  public void setItemLocationIndex(int itemLocationIndex) {
    this.itemLocationIndex = itemLocationIndex;
  }
  
  
  

}
