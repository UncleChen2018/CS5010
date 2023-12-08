package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Test class for model.
 */
public class MockModelNew implements GameModel {

  private StringBuilder log;

  private boolean gameOverWithMaxTurn;
  private boolean gameOverWithWinner;

  public MockModelNew(StringBuilder log) {
    this.log = log;
  }

  public void setGameOverWithMaxTurn(boolean gameOverWithMaxTurn) {
    this.gameOverWithMaxTurn = gameOverWithMaxTurn;
  }

  public void setGameOverWithWinner(boolean gameOverWithWinner) {
    this.gameOverWithWinner = gameOverWithWinner;
  }

  @Override
  public boolean isGameOverWithMaxTurn() {
    log.append("isGameOverWithMaxTurn called\n");
    return gameOverWithMaxTurn;
  }

  @Override
  public boolean isGameOverWithWinner() {
    log.append("isGameOverWithWinner called\n");
    return gameOverWithWinner;
  }

  @Override
  public void setupNewWorld(Readable source) {
    log.append("setupNewWorld called\n");
  }

  @Override
  public BufferedImage drawWorld() {
    log.append("drawWorld called\n");
    return null;
  }

  @Override
  public String getDetails() {
    log.append("getDetails called\n");
    return null;
  }

  @Override
  public String getName() {
    log.append("getName called\n");
    return null;
  }

  @Override
  public void setPlayerLocation(int playerId, int location) {
    log.append(String.format("setPlayerLocation called, playerId = %d, location = %d\n", playerId,
        location));
  }

  @Override
  public void setPetLocation(int location) {
    log.append(String.format("setPetLocation called, location = %d\n", location));
  }

  @Override
  public int getCurrentPlayer(int turn) {
    log.append(String.format("getCurrentPlayer called, turn = %d\n", turn));
    return 0;
  }

  @Override
  public int getCurrentPlayer() {
    log.append("getCurrentPlayer called\n");
    return 0;
  }

  @Override
  public void addNewPlayer(String name, int initLocation, int capacity, boolean isHumanControl) {
    log.append(String.format(
        "addNewPlayer called, name = %s, initLocation = %d, capacity = %d, isHumanControl = %b\n",
        name, initLocation, capacity, isHumanControl));
  }

  @Override
  public void moveTargetNextRoom() {
    log.append("moveTargetNextRoom called\n");
  }

  @Override
  public String queryRoomItem(int location) {
    log.append(String.format("queryRoomItem called, location = %d\n", location));
    return null;
  }

  @Override
  public void pickUpitem(int playerId, int itemId) {
    log.append(String.format("pickUpitem called, playerId = %d, itemId = %d\n", playerId, itemId));
  }

  // Implement other methods of the GameModel interface in a similar way

  @Override
  public void attackTarget(int damage) {
    log.append(String.format("attackTarget called, damage = %d\n", damage));
  }

  @Override
  public void removePlayerItem(int playerId, int itemId) {
    log.append(
        String.format("removePlayerItem called, playerId = %d, itemId = %d\n", playerId, itemId));
  }

  @Override
  public void setWinner(int playerId) {
    log.append(String.format("setWinner called, playerId = %d\n", playerId));
  }

  @Override
  public boolean isAttackInvisible(int playerId) {
    log.append(String.format("isAttackInvisible called, playerId = %d\n", playerId));
    return false;
  }

  @Override
  public String queryPlayerItems(int playerId) {
    log.append(String.format("queryPlayerItems called, playerId = %d\n", playerId));
    return null;
  }

  @Override
  public void movePetNextRoom() {
    log.append("movePetNextRoom called\n");
  }

  @Override
  public void teleportPetLocation(int location) {
    log.append(String.format("teleportPetLocation called, location = %d\n", location));
  }

  @Override
  public void moveNextTurn() {
    log.append("moveNextTurn called\n");
  }

  @Override
  public void setMaxTurn(int maxTurn) {
    log.append(String.format("setMaxTurn called, maxTurn = %d\n", maxTurn));
  }

  @Override
  public String getWorldName() {
    log.append("getWorldName called\n");
    return null;
  }

  @Override
  public int getRoomCount() {
    log.append("getRoomCount called\n");
    return 0;
  }

  @Override
  public int[] getRoomRect(int index) {
    log.append(String.format("getRoomRect called, index = %d\n", index));
    return null;
  }

  @Override
  public int[] getWorldSize() {
    log.append("getWorldSize called\n");
    return null;
  }

  @Override
  public String getRoomName(int index) {
    log.append(String.format("getRoomName called, index = %d\n", index));
    return null;
  }

  @Override
  public int getTargetLocation() {
    log.append("getTargetLocation called\n");
    return 0;
  }

  @Override
  public int getPlayerCount() {
    log.append("getPlayerCount called\n");
    return 0;
  }

  @Override
  public ArrayList<Integer> getRoomCharacter(int location) {
    log.append(String.format("getRoomCharacter called, location = %d\n", location));
    return null;
  }

  @Override
  public String queryTargetDetails() {
    log.append("queryTargetDetails called\n");
    return null;
  }

  @Override
  public String queryPlayerDetails(int playerId) {
    log.append(String.format("queryPlayerDetails called, playerId = %d\n", playerId));
    return null;
  }

  @Override
  public String queryRoomDetails(int location) {
    log.append(String.format("queryRoomDetails called, location = %d\n", location));
    return null;
  }

  @Override
  public int getCurrentTurn() {
    log.append("getCurrentTurn called\n");
    return 0;
  }

  @Override
  public int getMaxTurn() {
    log.append("getMaxTurn called\n");
    return 0;
  }

  @Override
  public int getWinner() {
    log.append("getWinner called\n");
    return 0;
  }

  @Override
  public String getPlayerString(int playerId) {
    log.append(String.format("getPlayerString called, playerId = %d\n", playerId));
    return null;
  }

  @Override
  public boolean isNeighbor(int quest, int base) {
    log.append(String.format("isNeighbor called, quest = %d, base = %d\n", quest, base));
    return false;
  }

  @Override
  public int getPlayerLocation(int playerId) {
    log.append(String.format("getPlayerLocation called, playerId = %d\n", playerId));
    return 0;
  }

  @Override
  public ArrayList<Integer> getRoomItems(int location) {
    log.append(String.format("getRoomItems called, location = %d\n", location));
    return null;
  }

  @Override
  public String querryItemInfo(int itemId) {
    log.append(String.format("querryItemInfo called, itemId = %d\n", itemId));
    return null;
  }

  @Override
  public ArrayList<Integer> getPlayerItems(int playerId) {
    log.append(String.format("getPlayerItems called, playerId = %d\n", playerId));
    return null;
  }

  @Override
  public String getItemName(int itemId) {
    log.append(String.format("getItemName called, itemId = %d\n", itemId));
    return null;
  }

  @Override
  public int getTargetHealth() {
    log.append("getTargetHealth called\n");
    return 0;
  }

  @Override
  public int getPetLocation() {
    log.append("getPetLocation called\n");
    return 0;
  }

  @Override
  public ArrayList<Integer> getRoomNeighbors(int location) {
    log.append(String.format("getRoomNeighbors called, location = %d\n", location));
    return new ArrayList<Integer>();
  }

  @Override
  public boolean isHumanPlayer(int playerId) {
    log.append(String.format("isHumanPlayer called, playerId = %d\n", playerId));
    return false;
  }

  @Override
  public String getTargetString() {
    log.append("getTargetString called\n");
    return null;
  }

  @Override
  public int getItemLocation(int itemId) {
    log.append(String.format("getItemLocation called, itemId = %d\n", itemId));
    return 0;
  }

  @Override
  public String queryRoomNeighbors(int playerLocation) {
    log.append(String.format("queryRoomNeighbors called, playerLocation = %d\n", playerLocation));
    return null;
  }

  @Override
  public boolean playerReachCapacity(int playerId) {
    log.append(String.format("playerReachCapacity called, playerId = %d\n", playerId));
    return false;
  }

  @Override
  public int getRoomItemCount(int location) {
    log.append(String.format("getRoomItemCount called, location = %d\n", location));
    return 0;
  }

  @Override
  public String getRoomString(int location) {
    log.append(String.format("getRoomString called, location = %d\n", location));
    return null;
  }

  @Override
  public String getItemString(int itemId) {
    log.append(String.format("getItemString called, itemId = %d\n", itemId));
    return null;
  }

  @Override
  public String getPetString() {
    log.append("getPetString called\n");
    return null;
  }

  @Override
  public boolean isLocationValid(int location) {
    log.append(String.format("isLocationValid called, location = %d\n", location));
    return false;
  }

  @Override
  public int getItemDamage(int itemId) {
    log.append(String.format("getItemDamage called, itemId = %d\n", itemId));
    return 0;
  }

}
