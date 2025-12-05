package com.hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HotelReservationSystem {
	private RoomDao roomdao = new RoomDao();
	private ReservationDao reservationdao = new ReservationDao();

	private Random random = new Random();
	private Scanner sc = new Scanner(System.in);

	// METHOD TO SEARCH FOR AVAILABLE ROOMS
	public List<Room> searchAvailableRooms(RoomType type){
		return roomdao.seeAvailableRooms(type);
	}

	// METHOD TO BOOK ROOM
	public Reservation bookRoom(int roomId, String guestName, String guestPhone, LocalDate checkIn, LocalDate checkOut) {
		Room room = roomdao.getRoomById(roomId);
		
		if(room == null) {
			System.out.println("Room not found");
			return null;
		}
		
		if(!room.isAvailable()) {
			System.out.println("Room is already booked!");
			return null;
		}
		
		long days = ChronoUnit.DAYS.between(checkIn, checkOut);
		if(days<=0) {
			System.out.println("Check-out date must be after check-in date.");
			return null;
		}
		
		double totalAmount = days * room.getPricePerNight();
		
		//PAYMENT SIMULATION
		if(!simulatePayment(totalAmount)) {
			System.out.println("Payment Cancelled.");
			return null;
		}
		
		//CREATE RESERVATION OBJECT(Id will be auto generated)
		Reservation reservation = new Reservation(0, roomId, guestName, guestPhone, checkIn, checkOut, totalAmount, false);
		
		//SAVE RESERVATION IN DB
		Reservation saved = reservationdao.saveReservation(reservation);
		
		if(saved == null) {
			System.out.println("Error saving reservation.");
			return null;
		}
		
		roomdao.updateRoomAvailability(roomId, false);
		return saved;
	}
	
	private boolean simulatePayment(double amount) {
		System.out.println("Total Amount: Rs. "+amount);
		System.out.println("Proceed to payment? (yes/no): ");
		
		String input =sc.nextLine().trim().toLowerCase();
		
		if(input.equals("yes")) {
			String transactionId = "TXN-" + (100000 + random.nextInt(900000));
			System.out.println("Payment successful. Transaction ID: "+transactionId);
			return true;
		}
		return false;
	}
	
	//CANCEL RESERVATION
	public boolean cancelReservation(int reservationId) {
		boolean success = reservationdao.cancelReservation(reservationId);
		if(!success) {
			return false;
		}
		
		List<Reservation> list = reservationdao.seeAllReservations();
		Reservation target = null;
		
		for(Reservation r : list) {
			if(r.getReservationId() == reservationId) {
				target=r;
				break;
			}
		}
		
		if(target != null) {
			roomdao.updateRoomAvailability(target.getRoomId(), true);
		}
		return true;
	}
	
	//VIEW RESERVATIONS BY PHONE NUMBER
	public List<Reservation> getReservationsByGuest(String phone){
		return reservationdao.getReservationsByPhone(phone);
	}
	
	//VIEW ALL ROOMS
	public List<Room> getAllRooms(){
		return roomdao.seeAllRooms();
	}
	
	//VIEW ALL RESERVATIONS
	public List<Reservation> getAllReservations(){
		return reservationdao.seeAllReservations();
	}
}
