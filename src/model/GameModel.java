package model;

import java.awt.image.BufferedImage;

/**
 * The game model interface. Including setup a brand new world, (including
 * rooms, target, character, player) and all methods that are used to get the
 * game process, as well as reveal details of the current states.
 * 
 */
public interface GameModel {
  void setupNewWorld(Readable source);

  BufferedImage drawWorld();
  
  String getDetails();
  String getName();
  
  int getPlayerCount();
  void setPlayerLocation(int playerNumber, int location);
  int getPlayerLocation(int playerId);
  boolean isNeighbor(int quest, int base);
  
  public int getPlayerTurn(String name);  
  void addNewPlayer(String name, int initLocation, int capacity, boolean isHumanControl)
      throws IllegalArgumentException;
  
  int getRoomCount();
}
