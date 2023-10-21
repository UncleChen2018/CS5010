package controller;


public abstract class TurnBaseCommand implements SimpleCommand {
  protected abstract void turnBegin();
  protected abstract void turnEnd();

}
