package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public interface SimpleCommand {
  void execute(GameModel model, Scanner scan, Appendable out) throws IOException;
}
