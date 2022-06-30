# SC2002-Hotel-App
## About 
This is an assignment for SC2002 (Object-Oriented Design & Programming) which is about applying Object-Oriented (OO) concepts to the development of the Hotel Reservation and Payment System (HRPS). It is used by hotel staff to **manage guests information**, **update room status**, **make reservations for guests**, **update room service menu items**, and **take room service orders from guests**.
\
\
We develop the hotel application using the **Model-View-Controller(MVC)** framework. Each object consists of its own **class** and a corresponding **_controller_*** class which handles user interaction and renders view to the user.
\
\
<sup>_(*) since we are only required to implement simple User interaction (UI) using **Command Line**, the **controller class** handles both **View** and **Controller**._</sup>
\
\
The **main object**, **[Hotel](src/hotelReservation/Hotel.java)** holds the other **5** key objects which make up the full app. They are: 
1. **[Guest](src/hotelReservation/Guests.java)** - **[CreditCard.java](src/hotelReservation/CreditCard.java)**, **[Identity.java](src/hotelReservation/Identity.java)**
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_Controller_) **[GuestController.java](src/hotelReservation/GuestController.java)**
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_File I/O_) **[EditFileGuest.java](src/hotelReservation/EditFileGuest.java)**, 
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_ENUM_) **[GENDERTYPE.java](src/hotelReservation/GENDERTYPE.java)**, **[TYPEOFID.java](src/hotelReservation/TYPEOFID.java)**, **[TYPEOFCARD.java](src/hotelReservation/TYPEOFCARD.java)**

2. **[Room](src/hotelResevation/Rooms.java)** - (_Controller_) **[RoomController.java](src/hotelReservation/RoomController.java)**
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_File I/O_) **[EditFileRoom.java](src/hotelReservation/EditFileRoom.java)**
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_ENUM_) **[BEDTYPE.java](src/hotelReservation/BEDTYPE.java)**, **[ROOMTYPE.java](src/hotelReservation/ROOMTYPE.java)**,  **[ROOMSTATUS.java](src/hotelReservation/ROOMSTATUS.java)**
3. **[Reservation](src/hotelReservation/Reservation.java)** - (_Controller_) **[ReservationController.java](src/hotelReservation/ReservationController.java)** 
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_File I/O_) **[EditFileReservation.java](src/hotelReservation/EditFileReservation.java)** 
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_ENUM_) **[RESERVATIONSTATUS.java](src/hotelReservation/RESERVATIONSTATUS.java)**
4. **[Menu](src/hotelReservation/Menu.java)** - (_Controller_) **[MenuController.java](src/hotelReservation/MenuController.java)**
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_File I/O_) **[EditFileMenu.java](src/hotelReservation/EditFileMenu.java)**
5. **[Room Service](src/hotelReservation/RoomServiceOrder.java)** - (_Controller_) **[RoomServiceController.java](src/hotelReservation/RoomServiceController.java)**
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_File I/O_) **[EditFileRSO.java](src/hotelReservation/EditFileRSO.java)**
\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_ENUM_) **[ORDERSTATUS.java](src/hotelReservation/ORDERSTATUS.java)**

### Utility Classes
In addition to the main classes and their controllers, we have also created several utility classes to facilitate **information exchange** between classes:
1. **[Bill.java](src/hotelReservation/Bill.java)** - Object class to hold **attributes** of the bill upon checkout
2. **[CheckDateUtility.java](src/hotelReservation/CheckDateUtility.java)** - To check if the check-in/check-out date **crashes** with any date of the existing reservations
3. **[CheckInController.java](src/hotelReservation/CheckInController.java)** - **Check-in** controller
4. **[CheckOutController.java](src/hotelReservation/CheckOutController.java)** - **Check-out** controller
5. **[PrinterController.java](src/hotelReservation/PrinterController.java)** - To print **room status**, **room occupancy rate** by room type, and **reservations** by room 


### Database 
As of the requirement of this project, instead of using database application (MySQL, MS Access, etc.), we use text file to store data. There are 5 text files in this project to store different type of data, namely: 
1. **[Guest.txt](Guest.txt)** _stores_ **name**, **address**, **nationality**, **phone number**, **gender**, **credit card number**, **card owner**, **expiry date**, **security code**, **card type**, **ID number**,and **ID type**
2. **[Menu.txt](Menu.txt)** _stores_ **menu item**, **price**, and **details**
3. **[Reservation.txt](Reservation.txt)** _stores_ **reservation code**, **check-in date**, **check-out date**, **number of adults**, **number of children**, **status**, **discount**, **guest's ID number**
4. **[RoomOrder.txt](RoomOrder.txt)** _stores_ **order ID**, **total amount**, **status**, **remarks**, **date**, and list of **ordered items**
5. **[Rooms.txt](Rooms.txt)** _stores_ **room number**, **floor number**, **unit number**, **rate**, **weekend rate**, **wifi(True/False)**, **smoke(True/False)**, **balcony(True/False)**, **window facing description**, **room type**, **bed type**, and **status**.
