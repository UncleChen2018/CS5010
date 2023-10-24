package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import model.GameModel;
import model.MockModel;
import org.junit.Before;
import org.junit.Test;

/**
 * In this test, computer use varargs.
 */
public class CommandControllerTest {
  private Readable worldData;


  private String baseOutput; // base out put

  /**
   * Set up the basic input and expected out put.
   */
  @Before
  public void setupTest() {
    worldData = new StringReader("36 30 Doctor Lucky's Mansion\n" + "50 Doctor Lucky\n" + "21\n"
        + "22 19 23 26 Armory\n" + "16 21 21 28 Billiard Room\n" + "28 0 35 5 Carriage House\n"
        + "12 11 21 20 Dining Hall\n" + "22 13 25 18 Drawing Room\n" + "26 13 27 18 Foyer\n"
        + "28 26 35 29 Green House\n" + "30 20 35 25 Hedge Maze\n" + "16 3 21 10 Kitchen\n"
        + "0 3 5 8 Lancaster Room\n" + "4 23 9 28 Library\n" + "2 9 7 14 Lilac Room\n"
        + "2 15 7 22 Master Suite\n" + "0 23 3 28 Nursery\n" + "10 5 15 10 Parlor\n"
        + "28 12 35 19 Piazza\n" + "6 3 9 8 Servants' Quarters\n" + "8 11 11 20 Tennessee Room\n"
        + "10 21 15 26 Trophy Room\n" + "22 5 23 12 Wine Cellar\n" + "30 6 35 11 Winter Garden\n"
        + "20\n" + "8 3 Crepe Pan\n" + "4 2 Letter Opener\n" + "12 2 Shoe Horn\n"
        + "8 3 Sharp Knife\n" + "0 3 Revolver\n" + "15 3 Civil War Cannon\n" + "2 4 Chain Saw\n"
        + "16 2 Broom Stick\n" + "1 2 Billiard Cue\n" + "19 2 Rat Poison\n" + "6 2 Trowel\n"
        + "2 4 Big Red Hammer\n" + "6 2 Pinking Shears\n" + "18 3 Duck Decoy\n" + "13 2 Bad Cream\n"
        + "18 2 Monkey Hand\n" + "11 2 Tight Hat\n" + "19 2 Piece of Rope\n" + "9 3 Silken Cord\n"
        + "7 2 Loud Noise\n");
    baseOutput = "Initializing the world map and drawing...finished\n"
        + "World [World name = Doctor Lucky's Mansion, room number =  21, item number = 20, "
        + "target charater = Doctor Lucky, player number = 0].\n"
        + "Save the map to png file?(y|n), default n\n"
        + "Set at least one player before the game begins:\n" + "Enter name for player 0:\n"
        + "Set location for player 0, enter number between 0 and 20,"
        + " or press enter to set default value 0\n"
        + "Set item capacity for player 0, enter number "
        + "greater than 0, or press enter to set default value 2\n"
        + "Set control for player 0 to Computer? (y|n, default no)\n"
        + "What you will add: Player 0, "
        + "with name Jimmy, initial location at room 0, item capacity = 2, controlled by HUMAN\n"
        + "Add this player to game(y) or abort(n), default y\n" + "Player 0 successfully added.\n"
        + "Totally 1 player, add more?(y|n)Enter name for player 1:\n"
        + "Set location for player 1, enter number between 0 and 20,"
        + " or press enter to set default value 0\n" + "Set item capacity for player 1, "
        + "enter number greater than 0, or press enter to set default value 2\n"
        + "Set control for player 1 to Computer? (y|n, default no)\n"
        + "What you will add: Player 1, with name Ai, "
        + "initial location at room 0, item capacity = 1, controlled by COMPUTER\n"
        + "Add this player to game(y) or abort(n), default y\n" + "Player 1 successfully added.\n"
        + "Totally 2 player, add more?(y|n)Enter name for player 2:\n"
        + "Set location for player 2, "
        + "enter number between 0 and 20, or press enter to set default value 0\n"
        + "Set item capacity for player 2, "
        + "enter number greater than 0, or press enter to set default value 2\n"
        + "Set control for player 2 to Computer? (y|n, default no)\n"
        + "What you will add: Player 2, "
        + "with name Penny, initial location at room 3, item capacity = 3, controlled by HUMAN\n"
        + "Add this player to game(y) or abort(n), default y\n" + "Player 2 successfully added.\n"
        + "Totally 3 player, add more?(y|n)Settting finished, game started.\n" + "\n" + "\n"
        + "[TURN 1]\n" + "Player No.0 \"Jimmy\"'s turn\n"
        + "Please select one of the option below\n"
        + "MENU|1.Move|2.Pickup|3.LookAround|4.PlayerInfo|5.RoomInfo|6.Target|7.Exit\n"
        + "Player end game in the process, now quiting.\n";

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
    assertEquals(baseOutput, out.toString());

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
    // assertEquals(baseOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right pickup method
    // assertEquals(baseOutput, out.toString());
    // Test the look around calling its neighbor.
    String expectedLog = "queryRoomDetails called, location = 1\n"
        + "queryRoomDetails called, location = 3\n" + "queryRoomDetails called, location = 4";
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
    String expectedOutput = "Max turn reached, game exit";
    // assertEquals(baseOutput, out.toString());
    assertTrue(out.toString().contains(expectedOutput));
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
    String expectedOutput = "Player No.1 \"Ai\"'s turn\n"
        + "Player No.1 \"Ai\" try to move to No.3 \"Dining Hall\"\n"
        + "Move to neighbor successfully.";
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right move method
    //assertEquals(baseOutput, out.toString());
    //    String expectedLog = "setPlayerLocation called, playerIndex = 0, destLocation = 3";
    //    assertTrue(log.toString().contains(expectedLog));
    //    // assertEquals(expectedLog, log.toString());
    //    // test turn passed.
    //    expectedLog = "getCurrentPlayer called, turn = 2";
    //    assertTrue(log.toString().contains(expectedLog));

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
    String expectedOutput = "Player No.1 \"Ai\"'s turn\n"
        + "Player No.1 \"Ai\" try to pick up No.0 \"Crepe Pan\" Damage:3\n"
        + "Pick up successfully.";
    assertTrue(out.toString().contains(expectedOutput));
    // Test call the right move method
    //assertEquals(baseOutput, out.toString());
    //    String expectedLog = "setPlayerLocation called, playerIndex = 0, destLocation = 3";
    //    assertTrue(log.toString().contains(expectedLog));
    //    // assertEquals(expectedLog, log.toString());
    //    // test turn passed.
    //    expectedLog = "getCurrentPlayer called, turn = 2";
    //    assertTrue(log.toString().contains(expectedLog));

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
    // Test call the right move method
    //assertEquals(baseOutput, out.toString());
    //    String expectedLog = "setPlayerLocation called, playerIndex = 0, destLocation = 3";
    //    assertTrue(log.toString().contains(expectedLog));
    //    // assertEquals(expectedLog, log.toString());
    //    // test turn passed.
    //    expectedLog = "getCurrentPlayer called, turn = 2";
    //    assertTrue(log.toString().contains(expectedLog));

  }

}
