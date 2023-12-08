package view;

import controller.GameControllerNew;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.ViewModel;

/**
 * The dialog to set max turn.
 */
public class SetMaxTurnDialog extends JDialog {

  private static final long serialVersionUID = -1013167432704287870L;
  private JTextField maxTurnsField;
  private JButton setButton;

  /**
   * Constructor for SetMaxTurnDialog.
   * 
   * @param parentFrame the parent frame
   * @param model       the view model
   * @param controller  the game controller
   */
  public SetMaxTurnDialog(JFrame parentFrame, ViewModel model, GameControllerNew controller) {
    super(parentFrame, "Set Max Turns", true);
    initComponents();
    addComponents();
    addListener(controller);
    setInputVerifier();
    pack();
    setLocationRelativeTo(parentFrame);
  }

  private void initComponents() {
    maxTurnsField = new JTextField();
    setButton = new JButton("Set");

    // Set initial width for text field
    maxTurnsField.setColumns(10);
  }

  private void addComponents() {
    setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.insets = new Insets(5, 5, 5, 5);

    // Label and Text Field for Max Turns
    constraints.gridx = 0;
    constraints.gridy = 0;
    add(new JLabel("Max Turns:"), constraints);

    constraints.gridx = 1;
    add(maxTurnsField, constraints);

    // Add Set Button
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 2;
    constraints.anchor = GridBagConstraints.CENTER;
    add(setButton, constraints);
  }

  private void addListener(GameControllerNew controller) {
    setButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          int maxTurn = getMaxTurn();
          boolean settingSuccessfully = controller.setMaxTurn(maxTurn);

          if (settingSuccessfully) {
            setVisible(false);
            dispose();
          } else {
            // Dialog for unsuccessful setting
            JOptionPane.showMessageDialog(SetMaxTurnDialog.this,
                "Please enter an integer greater than 0 for max turns.", "Setting Error",
                JOptionPane.ERROR_MESSAGE);
            maxTurnsField.setText("");
          }
        } catch (NumberFormatException ex) {
          // Dialog for non-integer input
          JOptionPane.showMessageDialog(SetMaxTurnDialog.this,
              "Invalid input. Please enter a valid integer for max turns.", "Setting Error",
              JOptionPane.ERROR_MESSAGE);
          maxTurnsField.setText("");
        }
      }

    });

  }

  private void setInputVerifier() {
    maxTurnsField.setInputVerifier(new MaxTurnVerifier());

  }

  private class MaxTurnVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
      JTextField textField = (JTextField) input;
      String text = textField.getText().trim();

      if (text.isEmpty()) {
        showError("Max turns cannot be empty.");
        return false;
      }

      try {
        int maxTurns = Integer.parseInt(text);
        if (maxTurns > 0) {
          return true;
        } else {
          showError("Max turns must be greater than 0.");
          maxTurnsField.setText("");
          return false;
        }
      } catch (NumberFormatException e) {
        showError("Invalid input. Please enter a valid integer.");
        maxTurnsField.setText("");
        return false; // Not a valid integer
      }
    }

    private void showError(String message) {
      JOptionPane.showMessageDialog(SetMaxTurnDialog.this, message, "Input Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Get the max turn.
   * @return the max turn.
   */
  public Integer getMaxTurn() {
    return Integer.parseInt(maxTurnsField.getText().trim());
  }
}
//
//  public static void main(String[] args) {
//    SwingUtilities.invokeLater(() -> {
//      JFrame frame = new JFrame("Main Frame");
//      frame.setSize(400, 300);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//      JButton openDialogButton = new JButton("Open Set Max Turns Dialog");
//      openDialogButton.addActionListener(new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//          SetMaxTurnDialog dialog = new SetMaxTurnDialog(frame);
//          dialog.setVisible(true);
//        }
//      });
//
//      frame.add(openDialogButton);
//      frame.setLocationRelativeTo(null);
//      frame.setVisible(true);
//    });
//  }
//}
