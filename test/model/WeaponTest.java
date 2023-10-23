package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * Unit test for Class Weapon.
 */
public class WeaponTest {

  @Test
  public void queryDetails() {
    Player player = new Player("Player1", 1, 5, true, 1);
    Item sword = new Weapon(1, "Sword", 10, 1);
    sword.setOwner(player);

    String expected = "Item [itemId = 1, itemName = Sword, itemDamage = 10, owner = \"Player1\"]";
    assertEquals(expected, sword.queryDetails());
  }

  @Test
  public void toStringTest() {
    Item sword = new Weapon(1, "Sword", 10, 1);

    String expected = "No.1 \"Sword\" Damage:10";
    assertEquals(expected, sword.toString());
  }

  @Test
  public void getItemDamage() {
    Item sword = new Weapon(1, "Sword", 10, 1);

    int expected = 10;
    assertEquals(expected, sword.getItemDamage());
  }

  @Test
  public void getItemName() {
    Item sword = new Weapon(1, "Sword", 10, 1);

    String expected = "Sword";
    assertEquals(expected, sword.getItemName());
  }

  @Test
  public void getStoredLocation() {
    Item sword = new Weapon(1, "Sword", 10, 1);

    int expected = 1;
    assertEquals(expected, sword.getStoredLoacation());
  }

  @Test
  public void setStoredLocation() {
    Item sword = new Weapon(1, "Sword", 10, 1);
    sword.setStoredLoacation(2);

    int expected = 2;
    assertEquals(expected, sword.getStoredLoacation());
  }

  @Test
  public void getOwner() {
    Player player = new Player("Player1", 1, 5, true, 1);
    Item sword = new Weapon(1, "Sword", 10, 1);
    sword.setOwner(player);

    Player owner = sword.getOwner();
    assertNotNull(owner);
    assertEquals("Player1", owner.getName());
  }

  @Test
  public void setOwner() {
    Player player1 = new Player("Player1", 1, 5, true, 1);

    Item sword = new Weapon(1, "Sword", 10, 1);
    sword.setOwner(player1);

    Player owner1 = sword.getOwner();
    assertNotNull(owner1);
    assertEquals("Player1", owner1.getName());
    Player player2 = new Player("Player2", 1, 5, true, 2);

    sword.setOwner(player2);

    Player owner2 = sword.getOwner();
    assertNotNull(owner2);
    assertEquals("Player2", owner2.getName());
  }

  @Test
  public void querryLocationDetails() {
    Item sword = new Weapon(1, "Sword", 10, 1);

    String expected = "-------------------Item DETAILS-------------------\n"
        + "Item: No.1 \"Sword\" Damage:10\n" + "Damage: 10\n" + "Location: 1\n" + "Owner: null\n";
    assertEquals(expected, sword.querryLocationDetails());
  }

  @Test
  public void getItemId() {
    Item sword = new Weapon(1, "Sword", 10, 1);

    int expected = 1;
    assertEquals(expected, sword.getItemId());
  }
}
