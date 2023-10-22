package model;



public class Test {

  public static void main(String[] args) {
    String dingbatZero = "\uD83C\uDD0B"; // Unicode for DINGBAT CIRCLED SANS-SERIF DIGIT ZERO

    System.out.println(dingbatZero);
    System.out.println(dingbatZero);
    Player player = new Player("Jimmy", 10, 2,true, 0);
    System.out.println(player);

    player.switchToComputerPlay();
    System.out.println(player.getDetails());
    System.out.println(player.isComputerPlayer());
    player.getDetails();
    
    Item item = new Item(0,"da fe ji", 99, 10);
    System.out.println(item.queryDetails());
    item.setOwner(player);
    System.out.println(item.queryDetails());
    player.addItem(item);
    System.out.println(player.getDetails());

  }

}
