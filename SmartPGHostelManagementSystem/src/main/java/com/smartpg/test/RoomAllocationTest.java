package com.smartpg.test;

import com.smartpg.dao.RoomAllocationDAO;
import com.smartpg.model.RoomAllocation;

import java.util.List;

public class RoomAllocationTest {

    public static void main(String[] args) {

        RoomAllocationDAO dao = new RoomAllocationDAO();

        RoomAllocation allocation = new RoomAllocation();

        allocation.setStudentId(6);
        allocation.setRoomId(4);
        allocation.setAllocationDate("2026-08-03");
        allocation.setEndDate(null);
        allocation.setStatus("Active");
        
        boolean result = dao.addRoomAllocation(allocation);

        System.out.println(result);
        
        List<RoomAllocation> list = dao.getAllRoomAllocations();

        for(RoomAllocation a : list) {

            System.out.println(
                a.getAllocationId() + " " +
                a.getStudentId() + " " +
                a.getRoomId() + " " +
                a.getStatus()
            );

        }
        RoomAllocation updateAllocation = new RoomAllocation();

        updateAllocation.setAllocationId(8);
        updateAllocation.setEndDate("2026-08-10");
        updateAllocation.setStatus("Completed");
        
        
        boolean updateResult = dao.updateRoomAllocation(updateAllocation);

        System.out.println(updateResult);
        
        boolean deleteResult = dao.deleteRoomAllocation(8);

        System.out.println(deleteResult);
    }

}