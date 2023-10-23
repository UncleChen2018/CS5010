package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;

/**
 * JUnit tests for the Player class.
 */
public class PlayerTest {

  @Test
  public void addItem() {
    Player player = new Player("John", 0, 3, true, 1);
    Item item1 = new Weapon(1, "Sword", 10, 0);
    Item item2 = new Weapon(2, "Axe", 8, 0);

    player.addItem(item1);
    player.addItem(item2);

    ArrayList<Item> expectedItemList = new ArrayList<>();
    expectedItemList.add(item1);
    expectedItemList.add(item2);

    assertEquals(expectedItemList, player.getItemList());
  }

  @Test
  public void switchToComputerPlay() {
    Player player = new Player("John", 0, 3, true, 1);
    player.switchToComputerPlay();
    assertFalse(player.isHumanPlayer());
  }

  @Test
  public void switchToHumanPlay() {
    Player player = new Player("John", 0, 3, false, 1);
    player.switchToHumanPlay();
    assertTrue(player.isHumanPlayer());
  }

  @Test
  public void isHumanPlayer() {
    Player player1 = new Player("John", 0, 3, true, 1);
    Player player2 = new Player("Jane", 0, 3, false, 2);

    assertTrue(player1.isHumanPlayer());
    assertFalse(player2.isHumanPlayer());
  }

  @Test
  public void isComputerPlayer() {
    Player player1 = new Player("John", 0, 3, true, 1);
    Player player2 = new Player("Jane", 0, 3, false, 2);

    assertFalse(player1.isComputerPlayer());
    assertTrue(player2.isComputerPlayer());
  }

}
