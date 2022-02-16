import becker.robots.*;
import java.util.*;
import java.awt.*;

/**
 *  This class is the main class of the "SoC World" application. 
 *  "Soc World" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  James Sicilia
 * @version 15.03.2015
 */

public class Game 
{
  
  //City Management
  private GameCity gameCity;
  private GameCityFrame gameFrame;
  private RoomStorage rooms;
    
  //Robot and Thing Entities
  private Player thePlayer;
  private Enemy enemy1, enemy2, enemy3, enemy4, enemy5;
  private Potion potion1, potion2, potion3, potion4, potion5;
  private Sword sword;
  private TreasureChest chest;
  private Key key;
  
  //Booleans
  private boolean inCombat;
  
  //Command Management
  private CommandManager playerCommands;
  private Parser parser;
  
  //Enemy Array
  private ArrayList<Enemy> enemyArray;
  
  //Enemy Threads
  private Thread t1, t2, t3;
    
  /**
   * Create the game and initialise its internal map.
   */
    public Game() 
    {
      printWelcome();
      City.showFrame(false);
      gameCity = new GameCity("GameRooms.txt");
      rooms = new RoomStorage();
      gameFrame = new GameCityFrame(gameCity, 0, 0, 8, 8);
      thePlayer = new Player(gameCity, 1, 0, Direction.EAST, 2, 100, false, false, 40);
      chest = new TreasureChest(gameCity, 13, 4);     
      addEntities();
      playerCommands = new CommandManager(thePlayer, gameFrame, gameCity, rooms);
      
      //Create thread for command input
      t2 = new Thread(playerCommands);
      t2.start();
      
    }
    

    /**
     * Method which adds all the potions, the sword and the enemies to the map.
     * Also adding all enemies except the boss
     * */
    private void addEntities()
    {
      
      //Add potions to the map
      potion1 = new Potion(gameCity, 1, 1);
      potion2 = new Potion(gameCity, 0, 4);
      potion3 = new Potion(gameCity, 3, 3);
      potion4 = new Potion(gameCity, 3, 6);
      potion5 = new Potion(gameCity, 3, 2);
      
      //Add the sword to the map
      sword = new Sword(gameCity, 0, 8);
      
      //Add the treasure chest to the map
      chest = new TreasureChest(gameCity, 13, 4); 
      chest.setCanBeCarried(false);
      chest.setBlocksEntry(true, true, true, true);
      
      //Add the key to the map
      key = new Key(gameCity, 4, 0);
      
      //Add enemies to the map
      enemy1 = new Enemy(gameCity, 1, 5, Direction.WEST, 10, 3);
      enemy2 = new Enemy(gameCity, 5, 4, Direction.NORTH, 10, 3);
      enemy3 = new Enemy(gameCity, 0, 7, Direction.SOUTH, 10, 4);
      enemy4 = new Enemy(gameCity, 3, 2, Direction.SOUTH, 10, 4);
      enemy5 = new Enemy(gameCity, 3, 1, Direction.SOUTH, 10, 5);
      
      
      //Add the enemies to the Array
      enemyArray = new ArrayList<Enemy>();
      enemyArray.add(enemy1);
      enemyArray.add(enemy2);
      enemyArray.add(enemy3);
      enemyArray.add(enemy4);
      enemyArray.add(enemy5);
      
      
      //Create threads for all the enemies and start them
      Thread t1 = new Thread(enemy3);
      t1.start();
      Thread t3 = new Thread(enemy4);
      t3.start();
      Thread t4 = new Thread(enemy5);
      t4.start();
    }


    /**
     *  Main play routine.  
     */
    public void play() 
    {                
      boolean playing = true;
      
      while (playing == true)
      {
        thePlayer =  playerCommands.getPlayer();
        Enemy inCombatEnemy = null;
        for (Enemy enemy : enemyArray)
        {
          enemy.setPlayerCoords(thePlayer.getStreet(), thePlayer.getAvenue());
          inCombat = enemy.checkForPlayer();
          boolean playerAlive = checkPlayerHitpoints();
          if (inCombat == true && playerAlive == true)
          {
            playerCommands.setCombatState(true, enemy);
            inCombatEnemy = enemy;
          }
        }
        enemyArray.remove(inCombatEnemy);
      }
    }
      
    /**
     * Check for players hitpoints
     * @return true if the player is still alive
     * */
    private boolean checkPlayerHitpoints()
    {
      int hp = thePlayer.getHitpoints();
      boolean quit = true;
      if (hp <= 0)
      {
        quit = false;
      }
      return quit;
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("You awaken on the cold floor..");
        System.out.println("You find yourself in the Dungeon of Cikilia.");
        System.out.println("In your bag you find nothing but a dagger and 2 potions.");
        System.out.println("Find the key to the treasure while surviving through the dungeon.");
        System.out.println("You finish the game by opening the treasure!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
    }
}
