package controller;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import model.GameModel;
import model.MockModel;
import org.junit.Before;
import org.junit.Test;

/**
 * In this test, computer use varargs.
 */
public class CommandControllerTest {
  private Readable worldData;

  private String baseInput; // base String to set up world.
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
  public void testAddPlayer() {
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
    // Add no name
    String inputString = "\n" + "\n" + "Jimmy\n0\n2\n\n\n" + "\n" + "7\n" // quit game
    ;
    StringBuffer out = new StringBuffer();
    StringBuilder log = new StringBuilder();
    StringReader in = new StringReader(inputString);

    GameModel mockModel = new MockModel(log);
    GameController controller = new CommandController(in, out, worldData, 100);
    controller.start(mockModel);
    // Test add player without name.
    String expectedOutput = "Name can not be blank";
    assertTrue(out.toString().contains(expectedOutput));
    // Test add Computer player
    // assertEquals(baseOutput, out.toString());
    // assertEquals(baseOutput, log.toString());

  }

}
