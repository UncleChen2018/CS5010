package model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for rooms. Most part are getters and setters.
 */

public class RoomSpaceTest {

  @Test
  public void addNeighbor() {
    RoomSpace room1 = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    RoomSpace room2 = new RoomSpace(2, 0, 0, 1, 1, "Room 2");

    room1.addNeighbor(room2);
    assertTrue(room1.getNeighbors().contains(room2));
  }

  @Test
  public void addVisible() {
    RoomSpace room1 = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    RoomSpace room2 = new RoomSpace(2, 0, 0, 1, 1, "Room 2");

    room1.addVisible(room2);
    assertTrue(room1.getVisibles().contains(room2));
  }

  @Test
  public void getSpaceName() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    assertEquals("Room 1", room.getSpaceName());
  }

  @Test
  public void getSpaceIndex() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    assertEquals(1, room.getSpaceIndex());
  }

  @Test
  public void addItem() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    Weapon weapon = new Weapon(1, "Sword", 10, 1);
    room.addItem(weapon);
    assertTrue(room.getSpaceItem().contains(weapon));
  }

  @Test
  public void getRoomRect() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    int[] expected = { 0, 0, 2, 2 };
    assertArrayEquals(expected, room.getRoomRect());
  }

  @Test
  public void getNeighbors() {
    RoomSpace room1 = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    RoomSpace room2 = new RoomSpace(2, 0, 0, 1, 1, "Room 2");

    room1.addNeighbor(room2);
    assertTrue(room1.getNeighbors().contains(room2));
  }

  @Test
  public void getVisibles() {
    RoomSpace room1 = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    RoomSpace room2 = new RoomSpace(2, 0, 0, 1, 1, "Room 2");

    room1.addVisible(room2);
    assertTrue(room1.getVisibles().contains(room2));
  }

  @Test
  public void getItems() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    Weapon weapon = new Weapon(1, "Sword", 10, 1);
    room.addItem(weapon);
    assertTrue(room.getItems().contains(weapon));
  }

  @Test
  public void getCharacterList() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    Player player = new Player("Player1", 1, 5, true, 1);
    room.addCharacer(player);
    assertTrue(room.getCharacterList().contains(player));
  }

  @Test
  public void removeCharacter() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    Player player = new Player("Player1", 1, 5, true, 1);
    room.addCharacer(player);
    room.removeCharacter(player);
    assertFalse(room.getCharacterList().contains(player));
  }

  @Test
  public void setTargetIn() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    room.setTargetIn();
    assertTrue(room.hasTarget());
  }

  @Test
  public void setTargetOut() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    room.setTargetOut();
    assertFalse(room.hasTarget());
  }

  @Test
  public void removeItem() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    Weapon weapon = new Weapon(1, "Sword", 10, 1);
    room.addItem(weapon);
    room.removeItem(weapon);
    assertFalse(room.getItems().contains(weapon));
  }

  @Test
  public void queryDetails() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");

    // Adding items and player for the test
    Item sword = new Weapon(1, "Sword", 10, 1);
    room.addItem(sword);

    Player player = new Player("Player1", 1, 5, true, 1);
    room.addCharacer(player);

    String expected = "-------------------ROOM DETAILS-------------------\n"
        + "Room: No.1 \"Room 1\"\n" + "Items: [No.1 \"Sword\" Damage:10]\n"
        + "Player: [No.1 \"Player1\"]\n" + "Target: Not Found\n" + "Pet: Not Found\n";
    assertEquals(expected, room.queryDetails());
  }

  @Test
  public void queryDetailsWithItemAndPlayer() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");

    Item sword = new Weapon(1, "Sword", 10, 1);
    room.addItem(sword);

    Player player = new Player("Player1", 1, 5, true, 1);
    room.addCharacer(player);

    String expected = "-------------------ROOM DETAILS-------------------\n"
        + "Room: No.1 \"Room 1\"\n" + "Items: [No.1 \"Sword\" Damage:10]\n"
        + "Player: [No.1 \"Player1\"]\n" + "Target: Not Found\n" + "Pet: Not Found\n";
    assertEquals(expected, room.queryDetails());
  }

  @Test
  public void queryDetailsWithNoItemAndNoPlayer() {
    RoomSpace room = new RoomSpace(2, 0, 0, 1, 1, "Room 2");

    String expected = "-------------------ROOM DETAILS-------------------\n"
        + "Room: No.2 \"Room 2\"\n" + "Items: No Item\n" + "Player: No Player\n"
        + "Target: Not Found\n" + "Pet: Not Found\n";
    assertEquals(expected, room.queryDetails());
  }

  @Test
  public void queryDetailsWithMultiplePlayers() {
    RoomSpace room = new RoomSpace(3, 0, 0, 1, 1, "Room 3");

    Player player1 = new Player("Player1", 1, 5, true, 1);
    Player player2 = new Player("Player2", 1, 5, true, 2);

    room.addCharacer(player1);
    room.addCharacer(player2);

    String expected = "-------------------ROOM DETAILS-------------------\n"
        + "Room: No.3 \"Room 3\"\n" + "Items: No Item\n"
        + "Player: [No.1 \"Player1\", No.2 \"Player2\"]\n" + "Target: Not Found\n"
        + "Pet: Not Found\n";
    assertEquals(expected, room.queryDetails());
  }

  @Test
  public void queryRoomNeighbors() {
    RoomSpace room1 = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    RoomSpace room2 = new RoomSpace(2, 0, 0, 1, 1, "Room 2");

    room1.addNeighbor(room2);
    String expected = "-------------------Neighbor Info-------------------\n" + "No.2 \"Room 2\"\n";
    assertEquals(expected, room1.queryRoomNeighbors());
  }

  @Test
  public void queryRoomNeighbors_NoNeighbors() {
    RoomSpace room = new RoomSpace(1, 0, 0, 1, 1, "Room 1");
    String expected = "-------------------Neighbor Info-------------------\n";
    assertEquals(expected, room.queryRoomNeighbors());
  }

  @Test
  public void testIsPetInInitially() {
    // Ensure that the pet is not in the room initially
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    assertFalse(room.isPetIn());
  }

  @Test
  public void testSetPetIn() {
    // Set pet in the room and check if it is in
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    room.setPetIn();
    assertTrue(room.isPetIn());
  }

  @Test
  public void testSetPetOut() {
    // Set pet in the room and then set it out, check if it is out
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    room.setPetIn();
    room.setPetOut();
    assertFalse(room.isPetIn());
  }

  @Test
  public void testIsRoomInvisibleWithoutPlayers() {
    // Room should be invisible initially as there are no players or pets
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    assertTrue(room.isRoomInvisible());
  }

  @Test
  public void testIsRoomVisibleWithMultiplePlayers() {
    // Add a player to the room, room should be visible
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    Player player1 = new Player("Player1", 0, 2, true, 0);
    Player player2 = new Player("Player2", 0, 2, true, 0);
    room.addCharacer(player1);
    assertTrue(room.isRoomInvisible());
    room.addCharacer(player2);
    assertFalse(room.isRoomInvisible());
  }

  @Test
  public void testIsRoomInvisibleWithPet() {
    // Add a pet to the room, room should be invisible
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    room.setPetIn();
    assertTrue(room.isRoomInvisible());
  }

  @Test
  public void testIsRoomInvisibleWithNeighborPlayers() {
    // Add a player to a neighbor room, room should be invisible
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    RoomSpace neighborRoom = new RoomSpace(1, 0, 6, 5, 11, "Neighbor Room");
    room.addNeighbor(neighborRoom);

    Player neighborPlayer = new Player("NeighborPlayer", 1, 2, true, 1);
    neighborRoom.addCharacer(neighborPlayer);

    assertFalse(room.isRoomInvisible());
  }

  @Test
  public void testIsRoomVisibleWithNeighborPlayersOut() {
    // Add a player to a neighbor room, but set it out, room should be visible
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    RoomSpace neighborRoom = new RoomSpace(1, 0, 6, 5, 11, "Neighbor Room");
    room.addNeighbor(neighborRoom);

    Player neighborPlayer = new Player("NeighborPlayer", 1, 2, true, 1);
    neighborRoom.addCharacer(neighborPlayer);
    neighborRoom.removeCharacter(neighborPlayer);

    assertTrue(room.isRoomInvisible());
  }

  @Test
  public void testIsRoomInvisibleWithNeighborPet() {
    // Add a pet to a neighbor room, room should be invisible
    RoomSpace room = new RoomSpace(0, 0, 0, 5, 5, "Test Room");
    RoomSpace neighborRoom = new RoomSpace(1, 0, 6, 5, 11, "Neighbor Room");
    room.addNeighbor(neighborRoom);

    neighborRoom.setPetIn();

    assertTrue(room.isRoomInvisible());
  }

}
