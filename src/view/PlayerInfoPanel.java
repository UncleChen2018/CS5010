package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *  The panel to display player information.
 */
public class PlayerInfoPanel extends JPanel {

  private static final long serialVersionUID = 1200975204838502497L;
  private JTextField playerNameField;
  private JTextField initialLocationField;
  private JTextField itemCapacityField;
  private JComboBox<String> controlModeComboBox;
  private JButton addButton;

  /**
   *  Constructor for PlayerInfoPanel.
   */
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
        playerNameField.getText();
        initialLocationField.getText();
        itemCapacityField.getText();
        controlModeComboBox.getSelectedItem();

        // Add your logic to validate and add the player
        // For example, display an error message if fields are empty or invalid

        // Reset fields after adding a player
        playerNameField.setText("");
        initialLocationField.setText("");
        itemCapacityField.setText("");
      }
    });
  }

}
