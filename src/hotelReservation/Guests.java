package hotelReservation;
import java.util.ArrayList;

/**
 * A class that contains all the information needed for a guest
 */
public class Guests {
	
	// Attributes
	/**
	 * Name of the guest
	 */
	private String name;
	/**
	 * Address of the guest
	 */
	private String address;
	/**
	 * Nationality of the guest
	 */
	private String nationality;
	/**
	 * Phone number of the guest
	 */
	private long phoneNumber;
	/**
	 * Gender type of the guest: {MALE, FEMALE}
	 */
	private GENDERTYPE gender;
	/**
	 * Credit card of the guest
	 */
	private CreditCard card;
	/**
	 * Indetity of the guest
	 */
	private Identity identity;
	/**
	 * Array list of Reservations
	 */
	private ArrayList<Reservation> reservations;
	
	/**
	 * Constructs and initialises a Guests object
	 * @param name Name of the guest
	 * @param address Address of the guest
	 * @param nationality Nationality of the guest
	 * @param phoneNumber Phone number of the guest
	 * @param gender Gender type of the guest
	 */
	public Guests(String name, String address, String nationality, long phoneNumber, GENDERTYPE gender) {
		this.name = name;
		this.address = address;
		this.nationality = nationality;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		identity = new Identity();
		reservations = new ArrayList<Reservation>();
	}
	
	
	/**
	 * A function that updates the identity of the guest
	 * @param idNumber New id number
	 * @param identityType New id type
	 */
	public void updateIdentity(String idNumber, TYPEOFID identityType) {
		identity.setIdNumber(idNumber);
		identity.setIdentityType(identityType);
	}
	
	/**
	 * Getter that returns the name of the guest
	 * @return A string
	 */
	public String getName() {
		return name;
	}
	  
	/**
	 * Setter that updates the name of the guest
	 * @param name New name of the guest
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter that returns the address of the guest
	 * @return A string
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Setter that updates the address of the guest
	 * @param address New address of the guest
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Getter that returns the nationality of the guest
	 * @return A string
	 */
	public String getNationality() {
		return nationality;
	}
	
	/**
	 * Setter that updates the nationality of the guest
	 * @param nationality New nationality of the guest
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * Getter that returns the phone number of the guest
	 * @return A long
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * Setter that updates the phone number of the guest
	 * @param phoneNumber New phone number
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Getter that returns the gender type of the guest
	 * @return The gender type: {MALE, FEMALE}
	 */
	public GENDERTYPE getGender() {
		return gender;
	}
	
	/**
	 * Setter that updates the gender type of the guest
	 * @param gender New gender type: {MALE, FEMALE}
	 */
	public void setGender(GENDERTYPE gender) {
		this.gender = gender;
	}
	
	/**
	 * Getter that returns the credit card class of the guest
	 * @return CreditCard class
	 */
	public CreditCard getCard() {
		return card;
	}
	
	/**
	 * Setter that updates the credit card class of the guest
	 * @param card New CreditCard class
	 */ 
	public void setCard(CreditCard card) {
		this.card = card;
	}

	/**
	 * Getter that returns the identity of the guest
	 * @return An Identity class
	 */
	public Identity getIdentity() {
		return identity;
	}
	  
	/**
	 * Setter that updates the identity of the guest
	 * @param identity New identity of the guest
	 */
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	
	/**
	 * Adds a reservation into the ArrayList of Reservation
	 * @param reservation Reservation to be added
	 */
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	/**
	 * Getter that returns all reservations
	 * @return ArrayList of Reservation
	 */
	public ArrayList<Reservation> getAllReservations(){
		return reservations;
	}
	
	/**
	 * Remove an existing reservation
	 * @param reservation Reservation to be removed
	 */
	public void removeReservation(Reservation reservation) {
		reservations.remove(reservation);
	}
	
	public void printString() {
		 System.out.printf("%-19s","Name");
	     System.out.println(this.name);
	     System.out.printf("%-19s","Address");
	     System.out.println(this.address);    
	     System.out.printf("%-19s","Nationality");	     
	     System.out.println(this.nationality);	     
	     System.out.printf("%-19s","Phone Number");	     
	     System.out.println(this.phoneNumber);
	     System.out.printf("%-19s","Gender");  
	     switch(this.gender) {
	     case MALE:
	    	 System.out.println("MALE");  
	    	 break;
	     case FEMALE: 
	    	 System.out.println("FEMALE");
	     }
	     System.out.printf("%-19s","ID number");     
	     System.out.println(this.identity.getIdNumber());
	     System.out.printf("%-19s","ID type");     
	     System.out.println(this.identity.getIdentityType() + "\n");
	}
}
