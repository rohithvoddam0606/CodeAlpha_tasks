package com.hotel;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		HotelReservationSystem system = new HotelReservationSystem();
		
		while(true) {
			printMenu();
			int choice = readInt("Enter yout choice: ");
			
			switch(choice) {
			case 1:
				viewAllRooms(system);
				break;
				
			case 2:
				searchAvailableRooms(system);
				break;
				
			case 3:
				makeReservation(system);
				break;
				
			case 4:
				cancelReservation(system);
				break;
				
			case 5:
				viewReservationsByPhone(system);
				break;
				
			case 6:
				viewAllReservations(system);
				break;
				
			case 0:
				System.out.println("Exiting system. Goodbye!");
				return;
				
			default:
				System.out.println("Invalid choice. Please select again.");
			}
		}
	}

	private static void printMenu() {
		System.out.println("\n====== HOTEL RESERVATION SYSTEM =====");
		System.out.println("1. View all rooms.");
		System.out.println("2. Search available rooms by type.");
		System.out.println("3. Make a reservation.");
		System.out.println("4. Cancel a reservation.");
		System.out.println("5. View my reservations (by phone number).");
		System.out.println("6. View all reservations (admin).");
		System.out.println("0. Exit");
		System.out.println("=========================================");
	}
	
	//VIEW ALL ROOMS
	private static void viewAllRooms(HotelReservationSystem system) {
		List<Room> rooms = system.getAllRooms();
		if(rooms.isEmpty()) {
			System.out.println("No rooms found in the system.");
			return;
		}
		rooms.forEach(System.out::println);
	}
	
	//SEARCH AVAILABLE ROOMS
	private static void searchAvailableRooms(HotelReservationSystem system) {
		RoomType type = readRoomType();
		List<Room> rooms = system.searchAvailableRooms(type);
		
		if(rooms.isEmpty()) {
			System.out.println("Noavailable rooms for type: "+type);
			return;
		}
		System.out.println("Available Rooms:");
		rooms.forEach(System.out::println);
	}
	
	//MAKE A RESERVATION
	private static void makeReservation(HotelReservationSystem system) {
		RoomType type = readRoomType();
		List<Room> available = system.searchAvailableRooms(type);
		
		if(available.isEmpty()) {
			System.out.println("No available rooms of type: "+type);
			return;
		}
		System.out.println("Available Rooms:");
		available.forEach(System.out::println);
		
		int roomId = readInt("Enter room ID to book: ");
		String guestName= readString("Enter guest name: ");
		String guestPhone= readString("Enter guest phone");
		
		LocalDate checkIn = LocalDate.parse(readString("Enter Check-in date (YYYY-MM-DD): "));
		LocalDate checkOut = LocalDate.parse(readString("Enter Check-out date (YYYY-MM-DD): "));
		
		Reservation reservation = system.bookRoom(roomId, guestName, guestPhone, checkIn, checkOut);
		
		if(reservation != null) {
			System.out.println("Reservation successful:");
			System.out.println(reservation);
		}
		else {
			System.out.println("Failed to reserve room.");
		}
	}
	
	//CANCEL RESERVATION
	private static void cancelReservation(HotelReservationSystem system) {
		int reservationId = readInt("Enter reservation ID to cancel: ");
		boolean success = system.cancelReservation(reservationId);
		
		if(success) {
			System.out.println("Reservation cancelled successfully.");
		}
		else {
			System.out.println("Cancellation failed. Check reservation ID.");
		}
	}
	
	//VIEW RESERVATIONS BY PHONE
	private static void viewReservationsByPhone(HotelReservationSystem system) {
		String phone = readString("Enter your phone number");
		List<Reservation> list = system.getReservationsByGuest(phone);
		
		if(list.isEmpty()) {
			System.out.println("No reservations found for phone: "+phone);
			return;
		}
		list.forEach(System.out::println);
	}
	
	//ADMIN: VIEW ALL RESERVATIONS
	private static void viewAllReservations(HotelReservationSystem system) {
		List<Reservation> list = system.getAllReservations();
		
		if(list.isEmpty()) {
			System.out.println("No reservations recorded in the system");
			return;
		}
		
		System.out.println("All Reservations:");
		list.forEach(System.out::println);
	}
	
	//HELPER INPUTS
	private static int readInt(String message) {
		System.out.println(message);
		while(!sc.hasNextInt()) {
			System.out.println("Please enter a valid number");
			sc.next();
		}
		int value = sc.nextInt();
		sc.nextLine();
		return value;
	}
	
	private static String readString(String message) {
		System.out.println(message);
		return sc.nextLine();
	}
	
	private static RoomType readRoomType() {
		System.out.println("Choose Room Type: ");
		System.out.println("1. STANDARD");
		System.out.println("2. DELUXE");
		System.out.println("3. SUITE");
		
		int choice = readInt("Enter choice: ");
		
		switch(choice) {
		case 1: return RoomType.STANDARD;
		case 2: return RoomType.DELUXE;
		case 3: return RoomType.SUITE;
		default:
			System.out.println("Invalid choice. Defaulting to STANDARD");
			return RoomType.STANDARD;
		}
	}
}
