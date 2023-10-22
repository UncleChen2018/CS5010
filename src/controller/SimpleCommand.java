package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public interface SimpleCommand {
  String execute(GameModel model) throws IOException;
}
