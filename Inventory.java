import java.util.Random;

/**
 *
 * @author Liam
 */
public class Inventory {
    double maxCarryCapacity = 25.0;
    double carryCapacity = 0.01;
    boolean maintenanceRoomKey,labKeycard,crowbar,smallExplosive,largeExplosive,doorCodeNote,laserCutter;
    int paperclip;
    Random rng = new Random();
    
    public Inventory(){
        maintenanceRoomKey = false;
        labKeycard = false;
        paperclip = 1;
        crowbar = false;
        smallExplosive = false;
        largeExplosive = false;
        doorCodeNote = false;
        laserCutter = false;
    }
    
    public boolean tryToPickUpItem(String itemName){
        boolean wasItemPickedUp = false;
        switch (itemName){
            case "maintenanceRoomKey":
                if (maintenanceRoomKey == false && carryCapacity+0.01 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
            case "labKeycard":
                if (labKeycard == false && carryCapacity+0.01 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
            case "crowbar":
                if (crowbar == false && carryCapacity+3.00 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
            case "smallExplosive":
                if (smallExplosive == false && carryCapacity+1.50 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
            case "largeExplosive":
                if (largeExplosive == false && carryCapacity+6.00 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
            case "doorCodeNote":
                if (doorCodeNote == false && carryCapacity+0.01 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
            case "laserCutter":
                if (laserCutter == false && carryCapacity+3.50 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
            case "paperclip":
                if (paperclip <= 10 && carryCapacity+0.01 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
        }
        return wasItemPickedUp;
    }
    
    public boolean tryToUseItem(String itemName){
        switch (itemName){
            case "maintenanceRoomKey":
                if (maintenanceRoomKey == true){
                    useItem(itemName);
                    return true;
                }
                break;
            case "largeExplosive":
                if (largeExplosive == true){
                    useItem(itemName);
                    return true;
                }
                break;
            case "smallExplosive":
                if (smallExplosive == true){
                    useItem(itemName);
                    return true;
                }
                break;
            case "laserCutter":
                if (laserCutter == true){
                    useItem(itemName);
                    return true;
                }
                break;
            case "doorCodeNote":
                if (doorCodeNote == true){
                    useItem(itemName);
                    return true;
                }
                break;
            case "paperclip":
                if (paperclip > 0){
                    if (rng.nextInt(100) <= 6){
                        useItem(itemName);
                        return true;
                    }else{
                        System.out.println("The paperclip broke!");
                    }
                }
                break;
        }
        return false;
    }
    
    private void obtainItem(String itemName){
        switch(itemName){
            case "maintenanceRoomKey":
                maintenanceRoomKey = true;
                carryCapacity += 0.03;
                System.out.println("You picked up the Maintenance Room Key!");
                break;
            case "labKeycard":
                labKeycard = true;
                carryCapacity += 0.03;
                System.out.println("You picked up the Lab Keycard!");
                break;
            case "smallExplosive":
                smallExplosive = true;
                carryCapacity += 1.50;
                System.out.println("You picked up the small explosive!");
                break;
            case "largeExplosive":
                largeExplosive = true;
                carryCapacity += 6.00;
                System.out.println("You picked up the large explosive!");
                break;
            case "laserCutter":
                laserCutter = true;
                carryCapacity += 3.50;
                System.out.println("You picked up the laser cutter!");
                break;
            case "doorCodeNote":
                doorCodeNote = true;
                carryCapacity += 0.01;
                System.out.println("You picked up the door code note!");
                break;
            case "crowbar":
                crowbar = true;
                carryCapacity += 3.00;
                System.out.println("You picked up the Crowbar!");
                break;
            case "paperclip":
                paperclip++;
                carryCapacity += 0.01;
                System.out.println("You picked up a Paperclip!");
                break;
        }
    }
    
    public void useItem(String itemName){
        switch(itemName){
            case "maintenanceRoomKey":
                maintenanceRoomKey = false;
                carryCapacity -= 0.03;
                System.out.println("You used the Maintenance Room Key!");
                break;
            case "labKeycard":
                labKeycard = false;
                carryCapacity -= 0.03;
                System.out.println("You used the Lab Keycard!");
                break;
            case "smallExplosive":
                smallExplosive = false;
                carryCapacity -= 1.50;
                System.out.println("You used the small explosive!");
                break;
            case "largeExplosive":
                largeExplosive = false;
                carryCapacity -= 6.00;
                System.out.println("You used the large explosive!");
                break;
            case "laserCutter":
                laserCutter = false;
                carryCapacity -= 3.50;
                System.out.println("You used the small explosive!");
                break;
            case "doorCodeNote":
                doorCodeNote = false;
                carryCapacity -= 0.01;
                System.out.println("You used the large explosive!");
                break;
            case "crowbar":
                crowbar = false;
                carryCapacity -= 3.00;
                System.out.println("You used the Crowbar!");
                break;
            case "paperclip":
                paperclip--;
                carryCapacity -= 0.01;
                System.out.println("You used a Paperclip!");
                break;
        }
    }
    
    public void showInventory(){
        System.out.println("Inventory:----------------------------------\nCarry Capacity: "+carryCapacity+"/"+maxCarryCapacity);
        if (maintenanceRoomKey == true){
            System.out.println("Maintenance Room Key");
        }
        if (labKeycard == true){
            System.out.println("Lab Keycard");
        }
        if (smallExplosive == true){
            System.out.println("Small Explosive");
        }
        if (largeExplosive == true){
            System.out.println("Large Explosive");
        }
        if (laserCutter == true){
            System.out.println("Laser Cutter");
        }
        if (largeExplosive == true){
            System.out.println("Large Explosive");
        }
        if (crowbar == true){
            System.out.println("Crowbar");
        }
        if (paperclip > 0){
            System.out.println("Paperclip: " + paperclip);
        }
        System.out.println("--------------------------------------------");
    }
}
