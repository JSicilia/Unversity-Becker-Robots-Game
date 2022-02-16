import becker.robots.*;
import javax.swing.ImageIcon;

 /*
  * The class which creates a key extending the becker imports Thing class
  * Will set the icon of the key upon creation
  * 
  * @author James Sicilia
  * @version 16.03.2015
  * 
  * */
public class Key extends Thing
{
  /*
   * Constructor for the key class
   * @param theCity The city the key will be in
   * @param str The street the key will be on
   * @param ave The avenue the key will be on
   * */
  public Key(City theCity, int str, int ave)
  {
    super(theCity, str, ave);
    this.setIcon(new KeyIcon());
  }
}