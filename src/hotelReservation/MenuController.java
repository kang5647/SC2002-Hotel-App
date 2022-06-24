package hotelReservation;
import java.util.Scanner;

/**
 * The main control that interacts with the user to allow them to modify the Menu.  
 */
public class MenuController {
    private final Scanner sc = new Scanner(System.in);
    /**
     * Hotel that contains the Menu
     */
	private Hotel hotel; 

    /**
     * Constructs and initialises a MenuController object.
     * @param hotel The hotel object
     */
    public MenuController(Hotel hotel){
        this.hotel = hotel; 
    }

    /**
     * The main interface with the user, who will be able to add, update,
     * remove and print all MenuItems in the Menu.
     */
    public void menuControl() {

        while(true){

            System.out.println("\nPlease select one operation to proceed\n"
                                + "1. Add Item \n"
                                + "2. Update Item \n"
                                + "3. Remove Item \n"
                                + "4. Print Menu \n"
                                + "5. Return to previous menu \n");
            
            int option = sc.nextInt();
            sc.nextLine();

            if(option < 1 || option > 5){
                System.out.println("Invalid option.");
                continue;
            }

            switch(option) {
            case 1: 
                newItem();
                break;
            case 2:
                updateItem();
                break;
            case 3: 
                removeItem();
                break;
            case 4:
                hotel.getMenu().displayMenu();
                break;
            case 5:
                return;
            }
        }
    }

    /**
     * A function that adds menuItems to the current Menu.
     */
    public void newItem(){
        System.out.println("Enter the name of the new item:"); 
        String name = sc.nextLine(); //any part of name 
        System.out.println("Enter the price of the new item:"); 
        double price= sc.nextDouble(); //any part of name 
        sc.nextLine();
        System.out.println("Enter the description of the new item:"); 
        String description = sc.nextLine(); //any part of name 

        hotel.getMenu().addItem(name, price, description);
        System.out.println("Item successfully added. \n");
    }

    /**
     * A function that updates a selected menuItem in the current Menu.
     */
    public void updateItem(){
        hotel.getMenu().displayMenu();
        System.out.println("Enter the index of the item to update:"); 
        int index= sc.nextInt(); //any part of name 
        sc.nextLine();
        System.out.println("Enter the new price of the item:"); 
        double price= sc.nextDouble(); //any part of name 
        sc.nextLine();
        System.out.println("Enter the new description of the item:"); 
        String description = sc.nextLine(); //any part of name 
        hotel.getMenu().updateItem(index, price, description);
        System.out.println("Item successfully updated. \n");

    }
     
    /**
     * A function that removes a selected menuItem from the current Menu.
     */
    public void removeItem(){
        hotel.getMenu().displayMenu();
        System.out.println("Enter the index of the item to remove:"); 
        int index= sc.nextInt(); //any part of name 
        sc.nextLine();
        hotel.getMenu().removeItem(index);
        System.out.println("Item successfully removed. \n");
        
    }
}
