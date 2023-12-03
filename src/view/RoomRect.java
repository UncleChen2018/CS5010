package view;

import java.awt.Rectangle;

public class RoomRect {
  private int index;
  private Rectangle bounds;
  private boolean hasPlayer;

  public RoomRect(int index, Rectangle bounds) {
    this.index = index;
    this.bounds = bounds;
    this.hasPlayer = false;
  }

  public int getIndex() {
    return index;
  }

  public Rectangle getBounds() {
    return bounds;
  }

  public boolean hasPlayer() {
    return hasPlayer;
  }

  public void setPlayer(boolean hasPlayer) {
    this.hasPlayer = hasPlayer;
  }
}
