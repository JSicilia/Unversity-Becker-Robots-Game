import becker.robots.icons.*;
import java.awt.*;

/*
 * This class is the icon creator for the players icon without the sword
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class PlayerIcon extends Icon
{
    /*
   * Constructor for the player icon, uses the super constructor from icon
   * */
  public PlayerIcon()
  {
    super();
  }
  /**
   * Method that is called when an icon is created
   * @param g The graphic that is used to draw image on
   * */
  protected void paintIcon(Graphics g)
  {
    Image image = Toolkit.getDefaultToolkit().getImage("player.png");
    g.drawImage(image,0,0,null);
  }
}
  