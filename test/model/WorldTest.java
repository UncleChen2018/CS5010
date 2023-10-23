package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for world. Most part is to check if it is correctly set up.
 */
public class WorldTest {

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
    assertFalse(model.getRoomCharacter(initialLocation).contains(playerIndex));
    assertTrue(model.getRoomCharacter(newLocation).contains(playerIndex));
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

    // Choose a location with no items
    assertEquals(expectedOutput, actualOutput);
    locationWithItems = 3; // Choose a location with items
    expectedOutput = "No item.\n";
    actualOutput = model.queryRoomItem(locationWithItems);

    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testPickUpItem() {
    // Arrange
    int playerId = 0; // Choose a player
    int roomId = 0; // Choose a room with items
    int itemId = 4; // Choose an item from the room

    // Act
    model.pickUpitem(playerId, itemId);

    // Assert
    assertTrue(model.getPlayerItems(playerId).contains(itemId));
    assertFalse(model.getRoomItems(roomId).contains(itemId));
    assertEquals(-1, model.getItemLocation(itemId));
  }

  @Test
  public void testGetPlayerItems() {
    // Arrange
    int playerId = 0; // Choose a player
    int itemId1 = 1;
    int itemId2 = 3;
    model.pickUpitem(playerId, itemId1);
    model.pickUpitem(playerId, itemId2);

    // Act
    ArrayList<Integer> playerItems = model.getPlayerItems(playerId);

    // Assert
    assertTrue(playerItems.contains(itemId1));
    assertTrue(playerItems.contains(itemId2));
    assertEquals(2, playerItems.size());
  }

  @Test
  public void testGetItemLocation() {
    // Arrange
    int itemId = 4; // Choose an existing item

    // Act
    int itemLocation = model.getItemLocation(itemId);

    // Assert
    assertEquals(0, itemLocation);
    model.pickUpitem(0, itemId);
    assertEquals(-1, model.getItemLocation(itemId));

  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetItemLocationInvalidId() {
    // Arrange
    int invalidItemId = -1; // Choose an invalid item ID

    // Act
    model.getItemLocation(invalidItemId);
  }

  @Test
  public void testQueryRoomDetails() {
    // Test two player and target in
    String details = model.queryRoomDetails(0);
    String expectedString = "-------------------ROOM DETAILS-------------------\n"
        + "Room: No.0 \"Armory\"\n" + "Items: [No.4 \"Revolver\" Damage:3]\n"
        + "Player: [No.0 \"Jimmy\", No.1 \"AI\"]\n" + "Target: Found\n" + "";

    assertEquals(expectedString, details);

    // Test no player and target not in
    details = model.queryRoomDetails(1);
    expectedString = "-------------------ROOM DETAILS-------------------\n"
        + "Room: No.1 \"Billiard Room\"\n" + "Items: [No.8 \"Billiard Cue\" Damage:2]\n"
        + "Player: No Player\n" + "Target: Not Found\n" + "";

    assertEquals(expectedString, details);

    // Test 1 player and target not in and no item
    details = model.queryRoomDetails(3);
    expectedString = "-------------------ROOM DETAILS-------------------\n"
        + "Room: No.3 \"Dining Hall\"\n" + "Items: No Item\n" + "Player: [No.2 \"Penny\"]\n"
        + "Target: Not Found\n" + "";

    assertEquals(expectedString, details);

  }

  @Test
  public void testQueryPlayerDetails() {
    // Test player 0 ("Jimmy") with items
    String details0 = model.queryPlayerDetails(0);
    String expectedString0 = "-------------------PLAYER DETAILS-------------------\n"
        + "Player: No.0 \"Jimmy\"\n" + "Control Type: HUMAN\n" + "Location: 0\n"
        + "Stock|Capacity: 0|2\n" + "Items: []\n";
    assertEquals(expectedString0, details0);

    // Test player 1 ("AI") with 1 items
    model.pickUpitem(1, 4);
    String details1 = model.queryPlayerDetails(1);
    String expectedString1 = "-------------------PLAYER DETAILS-------------------\n"
        + "Player: No.1 \"AI\"\n" + "Control Type: COMPUTER\n" + "Location: 0\n"
        + "Stock|Capacity: 1|1\n" + "Items: [No.4 \"Revolver\" Damage:3]\n" + "";
    ;
    assertEquals(expectedString1, details1);

  }

  @Test
  public void testQueryTargetDetails() {
    String detailsFound = model.queryTargetDetails();
    String expectedStringFound = "-------------------Target DETAILS-------------------\n"
        + "Target: \"Doctor Lucky\"\n" + "Health: 50\n" + "Location: 0\n" + "";
    assertEquals(expectedStringFound, detailsFound);
  }

  @Test
  public void testQueryRoomNeighbors() {
    // Test for a room with more than 1 neighbors
    String detailsWithNeighbors = model.queryRoomNeighbors(0);
    String expectedStringWithNeighbors = "-------------------Neighbor Info-------------------\n"
        + "No.1 \"Billiard Room\"\n" + "No.3 \"Dining Hall\"\n" + "No.4 \"Drawing Room\"\n" + "";
    assertEquals(expectedStringWithNeighbors, detailsWithNeighbors);

    // Test for a room without 1 neighbors
    String detailsWithoutNeighbors = model.queryRoomNeighbors(2);
    String expectedStringWithoutNeighbors = "-------------------Neighbor Info-------------------\n"
        + "No.20 \"Winter Garden\"\n" + "";

    assertEquals(expectedStringWithoutNeighbors, detailsWithoutNeighbors);
  }

  @Test
  public void testGetRoomNeighbors() {
    // Test for a room with more than 1neighbors
    ArrayList<Integer> neighborsWith = model.getRoomNeighbors(0);
    ArrayList<Integer> expectedNeighborsWith = new ArrayList<>(Arrays.asList(1, 3, 4));

    assertEquals(expectedNeighborsWith, neighborsWith);

    // Test for a room with 1 neighbors
    ArrayList<Integer> neighborsWithout = model.getRoomNeighbors(2);
    ArrayList<Integer> expectedNeighborsWithout = new ArrayList<>(Arrays.asList(20));

    assertEquals(expectedNeighborsWithout, neighborsWithout);
  }

  @Test
  public void testPlayerReachCapacity() {
    // Test for a player who has not reached capacity
    boolean reachedCapacity = model.playerReachCapacity(1);
    assertFalse(reachedCapacity);

    model.pickUpitem(1, 4);

    // Test for a player who has reached capacity
    reachedCapacity = model.playerReachCapacity(1);
    assertTrue(reachedCapacity);
  }

  @Test
  public void testGetRoomItemCount() {
    // Test for a room with 1 items
    int itemCount = model.getRoomItemCount(0);
    assertEquals(1, itemCount);

    // Test for a room with more than 1 items
    itemCount = model.getRoomItemCount(2);
    assertEquals(2, itemCount);

    // Test for a room with no items
    itemCount = model.getRoomItemCount(3);
    assertEquals(0, itemCount);
  }

  @Test
  public void testIsHumanPlayer() {
    // Test for a human player
    boolean isHuman = model.isHumanPlayer(0);
    assertTrue(isHuman);

    // Test for an AI player
    isHuman = model.isHumanPlayer(1);
    assertFalse(isHuman);

    // Test for a different player
    isHuman = model.isHumanPlayer(2);
    assertFalse(isHuman);
  }

  @Test
  public void testGetRoomItems() {
    // Assuming you have already set up some items in the room
    int locationWithItems = 0; // Choose a room with items
    int locationWithoutItems = 3; // Choose a room without items
    int locationWithoutManyItems = 2; // Choose a room with many items

    ArrayList<Integer> itemsWith = model.getRoomItems(locationWithItems);
    ArrayList<Integer> itemsWithout = model.getRoomItems(locationWithoutItems);
    ArrayList<Integer> itemsWithMany = model.getRoomItems(locationWithoutManyItems);

    ArrayList<Integer> expectedItemsWith = new ArrayList<>(Arrays.asList(4));
    ArrayList<Integer> expectedItemsWithout = new ArrayList<>(Arrays.asList());

    assertEquals(expectedItemsWith, itemsWith);
    assertEquals(expectedItemsWithout, itemsWithout);
    ArrayList<Integer> expectedItemsWithMany = new ArrayList<>(Arrays.asList(6, 11));
    assertEquals(expectedItemsWithMany, itemsWithMany);
  }

  @Test
  public void testGetPlayerString() {
    // Test for a human player
    int humanPlayerId = 0;
    String humanPlayerString = model.getPlayerString(humanPlayerId);
    String expectedHumanPlayerString = "No.0 \"Jimmy\"";
    assertEquals(expectedHumanPlayerString, humanPlayerString);

    // Test for an AI player
    int aiPlayerId = 1;
    String aiPlayerString = model.getPlayerString(aiPlayerId);
    String expectedAiPlayerString = "No.1 \"AI\"";
    assertEquals(expectedAiPlayerString, aiPlayerString);
  }

  @Test
  public void testGetRoomString() {
    // Test for a room with items, players, and target
    int roomWithAllDetails = 0;
    String roomStringWithAllDetails = model.getRoomString(roomWithAllDetails);
    String expectedRoomStringWithAllDetails = "No.0 \"Armory\"";
    assertEquals(expectedRoomStringWithAllDetails, roomStringWithAllDetails);

    // Test for a room with no players and no target
    int roomWithNoDetails = 1;
    String roomStringWithNoDetails = model.getRoomString(roomWithNoDetails);
    String expectedRoomStringWithNoDetails = "No.1 \"Billiard Room\"";
    assertEquals(expectedRoomStringWithNoDetails, roomStringWithNoDetails);
  }

  @Test
  public void testGetTargetString() {

    String targetStringFound = model.getTargetString();
    String expectedTargetStringFound = "\"Doctor Lucky\"";
    assertEquals(expectedTargetStringFound, targetStringFound);

  }

  @Test
  public void testGetItemString() {
    // Test for an item with an owner
    int itemWithOwner = 0; // Choose an item with an owner
    String itemStringWithOwner = model.getItemString(itemWithOwner);
    String expectedItemStringWithOwner = "No.0 \"Crepe Pan\" Damage:3";
    assertEquals(expectedItemStringWithOwner, itemStringWithOwner);
  }
  
  @Test
  public void testGetRoomCharacter() {
      // Test for a room with characters
      int roomWithCharacters = 0; // Choose a room with characters
      ArrayList<Integer> charactersInRoom = model.getRoomCharacter(roomWithCharacters);
      ArrayList<Integer> expectedCharactersInRoom = new ArrayList<>(Arrays.asList(0, 1));

      assertEquals(expectedCharactersInRoom, charactersInRoom);

      // Test for a room without characters
      int roomWithoutCharacters = 2; // Choose a room without characters
      ArrayList<Integer> charactersNotInRoom = model.getRoomCharacter(roomWithoutCharacters);
      ArrayList<Integer> expectedCharactersNotInRoom = new ArrayList<>();

      assertEquals(expectedCharactersNotInRoom, charactersNotInRoom);
  }


}
