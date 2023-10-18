package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import turtle.control.TracingTurtleCommand;
import turtle.tracingmodel.SmarterTurtle;
import turtle.tracingmodel.TracingTurtleModel;

public class Controller {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    int maxTurn = 100;
    Scanner s = new Scanner(System.in);
    GameModel m = new World();
    Command cmd = null;
    for (int i = 0; i < maxTurn; i++) {
      Player p = getPlayer(i);
      String in = s.next();
      switch (in) {
        case "move":
        case "pick":
        case "look":
          break;
      }
      if (cmd != null) {
        cmd.go(m);
        cmd = null;
      }
    } catch (InputMismatchException ime) {
      System.out.println("Bad length to " + in);
    }
      
    }

  }
}
