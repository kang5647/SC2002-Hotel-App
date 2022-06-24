package hotelReservation;

import java.util.ArrayList;

/**
 * Class that represents a hotel room
 */
public class Rooms {
	
	/**
     * Room number
     */
    private int number;
	/**
     * Room floor 
     */
    private int floor;
	/**
     * Room ID, i.e. "number-floor" 
     */
    private String ID;
	/**
     * Non-Weekend Rate of room
     */
    private double rate;
	/**
     * Weekend Rate of room 
     */
    private double weekendRate;
	/**
     * Boolean whether room is WiFI enabled
     */
    private boolean wifiEnabled;
	/**
     * Description of view of room 
     */
    private String facingDescription;
	/**
     * Boolean whether room has balcony
     */
    private boolean balcony;
	/**
     * Boolean whether room is smoking or non-smoking 
     */
    private boolean smoking;
	/**
     * Status of room (vacant, reserved, occupied or under maintenance)
     */
    private ROOMSTATUS status;
	/*
     * Type of Bed in the room (Single, Double, Queen or King) 
     */
    private BEDTYPE bedType;
	/**
     * Type of room (Single, Double, Deluxe, VIP or Suite)
     */
    private ROOMTYPE roomType;
	/**
	 * Array of all reservations for the room 
	 */
    private ArrayList<Reservation> reservations;

	/**
     * Constructor for room 
     */
    public Rooms(){
        number = 1;
        floor = 1;
        ID = "01-01";
        rate = 199;
        weekendRate = 249;
        wifiEnabled = true;
        facingDescription = "City view";
        balcony = false;
        smoking = false;
        status = ROOMSTATUS.VACANT;
        bedType = BEDTYPE.SINGLE;
        roomType = ROOMTYPE.SINGLE;
    }

	/**
     * Constructor for room 
     * @param no room number
     * @param f room floor 
     * @param id room ID
     * @param r Non-Weekend Rate
     * @param w Weekend Rate
     * @param wifi Boolean whether WiFi-Enabled
     * @param smoke Boolean whether smoking is allowed
     * @param b Boolean whether room has balcony 
     * @param facingV Description of view 
     * @param roomT Type of Room 
     * @param bed Type of Bed 
     * @param roomS Status of ROom 
     */
    public Rooms(int no, int f, String id, double r, double w,  boolean wifi, boolean smoke, boolean b, String facingV, ROOMTYPE roomT, BEDTYPE bed, ROOMSTATUS roomS){
        number = no;
        floor = f;
        ID = id;
        rate = r;
        weekendRate = w;
        wifiEnabled = wifi;
        facingDescription = facingV;
        balcony = b;
        smoking = smoke;
        status =roomS;
        bedType = bed;
        roomType = roomT;
        reservations = new ArrayList<Reservation>();
    }

	/**
     * @return room number 
     */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets room number 
	 * @param number to be set 
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return floor of room 
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * Sets floor of room
	 * @param floor to be set 
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * @return room ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Sets ID of room
	 * @param iD to be set 
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return rate of room 
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Sets Non-Weekend rate of room 
	 * @param rate to be set 
	 */
	public void setRate(double rate) {
		this.rate = rate;
	}

	/**
	 * @return weekend rate of room 
	 */
	public double getWeekendRate() {
		return weekendRate;
	}

	/**
	 * Sets weekend Rate 
	 * @param weekendRate to be set 
	 */
	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}

	/**
	 * @return whether room is WiFi enabled
	 */
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}

	/**
	 * @param wifiEnabled whether room is WiFi enabled
	 */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}

	/**
	 * @return description of room's view 
	 */
	public String getFacingDescription() {
		return facingDescription;
	}

	/**
	 * Sets description of room's view 
	 * @param facingDescription input of room's view 
	 */
	public void setFacingDescription(String facingDescription) {
		this.facingDescription = facingDescription;
	}

	/**
	 * @return whether room has balcony 
	 */
	public boolean isBalcony() {
		return balcony;
	}

	/**
	 * Sets Balcony 
	 * @param balcony sets balcony 
	 */
	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}

	/**
	 * @return whether room allows smoking 
	 */
	public boolean isSmoking() {
		return smoking;
	}

	/**
	 * Sets whether room allows smoking 
	 * @param smoking input whether room allows smoking 
	 */
	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	/**
	 * @return status of room 
	 */
	public ROOMSTATUS getStatus() {
		return status;
	}

	/**
	 * Sets status of room 
	 * @param status to be set 
	 */
	public void setStatus(ROOMSTATUS status) {
		this.status = status;
	}

	/**
	 * @return type of Bed 
	 */
	public BEDTYPE getBedType() {
		return bedType;
	}

	/**
	 * Set type of bed 
	 * @param bedType to be set 
	 */
	public void setBedType(BEDTYPE bedType) {
		this.bedType = bedType;
	}

	/**
	 * @return type of room 
	 */
	public ROOMTYPE getRoomType() {
		return roomType;
	}

	/**
	 * Sets type of room 
	 * @param roomType to be set 
	 */
	public void setRoomType(ROOMTYPE roomType) {
		this.roomType = roomType;
	}
	
	/*
	 * Adds reservation to array of reservations for the room 
	 */
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	/**
	 * Get reservation by reservation code
	 * @param reservationCode to be searched
	 * @return reservation object corresponding to the code 
	 */
	public Reservation getReservation(String reservationCode) {
		for(Reservation reservation : reservations) {
			if(reservation.getReservationCode().equals(reservationCode))
				return reservation;
		}
		return null;
	}
    
	/**
     * @return array of all reservations for the room 
     */
    public ArrayList<Reservation> getAllReservations(){
    	return reservations;
    }
    
	/**
     * @return all reservations where guests are currently checked-in 
     */
	public Reservation getCurrentReservation() {
		for(int i=0; i<reservations.size();i++){
			if(reservations.get(i).getReservationStatus() == RESERVATIONSTATUS.CHECKED_IN){
				return reservations.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Helper function
	 * Prints room attributes
	 */
	public void printString() { 
    
		System.out.printf("%-19s","Room ID:");
		System.out.println(this.getID());
		System.out.printf("%-19s","Rate:");
		System.out.println(this.getRate());
		System.out.printf("%-19s","Weekend Rate:");
		System.out.println(this.getWeekendRate());
		System.out.printf("%-19s","Can smoke?:");
		System.out.println(this.isSmoking());
		System.out.printf("%-19s","Got Wifi?:");
		System.out.println(this.isWifiEnabled());
		System.out.printf("%-19s","Got Balcony?:");
		System.out.println(this.isBalcony());
		System.out.printf("%-19s","Description:");
		System.out.println(this.getFacingDescription());
		System.out.printf("%-19s","Room Type:");
		System.out.println(this.getRoomType());
		System.out.printf("%-19s","Bed type:");
		System.out.println(this.getBedType());
		System.out.printf("%-19s","Room status:");
		System.out.println(this.getStatus() + "\n");
	} 

    
}