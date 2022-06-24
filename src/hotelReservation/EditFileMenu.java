package hotelReservation;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that reads and edit in the data from Menu.txt and stores it into an ArrayList of MenuItems
 */
public class EditFileMenu {

    private static Scanner x;
    static final String tempName = "temp2.txt";
    /**
     * Reads in data from the Menu.txt and stores it into the ArrayList of MenuItems
     * @param menuItems MenuItems ArrayList
     * @param FilePath File path of Menu.txt
     */
    public void readMenuItem(ArrayList<MenuItems> menuItems,String FilePath){
        
        // The attributes
        String item;
        Double price;
        String details;
        try{
           
            x = new Scanner(new File(FilePath));
            x.useDelimiter("[,\r\n]");
            while (x.hasNext()){

                // Getting data from the txt file
                item = x.next();
                price = x.nextDouble();
                details = x.next();

                // Creating the menuItems and appending to ArrayList
                MenuItems temp = new MenuItems(item, price, details);
                menuItems.add(temp);
                if(x.hasNext()) x.nextLine();
                else break;
                
            }
            x.close();
        }catch (Exception e){
            System.out.println("Exit");
        }
        
    }
    
    /**
     * Edits the data in the Menu.txt
     * @param item New name of the dish
     * @param price New price of the dish
     * @param details New description detail of the dish
     * @param FilePath File path of Menu.txt
     */
    public void editMenuItem(MenuItems menuItem, String FilePath){ 
    	 
        String itemNew = "";
        Double priceNew = 0d;
        String detailsNew = "";
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
                itemNew = x.next();
                priceNew = x.nextDouble();
                detailsNew = x.next();
                if (itemNew.equals(menuItem.getItem())){ 
                    pw.println(menuItem.getItem() + "," + menuItem.getPrice() + "," + menuItem.getDetails()); 
                }// prints in the tempfile for this reservation code if not j copy in 
                else 
                { 
                    pw.println(itemNew + "," + priceNew + "," + detailsNew); 
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
     * Appends a new dish into the Menu.txt database
     * @param item Name of the dish
     * @param price Price of the dish
     * @param details Description detail of the dish
     * @param FilePath File path of Menu.txt
     */
    public void addMenuItem(MenuItems menuItem, String FilePath){
    
        File oldFile = new File(FilePath);
        
        try{
            FileWriter fw = new FileWriter(oldFile,true);//append not override completely
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(menuItem.getItem() + "," + menuItem.getPrice() + "," + menuItem.getDetails());
            pw.flush();
            pw.close();
        }catch (Exception e){
            System.out.println("Exit");
        }
    }

    /**
     * Deletes specific dish from the Menu.txt 
     * @param item Name of the dish to be deleted
     * @param FilePath File path of Menu.txt
     */
    public void deleteMenuItem(String item, String FilePath){ 
    	 
        String itemNew = "";
        Double priceNew = 0d;
        String detailsNew = "";
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
                itemNew = x.next();
                priceNew = x.nextDouble();
                detailsNew = x.next();
                if (itemNew.equals(item)){ 
                   continue;
                }// prints in the tempfile for this reservation code if not j copy in 
                else 
                { 
                    pw.println(itemNew + "," + priceNew + "," + detailsNew); 
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


