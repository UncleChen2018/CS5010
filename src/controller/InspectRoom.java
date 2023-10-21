package controller;

import java.io.IOException;
import java.util.Scanner;

import model.GameModel;

public class InspectRoom implements SimpleCommand {
  private int location;

  public InspectRoom(int location) {
    this.location = location;
  }

  @Override
  public void execute(GameModel model, Scanner scan, Appendable out) throws IOException {
    out.append(model.queryRoomDetails(location));

  }

}
