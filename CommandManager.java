import java.util.*;
import java.io.*;
import becker.robots.*;

/*
 * The command manager of the game, this is where all the player inputs will be done
 * along with winning the game 
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * 
 * */
public class CommandManager implements Runnable 
{
  //City Management
  private RoomStorage rooms;
  private GameCityFrame cityView;
  private City gameCity;
  private String oldRoom;
  
  //Booleans
  private boolean isInterrupted, inCombat, alive;
  
  //Robot Entities
  private Enemy attackingEnemy;
  private Enemy boss;
  private Player thePlayer;
  
  //Command management
  private Parser parser;
  private BackManager backManager;
  
  
  /**
   *  Constructor the command manager. This requires a range of parameter all requiring to be valid.
   * @param p The defined player
   * @param eArray ArrayList full of all the enemies
   * @param view The game frame
   * @param city The city of the game
   * @param theRooms A storage of all the rooms in the game
   */
  public CommandManager(Player p, GameCityFrame view, City city, RoomStorage theRooms)
  {
    thePlayer = p;
    cityView = view;
    rooms = theRooms;
    alive = true;
    gameCity = city;
    parser = new Parser();
    backManager = new BackManager(city);
    boss = new Enemy(gameCity, 11, 4, Direction.SOUTH, 25, 8);
    boss.setIcon(new TransparentIcon());
    oldRoom = "Starting Room";
  }
  /*
   * The method for when the command manager thread starts
   * */
  public void run()
  {
    boolean playing = true;
    while (isInterrupted == false) 
    {
      if (inCombat == false && alive == true)
      {
        Command command = parser.getCommand();
        processCommand(command);
        alive = checkPlayerHitpoints();
      } else if (inCombat = true && alive == true) {
        System.out.println("Your hp is " + thePlayer.getHitpoints());
        System.out.println("Your enemies hitpoints are  " + attackingEnemy.getHitpoints());
        Command command = parser.getCommand();
        inCombat = attackCommand(command, attackingEnemy);
        alive = checkPlayerHitpoints();
      } else {
        System.out.println("Oh dear you have died. Game over.");
        isInterrupted = true;
      }
      if(boss.getHitpoints() <= 0){
        isInterrupted = true;
        System.out.println("Congratulations, you have have won the game!");
      }
    }
    System.out.println("Thank you for playing.");
  }
  /*
   * @return the current active player
   * */
  public Player getPlayer()
  {
    return thePlayer;
  }
  /*
   * Method for checking entered commands when the player is out of combat
   * @return true if the player is alive
   * */
  public boolean processCommand(Command command) 
  {
    boolean alive = true;
    CommandWord commandWord = command.getCommandWord();
    
    switch (commandWord) 
    {
      case UNKNOWN:
        System.out.println("I don't know what you mean...");
        break;
        
      case HELP:
        printHelp();
        break;
        
      case MOVE:
        backManager.setPreviousLocation(thePlayer.getStreet(), thePlayer.getAvenue(), thePlayer.getDirection());
        move();
        break;
        
      case TURN:
        backManager.setPreviousLocation(thePlayer.getStreet(), thePlayer.getAvenue(), thePlayer.getDirection());
        turn(command);
        break;
        
      case QUIT:
        alive = quit(command);
        break;
        
      case PICKUP:
        if(thePlayer.canPickThing()){
        pickup();
      } else {
        System.out.println("There is nothing here.");
      }
      break;
      
      case DRINK:
        drink();
        break;
        
      case BACK:
        thePlayer = backManager.back(thePlayer);
        break;
        
      case OPEN:
        if((thePlayer.getAvenue() == 4) && (thePlayer.getStreet() == 12) && (thePlayer.getDirection() == Direction.SOUTH))
      {
        open();
      } else {
        System.out.println("You are not near the chest!");
      }
      break;
      
      case LOAD:
        loadFile(command);
        break;
        
      case SAVE:
        saveFile(command);
        break;
        
      case DROP:
        thePlayer.dropPotion();
        break;
        
    }
    checkRoom();
    return alive;
  }
  
  /*
   * @return true if in combat
   * */
  public boolean getState() 
  {
    return inCombat;
  }
  /*
   * The method called when the player tries to open the chest
   * */
  private void open()
  {
    if(thePlayer.hasKey())
    {
      boss.setIcon(new EnemyIcon());
      thePlayer.turnAround();
      bossFight();
      inCombat = true;
      attackingEnemy = boss;
    } else {
      System.out.println("You don't have the key!");
    }
  }
  /*
   * The method called if the player is in combat, allowing only combat commands to be input.
   * @return true if the player is in combat
   * */
  public boolean attackCommand(Command command, Enemy enemy) 
  {
    CommandWord commandWord = command.getCommandWord();
    
    switch (commandWord) 
    {
      case UNKNOWN:
        System.out.println("I don't know what you mean...");
        break;
        
      case ATTACK:
        inCombat = attack(enemy);
        break;
        
      case DRINK:
        drink();
        break;
        
    }
    checkPlayerHitpoints();
    return inCombat;
  }
  /*
   * Method that is called when the enemy sets the player into combat
   * */
  public void setCombatState(boolean state, Enemy enemy)
  {
    inCombat = state;
    attackingEnemy = enemy;
    System.out.println("You are now in combat, your commands are attack and drink!\nPlease finish any previous commands");
  }
  
  /**
   * Print out some help information.
   * Here we print some stupid, cryptic message and a list of the 
   * command words.
   */
  private void printHelp() 
  {
    System.out.println("You have to find the treasure, find the key and open the treasure!");
    System.out.println("Move throughout the dungeon to find it.");
    System.out.println("\nCombat commands are: attack drink");
    System.out.println("\nYour command words are:");
    parser.showCommands();
  }
  
  /** 
   * Move the player forward, if something is in the way print that something is in the way.
   */
  private void move() 
  {
    if (thePlayer.frontIsClear())
    {
      if (inCombat == true) {
        System.out.print("You are in combat\n");
      } else {
        thePlayer.move();
      }
    }
  }
  
  /** 
   * "Turn" was entered. Check the rest of the command to see
   * whether we turn left, right or around.
   */
  private void turn(Command command)
  {
    if(!inCombat){
      if(!command.hasSecondWord()) 
      {
        // if there is no second word, we don't know which way to turn...
        System.out.println("turn which way?");
        return;
      }
      
      String direction = command.getSecondWord();
      if(direction.equals("left")) 
      { thePlayer.turnLeft();}
      else if(direction.equals("right")) 
      { thePlayer.turnRight();}
      else if(direction.equals("around")) 
      { thePlayer.turnAround();}
      else 
      { System.out.println("You can not turn that way");}
      System.out.println("You are currently facing " + thePlayer.getDirection());
    }
  }
  
  
  /** 
   * "Quit" was entered. Check the rest of the command to see
   * whether we really quit the game.
   * @return true, if this command quits the game, false otherwise.
   */
  private boolean quit(Command command) 
  {
    if(command.hasSecondWord())
    {
      System.out.println("Quit what?");
      return true;
    }
    else 
    {
      return false;  // signal that we want to quit
    }
  }
  /**
   * The method that picks up objects, in certain locations it will either pickup a key or sword.
   * Otherwise it will pickup a potion if something is there.
   * */
  private void pickup()
  {
    if(thePlayer.getAvenue() == 0 && thePlayer.getStreet() == 4)
    {
      thePlayer.gotKey();
      thePlayer.pickThing();
    } else if (thePlayer.getAvenue() == 8 && thePlayer.getStreet() == 0)
    {
      thePlayer.giveSword();
       thePlayer.pickThing();
    } else {
      thePlayer.addPotion();
    } 
  }
  
  /*
   * Method to check the players hitpoints 
   * @return true if the player is alive
   * */
  private boolean checkPlayerHitpoints()
  {
    int hp = thePlayer.getHitpoints();
    boolean playerAlive = true;
    if (hp <= 0)
    {
      playerAlive = false;
    }
    return playerAlive;
  }
  /*
   * The method that us called when the player attacks the enemy
   * @return true is the player is in combat
   * */
  private boolean attack(Enemy enemy)
  {
    boolean inCombat = true;
    Random rand = new Random();
    Random enemyRand = new Random();
    int playerHp = thePlayer.getHitpoints();
    int playerMaxHit = thePlayer.getMax();   
    int playerHit = rand.nextInt(playerMaxHit);
    int enemyHp = enemy.getHitpoints();
    int enemyMaxHit = enemy.getMax();
    int enemyHit = enemyRand.nextInt(enemyMaxHit);
    
    thePlayer.setHitpoints(playerHp - enemyHit);
    enemy.setHitpoints(enemyHp - playerHit);
    
    if ((enemyHp - playerHit) <= 0)
    {
      enemy.setIcon(new TransparentIcon());
      inCombat = false;
      thePlayer.giveXP((65));
      thePlayer.checkLevel();
      System.out.println("You defeated the enemy!");
    }
    return inCombat;
  }
  /*
   * The method that checks the room the player and changes room according to the room returned
   * Applying any effects on the player according to the room type
   * */
  private void checkRoom()
  {
    String[] roomArray = rooms.checkRoom(thePlayer.getStreet(), thePlayer.getAvenue());
    String currentRoom = roomArray[0];
    if (!currentRoom.equals(oldRoom))
    {
      oldRoom = currentRoom;
      System.out.println("You are in the " + currentRoom);
    }
    
    if(roomArray[0] =="Dungeon Room"){
      cityView.changeOrigin(0,3);
  } else if(roomArray[0] =="Dungeon Room 2") {
    cityView.changeOrigin(3,3);
    cityView.changeSize(370, 390);
  } else if(roomArray[0] =="Dungeon Room 3") {
    cityView.changeOrigin(0,6);
    cityView.changeSize(370, 705);
  } else if(roomArray[0] =="Poison Room") {
    cityView.changeOrigin(3,0);
  } else if(roomArray[0] =="Corridor") {
    cityView.changeOrigin(6,4);
    cityView.changeSize(160, 605);
  } else if(roomArray[0] =="Boss Room") {
    cityView.changeOrigin(11, 3);
    cityView.changeSize(370, 390);
  } else if(roomArray[0] =="Starting Room") {
    cityView.changeOrigin(0,0);
    cityView.changeSize(370, 390);
  }
  
  if(roomArray[1] == "poison") {
    int playerHp = thePlayer.getHitpoints();
    thePlayer.setHitpoints(playerHp - 1);
    System.out.println("You are hurt by poison in the room! You now have " + thePlayer.getHitpoints() + " hp.");
  }
  }
  /*
   * The method that is called when a player loads a file
   * @param command The second word of the command, in this case it is the name of the file you
   * want to load
   * */
  public void loadFile(Command command)
  {
  
    if(!command.hasSecondWord()) 
    {
      // if there is no second word, we don't know which way to turn...
      System.out.println("load what?");
      return;
    }
    
    String fileName = command.getSecondWord();
    Properties prop = new Properties();
    InputStream is = null;
    
    try {
      is = this.getClass().getClassLoader().getResourceAsStream(fileName + ".properties");
    } catch (Exception e)
    { is = null;
      System.out.println("There is no file under this name!");
    }
    
    try {
      if (is == null) {
        // tTry
        is = getClass().getResourceAsStream(fileName + ".properties");
      }
      
      prop.load(is);
      int playerstr = Integer.parseInt(prop.getProperty("playerstr"));
      int playerave = Integer.parseInt(prop.getProperty("playerave"));
      int potioncount = Integer.parseInt(prop.getProperty("potioncount"));
      int weight = Integer.parseInt(prop.getProperty("weight"));
      int level = Integer.parseInt(prop.getProperty("level"));
      int max = Integer.parseInt(prop.getProperty("max"));
      int xp = Integer.parseInt(prop.getProperty("xp"));
      int hp = Integer.parseInt(prop.getProperty("hp"));
      int maxHp = Integer.parseInt(prop.getProperty("maxhp"));
      boolean key = Boolean.parseBoolean(prop.getProperty("key"));
      boolean sword = Boolean.parseBoolean(prop.getProperty("sword"));
      System.out.println("Successfully loaded file.");
      
      //Get the direction of the player according to what string was saved
      String direction = prop.getProperty("playerdir");
      Direction loadedDirection = null;
     
      if(direction.equals("north")) {
        loadedDirection = Direction.NORTH;
      } else if (direction.equals("south")) {
        loadedDirection = Direction.SOUTH;
      } else if (direction.equals("east")) {
        loadedDirection = Direction.EAST;
      } else if (direction.equals("west")){
        loadedDirection = Direction.WEST;
      }
      
      thePlayer.setIcon(new TransparentIcon());
      thePlayer = new Player(gameCity, playerstr, playerave, loadedDirection, potioncount, xp, key, sword, weight);
      thePlayer.setLevel(level);
      thePlayer.setMax(max);
      thePlayer.setHitpoints(hp);
      thePlayer.setMaxHp(maxHp);
      checkRoom();
    }
    catch (Exception e ) {
      System.out.println("There is no file under this name!");
    }
  }
  /*
   * The method that is called when the user saves a file
   * @param command The second word which in this case is the name which you want to call the file
   * */
  public void saveFile(Command command)
  {
    if(!command.hasSecondWord()) 
    {
      // if there is no second word, we don't know which way to turn...
      System.out.println("save what?");
      return;
    }
    try {
      String fileName = command.getSecondWord();
      Properties saveFileInfo = new Properties();
      
      //All the properties to save of the player
      saveFileInfo.setProperty("playerstr", String.valueOf(thePlayer.getStreet()));
      saveFileInfo.setProperty("playerave", String.valueOf(thePlayer.getAvenue()));
      saveFileInfo.setProperty("playerdir", thePlayer.directionFinder(thePlayer.getDirection()));
      saveFileInfo.setProperty("potioncount", String.valueOf(thePlayer.getPotionCount()));
      saveFileInfo.setProperty("xp", String.valueOf(thePlayer.getXp()));
      saveFileInfo.setProperty("key", String.valueOf(thePlayer.hasKey()));
      saveFileInfo.setProperty("sword", String.valueOf(thePlayer.hasSword()));
      saveFileInfo.setProperty("max", String.valueOf(thePlayer.getMax()));
      saveFileInfo.setProperty("level", String.valueOf(thePlayer.getLevel()));
      saveFileInfo.setProperty("weight", String.valueOf(thePlayer.getWeight()));
      saveFileInfo.setProperty("hp", String.valueOf(thePlayer.getHitpoints()));
      saveFileInfo.setProperty("maxhp", String.valueOf(thePlayer.getMaxHp()));
      
      File saveFile = new File(fileName + ".properties");
      OutputStream out = new FileOutputStream(saveFile);
      saveFileInfo.store(out, "Player Save");
      System.out.println("You saved a file called " + fileName);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  /*
   * The method that is called when the player drinks a potion
   * */ 
  private void drink()
  {
    thePlayer.drinkPotion();
  }
  /*
   * Method to pause the current thread
   * @param time The amount of time to pause the thread in miliseconds
   * */
  private void pause(int time)
  {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
    }
  }
  /*
   * Method that calls the boss fight dialogue
   * */
  private void bossFight()
  {
    System.out.println("How dare you try to steal from me!");
    pause(1000);
    System.out.println("You will pay for this..");
    pause(1000);
  }
}