package model;

/**
 * The objects that can move between spaces in the game.
 * This mean they can exists temperately in only one place.
 */
public interface Movable {
  void setToSpace(int spaceIndex);
  RoomSpace getCurrentSpace();
  void moveNext();
  String getName();
}
