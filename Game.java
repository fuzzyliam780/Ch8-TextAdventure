import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom,lobby2,lobby1,lobby1V2,storageRoom, 
        maintenanceRoom, lab, office,elevator,elevatorShaft,hall1,
        lounge,bathroom,stairwell,buildingExterior,maintenanceRoomV2,
        hall2,hall1v2,hall2v2,managerOffice,basement,coworkerRoom;
    Random rng = new Random();
    Scanner scan = new Scanner(System.in);
    Inventory inv;
    private String response;
    private boolean elevatorOn = false,elevatorDoorOpen=false,isGuardEncounterOver = false,guardAtDoor,pickedUpLabKeycard = false,
    maintenanceRoomDoorUnlocked = false,labRoomDoorUnlocked = false, hasExplosive = false;
    private int guard;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Room> previousRooms = new ArrayList<>();
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    public void endGuardEncounterOver(){
        isGuardEncounterOver = false;
    }
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        guard = rng.nextInt(4);
        if (guard == 0){
            guardAtDoor = false;
        }else{
            guardAtDoor = true;
        }
      
        // create the rooms
        // name,DESC
        lobby1 = new Room("lobby1","in the lobby outside the elevators");
        lobby2 = new Room("lobby2","in the lobby outside the elevators on the second floor");
        storageRoom = new Room("storageRoom","in the storage room");
        maintenanceRoom = new Room("maintenanceRoom","in the maintenance room");
        lab = new Room("lab","in the explosives lab");
        managerOffice = new Room("managerOffice","in the manager's office");
        office = new Room("office","in the main offices");
        elevator = new Room("elevator","in the elevator");
        elevatorShaft = new Room("elevatorShaft","in the elevator shaft");
        buildingExterior = new Room("buildingExterior","outside the building");
        lounge = new Room("lounge","in the employee's lounge");
        hall1 = new Room("hall1","in the north half of the hallway");
        hall2 = new Room("hall2","in the south half of the hallway");
        bathroom = new Room("bathroom","in the bathroom");
        basement = new Room("basement","in the basement");
        stairwell = new Room("stairwell","in the stairwell to the second floor");
        coworkerRoom = new Room("coworkerRoom","in your coworker's room");
        
        //North,East,South,West
        lobby1.setAllExits(elevatorShaft,buildingExterior,hall1,maintenanceRoom);
        lobby2.setAllExits(null,buildingExterior,office,stairwell);
        storageRoom.setAllExits(hall1,null,null,null);
        maintenanceRoom.setAllExits(null,lobby1,null,null);
        lab.setAllExits(buildingExterior,buildingExterior,null,hall1);
        managerOffice.setAllExits(null,hall2,null,null);
        elevator.setAllExits(null,null,lobby2,null);
        elevatorShaft.setAllExits(null,null,null,null);
        elevatorShaft.setVerticalDirections(lobby2,lobby1);
        buildingExterior.setAllExits(null,null,null,null);
        buildingExterior.setVerticalDirections(lobby2,lobby1);
        lounge.setAllExits(null,hall1,null,stairwell);
        hall1.setAllExits(lobby1,lab,hall2,lounge);
        hall2.setAllExits(hall1,bathroom,storageRoom,managerOffice);
        office.setAllExits(lobby2,null,coworkerRoom,null);
        basement.setAllExits(null,null,null,null);
        basement.setVerticalDirections(stairwell,null);
        bathroom.setAllExits(null,null,null,hall2);
        stairwell.setAllExits(null,lounge,null,null);
        stairwell.setVerticalDirections(lobby2,basement);
        coworkerRoom.setAllExits(office,null,null,null);
        
        currentRoom = lobby1;  // start game lobby
        inv = new Inventory();
        
        rooms.add(lobby1);
        rooms.add(lobby2);
        rooms.add(storageRoom);
        rooms.add(maintenanceRoom);
        rooms.add(office);
        rooms.add(elevator);
        rooms.add(elevatorShaft);
        rooms.add(buildingExterior);
        rooms.add(lounge);
        rooms.add(hall1);
        rooms.add(hall2);
        rooms.add(lounge);
        rooms.add(office);
        rooms.add(basement);
        rooms.add(stairwell);
        rooms.add(coworkerRoom);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Yesterday you found out your coworker took an embarrassing picture of you at the christmas party.");
        System.out.println("You need to break into the office and steal the picture from his desk before he can expose you!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;

            case LOOK:
                look();
                break;

            case CLIMB:
                climb(command);
                break;

            case INVENTORY:
                inv.showInventory();
                break;

            case BACK:
                back(command);
                break;

            case GRAB:
                grab(command);
                break;

            case INTERACT:
                interact(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Yesterday you found out your coworker took an embarrassing picture of you at the christmas party.");
        System.out.println("You need to break into the office and steal the picture from his desk before he can expose you!");
        
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null && currentRoom == lobby2 && direction.equals("north")){
            System.out.println("The elevator doors are closed!");
        }else if (nextRoom == null) {
            System.out.println("I cannot go that way!");
        }else if (direction.equals("north") && currentRoom == lobby2 && elevatorOn == true){
            currentRoom = lobby2;
            System.out.println(currentRoom.getLongDescription());
        }else if (direction.equals("west") && currentRoom == lobby1 && maintenanceRoomDoorUnlocked == false){
            unlockMaintenanceDoor(direction,nextRoom);
        }else if (direction.equals("east") && currentRoom == hall1 && labRoomDoorUnlocked == false){
            if (pickedUpLabKeycard == true){
                System.out.println("You try to open the lab door, but it is locked! Would you like to use the lab keycard?\nYes or No");
                response = scan.nextLine().trim().toLowerCase();
                if (response.equals("yes")){
                    inv.tryToUseItem("labKeycard");
                    previousRooms.add(currentRoom);
                    currentRoom = nextRoom;
                    System.out.println(currentRoom.getLongDescription());
                }
            }else {
                System.out.println("You try to open the lab door, but it is locked! You'll need a keycard to unlock it!");
            }
        }else {
            previousRooms.add(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    private void unlockMaintenanceDoor(String direction,Room nextRoom){
        boolean result;
        System.out.println("You try to open the maintenance room door, but it is locked! Would you like to use an item\nYes or No");
        response = scan.nextLine().trim().toLowerCase();
        switch (response){
            case "yes":
                System.out.println("Which item do you want to use?\nApplicable Items: maintenance room key,paperclip,small explosive\n'exit' to cancel");
                inv.showInventory();
                response = scan.nextLine().trim().toLowerCase();
                switch(response){
                    case "maintenance room key":
                         result = inv.tryToUseItem("maintenanceRoomKey");
                        if (result == true){
                            System.out.println("You unlocked the door, but the key got stuck in the lock.");
                            maintenanceRoomDoorUnlocked = true;
                            previousRooms.add(currentRoom);
                            currentRoom = nextRoom;
                            System.out.println(currentRoom.getLongDescription());
                        }else{
                            System.out.println("You do not have the maintenance rooom key!");
                        }
                        break;
                    case "paperclip":
                        result = inv.tryToUseItem("paperclip");
                        if (result == true){
                            System.out.println("You unlocked the door with the paperclip, but the it got stuck in the lock.");
                            maintenanceRoomDoorUnlocked = true;
                            previousRooms.add(currentRoom);
                            currentRoom = nextRoom;
                            System.out.println(currentRoom.getLongDescription());
                        }else{
                            System.out.println("You do not have a paperclip!");
                        }
                        break;
                    case "small explosive":
                        result = inv.tryToUseItem("smallExplosive");
                        if (result == true){
                            hasExplosive = false;
                            System.out.println("You blew the door open with the small explosive!");
                            maintenanceRoomDoorUnlocked = true;
                            previousRooms.add(currentRoom);
                            currentRoom = nextRoom;
                            System.out.println(currentRoom.getLongDescription());
                        }else{
                            System.out.println("You do not have the small explosive!");
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "no":
                break;
            default:
                break;
        }
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void climb(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Climb to where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("You cannot climb that way!");
        }
        else {
            previousRooms.add(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Player looks around room, pointing out any details
     */
    private void look(){
        String currentRoomName = currentRoom.getName();
        switch(currentRoomName){ 
            case "lobby1":
                System.out.println ("I'm in the 1st floor lobby\n"
                + "The elevator door is closed to my north, but I could pry it open if I had a tool\n"
                + "There is a window to the exterior of the building to my east, but I'd have to break the glass to get out there\n" 
                + "The hall is to the rest of the 1st floor is to my south\n" 
                + "The elevator maintenance room is to my west, but the door is locked");
                break;
            case "lobby2":
                System.out.println("I'm in the 2nd floor lobby\n"
                + "The elevator door is closed to my north\n"
                + "The window to the exterior of the building is open to my east\n"
                + "The main employee offices are to my south\n" 
                + "There is a wall to my west");
                break;
            case "storageRoom":
                System.out.println("I'm in the storage room\n"
                + "the only way out is to my north\n"
                + "The only thing useful to me in here is a 'crowbar'");
                break;
            case "maintenanceRoom":
                System.out.println("I'm in the maintenance room\n"
                + "the only way out is to my east\n"
                + "The only thing useful to me in here is the elevator power 'panel'");
                break;
            case "hall1":
                System.out.println("I'm in the north half of the hallway\n"
                + "The 1st floor lobby is to my north\n"
                + "The prototypes laboratory is to my east, but I'd need a keycard to get in\n"
                + "The south half of the hallway is open to my south\n" 
                + "The employee lounge is to my west"
                + "There is a 'paperclip' on the ground I could grab");
                break;
            case "hall2":
                System.out.println("I'm in the south half of the hallway\n"
                + "The north half of the hallway is to my north\n"
                + "The bathroom is to my east\n"
                + "The storageroom is open to my south\n" 
                + "The manager's office is to my west");
                break;
            case "lab":
                System.out.println("I'm in the prototypes lab\n"
                + "the only way out is to my west\n"
                + "There are several prototype devices around the lab including the teleporter,explosive synthesizer and a LASERcutter");
                break;
            case "managerOffice":
                System.out.println("I'm in the manager's office\n"
                + "the only way out is to my east\n"
                + "There is a 'key' and 'note' on the desk that can be grabbed");
                break;
            case "office":
                System.out.println("I'm in the main office\n"
                + "The 2nd floor lobby is to my north\n"
                + "My coworker's room is to my south\n" 
                + "The stairs are to my west");
                break;
            case "elevator":
                System.out.println("I'm in the elevator\n"
                + "the only way out is to my east\n"
                + "there is panel on the wall to control the elevator");
                break;
            case "elevatorShaft":
                System.out.println("I'm in the elevator shaft\n"
                + "There is a ladder I can climb to get down to the 1st floor lobby\n"
                + "There is a ladder I can climb to get up to the 2nd floor lobby");
                break;
            case "bathroom":
                System.out.println("I'm in the bathroom\n"
                + "the only way out is to my west\n"
                + "there seems to be something under the sink");
                break;
            case "basement":
                System.out.println("I'm in the basement\n"
                + "the only way out for me to climb up the stairs");
                break;
            case "stairwell":
                System.out.println("I'm in the stairwell\n"
                + "The second floor lobby is up the stairs"
                + "The basement is down the stairs"
                + "The employee lounge is to the east");
                break;
            case "lounge":
                System.out.println("I'm in the lounge\n"
                + "The north half of the hall is to my east\n"
                + "The stairwell is to my west");
                break;
            case "buildingExterior":
                System.out.println("I'm on the building exterior\n"
                + "I can climb up to the second floor lobby"
                + "I can climb down to the 1st floor lobby");
                break;
            case "coworkerRoom":
                System.out.println("I'm in my coworker's room, the picture should be in his desk\n"
                + "the only exit is to my north\n");
                break;
            }
    }
    
    private void back(Command command){
        //go back by 1
        if(!command.hasSecondWord()) {
            if (previousRooms.size() == 1){
                currentRoom = previousRooms.get(previousRooms.size()-1);
            }else{
                System.out.println("No where to go back to.");
            }
        }
        
        //go back by x
        if (command.hasSecondWord()){
            int roomsToGoBack = Integer.parseInt(command.getSecondWord());
            if (previousRooms.size() >= roomsToGoBack){
                currentRoom = previousRooms.get(previousRooms.size()-roomsToGoBack);
                System.out.println(currentRoom.getLongDescription());
            }else{
            System.out.println("Cannot go back that far!");
            }
        }
    }
    
    private void grab(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Grab what?");
            return;
        }
        
        String itemToGrab = command.getSecondWord().trim().toLowerCase();
        
        if (currentRoom == managerOffice){
            switch (itemToGrab){
                case "key":
                    inv.tryToPickUpItem("maintenanceRoomKey");
                    break;
                case "note":
                    inv.tryToPickUpItem("doorCodeNote");
                    break;
            }
        }else if (currentRoom == hall1 && itemToGrab.equals("paperclip")){
            inv.tryToPickUpItem("paperclip");
        }else if (currentRoom == storageRoom && itemToGrab.equals("crowbar")){
            inv.tryToPickUpItem("crowbar");
        }else if (currentRoom == lab && itemToGrab.equals("lasercutter")){
            inv.tryToPickUpItem("laserCutter");
        }
    }
    
    private void interact(Command command){
        int teleporterCharges = 3;
        if(!command.hasSecondWord()) {
            System.out.println("Interact with what?");
            return;
        }
        boolean result;
        String itemToInteractWith = command.getSecondWord().trim().toLowerCase();
        
        if (currentRoom == lab){
            switch (itemToInteractWith){
                case "explosive synthesizer":
                    if (hasExplosive == false){
                        System.out.println("This machine is designed to fabricate an explosive charge.\nWhat size of charge would you like, small or large?");  
                        response = scan.nextLine().trim().toLowerCase();
                        switch (response){
                            case "small":
                                inv.tryToPickUpItem("smallExplosive");
                                break;
                            case "large":
                                inv.tryToPickUpItem("largeExplosive");
                                break;
                        }
                    }else{
                        System.out.println("This machine needs to recharge, please come back later");
                    }
                    break;
                case "teleporter":
                    if (teleporterCharges > 0){
                        System.out.println("This machine is designed to teleport a person around this building.\nWhich setting would you like to activate, fixed or random?");  
                        response = scan.nextLine().trim().toLowerCase();
                        switch (response){
                            case "fixed":
                                currentRoom = basement;
                                System.out.println(currentRoom.getLongDescription());
                                teleporterCharges--;
                                break;
                            case "random":
                                currentRoom = rooms.get(rng.nextInt(rooms.size()));
                                teleporterCharges--;
                                break;
                        }
                    }else{
                        System.out.println("This machine needs to recharge, please come back later");
                    }
                    break;
            }
        }else if (currentRoom == bathroom){
            if (itemToInteractWith.equals("sink")){
                System.out.println("There was a keycard under the sink, looks like it belongs to the lab\nAfter picking up the card the floor gave out beneath me!");
                inv.tryToPickUpItem("labKeycard");
                pickedUpLabKeycard = true;
                currentRoom = basement;
                System.out.println(currentRoom.getLongDescription());
            }
        }else if (currentRoom == maintenanceRoom){
            if (itemToInteractWith.equals("panel")){
                System.out.println("You press a few buttons and flip a switch and the elevator turns on! You should now be able to use the elevator.");
                elevatorOn = true;
                elevatorDoorOpen = true;
            }
        }else if (currentRoom == coworkerRoom){
            if (itemToInteractWith.equals("desk")){
                System.out.println("While investigating the desk you find a drawer that is locker and figure that must be where they are keeping the picture, but how will you open it?");
                System.out.println("Do you want to use an inventory item? Yes or No");
                response = scan.nextLine().trim().toLowerCase();
                if (response.equals("yes")){
                    System.out.println("Which item do you want to use?\nApplicable Items: LASERcutter,paperclip,large explosive\n'exit' to cancel");
                    inv.showInventory();
                    response = scan.nextLine().trim().toLowerCase();
                    switch(response){
                        case "lasercutter":
                            result = inv.tryToUseItem("laserCutter");
                            if (result == true){
                                System.out.println("You cut the lock open!\n You were sucessful in getting the picutre before your coworker could embaress you!");
                                System.exit(0);
                        }else{
                            System.out.println("You do not have the LASERcutter!");
                        }
                        break;
                    case "paperclip":
                        result = inv.tryToUseItem("paperclip");
                        if (result == true){
                            System.out.println("You unlocked the drawer with the paperclip!\n You were sucessful in getting the picutre before your coworker could embaress you!");
                            System.exit(0);
                        }else{
                            System.out.println("You do not have a paperclip!");
                        }
                        break;
                    case "large explosive":
                        result = inv.tryToUseItem("largeExplosive");
                        if (result == true){
                            hasExplosive = false;
                            System.out.println("You blew up the desk!\n You were sucessful in getting the picutre before your coworker could embaress you!");
                            System.exit(0);
                        }else{
                            System.out.println("You do not have the large explosive!");
                        }
                        break;
                    default:
                        break;
                    }
                }
            }
        }
    }
}