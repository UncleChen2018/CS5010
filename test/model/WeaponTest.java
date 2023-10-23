package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Unit test for Class Weapon.
 */
public class WeaponTest {

  @Test
  public void queryDetailsWithoutOwner() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    String expected = "Item [itemId = 1, itemName = Sword, itemDamage = 10, storedLocation = 3]";
    assertEquals(expected, weapon.queryDetails());
  }

  @Test
  public void queryDetailsWithOwner() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    Player owner = new Player("John", 2, 5, true, 1);
    weapon.setOwner(owner);

    String expected = "Item [itemId = 1, itemName = Sword, itemDamage = 10, owner = \"John\"]";
    assertEquals(expected, weapon.queryDetails());
  }

  @Test
  public void toStringTest() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    String expected = "No.1 \"Sword\" Damage:10";
    assertEquals(expected, weapon.toString());
  }

  @Test
  public void getSetItemDamage() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    assertEquals(10, weapon.getItemDamage());

    weapon.setStoredLoacation(15);
    assertEquals(15, weapon.getStoredLoacation());
  }

  @Test
  public void getItemName() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    assertEquals("Sword", weapon.getItemName());
  }

  @Test
  public void getSetStoredLocation() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    assertEquals(3, weapon.getStoredLoacation());

    weapon.setStoredLoacation(15);
    assertEquals(15, weapon.getStoredLoacation());
  }

  @Test
  public void getSetOwner() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    Player owner = new Player("John", 2, 5, true, 1);
    assertNull(weapon.getOwner());

    weapon.setOwner(owner);
    assertEquals(owner, weapon.getOwner());
  }

  @Test
  public void getItemId() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    assertEquals(1, weapon.getItemId());
  }

  @Test
  public void querryLocationDetails() {
    Weapon weapon = new Weapon(1, "Sword", 10, 3);
    String expected = "-------------------Item DETAILS-------------------\n"
        + "Item: No.1 \"Sword\" Damage:10\n" + "Damage: 10\n" + "Location: 3\n" + "Owner: null\n";
    assertEquals(expected, weapon.querryLocationDetails());
  }
}
