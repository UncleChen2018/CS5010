package model;

import java.util.ArrayList;

public class Player extends MovableCharacter {
  private final int ITEM_CAPACITY;
  private ArrayList<Item> itemList;
  private ControlType controlType;
  private int playerID;   // every player has unique id from 0
  
  private enum ControlType {
    HUMAN,
    COMPUTER
  }

  public Player(String name, int location, int itemCapacity, boolean isHumanControl, int id) {
    super(name, location);
    ITEM_CAPACITY = itemCapacity;
    itemList = new ArrayList<Item>(ITEM_CAPACITY);
    if(isHumanControl) {
    controlType = ControlType.HUMAN;
    }
    else {
      controlType = ControlType.COMPUTER;
    }
    playerID = id;
  }

  public void addItem(Item item) {
    if (itemList.size() < ITEM_CAPACITY) {
      itemList.add(item);
    }
  }
  
  public ArrayList<Item> getItemList() {
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


  public String getDetails() {
    return String.format("Player [name = %s, location = %d, controlType = %s, itemCapacity = %d, itemList = %s]",
        name, location, controlType, ITEM_CAPACITY, itemList);
  }


}
