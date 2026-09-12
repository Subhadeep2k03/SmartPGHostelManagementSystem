package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smartpg.model.Room;
import com.smartpg.util.DBConnection;

public class RoomDAO {

    // ADD ROOM
    public void addRoom(Room room) {

        String sql = "INSERT INTO room(room_number, floor, room_type, capacity, rent, status) VALUES(?,?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getFloor());
            ps.setString(3, room.getRoomType());
            ps.setInt(4, room.getCapacity());
            ps.setInt(5, room.getRent());
            ps.setString(6, room.getStatus());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Room Added Successfully");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // VIEW ALL ROOMS
    public List<Room> getAllRooms() {

        List<Room> rooms = new ArrayList<>();

        String sql = "SELECT * FROM room";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Room room = new Room();

                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setFloor(rs.getInt("floor"));
                room.setRoomType(rs.getString("room_type"));
                room.setCapacity(rs.getInt("capacity"));
                room.setRent(rs.getInt("rent"));
                room.setStatus(rs.getString("status"));

                rooms.add(room);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rooms;
    }

    // VIEW AVAILABLE ROOMS (Student portal - shared, non-private data;
    // every logged-in student is allowed to see this, unlike payments,
    // allocations, visitors and complaints which are per-student).
    public List<Room> getAvailableRooms() {

        List<Room> rooms = new ArrayList<>();

        String sql = "SELECT * FROM room WHERE status='Available'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Room room = new Room();

                room.setRoomId(rs.getInt("room_id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setFloor(rs.getInt("floor"));
                room.setRoomType(rs.getString("room_type"));
                room.setCapacity(rs.getInt("capacity"));
                room.setRent(rs.getInt("rent"));
                room.setStatus(rs.getString("status"));

                rooms.add(room);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rooms;
    }

    // GET ROOM BY ID FOR EDIT
    public Room getRoomById(int roomId) {

        Room room = null;

        String sql = "SELECT * FROM room WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    room = new Room();

                    room.setRoomId(rs.getInt("room_id"));
                    room.setRoomNumber(rs.getString("room_number"));
                    room.setFloor(rs.getInt("floor"));
                    room.setRoomType(rs.getString("room_type"));
                    room.setCapacity(rs.getInt("capacity"));
                    room.setRent(rs.getInt("rent"));
                    room.setStatus(rs.getString("status"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return room;
    }

    // UPDATE ROOM
    public void updateRoom(Room room) {

        String sql = "UPDATE room SET room_number=?, floor=?, room_type=?, capacity=?, rent=?, status=? WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, room.getRoomNumber());
            ps.setInt(2, room.getFloor());
            ps.setString(3, room.getRoomType());
            ps.setInt(4, room.getCapacity());
            ps.setInt(5, room.getRent());
            ps.setString(6, room.getStatus());
            ps.setInt(7, room.getRoomId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Room Updated Successfully");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // DELETE ROOM
    public void deleteRoom(int roomId) {

        String sql = "DELETE FROM room WHERE room_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, roomId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Room Deleted Successfully");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}
