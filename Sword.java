import becker.robots.*;
import javax.swing.ImageIcon;

  /*
  * The class which creates a sword extending the becker imports Thing class
  * Will set the icon of the sword upon creation
  * 
  * @author James Sicilia
  * @version 16.03.2015
  * 
  * */
public class Sword extends Thing
{
  /*
   * Constructor for the sword class
   * @param theCity The city the sword will be in
   * @param str The street the sword will be on
   * @param ave The avenue the sword will be on
   * */
  public Sword(City theCity, int str, int ave)
  {
    super(theCity, str, ave);
    this.setIcon(new SwordIcon());
    }
  }