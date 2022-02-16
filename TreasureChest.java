import becker.robots.*;
import javax.swing.ImageIcon;
 /*
  * The class which creates a treasure chest extending the becker imports Thing class
  * Will set the icon of the treasure chest upon creation
  * 
  * @author James Sicilia
  * @version 16.03.2015
  * 
  * */
public class TreasureChest extends Thing
{
  /*
   * Constructor for the TreasureChest class
   * @param theCity The city the chest will be in
   * @param str The street the chest will be on
   * @param ave The avenue the chest will be on
   * */
  public TreasureChest(City theCity, int str, int ave)
  {
    super(theCity, str, ave);
    this.setIcon(new TreasureIcon());
  }
}