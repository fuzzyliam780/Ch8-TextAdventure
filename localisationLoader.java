import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * Loads localistation files
 * currently does not work and is not used
 *
 * @author Liam Marquis
 * @version 4/15/19
 */
public class localisationLoader
{
    // instance variables - replace the example below with your own
    private FileReader file;
    private BufferedReader br;
    private HashMap<String,String> commands = new HashMap<>();

    /**
     * Constructor for objects of class localisationLoader
     * Loads all localisation files
     */
    public localisationLoader(){
    }

    /**
     * Loads the english localisation file into the commands HashMap
     * @return Returns the commands HashMap
     * @throws FileNotFoundException
     * @throws IOException
     */
    public HashMap loadEnglish() throws FileNotFoundException,IOException 
    {
        String directory = "localisation\\english.txt";
        String line;
        FileReader reader = new FileReader(directory);
        BufferedReader br = new BufferedReader(reader);
        line = br.readLine();
        commands.put("GO", line.substring(4,line.length()));
        line = br.readLine();
        commands.put("QUIT", line.substring(6,line.length()));
        line = br.readLine();
        commands.put("HELP", line.substring(6,line.length()));
        line = br.readLine();
        commands.put("UNKNOWN", line.substring(9,line.length()));
        line = br.readLine();
        commands.put("LOOK", line.substring(6,line.length()));
        line = br.readLine();
        commands.put("CLIMB", line.substring(7,line.length()));
        
        return commands;
    }
}
