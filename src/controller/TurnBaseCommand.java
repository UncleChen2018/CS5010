package controller;


public abstract class TurnBaseCommand implements SimpleCommand {
  // TODO: make common action into a turn begin and turn end.
  protected abstract void turnBegin();
  protected abstract void turnEnd();

}
