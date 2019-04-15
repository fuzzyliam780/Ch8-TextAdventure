import java.util.Scanner;

/**
 *
 * @author Liam
 */
public class GuardEncounter {
    Scanner scan = new Scanner(System.in);
    
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
        
    }
    
    private void guardEncounter_Edwards(){
        
    }
    
    private void guardEncounter_Thomas(){
        
    }
    
    
}
