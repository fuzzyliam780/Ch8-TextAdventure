/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */
//import java.io.IOException;
//import java.util.HashMap;

public enum CommandWord
{
    
    GO("go"),QUIT("quit"),HELP("help"),UNKNOWN("?"),CLIMB("climb"),LOOK("look"),INVENTORY("inventory"),
    INTERACT("interact"),GRAB("grab"),BACK("back");
    
    // The command string.
    private String commandString;
    //localisationLoader LL = new localisationLoader();
    //HashMap<String,String> localizedCommands = new HashMap<>();
        
    // A value for each command word along with its
    /*
    // corresponding user interface string.
    GO(localizedCommands.get("GO")), QUIT(localizedCommands.get("QUIT")), 
    HELP(localizedCommands.get("QUIT")), UNKNOWN(localizedCommands.get("UNKNOWN")), 
    CLIMB(localizedCommands.get("CLIMB")), LOOK(localizedCommands.get("LOOK"));
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
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
