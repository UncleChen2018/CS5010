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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
            // TODO: Pass the selected file to your controller method
            // controller.setWorldResource(null);
            System.out.println(selectedFile.getPath());
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
        // TODO:need the controller to do this.
        System.exit(0);
      }
    });

    JMenuBar menuBar = new JMenuBar();
    menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    menuBar.add(loadWorld);
    menuBar.add(restartGame);
    menuBar.add(quitItem);
    frame.setJMenuBar(menuBar);

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
