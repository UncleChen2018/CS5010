package world;

public class Driver {

  public static void main(String[] args) {
    World myWorld = World.getWorldInstance();
    World myWorld2 = World.getWorldInstance();
    if(args.length != 1) {
      return;
    }
    myWorld.setupWorld(args[0]);

    
    System.out.println(String.format("Welcome to the world of %s", myWorld.getWorldName()));
    System.out.println(myWorld == myWorld2);
  }

}
