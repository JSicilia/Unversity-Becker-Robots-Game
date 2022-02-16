import becker.robots.*;
import java.awt.Color;

/*
 * This class is the main player and main features of the player.
 * It extends the RobotSE class from becker
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */

public class Player extends RobotSE
{
  private int hitpoints, maxHitpoints, xp, potionCount, maxHit, level, weight;
  private boolean hasKey, hasSword;

    /**
   * Create a player object extending the RobotSE class. The city and location must be provided by the player.
   * The base hitpoints and max hit must also be supplied.
   * @param theCity The city that the enemy will be spawned into.
   * @param str The street that the enemy will be located.
   * @param ave The avenue that the enemy will be located.
   * @param dir The direction the enemy will be facing.
   * @param count The amount of potions the player has
   * @param key Whether the player has the key or not.
   * @param sword Whether the player has the sword or not.
   * */
  public Player(City theCity, int str, int ave, Direction dir, int count, int currentXp, boolean key, boolean sword, int playerWeight)
  {
    super(theCity, str, ave, dir);
    hasKey=key;
    hasSword=sword;
    hitpoints = 10;
    maxHitpoints = 10;
    xp = currentXp;
    level = 1;
    potionCount = count;
    maxHit = 6;
    weight = playerWeight;
    if (sword == false) {
      this.setIcon(new PlayerIcon());
    } else {
      this.setIcon(new PlayerSwordIcon());
    }
  }
  /**
   * The method that is called when setting an old player transparent
   * */
  public void setTransparent()
  {
    this.setIcon(new TransparentIcon()); 
  }
  
  public int getWeight()
  {
    return weight;
  }
  /**
   * @return the player hitpoints
   * */
  public int getHitpoints()
  {
    return hitpoints;
  }
  /**
   * @return the players potion count
   * */
  public int getPotionCount()
  {
    return potionCount;
  }
  /**
   * The method that sets the players hitpoints
   * */
  public void setHitpoints(int hp)
  {
    hitpoints = hp;
  }
  /*
   * @return the players max hitpoints
   * */
  public int getMaxHp()
  {
    return maxHitpoints;
  }
  /*
   * Method to set the players max hitpoints. Used for loading the player from a save
   * @param max The max hitpoints the player will be set to
   * */
  public void setMaxHp(int max)
  {
    maxHitpoints = max;
  }
  /**
   * @return the players max hit
   * */
  public int getMax()
  {
    return maxHit;
  }
  /*
   * Method which sets the players maxhit
   * @param max The max hit of the player
   * */
  public void setMax(int max)
  {
    maxHit = max;
  }
  /**
   * The method that is called when the player has found the key
   * */
  public void gotKey()
  {
    System.out.println("You picked up a key!");
    hasKey = true;
  }
  /**
   * @return true is the player has the key
   * */
  public boolean hasKey()
  {
    return hasKey;
  }
  
  public int getXp()
  { 
    return xp;
  }
  /**
   * The method that is called when the player is given xp
   * */
  public void giveXP(int amount)
  {
    xp += amount;
    System.out.println("You gained " + amount + " xp!");
  }
  
  /**
   * The method that is called when the it checks if the player has advanced a level
   * */
  public void checkLevel()
  {
    int newLevel = xp /100;
    int difference = newLevel - level;
    if (newLevel != level)
    {
      level = newLevel;
      maxHit += difference;
      maxHitpoints += difference;
      System.out.println("You advanced to level " + newLevel + "!");
      System.out.println("Your max hitpoints are now " + maxHitpoints);
      System.out.println("Your max hit is now " + maxHit);
    }
  }
  /*
   * @return The level of the player
   * */
  public int getLevel()
  {
    return level;
  }
  /*
   * Method to set the players level, used with saving and loading the player
   * @param newLevel The level the player is going to be set
   * */
  public void setLevel(int newLevel)
  {
    level = newLevel;
  }
  /**
   * The method that is called when it gives the player the sword
   * */
  public void giveSword()
  {
    if ((weight + 40) < 100) {
      weight+=40;
      hasSword=true;
      maxHit+=2;
      System.out.println("You found and equipped the sword! You max hit has increased to " + maxHit);
      this.setIcon(new PlayerSwordIcon());
    } else {
      System.out.println("You are too heavy!");
    }
  }
  /**
   * The method that is called when the program checks if the player has the sword.
   * @return true if the player has the sword
   * */
  public boolean hasSword()
  {
    return hasSword;
  }
  /**
   * The method that is called when the player picks up a potion.
   * */
  public void addPotion()
  {
    if ((weight + 20) < 100) {
      weight+= 20;
      potionCount +=1;
      this.pickThing();
      System.out.println("You picked up a potion!");
      System.out.println("You now have " + potionCount + " potions.");
    } else {
      System.out.println("You are too heavy!");
    }
  }
  /*
   * Method to drop a potion, this is to lower the players weight
   * */
  public void dropPotion()
  {
    if(potionCount > 0) {
      potionCount--;
      weight-=20;
      System.out.println("You spill the potion over the floor. Lowering your weight.");
    }
  }
  /**
   * The method that is called when the player drinks a potion.
   * */
  public void drinkPotion()
  {
    if(potionCount > 0)
    { 
      if(hitpoints < (maxHitpoints -5))
      {
        hitpoints+=5;
        potionCount--;
        weight-=20;
        System.out.println("You restored 5 hitpoints. You now have "+hitpoints+ " hitpoints.");
      } else if (hitpoints > (maxHitpoints-5) &&  hitpoints < maxHitpoints)  {
        System.out.println("You restored " + (maxHitpoints-hitpoints) + " hitpoints. You now have " + maxHitpoints+ " hitpoints.");
        hitpoints = maxHitpoints;
        potionCount--;
        weight-=20;
      } else if(hitpoints == maxHitpoints){
        System.out.println("You already have full hitpoints.");
      }
      System.out.println("You now have " + potionCount + " potions.");
    } else {
      System.out.println("You don't have any potions");
    }
  }
  
  public String directionFinder(Direction dir) {
    String direction = null;
    if (dir == Direction.NORTH) {
      direction = "north";
    } else if (dir == Direction.SOUTH) {
      direction = "south";
    } else if (dir == Direction.EAST) {
      direction = "east";
    } else {
        direction = "west";
    }
    return direction;
  }
}