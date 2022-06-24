package hotelReservation;

/**
 * A class that contains the name, price and description of a dish.
 */
public class MenuItems {
	/**
	 * Name of the dish
	 */
    private String item;
	/**
	 * Price of the dish
	 */
    private double price;
	/**
	 * Description details of the dish
	 */
    private String details;

	/**
	 * Constructs and initialises a MenuController object.
	 * @param itemName Name of the dish
	 * @param itemPrice Price of the dish
	 * @param itemDetail Description of the dish
	 */
    public MenuItems(String itemName, double itemPrice, String itemDetail){
        item = itemName;
        price = itemPrice;
        details = itemDetail;
    }

	/**
	 * Getter that returns the name of the dish
	 * @return A string
	 */
	public String getItem() {
		return item;
	}

	/**
	 * Setter that updates the name of the dish
	 * @param item New name of the dish
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * Getter that returns the price of the dish
	 * @return A double
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter that updates the price of the dish
	 * @param price New price of the dish
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Getter that returns the discription of the dish
	 * @return A string
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Setter that updates the description details of the dish
	 * @param details New description of the dish
	 */
	public void setDetails(String details) {
		this.details = details;
	}
    

}
