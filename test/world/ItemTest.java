package world;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for item. Most part are getters and setters.
 */
public class ItemTest {

  @Test
  public void testGetItemDamage() {
    Item item = new Item("Sword", 10, 1);
    assertEquals(10, item.getItemDamage());
  }

  @Test
  public void testGetItemName() {
    Item item = new Item("Shield", 5, 2);
    assertEquals("Shield", item.getItemName());
  }

  @Test
  public void testGetItemLocationIndex() {
    Item item = new Item("Potion", 0, 3);
    assertEquals(3, item.getItemLocationIndex());
  }

  @Test
  public void testSetItemLocationIndex() {
    Item item = new Item("Axe", 15, 4);
    item.setItemLocationIndex(5);
    assertEquals(5, item.getItemLocationIndex());
  }

  @Test
  public void testToString() {
    Item item = new Item("Bow", 8, 6);
    String expected = "Bow: with damage: 8, in room No. 6";
    assertEquals(expected, item.toString());
  }
}
