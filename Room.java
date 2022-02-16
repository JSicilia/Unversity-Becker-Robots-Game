import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The class for the room.
 *
 * A room represents a set amount of tiles.
 * 
 * @author  James Sicilia
 * @version 16.03.2015
 */

public class Room 
{
    private String roomName, roomType;
    private int startingStr, startingAve, roomWidth, roomLength;       // stores exits of this room.

    /**
     * Construct a room object with a name, the starting street, the starting avenue,
     * width, length and room type.
     * @param name The name of the room
     * @param str The starting street
     * @param ave The starting avenue
     * @param width The width of the room (-1)
     * @param length The length of the room (-1)
     * @param type The type of the room
     */
    public Room(String name, int str, int ave, int width, int length, String type) 
    {
      roomName = name;
      startingStr = str;
      startingAve = ave;
      roomWidth = width;
      roomLength = length;
      roomType = type;
    }
    /**
     * @return the name of the room
     * */
    public String getName()
    {
      return roomName;
    }
    /**
     * @return the type of the room
     * */
    public String getType()
    {
      return roomType;
    }
    /**
     * @return the starting avenue of the room
     * */
    public int getStartingAve()
    {
      return startingAve;
    }
    /**
     * @return the starting street of the room
     * */
    public int getStartingStr()
    {
      return startingStr;
    }
    /**
     * @return the length of the room minus 1
     * */
    public int getLength()
    {
      return roomLength;
    }
    /**
     * @return the width of the room minus 1
     * */
    public int getWidth()
    {
      return roomWidth;
    }
}

