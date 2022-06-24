package hotelReservation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads and edit in the data from Rooms.txt and stores it into an ArrayList of Rooms
 */
public class EditFileRoom implements EditFile{

	static final String tempName = "temp4.txt";
    private static Scanner x;
    
    private Hotel hotel;
    private Rooms room;
    
    public EditFileRoom(Hotel hotel) {
    	this.hotel = hotel;
    	room = null;
    }
    
    public void setRoom(Rooms room) {
    	this.room = room;
    }
    /**
     * Reads in data from the Rooms.txt and stores it into the ArrayList of Rooms
     * @param rooms ArrayList of Rooms
     * @param FilePath File path of Rooms.txt
     */
    public void readFile(String FilePath){
        
    	ArrayList<Rooms> rooms = hotel.getAllRooms();
    	
        int no;
        int floor;
        String id;
        double rate;
        double weekendRate;
        boolean wifi;
        boolean smoke; 
        boolean balcony; 
        String facingDescription;
        ROOMTYPE RT; 
        BEDTYPE BT; 
        ROOMSTATUS RS;
        try{
            x = new Scanner(new File(FilePath));
            x.useDelimiter("[,\r\n]");
            while (x.hasNext()){
                no = x.nextInt();
                floor = x.nextInt(); 
                id = x.next();
                rate = x.nextDouble();
                weekendRate = x.nextDouble();
                String read_boolean;
                read_boolean = x.next();
                if (read_boolean.equals("true")) wifi = true;
                else wifi = false;
                read_boolean = x.next();
                if (read_boolean.equals("true")) smoke = true;
                else smoke = false;
                read_boolean = x.next();
                if (read_boolean.equals("true")) balcony = true;
                else balcony = false;
                facingDescription = x.next();
                
                String rt = x.next();
                switch (rt){
                    case "SINGLE":
                        RT = ROOMTYPE.SINGLE;
                        break;
                    case "DOUBLE":
                        RT = ROOMTYPE.DOUBLE;
                        break;
                    case "DELUXE":
                        RT = ROOMTYPE.DELUXE;
                        break;
                    case "VIP": 
                        RT = ROOMTYPE.VIP;
                        break;
                    case "SUITE": 
                        RT = ROOMTYPE.SUITE;
                        break;
                    default:
                        RT = ROOMTYPE.SINGLE;
                }

                
                String bt = x.next();
                switch (bt){
                    case "SINGLE":
                        BT = BEDTYPE.SINGLE;
                        break;
                    case "DOUBLE":
                        BT = BEDTYPE.DOUBLE;
                        break;
                    case "QUEEN":
                        BT = BEDTYPE.QUEEN;
                        break;
                    case "KING": 
                        BT = BEDTYPE.KING;
                        break;
                    default:
                        BT = BEDTYPE.SINGLE;
                }

                String rs = x.next();
                switch (rs){
                    case "VACANT":
                        RS = ROOMSTATUS.VACANT;
                        break;
                    case "RESERVED":
                        RS = ROOMSTATUS.RESERVED;
                        break;
                    case "OCCUPIED":
                        RS = ROOMSTATUS.OCCUPIED;
                        break;
                    case "UNDER_MAINTENANCE": 
                        RS = ROOMSTATUS.UNDER_MAINTENANCE;
                        break;
                    default:
                        RS = ROOMSTATUS.VACANT;
                }

                
                Rooms temp = new Rooms(no,floor,id,rate,weekendRate,wifi,smoke,balcony,facingDescription,RT,BT,RS);
                rooms.add(temp);
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
     * @param no New room numner
     * @param f New room floor
     * @param id New room id
     * @param r New room weekday rate
     * @param w New room weekend rate
     * @param wifi New boolean of is WiFi enabled
     * @param smoke New boolean of is smoking room
     * @param b New boolean of if have balcony
     * @param facingV New description of room's view
     * @param roomT New room type: {SINGLE, DOUBLE, DELUXE, VIP, SUITE}
     * @param bed New bed type: {SINGLE, DOUBLE, QUEEN, KING}
     * @param roomS New room status: {VACANT, RESERVED, OCCUPIED, UNDER_MAINTENANCE}
     * @param FilePath File path of Rooms.txt
     */
    public void edit(String FilePath){ 
    	 
        
        int nonew=0; 
        int floornew=0; 
        String idnew=""; 
        double ratenew=0; 
        double weekendRatenew=0; 
        String wifinew=""; 
        String smokenew="";  
        String balconynew="";  
        String facingDescriptionNew=""; 
        String RTnew = ""; 
        String bedStringnew = ""; 
        String statusStringnew = ""; 
        String tempFile = tempName; 
        File oldFile = new File(FilePath); 
        File newFile = new File(tempFile); 
        Boolean read_boolean; 
                String wifiString, balconyString, smokeString; 
                read_boolean = room.isWifiEnabled(); 
                if (read_boolean) wifiString = "true"; 
                else wifiString = "false"; 
                read_boolean = room.isSmoking(); 
                if (read_boolean) smokeString = "true"; 
                else smokeString = "false"; 
                read_boolean = room.isBalcony(); 
                if (read_boolean) balconyString = "true"; 
                else balconyString = "false"; 
        String roomString; 
        String bedString; 
        String statusString; 
        switch (room.getRoomType()){ 
            case SINGLE: 
                roomString = "SINGLE"; 
                break; 
            case DOUBLE: 
                roomString = "DOUBLE"; 
                break; 
            case DELUXE: 
                roomString = "DELUXE"; 
                break; 
            case VIP:  
                roomString = "VIP"; 
                break; 
            case SUITE:  
                roomString = "SUITE"; 
                break; 
            default: 
                roomString = "SINGLE"; 
        } 
        switch (room.getBedType()){ 
            case SINGLE: 
                bedString = "SINGLE"; 
                break; 
            case DOUBLE: 
                bedString = "DOUBLE"; 
                break; 
            case QUEEN: 
                bedString = "QUEEN"; 
                break; 
            case KING:  
                bedString = "KING"; 
                break; 
            default: 
                bedString = "SINGLE"; 
        } 
 
        switch (room.getStatus()){ 
            case VACANT: 
                statusString = "VACANT"; 
                break; 
            case RESERVED: 
                statusString = "RESERVED"; 
                break; 
            case OCCUPIED: 
                statusString = "OCCUPIED"; 
                break; 
            case UNDER_MAINTENANCE:  
                statusString = "UNDER_MAINTENANCE"; 
                break; 
            default: 
                statusString = "VACANT"; 
        } 
        try{ 
            FileWriter fw = new FileWriter(tempFile,true);//append not override completely 
            BufferedWriter bw = new BufferedWriter(fw); 
            PrintWriter pw = new PrintWriter(bw); 
            x = new Scanner(oldFile); 
            x.useDelimiter("[,\r\n]"); 
             
            while (x.hasNext()){ 
                nonew = x.nextInt(); 
                floornew = x.nextInt();  
                idnew = x.next(); 
                ratenew = x.nextDouble(); 
                weekendRatenew = x.nextDouble(); 
                wifinew = x.next(); 
                smokenew = x.next(); 
                balconynew = x.next(); 
                facingDescriptionNew = x.next(); 
                RTnew = x.next(); 
                bedStringnew = x.next(); 
                statusStringnew = x.next(); 
                if (idnew.equals(room.getID())){ 
                    pw.println(room.getNumber() +","+ room.getFloor() + "," + room.getID() + "," + room.getRate() + "," +
                    		   room.getWeekendRate() + "," + wifiString + "," + smokeString + "," + balconyString + "," +
                    		   room.getFacingDescription()  + "," + roomString+ "," + bedString + "," + statusString ); 
                }// prints in the tempfile for this reservation code if not j copy in 
                else 
                { 
                    pw.println(nonew + "," +floornew + "," + idnew + "," + ratenew + "," + weekendRatenew + "," + wifinew + "," + smokenew + "," + balconynew+ "," +facingDescriptionNew+ "," + RTnew+ "," + bedStringnew + "," + statusStringnew); 
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
    
    //not use
    public void deleteRecord(String filePath) {
    	
    }
    
    //not use
    public void addRecord(String filePath) {
    	
    }
}


