# SC2002-Hotel-App
## About 
This is an assignment for SC2002 (Object-Oriented Design & Programming) which is about applying Object-Oriented (OO) concepts to the development of the Hotel Reservation and Payment System (HRPS). It is used by hotel staff to **manage guests information**, **update room status**, **make reservations for guests**, **update room service menu items**, and **take room service orders from guests**.
\
\
The main object, [Hotel](src/hotelReservation/Hotel.java) holds the other 5 key objects which make up the full app. They are: 
1. **[Guest](src/hotelReservation/Guests.java)**
2. **[Room](src/hotelResevation/Rooms.java)**
3. **[Reservation](src/hotelReservation/Reservation.java)**
4. **[Menu](src/hotelReservation/Menu.java)** 
5. **[Room Service](src/hotelReservation/RoomServiceOrder.java)** 

We implements Model-View-Controller framework into our project. Each object consists of its class and a corresponding controller.


### Database 
As of the requirement of this project, instead of using database application (MySQL, MS Access, etc.), we use text file to store data. There are 5 text files in this project to store different type of data, namely: 
1. **[Guest.txt](Guest.txt)** _stores_ **name**, **address**, **nationality**, **phone number**, **gender**, **credit card number**, **card owner**, **expiry date**, **security code**, **card type**, **ID number**,and **ID type**
2. **[Menu.txt](Menu.txt)** _stores_ **menu item**, **price**, and **details**
3. **[Reservation.txt](Reservation.txt)** _stores_ **reservation code**, **check-in date**, **check-out date**, **number of adults**, **number of children**, **status**, **discount**, **guest's ID number**
4. **[RoomOrder.txt](RoomOrder.txt)** _stores_ **order ID**, **total amount**, **status**, **remarks**, **date**, and list of **ordered items**
5. **[Rooms.txt](Rooms.txt)** _stores_ **room number**, **floor number**, **unit number**, **rate**, **weekend rate**, **wifi(True/False)**, **smoke(True/False)**, **balcony(True/False)**, **window facing description**, **room type**, **bed type**, and **status**.
