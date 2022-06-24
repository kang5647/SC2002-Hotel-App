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
 * Class that reads and edit in the data from RSO.txt and stores it into an ArrayList of RoomServiceOrder in the Reservation
 */
public class EditFileRSO {

	/** 
	 * Menu class that contains all the MenuItems
	 */
	static final String tempName = "temp5.txt";
	
	private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	private static Scanner x;
	private Hotel hotel;
	private Menu menu;
	
	public EditFileRSO(Hotel hotel) {
		this.hotel = hotel;
	}

	/**
	 * Reads in data from the RSO.txt and stores it into the ArrayList of Reservation
	 * @param hotel Hotel that contains the Menu
	 * @param FilePath File path of RSO.txt
	 */
	public void readFile(String FilePath) {
		menu = hotel.getMenu();
		ArrayList<Reservation> reservations = hotel.getAllReservations();
		String orderID = "";
		double totalAmount = 0; 
		String orderStatus = "";
		ORDERSTATUS status = ORDERSTATUS.NONE;
	    String remarks ="";
		String dateString = "";
		LocalDateTime date;
		
		String itemString;	
		
		try {
			x = new Scanner(new File(FilePath));
			x.useDelimiter("[,\r\n]");
			while(x.hasNext()) {
				ArrayList<MenuItems> items = new ArrayList<MenuItems>();
				orderID = x.next();
				totalAmount = x.nextDouble();
				orderStatus = x.next();
				switch(orderStatus) {
				case "CONFIRMED":
					status = ORDERSTATUS.CONFIRMED;
					break;
				case "PREPARING":
					status = ORDERSTATUS.PREPARING;
					break;
				case "DELIVERED":
					status = ORDERSTATUS.DELIVERED;
					break;
				case "NONE":
					status = ORDERSTATUS.NONE;
					break;
				}
				
				remarks = x.next();
				dateString = x.next();
				date = LocalDateTime.parse(dateString, formatter);
				
				while(x.hasNext()) {
					itemString = x.next();
					if(itemString.equals(""))
						break;
					for(MenuItems item: menu.getAllItems()) {
						if(itemString.equals(item.getItem())) {
							items.add(item);	
							break;
						}				    
					}
				}
				RoomServiceOrder rso = new RoomServiceOrder(orderID, totalAmount, status, remarks, date, items);
				String reservationID = orderID.substring(0,7);
				for(Reservation r : reservations) {
					if(r.getReservationCode().equals(reservationID))
					   r.addRSO(rso);
				}				
				if(x.hasNext()) x.nextLine();
                else break;
				
			}
			x.close();
		} catch(Exception e) {
			System.out.println("Exit");
		}
	}
	
	/**
	 * Appends a new order into the RSO.txt database
	 * @param rso Order to be added
	 * @param FilePath File path of RSO.txt
	 */
	public void addRSOToFile(RoomServiceOrder rso, String FilePath) {
		
		File file = new File(FilePath);
		
		String osString ="";
		
		switch(rso.getOrderStatus()) {
		case CONFIRMED:
			osString = "CONFIRMED";
			break;
		case PREPARING:
			osString = "PREPARING";
			break;
		case DELIVERED:
			osString = "DELIVERED";
			break;
		case NONE:
			osString = "NONE";
		}
		
		String dateString = rso.getDateTime().format(formatter);
		
		try{
            FileWriter fw = new FileWriter(file,true);//append not override completely
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(rso.getOrderID() + "," + rso.getTotalAmount() + "," + osString +"," + rso.getRemarks() + "," + dateString +",");
            for(int i =0; i< rso.getChosenMenuItems().size(); i++) {
            	MenuItems item = rso.getChosenMenuItems().get(i);
            	pw.print(item.getItem());
            	if(i< rso.getChosenMenuItems().size()-1)
            		pw.print(",");          	
            }
            pw.println();
            pw.flush();
            pw.close();
        }catch (Exception e){
            System.out.println("Exit");
        }
	}
	

	/**
	 * Edits the data in the RSO.txt
	 * @param rso Order to be edited 
	 * @param FilePath File path of RSO.txt
	 */
	public void editRSO(RoomServiceOrder rso, String FilePath) {
		String orderID = "";
		double totalAmount = 0; 
		String orderStatus = "";
	    String remarks ="";
		String dateString = "";
		String dateString2 = rso.getDateTime().format(formatter);
		
		String osString ="";
		
		switch(rso.getOrderStatus()) {
			case CONFIRMED:
				osString = "CONFIRMED";
				break;
			case PREPARING:
				osString = "PREPARING";
				break;
			case DELIVERED:
				osString = "DELIVERED";
				break;
			case NONE:
				osString = "NONE";
		}
		
		String tempFile = tempName;
	    File oldFile = new File(FilePath);
	    File newFile = new File(tempFile);
		
		try {
			    FileWriter fw = new FileWriter(tempFile,true);//append not override completely
			    BufferedWriter bw = new BufferedWriter(fw);
	            PrintWriter pw = new PrintWriter(bw);
	            x = new Scanner(oldFile);
	            x.useDelimiter("[,\r\n]");
	            while(x.hasNext()) {
	            	orderID = x.next();
	            	totalAmount = x.nextDouble();
	            	orderStatus = x.next();
					remarks = x.next();
					dateString = x.next();
					if(orderID.equals(rso.getOrderID())) {
						pw.print(rso.getOrderID() + "," + rso.getTotalAmount() + "," + osString + "," + rso.getRemarks() + "," + dateString2 + "," );
						 for(int i =0; i< rso.getChosenMenuItems().size(); i++) {
				            	MenuItems item = rso.getChosenMenuItems().get(i);
				            	pw.print(item.getItem());
				            	if(i< rso.getChosenMenuItems().size()-1)
				            		pw.print(",");          	
				           }
					}else {
						ArrayList<String> items = new ArrayList<String>();	
						while(x.hasNext()) {
							String itemString = x.next();
							if(itemString.equals(""))
								break;
							items.add(itemString);
						}
						pw.print(orderID + "," + totalAmount + "," + orderStatus + "," + remarks + "," + dateString + ",");
						for(int i =0; i< items.size(); i++) {
			            	pw.print(items.get(i));
			            	if(i< items.size()-1)
			            		pw.print(",");
						}
					} 
					pw.println();
					if(x.hasNext()) x.nextLine();
	                else break;
	            }
				x.close();
		        pw.flush();
		        pw.close();
		        oldFile.delete();
         	    File dump = new File(FilePath);
         	    newFile.renameTo(dump);	            
         	      
		}catch (Exception e){
            System.out.println("Exit");
        }
	}
	

	/**
	 * Deletes specific order from the RSO.txt
	 * @param reservationCode Reservation code for the order to be deleted
	 * @param FilePath File path of RSO.txt
	 */
	public void deleteRSO(String reservationCode, String FilePath) {
		String orderID = "";
		double totalAmount = 0; 
		String orderStatus = "";
	    String remarks ="";
		String dateString = "";
		String tempFile = tempName;
	    File oldFile = new File(FilePath);
	    File newFile = new File(tempFile);
		
		try {
			    FileWriter fw = new FileWriter(tempFile,true);//append not override completely
	            BufferedWriter bw = new BufferedWriter(fw);
	            PrintWriter pw = new PrintWriter(bw);
	            x = new Scanner(oldFile);
	            x.useDelimiter("[,\r\n]");
	            while(x.hasNext()) {
	            	orderID = x.next();
	            	totalAmount = x.nextDouble();
	            	orderStatus = x.next();
					remarks = x.next();
					dateString = x.next();

					if(orderID.substring(0,7).equals(reservationCode)) {
						x.nextLine();
						continue;
					}else {
						ArrayList<String> items = new ArrayList<String>();	
						while(x.hasNext()) {
							String itemString = x.next();
							if(itemString.equals(""))
								break;
							items.add(itemString);
						}
						pw.print(orderID + "," + totalAmount + "," + orderStatus + "," + remarks + "," + dateString + ",");
						for(int i =0; i< items.size(); i++) {
			            	pw.print(items.get(i));
			            	if(i< items.size()-1)
			            		pw.print(",");
						}
					} 
					pw.println();
					if(x.hasNext()) x.nextLine();
	                else break;
	            }
				x.close();
		        pw.flush();
		        pw.close();
		        oldFile.delete();
         	    File dump = new File(FilePath);
         	    newFile.renameTo(dump);	                
		}catch (Exception e){
            System.out.println("Exit");
        }
	}
}
