package hotelReservation;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface that allows the user to add, edit and search for guests
 */
public class GuestController {
	
	private Hotel hotel; 
	
	private final Scanner sc = new Scanner(System.in);
    private EditFileGuest eg = new EditFileGuest(hotel);
    /**
     * Hotel that contains the guests
     */
	
    /**
     * Constructs and initialises the GuestController object
     * @param hotel Hotel that contains the guests 
     */
	public GuestController(Hotel hotel) {
		this.hotel = hotel; 
	}
    

    /**
     * Main interface that allows the user to add, edit and search for guests
     */
	public void guestControl() {

        while(true){
            System.out.println("\nPlease select one operation to proceed\n"
                                + "1. Insert New Guest \n"
                                + "2. Update Current Guest Details \n"
                                + "3. Search Guest's Details \n"
                                + "4. Print Guest's Reservations \n"
                                + "5. Print all Guests'details \n"
                                + "6. Return to previous menu \n");
            
            int option = sc.nextInt();
            sc.nextLine();

            if(option < 1 || option > 6){
                System.out.println("Invalid option.");
                continue;
            }

            switch(option) {
            case 1: 
                newGuest();
                break;
            case 2:
                updateGuests();
                break;
            case 3: 
                searchGuestDetails();
                break;
            case 4: 
            	printGuestReservation();
            	break;
            case 5:
               printAllGuests();
            case 6:
            	return;
            }
        }
    }
	

    /**
     * A function that allows users to add a guest 
     * @return A Guests class of the new guest
     */
	public Guests newGuest() {

        //Search if Existing Guest
        System.out.println("Enter your name:"); 
        String name = sc.nextLine(); //any part of name 
        
        //array list of guests
        ArrayList<Guests> guests = hotel.searchGuests(name); 

        if (guests != null && guests.size() != 0) {
            System.out.println("Existing records found: \n");

            System.out.printf("%-4s%20s%20s%n","No.","Name","ID");

            for (int i = 0; i < guests.size(); i++) {
                System.out.printf("%-4d%20s%20s%n",(i+1),guests.get(i).getName(),guests.get(i).getIdentity().getIdNumber());
            }

            System.out.println("Select existing guest if applicable (else input 0):");
            int option = sc.nextInt(); 
            sc.nextLine();

            if (option != 0) {
                System.out.println("Previous record is used for your check-in.\n");
                return guests.get(option -1);
                // System.out.println("Do you want to update guest details? (Y/N)"); 
                // String choice = sc.nextLine();
                // if(choice.compareTo("Y") == 0) updateGuests();
                // return oldGuest; 
            }
        }

        //If NOT existing guest, create new guest:
        System.out.println("Please enter your details:");

        //attributes 
        //System.out.print("Name: ");
        String fullName = name; 
        System.out.print("Address:");
        String address = sc.nextLine();
        System.out.print("Nationality: ");
        String nationality = sc.nextLine(); 
        System.out.print("Phone Number: ");
        long phoneNumber = sc.nextLong(); 
        sc.nextLine();
        System.out.print("Gender: (MALE/ FEMALE):");
        String inputGender = sc.nextLine(); 
        GENDERTYPE gender; 
        if (inputGender.compareTo("MALE") == 0) {
            gender = GENDERTYPE.MALE;}
        else {
            gender = GENDERTYPE.FEMALE;}

        //Identity 
        System.out.print("Type of ID (PASSPORT/DRIVING_LICENSE):");
        String inputID = sc.nextLine(); 
        TYPEOFID identityType;
        if (inputID.compareTo("PASSPORT") == 0) {
            identityType = TYPEOFID.PASSPORT; }
        else {identityType = TYPEOFID.DRIVING_LICENSE;}
        System.out.print("ID Number: ");
        String idNumber = sc.nextLine();

        //Credit Card
        System.out.print("Card Type(PAYPAL/VISA/MASTER):");
        String inputCardType = sc.nextLine(); 
        TYPEOFCARD cardType;
        if(inputCardType.compareTo("PAYPAL")==0) {
            cardType = TYPEOFCARD.PAYPAL; 
        }
        else if(inputCardType.compareTo("VISA")==0) {
            cardType = TYPEOFCARD.VISA;}
        else {
            cardType = TYPEOFCARD.MASTER;}
        System.out.print("Card Number: ");
        String cardNumber = sc.nextLine(); 
        System.out.print("Expire Date: ");
        String expireDate = sc.nextLine(); 
        System.out.print("Security Code: ");
        String securityCode = sc.nextLine(); 

        //Set everything
        Guests newGuest = new Guests(fullName, address, nationality, phoneNumber, gender);
        CreditCard newcard = new CreditCard(cardNumber, fullName, expireDate, securityCode, cardType);
        newGuest.setCard(newcard);
        newGuest.updateIdentity(idNumber, identityType);
        eg.setGuest(newGuest);
        eg.addRecord("Guest.txt");
        hotel.addGuests(newGuest);
        System.out.println("Guest successfully added to the system.\n");
        newGuest.printString();
        return newGuest; 
    }

    /**
     * A function that prints out the guest details
     * @param oldGuest Guest whose details are to be printed
     */
    public void printGuest(Guests oldGuest) {

        System.out.println("\n__________________________________________\n");

        System.out.printf("1. %-14s","Name:");
        System.out.println(oldGuest.getName());
        System.out.printf("2. %-14s","Address:");
        System.out.println(oldGuest.getAddress());
        System.out.printf("3. %-14s","Nationality:");
        System.out.println(oldGuest.getNationality());
        System.out.printf("3. %-14s","Gender:");
        System.out.println(oldGuest.getGender());
        System.out.printf("4. %-14s","Phone Number:");
        System.out.println(oldGuest.getPhoneNumber());
        System.out.printf("5. %-14s","Credit Card:");
        System.out.println(oldGuest.getCard().getCardType() + "    " + oldGuest.getCard().getCardNumber());
        System.out.printf("6. %-14s","Identity:");
        System.out.println(oldGuest.getIdentity().getIdentityType() + "    " + oldGuest.getIdentity().getIdNumber());
        System.out.println("7. Return to previous menu.\n");

        System.out.println("__________________________________________\n");


    }
	
    /**
     * A function that updates the details of an existing guest
     */
    public void updateGuests() {

       System.out.println("Enter ID Number:"); 
       String documentID = sc.nextLine();

       Guests guest = hotel.searchGuestsbyID(documentID);

       if (guest == null) {
            System.out.println("Guest not found");
            return; 
       }

       System.out.println("Guest found.");

       while (true) {

        printGuest(guest);
        System.out.println("Enter number to select detail to update:");
        int choice = sc.nextInt(); 
        sc.nextLine();

        if (choice < 1 || choice > 7){
            System.out.println("Invalid choice.");
            continue;
        }
 
        switch(choice) {
             case 1:
                System.out.println("Enter new name:");
                 String name = sc.nextLine(); 
                 guest.setName(name);
                 break; 
             case 2: 
                 System.out.println("Enter new address:");
                 String address = sc.nextLine(); 
                 guest.setAddress(address);
                 break;
             case 3:
                 System.out.println("Enter new nationality:");
                 String nationality = sc.nextLine(); 
                 guest.setNationality(nationality);
                 break; 
             case 4: 
                System.out.println("Enter new phone number:");
                 long phoneNumber = sc.nextLong(); 
                 sc.nextLine();
                 guest.setPhoneNumber(phoneNumber);
                 break; 
             case 5:
                 //Credit Card
                 System.out.println("Enter Card Type (PAYPAL/VISA/MASTER):");
                 String cardType = sc.nextLine(); 

                 if (cardType.compareTo("PAYPAL") ==0) {
                     guest.getCard().setCardType(TYPEOFCARD.PAYPAL);
                 }
                 else if (cardType.compareTo("VISA") ==0) {
                     guest.getCard().setCardType(TYPEOFCARD.VISA);
                 }
                 else {
                     guest.getCard().setCardType(TYPEOFCARD.MASTER);
                 }

                 System.out.println("Enter Card Number:");
                 String cardNumber = sc.nextLine(); 
                 guest.getCard().setCardNumber(cardNumber);
                 break; 
             case 6: 
                 //Identity
                 System.out.println("Enter Identity Type (PASSPORT/DRIVING_LICENSE):");
                 String identityType = sc.nextLine(); 
                 
                 if (identityType.compareTo("PASSPORT") ==0) {
                     guest.getIdentity().setIdentityType(TYPEOFID.PASSPORT);
                 }
                 else {
                     guest.getIdentity().setIdentityType(TYPEOFID.DRIVING_LICENSE);
                 }
                 System.out.println("Enter Identity Number:");
                 String idNumber = sc.nextLine(); 
                 guest.getIdentity().setIdNumber(idNumber);
 
                 break; 
             case 7: 
                 return; 
        }
        eg.setGuest(guest);
        eg.edit("Guest.txt");
        System.out.println("Guest details successfully updated. \n");
       }
    }
	

    /**
     * A function that searches and prints the details of a specified guest
     */
    public void searchGuestDetails() {

        System.out.println("Enter your name"); 
        String name = sc.nextLine(); //any part of name 
        
        //array list of guests
        ArrayList<Guests> guests = hotel.searchGuests(name); 

        if (guests != null && guests.size() != 0) {
            System.out.println("Existing records found: \n");

            System.out.printf("%-4s%20s%20s%n","No.","Name","ID");

            for (int i = 0; i < guests.size(); i++) {
                System.out.printf("%-4d%20s%20s%n",(i+1),guests.get(i).getName(),guests.get(i).getIdentity().getIdNumber());
            }

            System.out.println("Select existing guest if applicable (else input 0):");
            int option = sc.nextInt(); 
            sc.nextLine();

            if (option != 0) {
                printGuest(guests.get(option-1));
                return; 
            }
        }

        System.out.println("Guest name not found. \n");
    }
    
    /**
     * A function that prints the guest reservations
     */
    public void printGuestReservation() {
        System.out.println("Enter ID Number:"); 
        String documentID = sc.nextLine();

        Guests guest = hotel.searchGuestsbyID(documentID);

        if (guest == null) {
             System.out.println("Guest not found");
             return; 
        }

        System.out.println("Guest found.\n");
        for(Reservation r: guest.getAllReservations()) {
            r.printString();
        	// System.out.println(r.toString());
        	// System.out.println();
        }

    }
    
    public void printAllGuests() {
    	for(Guests guest: hotel.getAllGuests()) {
    		guest.printString();
    	}
    }

}
