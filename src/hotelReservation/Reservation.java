package hotelReservation;
import java.lang.String;
import java.util.ArrayList;
import java.time.*; 

public class Reservation implements Comparable<Reservation>{ 
 
    /**
     * The reservation code.
     */
    private String reservationCode;
    /**
    * Date of check-in for the reservation.
    */ 
    private LocalDateTime dateOfCheckIn; 
    /**
     * Date of check-out for the reservation.
     */
    private LocalDateTime dateOfCheckOut; 
    /**
     * Number of adults staying.
     */
    private int numberOfAdults; 
    /**
     * Number of children staying.
     */
    private int numberOfChildren; 
    /**
     * The status of the reservation, be it confirmed, in wait list, guest checked-in or expired.
     */
    private RESERVATIONSTATUS reservationStatus; 
    /**
     * Discount for the reservation.
     */
    private double discount; 
    /**
     * Detailed bill of the reservation
     */
    private Bill bill; 
    /**
     * The guest that booked the reservation.
     */
    private Guests guest; 
    /**
     * All the room service orders made during the stay
     */
    private ArrayList<RoomServiceOrder> orderList; 
    /**
     * The room reserved for the reservation.
     */
    private Rooms room; 
    
    /**
     * Creates a new reservation with the details.
     * @param reservationCode This reservation's reservation code.
     * @param checkIn  Date of check-in for this reservation.
     * @param checkOut  Date of check-out for this reservation.
     * @param numberOfAdults  Number of adults staying for this reservation.
     * @param numberOfChildren  Number of children staying for this reservation.
     * @param reservationStatus2 Status of this reservation.
     * @param discount Discount for this reservation.
     */
    public Reservation(String reservationCode, LocalDateTime checkIn, LocalDateTime checkOut, 
            int numberOfAdults, int numberOfChildren, RESERVATIONSTATUS reservationStatus2, 
            double discount)
    { 
        this.reservationCode = reservationCode; 
        this.dateOfCheckIn = checkIn; 
        this.dateOfCheckOut = checkOut; 
        this.numberOfAdults = numberOfAdults; 
        this.numberOfChildren = numberOfChildren; 
        this.reservationStatus = reservationStatus2; 
        this.discount = discount; 
        this.orderList = new ArrayList<RoomServiceOrder>();

    } 
     
    /**
     * @return room object tied to reservation
     */
    public Rooms getRoom() { 
        return this.room; 
    } 
    
    /**
     * Sets room for reservation
     * @param room to be set 
     */
    public void setRoom(Rooms room) { 
        this.room = room; 
    } 
    
    /**
     * @return guest who booked reservation
     */
    public Guests getGuest() { 
        return this.guest; 
    } 
    
    /**
     * Sets guest who booked the reservation
     * @param guest who booked the reservation
     */
    public void addGuest(Guests guest) { 
        this.guest = guest; 
    } 
    
   /* public Bill getBill(){ 
        return this.bill 
    } 
    
    public void setBill(Bill bill){ 
    this.bill = bill; 
    } 
    
    public void addRoomServiceOrder(RoomServiceOrder order) { 
    orderlist.add(order); 
    } 
    
    */

    /**
     * @return array of Room Service Orders tied to reservation
     */
    public ArrayList<RoomServiceOrder> getOrderList(){ 
        return orderList; 
    } 
    
    /**
     * @return reservation code 
     */
    public String getReservationCode() { 
        return this.reservationCode; 
    } 
    
    /**
     * Sets reservation code 
     * @param reservationCode to be set 
     */
    public void setReservationCode(String reservationCode) { 
        this.reservationCode = reservationCode; 
    } 
    
    /**
     * @return date of check-in
     */
    public LocalDateTime getDateOfCheckIn() { 
        return this.dateOfCheckIn; 
    } 
    
    /**
     * Sets date of check-in
     * @param dateOfCheckIn date of Check-In
     */
    public void setDateOfCheckIn(LocalDateTime dateOfCheckIn) { 
        this.dateOfCheckIn = dateOfCheckIn; 
    } 
    
    /**
     * @return date of check-out
     */
    public LocalDateTime getDateOfCheckOut() { 
    return this.dateOfCheckOut; 
    } 
    
    /**
     * Sets date of check-out 
     * @param dateOfCheckOut Date of Check-Out 
     */
    public void setDateOfCheckOut(LocalDateTime dateOfCheckOut) { 
    this.dateOfCheckOut = dateOfCheckOut; 
    } 
    
    /**
     * @return number of adults
     */
    public int getNumberOfAdults() { 
    return this.numberOfAdults; 
    } 
    
    /**
     * Sets number of adults
     * @param numberOfAdults Number of Adults
     */
    public void setNumberOfAdults(int numberOfAdults) { 
    this.numberOfAdults = numberOfAdults; 
    } 
    
    /**
     * @return number of children
     */
    public int getNumberOfChildren() { 
    return this.numberOfChildren; 
    } 
    
    /**
     * Sets number of children
     * @param numberOfChildren Number of Children
     */
    public void setNumberOfChildren(int numberOfChildren) { 
    this.numberOfChildren = numberOfChildren; 
    } 
    
    /**
     * @return status of reservation
     */
    public RESERVATIONSTATUS getReservationStatus() { 
    return this.reservationStatus;  
    } 
    
    /**
     * Sets reservation status
     * @param rs Status of reservation
     */
    public void setReservationStatus(RESERVATIONSTATUS rs) { 
    this.reservationStatus = rs; 
    } 
    
    /**
     * @return discount 
     */
    public double getDiscount() { 
    return this.discount; 
    } 
    
    /**
     * Sets discount rate
     * @param discount to be set 
     */
    public void setDiscount(double discount) { 
    this.discount = discount; 
    } 
    
    /**
     * Prints out all the details of this reservation as one string
     */
    public void printString() { 
    
        System.out.printf("%-19s","ReservationCode");
        System.out.println(this.reservationCode);
        System.out.printf("%-19s","DateOfCheckIn");
        System.out.println(this.dateOfCheckIn);
        System.out.printf("%-19s","DateOfCheckOut");
        System.out.println(this.dateOfCheckOut);
        System.out.printf("%-19s","NumberOfAdults");
        System.out.println(this.numberOfAdults);
        System.out.printf("%-19s","NumberOfChildren");
        System.out.println(this.numberOfChildren);
        System.out.printf("%-19s","ReservationStatus");
        System.out.println(this.reservationStatus + "\n");
        
    } 

    /**
     * Adds a new room service order to this reservation.
     * @param RSO A room service order.
     */
    public void addRSO(RoomServiceOrder RSO){
        RSO.setReservationCode(reservationCode);
        orderList.add(RSO);
    }
    
    /**
     * Compare this reservation to the reservation passed in parameter in terms of their check-in dates.
     * @param r A reservation.
     */
    @Override 
    public int compareTo(Reservation r) {
    	return getDateOfCheckIn().compareTo(r.getDateOfCheckIn());
    }
    
    
}