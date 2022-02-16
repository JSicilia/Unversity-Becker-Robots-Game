import becker.robots.*;

public class TestManager
{
  private City testCity;
  
  public TestManager()
  {
    City.showFrame(false);
    testCity = new City();
  }
  
  public void testPlayerCommands()
  {
    Player testPlayer = new Player(testCity, 0, 0, Direction.EAST,2, 100, false, false, 0);
    
    pause();
    
    System.out.println("TESTING PLAYER COMMANDS \n\nTESTING PLAYER HITPOINTS\nChanging players hitpoints..");
    System.out.println();
    System.out.println("Current player hitpoints " + testPlayer.getHitpoints());
    testPlayer.setHitpoints(5);
    System.out.println("New player hitpoints " + testPlayer.getHitpoints());
    
    pause();
    
    System.out.println("\nTESTING PLAYER POTION COMMANDS\nThe current potion count is " + testPlayer.getPotionCount() + ". This is the default value.");
    Potion testPotion = new Potion(testCity, 0, 0);
    testPlayer.addPotion();
    testPlayer.setHitpoints(1);
    System.out.println("Testing drinking the potions at various hitpoints\nSetting player hitpoints to " + testPlayer.getHitpoints());
    testPlayer.drinkPotion();
    testPlayer.setHitpoints(8);
    System.out.println("Testing drinking the potions at various hitpoints\nSetting player hitpoints to " + testPlayer.getHitpoints());
    testPlayer.drinkPotion();
    testPlayer.setHitpoints(10);
    System.out.println("Testing drinking the potions at various hitpoints\nSetting player hitpoints to " + testPlayer.getHitpoints());
    testPlayer.drinkPotion();
    
    pause();
    
    System.out.println("\nTESTING PLAYER XP GAIN, LEVEL AND MAX HIT");
    System.out.println("Default level is 1\nDefault max hitpoints is 10\nDefault max hit is 6\nGiving the player 100xp");
    System.out.println();
    testPlayer.giveXP(100);
    testPlayer.checkLevel();
    System.out.println("Returning the players current level: " + testPlayer.getLevel());
    System.out.println("Setting the players level to 5");
    testPlayer.setLevel(5);
    System.out.println("Returning the players new level: " + testPlayer.getLevel());
    System.out.println("\nReturning the players current max hit: " + testPlayer.getMax());
    System.out.println("Setting the players max hit to 20");
    testPlayer.setLevel(20);
    System.out.println("Returning the play6ers new max hit: " + testPlayer.getLevel());
    
    
    pause();
    
    System.out.println("\nTESTING PLAYER SWORD AND KEY COMMANDS");
    System.out.println("Default values are false");
    System.out.println("Giving the player the sword and key...");
    testPlayer.gotKey();
    testPlayer.giveSword();
    System.out.println("\nTesting the has key:" + testPlayer.hasKey() + "\nTesting the player has sword:" + testPlayer.hasSword());
   
  }

  
  public void testEnemyCommands()
  {
    testCity = new City();
    Enemy testEnemy = new Enemy(testCity, 1,1, Direction.EAST, 10, 3);
    
    System.out.println("TESTING ENEMY COMMANDS\n\nTESTING ENEMY HITPOINTS");
    System.out.println("Enemy spawned at str: 1. Ave: 1. Direction: east. Hitpoints: " + testEnemy.getHitpoints() + ". Maxhit: " + testEnemy.getMax());
    System.out.println("\nChanging the enemies hitpoints to 5.");
    testEnemy.setHitpoints(5);
    System.out.println("The enemy is now " + testEnemy.getHitpoints() + " hitpoints.");
    
    pause();
    
    System.out.println("\nTESTING ENEMY PLAYER CHECK");
    System.out.println("Enemy can find player: " + testEnemy.checkForPlayer());
    System.out.println("Spawning player and passing coords to enemy");
    Player testPlayer = new Player(testCity, 1, 2, Direction.WEST,2, 100, false, false, 0);
    testEnemy.setPlayerCoords(testPlayer.getStreet(), testPlayer.getAvenue());
    System.out.println("Enemy can find player: " + testEnemy.checkForPlayer());
  }
  
  public void testIcons()
  {
    City.showFrame(true);
    testCity = new City();
    System.out.println("\nTESTING ALL ICONS\n\n");
    Player testPlayer = new Player(testCity, 0, 0, Direction.EAST,2, 100, false, false, 0);
    System.out.println("Changing to player icon.\n");
    pause();
    testPlayer.setIcon(new PlayerIcon());
    pause();
    System.out.println("Changing to player with sword icon.\n");
    testPlayer.setIcon(new PlayerSwordIcon());
    pause();
    System.out.println("Changing to potion icon.\n");
    testPlayer.setIcon(new PotionIcon());
    pause();
    System.out.println("Changing to floor icon.\n");
    testPlayer.setIcon(new FloorIcon());
    pause();
    System.out.println("Changing to key icon.\n");
    testPlayer.setIcon(new KeyIcon());
    pause();
    System.out.println("Changing to sword icon.\n");
    testPlayer.setIcon(new SwordIcon());
    pause();
    System.out.println("Changing to enemy icon.\n");
    testPlayer.setIcon(new EnemyIcon());
    pause();
    System.out.println("Changing to a transparent icon.\n");
    testPlayer.setIcon(new TransparentIcon());
    pause();
    
  }
  
  public void testRoomStorage() 
  {
    RoomStorage testStorage = new RoomStorage();
    Room testRoom = new Room("Test Room", 15,15,2,2, "normal");
    
    pause();
    
    System.out.println("\nTESTING ROOM COMMANDS");
    System.out.println("\nTesting all get commands");
    System.out.println("Getting name: " + testRoom.getName());
    System.out.println("Getting starting street: " + testRoom.getStartingStr());
    System.out.println("Getting starting avenue: " + testRoom.getStartingAve());
    System.out.println("Getting width: " + testRoom.getWidth());
    System.out.println("Getting length: " + testRoom.getLength());
    System.out.println("Getting type: " + testRoom.getType());
    
    pause();
    
    System.out.println("\nTESTING ROOM STORAGE");
    System.out.println("\nAdding a new test room to the storage");
    testStorage.addRoom(testRoom);
    String[] testArray = testStorage.checkRoom(15,15);
    System.out.println("Testing if array is returned from checkRoom: " + testArray[0] + ", " + testArray[1]);
  }
    
  
  private void pause()
  {
    try {
      Thread.sleep(2000); //2 Seconds
    } catch (InterruptedException e) {
    }
  }
  
}