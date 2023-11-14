package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test class for pet.
 */
public class PetTest {

  @Test
  public void testQuerryDetails() {
    Pet pet = new Pet("Fluffy", 10);
    String details = pet.querryDetails();
    assertEquals("Target's pet: \"Fluffy\"\nLocation: 10\n", details);
  }

  @Test
  public void testIsStunnedInitiallyFalse() {
    Pet pet = new Pet("Rover", 5);
    assertFalse(pet.isStunned());
  }

  @Test
  public void testSetStunned() {
    Pet pet = new Pet("Buddy", 15);
    pet.setStunned();
    assertTrue(pet.isStunned());
  }

  @Test
  public void testWakeUp() {
    Pet pet = new Pet("Whiskers", 8);
    // Set the pet to stunned state
    pet.setStunned();
    // Wake up the pet
    pet.wakeUp();
    assertFalse(pet.isStunned());
  }
}
