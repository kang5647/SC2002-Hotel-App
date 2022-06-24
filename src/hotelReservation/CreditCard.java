package hotelReservation;

/**
 * CreditCard class which contains all the necessary information needed to validate a credit card.
 */
public class CreditCard {

    /**
     * Credit card number
     */
    private String cardNumber;
    /**
     * Name of the owner of the credit card 
     */
    private String nameOfOwner;
    /**
     * Expriy date of credit card
     */
	private String expireDate;
    /**
     * CVV
     */
	private String securityCode;
    /**
     * Type of cerdit card, can be PAYPAL, VISA, MASTER
     */
	private TYPEOFCARD cardType;
    
    /**
     * Constructs and initialises a CreditCard object
     * @param cardNumber Credit card number
     * @param nameOfOwner Name of the owner of the credit card 
     * @param expireDate Expriy date of credit card
     * @param securityCode CVV
     * @param cardType Type of credit card
     */
    public CreditCard(String cardNumber, String nameOfOwner,String expireDate, String securityCode, TYPEOFCARD cardType) {
        this.cardNumber = cardNumber;
        this.nameOfOwner=  nameOfOwner;
        this.expireDate = expireDate;
        this.securityCode = securityCode;
        this.cardType = cardType;
    }
    
    /**
     * Getter that returns the credit card number
     * @return A string
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Setter that updates the credit card number
     * @param cardNumber New credit card number
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Getter that returns the name of the credit card owner
     * @return A string
     */
    public String getNameOfOwner() {
        return nameOfOwner;
    }

    /**
     * Setter that updates the name of the credit card owner
     * @param nameOfOwner New name of the credit card owner
     */
    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }
    
    /**
     * Getter that returns the expiry date of the credit card
     * @return A string
     */
    public String getExpireDate() {
        return expireDate;
    }

    /**
     * Setter that updates the expiry date of the credit card
     * @param expireDate New expiry date of the credit card
     */
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * Getter that returns the CVV of the credit card
     * @return A string
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * Setter that updates the CVV of the credit card
     * @param securityCode New CVV
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * Getter that returns the type of credit card
     * @return PAYPAL, VISA, MASTER
     */
    public TYPEOFCARD getCardType() {
        return cardType;
    }

    /**
     * Setter that updates the type of credit card
     * @param cardType New credit card type
     */
    public void setCardType(TYPEOFCARD cardType) {
        this.cardType = cardType;
    }
}
