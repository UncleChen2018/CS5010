package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Test class for target. Most part are getters and setters.
 */
public class TargetCharacterTest {

  @Test
  public void testGetName() {
    TargetCharacter character = new TargetCharacter("Dr. Smith", 50);
    assertEquals("Dr. Smith", character.getName());
  }

  @Test
  public void testGetHealth() {
    TargetCharacter character = new TargetCharacter("Dr. Watson", 70);
    assertEquals(70, character.getHealth());
  }

  @Test
  public void testSetHealth() {
    TargetCharacter character = new TargetCharacter("Dr. Jekyll", 80);
    character.setHealth(90);
    assertEquals(90, character.getHealth());
  }

  @Test
  public void testGetLocatedRoomIndex() {
    TargetCharacter character = new TargetCharacter("Dr. Hyde", 100);
    assertEquals(0, character.getLocation());
  }

  @Test
  public void testSetLocatedRoomIndex() {
    TargetCharacter character = new TargetCharacter("Dr. Strange", 30);
    character.setLocation(5);
    assertEquals(5, character.getLocation());
  }

  @Test
  public void testToString() {
    TargetCharacter character = new TargetCharacter("Dr. Who", 60);
    String expected = "Dr. Who: with health point: 60, room index: 0.";
    assertEquals(expected, character.toString());
  }
}