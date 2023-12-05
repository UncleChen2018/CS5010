package view;

import controller.GameController;
import controller.GameControllerNew;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicMenuItemUI;

import model.ViewModel;

/**
 * The GUI view based on Jframe.
 */
public class GraphView implements GameView {

  // the view model, which is the read only subset of game model.
  ViewModel model;
  private JFrame frame;
  // TODO: maybe world panel should get its own class.
  private JScrollPane worldScrollPane;
  private JScrollPane infoScrollPane;

  private WorldPanel worldlPanel;

  private JPanel playerInfoPanel;
  private JPanel resultPanel;
  private JLabel resultLabel;

  private JLabel targetLabel;
  private JLabel[] playerLabels;

  private JMenuItem loadWorld;
  private JMenuItem restartGame;
  private JMenuItem quitItem;

  private JMenuBar menuBar;

  private ArrayList<RoomRect> roomList;
  private JLabel playerLabel;

  private class RoomRect {
    public final Rectangle bounds;
    private int index;
    private Rectangle realBounds;
    private boolean hasPlayer;

    public RoomRect(int index, Rectangle bounds) {
      this.index = index;
      this.bounds = bounds;
      this.realBounds = new Rectangle();
      this.hasPlayer = false;
    }

    public int getIndex() {
      return index;
    }

    public Rectangle getRealBounds() {
      return realBounds;
    }

    public void setBounds(int ratio) {
      realBounds.x = bounds.x * ratio;
      realBounds.y = bounds.y * ratio;
      realBounds.width = bounds.width * ratio;
      realBounds.height = bounds.height * ratio;

    }

    public boolean containsPoint(Point point) {
      return realBounds.contains(point);
    }

  }

  class WorldPanel extends JPanel {
    private static final int MIN_SCALE_PER_CELL = 10;
    private static final int MAX_PLAYER_NUM = 10;
    private static final long serialVersionUID = 5374257364893332638L;
    private ArrayList<RoomRect> roomList;
    private CharcterMark targetMark;
    // Note, the index of the player mark should be the same as the player id.
    private ArrayList<CharcterMark> playerMarkList;

    public WorldPanel() {
      this.roomList = new ArrayList<>();
      // addMouseListener(new RoomClickListener());
      targetMark = new CharcterMark("./res/graph/targetIcon.png");
      playerMarkList = new ArrayList<>(MAX_PLAYER_NUM);
      for (int i = 0; i < MAX_PLAYER_NUM; i++) {
        CharcterMark playerMarks = new CharcterMark("./res/graph/player" + i + ".png");
        playerMarkList.add(playerMarks);
      }
      System.out.println("Marks finished");
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
      int iconSize = ratio;

      for (int i = 0; i < roomList.size(); i++) {
        RoomRect room = roomList.get(i);
        room.setBounds(ratio);
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

    private class CharcterMark {
      public Image image;
      // public int size;
      public Rectangle bounds;

      public CharcterMark(String imagePath) {
        try {
          this.image = ImageIO.read(new File(imagePath));
          System.out.println(imagePath);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      public void setBounds(int x, int y, int size) {
        // this.image = this.image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        this.bounds = new Rectangle(x, y, size, size);
      }

      public boolean containsPoint(Point point) {
        return bounds.contains(point);
      }

      public void draw(Graphics g) {
        g.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
      }
    }

    public ArrayList<RoomRect> getStoredRoomRect() {
      return roomList;
    }

    // draw icon number N in room with index
    // TODO: the room should know its target and player list.
    private void drawAllMarks(RoomRect room, int ratio, Graphics g) {
      int location = room.getIndex();
      int marksToDraw = 0;
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
        // System.out.println(String.format("In draw marks, %d, %d, %d", iconToDraw, x,
        // y));
      }

    }

  }

  /**
   * The default constructor.
   * 
   * @param model the read only model which support the data toe display.
   */
  public GraphView(ViewModel model) {
    this.model = model;
    initializeFrame();
  }

  private void initializeFrame() {
    frame = new JFrame("Kill Doctor Lucky");
    frame.setSize(1200, 800); // Initial size
    frame.setLocationRelativeTo(null); // in the center
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create Jpanel
    frame.setLayout(new GridBagLayout());
    // set the minimum size of the window.
    frame.setMinimumSize(new Dimension(300, 300));
    // JPanel menuBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    createMenuBar();

    createMenuBar();

    createWorldPanel();
    createInfoPanel();

  }

  private void createMenuBar() {
    loadWorld = new JMenuItem("Load World");
    restartGame = new JMenuItem("Restart Game");
    quitItem = new JMenuItem("Quit");

    menuBar = new JMenuBar();
    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    menuBar.add(loadWorld);
    menuBar.add(restartGame);
    menuBar.add(quitItem);
    frame.setJMenuBar(menuBar);

  }

  private void createWorldPanel() {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 0.7; // 70% of the horizontal space for worldPanel
    constraints.weighty = 1.0; // Fill the whole height
    constraints.fill = GridBagConstraints.BOTH;

    worldlPanel = new WorldPanel();
    worldlPanel.setMinimumSize(new Dimension(300, 300)); // Set minimum size

    worldScrollPane = new JScrollPane(worldlPanel);
    worldScrollPane.setMinimumSize(new Dimension(300, 300));
    worldScrollPane.setBackground(Color.GRAY);
    worldScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    worldScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    frame.add(worldScrollPane, constraints);
  }

  private void createInfoPanel() {

    GridBagConstraints constraints = new GridBagConstraints();
    // constraints.gridx = 1; // Next column
    constraints.weightx = 0.3; // 30% of the horizontal space for infoPanel
    constraints.fill = GridBagConstraints.BOTH;

    infoScrollPane = new JScrollPane();
    infoScrollPane.setMinimumSize(new Dimension(90, 300));
    infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    frame.add(infoScrollPane, constraints);

    // Create a panel to be added to the JScrollPane's viewport
    JPanel infoPanelView = new JPanel();
    infoPanelView.setLayout(new GridLayout(2, 1)); // Two rows (top and bottom)
    infoPanelView.setBackground(Color.GREEN);

    // infoScrollPane.setLayout(new GridLayout(2, 1)); // Two rows (top and bottom)
    // infoScrollPane.setBackground(Color.GREEN);

    infoPanelView.add(createPlayerInfoPanel());
    infoPanelView.add(createResultPanel());

    // Set the view component for the JScrollPane
    infoScrollPane.setViewportView(infoPanelView);

  }

  private JPanel createPlayerInfoPanel() {
    playerInfoPanel = new JPanel();
    playerInfoPanel.setBackground(Color.LIGHT_GRAY);
    playerLabel = new JLabel("Player Information");
    playerInfoPanel.add(playerLabel);

    // Add components to display player information
    // For example: JLabels or JTextAreas for player details

    return playerInfoPanel;
  }

  private JPanel createResultPanel() {
    resultPanel = new JPanel();
    resultPanel.setBackground(Color.WHITE);
    resultLabel = new JLabel();
    resultPanel.add(resultLabel);

    // Add components to display game result or world information
    // For example: JLabels or JTextAreas for result details

    return resultPanel;
  }

  // draw the world based on the model.
  @Override
  public void drawMap() {
    worldlPanel.removeAll();
    worldlPanel.getRoomRect(model);
    // System.out.println(w.size());
    worldlPanel.revalidate();
    worldlPanel.repaint();
    worldlPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Point mousePoint = e.getPoint();
        for (RoomRect room : worldlPanel.getStoredRoomRect()) {
          if (room.containsPoint(mousePoint)) {
            // The mouse is hovering over this room
            String infoString = "Click over Room " + room.getIndex();
            System.out.println(infoString);
            resultLabel.setText(infoString);
            // Add your logic here to handle the hover event
            break; // Assuming only one room can be hovered at a time
          }
        }

        // TODO: add player click check
        if (worldlPanel.targetMark.containsPoint(mousePoint)) {
          playerLabel.setText(model.queryTargetDetails());
        }

        for (int i = 0; i < model.getPlayerCount(); i++) {
          System.out.println(String.format("check player %d", i));
          if (worldlPanel.playerMarkList.get(i).containsPoint(mousePoint)) {
            playerLabel.setText(model.queryPlayerDetails(i));
            System.out.println("Is in ");
          }
        }
      }
    });

    worldlPanel.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        Point mousePoint = e.getPoint();
        for (RoomRect room : worldlPanel.getStoredRoomRect()) {
          if (room.containsPoint(mousePoint)) {
            // System.out.println("Hovering over Room " + room.getIndex());
            // Add your logic here to handle the mouse move event
            break;
          }
        }
      }
    });
  }

  @Override
  public void displayAddPlayer(GameControllerNew controller) {
    JOptionPane.showMessageDialog(frame,
        "You must create at least one player before the game begins.", "No Players",
        JOptionPane.INFORMATION_MESSAGE);
    CreatePlayerDialog dialog = new CreatePlayerDialog(frame, model, controller);
    dialog.setVisible(true);

  }

  @Override
  public void configureView(GameControllerNew controller) {
    loadWorld.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser("./");

        // Create a file filter to show only .txt files
        FileFilter txtFilter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
        fileChooser.setFileFilter(txtFilter);

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
          // User selected a file
          File selectedFile = fileChooser.getSelectedFile();
          // Assuming you have a reference to your GameController
          if (controller != null) {

            controller.loadWorldFile(selectedFile.getPath());
            // System.out.println(model.getWorldName());
          }
        }
      }
    });

    restartGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    quitItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.exitGame();
      }
    });

  }

  @Override
  public void showWelcomeMessage() {
    String welcomeMessage = "Welcome to the Game!\n\n" + "This game was created by Eric Chen.\n"
        + "It is based on Java's Swing GUI library.\n" + "Enjoy playing!";
    JOptionPane.showMessageDialog(frame, welcomeMessage, "Welcome",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showFarewellMessage() {
    JOptionPane.showMessageDialog(frame, "Thank you for playing the game!", "Farewell",
        JOptionPane.INFORMATION_MESSAGE);
  }

  // make the GUI visible
  @Override
  public void display() {
    // frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void refresh() {
    frame.repaint();

  }

  @Override
  public Readable getInputSource() {
    return null;
  }

  @Override
  public Appendable getOutputDestination() {
    return null;
  }

  @Override
  public boolean requiresGuiOutput() {
    return true;
  }

}
