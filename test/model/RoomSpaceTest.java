package world;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;

/**
 * Test class for rooms. Most part are getters and setters.
 */
public class RoomSpaceTest {

  @Test
  public void testGetSpaceName() {
    RoomSpace room = new RoomSpace(1, 0, 0, 3, 3, "Living Room");
    assertEquals("Living Room", room.getSpaceName());
  }

  @Test
  public void testGetSpaceIndex() {
    RoomSpace room = new RoomSpace(2, 1, 1, 4, 4, "Bedroom");
    assertEquals(2, room.getSpaceIndex());
  }

  @Test
  public void testAddItemAndGetSpaceItem() {
    RoomSpace room = new RoomSpace(3, 2, 2, 5, 5, "Kitchen");
    Item item = new Item("Knife", 5, 3);
    room.addItem(item);
    ArrayList<Item> itemList = room.getSpaceItem();
    assertTrue(itemList.contains(item));
  }

  @Test
  public void testGetRoomRect() {
    RoomSpace room = new RoomSpace(4, 3, 3, 6, 6, "Bathroom");
    int[] rect = room.getRoomRect();
    int[] expected = { 3, 3, 7, 7 }; // Expected coordinates after adjustment +1

    assertArrayEquals(expected, rect);
  }

  @Test
  public void testGetNeighbors() {
    RoomSpace room1 = new RoomSpace(5, 0, 0, 3, 3, "Room 1");
    RoomSpace room2 = new RoomSpace(6, 4, 4, 7, 7, "Room 2");
    room1.addNeighbor(room2);
    ArrayList<RoomSpace> neighbors = room1.getNeighbors();
    assertTrue(neighbors.contains(room2));
  }

  @Test
  public void testGetVisibles() {
    RoomSpace room1 = new RoomSpace(7, 0, 0, 3, 3, "Room 1");
    RoomSpace room2 = new RoomSpace(8, 4, 4, 7, 7, "Room 2");
    room1.addVisible(room2);
    ArrayList<RoomSpace> visibles = room1.getVisibles();
    assertTrue(visibles.contains(room2));
  }

  @Test
  public void testToString() {
    RoomSpace room = new RoomSpace(9, 0, 0, 3, 3, "Hallway");
    assertEquals("Hallway", room.toString());
  }

  @Test
  public void testGetSpaceInfo() {
    RoomSpace room = new RoomSpace(10, 0, 0, 3, 3, "Library");
    Item item = new Item("Book", 2, 10);
    room.addItem(item);
    RoomSpace visibleRoom = new RoomSpace(11, 4, 4, 7, 7, "Study");
    room.addVisible(visibleRoom);

    String expected = "Library, with items: [Book: with damage: 2, in room No. 10]."
        + " visible space: [Study]";
    assertEquals(expected, room.getSpaceInfo());
  }
}
