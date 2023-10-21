package controller;

import java.util.Scanner;

public abstract class TurnBaseCommand implements SimpleCommand {
  protected abstract void turnBegin();
  protected abstract void turnEnd();
  protected  final Appendable out;
  protected final Scanner scan;
  
  protected TurnBaseCommand(Scanner scan, Appendable out) {
    this.scan = scan;
    this.out = out;
  }

}
