package controller;

import model.GameModel;

public interface SimpleCommand {
  void execute(GameModel model);
}
