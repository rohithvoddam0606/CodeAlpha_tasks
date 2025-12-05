package com.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

	// METHOD TO SEE ALL THE ROOMS
	public List<Room> seeAllRooms() {
		List<Room> rooms = new ArrayList<Room>();
		String sql = "SELECT * FROM rooms ORDER BY room_id";

		try (Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				rooms.add(new Room(rs.getInt("room_id"), RoomType.valueOf(rs.getString("room_type")),
						rs.getDouble("price_per_night"), rs.getBoolean("is_available")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rooms;
	}

	// METHOD TO SEE ONLY AVAILABLE ROOMS
	public List<Room> seeAvailableRooms(RoomType type) {
		List<Room> rooms = new ArrayList<Room>();
		String sql = "SELECT * FROM rooms WHERE room_type=? AND is_available=true";

		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, type.name());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				rooms.add(new Room(rs.getInt("room_id"), type, rs.getDouble("price_per_night"),
						rs.getBoolean("is_available")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rooms;
	}

	// METHOD TO GET A ROOM BY ID
	public Room getRoomById(int id) {
		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM rooms WHERE room_id=?")) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				return new Room(id, RoomType.valueOf(rs.getString("room_type")), rs.getDouble("price_per_night"),
						rs.getBoolean("is_available"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// METHOD TO UPDATE THE AVAILABILITY OF ROOM
	public void updateRoomAvailability(int roomId, boolean available) {
		String sql = "UPDATE rooms SET available=? WHERE room_id=?";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setBoolean(1, available);
			pstmt.setInt(2, roomId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
