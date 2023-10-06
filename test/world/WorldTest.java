package world;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for world. Most part is to check if it is correctly set up.
 */
public class WorldTest {

  private World world;

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
    world = new World(new StringReader(input));

  }

  @Test
  public void testSetupNewWorld() {
    assertEquals("Doctor Lucky's Mansion", world.getWorldName());

    assertEquals(50, world.getTarget().getHealth());
    assertEquals("Doctor Lucky", world.getTarget().getName());
    // test room
    assertEquals(21, world.getWorldSpace().size());
    RoomSpace room = world.getWorldSpace().get(0);
    assertEquals("Armory", room.getSpaceName());
    // test item
    assertEquals(20, world.getItems().size());
    Item item = world.getItems().get(0);
    assertEquals("Crepe Pan", item.getItemName());
    assertEquals(3, item.getItemDamage());
    assertEquals(8, item.getItemLocationIndex());
  }

  @Test
  public void testMoveTargetNextRoom() {

    TargetCharacter target = world.getTarget();
    int initialRoomIndex = target.getLocatedRoomIndex();
    world.moveTargetNextRoom();
    int newRoomIndex = target.getLocatedRoomIndex();

    assertEquals((initialRoomIndex + 1) % world.getWorldSpace().size(), newRoomIndex);
  }

}
