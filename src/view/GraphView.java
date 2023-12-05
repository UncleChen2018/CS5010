package view;

import controller.GameControllerNew;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
  private JTextArea playerLabel;

  private JPanel resultPanel;
  private JTextArea resultLabel;

  private JMenuItem loadWorld;
  private JMenuItem restartGame;
  private JMenuItem quitItem;

  private JMenuBar menuBar;

  private Font largerFont = new Font("Arial", Font.BOLD, 16);

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
    // worldlPanel.setMinimumSize(new Dimension(300, 300)); // Set minimum size

    worldScrollPane = new JScrollPane(worldlPanel);
    // worldScrollPane.setMinimumSize(new Dimension(300, 300));
    worldScrollPane.setBackground(Color.GRAY);
    worldScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    worldScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    frame.add(worldScrollPane, constraints);
  }

  private void createInfoPanel() {

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 0.3; // 30% of the horizontal space for infoPanel
    constraints.fill = GridBagConstraints.BOTH;

//    infoScrollPane = new JScrollPane();
//    //infoScrollPane.setMinimumSize(new Dimension(90, 300));
//    infoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//    infoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);



    // Create a panel to be added to the JScrollPane's viewport
    JPanel infoPanelView = new JPanel();
    infoPanelView.setLayout(new GridLayout(2, 1)); // Two rows (top and bottom)
    infoPanelView.setBackground(Color.GREEN);

    infoPanelView.add(createPlayerInfoPanel());
    infoPanelView.add(createResultPanel());

//    // Set the view component for the JScrollPane
//    infoScrollPane.setViewportView(infoPanelView);
//    frame.revalidate();
    
    frame.add(infoPanelView, constraints);

  }

  private JPanel createPlayerInfoPanel() {
    playerInfoPanel = new JPanel(new BorderLayout());
    playerInfoPanel.setBackground(Color.LIGHT_GRAY);
    playerLabel = new JTextArea("Player Information");
    playerLabel.setFont(largerFont);
    playerLabel.setEditable(false);
    
    
    JScrollPane playerScrollPane = new JScrollPane(playerLabel);
    playerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    playerInfoPanel.add(playerScrollPane, BorderLayout.CENTER);
    return playerInfoPanel;
   
  }

  private JPanel createResultPanel() {
    resultPanel = new JPanel(new BorderLayout());
    resultPanel.setBackground(Color.WHITE);
    resultLabel = new JTextArea("World Information");
    resultLabel.setFont(largerFont);
    resultLabel.setEditable(false);
    JScrollPane resultScrollPane = new JScrollPane(resultLabel);
    resultScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    resultPanel.add(resultScrollPane, BorderLayout.CENTER);

    return resultPanel;

  }

  // draw the world based on the model.
  @Override
  public void drawMap() {
    worldlPanel.removeAll();
    worldlPanel.setModel(model);
    worldlPanel.getRoomRect(model);
    worldlPanel.revalidate();
    worldlPanel.repaint();
    worldlPanel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Point mousePoint = e.getPoint();
        for (WorldPanel.RoomRect room : worldlPanel.getStoredRoomRect()) {
          if (room.containsPoint(mousePoint)) {
            resultLabel.setText(model.queryRoomDetails(room.getIndex()));
            break;
          }
        }

        if (worldlPanel.targetMark.containsPoint(mousePoint)) {
          playerLabel.setText(model.queryTargetDetails());
        }

        for (int i = 0; i < model.getPlayerCount(); i++) {
          System.out.println(String.format("check player %d", i));
          if (worldlPanel.playerMarkList.get(i).containsPoint(mousePoint)) {
            playerLabel.setText(model.queryPlayerDetails(i));
          }
        }
      }
    });

    worldlPanel.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
        Point mousePoint = e.getPoint();
        for (WorldPanel.RoomRect room : worldlPanel.getStoredRoomRect()) {
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
    PlayerCreationDialog dialog = new PlayerCreationDialog(frame, model, controller);
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
