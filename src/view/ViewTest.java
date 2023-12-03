package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class InnerPane extends JComponent {
  /**
  * 
  */
  private static final long serialVersionUID = -6860559824333647015L;
  private static final double RECTANGLE_RATIO = 0.9; // Adjust as needed
  private static final int GRID_SIZE = 10; // Number of grids in both rows and columns

  private int numberOfRectangles = 3; // Adjust as needed
  
  public InnerPane() {
    setLayout(new GridBagLayout());
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.BLUE);
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;

    int cellSize = Math.min(getWidth() / GRID_SIZE, getHeight() / GRID_SIZE);

    int width = getWidth();
    int height = getHeight();

    int minRectangleWidth = 300;
    int minRectangleHeight = 600;

    int rectangleWidth = Math.max(minRectangleWidth,
        (int) (width * RECTANGLE_RATIO / numberOfRectangles));
    int rectangleHeight = Math.max(minRectangleHeight, (int) (height * RECTANGLE_RATIO));

    for (int i = 0; i < numberOfRectangles; i++) {
      int x = (int) (i * (width * RECTANGLE_RATIO / numberOfRectangles))
          + (int) (width * (1 - RECTANGLE_RATIO) / 2);
      int y = height / 2 - rectangleHeight / 2;

      g2d.drawRect(x, y, rectangleWidth, rectangleHeight);
    }
  }
}

public class ViewTest {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Resizable Rectangles Example");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JScrollPane scrollPane = new JScrollPane(new InnerPane());

      scrollPane.setBackground(Color.GRAY);
      scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

      // Add a component listener to the JScrollPane to repaint on size changes
      scrollPane.addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          scrollPane.getViewport().getView().repaint();
        }
      });

      frame.add(scrollPane);
      frame.setSize(400, 300);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }

  private static void resizeInnerPanel(InnerPane innerPanel, JScrollPane scrollPane) {
    int containerWidth = scrollPane.getWidth();
    int containerHeight = scrollPane.getHeight();

    int innerPanelWidth = (int) (containerWidth * 0.9);
    int innerPanelHeight = (int) (containerHeight * 1.0);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.fill = GridBagConstraints.BOTH;

    innerPanel.setPreferredSize(new Dimension(innerPanelWidth, innerPanelHeight));
    innerPanel.revalidate();
  }
}
