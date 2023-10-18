package model;



public class Test {

  public static void main(String[] args) {
    Player player = new Player("Jimmy", 10, 2);
    System.out.println(player);

    player.switchToComputerPlay();
    System.out.println(player);
    System.out.println(player.isComputerPlayer());

  }

}
