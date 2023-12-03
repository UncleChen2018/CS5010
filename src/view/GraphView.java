package view;

import controller.GameController;
import controller.GameControllerNew;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import javax.swing.plaf.basic.BasicMenuItemUI;

import model.ViewModel;

/**
 * The GUI view based on Jframe.
 */
public class GraphView implements GameView {

  // the view model, which is the read only subset of game model.
  ViewModel model;
  private JFrame frame;
  private JScrollPane worldScrollPane;
  private JScrollPane infoScrollPane;
  private JPanel playerInfoPanel;
  private JPanel resultPanel;
  private JLabel targetLabel;
  private JLabel[] playerLabels;

  private Rectangle roomList;
  
  /**
   * The default constructor.
   * 
   * @param model the read only model which support the data toe display.
   */
  public GraphView(ViewModel model) {
    this.model = model;
    initializeGUI();
  }

  private void initializeGUI() {
    frame = new JFrame("Kill Doctor Lucky");
    frame.setSize(1200, 800); // Initial size
    frame.setLocationRelativeTo(null); // in the center
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create Jpanel
    frame.setLayout(new GridBagLayout());
    // set the minimum size of the window.
    frame.setMinimumSize(new Dimension(300, 300));

    createWorldPanel();
    createInfoPanel();

  }

  private void createWorldPanel() {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 0.7; // 70% of the horizontal space for worldPanel
    constraints.weighty = 1.0; // Fill the whole height
    constraints.fill = GridBagConstraints.BOTH;

    worldScrollPane = new JScrollPane();
    worldScrollPane.setMinimumSize(new Dimension(210, 300));
    worldScrollPane.setBackground(Color.GRAY);
    worldScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    worldScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    ImageIcon imageIcon = new ImageIcon("./WorldMap.png");
    JLabel imageLabel = new JLabel(imageIcon);
    // worldScrollPane.add(imageLabel);
    worldScrollPane.setViewportView(imageLabel);

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
    
    //    infoScrollPane.setLayout(new GridLayout(2, 1)); // Two rows (top and bottom)
    //    infoScrollPane.setBackground(Color.GREEN);

    // Add components to display player information (top) and game result/world
    // information (bottom)
    infoPanelView.add(createPlayerInfoPanel());
    infoPanelView.add(createResultPanel());

    // Set the view component for the JScrollPane
    infoScrollPane.setViewportView(infoPanelView);

  }

  private JPanel createPlayerInfoPanel() {
    playerInfoPanel = new JPanel();
    playerInfoPanel.setBackground(Color.LIGHT_GRAY);

    // Add components to display player information
    // For example: JLabels or JTextAreas for player details

    return playerInfoPanel;
  }

  private JPanel createResultPanel() {
    resultPanel = new JPanel();
    resultPanel.setBackground(Color.WHITE);

    // Add components to display game result or world information
    // For example: JLabels or JTextAreas for result details

    return resultPanel;
  }
  
  
  @Override
  public void drawMap() {
    
    
  }
  
  private void getRoomRect() {
    for(int i=0; i<mode)
  }
  

  @Override
  public void configureView(GameControllerNew controller) {
    JMenuItem loadWorld = new JMenuItem("Load World");

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
            System.out.println(model.getWorldName());
          }
        }
      }
    });

    JMenuItem restartGame = new JMenuItem("Restart Game");
    restartGame.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    JMenuItem quitItem = new JMenuItem("Quit");
    quitItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.exitGame();
      }
    });

    JMenuBar menuBar = new JMenuBar();
    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    menuBar.add(loadWorld);
    menuBar.add(restartGame);
    menuBar.add(quitItem);
    frame.setJMenuBar(menuBar);

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
    //frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void refresh() {
    // TODO Auto-generated method stub

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
