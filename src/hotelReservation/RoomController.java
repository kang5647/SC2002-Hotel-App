package hotelReservation;
import java.util.Scanner;


public class RoomController {

    private final Scanner sc = new Scanner(System.in);
	/**
     * Current hotel 
     */
	private Hotel hotel; 
	private final EditFileRoom er = new EditFileRoom(hotel);
	
	/**
	 * Constructor
	 * @param hotel current hotel 
	 */
    public RoomController(Hotel hotel) {
		this.hotel = hotel; 
	}

	/**
     * Displays menu of available options under room control
     */
    public void roomControl() {

		int option;
		boolean run = true;
		while(run){

			System.out.println("\nPlease select one operation to proceed\n"
								+ "1. Update Room\n"
								+ "2. Display All Rooms Info\n"
								+ "3. Return to previous menu\n");
			
			option = sc.nextInt();
			sc.nextLine();

			if(option < 1 || option > 3){
				System.out.println("Invalid option.");
				continue;
			}

			switch(option) {
			case 1: 
				updateRoom();
				break;
			case 2: 
				displayAllRooms();
				break;
			case 3: 
				run = false;
				return;
			default:
				break;
			}
		}
	}
    
	/**
     * Updates attributes of room based on ID
     */
    public void updateRoom() {
    	
    	String roomNo;
        Rooms room;
		int option;
		
    	 while(true){
             System.out.println("Enter the room ID: ");
             roomNo = sc.nextLine();
             if((room = hotel.findRoom(roomNo)) != null){
            	 room.printString();
                 break;
             }
             System.out.println("Room not found.");
         }
    	 
    	 while(true){

 			System.out.println("Please select one operation to proceed\n"
 								+ "1. Update Room Status \n"
 								+ "2. Update Bed Type\n"
 								+ "3. Update Rate\n"
 								+ "4. Return to previous menu\n");
 			
 			option = sc.nextInt();
 			sc.nextLine();

 			if(option < 1 || option > 4){
 				System.out.println("Invalid option.");
 				continue;
 			}

 			switch(option) {
 			case 1: 
 				updateStatus(room);
 				break;
 			case 2:
 				updateBed(room);
 				break;
 			case 3: 
 				updateRate(room);
 				break;
 			case 4: 
 				return;
 			}
 			
 			er.setRoom(room);
 			er.edit("Rooms.txt");
 		}
    }

	/**
	 * Updates status of room (vacant, reserved, occupied, under maintenance)
	 * @param room to be updated 
	 */
    public void updateStatus(Rooms room) {
		
		for (int i = 0; i < 4; i ++) 
            System.out.println((i+1) + ": " + ROOMSTATUS.values()[i]);

		System.out.println("Enter number to select status");

		int choice = sc.nextInt(); 

		if (choice < 1 || choice > 4) {
			System.out.println("Invalid input");
			return; 
		}

		room.setStatus(ROOMSTATUS.values()[choice-1]);
		System.out.println("Room's status successfully updated!\n");

	}

	/**
    * Updates bed type of room 
    * @param room to be updated
    */
    public void updateBed(Rooms room) {

		for (int i = 0; i < 4; i ++) 
			System.out.println((i+1) + ": " + BEDTYPE.values()[i]);

		System.out.println("Enter number to select status");

		int choice = sc.nextInt(); 

		if (choice < 1 || choice > 4) {
			System.out.println("Invalid input");
			return; 
		}

		room.setBedType(BEDTYPE.values()[choice-1]);
		
		System.out.println("Room's bed successfully updated!\n");

	};

	/**
	 * Update rate of room (non-Weekend and Weekend)
	 * @param room to be updated 
	 */
    public void updateRate(Rooms room) {

		System.out.println("Enter new rate (non-Weekend):");
		double rate = sc.nextDouble(); 
		sc.nextLine();

		room.setRate(rate);

		System.out.println("Rate successfully changed to" + rate + "\n");

		System.out.println("Enter new rate (Weekend):");

		rate = sc.nextDouble();

		room.setWeekendRate(rate);

		System.out.println("Weekend rate successfully changed to" + rate +"\n");

	};

	/**
	 * Prints all attributes of a certain room
	 * @param room to be displayed
	 */
    public void displayRoom(Rooms room) {
		System.out.println("_______________________________________________\n");
		System.out.printf("%-20s%s%n","Room ID:",room.getID());
		System.out.printf("%-20s%s%n","Room Status:",room.getStatus().name());
		System.out.printf("%-20s%s%n","Bed Type:",room.getBedType().name());
		System.out.printf("%-20s$%.2f%n","Rate (Non-Weekend):",room.getRate());
		System.out.printf("%-20s$%.2f%n","Rate (Weekend):",room.getWeekendRate());
		System.out.println("_______________________________________________\n");
	}

	/**
     * Prints all room numbers in the hotel 
     */
    public void displayAllRooms() {
    	for( Rooms room : hotel.getAllRooms()) {
			room.printString();
			// System.out.println(room.toString());
			// System.out.println("");
		}
    }
}