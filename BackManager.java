import becker.robots.*;

/*
 * The class that handles when the player wants to use the back command
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class BackManager
{
  private int previousAve, previousStr;
  private Direction previousDir;
  private City gameCity;
  
  /*
   * Constructor for the back manager
   * @param city The city the current player is in 
   * */
  public BackManager(City city)
  {
    gameCity = city;
  }
  /**
   * Set the previous location of the player, when the player moves it will pass its previous position through this method
   * @param str The previous street
   * @param ave The previous avenue
   * @param dir The previous direction
   * */
  public void setPreviousLocation(int str, int ave, Direction dir)
  {
    previousStr = str;
    previousAve = ave;
    previousDir = dir;
  }
  /**
   * Redraws the player in the previous position they were. Saving the current position as the old.
   * @param Player This is the player being passed into the command to get the current locations
   * @return The new player that has been created
   * */
  public Player back(Player player)
  {
    if (previousDir != null) {
      player.setTransparent();
      int currentAve = player.getAvenue();
      int currentStr = player.getStreet();
      Direction currentDir = player.getDirection();
      player = new Player(gameCity, previousStr, previousAve, previousDir, player.getPotionCount(), player.getXp(), player.hasKey(), player.hasSword(), player.getWeight());
      previousAve = currentAve;
      previousStr = currentStr;
      previousDir = currentDir;
    } else {
      System.out.println("You can't back at this time.");
    }    
    return player;
  }
}