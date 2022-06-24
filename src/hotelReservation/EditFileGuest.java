package hotelReservation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads and edit in the data from Guest.txt and stores it into an ArrayList of Guests
 */
public class EditFileGuest implements EditFile{

	static final String tempName = "temp.txt";
    private static Scanner x;
    private Hotel hotel;
    private Guests guest;
    
    public EditFileGuest(Hotel hotel) {
    	this.hotel = hotel;
    	this.guest = null;
    }
    
    public void setGuest(Guests guest) {
    	this.guest = guest;
    }
    
    /**
     * Reads in data from the Guest.txt and stores it into the ArrayList of Guests
     * @param guest Guest ArrayList
     * @param FilePath File path of the Guest.txt
     */
    public void readFile(String FilePath){
        
    	ArrayList<Guests> guest = hotel.getAllGuests();
        String name;
        String address; 
        String nationality;
        long phoneNumber; 
        GENDERTYPE gender;

        String cardNumber;
        String nameOfOwner;
	    String expireDate;
	    String securityCode;
	    TYPEOFCARD cardType;

        String idNumber;
        TYPEOFID identityType;
        try{
           
            x = new Scanner(new File(FilePath));
            x.useDelimiter("[,\r\n]");
            while (x.hasNext()){
                name = x.next();
                address = x.next(); 
                nationality = x.next();
                phoneNumber = x.nextLong();
                
                String GT = x.next();
                switch (GT){
                    case "MALE":
                        gender = GENDERTYPE.MALE;
                        break;
                    case "FEMALE":
                        gender = GENDERTYPE.FEMALE;
                        break;
                    default:
                        gender = GENDERTYPE.MALE;
                }
                cardNumber = x.next();
                nameOfOwner = x.next();
                expireDate = x.next();
                securityCode = x.next();
                
                String ct = x.next();
                switch (ct){
                    case "PAYPAL":
                        cardType = TYPEOFCARD.PAYPAL;
                        break;
                    case "VISA":
                        cardType = TYPEOFCARD.VISA;
                        break;
                    case "MASTER":
                        cardType = TYPEOFCARD.MASTER;
                        break;
                    default:
                        cardType = TYPEOFCARD.PAYPAL;
                }

                idNumber = x.next();
                String ti = x.next();
                switch (ti){
                    case "PASSPORT":
                        identityType = TYPEOFID.PASSPORT;
                        break;
                    case "DRIVING_LICENSE":
                        identityType = TYPEOFID.DRIVING_LICENSE;
                        break;
                    case "NONE":
                        identityType = TYPEOFID.NONE;
                        break;
                    default:
                        identityType = TYPEOFID.NONE;
                }

                
                Guests temp = new Guests(name,address, nationality, phoneNumber,gender);
                CreditCard cardtemp = new CreditCard(cardNumber,nameOfOwner,expireDate,securityCode,cardType);
                temp.setCard(cardtemp);
                temp.updateIdentity(idNumber, identityType);
                guest.add(temp);
                if(x.hasNext()) x.nextLine();
                else break;
                
            }
            x.close();
        }catch (Exception e){
            System.out.println("Exit");
        }
    }

    /**
     * Edits the data in the Guest.txt
     * @param name New name of guest
     * @param address New address of guest
     * @param nationality New nationality of guest
     * @param phoneNumber New phone numnber of guest
     * @param gender New gender of guest
     * @param cardNumber New card number of guest
     * @param nameOfOwner New name of the owner of credit card
     * @param expireDate New expiry date of the credit card
     * @param securityCode New CVV of the credit card
     * @param cardType New card type of the credit card
     * @param idNumber New id number of the guest
     * @param identityType New id type of the guest
     * @param FilePath File path of the Guest.txt
     */
    public void edit(String FilePath){ 
        String namenew;
        String addressnew; 
        String nationalitynew;
        long phoneNumbernew; 
        String gendernew;

        String cardNumbernew;
        String nameOfOwnernew;
	    String expireDatenew;
	    String securityCodenew;
	    String cardTypenew;

        String idNumbernew;
        String identityTypenew;
        
        String tempFile = tempName; 
        File oldFile = new File(FilePath); 
        File newFile = new File(tempFile); 

        String genderS;
        String cardS;
        String identityS;
        switch (guest.getGender()){ 
            case MALE: 
                genderS = "MALE"; 
                break; 
            case FEMALE: 
                genderS = "FEMALE";
                break; 
            default: 
                genderS = "MALE"; 
        } 
        switch (guest.getCard().getCardType()){ 
            case PAYPAL:
                cardS = "PAYPAL";
                break;
            case VISA:
                cardS = "VISA";
                break;
            case MASTER:
                cardS = "MASTER";
                break;
            default:
                cardS = "PAYPAL";
        } 
 
        switch (guest.getIdentity().getIdentityType()){ 
            case PASSPORT:
                identityS = "PASSPORT";
                break;
            case DRIVING_LICENSE:
                identityS = "DRIVING_LICENSE";
                break;
            case NONE:
                identityS = "NONE";
                break;
            default:
                identityS = "NONE";
        } 
        try{ 
            FileWriter fw = new FileWriter(tempFile,true);//append not override completely 
            BufferedWriter bw = new BufferedWriter(fw); 
            PrintWriter pw = new PrintWriter(bw); 
            x = new Scanner(oldFile); 
            x.useDelimiter("[,\r\n]"); 
             
            while (x.hasNext()){ 
                namenew = x.next(); 
                addressnew = x.next();  
                nationalitynew = x.next();
                phoneNumbernew = x.nextLong(); 
                gendernew = x.next();
                cardNumbernew= x.next();
                nameOfOwnernew= x.next();
	            expireDatenew = x.next();
	            securityCodenew = x.next();
	            cardTypenew = x.next();

                idNumbernew= x.next();
                identityTypenew= x.next();
                if (idNumbernew.equals(guest.getIdentity().getIdNumber())){ 
                    pw.println(guest.getName() + "," + guest.getAddress() + "," + guest.getNationality() + "," +
                    guest.getPhoneNumber() + "," + genderS + "," + guest.getCard().getCardNumber() + "," + 
                    guest.getCard().getNameOfOwner() + "," + guest.getCard().getExpireDate()  + "," + guest.getCard().getSecurityCode()+ "," +
                    cardS + "," + guest.getIdentity().getIdNumber() + "," + identityS); 
                }// prints in the tempfile for this reservation code if not j copy in 
                else 
                { 
                    pw.println(namenew + "," +addressnew + "," + nationalitynew + "," + phoneNumbernew + "," + gendernew + "," + cardNumbernew + "," + nameOfOwnernew + "," + expireDatenew+ "," +securityCodenew+ "," + cardTypenew+ "," + idNumbernew + "," + identityTypenew); 
                } 
                if(x.hasNext()) x.nextLine(); 
                else break;                                                                        
            } 
            x.close(); 
            pw.flush(); 
            pw.close(); 
            oldFile.delete(); 
            File dump = new File(FilePath); 
            newFile.renameTo(dump);//renaming temp to whatever old filename was 
        }catch (Exception e){ 
            System.out.println("Exit"); 
        }  
    }

    /**
     * Deletes specific guest from the Guest.txt
     * @param idNumber Id number of guest to be deleted
     * @param FilePath File path of Guest.txt
     */
    public void deleteRecord(String FilePath){ 
        String namenew;
        String addressnew; 
        String nationalitynew;
        long phoneNumbernew; 
        String gendernew;

        String cardNumbernew;
        String nameOfOwnernew;
	    String expireDatenew;
	    String securityCodenew;
	    String cardTypenew;

        String idNumbernew;
        String identityTypenew;
        
        String tempFile = tempName; 
        File oldFile = new File(FilePath); 
        File newFile = new File(tempFile); 

        try{ 
            FileWriter fw = new FileWriter(tempFile,true);//append not override completely 
            BufferedWriter bw = new BufferedWriter(fw); 
            PrintWriter pw = new PrintWriter(bw); 
            x = new Scanner(oldFile); 
            x.useDelimiter("[,\r\n]"); 
             
            while (x.hasNext()){ 
                namenew = x.next(); 
                addressnew = x.next();  
                nationalitynew = x.next();
                phoneNumbernew = x.nextLong(); 
                gendernew = x.next();
                cardNumbernew= x.next();
                nameOfOwnernew= x.next();
	            expireDatenew = x.next();
	            securityCodenew = x.next();
	            cardTypenew = x.next();

                idNumbernew= x.next();
                identityTypenew= x.next();
                if (idNumbernew.equals(guest.getIdentity().getIdNumber())){ 
                    continue;
                }// prints in the tempfile for this reservation code if not j copy in 
                else 
                { 
                    pw.println(namenew + "," +addressnew + "," + nationalitynew + "," + phoneNumbernew + "," + gendernew + "," + cardNumbernew + "," + nameOfOwnernew + "," + expireDatenew+ "," +securityCodenew+ "," + cardTypenew+ "," + idNumbernew + "," + identityTypenew); 
                } 
                if(x.hasNext()) x.nextLine(); 
                else break;                                                                        
            } 
            x.close(); 
            pw.flush(); 
            pw.close(); 
            oldFile.delete(); 
            File dump = new File(FilePath); 
            newFile.renameTo(dump);//renaming temp to whatever old filename was 
        }catch (Exception e){ 
            System.out.println("Exit"); 
        }    
    }

    /**
     * Appends a new guest into the Guest.txt database
     * @param name Name of guest
     * @param address Adress of guest
     * @param nationality Nationality of guest
     * @param phoneNumber Phone numnber of guest
     * @param gender Gender of guest
     * @param cardNumber Card number of guest
     * @param nameOfOwner Name of the owner of credit card
     * @param expireDate Expiry date of the credit card
     * @param securityCode CVV of the credit card
     * @param cardType Card type of the credit card
     * @param idNumber Id number of the guest
     * @param identityType Id type of the guest
     * @param FilePath File path of the Guest.txt
     */
    public void addRecord(String FilePath){
        String genderS;
        String cardS;
        String identityS;
        switch (guest.getGender()){ 
            case MALE: 
                genderS = "MALE"; 
                break; 
            case FEMALE: 
                genderS = "FEMALE";
                break; 
            default: 
                genderS = "MALE"; 
        } 
        switch (guest.getCard().getCardType()){ 
            case PAYPAL:
                cardS = "PAYPAL";
                break;
            case VISA:
                cardS = "VISA";
                break;
            case MASTER:
                cardS = "MASTER";
                break;
            default:
                cardS = "PAYPAL";
        } 
 
        switch (guest.getIdentity().getIdentityType()){ 
            case PASSPORT:
                identityS = "PASSPORT";
                break;
            case DRIVING_LICENSE:
                identityS = "DRIVING_LICENSE";
                break;
            case NONE:
                identityS = "NONE";
                break;
            default:
                identityS = "NONE";
        } 
        File oldFile = new File(FilePath);
        
        try{
            FileWriter fw = new FileWriter(oldFile,true);//append not override completely
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(guest.getName() + "," + guest.getAddress() + "," + guest.getNationality() + "," +
                    guest.getPhoneNumber() + "," + genderS + "," + guest.getCard().getCardNumber() + "," + 
                    guest.getCard().getNameOfOwner() + "," + guest.getCard().getExpireDate()  + "," + guest.getCard().getSecurityCode()+ "," +
                    cardS + "," + guest.getIdentity().getIdNumber() + "," + identityS); 
            pw.flush();
            pw.close();
        }catch (Exception e){
            System.out.println("Exit");
        }
    }
}