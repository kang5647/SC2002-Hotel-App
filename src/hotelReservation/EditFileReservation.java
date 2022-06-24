package hotelReservation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads and edit in the data from Reservation.txt and stores it into an ArrayList of Reservation and Guests
 */
public class EditFileReservation implements EditFile{
	
	static final String tempName = "temp3.txt";
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private static Scanner x;
 
    private Hotel hotel;
    private Reservation reservation;
    
    public EditFileReservation(Hotel hotel) {
    	this.hotel = hotel;
    	this.reservation = null;
    }
    
    public void setReservation(Reservation reservation) {
    	this.reservation = reservation;
    }
    /**
     * Reads in data from the Reservation.txt and stores it into the ArrayList of Guests and Reservation
     * @param reservations Reservation ArrayList
     * @param guest Guests ArrayList
     * @param FilePath File path of Reservation.txt
     */
    public void readFile(String FilePath){
       
    	ArrayList<Reservation> reservations = hotel.getAllReservations();
    	ArrayList<Guests> guest = hotel.getAllGuests();
    	
        String r_Code = "";
        String DateCheckIn = ""; 
        String DateCheckOut = "";
        int Adults = 0;
        int Children = 0;
        String reservationStat = "";
        double disc = 0;
        String guestID;
        try{
           
            x = new Scanner(new File(FilePath));
            x.useDelimiter("[,\r\n]");
            while (x.hasNext()){
                r_Code = x.next();
                DateCheckIn = x.next(); //still need change to DateTime for reading in
                DateCheckOut = x.next();
                LocalDateTime dateTimeIn = LocalDateTime.parse(DateCheckIn, formatter);
                LocalDateTime dateTimeOut = LocalDateTime.parse(DateCheckOut, formatter); //still need change to DateTime for reading in 
                //just run readReservation record after editReservation so we just need edit textfile everytime for reservation details
                Adults = x.nextInt();
                Children = x.nextInt();
                reservationStat = x.next();
                RESERVATIONSTATUS res;
                switch (reservationStat){
                    case "CONFIRMED":
                        res = RESERVATIONSTATUS.CONFIRMED;
                        break;
                    case "WAITLIST":
                        res = RESERVATIONSTATUS.WAITLIST;
                        break;
                    case "CHECKED_IN":
                        res = RESERVATIONSTATUS.CHECKED_IN;
                        break;
                    case "EXPIRED": 
                        res = RESERVATIONSTATUS.EXPIRED;
                        break;
                    default:
                        res = RESERVATIONSTATUS.CONFIRMED;
                }
                disc = x.nextDouble();
                guestID = x.next();
                Reservation temp = new Reservation(r_Code,dateTimeIn,dateTimeOut,Adults,Children,res,disc);
                int found = 0;
                for (Guests guest1: guest){
                    Guests tempG;
                    if (guest1.getIdentity().getIdNumber().equals(guestID)){
                    tempG = guest1;
                    found = 1;
                    temp.addGuest(tempG);
                    tempG.addReservation(temp);
                    }
                }
                if (found == 0){
                    System.out.println("There is no guest with ID: " + guestID +",\n please check the database");
                }
                else if (found == 1 ) reservations.add(temp);
                if(x.hasNext()) x.nextLine();
                else break;
                
            }
            x.close();

        }catch (Exception e){
            System.out.println("Exit");
        }

    }

    /**
     * Edits the data in the Reservation.txt
     * @param reservationCodeEditTerm Reservation code of the reservation to be editied
     * @param temp New check in date
     * @param temp2 New check out date
     * @param numberOfAdults New number of adults
     * @param numberOfChildren New number of children
     * @param reservationStatus2 New reservation status
     * @param discount New discount rate
     * @param guestID Guest ID
     * @param FilePath File path of reservation.txt
     */
    public void edit(String FilePath){

    	// converts localdatetime to string
    	String checkInDate = reservation.getDateOfCheckIn().format(formatter);
        String checkOutDate = reservation.getDateOfCheckOut().format(formatter);
        
        String res;
        switch (reservation.getReservationStatus()){
            case CONFIRMED:
                res = "CONFIRMED";
                break;
            case WAITLIST:
                res = "WAITLIST";
                break;
            case CHECKED_IN:
                res = "CHECKED_IN";
                break;
            case EXPIRED: 
                res = "EXPIRED";
                break;
            default:
                res = "CONFIRMED";
        }
        //still need to convert reservationstatus to string
        String tempFile = tempName;
        File oldFile = new File(FilePath);
        File newFile = new File(tempFile);
        String r_Code = "";
        String DateCheckIn = ""; 
        String DateCheckOut = "";
        int Adults = 0;
        int Children = 0;
        String reservationStat = "";
        double disc = 0;
        String GUEST;
        try{
            FileWriter fw = new FileWriter(tempFile,true);//append not override completely
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(oldFile);
            x.useDelimiter("[,\r\n]");
            while (x.hasNext()){
                r_Code = x.next();
                DateCheckIn = x.next(); //still need change to DateTime for reading in
                DateCheckOut = x.next(); //still need change to DateTime for reading in 
                //just run readReservation record after editReservation so we just need edit textfile everytime for reservation details
                Adults = x.nextInt();
                Children = x.nextInt();
                reservationStat = x.next();
                disc = x.nextDouble();
                GUEST = x.next();
                if (r_Code.equals(reservation.getReservationCode())){
                    pw.println(r_Code + "," + checkInDate + "," + checkOutDate + "," + reservation.getNumberOfAdults()+ "," + 
                    		   reservation.getNumberOfChildren() + "," + res + "," + reservation.getDiscount() + "," + 
                    		   reservation.getGuest().getIdentity().getIdNumber());
                }// prints in the tempfile for this reservation code if not j copy in
                else
                {
                    pw.println(r_Code + "," + DateCheckIn + "," + DateCheckOut + "," + Adults + "," + Children + "," + reservationStat + "," + disc+ "," + GUEST);
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
     * Appends a new guest into the Reservation.txt database
     * @param reservationCodeNew Reservation code of the reservation to be editied
     * @param temp Check in date
     * @param temp2 Check out date
     * @param numberOfAdults Number of adults
     * @param numberOfChildren Number of children
     * @param reservationStatus2 Reservation status
     * @param discount Discount rate
     * @param guestID Guest ID
     * @param FilePath File path of reservation.txt
     */
    public void addRecord(String FilePath){
            
    	String checkInDate = reservation.getDateOfCheckIn().format(formatter);
        String checkOutDate = reservation.getDateOfCheckOut().format(formatter);
        // converts localdatetime to string
        String res;
        switch (reservation.getReservationStatus()){
            case CONFIRMED:
                res = "CONFIRMED";
                break;
            case WAITLIST:
                res = "WAITLIST";
                break;
            case CHECKED_IN:
                res = "CHECKED_IN";
                break;
            case EXPIRED: 
                res = "EXPIRED";
                break;
            default:
                res = "CONFIRMED";
        }
        //still need to convert reservationstatus to string
       
        File oldFile = new File(FilePath);
        
        try{
            FileWriter fw = new FileWriter(oldFile,true);//append not override completely
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(reservation.getReservationCode()+ "," + checkInDate + "," + checkOutDate + "," + 
            reservation.getNumberOfAdults() + "," + reservation.getNumberOfChildren() + "," + res + "," + reservation.getDiscount()+ "," + 
            reservation.getGuest().getIdentity().getIdNumber());
            pw.flush();
            pw.close();
        }catch (Exception e){
            System.out.println("Exit");
        }
    }

    /**
     * Deletes specific reservation from the Reservation.txt
     * @param reservationCodeEditTerm Reservation code of the reservation to be deleted
     * @param FilePath File path of Reservation.txt
     */
    public void deleteRecord(String FilePath){ 
   	  
        // converts localdatetime to string 
 
        //still need to convert reservationstatus to string 
        String tempFile = tempName; 
        File oldFile = new File(FilePath); 
        File newFile = new File(tempFile); 
        String r_Code = ""; 
        String DateCheckIn = "";  
        String DateCheckOut = ""; 
        int Adults = 0; 
        int Children = 0; 
        String reservationStat = ""; 
        double disc = 0; 
        String guestID = "";
        try{ 
            FileWriter fw = new FileWriter(tempFile,true);//append not override completely 
            BufferedWriter bw = new BufferedWriter(fw); 
            PrintWriter pw = new PrintWriter(bw); 
            x = new Scanner(oldFile); 
            x.useDelimiter("[,\r\n]"); 
            while (x.hasNext()){ 
                r_Code = x.next(); 
                DateCheckIn = x.next(); //still need change to DateTime for reading in 
                DateCheckOut = x.next(); //still need change to DateTime for reading in  
                //just run readReservation record after editReservation so we just need edit textfile everytime for reservation details 
                Adults = x.nextInt(); 
                Children = x.nextInt(); 
                reservationStat = x.next(); 
                disc = x.nextDouble(); 
                guestID = x.next();
                if (r_Code.equals(reservation.getReservationCode())){ 
                	x.nextLine();
                    continue; 
                }// prints in the tempfile for this reservation code if not j copy in 
                else 
                { 
                    pw.println(r_Code + "," + DateCheckIn + "," + DateCheckOut + "," + Adults + "," + Children + "," + reservationStat + "," + disc+ "," + guestID); 
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

}