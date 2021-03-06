import becker.robots.icons.*;
import java.awt.*;

/*
 * This class is the icon creator for treasure icon
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class TreasureIcon extends Icon
{
  /*
   * Constructor for the treasure icon, uses the super constructor from icon
   * */
  public TreasureIcon()
  {
    super();
  }
   /**
   * Method that is called when an icon is created
   * @param g The graphic that is used to draw image on
   * */
  protected void paintIcon(Graphics g)
  {
    Image image = Toolkit.getDefaultToolkit().getImage("treasure.png");
    g.drawImage(image,0,0,null);
  }
}