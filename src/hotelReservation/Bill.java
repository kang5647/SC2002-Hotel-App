package hotelReservation;
import java.time.LocalDateTime;

/**
 * The class Bill contains the amount that the customer has to pay for the room, room service and tax.
 */
public class Bill {
    
	/**
	 * Cost of the room
	 */
	private double roomFee; 
	/**
	 * Cost of the room service
	 */
    private double roomServiceFee; 
	/**
	 * Tax
	 */
    private double tax; 
	/**
	 * Amount due for tax
	 */
	private double taxAmount; 
	/**
	 * Total amount due including tax
	 */
    private double totalPrice; //tax inclusive
	/**
	 * Number of weekdays stayed
	 */
    private int numWeekdays; 
	/**
	 * Number of weekends stayed
	 */
    private int numWeekends; 
	/**
	 * Discount rate
	 */
    private double discount;

	/**
	 * Constructs and initialises a Bill object
	 * @param reservation Reservation object
	 */
    public Bill(Reservation reservation) { //constructor 

        double weekdayRate = reservation.getRoom().getRate();
        double weekendRate = reservation.getRoom().getWeekendRate();
        
        numWeekdays = 0; 
        numWeekends = 0; 
        
        LocalDateTime temp = reservation.getDateOfCheckIn(); 

        while (!temp.isAfter(reservation.getDateOfCheckOut())) {
            if(temp.getDayOfWeek().name() == "SATURDAY" || temp.getDayOfWeek().name() == "SUNDAY" ) 
                numWeekends+=1;
            else
                numWeekdays +=1; 

            temp = temp.plusDays(1);
        }   

        // roomFee = reservation.getRoom().getRate()
        roomFee = numWeekdays*weekdayRate + numWeekends*weekendRate; 
        
        discount = roomFee * (reservation.getDiscount()/100);
        roomFee = roomFee - discount;
        
        for(int i = 0; i<reservation.getOrderList().size();i++){
            roomServiceFee += reservation.getOrderList().get(i).getTotalAmount();
        }

        tax = 0.07; 
		taxAmount = 0.07 * (roomFee + roomServiceFee);
        totalPrice = (roomFee + roomServiceFee) * (1 + tax); 
    }

	/**
	 * Constructs and initialises a Bill object
	 * @param roomFee Cost for the room
	 * @param roomServiceFee Cost for the room service
	 */
    public Bill(double roomFee, double roomServiceFee) { //constructor 
        this.roomFee = roomFee; 
        this.roomServiceFee = roomServiceFee; 
        totalPrice = (roomFee + roomServiceFee) * (1 + tax); 
    }

    /**
	 * Getter that returns the fee of the room
	 * @return A double
	 */
    public double getRoomFee() {
  		return roomFee;
  	}

	/**
	 * Setter that updates the fee of the room
	 * @param roomFee New fee of the room
	 */
  	public void setRoomFee(double roomFee) {
  		this.roomFee = roomFee;
  	}

	/**
	 * Getter that returns the the fee of the room service
	 * @return A double
	 */
  	public double getRoomServiceFee() {
  		return roomServiceFee;
  	}

	/**
	 * Setter that updates the fee of the room service
	 * @param roomServiceFee New room service fee
	 */
  	public void setRoomServiceFee(double roomServiceFee) {
  		this.roomServiceFee = roomServiceFee;
  	}

	/**
	 * Getter that returns the tax rate
	 * @return A double
	 */
  	public double getTax() {
  		return tax;
  	}

	/**
	 * Setter that updates the tax rate
	 * @param tax New tax rate
	 */
  	public void setTax(double tax) {
  		this.tax = tax;
  	}

	/**
	 * Getter that returns the total price that customer has to pay
	 * @return A double
	 */
  	public double getTotalPrice() {
  		return totalPrice;
  	}

	/**
	 * Setter that updates the total price that customer has to pay
	 * @param totalPrice New total price
	 */
  	public void setTotalPrice(double totalPrice) {
  		this.totalPrice = totalPrice;
  	}

	/**
	 * Getter that returns the number of weekdays that the customer stayed
	 * @return An int
	 */
  	public int getNumWeekdays() {
  		return numWeekdays;
  	}

	/**
	 * Setter that updates the number of weekdays that the customer stayed
	 * @param numWeekdays New number of weekdays stayed
	 */
  	public void setNumWeekdays(int numWeekdays) {
  		this.numWeekdays = numWeekdays;
  	}

	/**
	 * Getter that returns the number of weekends that the customer stayed
	 * @return An int
	 */
  	public int getNumWeekends() {
  		return numWeekends;
  	}

	/**
	 * Setter that updates the number of weekedns that the customr stayed
	 * @param numWeekends New number of weekends stayed
	 */
  	public void setNumWeekends(int numWeekends) {
  		this.numWeekends = numWeekends;
  	}

	/**
	 * Getter that returns the total tax amount that customer has to pay
	 * @return A double
	 */
	public double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * Getter that returns the discount rate
	 * @return A double
	 */
	public double getDiscount() {
		return discount;
	}
	// public void setTaxAmount(double taxAmount) {
	// 	this.taxAmount = taxAmount;
	// }
}