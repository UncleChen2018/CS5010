package controller;


public abstract class TurnBaseCommand implements SimpleCommand {
  protected int playerId;
  protected TurnBaseCommand(int playerId) {
    this.playerId = playerId;
  }
}
