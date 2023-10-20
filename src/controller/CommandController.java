package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

/**
 * The implementation of controller using command pattern.
 * 
 */
public class CommandController implements GameController {

  private GameModel model;
  private final Appendable out;
  private final Scanner scan;
  private int MAX_TURN;
  private int currentTurn;
  private Readable worldData;

  // Build a controller, so the in, out, and MaxTurn is set.
  public CommandController(Readable in, Appendable out, Readable worldSource, int turnLimit) {
    if (in == null || out == null || worldSource == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    if (turnLimit<=0) {
      throw new IllegalArgumentException("Max turu must be positive");
    }
    this.out = out;
    scan = new Scanner(in);
    
    this.worldData = worldSource;   
    
    MAX_TURN = turnLimit;
    currentTurn = 0;
  }

  @Override
  public void start(GameModel model) {
  // TODO Auto-generated method stub
    this.model = model;
    System.out.print("Initializing the world map...");
    model.setupNewWorld(worldData);
    System.out.println("finished");
    
//    try {
//      String element = scan.next();
//      out.append("Hello world, " + element);
//    } catch (IOException ioe) {
//      throw new IllegalStateException("Append failed", ioe);
//    }
//        
//    while(currentTurn<MAX_TURN) {
//      FFFmakeTurns();
//    }
  }

  private void addPlayer(String name) {
    // Already exist player with same name.
    if (model.getPlayerTurn(name) != -1) {
      return;
    }
  }
  
  private void FFFmakeTurns() {
    // TODO 
  }

}
