package model;

// TODO: add item interface.
/**
 * Items in the world. Store its damage, name, and room index where it's now in.
 * Items can also be taken by a player, who becomes its owner, and the location
 * is set to null for its no longer belong to certain space.
 */
public class Item {
  private final int itemId;
  private final int itemDamage;
  private final String itemName;
  private int storedLocation;
  private Player owner;

  /**
   * Initialize item.
   * 
   * @param itemName          name of the item.
   * @param itemDamage        damage of the item.
   * @param itemLocationIndex current located at which room.
   */
  public Item(int id, String itemName, int itemDamage, int itemLocationIndex) {
    this.itemId = id;
    this.itemDamage = itemDamage;
    this.itemName = itemName;
    this.storedLocation = itemLocationIndex;
    this.owner = null; // No one owns it at the start.
  }

  public String queryDetails() {
    if (owner != null) {
      return String.format("Item [itemId = %d, itemName = %s, itemDamage = %d, owner = \"%s\"]",
          itemId, itemName, itemDamage, owner.getName());

    } else {
      return String.format(
          "Item [itemId = %d, itemName = %s, itemDamage = %d, storedLocation = %s]", itemId,
          itemName, itemDamage, storedLocation);
    }
  }

  @Override
  public String toString() {
    return String.format("No.%d \"%s\" Damage:%d", itemId, itemName, itemDamage);
  }

  public int getItemDamage() {
    return itemDamage;
  }

  public String getItemName() {
    return itemName;
  }

  public int getStoredLoacation() {
    return storedLocation;
  }

  public void setStoredLoacation(int index) {
    this.storedLocation = index;
  }

  public Player getOwner() {
    return owner;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public String querryDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("-------------------Item DETAILS-------------------\n");
    stringBuilder.append("Item: ").append(this).append("\n").append("Damage: ").append(itemDamage)
        .append("\n").append("Location: ")
        .append(storedLocation != -1 ? storedLocation : "Be Taken").append("\n").append("Owner: ")
        .append(owner).append("\n").append("-------------------DETAILS END-------------------")
        .append("\n");

    return stringBuilder.toString();
  }

  public int getItemId() {
    return this.itemId;
  }

}
