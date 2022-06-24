package hotelReservation;
import java.util.ArrayList;

/**
 * Menu class which contains an ArrayList of menuItems.
 * You can add, edit, remove or display menuItems in the Menu.
 */
public class Menu {
    /**
     * ArrayList of MenuItems
     */
    private ArrayList<MenuItems> menuItems;
    private EditFileMenu em = new EditFileMenu();

    /**
     * Constructs and initialises a Menu object.
     */
    public Menu(){
        menuItems = new ArrayList<MenuItems>();
        em.readMenuItem(menuItems, "Menu.txt");
    }

    /**
     * A function to display all menuItems.
     */
    void displayMenu() {

        System.out.println("");
        System.out.println("________________________________________________________________________\n");

        System.out.printf("%-6s%-20s%-10s%-20s%n%n","No.","Item","Price","Description");

        for (int i = 0; i < menuItems.size(); i++) {
            System.out.printf("%-6d%-20s%-10.2f%-20s%n%n", (i+1), menuItems.get(i).getItem(), menuItems.get(i).getPrice(), menuItems.get(i).getDetails());
        }
        System.out.println("________________________________________________________________________\n");
        System.out.println("");
    }

    /**
     * A function to append menuItems to the current Menu.
     * @param item Name of the new dish
     * @param price Price of the new dish
     * @param details Description details of the new dish
     */
    void addItem(String item, double price, String details) {
        MenuItems newMenuItem = new MenuItems(item, price, details); 
        menuItems.add(newMenuItem); 
        em.addMenuItem(newMenuItem, "Menu.txt");
    }

    /**
     * A function to remove an existing menuItem from the current Menu.
     * @param indexPlusOne Index of the menuItem to be removed
     */
    void removeItem(int indexPlusOne) {
        em.deleteMenuItem(menuItems.get(indexPlusOne-1).getItem(), "Menu.txt");
        menuItems.remove(indexPlusOne-1); 
       
    }

    /**
     * A function to select a menuItem from the Menu.
     * @param indexPlusOne Index of the menuItem to be selected
     * @return menuItem of selected index
     */
    MenuItems chooseItem(int indexPlusOne) {
        return menuItems.get(indexPlusOne-1);
    }

    /**
     * A function that updates an existing menuItem in the Menu.
     * @param indexPlusOne Index of the menuItem to be updated
     * @param price New price of the menuItem
     * @param details New description details of the menuItem
     */
    void updateItem(int indexPlusOne, Double price, String details) {
        menuItems.get(indexPlusOne-1).setPrice(price);
        menuItems.get(indexPlusOne-1).setDetails(details);
        em.editMenuItem(menuItems.get(indexPlusOne-1), "Menu.txt");
    }

    /**
     * A function that returns the length of the menu.
     * @return Number of menuItems in the Menu
     */
    int size() {
        return menuItems.size(); 
    }
    
    /**
     * A function that returns the ArrayList of all menuItems in the Menu.
     * @return ArrayList of all menuItems in the Menu
     */
    public ArrayList<MenuItems> getAllItems() {
    	return menuItems;
    }
}
