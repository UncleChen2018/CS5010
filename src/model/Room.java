package model;

import java.util.ArrayList;

public interface Room {
  int getSpaceIndex();

  int[] getRoomRect();

  String getSpaceName();

  void addItem(Item item);

  ArrayList<Item> getSpaceItem();

  ArrayList<Room> getNeighbors();

  ArrayList<Room> getVisibles();

  ArrayList<Item> getItems();

  String getSpaceInfo();

  ArrayList<Movable> getCharacters();

  ArrayList<Movable> getCharacterList();

  void addCharacer(Movable character);

  void removeCharacter(Movable character);

  void setTargetIn();

  void setTargetOut();

  boolean hasTarget();

  void removeItem(Item item);

  String queryDetails();

  String queryRoomNeighbors();
}
