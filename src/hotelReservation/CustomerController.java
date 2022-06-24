package hotelReservation;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomerController {
	private final Scanner sc = new Scanner(System.in);
	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	private Hotel hotel; 
	private ReservationController reservationController;
	private CheckInController checkInController;
	private CheckOutController checkOutController;
	private PrinterController printerController;
	
	public CustomerController(Hotel hotel) {
		this.hotel = hotel;
		reservationController = new ReservationController(hotel);
		checkInController = new CheckInController(hotel);
		checkOutController = new CheckOutController(hotel);
		printerController = new PrinterController(hotel);
	}
	
	public void chooseOption() {
		int option;
		
		while(true){

			System.out.println("\nPlease select one operation to proceed\n"
								+ "1. Create new reservation \n"
								+ "2. Update reservation \n"
								+ "3. Remove reservation \n"
								+ "4. Print all reservations \n"
								+ "5. Check-in \n"
								+ "6. Check-out and print bill invoice \n"
								+ "7. Print Room Report \n"
								+ "8. Return to previous menu\n");
			
			option = sc.nextInt();
			sc.nextLine();
			if(option < 1 || option >8){
				System.out.println("Invalid option.");
				continue;
			}
			switch(option) {
			 case 1: 
			 	reservationController.newReservation();
			 	break;
			 case 2:
			 	reservationController.updateReservation();
			 	break;
			 case 3: 
			 	reservationController.removeReservation();
			 	break;
			 case 4:
				 printerController.printAllReservations();
			 	break;
			case 5:
				checkInController.checkIn();
				break;
			case 6:
				checkOutController.checkOut();
				break;
			case 7:
				printerController.printRoomReport();
				break;
			case 8: 
				return;
			}
		}
	}
}
