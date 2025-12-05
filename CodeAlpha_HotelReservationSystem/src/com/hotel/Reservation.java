package com.hotel;

import java.time.LocalDate;

public class Reservation {
	private int reservationId;
	private int roomId;
	private String guestName;
	private String guestPhoneNo;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private double totalAmount;
	private boolean cancelled;

	public Reservation(int reservationId, int roomId, String guestName, String guestPhoneNo, LocalDate checkInDate,
			LocalDate checkOutDate, double totalAmount, boolean cancelled) {
		super();
		this.reservationId = reservationId;
		this.roomId = roomId;
		this.guestName = guestName;
		this.guestPhoneNo = guestPhoneNo;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.totalAmount = totalAmount;
		this.cancelled = cancelled;
	}

	public int getReservationId() {
		return reservationId;
	}

	public int getRoomId() {
		return roomId;
	}

	public String getGuestName() {
		return guestName;
	}

	public String getGuestPhoneNo() {
		return guestPhoneNo;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public String toString() {
		return "Reservation [reservationId=" + reservationId + ", roomId=" + roomId + ", guestName=" + guestName
				+ ", guestPhoneNo=" + guestPhoneNo + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", totalAmount=" + totalAmount + ", cancelled=" + cancelled + "]";
	}

}
