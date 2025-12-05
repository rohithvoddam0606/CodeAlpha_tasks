# Hotel Reservation System (Java + PostgreSQL)

A fully functional **Hotel Reservation System** built using **Java (OOP)** and **PostgreSQL**, designed to search rooms, book reservations, cancel bookings, and manage hotel operations.  
This project demonstrates clean architecture, layered design (DAO pattern), and real-world database integration.

## 🚀 Features

### **1. Room Management**
- View all rooms
- Rooms categorized as:
  - Standard
  - Deluxe
  - Suite
- Live availability updates

### **2. Reservation Management**
- Search available rooms by type
- Make a reservation with:
  - Guest details  
  - Check-in & Check-out dates  
- Calculate total cost based on room price × number of nights
- Simulated payment system (with transaction ID)
- Cancel reservations
- View reservations by phone number
- Admin view of **all reservations**

### **3. Database Integration**
- PostgreSQL database used for:
  - Room data
  - Reservation records
  - Auto-increment reservation IDs
- JDBC-based DAO layer (`RoomDAO` & `ReservationDAO`)

### **4. Clean Java OOP Structure**
- Classes: `Room`, `Reservation`, `RoomType`, `HotelReservationSystem`
- DAO Pattern applied
- Separation of concerns
- Fully modular and extendable

---

▶️ How to Run the Project
Prerequisites

Java 8+ installed

PostgreSQL installed

JDBC Driver: postgresql-42.x.x.jar added to project classpath

Eclipse / IntelliJ / VS Code (any Java IDE)


1. Clone the repository:

git clone https://github.com/your-username/hotel-reservation-system.git

2. Import into your IDE.

3. Add PostgreSQL JDBC driver to your project build path.

4. Set up the database using the SQL commands above.

5. Run: Main.java

6. Use the console menu to:

Search rooms

Book a room

Cancel booking

View reservation history

