package model;

/**
 * Interface for representing items in the game.
 */
public interface Item {
  /**
   * Retrieves details about the item.
   *
   * @return A string containing details about the item.
   */
  String queryDetails();

  /**
   * Returns a string representation of the item.
   *
   * @return A string representation of the item.
   */
  String toString();

  /**
   * Gets the damage value of the item.
   *
   * @return The damage value of the item.
   */
  int getItemDamage();

  /**
   * Gets the name of the item.
   *
   * @return The name of the item.
   */
  String getItemName();

  /**
   * Gets the stored location of the item.
   *
   * @return The stored location index of the item.
   */
  int getStoredLoacation();

  /**
   * Sets the stored location of the item.
   *
   * @param index The index representing the new stored location of the item.
   */
  void setStoredLoacation(int index);

  /**
   * Gets the owner of the item.
   *
   * @return The player who owns the item.
   */
  Player getOwner();

  /**
   * Sets the owner of the item.
   *
   * @param owner The player to set as the owner of the item.
   */
  void setOwner(Player owner);

  /**
   * Queries details about the location of the item.
   *
   * @return A string containing details about the location of the item.
   */
  String querryLocationDetails();

  /**
   * Gets the unique identifier of the item.
   *
   * @return The item's identifier.
   */
  int getItemId();

}
