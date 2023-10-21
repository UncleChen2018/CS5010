package controller;

import java.io.IOException;

import model.GameModel;

public interface SimpleCommand {
  void execute(GameModel model) throws IOException;
}
