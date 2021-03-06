/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    MOVE("move"),TURN("turn"), QUIT("quit"), HELP("help"), UNKNOWN("?"), ATTACK("attack"), FLEE("flee"),
      DRINK("drink"), TEST("test"), PICKUP("pickup"), SAVE("save"), LOAD("load"), BACK("back"), OPEN("open"), DROP("drop");
    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command word.
     * @param commandWord The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
