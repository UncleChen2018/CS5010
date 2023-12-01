package view;

import controller.GameController;
import controller.GameControllerNew;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
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
  private JPanel worldPanel;
  private JLabel targetLabel;
  private JLabel[] playerLabels;

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
    frame.setSize(800, 600); // Initial size
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    worldPanel = new JPanel();
    worldPanel.setLayout(new BorderLayout());

    targetLabel = new JLabel("Target Character");
    worldPanel.add(targetLabel, BorderLayout.CENTER);

    // Initialize player labels
    playerLabels = new JLabel[10];
    for (int i = 0; i < playerLabels.length; i++) {
      playerLabels[i] = new JLabel("Player " + (i + 1));
      worldPanel.add(playerLabels[i], BorderLayout.CENTER);
    }

    frame.add(worldPanel);
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
    String welcomeMessage = "Welcome to the Game!\n\n"
        + "This game was created by Eric Chen.\n"
        + "It is based on Java's Swing GUI library.\n"
        + "Enjoy playing!";
    JOptionPane optionPane = new JOptionPane(welcomeMessage,
        JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);

    JDialog dialog = optionPane.createDialog(frame, "Open Screen");
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    // Add a custom button to the dialog
    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> {
      dialog.dispose();
    });

    optionPane.setOptions(new Object[] {okButton});
    dialog.setContentPane(optionPane);

    // Make the dialog visible
    dialog.pack();
    dialog.setLocationRelativeTo(frame);
    dialog.setVisible(true);
    
  }

  @Override
  public void showFarewellMessage() {
    JOptionPane optionPane = new JOptionPane("Thank you for playing the game!",
        JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);

    JDialog dialog = optionPane.createDialog(frame, "Farewell");
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    // Add a custom button to the dialog
    JButton okButton = new JButton("OK");
    okButton.addActionListener(e -> {
      dialog.dispose();
      System.exit(0);
    });

    optionPane.setOptions(new Object[] {okButton});
    dialog.setContentPane(optionPane);

    // Make the dialog visible
    dialog.pack();
    dialog.setLocationRelativeTo(frame);
    dialog.setVisible(true);
  }

  // make the GUI visible
  @Override
  public void display() {
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
