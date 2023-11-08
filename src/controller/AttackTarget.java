package controller;

import model.GameModel;

/**
 * Represents a command to move the pet to a valid room.
 */

public class AttackTarget extends TurnBaseCommand {

  // if use item
  private int itemId;

  // the default attack, itemId = -1
  public AttackTarget(int playerId) {
    this(playerId, -1);
  }

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

    // TODO test if attack can be successful.
    if (model.isAttackInvisible(playerId)) {
      model.attackTarget(damage);
      stringBuilder.append(String.format("Attack successfully, target get damage of %d\n", damage));
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
