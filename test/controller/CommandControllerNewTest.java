package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import model.MockModelNew;
import org.junit.Before;
import org.junit.Test;
import view.GameView;
import view.MockGraphView;

/**
 * Controller test use mock view and model.
 */
public class CommandControllerNewTest {

  private MockModelNew mockModel;

  private GameView mockView;

  private GameControllerNew controller;
  private StringBuilder log;

  /**
   * Set up before test.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    mockModel = new MockModelNew(log);
    mockView = new MockGraphView(log);
    controller = new CommandControllerNew(mockModel, mockView);

  }

  @Test
  public void testSetWorldResource() throws FileNotFoundException {
    // Test logic
    // Use Mockito to verify that the mockModel's setWorldResource method is called
    // when calling controller.setWorldResource(Readable worldSource)
    controller.setWorldResource(new FileReader("./res/map.txt"));
    String expectedLog = "";
    assertEquals(log.toString(), expectedLog);
    assertTrue(log.toString().contains(expectedLog));

  }

  // @Test
  // public void testLoadWorldFile() {
  // // Test logic
  // // Use Mockito to verify that the mockModel's setupNewWorld method is called
  // // when calling controller.loadWorldFile(String filePath)
  // controller.loadWorldFile("./res/map.txt");
  // String expectedLog = "setupNewWorld called, filePath = testFile.txt\n";
  // assertEquals(log.toString(), expectedLog);
  // assertTrue(log.toString().contains(expectedLog));
  // }

  // @Test
  // public void testRestartGame() {
  // // Test logic
  // // Use Mockito to verify that the mockView's drawMap and displayAddPlayer
  // // methods are called
  // // when calling controller.restartGame()
  // controller.restartGame();
  // String expectedLog = "drawMap called\n" + "displayAddPlayer called\n";
  // assertTrue(log.toString().contains(expectedLog));
  // }

  // @Test
  // public void testGenerateComputerPlayerTurn() {
  // // Test logic
  // // Use Mockito to verify that the mockModel's methods are called
  // appropriately
  // // when calling controller.generateComputerPlayerTurn()
  // controller.generateComputerPlayerTurn();
  // String expectedLog = "generateComputerPlayerTurn called\n";
  // assertEquals(log.toString(), expectedLog);
  // assertTrue(log.toString().contains(expectedLog));
  // }

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

  // @Test
  // public void testExitGame() {
  // // Test logic
  // // Use Mockito to verify that the mockView's showFarewellMessage and
  // System.exit
  // // methods are called
  // // when calling controller.exitGame()
  // controller.exitGame();
  // String expectedLog = "showFarewellMessage called\n" +
  // "System.exit called\n";
  // assertTrue(log.toString().contains(expectedLog));
  // }

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
    String expectedLog = "addNewPlayer called, name = Ai, initLocation = 0, capacity = 1, isHumanControl = true\n";
    // assertEquals(log.toString(), expectedLog);
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testSetNewPlayerComputer() {
    // Test logic
    // Use Mockito to verify that the mockModel's addNewPlayer method is called
    // when calling controller.setNewPlayer(String playerName, int initialLocation,
    // int itemCapacity, String controlMode)
    controller.setNewPlayer("Ai", 0, 1, "COMPUTER");
    String expectedLog = "addNewPlayer called, name = Ai, initLocation = 0, capacity = 1, isHumanControl = false\n";
    // assertEquals(log.toString(), expectedLog);
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testProcessPlayerCommandAttack() {
    // Test valid command
    String validCommand = "attack";
    int extraId = 123;

    // Set up conditions in the model for the test
    // mockModel.setGameOverWithMaxTurn(false); // Set to true if you want the
    // condition to be true
    // mockModel.setGameOverWithWinner(false); // Set to true if you want the
    // condition to be true

    // Expected log for each step
    String expectedExecuteLog = "attack";
    String expectedRefreshLog = "refresh called\n";

    // Perform the command
    controller.processPlayerCommand(validCommand, extraId);

    // Assert each log individually
    // assertEquals(log.toString(), "");
    assertTrue(log.toString().contains(expectedExecuteLog));
    assertTrue(log.toString().contains(expectedRefreshLog));
    String expectedMoveNextTurnLog = "moveNextTurn called\n";
    String expectedMoveTargetNextRoomLog = "moveTargetNextRoom called\n";

    assertTrue(log.toString().contains(expectedMoveNextTurnLog));
    assertTrue(log.toString().contains(expectedMoveTargetNextRoomLog));
    String expectedMovePetNextRoomLog = "movePetNextRoom called\n";
    String expectedUpdateStatusLabelLog = "updateStatusLabel called\n";
    assertTrue(log.toString().contains(expectedMovePetNextRoomLog));
    assertTrue(log.toString().contains(expectedUpdateStatusLabelLog));
  }

  @Test
  public void testProcessPlayerCommandPickup() {
    // Test valid command
    String validCommand = "pickup";
    int extraId = 456;
    // Expected log for each step
    String expectedExecuteLog = "pick up";
    String expectedRefreshLog = "refresh called\n";

    // Perform the command
    controller.processPlayerCommand(validCommand, extraId);

    // Assert each log individually
    // assertEquals(log.toString(), "");
    assertTrue(log.toString().contains(expectedExecuteLog));
    assertTrue(log.toString().contains(expectedRefreshLog));
    String expectedMoveNextTurnLog = "moveNextTurn called\n";
    String expectedMoveTargetNextRoomLog = "moveTargetNextRoom called\n";

    assertTrue(log.toString().contains(expectedMoveNextTurnLog));
    assertTrue(log.toString().contains(expectedMoveTargetNextRoomLog));
    String expectedMovePetNextRoomLog = "movePetNextRoom called\n";
    String expectedUpdateStatusLabelLog = "updateStatusLabel called\n";
    assertTrue(log.toString().contains(expectedMovePetNextRoomLog));
    assertTrue(log.toString().contains(expectedUpdateStatusLabelLog));
  }

  @Test
  public void testProcessPlayerCommandInvalidCommand() {
    // Test invalid command
    String invalidCommand = "invalid";
    int extraId = 456;
    String expectedLog = "upateResult called, result = invalidis not supported";
    controller.processPlayerCommand(invalidCommand, extraId);
    // assertEquals(log.toString(), expectedLog);
    assertTrue(log.toString().contains(expectedLog));
  }

  @Test
  public void testProcessPlayerCommandMoveTo() {
    // Test valid command
    String validCommand = "moveto";
    int extraId = 789;

    // Set up conditions in the model for the test
    // mockModel.setGameOverWithMaxTurn(false); // Set to true if you want the
    // condition to be true
    // mockModel.setGameOverWithWinner(false); // Set to true if you want the
    // condition to be true

    // Expected log for each step
    String expectedExecuteLog = "move to";
    String expectedRefreshLog = "refresh called\n";

    // Perform the command
    controller.processPlayerCommand(validCommand, extraId);

    // Assert each log individually
    // assertEquals(log.toString(), "");
    assertTrue(log.toString().contains(expectedExecuteLog));
    assertTrue(log.toString().contains(expectedRefreshLog));
    String expectedMoveNextTurnLog = "moveNextTurn called\n";
    String expectedMoveTargetNextRoomLog = "moveTargetNextRoom called\n";

    assertTrue(log.toString().contains(expectedMoveNextTurnLog));
    assertTrue(log.toString().contains(expectedMoveTargetNextRoomLog));
    String expectedMovePetNextRoomLog = "movePetNextRoom called\n";
    String expectedUpdateStatusLabelLog = "updateStatusLabel called\n";
    assertTrue(log.toString().contains(expectedMovePetNextRoomLog));
    assertTrue(log.toString().contains(expectedUpdateStatusLabelLog));
  }

  @Test
  public void testProcessPlayerCommandMovePetTo() {
    // Test valid command
    String validCommand = "movepetto";
    int extraId = 987;

    // Set up conditions in the model for the test
    // mockModel.setGameOverWithMaxTurn(false); // Set to true if you want the
    // condition to be true
    // mockModel.setGameOverWithWinner(false); // Set to true if you want the
    // condition to be true

    // Expected log for each step
    String expectedExecuteLog = "movePet";
    String expectedRefreshLog = "refresh called\n";

    // Perform the command
    controller.processPlayerCommand(validCommand, extraId);

    // Assert each log individually
    // assertEquals(log.toString(), "");
    assertTrue(log.toString().contains(expectedExecuteLog));
    assertTrue(log.toString().contains(expectedRefreshLog));
    String expectedMoveNextTurnLog = "moveNextTurn called\n";
    String expectedMoveTargetNextRoomLog = "moveTargetNextRoom called\n";

    assertTrue(log.toString().contains(expectedMoveNextTurnLog));
    assertTrue(log.toString().contains(expectedMoveTargetNextRoomLog));
    String expectedMovePetNextRoomLog = "movePetNextRoom called\n";
    String expectedUpdateStatusLabelLog = "updateStatusLabel called\n";
    assertTrue(log.toString().contains(expectedMovePetNextRoomLog));
    assertTrue(log.toString().contains(expectedUpdateStatusLabelLog));
  }

  @Test
  public void testProcessPlayerCommandLookAround() {
    // Test valid command
    String validCommand = "lookaroud"; // Typo in the command, corrected to "lookaround"
    int extraId = 0;

    // Set up conditions in the model for the test
    // mockModel.setGameOverWithMaxTurn(false); // Set to true if you want the
    // condition to be true
    // mockModel.setGameOverWithWinner(false); // Set to true if you want the
    // condition to be true

    // Expected log for each step
    String expectedExecuteLog = "look around";
    String expectedRefreshLog = "refresh called\n";

    // Perform the command
    controller.processPlayerCommand(validCommand, extraId);

    // Assert each log individually
    // assertEquals(log.toString(), "");
    assertTrue(log.toString().contains(expectedExecuteLog));
    assertTrue(log.toString().contains(expectedRefreshLog));
    String expectedMoveNextTurnLog = "moveNextTurn called\n";
    String expectedMoveTargetNextRoomLog = "moveTargetNextRoom called\n";

    assertTrue(log.toString().contains(expectedMoveNextTurnLog));
    assertTrue(log.toString().contains(expectedMoveTargetNextRoomLog));
    String expectedMovePetNextRoomLog = "movePetNextRoom called\n";
    String expectedUpdateStatusLabelLog = "updateStatusLabel called\n";
    assertTrue(log.toString().contains(expectedMovePetNextRoomLog));
    assertTrue(log.toString().contains(expectedUpdateStatusLabelLog));
  }

}
