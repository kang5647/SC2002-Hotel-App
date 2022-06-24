package hotelReservation;
import java.util.Scanner;


/**
 * User interafce that allows users to create, update and print orders
 */
public class RoomServiceController {
    
    ///goes hotel -> calls roomServiceOrder -> calls menu -> 
    private Hotel hotel; 
    private final Scanner sc = new Scanner(System.in);
	private EditFileRSO eRSO;
    
    /**
	 * Constructor
	 * @param hotel current hotel 
	 */
	public RoomServiceController(Hotel hotel) {
		this.hotel = hotel; 
		eRSO = new EditFileRSO(hotel);
	}

    /**
	 * Menu of all the options under Room Service Controller 
	 */
    public  void roomService(){
		
        int option ; 
        Reservation reservation; 
		while (true) {
            System.out.println("\nPlease select one operation to proceed\n"
							+ "1. Create new order \n"
							+ "2. Update order \n"
							+ "3. Print order \n"
                            + "4. Return to previous menu\n");
		
            option = sc.nextInt();
            sc.nextLine();
            if (option == 4){
                break;
            } 
 
            if (option < 1 || option > 3){
                System.out.println("Invalid input. Please try again:");
                continue;
            } 

            System.out.println("Input room number:");
            String roomCode = sc.nextLine();

            reservation = hotel.findReservationByRoom(roomCode); 

            if (reservation == null){
                System.out.println("Room ID invalid\n");
                continue;
            }
    
            switch(option) {
            case 1: 
                createNewRSO(reservation);
                break;
            case 2:
                updateRSO(reservation);
                break;
            case 3: 
                printRSO(reservation);
                break;
            }
        }
        return;
    }

    /**
     * Creates a new Room Service Order 
     * @param reservation the reservation that is making the Room Service Order
     */
    public void createNewRSO(Reservation reservation){
            
        RoomServiceOrder newRSO = new RoomServiceOrder(); 
        newRSO.setOrderStatus(ORDERSTATUS.CONFIRMED);

        int choice = 0; 
        

        hotel.getMenu().displayMenu();

        while (true) {
            System.out.println("Enter Item ID to add to order (Enter 0 to submit order):");
            choice = sc.nextInt(); 

            if (choice <= 0) break; 
            
            if (choice > hotel.getMenu().size()) {
                System.out.println("Choice Invalid.");
                continue; 
            }
            newRSO.addItemOrdered(hotel.getMenu().chooseItem(choice));

        }

        if (newRSO.size() == 0) { 
            System.out.println("Your order is empty.");
        }
        else {
            sc.nextLine(); 
            System.out.println("Enter any special requests (eg. less oil):");
            String remarks = sc.nextLine();

            int size = reservation.getOrderList().size();
            String orderID = reservation.getReservationCode() + '-' + String.valueOf(size+1); 
            newRSO.setRemarks(remarks);
            newRSO.setOrderID(orderID);
            newRSO.updateTotal();
            reservation.addRSO(newRSO);
        
            System.out.println("Your Order ID is:" + orderID);
            System.out.println("Order successfully added.\n\n");
            eRSO.addRSOToFile(newRSO, "RoomOrder.txt");
        }
        
    }

    /**
     * Update existing Room Service Order
     * @param reservation the reservation that made the Room Service Order
     */
    public void updateRSO(Reservation reservation) {

        RoomServiceOrder RSO; 

        for (int i = 0; i < reservation.getOrderList().size(); i++) {
            System.out.println((i + 1)+ ": " + reservation.getOrderList().get(i).getOrderID());
        }
        int option;

        while(true){
            System.out.println("Enter a number to select order:"); 

            option = sc.nextInt(); 
    
            if (option < 1 || option > reservation.getOrderList().size()) {
                System.out.println("Invalid input"); 
                continue;
            }
            RSO = reservation.getOrderList().get(option-1);
            break;

        }

        System.out.println("What is the new status of the order?");
        for (int i = 0; i < 3; i ++) {
            System.out.println((i+1) + ": " + ORDERSTATUS.values()[i]);
        }
        System.out.println("Enter number to make selection:");
        option = sc.nextInt(); 

        //if option out of range **

        RSO.setOrderStatus(ORDERSTATUS.values()[option -1]);
        eRSO.editRSO(RSO, "RoomOrder.txt");
        System.out.println("Order status successfully updated.\n");
    }

    /**
     * Print all Room Service Orders linked to a certain reservation
     * @param reservation the reservation that made the Room Service Orders
     */
    public void printRSO(Reservation reservation) {

        RoomServiceOrder RSO; 

        for (int i = 0; i < reservation.getOrderList().size(); i++) {
            System.out.println((i + 1)+ ": " + reservation.getOrderList().get(i).getOrderID());
        }
        int option;

        while(true){
            System.out.println("Enter a number to select order:"); 

            option = sc.nextInt(); 
    
            if (option < 1 || option > reservation.getOrderList().size()) {
                System.out.println("Invalid input"); 
                continue;
            }
            RSO = reservation.getOrderList().get(option-1);
            break;

        }
        System.out.println("\n____________________________\n");
        System.out.printf("%-20s%s%n","Order ID:",RSO.getOrderID());
        System.out.printf("%-4s%-16s%s%n","No:", "Items","Price");

        //print indiv items and price 
        for (int i = 0; i < RSO.getChosenMenuItems().size(); i++) {
            MenuItems menuItem = RSO.getChosenMenuItems().get(i); 
            System.out.printf("%-4d%-16s%.2f%n",i+1,menuItem.getItem(),menuItem.getPrice());
        }
        //print status 
        System.out.printf("%-20s%s%n","Remarks:",RSO.getRemarks());
        System.out.printf("%-20s%s%n","Order Status:",RSO.getOrderStatus());
        System.out.println("____________________________\n");
    }

}
