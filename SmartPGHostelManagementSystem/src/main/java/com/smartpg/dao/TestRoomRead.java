package com.smartpg.dao;

import java.util.List;
import com.smartpg.model.Room;

public class TestRoomRead {

	public static void main(String[] args) {


		RoomDAO dao = new RoomDAO();
		List<Room> rooms = dao.getAllRooms();

		for (Room r : rooms) {

		    System.out.println(
		        r.getRoomId() + " " +
		        r.getRoomNumber() + " " +
		        r.getFloor() + " " +
		        r.getRoomType() + " " +
		        r.getCapacity() + " " +
		        r.getRent() + " " +
		        r.getStatus()
		    );
		}
	}
}