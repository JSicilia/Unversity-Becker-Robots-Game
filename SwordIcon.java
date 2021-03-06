import becker.robots.icons.*;
import java.awt.*;

/*
 * This class is the icon creator for the players icon with sword
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class SwordIcon extends Icon
{
  /*
   * Constructor for the sword icon, uses the super constructor from icon
   * */
  public SwordIcon()
  {
    super();
  }
  /**
   * Method that is called when an icon is created
   * @param g The graphic that is used to draw image on
   * */
  protected void paintIcon(Graphics g)
  {
    Image image = Toolkit.getDefaultToolkit().getImage("sword.png");
    g.drawImage(image,0,0,null);
  }
}
  