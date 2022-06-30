package hotelReservation;



import java.util.Scanner;

public class MainApp {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Hotel FiveGuys = new Hotel("Five Guys");
        CustomerController customerController = new CustomerController(FiveGuys);   
        GuestController GuestControl = new GuestController(FiveGuys);
        RoomServiceController RoomServiceControl = new RoomServiceController(FiveGuys); 
        RoomController RoomControl = new RoomController(FiveGuys);
        MenuController MenuControl = new MenuController(FiveGuys);
        boolean run = true;
        while (run) {
            System.out.println("Welcome to Five Guys Hotel! \n");
            System.out.println("Make a choice");
            System.out.println("1. Reservation");
            System.out.println("2. Guest");
            System.out.println("3. Room Service");
            System.out.println("4. Menu");
            System.out.println("5. Room");
            System.out.println("6. Quit");
            int choice = sc.nextInt(); 
            sc.nextLine();
            if(choice < 1 || choice > 6){
                System.out.println("Invalid choice");
                continue;
            }
            switch(choice) {
                case 1: 
                    customerController.chooseOption(); 
                    break; 
                case 2: 
                    GuestControl.guestControl();
                    break;  
                case 3: 
                    RoomServiceControl.roomService();
                    break; 
                case 4: 
                    MenuControl.menuControl();
                    break;
                case 5: 
                    RoomControl.roomControl();
                    break;
                case 6:
                    run = false;
                    break;
                default:
                    break; 
            }
           
        }
    

    }
}
