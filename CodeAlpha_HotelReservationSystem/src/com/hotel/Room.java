package com.hotel;

public class Room {
	private int roomId;
	private RoomType roomType;
	private double pricePerNight;
	private boolean isAvailable;

	public Room(int roomId, RoomType roomType, double pricePerNight, boolean isAvailable) {
		this.roomId = roomId;
		this.roomType = roomType;
		this.pricePerNight = pricePerNight;
		this.isAvailable = isAvailable;
	}

	public int getRoomId() {
		return roomId;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomType=" + roomType + ", pricePerNight=" + pricePerNight
				+ ", isAvailable=" + isAvailable + "]";
	}

}
