import java.util.Scanner;

/**
 *
 * @author Liam Marquis
 * @version 4/15/19
 */
public class GuardEncounter {
    Scanner scan = new Scanner(System.in);
    static Inventory inv = new Inventory();
    Game game = new Game();
    static boolean isGreetingOver = false, isEncounterOver = false;
    
    /**
     * controls the encounter with guard grant
     */
    public GuardEncounter() {
    }
    /**
     * Player encounters guard grant outside of the main offices
     * @return Returns boolean indicating whether or not the security door is locked
     * @param hasCoffee Does the player have a coffee for grant
     */
    public boolean guardEncounter_Grant(boolean hasCoffee){
        if (isGreetingOver == false && isEncounterOver == false){
        System.out.println("Guard Grant: Hey, what are you doing here today?\n"
                            + "You: Oh, I just forgot something from my desk.\n"
                            + "Guard Grant: Right... Listen if you can be me some coffee from the employee lounge then I'll let you into the offices.");
             isGreetingOver = true;
        }
        
        if (hasCoffee==true && isEncounterOver == false){
            System.out.println("Guard Grant: Oh good you got it, head on in!");
            isEncounterOver = true;
        }else {
            System.out.println("Guard Grant: I'm not letting you in till I get my coffee!");
        }
        
        if (isEncounterOver == true){
            return false;
        }else{
            return true;
        }
    }
    
    
}
