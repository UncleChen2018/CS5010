package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePlayerDialog extends JDialog {
  private JTextField playerNameField;
  private JTextField initialLocationField;
  private JTextField itemCapacityField;
  private JComboBox<String> controlModeComboBox;
  private JButton addButton;

  public CreatePlayerDialog(JFrame parentFrame) {
    super(parentFrame, "Create Player", true);
    initComponents();
    addComponents();
    addListeners();
    pack();
    setLocationRelativeTo(parentFrame);
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
        // Add logic to handle the button click (e.g., validate input, add player)
        // Then close the dialog
        System.out.println(getPlayerName());
        System.out.println(getInitialLocation());
        System.out.println(getItemCapacity());
        System.out.println(getControlMode());
        // setVisible(false);

        playerNameField.setText("");
        initialLocationField.setText("");
        itemCapacityField.setText("");
      }
    });
  }

  private class LocationVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
      JTextField textField = (JTextField) input;
      String text = textField.getText().trim();

      try {
        int location = Integer.parseInt(text);
        return location >= 0 && location <= 21;
      } catch (NumberFormatException e) {
        return false; // Not a valid integer
      }
    }
  }

  public String getPlayerName() {
    return playerNameField.getText();
  }

  public String getInitialLocation() {
    return initialLocationField.getText();
  }

  public String getItemCapacity() {
    return itemCapacityField.getText();
  }

  public String getControlMode() {
    return (String) controlModeComboBox.getSelectedItem();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Main Frame");
      frame.setSize(400, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JButton openDialogButton = new JButton("Open Create Player Dialog");
      openDialogButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          CreatePlayerDialog dialog = new CreatePlayerDialog(frame);
          dialog.setPreferredSize(new Dimension(300, 300));
          dialog.setVisible(true);
        }
      });

      frame.add(openDialogButton);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    });
  }
}
