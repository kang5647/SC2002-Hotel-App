package hotelReservation;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class that contains the order requested from the guest
 */
public class RoomServiceOrder {
    

	/**
     * Reservation Code of reservation that made the Room Service order
     */
    private String reservationCode; 
	/**
     * ID of the Room Service Order
     */
    private String orderID; 
	/**
     * Total amount of the order
     */
    private double totalAmount; 
	/**
     * Status of order (confirmed, preparing or delivered) 
     */
    private ORDERSTATUS orderStatus; 
	/**
     * Remarks/ Special requests 
     */
    private String remarks; 
	/**
     * Date that order was made 
     */
    private LocalDateTime dateTime;
	/**
     * Array of all the chosen menu items
     */ 
    private ArrayList<MenuItems> chosenMenuItems;
    

	/**
     * Default constructor
     */
    public RoomServiceOrder() {
        reservationCode = orderID = remarks = null; 
        totalAmount = 0; 
        orderStatus = ORDERSTATUS.NONE;
        dateTime = LocalDateTime.now(); //set to current time
        chosenMenuItems = new ArrayList<MenuItems>();
    }
    
	/**
	 * Constructor with attributes 
	 * @param orderID order ID
	 * @param totalAmount total cost of RSO 
	 * @param status of RSO 
	 * @param remarks special requests
	 * @param date that RSO was made
	 * @param items Menu Items chosen 
	 */
    public RoomServiceOrder(String orderID,double totalAmount, ORDERSTATUS status, String remarks, LocalDateTime date, ArrayList<MenuItems> items ) {
    	this.orderID = orderID;
    	this.totalAmount = totalAmount;
    	this.orderStatus = status; 
    	this.remarks = remarks;
    	this.dateTime = date;
    	this.chosenMenuItems = items;
    }

	/**
     * Add a menu item to the array of chosen menu items
     * @param item chosen item 
     */
    public void addItemOrdered(MenuItems item) {
        chosenMenuItems.add(item);
    }

	/**
     * @return size of array of chosen menu items
     */
    public int size() {
        return chosenMenuItems.size(); 
    }

	/**
     * @return ORDER ID 
     */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * Sets Order ID
	 * @param orderID of Room Service order
	 */
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	/**
	 * Gets date and time that Order was made
	 * @return date AND time 
	 */
    public LocalDateTime getDateTime() {
        return dateTime; 
    }

	/**
     * @return total cost 
     */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Sets total cost 
	 * @param totalAmount of Room Service Order
	 */
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return order status
	 */
	public ORDERSTATUS getOrderStatus() {
		return orderStatus;
	}

	/**
	 * sets status of Order 
	 * @param orderStatus of Order
	 */
	public void setOrderStatus(ORDERSTATUS orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * @return remarks for the order
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Sets remarks 
	 * @param remarks input 
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return array of chosen menu items
	 */
	public ArrayList<MenuItems> getChosenMenuItems() {
		return chosenMenuItems;
	}

	/**
	 * Sets array of chosen menu items
	 * @param chosenMenuItems array of chosen menu items
	 */
	public void setChosenMenuItems(ArrayList<MenuItems> chosenMenuItems) {
		this.chosenMenuItems = chosenMenuItems;
	}

	/**
	 * Sets reservation code 
	 * @param reservationC reservation code
	 */
    public void setReservationCode(String reservationC){
        this.reservationCode = reservationC;
    }

	/**
     * Adds cost of all chosen menu items into total amount
     */
	public void updateTotal(){
		double total = 0;
		for(int i =0; i<chosenMenuItems.size();i++){
			total+=chosenMenuItems.get(i).getPrice();
		}
		totalAmount = total;
	}
    
    
}