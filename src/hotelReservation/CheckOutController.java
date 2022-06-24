package hotelReservation;

import java.util.Scanner;

public class CheckOutController {

	private Hotel hotel;
	private final Scanner sc = new Scanner(System.in);
	private EditFileReservation ef;
	private EditFileRoom er;
	
	public CheckOutController(Hotel hotel) {
		this.hotel = hotel;
		ef = new EditFileReservation(hotel);
		er = new EditFileRoom(hotel);
	}
	
	/**
	 * Checks-out the guest
	 * Prints total bill
	 * Accepts payment 
	 */
	public void checkOut() {
		System.out.println("Enter room ID: ");
		String roomID = sc.nextLine();

		Reservation reservation = hotel.findReservationByRoom(roomID); 

		Bill bill = new Bill(reservation);
		

		System.out.println("\n________________________________________\n");


		System.out.printf("%-20s%-4s%-10s%-10s%n","Item","Qty","Unit $","Total");
		System.out.printf("%-20s%-4d%-10.2f%-10.2f%n","Room (Weekdays)", bill.getNumWeekdays(),reservation.getRoom().getRate(), (float)bill.getNumWeekdays()*reservation.getRoom().getRate());
		System.out.printf("%-20s%-4d%-10.2f%-10.2f%n","Room (Weekends)", bill.getNumWeekends(),reservation.getRoom().getWeekendRate(), (float)bill.getNumWeekends()*reservation.getRoom().getWeekendRate());
		System.out.printf("%-20s%-4d%-10.2f%-10.2f%n","Discount (-)",1,bill.getDiscount(),bill.getDiscount());
		System.out.printf("%-20s%-4d%-10.2f%-10.2f%n%n","Subtotal (Rooms)",1,bill.getRoomFee(),bill.getRoomFee());

		for(int i =0; i<reservation.getOrderList().size();i++){ //reservation has array of RSO
			
			for (int j = 0; j < reservation.getOrderList().get(i).getChosenMenuItems().size(); j++) {
				
				System.out.printf("%-20s%-4d%-10.2f%-10.2f%n", reservation.getOrderList().get(i).getChosenMenuItems().get(j).getItem(),1,
				reservation.getOrderList().get(i).getChosenMenuItems().get(j).getPrice(),reservation.getOrderList().get(i).getChosenMenuItems().get(j).getPrice());

			}
		}
		System.out.printf("%-20s%-4d%-10.2f%-10.2f%n%n","Subtotal (Service)",1,bill.getRoomServiceFee(),bill.getRoomServiceFee());


		System.out.printf("%-20s%-4d%-10.2f%-10.2f%n", "Tax",1,bill.getTax(),bill.getTaxAmount(),bill.getTaxAmount()); //tax

		// System.out.println("Discount" );

		System.out.printf("%-20s%-4d%-10.2f%-10.2f%n", "Total",1,bill.getTotalPrice(),bill.getTotalPrice());


		// System.out.println("\nTotal Price:" + bill.getTotalPrice()); 

		System.out.println("\n________________________________________\n");
		

		boolean success = true;
		
		while (success) {
			   System.out.println("Select your method of payment:");
			   System.out.println("1. Cash");
			   System.out.println("2. Credit Card " + reservation.getGuest().getCard().getCardNumber().substring(1, 4)+"XXXXXX");
			   System.out.println("3. Paypal");

			   int choice = sc.nextInt();
			   sc.nextLine(); 
			   
			   switch (choice) {
			    case 1: 
					System.out.println("Enter cash amount:");
					float cashReceived = sc.nextFloat(); 
					if (cashReceived < bill.getTotalPrice()){ 
						System.out.println("Cash amount insufficient. Please try again.");
						continue; 
			     	}
					else {
						System.out.printf("%-20s%-20.2f%n","Change: ",(cashReceived-bill.getTotalPrice()));
						System.out.println("Payment successful. Hope you enjoyed your stay. \n");
						success= false;
					}
					break;
			    case 2: 
					System.out.println("Payment successful. Hope you enjoyed your stay. \n");
					success = false;
					break;
				case 3: 
					System.out.println("Payment successful. Hope you enjoyed your stay. \n");
					success = false;
					break;
			    default: 
			     	System.out.println("Invalid option. Please try again.");
			   	}
		}
		//make room avaialble **** 
			Rooms room = hotel.findRoom(roomID);
			room.setStatus(ROOMSTATUS.VACANT);
			reservation.setReservationStatus(RESERVATIONSTATUS.EXPIRED);
			
			//update room in database
			er.setRoom(room);
			er.edit("Rooms.txt");
			
			//update reservations in database
			ef.setReservation(reservation);
			ef.edit("Reservation.txt"); 
		
		
	}
}
