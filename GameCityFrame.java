import becker.robots.*;
import java.awt.*;
import javax.swing.*;

/*
 * The class which creates a new frame for the game
 * This is so the rooms can change size and move along when the player enters a new room
 * 
 * @author James Sicilia
 * @version 16.03.2015
 * */
public class GameCityFrame
{
  private CityView sp;
  private JFrame cityFrame;
  private JPanel cityPanel;
  private JButton button;
  private RobotUIComponents ui;
  
  /**
   * Constructor for the city frame.
   * 
   * @param city The city
   * @param firstVisibleStreet The first visible street
   * @param firstVisibleAvenue The first visible avenue
   * @param numVisibleStreets The number of visible streets
   * @param numVisibleAvenues The number of visible avenues
   * */
  public GameCityFrame(City city, int firstVisibleStreet, int firstVisibleAvenue, int numVisibleStreets, int numVisibleAvenues)
  {
    
    ui = new RobotUIComponents(city, firstVisibleStreet, firstVisibleAvenue, numVisibleStreets, numVisibleAvenues);
    cityFrame = new JFrame("The Dungeon Of Cikilia");
    cityFrame.setSize(370,390);
    cityPanel = new JPanel(new BorderLayout());
    sp = ui.getCityView();
    cityPanel.add(sp, BorderLayout.CENTER);
    cityFrame.setContentPane(cityPanel);
    sp.setSimSize(105);
    sp.setOrigin(0,0);
    button = ui.getStartStopButton();
    button.doClick();
    cityFrame.setVisible(true);
  }
  /**
   * The method that changes the origin on the map
   * @param str Street to set to the origin
   * @param ave Avenue to set to the origin
   * */
  public void changeOrigin(int str, int ave)
  {
    sp.setOrigin(str,ave);
  }
  /**
   * The method that changes the size of the frame
   * @param width The width of the frame
   * @param length The length of the frame
   * */
    public void changeSize(int width, int length)
  {
   cityFrame.setSize(width,length);
  }
}