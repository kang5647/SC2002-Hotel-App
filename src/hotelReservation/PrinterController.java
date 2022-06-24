package hotelReservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class PrinterController {
	
	private Hotel hotel;
	
	public PrinterController(Hotel hotel) {
		this.hotel = hotel;
	}
	public void printRoomReport() {
		ArrayList<String> singleRoom = new ArrayList<String>(); //vacant single
		ArrayList<String> doubleRoom = new ArrayList<String>(); //vacant double
		ArrayList<String> deluxeRoom = new ArrayList<String>(); //vacant Deluxe
		ArrayList<String> suite = new ArrayList<String>(); //vacant suite
		ArrayList<String> vipRoom = new ArrayList<String>(); //vacant vip
		ArrayList<String> room_Occ = new ArrayList<String>();
		ArrayList<String> room_Res = new ArrayList<String>();
		ArrayList<String> room_Und = new ArrayList<String>();
		
		int stotal=0, svacant=0, doubleTotal=0, doubleVacant=0, deluxTotal=0, deluxVacant=0, suiteTotal =0, suiteVacant= 0, vipTotal=0, vipVacant=0;
		
		ArrayList<Rooms> rooms = hotel.getAllRooms();
		for (int i=0;i<rooms.size();i++) {
			if(rooms.get(i).getRoomType()==ROOMTYPE.SINGLE) {
				stotal+=1;
				if(rooms.get(i).getStatus()==ROOMSTATUS.VACANT) {
					svacant+=1;
					singleRoom.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.OCCUPIED) {
					room_Occ.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.RESERVED) {
					room_Res.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.UNDER_MAINTENANCE) {
					room_Und.add(rooms.get(i).getID());
				}
			}else if(rooms.get(i).getRoomType()==ROOMTYPE.DOUBLE) {
				doubleTotal+=1;
				if(rooms.get(i).getStatus()==ROOMSTATUS.VACANT) {
					doubleVacant+=1;
					doubleRoom.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.OCCUPIED) {
					room_Occ.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.RESERVED) {
					room_Res.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.UNDER_MAINTENANCE) {
					room_Und.add(rooms.get(i).getID());
				}
			}else if (rooms.get(i).getRoomType()==ROOMTYPE.DELUXE) {
				deluxTotal+=1;
				if(rooms.get(i).getStatus()==ROOMSTATUS.VACANT) {
					deluxVacant+=1;
					deluxeRoom.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.OCCUPIED) {
					room_Occ.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.RESERVED) {
					room_Res.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.UNDER_MAINTENANCE) {
					room_Und.add(rooms.get(i).getID());
				}
			}else if (rooms.get(i).getRoomType()==ROOMTYPE.SUITE) {
				suiteTotal+=1;
				if(rooms.get(i).getStatus()==ROOMSTATUS.VACANT) {
					suiteVacant+=1;
					suite.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.OCCUPIED) {
					room_Occ.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.RESERVED) {
					room_Res.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.UNDER_MAINTENANCE) {
					room_Und.add(rooms.get(i).getID());
				}
			}else if (rooms.get(i).getRoomType()==ROOMTYPE.VIP) {
				vipTotal+=1;
				if(rooms.get(i).getStatus()==ROOMSTATUS.VACANT) {
					vipVacant+=1;
					vipRoom.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.OCCUPIED) {
					room_Occ.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.RESERVED) {
					room_Res.add(rooms.get(i).getID());
				}else if (rooms.get(i).getStatus()==ROOMSTATUS.UNDER_MAINTENANCE) {
					room_Und.add(rooms.get(i).getID());
				}
			}
		}

		//room type occupancy rate
		System.out.println("\nRoom Status statistic report");
		System.out.println("\n__________________________________________________\n");
		
		System.out.println("By Room type occupancy rate (room status = vacant) \n");
		
		int counter = 1; 

		System.out.printf("%-11s%-9s","Single:","Number:");
		System.out.println(svacant + " out of " + stotal);
		System.out.printf("%18s%2s","Rooms: "," ");
		for(int i=0;i<singleRoom.size();i++) {
			System.out.printf(singleRoom.get(i));
			if(i<singleRoom.size()-1) {
				System.out.printf(", ");
			}
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%20s"," ");
			}
			counter++;
		}	
		counter = 1; 	

		System.out.printf("%n%-11s%-9s","Standard:","Number:");
		System.out.println(doubleVacant + " out of " + doubleTotal);
		System.out.printf("%18s%2s","Rooms: "," ");
		for(int i=0;i<doubleRoom.size();i++) {
			System.out.printf(doubleRoom.get(i));
			if(i<doubleRoom.size()-1) {
				System.out.printf(", ");
			}
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%20s"," ");
			}
			counter++;
		}
		counter = 1; 
	
		System.out.printf("%n%-11s%-9s","Deluxe:","Number:");
		System.out.println(deluxVacant + " out of " + deluxTotal);
		System.out.printf("%18s%2s","Rooms: "," ");
		for(int i=0;i<deluxeRoom.size();i++) {
			System.out.printf(deluxeRoom.get(i));
			if(i<deluxeRoom.size()-1) {
				System.out.printf(", ");
			}
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%20s"," ");
			}
			counter++;
		}
		counter = 1; 

		System.out.printf("%n%-11s%-9s","Suite:","Number:");
		System.out.println(suiteVacant + " out of " + suiteTotal);
		System.out.printf("%18s%2s","Rooms: "," ");
		for(int i=0;i<suite.size();i++) {
			System.out.printf(suite.get(i));
			if(i<suite.size()-1) {
				System.out.printf(", ");
			}
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%20s"," ");
			}
			counter++;
		}
		counter = 1; 

		System.out.printf("%n%-11s%-9s","VIP:","Number:");
		System.out.println(vipVacant + " out of " + vipTotal);
		System.out.printf("%18s%2s","Rooms: "," ");
		for(int i=0;i<vipRoom.size();i++) {
			System.out.printf(vipRoom.get(i));
			if(i<vipRoom.size()-1) {
				System.out.printf(", ");
			}
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%20s"," ");
			}
			counter++;
		}
		
		System.out.println("\n__________________________________________________");

		System.out.println("\nBy Room Status\n");
		
		//room status -- vacant
		counter = 1; 

		System.out.printf("%-11s%n","Vacant:");
		System.out.printf("%18s","Rooms: ");
	
		for(int i=0;i<singleRoom.size();i++) {
			System.out.printf(singleRoom.get(i)+", ");
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%18s"," ");
			}
			counter++;
		}
		for(int i=0;i<doubleRoom.size();i++) {
			System.out.printf(doubleRoom.get(i)+", ");
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%18s"," ");
			}
			counter++;
		}
		for(int i=0;i<deluxeRoom.size();i++) {
			System.out.printf(deluxeRoom.get(i)+", ");
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%18s"," ");
			}
			counter++;
		}
		for(int i=0;i<suite.size();i++) {
			System.out.printf(suite.get(i)+", ");
			if (counter == 10) {
				counter = 0; 
				System.out.printf("%n%18s"," ");
			}
			counter++;
		}
		for(int i=0;i<vipRoom.size();i++) {
			System.out.printf(vipRoom.get(i));
			if(i<vipRoom.size()-1) {
				System.out.printf(", ");
				if (counter == 10) {
					counter = 0; 
					System.out.printf("%n%18s"," ");
				}
				counter++;
			}
		}
		//room status -- Occupied
		
		System.out.printf("%n%-11s%n","Occupied:");
		System.out.printf("%18s","Rooms: ");
		for(int i=0;i<room_Occ.size();i++) {
			System.out.printf(room_Occ.get(i));
			if(i<room_Occ.size()-1) {
				System.out.printf(", ");
			}
		}

		//room status -- Reserved
		System.out.printf("%n%-11s%n","Reserved:");
		System.out.printf("%18s","Rooms: ");
		for(int i=0;i<room_Res.size();i++) {
			System.out.printf(room_Res.get(i));
			if(i<room_Res.size()-1) {
				System.out.printf(", ");
			}
		}

		//room status -- UnderMaintenance
		
		System.out.printf("%n%-11s%n","UnderMaintenance:");
		System.out.printf("%18s","Rooms: ");
		for(int i=0;i<room_Und.size();i++) {
			System.out.printf(room_Und.get(i));
			if(i<room_Und.size()-1) {
				System.out.printf(", ");
			}
		}
		System.out.println("");
	}
	
	/**
	 * Prints all reservations
	 */
	public void printAllReservations() {
		System.out.println("");
		for(Reservation r: hotel.getAllReservations()) {
			//if (!r.getReservationStatus().equals(RESERVATIONSTATUS.EXPIRED))
				r.printString();		
			// System.out.println(r.toString());
			// System.out.println("");
		}
	}
	
	/**
	 * Prints all available rooms
	 * @param checkInDate start of date range
	 * @param checkOutDate end of date range 
	 */
	public void printAvailableRooms(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
		ArrayList<String> singleRoom = new ArrayList<String>(); //vacant single
		ArrayList<String> doubleRoom = new ArrayList<String>(); //vacant double
		ArrayList<String> deluxeRoom = new ArrayList<String>(); //vacant Deluxe
		ArrayList<String> suite = new ArrayList<String>(); //vacant suite
		ArrayList<String> vipRoom = new ArrayList<String>(); //vacant vip
		
		ArrayList<Rooms> rooms = hotel.getAllRooms();
		for(int i = 0; i< rooms.size(); i++) {
			Rooms room = rooms.get(i);
			if(room.getStatus() != ROOMSTATUS.VACANT)
				continue;
			if(room.getRoomType() == ROOMTYPE.SINGLE) {
				if(room.getAllReservations().size() ==0)
					singleRoom.add(room.getID());
				else 
					if(!CheckDateUtility.checkCrash(checkInDate, checkOutDate, room.getAllReservations()))
							singleRoom.add(room.getID());
				}	      
			if(room.getRoomType() == ROOMTYPE.DOUBLE) {
				if(room.getAllReservations().size() ==0)
					doubleRoom.add(room.getID());
				else 
					if(!CheckDateUtility.checkCrash(checkInDate, checkOutDate, room.getAllReservations()))
						doubleRoom.add(room.getID());
				}	   
			if(room.getRoomType() == ROOMTYPE.DELUXE) {
				if(room.getAllReservations().size() ==0)
					deluxeRoom.add(room.getID());
				else 
					if(!CheckDateUtility.checkCrash(checkInDate, checkOutDate, room.getAllReservations()))
						deluxeRoom.add(room.getID());
				}	   
			if(room.getRoomType() == ROOMTYPE.SUITE) {
				if(room.getAllReservations().size() ==0)
					suite.add(room.getID());
				else 
					if(!CheckDateUtility.checkCrash(checkInDate, checkOutDate, room.getAllReservations()))
						suite.add(room.getID());
			}
			if(room.getRoomType() == ROOMTYPE.VIP) {
				if(room.getAllReservations().size() ==0)
					vipRoom.add(room.getID());
				else 
					if(!CheckDateUtility.checkCrash(checkInDate, checkOutDate, room.getAllReservations()))
						vipRoom.add(room.getID());
			}	   
					
			}
		
		// print available room 
		System.out.println("Single Rooms: ");
		System.out.print("             ");
		for(int i = 0; i<singleRoom.size(); i++) {
			System.out.print(singleRoom.get(i));
			if(i<singleRoom.size()-1) {
				System.out.print(", ");
			}
		}
		
		System.out.println("\nDouble Rooms: ");
		System.out.print("             ");
		for(int i = 0; i<doubleRoom.size(); i++) {
			System.out.print(doubleRoom.get(i));
			if(i<doubleRoom.size()-1) {
				System.out.print(", ");
			}
		}
		
		System.out.println("\nDeluxe Rooms: ");
		System.out.print("             ");
		for(int i = 0; i<deluxeRoom.size(); i++) {
			System.out.print(deluxeRoom.get(i));
			if(i<deluxeRoom.size()-1) {
				System.out.print(", ");
			}
		}
		
		System.out.println("\nSuite: ");
		System.out.print("             ");
		for(int i = 0; i<suite.size(); i++) {
			System.out.print(suite.get(i));
			if(i<suite.size()-1) {
				System.out.print(", ");
			}
		}
		
		System.out.println("\nVIP Rooms: ");
		System.out.print("             ");
		for(int i = 0; i<vipRoom.size(); i++) {
			System.out.print(vipRoom.get(i));
			if(i<vipRoom.size()-1) {
				System.out.print(", ");
			}
		}
		
		System.out.println("\n");
	}
	
	/**
	 * Helper function for Update Reservation
	 * Print all reservations for the room that isn't current reservation i.e. when room isn't available
	 * @param room to be searched
	 * @param myReservation current reservation of guest 
	 */
	public void printReservationByRoom(Rooms room, Reservation myReservation) {
		
		LocalDate checkIn;
		LocalDate checkOut;
		System.out.println("Room " + room.getID() + " (List of Reservations): ");
		System.out.println(myReservation.getDateOfCheckIn().toLocalDate() + " - " +
							myReservation.getDateOfCheckOut().toLocalDate() + " (your reservation)");
		
		Collections.sort(room.getAllReservations());
		
		for(Reservation r : room.getAllReservations()) {
			if(r == myReservation) 
				continue;
			checkIn = r.getDateOfCheckIn().toLocalDate();
			checkOut = r.getDateOfCheckOut().toLocalDate();
			
			System.out.println(checkIn + " - " + checkOut);
		}
		room.getAllReservations().remove(myReservation);
		System.out.println();
	}
}
