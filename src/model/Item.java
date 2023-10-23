package model;

/**
 * Interface for representing items in the game.
 */
public interface Item {

  String queryDetails();

  String toString();

  int getItemDamage();

  String getItemName();

  int getStoredLoacation();

  void setStoredLoacation(int index);

  Player getOwner();

  void setOwner(Player owner);

  String querryLocationDetails();

  int getItemId();

}
