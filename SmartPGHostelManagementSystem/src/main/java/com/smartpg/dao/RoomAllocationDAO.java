package com.smartpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import com.smartpg.model.RoomAllocation;
import com.smartpg.util.DBConnection;

public class RoomAllocationDAO {

    // CREATE - Add Room Allocation
    public boolean addRoomAllocation(RoomAllocation allocation) {

        boolean status = false;

        String query = "INSERT INTO room_allocation(Student_ID, Room_ID, Allocation_Date, End_Date, Status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, allocation.getStudentId());
            ps.setInt(2, allocation.getRoomId());
            ps.setString(3, allocation.getAllocationDate());
            ps.setString(4, allocation.getEndDate());
            ps.setString(5, allocation.getStatus());

            int result = ps.executeUpdate();

            System.out.println("Result value: " + result);

            if (result > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // READ - Get All Room Allocations
    public List<RoomAllocation> getAllRoomAllocations() {

        List<RoomAllocation> list = new ArrayList<>();

        String query = "SELECT * FROM room_allocation";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                RoomAllocation allocation = new RoomAllocation();

                allocation.setAllocationId(rs.getInt("Allocation_ID"));
                allocation.setStudentId(rs.getInt("Student_ID"));
                allocation.setRoomId(rs.getInt("Room_ID"));
                allocation.setAllocationDate(rs.getString("Allocation_Date"));
                allocation.setEndDate(rs.getString("End_Date"));
                allocation.setStatus(rs.getString("Status"));

                list.add(allocation);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // VIEW ROOM ALLOCATION(S) FOR ONE STUDENT (Student portal).
    // studentId must come from the authenticated session, never from a
    // request parameter, so a student can only see their own allocation.
    public List<RoomAllocation> getAllocationsByStudentId(int studentId) {

        List<RoomAllocation> list = new ArrayList<>();

        String query = "SELECT * FROM room_allocation WHERE Student_ID=? ORDER BY Allocation_Date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, studentId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    RoomAllocation allocation = new RoomAllocation();

                    allocation.setAllocationId(rs.getInt("Allocation_ID"));
                    allocation.setStudentId(rs.getInt("Student_ID"));
                    allocation.setRoomId(rs.getInt("Room_ID"));
                    allocation.setAllocationDate(rs.getString("Allocation_Date"));
                    allocation.setEndDate(rs.getString("End_Date"));
                    allocation.setStatus(rs.getString("Status"));

                    list.add(allocation);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    public RoomAllocation getAllocationById(int allocationId) {

        RoomAllocation allocation = null;

        String query = "SELECT * FROM room_allocation WHERE Allocation_ID=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, allocationId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    allocation = new RoomAllocation();

                    allocation.setAllocationId(rs.getInt("Allocation_ID"));
                    allocation.setStudentId(rs.getInt("Student_ID"));
                    allocation.setRoomId(rs.getInt("Room_ID"));
                    allocation.setAllocationDate(rs.getString("Allocation_Date"));
                    allocation.setEndDate(rs.getString("End_Date"));
                    allocation.setStatus(rs.getString("Status"));
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return allocation;
    }

    public boolean updateRoomAllocation(RoomAllocation allocation) {

        boolean status = false;

        String query = "UPDATE room_allocation SET End_Date=?, Status=? WHERE Allocation_ID=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, allocation.getEndDate());
            ps.setString(2, allocation.getStatus());
            ps.setInt(3, allocation.getAllocationId());

            int result = ps.executeUpdate();

            if (result > 0) {
                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }

    public boolean deleteRoomAllocation(int allocationId) {

        boolean status = false;

        String query = "DELETE FROM room_allocation WHERE Allocation_ID=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, allocationId);

            int result = ps.executeUpdate();

            if (result > 0) {
                status = true;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}
