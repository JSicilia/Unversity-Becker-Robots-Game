import becker.robots.icons.*;
import java.awt.*;

public class PlayerSwordIcon extends Icon
{
    /*
   * Constructor for the player icon, uses the super constructor from icon
   * */
  public PlayerSwordIcon()
  {
    super();
  }
  /**
   * Method that is called when an icon is created
   * @param g The graphic that is used to draw image on
   * */
  protected void paintIcon(Graphics g)
  {
    Image image = Toolkit.getDefaultToolkit().getImage("playersword.png");
    g.drawImage(image,0,0,null);
  }
}
  