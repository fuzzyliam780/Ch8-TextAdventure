import java.util.Scanner;

/**
 *
 * @author Liam
 */
public class GuardEncounter {
    Scanner scan = new Scanner(System.in);
    Inventory inv = new Inventory();
    Game game = new Game();
    boolean isGreetingOver;
    
    
    public GuardEncounter(int guard) {
        switch(guard){
            case 1:
                guardEncounter_Grant();
                break;
            case 2:
                guardEncounter_Edwards();
                break;
            case 3:
                guardEncounter_Thomas();
                break;
        }
    }
    
    private void guardEncounter_Grant(){
        if (isGreetingOver == false){
        System.out.println("Guard Grant: Hey, what are you doing here today?\n"
                            + "You: Oh, I just forgot something from my desk.\n"
                            + "Guard Grant: Right... Listen if you can be me some coffee from the employee lounge then I'll let you into the offices.");
             isGreetingOver = true;
        }
        if (inv.hasCoffee()==true){
            System.out.println("Guard Grant: Oh good you got it, head on in!");
        }
    }
    
    private void guardEncounter_Edwards(){
        
    }
    
    private void guardEncounter_Thomas(){
        
    }
    
    
}
