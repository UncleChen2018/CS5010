package controller;

import model.GameModel;

public interface SimpleCommand {
  String execute(GameModel model);
}
