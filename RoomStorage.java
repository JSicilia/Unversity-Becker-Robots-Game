import java.util.ArrayList;
import becker.robots.*;

public class RoomStorage 
{
  private ArrayList<Room> roomStorage;
  private City city;
  /**
   * Concstructs a RoomStorage object, this is the array that holds all of the rooms in the game
   * */
  public RoomStorage()
  {
       // city = theCity;
    roomStorage = new ArrayList<Room>();
    addRoom(new Room("Starting Room", 0, 0, 2, 2, "normal"));
    addRoom(new Room("Dungeon Room", 0,3,2,2, "normal"));
    addRoom(new Room("Dungeon Room 2", 3,3,2,2,"normal"));
    addRoom(new Room("Dungeon Room 3", 0, 6, 2, 5, "normal"));
    addRoom(new Room("Poison Room", 3, 0, 2, 2, "poison"));
    addRoom(new Room("Corridor", 6, 4, 0, 4, "normal"));
    addRoom(new Room("Boss Room", 11, 3 , 2, 2, "normal"));
  }
  /**
   * Method to add a room to an array
   * @param room The room that is going to be added.
   * */
  public void addRoom(Room room)
  {
    roomStorage.add(room);
  }
  
 /**
  * The method that checks the room the player is in
  * @param currentStr The current street of the player.
  * @param currentAve The current avenue of the player.
  * @return an array that holds the room name and the room type the player is in.
  * */
  public String[] checkRoom(int currentStr, int currentAve)
  {
    String[] roomArray = new String[2];
    for(Room item : roomStorage)
    {
      
      
     int finishingAve = (item.getStartingAve() + item.getWidth());
     int finishingStr = (item.getStartingStr() + item.getLength());
     if ((currentStr >= item.getStartingStr()) && (currentAve >= item.getStartingAve()) && (currentStr <= finishingStr) && (currentAve <= finishingAve))
     {
       roomArray[0] = item.getName();
       roomArray[1] = item.getType();
     }
    }
    return roomArray;
  }
}