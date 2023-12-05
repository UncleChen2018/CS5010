package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerInfoPanel extends JPanel {
  private JTextField playerNameField;
  private JTextField initialLocationField;
  private JTextField itemCapacityField;
  private JComboBox<String> controlModeComboBox;
  private JButton addButton;

  public PlayerInfoPanel() {
    initComponents();
    addComponents();
    addListeners();
  }

  private void initComponents() {
    playerNameField = new JTextField();
    initialLocationField = new JTextField();
    itemCapacityField = new JTextField();
    controlModeComboBox = new JComboBox<>(new String[] { "Human", "Computer" });
    addButton = new JButton("Add Player");
    
    // Set initial width for text fields
    playerNameField.setColumns(15);
    initialLocationField.setColumns(15);
    itemCapacityField.setColumns(15);
  }

  private void addComponents() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);

    // Label and Text Field for Player Name
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(new JLabel("Player Name:"), constraints);

    constraints.gridx = 1;
    add(playerNameField, constraints);

    // Label and Text Field for Initial Location
    constraints.gridx = 0;
    constraints.gridy = 1;
    add(new JLabel("Initial Location:"), constraints);

    constraints.gridx = 1;
    add(initialLocationField, constraints);

    // Label and Text Field for Item Capacity
    constraints.gridx = 0;
    constraints.gridy = 2;
    add(new JLabel("Item Capacity:"), constraints);

    constraints.gridx = 1;
    add(itemCapacityField, constraints);

    // Label and Combo Box for Control Mode
    constraints.gridx = 0;
    constraints.gridy = 3;
    add(new JLabel("Control Mode:"), constraints);

    constraints.gridx = 1;
    add(controlModeComboBox, constraints);

    // Add Button
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    add(addButton, constraints);
  }

  private void addListeners() {
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Perform checks and add player logic here
        String playerName = playerNameField.getText();
        String initialLocation = initialLocationField.getText();
        String itemCapacity = itemCapacityField.getText();
        String controlMode = (String) controlModeComboBox.getSelectedItem();

        // Add your logic to validate and add the player
        // For example, display an error message if fields are empty or invalid

        // Reset fields after adding a player
        playerNameField.setText("");
        initialLocationField.setText("");
        itemCapacityField.setText("");
      }
    });
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Player Info Panel");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel();
      frame.add(playerInfoPanel);

      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}
