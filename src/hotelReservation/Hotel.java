package hotelReservation;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Hotel class that contains the rooms, reservations, guests, orders and menu.
 */
public class Hotel {

    /**
	 * Name of hotel 
	 */
    private String hotelName;
    /**
     * Array of rooms in the hotel 
     */ 
    private ArrayList<Rooms> rooms;
    /**
     * Array of all reservations ever made at the hotel 
     */
    private ArrayList<Reservation> reservations;
    /**
     * Array of current guests at hotel 
     */
    private ArrayList<Guests> guests;
     /**
     * Array of all Room Service Orders ever made at hotel 
     */
    private ArrayList<RoomServiceOrder> rso_list;
    private EditFileReservation ef = new EditFileReservation(this);   
    private EditFileRoom er = new EditFileRoom(this);
    private EditFileRSO eRSO = new EditFileRSO(this);
    private EditFileGuest eg = new EditFileGuest(this);
    /**
     * Menu of Room Service Orders that can be made 
     */
    private Menu menu; 
 

    /**
     * Constructor 
     * @param hotelName name of Hotel 
     */
    Hotel (String hotelName) {
        this.hotelName = hotelName; 
        rooms = new ArrayList<Rooms>();
        reservations = new ArrayList<Reservation>();
        guests = new ArrayList<Guests>();
        rso_list = new ArrayList<RoomServiceOrder>();
        
        eg.readFile("Guest.txt");
        ef.readFile("Reservation.txt");
        er.readFile("Rooms.txt");
        menu = new Menu();

        for(Reservation reservation : reservations) {
        	if(reservation.getReservationStatus() == RESERVATIONSTATUS.CONFIRMED)
        		if(LocalDateTime.now().toLocalDate().isAfter(reservation.getDateOfCheckIn().toLocalDate()))
        			reservation.setReservationStatus(RESERVATIONSTATUS.EXPIRED);
        		else if(LocalDateTime.now().toLocalTime().toSecondOfDay() - reservation.getDateOfCheckIn().toLocalTime().toSecondOfDay() >3600)
        			reservation.setReservationStatus(RESERVATIONSTATUS.EXPIRED);
        	String roomID = reservation.getReservationCode().substring(0,5);
        	Rooms room = this.findRoom(roomID);
        	
        	if(room == null) {
        		return;
        	}
        	if(room.getStatus() != ROOMSTATUS.OCCUPIED) {
        		if(reservation.getDateOfCheckIn().toLocalDate() == LocalDateTime.now().toLocalDate())
        			room.setStatus(ROOMSTATUS.RESERVED);
        	}
        	room.addReservation(reservation);
        	reservation.setRoom(room);
        	
        	ef.setReservation(reservation);
        	ef.edit("Reservation.txt");
        	
        	er.setRoom(room);
        	er.edit("Rooms.txt");
        }
        eRSO.readFile("RoomOrder.txt");
        
        

    }

    /**
     * @return menu available at hotel 
     */
    public Menu getMenu(){
        return menu;
    }

    /**
     * @param roomCode Room ID to be searched 
     * @return reservation linked to room code 
     */
    public Reservation findReservationByRoom(String roomCode){
        for(int i=0; i<rooms.size(); i++){
            if(rooms.get(i).getID().compareTo(roomCode) == 0){
                return rooms.get(i).getCurrentReservation();
            }
        }
        return null;
    }

    /**
     * Find Room by room code 
     * @param roomCode room ID 
     * @return room object 
     */
    public Rooms findRoom(String roomCode){
        for(int i=0; i<rooms.size(); i++){
            if(rooms.get(i).getID().compareTo(roomCode) == 0){
                return rooms.get(i);
            }
        }
        return null;
    }
    
    /**
     * Search guests by name 
     * @param name of guest to be searched
     * @return array list of guests who share the name 
     */
    public ArrayList<Guests> searchGuests(String name){
        ArrayList<Guests> guestArray = new ArrayList<Guests>(); 

        for(int i=0; i<guests.size(); i++){
            if(guests.get(i).getName().toUpperCase().contains(name.toUpperCase()) == true){
                guestArray.add(guests.get(i));
            }
        }

        return guestArray;
    }

    /**
     * Search guests by ID 
     * @param identityNo ID number 
     * @return guest object 
     */
    public Guests searchGuestsbyID(String identityNo){
        for(int i=0; i<guests.size(); i++){
            if(guests.get(i).getIdentity().getIdNumber().compareTo(identityNo) == 0){
                return guests.get(i);
            }
        }
        return null;
    }

    /**
     * Getter that returns all reservations
     * @return ArrayList of all reservations
     */
    public ArrayList<Reservation> getAllReservations() {
    	return reservations;
    }
    
    /**
     * @return ArrayList of all rooms in hotel 
     */
    public ArrayList<Rooms> getAllRooms(){
    	return rooms;
    }
    
    public ArrayList<Guests> getAllGuests(){
    	return guests;
    }
    
    /**
     * Adds guest to hotel 
     * @param guest Guest 
     */
    public void addGuests(Guests guest) {
        guests.add(guest);
    }
    
    /**
     * Adds reservation to the array
     * @param reservation Reservation
     */
    public void addReservation(Reservation reservation) {
    	reservations.add(reservation);
    }
}
