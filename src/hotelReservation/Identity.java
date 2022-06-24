package hotelReservation;

/**
 * A class that contains the neccessary information neeeded for a person's identity
 */
public class Identity {
	
	/**
	 * Id number of the person
	 */
	private String idNumber;
	/**
	 * Identity type of the person: {PASSPORT, DRIVING_LICENSE, NONE};
	 */
	private TYPEOFID identityType;
	
	/**
	 * Default constructor that constructs and initialises an Identity object. 
	 */
	public Identity() {
		this.idNumber = "NULL";
		this.identityType = TYPEOFID.NONE;
		
	}
	
	/**
	 * Constructs and initialises an Identity object. 
	 * @param idNumber Id number of the person
	 * @param identity Identity type of the person
	 */
	public Identity(String idNumber, TYPEOFID identity) {
		this.idNumber = idNumber;
		this.identityType = identity;
	}
	
	/**
	 * Getter that returns the id number of the person 
	 * @return A string
	 */
	public String getIdNumber() {
		return this.idNumber;
	}
	
	/**
	 * Setter that returns the id number of the person
	 * @param idNumber New id number of the person
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	/**
	 * Getter that returns the type of identity 
	 * @return The type of id: {PASSPORT, DRIVING_LICENSE, NONE};
	 */
	public TYPEOFID getIdentityType() {
		return this.identityType;
	}
	
	/**
	 * Setter that updates the new type of id
	 * @param identity New identity of the person
	 */
	public void setIdentityType(TYPEOFID identity) {
		this.identityType = identity;
	}
}
