package model;

import java.util.ArrayList;

public class Player extends MovableCharacter {
  private final int ITEM_CAPACITY;
  private ArrayList<Weapon> itemList;
  private ControlType controlType;
  private int playerID;   // every player has unique id from 0
  
  private enum ControlType {
    HUMAN,
    COMPUTER
  }

  public Player(String name, int location, int itemCapacity, boolean isHumanControl, int id) {
    super(name, location);
    ITEM_CAPACITY = itemCapacity;
    itemList = new ArrayList<Weapon>(ITEM_CAPACITY);
    if(isHumanControl) {
    controlType = ControlType.HUMAN;
    }
    else {
      controlType = ControlType.COMPUTER;
    }
    playerID = id;
  }

  public void addItem(Weapon item) {
    if (itemList.size() < ITEM_CAPACITY) {
      itemList.add(item);
    }
  }
  
  public ArrayList<Weapon> getItemList() {
    return itemList;
  }
  

  public void switchToComputerPlay() {
    this.controlType = ControlType.COMPUTER;
  }
  
  public void switchToHumanPlay() {
    this.controlType = ControlType.HUMAN;
  }
  
  public boolean isHumanPlayer() {
    return controlType == ControlType.HUMAN;
  }
  
  public boolean isComputerPlayer() {
    return controlType == ControlType.COMPUTER;
  }


  public String querryDetails() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("-------------------PLAYER DETAILS-------------------\n");
    stringBuilder.append("Player: ").append(this).append("\n")
    .append("Control Type: ").append(controlType).append("\n")
    .append("Location: ").append(location).append("\n")
    .append("Stock|Capacity: ").append(itemList.size()).append("|").append(ITEM_CAPACITY).append("\n")
    .append("Items: ").append(itemList).append("\n")
    ;
        
    return stringBuilder.toString();
  }

  @Override
  public String toString() {
    return String.format("No.%d \"%s\"", playerID, name) ;
  }

  public boolean reachItemCapacity() {
    return itemList.size()== ITEM_CAPACITY;
  }
  
  
  
  

}
