package controller;

import model.GameModel;

/**
 * Represents a command to move the pet to a valid room.
 */

public class AttackTarget extends TurnBaseCommand {

  // if use item
  private int itemId;

  /**
   * Creates an {@code AttackTarget} instance with the default attack (no item
   * used).
   *
   * @param playerId The ID of the player initiating the attack.
   */
  public AttackTarget(int playerId) {
    this(playerId, -1);
  }

  /**
   * Creates an {@code AttackTarget} instance with a specific item used in the
   * attack.
   *
   * @param playerId The ID of the player initiating the attack.
   * @param itemId   The ID of the item used in the attack. Use -1 for the default
   *                 attack with no item.
   */
  public AttackTarget(int playerId, int itemId) {
    super(playerId);
    this.itemId = itemId;
  }

  @Override
  public String execute(GameModel model) throws IllegalArgumentException {
    int damage;
    String actionString;
    if (itemId == -1) {
      damage = 1;
      actionString = "poking in the eye";
    } else {
      damage = model.getItemDamage(itemId);
      actionString = model.getItemName(itemId);
    }
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.format("Player %s try to attack %s using %s ...\n",
        model.getPlayerString(playerId), model.getTargetString(), actionString));

    // TODO think ways to test, and give more message feed back.
    if (model.isAttackInvisible(playerId)) {
      model.attackTarget(damage);
      stringBuilder
          .append(String.format("Attack successfully, target get damage of %d.\n", damage));
      if (itemId != -1) {
        model.removePlayerItem(playerId, itemId);
        stringBuilder.append(String.format("%s is removed.\n", model.getItemString(itemId)));
      }
      if (model.getTargetHealth() <= 0) {
        model.setWinner(playerId);
        stringBuilder.append("Target's health is zero");
        stringBuilder.append(String.format("%s win the game.\n", model.getPlayerString(playerId)));
      }
    } else {
      stringBuilder.append("Someone has seen you, attack has to stop...").append("\n");
    }

    return stringBuilder.toString();

  }

}
