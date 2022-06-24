package hotelReservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CheckDateUtility {
	
	/**
	 * Utility Function to check if requested reservation date coincides with existing reservations
	 * @param checkIn start of date range
	 * @param checkOut end of date range
	 * @param reservations Array List of existing reservations
	 * @return whether there's a clash 
	 */
	public static boolean checkCrash(LocalDateTime checkIn, LocalDateTime checkOut, ArrayList<Reservation> reservations) {
		
		LocalDate checkInDate = checkIn.toLocalDate();
		LocalDate checkOutDate = checkOut.toLocalDate();
		//return false if check-in date and check-out date are on the same day
		if (reservations.size() == 0) return false;
		for (Reservation r : reservations ) {
			boolean retVal1 = checkInDate.isBefore(r.getDateOfCheckIn().toLocalDate()) && 
							  (checkOutDate.isBefore(r.getDateOfCheckIn().toLocalDate()) || checkOutDate.equals(r.getDateOfCheckIn().toLocalDate()));
			
			boolean retVal2 = (checkInDate.isAfter(r.getDateOfCheckOut().toLocalDate()) || checkInDate.equals(r.getDateOfCheckOut().toLocalDate())) &&
								checkOutDate.isAfter(r.getDateOfCheckOut().toLocalDate());
			
			if(retVal1 || retVal2)
				return false;
		}
		return true;
	}
}
