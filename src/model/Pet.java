package model;

/**
 * The pet class, a special moving character with unique moving logic.
 */
public class Pet extends MovableCharacter {
  private boolean stunned;

  /**
   * Constructs a pet with the specified name and initial location.
   *
   * @param name     The name of the pet.
   * @param location The initial location of the pet.
   */
  public Pet(String name, int location) {
    super(name, location);
    stunned = false;
  }

  /**
   * Generates a formatted string containing the details of the pet.
   *
   * @return The details of the pet.
   */
  public String querryDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Target's pet: ").append(this).append("\n").append("Location: ")
        .append(location).append("\n");
    return stringBuilder.toString();
  }

  /**
   * Checks if the pet is currently stunned.
   *
   * @return {@code true} if the pet is stunned, {@code false} otherwise.
   */
  public boolean isStunned() {
    return stunned;
  }

  /**
   * Sets the pet to be stunned.
   */
  public void setStunned() {
    stunned = true;
  }

  /**
   * Wakes up the pet, removing the stunned status.
   */
  public void wakeUp() {
    stunned = false;
  }

}
