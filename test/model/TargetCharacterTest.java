package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for target. Most part are getters and setters.
 */
public class TargetCharacterTest {

  @Test
  public void getName() {
    TargetCharacter target = new TargetCharacter("Enemy", 100);
    assertEquals("Enemy", target.getName());
  }

  @Test
  public void getHealth() {
    TargetCharacter target = new TargetCharacter("Enemy", 100);
    assertEquals(100, target.getHealth());
  }

  @Test
  public void setHealth() {
    TargetCharacter target = new TargetCharacter("Enemy", 100);
    target.setHealth(75);
    assertEquals(75, target.getHealth());
  }

  @Test
  public void getDetails() {
    TargetCharacter target = new TargetCharacter("Enemy", 100);
    String expected = "Target [name = \"Enemy\", location = 0, health = 100]";
    assertEquals(expected, target.getDetails());
  }

  @Test
  public void toStringTest() {
    TargetCharacter target = new TargetCharacter("Enemy", 100);
    assertEquals("\"Enemy\"", target.toString());
  }

  @Test
  public void querryDetails() {
    TargetCharacter target = new TargetCharacter("Enemy", 100);
    String expected = "-------------------Target DETAILS-------------------\n"
        + "Target: \"Enemy\"\n" + "Health: 100\n" + "Location: 0\n";
    assertEquals(expected, target.querryDetails());
  }
}
