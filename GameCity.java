import becker.robots.*;
import java.awt.*;

/*
 * The class which is used to edit the city to add the floor icon to each intersection
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class GameCity extends City
{
  /*
   * Constructor for the new GameCity
   * @param filename The name of the text file the city is loading
   * */
  public GameCity(String filename)
  {
    super(filename);
  }
  /*
   * Method to make the intersection and set it to the new icon
   * @param str The street the intersection is made
   * @param ave The avenue the intersection is made
   * */
  protected Intersection makeIntersection(int str, int ave)
   {
    Intersection floorTile = new Intersection(this, str, ave);
    floorTile.setIcon(new FloorIcon());
    return floorTile;
   }
  

}
    