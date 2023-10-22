package controller;


public abstract class TurnBaseCommand implements SimpleCommand {
  protected int playerId;
  protected abstract void turnBegin();
  protected abstract void turnEnd();

  protected TurnBaseCommand(int playerId) {
    this.playerId = playerId;
  }
}
