package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Weapon;

/**
 * Test class for item. Most part are getters and setters.
 */
public class ItemTest {

  @Test
  public void testGetItemDamage() {
    Weapon item = new Weapon("Sword", 10, 1);
    assertEquals(10, item.getItemDamage());
  }

  @Test
  public void testGetItemName() {
    Weapon item = new Weapon("Shield", 5, 2);
    assertEquals("Shield", item.getItemName());
  }

  @Test
  public void testGetItemLocationIndex() {
    Weapon item = new Weapon("Potion", 0, 3);
    assertEquals(3, item.getStoredLoacation());
  }

  @Test
  public void testSetItemLocationIndex() {
    Weapon item = new Weapon("Axe", 15, 4);
    item.setStoredLoacation(5);
    assertEquals(5, item.getStoredLoacation());
  }

  @Test
  public void testToString() {
    Weapon item = new Weapon("Bow", 8, 6);
    String expected = "Bow: with damage: 8, in room No. 6";
    assertEquals(expected, item.toString());
  }
}
