package model;



public class Test {

  public static void main(String[] args) {
    Player player = new Player("Jimmy", 10, 2);
    System.out.println(player);

    player.switchToComputerPlay();
    System.out.println(player);
    System.out.println(player.isComputerPlayer());
    
    Item item = new Item("da fe ji", 99, 10);
    System.out.println(item);
    item.setOwner(player);
    System.out.println(item);

  }

}
