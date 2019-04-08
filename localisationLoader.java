import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/**
 * Write a description of class localisationLoader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class localisationLoader
{
    // instance variables - replace the example below with your own
    private FileReader file;
    private BufferedReader br;

    /**
     * Constructor for objects of class localisationLoader
     */
    public localisationLoader()
    {
        loadEnglish();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void loadEnglish()
    {
        String directory = System.getProperty("user.home") + "\\Desktop\\Ch8-TextAdventure\\localisation\\english.txt";
        
    }
}
