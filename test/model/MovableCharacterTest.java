package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit tests for the MovableCharacter.
 */
public class MovableCharacterTest {

  @Test
  public void setLocation() {
    // Create a movable character
    MovableCharacter character = new Player("John", 0, 5, true, 1);

    // Set a new location
    character.setLocation(2);

    // Check if the location was set correctly
    assertEquals(2, character.getLocation());
  }

  @Test
  public void getName() {
    // Create a movable character
    MovableCharacter character = new Player("John", 0, 5, true, 1);

    // Check if the name is correct
    assertEquals("John", character.getName());
  }


  @Test
  public void getDetails() {
    // Create a movable character
    MovableCharacter character = new Player("John", 0, 5, true, 1);

    // Check if getDetails() returns the correct value
    assertEquals("MovableCharacter [name = John, location = 0]", character.getDetails());
  }
}
