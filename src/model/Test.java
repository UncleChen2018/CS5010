package model;



public class Test {

  public static void main(String[] args) {
    Player player = new Player("Jimmy", 10, 2);
    System.out.println(player);

    player.switchToComputerPlay();
    System.out.println(player.getDetails());
    System.out.println(player.isComputerPlayer());
    player.getDetails();
    
    Item item = new Item("da fe ji", 99, 10);
    System.out.println(item.getDetails());
    item.setOwner(player);
    System.out.println(item.getDetails());
    player.addItem(item);
    System.out.println(player.getDetails());

  }

}
