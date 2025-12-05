package com.hotel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

	// METHOD TO SAVE A RESERVATION
	public Reservation saveReservation(Reservation reservation) {
		String sql = "INSERT INTO reservations(room_id, guest_name, guest_phone, check_in, check_out, total_amount, cancelled) VALUES (?,?,?,?,?,?,?) RETURNING reservation_id";

		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, reservation.getRoomId());
			pstmt.setString(2, reservation.getGuestName());
			pstmt.setString(3, reservation.getGuestPhoneNo());
			pstmt.setDate(4, Date.valueOf(reservation.getCheckInDate()));
			pstmt.setDate(5, Date.valueOf(reservation.getCheckOutDate()));
			pstmt.setDouble(6, reservation.getTotalAmount());
			pstmt.setBoolean(7, reservation.isCancelled());

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int generatedId = rs.getInt(1);
				return new Reservation(generatedId, reservation.getRoomId(), reservation.getGuestName(),
						reservation.getGuestPhoneNo(), reservation.getCheckInDate(), reservation.getCheckOutDate(),
						reservation.getTotalAmount(), reservation.isCancelled());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// METHOD TO CANCEL THE RESERVATION
	public boolean cancelReservation(int reservationId) {
		String sql = "UPDATE reservations SET cancelled=true WHERE reservation_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, reservationId);
			int rows = pstmt.executeUpdate();
			return rows > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// METHOD TO GET ALL RESERVATIONS BY PHONE NUMBER
	public List<Reservation> getReservationsByPhone(String phone) {
		List<Reservation> list = new ArrayList<Reservation>();
		String sql = "SELECT * FROM reservations WHERE guest_phone=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, phone);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(mapRow(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private Reservation mapRow(ResultSet rs) throws Exception {
		return new Reservation(rs.getInt("reservation_id"), rs.getInt("room_id"), rs.getString("guest_name"),
				rs.getString("guest_phone"), rs.getDate("check_in").toLocalDate(),
				rs.getDate("check_out").toLocalDate(), rs.getDouble("total_amount"), rs.getBoolean("cancelled"));
	}

	// METHOD TO SEE ALL AVAILABLE ROOMS
	public List<Reservation> seeAllReservations() {
		List<Reservation> reservations = new ArrayList<Reservation>();
		String sql = "SELECT * FROM reservations ORDER BY reservation_id";

		try (Connection con = DBConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			while (rs.next()) {
				reservations.add(new Reservation(rs.getInt("reservation_id"), rs.getInt("room_id"),
						rs.getString("guest_name"), rs.getString("guest_phone"), rs.getDate("check_in").toLocalDate(),
						rs.getDate("check_out").toLocalDate(), rs.getDouble("total_amount"),
						rs.getBoolean("cancelled")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reservations;
	}
}
