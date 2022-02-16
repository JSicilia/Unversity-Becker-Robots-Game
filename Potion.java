import becker.robots.*;
import javax.swing.ImageIcon;

/*
 * This class is the icon creator for the players icon without the sword
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class Potion extends Thing
{
    /*
   * Constructor for the potion class
   * @param theCity The city the potion will be in
   * @param str The street the potion will be on
   * @param ave The avenue the potion will be on
   * */
  public Potion(City theCity, int str, int ave)
  {
    super(theCity, str, ave);
    this.setIcon(new PotionIcon());
  }
}