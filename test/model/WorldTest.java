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

//  @Test
//  public void testGetWorldName() {
//    assertEquals("Doctor Lucky's Mansion", model.getWorldName());
//  }
//
//  @Test
//  public void testGetName() {
//    String expectedName = "Doctor Lucky's Mansion";
//    assertEquals(expectedName, world.getName());
//  }
//
//  @Test
//  public void testGetRoomCount() {
//    assertEquals(21, world.getRoomCount());
//  }
//
//  @Test
//  public void testGetItemCount() {
//    assertEquals(20, world.getItems().size());
//  }
//
//  @Test
//  public void testGetPlayerCount() {
//    assertEquals(3, world.getPlayerCount());
//    
//
//  }
//
//  @Test
//  public void testGetTargetName() {
//    assertEquals("Doctor Lucky", world.getTarget().getName());
//  }
//
//  @Test
//  public void testGetRoomSpace() {
//    assertNotNull(world.getRoomSpace(0));
//  }
//
//  @Test
//  public void testGetDetails() {
//    String expectedDetails = String.format(
//        "World [World name = %s, room number =  %d, item number = %d, "
//            + "target charater = %s, player number = %d].",
//        world.getWorldName(), world.getRoomCount(), world.getItems().size(),
//        world.getTarget().getName(), world.getPlayerCount());
//
//    assertEquals(expectedDetails, world.getDetails());
//  }
//
//  @Test
//  public void testSetPlayerLocationValid() {
//    // Assuming you have at least one player and one room
//    int playerId = 0; // Assuming player index 0 exists
//    int destinationRoom = 1; // Assuming room index 1 exists
//
//    // Get the initial location of the player
//    int initialLocation = world.getPlayerLocation(playerId);
//
//    // Set the player to a new location
//    world.setPlayerLocation(playerId, destinationRoom);
//
//    // Check if the player's location has changed
//    int newLocation = world.getPlayerLocation(playerId);
//    assertEquals(destinationRoom, newLocation);
//
//    // Check if the player has been removed from the initial room
//    assertFalse(world.getRoomCharater(initialLocation).contains(playerId));
//
//    // Check if the player has been added to the destination room
//    assertTrue(world.getRoomCharater(destinationRoom).contains(playerId));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testSetPlayerLocationInvalidPlayer() {
//    int invalidPlayerId = -1; // Assuming an invalid player index
//    int destinationRoom = 1; // Assuming room index 1 exists
//    world.setPlayerLocation(invalidPlayerId, destinationRoom);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testSetPlayerLocationInvalidLocation() {
//    int playerId = 0; // Assuming player index 0 exists
//    int invalidDestinationRoom = -1; // Assuming an invalid room index
//    world.setPlayerLocation(playerId, invalidDestinationRoom);
//  }
}
