package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.ViewModel;

class WorldPanel extends JPanel {
  private static final int MIN_SCALE_PER_CELL = 18;
  private static final int MAX_PLAYER_NUM = 10;
  private static final long serialVersionUID = 5374257364893332638L;
  public ArrayList<RoomRect> roomList;
  public CharcterMark targetMark;
  // Note, the index of the player mark should be the same as the player id.
  public ArrayList<CharcterMark> playerMarkList;
  private ViewModel model;

  /**
   *  Constructor for WorldPanel.
   */
  public WorldPanel() {
    this.roomList = new ArrayList<>();
    // addMouseListener(new RoomClickListener());
    targetMark = new CharcterMark("./res/graph/targetIcon.png");
    playerMarkList = new ArrayList<>(MAX_PLAYER_NUM);
    this.setBackground(Color.WHITE);
    for (int i = 0; i < MAX_PLAYER_NUM; i++) {
      CharcterMark playerMarks = new CharcterMark("./res/graph/player" + i + ".png");
      playerMarkList.add(playerMarks);
    }

  }

  /**
   * set the model
   * @param model the model
   */
  public void setModel(ViewModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (roomList.size() == 0) {
      return;
    }

    int horizonCell = model.getWorldSize()[1];
    int verticalCell = model.getWorldSize()[0];

    // the x span
    int ratioX = getWidth() / horizonCell;
    // the y span
    int ratioY = getHeight() / verticalCell;

    int ratio = Math.max(Math.min(ratioX, ratioY), MIN_SCALE_PER_CELL);

    for (int i = 0; i < roomList.size(); i++) {
      RoomRect room = roomList.get(i);
      room.setBounds(ratio);

      // Fill the room with light green color if the pet is in
      if (model.getPetLocation() == i) {
        g.setColor(new Color(144, 238, 144)); // Light Green
        g.fillRect(room.getRealBounds().x, room.getRealBounds().y, room.getRealBounds().width,
            room.getRealBounds().height);
      }

      // Fill the room with pale pink color if the current player is in
      if (model.getMaxTurn() > 0) {
        int currentPlayerLocation = model.getPlayerLocation(model.getCurrentPlayer());
        if (currentPlayerLocation == i) {
          g.setColor(new Color(255, 182, 193)); // Pale Pink
          g.fillRect(room.getRealBounds().x, room.getRealBounds().y, room.getRealBounds().width,
              room.getRealBounds().height);
        }
      }

      g.setColor(Color.BLACK);
      g.drawString(String.valueOf(room.getIndex()), room.getRealBounds().x + 5,
          room.getRealBounds().y + 15);
      g.drawString(model.getRoomName(room.getIndex()), room.getRealBounds().x + 5,
          room.getRealBounds().y + 30);

      g.drawRect(room.getRealBounds().x, room.getRealBounds().y, room.getRealBounds().width,
          room.getRealBounds().height);

      drawAllMarks(room, ratio, g);

    }

    // here make sure at least keep the min size to show the rectangles full.
    int minWidth = MIN_SCALE_PER_CELL * horizonCell;
    int minHeight = MIN_SCALE_PER_CELL * verticalCell;

    setPreferredSize(new Dimension(minWidth, minHeight));
    revalidate();
  }

  /**
   * get the room rectangle list
   * @param model the model
   * @return the room rectangle list
   */
  public ArrayList<RoomRect> getRoomRect(ViewModel model) {
    roomList = new ArrayList<>();
    for (int i = 0; i < model.getRoomCount(); i++) {
      int[] rect = model.getRoomRect(i);
      int x1 = rect[1];
      int y1 = rect[0];
      int x2 = rect[3];
      int y2 = rect[2];
      int rectX = x1;
      int rectY = y1;
      int rectWidth = (x2 - x1);
      int rectHeight = (y2 - y1);
      roomList.add(new RoomRect(i, new Rectangle(rectX, rectY, rectWidth, rectHeight)));
    }
    return roomList;

  }

  public class RoomRect {
    public final Rectangle bounds;
    private int index;
    private Rectangle realBounds;
    /**
     * Constructor for RoomRect.
     * @param index the index
     * @param bounds the bounds
     */
    public RoomRect(int index, Rectangle bounds) {
      this.index = index;
      this.bounds = bounds;
      this.realBounds = new Rectangle();

    }

    /**
     * get the room index
     * @return  the room index
     */
    public int getIndex() {
      return index;
    }

    /**
     * get the real bounds to draw
     * @return the real bounds to draw
     */
    public Rectangle getRealBounds() {
      return realBounds;
    }

    /**
     * set the bounds
     * @param ratio the ratio
     */
    public void setBounds(int ratio) {
      realBounds.x = bounds.x * ratio;
      realBounds.y = bounds.y * ratio;
      realBounds.width = bounds.width * ratio;
      realBounds.height = bounds.height * ratio;

    }

    /**
     * check if the point is in the room
     * @param point the point
     * @return true if the point is in the room
     */
    public boolean containsPoint(Point point) {
      return realBounds.contains(point);
    }

  }

  public class CharcterMark {
    public Image image;
    // public int size;
    public Rectangle bounds;

    /**
     * Constructor for CharcterMark.
     * @param imagePath the image path
     */
    public CharcterMark(String imagePath) {
      try {
        this.image = ImageIO.read(new File(imagePath));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    /**
     * set the bounds
     * @param x the x
     * @param y the y
     * @param size the size
     */
    public void setBounds(int x, int y, int size) {
      // this.image = this.image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
      this.bounds = new Rectangle(x, y, size, size);
    }

    /**
     * get the bounds
     * @param point the point
     * @return true if the point is in the bounds
     */
    public boolean containsPoint(Point point) {
      return bounds.contains(point);
    }

    /**
     * draw the image
     * @param g the graphics
     */
    public void draw(Graphics g) {
      g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
    }

  }

   /**
     * get the stored room rectangle list
     */
  public ArrayList<RoomRect> getStoredRoomRect() {
    return roomList;
  }

  // draw icon number N in room with index
  private void drawAllMarks(RoomRect room, int ratio, Graphics g) {
    int location = room.getIndex();

    ArrayList<CharcterMark> toDrawList = new ArrayList<>();
    if (model.getTargetLocation() == location) {
      toDrawList.add(targetMark);
    }
    for (Integer i : model.getRoomCharacter(location)) {
      toDrawList.add(playerMarkList.get(i));
    }
    for (int iconToDraw = 0; iconToDraw < toDrawList.size(); iconToDraw++) {
      int x = iconToDraw % room.bounds.width * ratio + room.getRealBounds().x;
      int y = iconToDraw / room.bounds.width * ratio + room.getRealBounds().y;

      toDrawList.get(iconToDraw).setBounds(x, y, ratio);
      toDrawList.get(iconToDraw).draw(g);
    }

  }

}
