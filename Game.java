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
    private Room currentRoom,lobby2;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room lobby1,lobby1V2,storageRoom, 
        maintenanceRoom, lab, office,elevator,elevatorShaft,hall1,
        lounge,bathroom,stairwell,buildingExterior,maintenanceRoomV2,
        hall2,hall1v2,hall2v2,managerOffice,basement;
      
        // create the rooms
        // DESC,North,East,South,West
        lobby1 = new Room("lobby outside the elevators");
        lobby2 = new Room("lobby outside the elevators on the second floor");
        storageRoom = new Room("in the storage room");
        maintenanceRoom = new Room("in the maintenance room");
        lab = new Room("in the explosives lab");
        managerOffice = new Room("in the manager's office");
        office = new Room("in the main offices");
        elevator = new Room("in the elevator");
        elevatorShaft = new Room("in the elevator shaft");
        buildingExterior = new Room("outside the building");
        lounge = new Room("in the employee's lounge");
        hall1 = new Room("in the hallway");
        hall2 = new Room("in the hallway");
        bathroom = new Room("in the bathroom");
        basement = new Room("in the basement");
        stairwell = new Room("in the stairwell to the second floor");

        lobby1.setAllExits(elevatorShaft,buildingExterior,hall1,maintenanceRoom);
        lobby2.setAllExits(null,buildingExterior,office,stairwell);
        storageRoom.setAllExits(hall1,null,null,null);
        maintenanceRoom.setAllExits(null,lobby1,null,null);
        lab.setAllExits(buildingExterior,buildingExterior,null,hall1);
        office.setAllExits(lounge,hall1,null,null);
        elevator.setAllExits(null,null,lobby1,null);
        elevatorShaft.setAllExits(null,null,lobby1,null);
        buildingExterior.setAllExits(null,null,null,lobby1);
        buildingExterior.setVerticalDirections(lobby2,null);
        lounge.setAllExits(null,hall1,null,stairwell);
        hall1.setAllExits(lobby1,lab,hall2,lounge);
        hall2.setAllExits(hall1,bathroom,storageRoom,managerOffice);
        office.setAllExits(lobby2,null,null,managerOffice);
        basement.setAllExits(null,null,null,null);
        basement.setVerticalDirections(stairwell,null);
        bathroom.setAllExits(null,null,null,hall2);
        stairwell.setAllExits(null,null,null,hall2);
        stairwell.setVerticalDirections(lobby2,basement);
        
        currentRoom = lobby1;  // start game lobby
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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
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

        if (nextRoom == null && currentRoom == lobby2 && direction == "north"){
            System.out.println("The elevator doors closed!");
        }else if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
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
}
