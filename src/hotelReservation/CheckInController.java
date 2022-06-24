package hotelReservation;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

public class CheckInController {
	
	private Hotel hotel;
	private final Scanner sc = new Scanner(System.in);
	private ReservationController rc;
	private EditFileReservation ef;
	private EditFileRoom er;
	
	public CheckInController(Hotel hotel) {
		this.hotel = hotel;
		rc = new ReservationController(hotel);
		ef = new EditFileReservation(hotel);
		er = new EditFileRoom(hotel);
	}
	
	/**
	 * Checks-in a guest
	 * Creates a new reservation if guest does not have one 
	 * If check-in time is before scheduled check-in time (3pm), checks if room is vacant
	 * If check-in time is after the scheduled check-in time, check if its not more than 1 hour 
	 * Sets room status to occupied
	 * Sets reservation status to checked-in
	 */
	public void checkIn() {
		try {
			System.out.print("Do you have a reservation? (Y/N, -1 to return to main menu): ");
			char option = sc.next().charAt(0);
			sc.nextLine();
			Rooms room;
			
			//if no reservation, check for available room to check in
			if(option == 'N' || option == 'n') {
				//printRoomReport();
				//System.out.print("Enter the room ID to check in: ")				
				rc.newReservation();	//updates status to occupied				
			}
			else if(option == 'Y' || option == 'y'){
				Reservation currentReservation = null;
				boolean isFound = false;
				do{
					System.out.print("Enter the reservation code to check in (-1 to return to main menu): ");
					
					String reservationCode = sc.nextLine();
					if(reservationCode.equals("-1"))
						return;
					String roomID = reservationCode.substring(0,5);
					
					if ((room = hotel.findRoom(roomID)) != null)
					{
						isFound = true;
						currentReservation = room.getReservation(reservationCode);
					}
				}while(!isFound);
				
				//get the current date and time in Singapore timezone
				LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("Asia/Singapore"));
				//check for check-in time
				//if check-in time is before the scheduled check-in time (3pm), check if room is vacant,
				if(currentTime.toLocalDate().equals(currentReservation.getDateOfCheckIn().toLocalDate())) {
					if(currentTime.toLocalTime().toSecondOfDay() < currentReservation.getDateOfCheckIn().toLocalTime().toSecondOfDay()) {
						// check room is vacant/ reserved 
						if(room.getStatus().equals(ROOMSTATUS.VACANT)) {
							currentReservation.setReservationStatus(RESERVATIONSTATUS.CHECKED_IN);
							room.setStatus(ROOMSTATUS.OCCUPIED);
						}
						else if(room.getStatus().equals(ROOMSTATUS.OCCUPIED)){
							System.out.println("The room is still occupied. Please come back again soon");
						}
					}
					else if(currentTime.toLocalTime().toSecondOfDay() - currentReservation.getDateOfCheckIn().toLocalTime().toSecondOfDay() <3600) {
						room.setStatus(ROOMSTATUS.OCCUPIED);
						currentReservation.setReservationStatus(RESERVATIONSTATUS.CHECKED_IN);
					}
				//if check-in time is after the scheduled check-in time, check if its not more than 1 hour 
					else if(currentTime.toLocalTime().toSecondOfDay() - currentReservation.getDateOfCheckIn().toLocalTime().toSecondOfDay() >3600) {
						currentReservation.setReservationStatus(RESERVATIONSTATUS.EXPIRED);
						System.out.println("Sorry. Your reservation has expired");
						
						 ef.setReservation(currentReservation);
						 ef.edit("Reservation.txt"); 
						throw new Exception();
					}
					ef.setReservation(currentReservation);
				    ef.edit("Reservation.txt"); 

					//update room database
				    er.setRoom(room);
					er.edit("Rooms.txt");	
					System.out.println("Check-in Done \n");
					
					}
				else {
					currentReservation.setReservationStatus(RESERVATIONSTATUS.EXPIRED);
					ef.setReservation(currentReservation);
					ef.edit("Reservation.txt"); 
					throw new Exception();
				}
				
			}
			else {
				return;
			}
			// set room status to OCCUPIED
		} catch (Exception e) {
			
			System.out.println("Check in failed");
			return;
		}
	}
}
