package model;

/**
 * Items in the world. Store its damage, name, and room index where it's now in.
 */
public class Item {
  private final int itemDamage;
  private final String itemName;
  private int itemLocationIndex;

  /**
   * Initialize item.
   * @param itemName name of the item.
   * @param itemDamage damage of the item.
   * @param itemLocationIndex current located at which room.
   */
  public Item(String itemName, int itemDamage, int itemLocationIndex) {
    this.itemDamage = itemDamage;
    this.itemName = itemName;
    this.itemLocationIndex = itemLocationIndex;
  }

  @Override
  public String toString() {
    return String.format("%s: with damage: %d, in room No. %d", itemName, itemDamage,
        itemLocationIndex);
  }

  public int getItemDamage() {
    return itemDamage;
  }

  public String getItemName() {
    return itemName;
  }

  public int getItemLocationIndex() {
    return itemLocationIndex;
  }

  public void setItemLocationIndex(int index) {
    this.itemLocationIndex = index;
  }

}
