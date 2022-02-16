import becker.robots.*;

/*
 * This class is the enemies class. It extends the RobotSE class from becker to create a robot
 * Using the super constructer anything that the enemy does will go through here
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class Enemy extends RobotSE implements Runnable
{
  private int hitpoints, maxHit, pAve, pStr;
  private boolean inCombat;
  private Direction direction;
  
  /**
   * Create an enemy object extending the RobotSE class. The city and location must be provided by the player.
   * The base hitpoints and max hit must also be supplied.
   * @param theCity The city that the enemy will be spawned into.
   * @param str The street that the enemy will be located.
   * @param ave The avenue that the enemy will be located.
   * @param dir The direction the enemy will be facing.
   * @param hp The hitpoints the enemy starts with.
   * @param maxhit The max the enemy will hit.
   * */
  public Enemy(City theCity, int str, int ave, Direction dir, int hp, int maxhit)
  {
    super(theCity, str, ave, dir);
    direction = dir;
    hitpoints = hp;
    inCombat = false;
    pAve = 1;
    pStr = 1;
    maxHit = maxhit;
    this.setIcon(new EnemyIcon());
  }
  /**
   * The method that is called when the enemy checks if the player is in front of him.
   * @return true if the player is in front of the player
   * */
  
  public boolean checkForPlayer()
  {
    boolean startFight = false;
    int enemyStr = this.getStreet();
    int enemyAve = this.getAvenue();
    if (direction == Direction.NORTH)
    {
      enemyStr = enemyStr - 1;
    } else if (direction == Direction.EAST) {
      enemyAve = enemyAve + 1;
    } else if (direction == Direction.WEST) {
      enemyAve = enemyAve - 1;
    } else {
      enemyStr = enemyStr + 1;
    }
        
    if ((enemyAve == pAve) && (enemyStr == pStr))
    {
      startFight = true;
      inCombat = true;
    }
    return startFight;
  }
  /**
   * Method that sets the players location
   * @param str The street that the player is located
   * @param ave The avenue that the player is located
   * */
  
  public void setPlayerCoords(int str, int ave)
  {
    pStr = str;
    pAve = ave;
  }
  
  /**
   * @return the max hit of the player
   * */
  public int getMax()
  {
    return maxHit;
  }
  /**
   * @return the hitpoints of the player
   * */
  public int getHitpoints()
  {
    return hitpoints;
  }
  /**
   * Method that sets the players hitpoints
   * @param hp The hitpoints that players will be set
   * */
  public void setHitpoints(int hp)
  {
    hitpoints = hp;
  }
  /**
   * Method that runs when a thread is started
   * */
  public void run()
  {
    while(this.frontIsClear() == true && inCombat == false)
    {
      this.move();
    }
    if(!inCombat) {
    this.turnAround();
    direction = this.getDirection();
    run();
    }
  }
}