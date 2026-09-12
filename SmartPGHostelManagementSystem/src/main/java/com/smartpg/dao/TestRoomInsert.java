package com.smartpg.dao;
import com.smartpg.model.Room;
public class TestRoomInsert {

	public static void main(String[] args) {
		Room room=new Room();
		room.setRoomNumber("A101");
		room.setFloor(1);
		room.setRoomType("Single");
		room.setCapacity(1);
		room.setRent(5000);
		room.setStatus("Available");
		
		RoomDAO dao=new RoomDAO();
		
		dao.addRoom(room);

	}

}
