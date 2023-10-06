package world;

import static org.junit.Assert.*;

import org.junit.Test;

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
      assertEquals(0, character.getLocatedRoomIndex());
  }

  @Test
  public void testSetLocatedRoomIndex() {
      TargetCharacter character = new TargetCharacter("Dr. Strange", 30);
      character.setLocatedRoomIndex(5);
      assertEquals(5, character.getLocatedRoomIndex());
  }

  @Test
  public void testToString() {
      TargetCharacter character = new TargetCharacter("Dr. Who", 60);
      String expected = "Dr. Who: with health point: 60, room index: 0.";
      assertEquals(expected, character.toString());
  }
}