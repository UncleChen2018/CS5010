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
  
}
