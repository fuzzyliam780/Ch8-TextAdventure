import java.util.Random;

/**
 *
 * @author Liam Marquis
 * @version 4/15/19
 */
public class Inventory {
    double maxCarryCapacity = 25.0;
    double carryCapacity = 0.01;
    boolean maintenanceRoomKey,coffee,labKeycard,crowbar,smallExplosive,largeExplosive,doorCodeNote,laserCutter;
    int paperclip;
    Random rng = new Random();
    
    /**
     * Creates the players inventory
     */
    public Inventory(){
        maintenanceRoomKey = false;
        coffee = false;
        labKeycard = false;
        paperclip = 1;
        crowbar = false;
        smallExplosive = false;
        largeExplosive = false;
        doorCodeNote = false;
        laserCutter = false;
    }
    
    /**
     * checks if the player has picked up the coffee
     */
    public boolean hasCoffee(){
        return coffee;
    }
    
    /**
     * player checks to see if they can pick up an item
     * @param itemName the name of the item
     * @return Returns whether or not the player was able to pick up the item
     */
    public boolean tryToPickUpItem(String itemName){
        boolean wasItemPickedUp = false;
        switch (itemName){
            case "maintenanceRoomKey":
                if (maintenanceRoomKey == false && carryCapacity+0.01 <= maxCarryCapacity){
                    wasItemPickedUp = true;
                    obtainItem(itemName);
                }
                break;
            case "coffee":
                if (coffee == false && carryCapacity+0.30 <= maxCarryCapacity){
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
                break;
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
    
    /**
     * Player checks to see if they can use an item
     * @param itemName the name of the item
     * @return Returns whether or not the player was able to use the item
     */
    public boolean tryToUseItem(String itemName){
        switch (itemName){
            case "maintenanceRoomKey":
                if (maintenanceRoomKey == true){
                    useItem(itemName);
                    return true;
                }
                break;
            case "coffee":
                if (coffee == true){
                    useItem(itemName);
                    return true;
                }
                break;
            case "labKeycard":
                if (labKeycard == true){
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
                        useItem(itemName);
                        System.out.println("The paperclip broke!");
                    }
                }
                break;
        }
        return false;
    }
    
    /**
     * Player picks up an item
     * @param itemName the name of the item
     */
    private void obtainItem(String itemName){
        switch(itemName){
            case "maintenanceRoomKey":
                maintenanceRoomKey = true;
                carryCapacity += 0.03;
                System.out.println("You picked up the Maintenance Room Key!");
                break;
            case "coffee":
                coffee = true;
                carryCapacity += 0.30;
                System.out.println("You picked up the coffee!");
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
                System.out.println("You picked up the LASERcutter!");
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
    
    /**
     * player uses an item
     * @param itemName the name of the item
     */
    public void useItem(String itemName){
        switch(itemName){
            case "maintenanceRoomKey":
                maintenanceRoomKey = false;
                carryCapacity -= 0.03;
                System.out.println("You used the Maintenance Room Key!");
                break;
            case "coffee":
                coffee = false;
                carryCapacity -= 0.30;
                System.out.println("You gave Grant the coffee!");
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
                System.out.println("You used the LASERcutter!");
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
    
    /**
     * Shows the inventory of the player
     */
    public void showInventory(){
        System.out.println("Inventory:----------------------------------\nCarry Capacity: "+carryCapacity+"/"+maxCarryCapacity);
        if (maintenanceRoomKey == true){
            System.out.println("Maintenance Room Key: A key to the maintenance room");
        }
        if (labKeycard == true){
            System.out.println("Lab Keycard: A keycard to the prototype lab");
        }
        if (coffee == true){
            System.out.println("Coffee: A cup of coffee");
        }
        if (smallExplosive == true){
            System.out.println("Small Explosive: A small explosive that can be used to open doors");
        }
        if (largeExplosive == true){
            System.out.println("Large Explosive: A large explosive that could be used to destroy a large object");
        }
        if (laserCutter == true){
            System.out.println("Laser Cutter: A laser cutter that could open a lock");
        }
        if (doorCodeNote == true){
            System.out.println("Door Code Note: The code for the 2nd floor security door is 959");
        }
        if (crowbar == true){
            System.out.println("Crowbar: A crowbar that could be used to pry open a door");
        }
        if (paperclip > 0){
            System.out.println("Paperclip: " + paperclip+": Paperclips have a 7/100 chance to pick a lock");
        }
        System.out.println("--------------------------------------------");
    }
}
