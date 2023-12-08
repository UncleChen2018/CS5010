package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import model.GameModel;
import model.MockModel;
import view.GameView;
import view.MockGraphView;

public class CommandControllerNewTest {

  private GameModel mockModel;

  private GameView mockView;

  private GameControllerNew controller;
  private StringBuilder log;

  @Before
  public void setUp() {
    log = new StringBuilder();
    mockModel = new MockModel(log);
    mockView = new MockGraphView(log);
    controller = new CommandControllerNew(mockModel, mockView);

  }

  @Test
  public void testSetWorldResource() {
    // Test logic
    // Use Mockito to verify that the mockModel's setWorldResource method is called
    // when calling controller.setWorldResource(Readable worldSource)
    controller.setWorldResource(null);
    String expectedLog = "setWorldResource called\n";
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testLoadWorldFile() {
    // Test logic
    // Use Mockito to verify that the mockModel's setupNewWorld method is called
    // when calling controller.loadWorldFile(String filePath)
    controller.loadWorldFile("testFile.txt");
    String expectedLog = "setupNewWorld called, filePath = testFile.txt\n";
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testRestartGame() {
    // Test logic
    // Use Mockito to verify that the mockView's drawMap and displayAddPlayer
    // methods are called
    // when calling controller.restartGame()
    controller.restartGame();
    String expectedLog = "drawMap called\n" + "displayAddPlayer called\n";
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testGenerateComputerPlayerTurn() {
    // Test logic
    // Use Mockito to verify that the mockModel's methods are called appropriately
    // when calling controller.generateComputerPlayerTurn()
    controller.generateComputerPlayerTurn();
    String expectedLog = "generateComputerPlayerTurn called\n";
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testSetMaxTurn_ValidTurnLimit() {
    int validTurnLimit = 10;

    // Call setMaxTurn with a valid turn limit
    boolean result = controller.setMaxTurn(validTurnLimit);

    // Verify that the method returns true (indicating success)
    assertTrue(result);

  }

  @Test
  public void testSetMaxTurn_InvalidTurnLimit() {
    int invalidTurnLimit = -5;

    // Call setMaxTurn with an invalid turn limit
    boolean result = controller.setMaxTurn(invalidTurnLimit);

    // Verify that the method returns false (indicating failure)
    assertFalse(result);
  }
  
  @Test
  public void testSetMaxTurn_ZeroTurnLimit() {
      int zeroTurnLimit = 0;

      // Call setMaxTurn with a zero turn limit
      boolean result = controller.setMaxTurn(zeroTurnLimit);

      // Verify that the method returns false (indicating failure)
      assertFalse(result);
  }

  

  @Test
  public void testExecuteGame() {
    // Test logic
    // Use Mockito to verify that the mockView's showWelcomeMessage method is called
    // when calling controller.executeGame()
    controller.executeGame();
    String expectedLog = "showWelcomeMessage called\n";
    assertTrue(log.toString().contains(expectedLog));
  }

//  @Test
//  public void testExitGame() {
//    // Test logic
//    // Use Mockito to verify that the mockView's showFarewellMessage and System.exit
//    // methods are called
//    // when calling controller.exitGame()
//    controller.exitGame();
//    String expectedLog = "showFarewellMessage called\n" +
//                        "System.exit called\n";
//    assertTrue(log.toString().contains(expectedLog));
//  }

  @Test
  public void testStart() {
    // Test logic
    // Use Mockito to verify that the mockView's configureView method is called
    // when calling controller.start(GameModel model)
    controller.start(mockModel);
    String expectedLog = "configureView called\n";
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testSetNewPlayer() {
    // Test logic
    // Use Mockito to verify that the mockModel's addNewPlayer method is called
    // when calling controller.setNewPlayer(String playerName, int initialLocation,
    // int itemCapacity, String controlMode)
    controller.setNewPlayer("Ai", 0, 1, "HUMAN");
    String expectedLog = "addNewPlayer called, name = Ai, initLocation = 0, capacity = 1, isHumanControl = false\n";
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testProcessPlayerCommand() {

    controller.processPlayerCommand("command", 1);
    String expectedLog = "updateResult called, command = command, extraId = 1\n"
        + "refresh called\n";
    assertTrue(log.toString().contains(expectedLog));
  }
}
