package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.GameModel;
import model.MockModel;
import org.junit.Before;
import org.junit.Test;

/**
 * In this test, computer use varargs.
 */
public class CommandControllerTest {
  private Readable worldData;

  /**
   * Set up the basic input and expected out put.
   */
  @Before
  public void setupTest() {
    worldData = new StringReader("36 30 Doctor Lucky's Mansion\n" + "50 Doctor Lucky\n"
        + "Fortune the Cat\n" + "21\n" + "22 19 23 26 Armory\n" + "16 21 21 28 Billiard Room\n"
        + "28 0 35 5 Carriage House\n" + "12 11 21 20 Dining Hall\n" + "22 13 25 18 Drawing Room\n"
        + "26 13 27 18 Foyer\n" + "28 26 35 29 Green House\n" + "30 20 35 25 Hedge Maze\n"
        + "16 3 21 10 Kitchen\n" + "0 3 5 8 Lancaster Room\n" + "4 23 9 28 Library\n"
        + "2 9 7 14 Lilac Room\n" + "2 15 7 22 Master Suite\n" + "0 23 3 28 Nursery\n"
        + "10 5 15 10 Parlor\n" + "28 12 35 19 Piazza\n" + "6 3 9 8 Servants' Quarters\n"
        + "8 11 11 20 Tennessee Room\n" + "10 21 15 26 Trophy Room\n" + "22 5 23 12 Wine Cellar\n"
        + "30 6 35 11 Winter Garden\n" + "20\n" + "8 3 Crepe Pan\n" + "4 2 Letter Opener\n"
        + "12 2 Shoe Horn\n" + "8 3 Sharp Knife\n" + "0 3 Revolver\n" + "15 3 Civil War Cannon\n"
        + "2 4 Chain Saw\n" + "16 2 Broom Stick\n" + "1 2 Billiard Cue\n" + "19 2 Rat Poison\n"
        + "6 2 Trowel\n" + "2 4 Big Red Hammer\n" + "6 2 Pinking Shears\n" + "18 3 Duck Decoy\n"
        + "13 2 Bad Cream\n" + "18 2 Monkey Hand\n" + "11 2 Tight Hat\n" + "19 2 Piece of Rope\n"
        + "9 3 Silken Cord\n" + "7 2 Loud Noise\n");

  }

  @Test
  public void testConstructorValidArguments() {
    // Arrange
    Readable in = new StringReader("input");
    Appendable out = new StringBuilder();
    Readable worldSource = new StringReader("world");
    int turnLimit = 10;

    // Act
    CommandController controller = new CommandController(in, out, worldSource, turnLimit);

    // Assert
    assertNotNull(controller);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullReadable() {
    // Arrange
    Readable in = null;
    Appendable out = new StringBuilder();
    Readable worldSource = new StringReader("world");
    int turnLimit = 10;

    // Act
    new CommandController(in, out, worldSource, turnLimit);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullAppendable() {
    // Arrange
    Readable in = new StringReader("input");
    Appendable out = null;
    Readable worldSource = new StringReader("world");
    int turnLimit = 10;

    // Act
    new CommandController(in, out, worldSource, turnLimit);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullWorldSource() {
    // Arrange
    Readable in = new StringReader("input");
    Appendable out = new StringBuilder();
    Readable worldSource = null;
    int turnLimit = 10;

    // Act
    new CommandController(in, out, worldSource, turnLimit);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNegativeTurnLimit() {
    // Arrange
    Readable in = new StringReader("input");
    Appendable out = new StringBuilder();
    Readable worldSource = new StringReader("world");
    int turnLimit = -10;

    // Act
    new CommandController(in, out, worldSource, turnLimit);
  }

  @Test

  public void testSetUpWorldAndAddPlayer() {
    // name location capacity control [confirm add] [add more]
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" + "7\n" // quit game
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    assertTrue(log.toString().contains("setupNewWorld called"));
    // assertEquals(baseOutput, out.toString());

    // Test add Human player
    String expectedLog = "addNewPlayer called, name = Jimmy, initLocation = 0,"
        + " capacity = 2, isHumanControl = true \n";
    assertTrue(log.toString().contains(expectedLog));
    // Test add Computer player
    expectedLog = "addNewPlayer called, name = Ai, initLocation = 0,"
        + " capacity = 1, isHumanControl = false \n";
    assertTrue(log.toString().contains(expectedLog));

  }

  // Test player without name.
  @Test
  public void testAddInValidPlayerName() {
    // name location capacity control [confirm add] [add more]
    // Add no name
    String inputString = "\n" + "\n" + "Jimmy\n0\n2\n\n\n" + "\n" + "7\n" // quit game
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 1);
    controller.start(mockModel);
    // Test add player without name.
    String expectedOutput = "Name can not be blank";
    assertTrue(out.toString().contains(expectedOutput));
    // Test add Computer player
    // assertEquals(baseOutput, out.toString());
    // assertEquals(baseOutput, log.toString());

  }

  @Test
  public void testAddInValidPlayerLocation() {
    // name location capacity control [confirm add] [add more]
    String inputString = "\n" + "Jimmy\n" // right name
        + "wx\n" + "-2\n" + "22\n" // three wrong enter
        + "0\n2\n\n\n" + "\n" + "7\n" // quit game
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test player location is not an integer.
    String expectedOutput = "Invalid location input wx, must be valid integer";
    assertTrue(out.toString().contains(expectedOutput));
    // Test player location is less than zero.
    expectedOutput = "Number -2 is not between 0 and 20";
    assertTrue(out.toString().contains(expectedOutput));
    // Test player location too large.
    expectedOutput = "Number 22 is not between 0 and 20";
    assertTrue(out.toString().contains(expectedOutput));

    // Test add Computer player
    // assertEquals(baseOutput, out.toString());
    // assertEquals(baseOutput, log.toString());

  }

  @Test
  public void testAddInValidPlayerCapacity() {
    // name location capacity control [confirm add] [add more]
    String inputString = "\n" + "Jimmy\n0\n" // right name
        + "ko^^&\n" + "0\n" + "-1\n" // wrong capacity
        + "2\n\n\n" + "\n" + "7\n" // quit game
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test player capacity is not an integer.
    String expectedOutput = "Invalid capacity input ko^^&, must be valid integer";
    assertTrue(out.toString().contains(expectedOutput));
    // Test player capacity is zero.
    expectedOutput = "Capacity 0 is no greater than 0, try again";
    assertTrue(out.toString().contains(expectedOutput));
    // Test player capacity is negative.
    expectedOutput = "Capacity -1 is no greater than 0, try again";
    assertTrue(out.toString().contains(expectedOutput));

    // Test add Computer player
    // assertEquals(baseOutput, out.toString());
    // assertEquals(baseOutput, log.toString());
  }

  @Test
  public void testNotAddPlayerToBeginGame() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\nn\n" + "\n" // not add player
        + "Jimmy\n0\n2\n\n\n" + "\n" + "7\n";
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test player capacity is not an integer.
    String expectedOutput = "Set at least one player before the game begins";
    assertTrue(out.toString().contains(expectedOutput));
    // Test add Computer player
    // assertEquals(baseOutput, out.toString());
    // assertEquals(baseOutput, log.toString());

  }

  @Test
  public void testValidMoveNeighbor() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "1\n3\n" // Player 0 move from room 0 to neighbor room 3.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test out put the player moving action and result.
    String expectedOutput = "Player No.0 \"Jimmy\" try to move to No.3 \"Dining Hall\"\n"
        + "Move to neighbor successfully.";
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right move method
    // assertEquals(baseOutput, out.toString());
    String expectedLog = "setPlayerLocation called, playerIndex = 0, destLocation = 3";
    assertTrue(log.toString().contains(expectedLog));
    // assertEquals(expectedLog, log.toString());
    // test turn passed.
    expectedLog = "getCurrentPlayer called, turn = 2";
    assertTrue(log.toString().contains(expectedLog));

  }

  @Test
  public void testInValidMoveNeighbor() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "1\n" // Choose to move to neighbor
        + "rt\n" + "-1\n" + "5\n" // enter wrong choice.
        + "3\n" // Player 0 move from room 0 to neighbor room 3.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test not enter integer
    String expectedOutput = "Wrong format for an integer";
    assertTrue(out.toString().contains(expectedOutput));
    // Test not enter invalid room number
    expectedOutput = "Room index not valid";
    assertTrue(out.toString().contains(expectedOutput));
    // Test not enter neighbor room number
    expectedOutput = "Not a valid neighbor";
    assertTrue(out.toString().contains(expectedOutput));
    // assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testPickUp() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "2\n" // Choose to pick up item
        + "4\n" // Player 0 pick up item number 4.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test out put the player moving action and result.
    String expectedOutput = "Player No.0 \"Jimmy\" try to pick up No.4 \"Revolver\" Damage:3\n"
        + "Pick up successfully.";
    // assertEquals(baseOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right pickup method
    // assertEquals(baseOutput, out.toString());
    String expectedLog = "pickUpitem called, playerId = 0, itemId = 4";
    assertTrue(log.toString().contains(expectedLog));
    // assertEquals(expectedLog, log.toString());
    // assertEquals(baseOutput, out.toString());
    // test turn passed.
    expectedLog = "getCurrentPlayer called, turn = 2";
    assertTrue(log.toString().contains(expectedLog));

  }

  @Test
  public void testInValidPickUpItem() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "2\n" // Choose to pick up
        + "rt\n" + "-1\n" + "5\n" // enter wrong choice.
        + "4\n" // Player 0 pick up Item 4.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test not enter integer
    String expectedOutput = "Wrong format for an integer";
    // assertEquals(baseOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // Test not enter invalid room number
    expectedOutput = "Invalid item id -1";
    assertTrue(out.toString().contains(expectedOutput));
    // Test not enter neighbor room number
    expectedOutput = "No such item 5 in this room";
    assertTrue(out.toString().contains(expectedOutput));
    // assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testNotAllowedToPickUpRoomEmpty() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "\n" // Enter game,

        + "2\n" // Jimmy has Item capacity of 2 Choose to pick up
        + "4\n" // Player 0 pick up Item 4
        + "2\n" // try to pick up at the same room
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test pick up from empty room
    String expectedOutput = "Room has not item, choose other option";
    // assertEquals(baseOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testNotAllowedToPickUpWhenReachCapacity() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n8\n1\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "\n"

        + "2\n" // Choose to pick up
        + "0\n" // Player 0 pick up Item 4, and capacity full.
        + "2\n" // try to pick up at the same room, can pick up item 3.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test pick up from empty room
    String expectedOutput = "Item capacity reached, choose other option";
    // assertEquals(baseOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));

    // assertEquals(expectedLog, log.toString());
  }

  @Test
  public void testLookAround() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "3\n" // Choose to look around
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test out put the player moving action and result.
    String expectedOutput = "Player No.0 \"Jimmy\" try to look around from No.0 \"Armory\"";
    // assertEquals("", out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right pickup method
    // assertEquals(baseOutput, out.toString());
    // Test the look around calling its neighbor.
    String expectedLog = "queryRoomDetails called, location = 1\n";
    // assertEquals(expectedLog, log.toString());
    assertTrue(log.toString().contains(expectedLog));
    expectedLog = "queryRoomDetails called, location = 3\n";
    assertTrue(log.toString().contains(expectedLog));
    expectedLog = "queryRoomDetails called, location = 4\n";
    assertTrue(log.toString().contains(expectedLog));

    // test turn passed.
    expectedLog = "getCurrentPlayer called, turn = 2";
    assertTrue(log.toString().contains(expectedLog));
    // assertEquals(expectedLog, log.toString());
    // assertEquals(baseOutput, out.toString());

  }

  @Test
  public void testLookAroundWhenPetIn() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n1\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "3\n" // Choose to look around
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test out put the player moving action and result.
    String expectedOutput = "Player No.0 \"Jimmy\" try to look around from No.1 \"Billiard Room\"";
    //assertEquals("", out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right pickup method
    // assertEquals(baseOutput, out.toString());
    // Test the look around calling its neighbor.
    String expectedLog = "queryRoomDetails called, location = 0\n";
    // assertEquals(expectedLog, log.toString());
    assertFalse(log.toString().contains(expectedLog));
    expectedLog = "queryRoomDetails called, location = 3\n";
    assertTrue(log.toString().contains(expectedLog));
    expectedLog = "queryRoomDetails called, location = 18\n";
    assertTrue(log.toString().contains(expectedLog));

    // test turn passed.
    expectedLog = "getCurrentPlayer called, turn = 2";
    assertTrue(log.toString().contains(expectedLog));
    // assertEquals(expectedLog, log.toString());
    // assertEquals(baseOutput, out.toString());

  }

  @Test
  public void testMaxTurnGameEnding() {
    // name location capacity control [confirm add] [add more]

    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "2\n" // Choose to pick up item
        + "4\n" // Player 0 pick up item number 4.
    ; // No quit game.
    ;
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 2);
    controller.start(mockModel);
    // Test max turn reached.
    String expectedOutput = "Max turn reached";
    // assertEquals(baseOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    assertTrue(out.toString().contains("Game exited..."));
    // assertEquals(expectedLog, log.toString());

  }

  @Test
  public void testComputerMove() {
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "1\n3\n" // Player 0 move from room 0 to neighbor room 3.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // let computer pickup itme
    GameController controller = new CommandController(in, out, worldData, 100, 0, 1);
    controller.start(mockModel);
    // Test out put the Ai player move right.
    String expectedOutput = "Player No.1 \"Ai\" try to move to No.3 \"Dining Hall\"\n"
        + "Move to neighbor successfully.";
    // assertEquals(expectedOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right move method
    // assertEquals(baseOutput, out.toString());
    // String expectedLog = "setPlayerLocation called, playerIndex = 0, destLocation
    // = 3";
    // assertTrue(log.toString().contains(expectedLog));
    // // assertEquals(expectedLog, log.toString());
    // // test turn passed.
    // expectedLog = "getCurrentPlayer called, turn = 2";
    // assertTrue(log.toString().contains(expectedLog));

  }

  @Test
  public void testComputerPickUp() {
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "1\n3\n" // Player 0 move from room 0 to neighbor room 3.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // let computer pickup itme
    GameController controller = new CommandController(in, out, worldData, 100, 1, 4);
    controller.start(mockModel);
    // Test out put the Ai player pick up item.
    String expectedOutput = "Player No.1 \"Ai\" try to pick up No.4 \"Revolver\" Damage:3\n"
        + "Pick up successfully.";
    // assertEquals(expectedOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right move method
    // assertEquals(baseOutput, out.toString());
    // String expectedLog = "setPlayerLocation called, playerIndex = 0, destLocation
    // = 3";
    // assertTrue(log.toString().contains(expectedLog));
    // // assertEquals(expectedLog, log.toString());
    // // test turn passed.
    // expectedLog = "getCurrentPlayer called, turn = 2";
    // assertTrue(log.toString().contains(expectedLog));

  }

  @Test
  public void testComputerLookAround() {
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "y\nPenny\n3\n3\n\n\n"
        + "\n" // Enter game
        + "1\n3\n" // Player 0 move from room 0 to neighbor room 3.
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // let computer pickup itme
    GameController controller = new CommandController(in, out, worldData, 100, 2, 4);
    controller.start(mockModel);
    // Test out put the Ai player pick up item.
    String expectedOutput = "Player No.1 \"Ai\" try to look around from No.0 \"Armory\"\n"
        + "Computer look around result ommitted.";
    assertTrue(out.toString().contains(expectedOutput));

  }

  // Here the new test for attack, succeed without item.
  @Test
  public void testAttackSucceedWithouItem() {
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "\n" // Enter game
        + "4\n" // Player 0 attack with no item
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);

    GameController controller = new CommandController(in, out, worldData, 100, 2, 4);
    controller.start(mockModel);
    String expectedOutput = "Player No.0 \"Jimmy\" try to attack \"Doctor Lucky\" "
        + "using poking in the eye ...\n" + "" + "Attack successfully, target get damage of 1.";
    // assertEquals(expectedOutput, out.toString());

    assertTrue(out.toString().contains(expectedOutput));
    assertEquals(49, mockModel.getTargetHealth());
    String expectedLog = "attackTarget called, damage = 1";
    assertTrue(log.toString().contains(expectedLog));
    // assertEquals(expectedLog, log.toString());

  }

  @Test
  public void testAttackSucceedWithItem() {
    String inputString = "\n" + "Jimmy\n4\n2\n\n\n" + "\n" // Enter game
        + "2\n1\n" // Choose to pick up
        + "3\n3\n3\n" // wait 3 turns
        + "4\n1\n" // Player 0 attack
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);

    GameController controller = new CommandController(in, out, worldData, 100, 2, 4);
    controller.start(mockModel);
    // the output string should contain item and give damage of 2
    String expectedOutput = "Player No.0 \"Jimmy\" try to attack \"Doctor Lucky\""
        + " using Letter Opener ...\n" + "Attack successfully, target get damage of 2.";
    // assertEquals(expectedOutput, out.toString());

    assertTrue(out.toString().contains(expectedOutput));
    assertEquals(48, mockModel.getTargetHealth());
    String expectedLog = "attackTarget called, damage = 2";
    // assertEquals(expectedLog, log.toString());
    assertTrue(log.toString().contains(expectedLog));
    expectedLog = "removePlayerItem called, playerId = 0, itemId = 1";
    assertTrue(log.toString().contains(expectedLog));

  }

  @Test
  public void testAttackSucceedWithPetSeenByOthersNeighborRoom() {
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nPenny\n4\n3\n\n\n" + "\n" // Enter game
        + "4\n" // Player 0 attack with no item
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // let computer pickup item
    GameController controller = new CommandController(in, out, worldData, 100, 2, 4);
    controller.start(mockModel);
    // Test out put the AI player pick up item.
    String expectedOutput = "Player No.0 \"Jimmy\" try to attack \"Doctor Lucky\" "
        + "using poking in the eye ...\n" + "" + "Attack successfully, target get damage of 1.";
    // assertEquals(expectedOutput, out.toString());
    // String expectedLog = "XX";
    assertTrue(out.toString().contains(expectedOutput));
    String expectedLog = "attackTarget called, damage = 1";
    // assertEquals(expectedLog, log.toString());
    assertTrue(log.toString().contains(expectedLog));

  }

  @Test
  public void testAttackFailedNoTargetIn() {
    String inputString = "\n" + "Jimmy\n3\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "\n" // Enter game
        + "4\n" // Player 0 attack with no item
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);

    // let computer pickup item
    GameController controller = new CommandController(in, out, worldData, 100, 2, 4);
    controller.start(mockModel);
    // Test out put the Ai player pick up item.
    String expectedOutput = "Target not in this room, cannot attack";
    // assertEquals(expectedOutput, out.toString());
    // String expectedLog = "XX";
    assertTrue(out.toString().contains(expectedOutput));

    // assertEquals(expectedLog, log.toString());

  }

  @Test
  public void testAttackFailedSeenByOthersSameRoom() {
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nAi\n0\n1\ny\n\n" + "\n" // Enter game
        + "4\n" // Player 0 attack with no item
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);

    // let computer pickup item
    GameController controller = new CommandController(in, out, worldData, 100, 2, 4);
    controller.start(mockModel);
    // Test out put the Ai player pick up item.
    String expectedOutput = "Player No.0 \"Jimmy\" try to attack \"Doctor Lucky\" "
        + "using poking in the eye ...\n" + "" + "Someone has seen you, attack has to stop...";
    // assertEquals(expectedOutput, out.toString());
    // String expectedLog = "XX";
    assertTrue(out.toString().contains(expectedOutput));

    // assertEquals(expectedLog, log.toString());

  }

  @Test
  public void testAttackFailedWithPetSeenByOthersSameRoom() {
    String inputString = "\n" + "Jimmy\n0\n2\n\n\n" + "y\nPenny\n1\n3\n\n\n" + "y\nJack\n1\n3\n\n\n"
        + "\n" // Enter game
        + "5\n1\n" // Player 0 move pet to room1
        + "4\n" // Player 1 attack with no item, with pet in it
        + "7\n"; // Quit game.
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // let computer pickup itme
    GameController controller = new CommandController(in, out, worldData, 100, 2, 4);
    controller.start(mockModel);
    // Test out put the Ai player pick up item.
    String expectedOutput = "Player No.1 \"Penny\" try to attack \"Doctor Lucky\" "
        + "using poking in the eye ...\n" + "" + "Someone has seen you, attack has to stop...";
    // assertEquals(expectedOutput, out.toString());
    // String expectedLog = "XX";
    assertTrue(out.toString().contains(expectedOutput));

    // assertEquals(expectedLog, log.toString());

  }

  // test pet travels using just Computer player.
  @Test
  public void testPetDfsTravel() {
    String inputString = "\n" + "Ai\n0\n1\ny\n\n" + "\n"; // Enter game

    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // let computer always look around
    GameController controller = new CommandController(in, out, worldData, 28, 2);
    controller.start(mockModel);
    // Test out put the Ai player pick up item.

    List<Integer> posList = new ArrayList<>();
    String patternString = "Pet:\\s*\\[.+pos = (\\d+)\\]";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(out.toString());

    while (matcher.find()) {
      int posValue = Integer.parseInt(matcher.group(1));
      posList.add(posValue);
    }

    List<Integer> expctedList = Arrays.asList(0, 1, 3, 4, 5, 15, 7, 6, 7, 15, 20, 2, 20, 15, 5, 4,
        19, 8, 14, 16, 9, 11, 12, 10, 13, 10, 18, 17);

    assertEquals(expctedList, posList);

    // String expectedLog = "XX";
    // assertTrue(out.toString().contains(expectedOutput));

    // assertEquals(expectedLog, log.toString());

  }

  // test pet travels after teleport.
  @Test
  public void testPetDfsTravelAfterBeenMoved() {
    String inputString = "\n" + "Ai\n0\n1\ny\n\n" + "\n"; // Enter game
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // let computer always look around

    int[] randomOrder = new int[31];
    randomOrder[0] = 3;
    for (int i = 1; i < 31; i++) {
      randomOrder[i] = 2;
    }
    GameController controller = new CommandController(in, out, worldData, 36, randomOrder);
    controller.start(mockModel);
    // Parse out the pet's trace into a list.
    List<Integer> posList = new ArrayList<>();
    String patternString = "Pet:\\s*\\[.+pos = (\\d+)\\]";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(out.toString());

    while (matcher.find()) {
      int posValue = Integer.parseInt(matcher.group(1));
      posList.add(posValue);
    }

    List<Integer> expctedList = Arrays.asList(0, 1, 2, 20, 15, 5, 4, 0, 1, 3, 8, 14, 16, 9, 11, 12,
        10, 13, 10, 18, 17, 18, 10, 12, 11, 9, 16, 14, 8, 19, 8, 3, 1, 2, 20, 15);

    // assertEquals("", out.toString());

    assertEquals(expctedList, posList);

    // assertTrue(out.toString().contains(expectedOutput))

    // String expectedLog = "XX";
    // assertTrue(out.toString().contains(expectedOutput));

    // assertEquals(expectedLog, log.toString());

  }

  // Test for winner
  @Test
  public void testWinnerEndGame() {
    worldData = new StringReader("36 30 Doctor Lucky's Mansion\n" + "1 Doctor Lucky\n"
        + "Fortune the Cat\n" + "21\n" + "22 19 23 26 Armory\n" + "16 21 21 28 Billiard Room\n"
        + "28 0 35 5 Carriage House\n" + "12 11 21 20 Dining Hall\n" + "22 13 25 18 Drawing Room\n"
        + "26 13 27 18 Foyer\n" + "28 26 35 29 Green House\n" + "30 20 35 25 Hedge Maze\n"
        + "16 3 21 10 Kitchen\n" + "0 3 5 8 Lancaster Room\n" + "4 23 9 28 Library\n"
        + "2 9 7 14 Lilac Room\n" + "2 15 7 22 Master Suite\n" + "0 23 3 28 Nursery\n"
        + "10 5 15 10 Parlor\n" + "28 12 35 19 Piazza\n" + "6 3 9 8 Servants' Quarters\n"
        + "8 11 11 20 Tennessee Room\n" + "10 21 15 26 Trophy Room\n" + "22 5 23 12 Wine Cellar\n"
        + "30 6 35 11 Winter Garden\n" + "20\n" + "8 3 Crepe Pan\n" + "4 2 Letter Opener\n"
        + "12 2 Shoe Horn\n" + "8 3 Sharp Knife\n" + "0 3 Revolver\n" + "15 3 Civil War Cannon\n"
        + "2 4 Chain Saw\n" + "16 2 Broom Stick\n" + "1 2 Billiard Cue\n" + "19 2 Rat Poison\n"
        + "6 2 Trowel\n" + "2 4 Big Red Hammer\n" + "6 2 Pinking Shears\n" + "18 3 Duck Decoy\n"
        + "13 2 Bad Cream\n" + "18 2 Monkey Hand\n" + "11 2 Tight Hat\n" + "19 2 Piece of Rope\n"
        + "9 3 Silken Cord\n" + "7 2 Loud Noise\n");
    String inputString = "\n" + "Ai\n0\n1\ny\n\n" + "\n"; // AI Enter game
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 36);
    controller.start(mockModel);
    String expectedOutput = "Opps, \"Doctor Lucky\" is dead, No.0 \"Ai\" is the winner!";
    // assertEquals("", out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    assertTrue(out.toString().contains("Game exited..."));
    // assertEquals(expectedLog, log.toString());
  }

  // TODO test computer always kill with the most powerful item.
  @Test
  public void testComputerAttackWithBestItem() {
    String inputString = "\n" + "Ai\n4\n2\ny\n\n" + "\n"; // Enter game
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    // [turn 1]: 1,0: pick up item at room 4; tar=0
    // [turn 2]: 0,0: move to place 0; tar=1
    // [turn 3]: 1,0: pick up item at room 0; tar=2
    // [turn 4]: 0,2: move to room 2; tar=3
    // [turn 5]: then kill will happen.
    int[] randomOrder = new int[] { 1, 0, 0, 0, 1, 0, 0, 2 };
    GameController controller = new CommandController(in, out, worldData, 10, randomOrder);

    controller.start(mockModel);
    // the output string should contain item and give damage of 2
    String expectedOutput = "Player No.0 \"Ai\" try to attack \"Doctor Lucky\" using Revolver ...\n"
        + "Attack successfully, target get damage of 3.\n"
        + "No.4 \"Revolver\" Damage:3 is removed.\n";
    // assertEquals(expectedOutput, out.toString());

    assertTrue(out.toString().contains(expectedOutput));
    assertEquals(47, mockModel.getTargetHealth());

    String expectedLog = "attackTarget called, damage = 3";
    // assertEquals(expectedLog, log.toString());
    assertTrue(log.toString().contains(expectedLog));
    expectedLog = "removePlayerItem called, playerId = 0, itemId = 4";
    assertTrue(log.toString().contains(expectedLog));

  }

}
