package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for world. Most part is to check if it is correctly set up.
 */
public class WorldTest {
// TODO: use model to test.
  private GameModel model;

  /**
   * Give the string as the map input and get the world set up.
   */
  @Before
  public void testSetup() {
    String input = "36 30 Doctor Lucky's Mansion\n" + "50 Doctor Lucky\n" + "21\n"
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
        + "7 2 Loud Noise";
    model = new World(new StringReader(input));
    assertNotNull(model);
    model.addNewPlayer("Jimmy", 0, 2, true);
    model.addNewPlayer("AI", 0, 1, false);
    model.addNewPlayer("Penny", 3, 2, false);

  }

  @Test
  public void testSetupNewWorld() {
    String expectedWorldName = "Doctor Lucky's Mansion";
    int expectedRoomCount = 21;

    assertEquals(expectedWorldName, model.getName());
    assertEquals(expectedRoomCount, model.getRoomCount());
    int expectedItemNumber = 1;
    assertEquals(expectedItemNumber, model.getRoomItemCount(0)); // Assuming you want to check the
    int expectedPlayerCount = 3; // Assuming no players yet // first room
    assertEquals(expectedPlayerCount, model.getPlayerCount());
  }

  @Test
  public void testGetName() {
    String expectedName = "Doctor Lucky's Mansion";
    assertEquals(expectedName, model.getName());
  }

  @Test
  public void testGetDetails() {
    String expectedDetails = "World [World name = Doctor Lucky's Mansion,"
        + " room number =  21, item number = 20, "
        + "target charater = Doctor Lucky, player number = 3].";
    String actualDetails = model.getDetails();
    assertEquals(expectedDetails, actualDetails);
  }

  @Test
  public void testGetPlayerCount() {
    assertEquals(3, model.getPlayerCount());

  }

  @Test
  public void testSetPlayerLocation() {
    // Arrange: Get the initial location of the player
    int playerIndex = 0; // Assuming player with index 0
    int initialLocation = model.getPlayerLocation(playerIndex);

    // Act: Set the player to a new location
    int newLocation = 2; // Choose a valid new location
    model.setPlayerLocation(playerIndex, newLocation);

    // Assert: Check if the player's location was updated correctly
    assertEquals(newLocation, model.getPlayerLocation(playerIndex));

    // Assert: Check if the player was removed from the origin room and added to the
    // dest room
    assertFalse(model.getRoomCharater(initialLocation).contains(playerIndex));
    assertTrue(model.getRoomCharater(newLocation).contains(playerIndex));
  }

  @Test
  public void testGetPlayerLocation() {
    // Arrange: Create a player and set their location
    int playerId = 3; // Assuming player with index 0
    int expectedLocation = 3; // Choose an arbitrary location

    model.addNewPlayer("TestPlayer", expectedLocation, 5, true);

    // Act: Retrieve the player's location using getPlayerLocation
    int actualLocation = model.getPlayerLocation(playerId);

    // Assert: Check if the returned location matches the expected location
    assertEquals(expectedLocation, actualLocation);
  }

  @Test
  public void testIsNeighbor() {
    // Arrange: Define quest and base locations
    int questLocation = 2; // Choose a valid location
    int baseLocation = 3; // Choose another valid location

    // Act: Check if quest is a neighbor of base
    boolean isNeighbor = model.isNeighbor(questLocation, baseLocation);

    // Assert: Check if the result is as expected
    assertFalse(isNeighbor);

    questLocation = 8;
    isNeighbor = model.isNeighbor(questLocation, baseLocation);
    // Assert: Check if the result is as expected
    assertTrue(isNeighbor);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testIsNeighborInvalidIndices() {
    // Arrange
    int quest = -1; // Invalid index
    int base = 0; // Assuming base is valid

    // Act
    model.isNeighbor(quest, base);

    // Assert (not needed, the test is expected to throw an exception)
  }

  @Test
  public void testGetCurrentPlayer() {
    // Act and Assert: Check the current player for different turns
    assertEquals(0, model.getCurrentPlayer(0)); // Turn 0, Player 0
    assertEquals(1, model.getCurrentPlayer(1)); // Turn 1, Player 1
    assertEquals(2, model.getCurrentPlayer(2)); // Turn 2, Player 2
    assertEquals(0, model.getCurrentPlayer(3)); // Turn 3, Player 0 (cycling back to the first)

    assertEquals(1, model.getCurrentPlayer(4)); // Turn 4, Player 1
    assertEquals(2, model.getCurrentPlayer(5)); // Turn 5, Player 2
    assertEquals(0, model.getCurrentPlayer(6)); // Turn 6, Player 0 (cycling back to the first)
  }

  @Test
  public void testAddNewPlayer() {
    // Arrange: Assuming the roomList has at least one room
    String playerName = "John Doe";
    int initLocation = 0;
    int capacity = 3;
    boolean isHumanControl = true;

    // Act
    model.addNewPlayer(playerName, initLocation, capacity, isHumanControl);

    // Assert
    int expectedPlayerCount = 4; // 3 players added in setup + 1 new player
    assertEquals(expectedPlayerCount, model.getPlayerCount());

    int expectedPlayerLocation = initLocation;
    assertEquals(expectedPlayerLocation, model.getPlayerLocation(3)); // Assuming 0-based indexing
    // for player IDs

    String playeString = "No.3 \"John Doe\"";
    // Assuming player IDs start from 0
    assertEquals(playeString, model.getPlayerString(3));

    // Add more assertions as needed...
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNewPlayerInvalidLocation() {
    // Arrange
    String playerName = "John Doe";
    int initLocation = 100; // Assuming there are not more than 10 rooms
    int capacity = 3;
    boolean isHumanControl = true;

    // Act
    model.addNewPlayer(playerName, initLocation, capacity, isHumanControl);

    // Assert (not needed, the test is expected to throw an exception)
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNewPlayerInvalidCapacity() {
    // Arrange
    String playerName = "John Doe";
    int initLocation = 0;
    int capacity = 0; // Invalid capacity
    boolean isHumanControl = true;

    // Act
    model.addNewPlayer(playerName, initLocation, capacity, isHumanControl);

    // Assert (not needed, the test is expected to throw an exception)
  }

  @Test
  public void testGetRoomCount() {
    assertEquals(21, model.getRoomCount());
  }

  @Test
  public void testMoveTargetNextRoom() {

    // Act
    for (int i = 0; i <= model.getRoomCount(); i++) {
      int initialLocation = model.getTargetLocation();
      int expectedNextLocation = (initialLocation + 1) % model.getRoomCount();
      model.moveTargetNextRoom();
      int actualNextLocation = model.getTargetLocation();
      // Assert
      assertEquals(expectedNextLocation, actualNextLocation);

    }
  }

  @Test
  public void testQueryRoomItem() {
    int locationWithItems = 0; // Choose a location with 1 items
    String expectedOutput = "Item [itemId = 4, itemName = Revolver, "
        + "itemDamage = 3, storedLocation = 0]\n";
    String actualOutput = model.queryRoomItem(locationWithItems);

    assertEquals(expectedOutput, actualOutput);

    // Choose a location with 1 items
    locationWithItems = 2; // Choose a location with items
    expectedOutput = "Item [itemId = 6, itemName = Chain Saw, itemDamage = 4, storedLocation = 2]\n"
        + "Item [itemId = 11, itemName = Big Red Hammer, itemDamage = 4, storedLocation = 2]\n";
    actualOutput = model.queryRoomItem(locationWithItems);

    assertEquals(expectedOutput, actualOutput);
    locationWithItems = 3; // Choose a location with items
    expectedOutput = "No item.\n";
    actualOutput = model.queryRoomItem(locationWithItems);

    assertEquals(expectedOutput, actualOutput);
  }

}
