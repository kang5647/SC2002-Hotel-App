package hotelReservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReservationController {
	private final Scanner sc = new Scanner(System.in);
	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	private Hotel hotel; 
	private PrinterController printerController;
	private final EditFileReservation ef = new EditFileReservation(hotel);
	private final EditFileRoom er = new EditFileRoom(hotel);
	private final EditFileRSO eRSO = new EditFileRSO(hotel);
	
	public ReservationController(Hotel hotel) {
		this.hotel = hotel;
		printerController = new PrinterController(hotel);
	}
	/**
	 * Creates a new reservation
	 */
	public void newReservation() {
		String reservationCode = "";
		LocalDateTime dateOfCheckIn;
		LocalDateTime dateOfCheckOut;
		int numberOfAdults;
		int numberOfChildren;
		RESERVATIONSTATUS reservationStatus = RESERVATIONSTATUS.CONFIRMED;
		double discount;
		
		
		do {
			try {
				//check-in date 
				System.out.print("Check-in date(DD/MM/YYYY): ");
				String date = sc.nextLine();
				if(date.equals("-1"))
					return; 
				date = date + " 15:00";
				dateOfCheckIn = LocalDateTime.parse(date, dtf);
						
				//check-out date 
				System.out.print("Check-out date(DD/MM/YYYY): ");
				date = sc.nextLine();
				if(date.equals("-1")) 
					return;
				date = date + " 11:00";
				dateOfCheckOut = LocalDateTime.parse(date, dtf);
				
				//print available hotel.rooms 
				printerController.printAvailableRooms(dateOfCheckIn, dateOfCheckOut);
				
				
				System.out.println("Please enter the room ID (-1 to return to main menu): ");
				String roomID = sc.next();
				if(roomID.equals("-1"))
					return;
				
				//generate reservation code 
				Rooms room = hotel.findRoom(roomID);
				if(room != null)
					reservationCode = roomID + "-" + room.getAllReservations().size();
				else {
					System.out.println("Room not found. Please try again.");
					sc.nextLine();
					continue;
				}
				// if room occupied or under maintenance, throw exception
				System.out.println("Please enter the reservation detail (-1 to return to main menu)");

				//Number of adults 
				System.out.print("Number of adult(s): ");
				numberOfAdults = sc.nextInt();
				sc.nextLine();
				if(numberOfAdults == -1)
					return;
				
				//Number of children 
				System.out.print("Number of child(ren): ");
				numberOfChildren = sc.nextInt();
				sc.nextLine();
				if(numberOfChildren == -1) 
					return;
				
				//Discount (if any)
				System.out.print("Discount rate (in %): ");
				discount = sc.nextDouble();
				sc.nextLine();
				
				System.out.print("Reservation status (1. Checked-in, 2. Confirmed, 3. Expired, 4. In WaitList): ");
				int status = sc.nextInt();
				if(status == -1)
					return;
				reservationStatus = reservationStatusSelector(status);
				
				
				// add guest to reservation + hotel's array of guests
				GuestController guestControl = new GuestController(hotel);
				Guests currentGuest = guestControl.newGuest(); 
				Reservation newReservation = new Reservation(reservationCode, dateOfCheckIn, dateOfCheckOut, numberOfAdults, numberOfChildren,
						reservationStatus, discount);
				newReservation.addGuest(currentGuest);
				hotel.addGuests(currentGuest);
				
				//set room
				 newReservation.setRoom(room);
				//add reservation to the room 
				room.addReservation(newReservation);
				hotel.addReservation(newReservation);
				
				//if reservation is confirmed and dateofcheckin == today's date
				if(reservationStatus.equals(RESERVATIONSTATUS.CONFIRMED) && dateOfCheckIn.toLocalDate() == LocalDateTime.now().toLocalDate())
					room.setStatus(ROOMSTATUS.RESERVED);
				else if(reservationStatus.equals(RESERVATIONSTATUS.CHECKED_IN)) {
					room.setStatus(ROOMSTATUS.OCCUPIED);
					er.setRoom(room);
					er.edit("Rooms.txt");
				}
				ef.setReservation(newReservation);
				ef.addRecord("Reservation.txt");
				
				System.out.println("\nSuccessfully addded reservation with code " + reservationCode + "\n");
				newReservation.printString();
				// System.out.println(newReservation.toString());
				return;
				// show the bill of the reserved room 
			} catch(Exception e) {
				System.out.println("Failed to add new reservation");
			}
		} while(true);
		
		
	}
	
	/**
	 * Updates details of existing reservation
	 */
	public void updateReservation() {
		boolean isFound = false;
		Reservation reservation = null;
		Rooms room = null;
		do {
			try {
				//sc.nextLine();
				System.out.print("Enter the reservation code (-1 to return to main menu): ");
				String code = sc.nextLine();
				if(code.equals("-1"))
					return;
				
				String roomID = code.substring(0,5);
				// search for the room using the reservation code 
				room = hotel.findRoom(roomID);
				if(room != null) {
				//search if reservation exist in that room
					reservation = room.getReservation(code);
					if(reservation == null)
						throw new Exception();
					else 
						isFound = true;
				}
				else {
					System.out.println("Room not found. Please try again");
					continue;
				}
				//if not found, throw exception
				//if found, end the loop 
			} catch(Exception e) {
				System.out.println("Failed to find the reservation. Please try again. ");
				//sc.nextLine();
			}
		}while(!isFound);
		
		if(isFound) {
			System.out.println("\nThis is your reservation");
			reservation.printString();
			do {
				System.out.println("Select an option to update (-1 to return to main menu) "
									+ "\n1. Check-In/Check-Out"
									+ "\n2. Number of Adults"
									+ "\n3. Number of Children"
									+ "\n4. Reservation Status"
									+ "\n5. Discount rate");
			
				int option = sc.nextInt();
				if(option == -1)
					return;
				sc.nextLine();
				try {
					switch(option) {
					case 1:
						printerController.printReservationByRoom(room, reservation);
						System.out.print("Check-in date(DD/MM/YYYY): ");
						String date = sc.nextLine();
						if(date.equals("-1"))
							return;
						date = date + " 15:00";
						LocalDateTime checkIn = LocalDateTime.parse(date, dtf);
					
						System.out.print("Check-out date(DD/MM/YYYY): ");
						date = sc.nextLine();
						if(date.equals("-1"))
							return;
						date = date + " 11:00";
						LocalDateTime checkOut = LocalDateTime.parse(date, dtf);
						//check check-out date, if okay, update the check-out data 
						if(CheckDateUtility.checkCrash(checkIn, checkOut, room.getAllReservations())) {
							System.out.print("Crash with another reservation! You will be waitlisted, are you sure you want to continue (Y/N): ");
							char choice = sc.next().charAt(0);
							switch(choice) {
							case 'y', 'Y':
								reservation.setDateOfCheckIn(checkIn);
								reservation.setDateOfCheckOut(checkOut);
								reservation.setReservationStatus(RESERVATIONSTATUS.WAITLIST);
								break;
							default: 
								break;
							}
						}
						else {
							reservation.setDateOfCheckIn(checkIn);
							reservation.setDateOfCheckOut(checkOut);
						}
						room.addReservation(reservation);
						break;
						
					case 2:  
					      System.out.print("Number of adult(s): "); 
					      int numberOfAdults = sc.nextInt(); 
					      if(numberOfAdults == -1) 
					       return; 
					      reservation.setNumberOfAdults(numberOfAdults); 
					      sc.nextLine(); 
					      break; 
					       
					case 3:  
					      System.out.print("Number of child(ren): "); 
					      int numberOfChildren = sc.nextInt(); 
					      if(numberOfChildren == -1) 
					       return; 
					 
					      reservation.setNumberOfChildren(numberOfChildren); 
					       
					      sc.nextLine(); 
					       
					       
					      break; 
					     
				    case 4: 
					      System.out.print("Reservation status (1. Checked-in, 2. Confirmed, 3. Expired, 4. In WaitList): "); 
					      int status = sc.nextInt(); 
					      if(status == -1) 
					       return; 
					      switch (status){ 
					       case 1: 
					        reservation.setReservationStatus(RESERVATIONSTATUS.CHECKED_IN); 
					        break; 
					       case 2: 
					        reservation.setReservationStatus(RESERVATIONSTATUS.CONFIRMED); 
					        break; 
					       case 3: 
					        reservation.setReservationStatus(RESERVATIONSTATUS.EXPIRED); 
					        break; 
					       case 4: 
					        reservation.setReservationStatus(RESERVATIONSTATUS.WAITLIST); 
					        break; 
					      } 
					       
					      break; 
					     
					case 5: 
					      System.out.print("Discount rate: "); 
					      double discount = sc.nextDouble(); 
					      if(discount == -1) 
					       return; 
					      reservation.setDiscount(discount); 
					      break; 
					     default:  
					      return; 
					     } 
						 ef.setReservation(reservation);
					     ef.edit("Reservation.txt"); 
					 
					     //update reservation  
					     System.out.println("Successfully updated reservation with code  "); 
					    //  System.out.println(reservation.toString()); 
						 reservation.printString();
					     return; 
					    } catch(Exception e) { 
					     System.out.println("Failed to update reservation detail, please try again"); 
					     //sc.nextLine(); 
					    } 
					   } while(true); 
			 } 
	}
	
	/**
	 * Removes an existing reservation
	 */
	public void removeReservation() {
		String reservationCode = "";
		do {
			try {
				System.out.print("Enter the reservation code to remove (-1 to return to main menu): ");
				reservationCode = sc.nextLine();
				if(reservationCode.equals("-1"))
					return; 
				
				String roomID = reservationCode.substring(0,5);
				Rooms room = hotel.findRoom(roomID);
				for(Reservation r : room.getAllReservations()) {
					if(r.getReservationCode() == reservationCode)
						System.out.println();
						r.printString();
						System.out.println("Are you sure you want to remove this reservation? (Y/N)");
						String option = sc.nextLine();
						if((!option.equals("y")) && (!option.equals("Y")))
							return;
						room.getAllReservations().remove(r);
						hotel.getAllReservations().remove(r);
						Guests guest = r.getGuest();
						guest.getAllReservations().remove(r);
						ef.setReservation(r);
						ef.deleteRecord("Reservation.txt");
						eRSO.deleteRSO(reservationCode, "RoomOrder.txt");
						System.out.println("Reservation successfully removed from the system. \n");
						break;
				}
				return;
				//search for the reservation, if found, remove it
				//change the status of the room to vacant
			}catch(Exception e) {
				System.out.println("Failed to remove the reservation, please try again \n");
				//sc.nextLine();
			}
		}while(true);
		
	}
	
	/**
	 * @return selected reservation status 
	 */
	public RESERVATIONSTATUS reservationStatusSelector(int choice) {
		switch(choice) {
		case 1: 
			return RESERVATIONSTATUS.CHECKED_IN;
		case 2:
			return RESERVATIONSTATUS.CONFIRMED;
		case 3: 
			return RESERVATIONSTATUS.EXPIRED;
		case 4: 
			return RESERVATIONSTATUS.WAITLIST;
		default:
			return RESERVATIONSTATUS.CONFIRMED;
		}
	}
}
