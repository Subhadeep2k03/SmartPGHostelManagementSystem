package com.smartpg.dao;


import com.smartpg.model.Room;

public class TestRoomUpdate {

	public static void main(String[] args) {
		Room room = new Room();
		room.setRoomId(1);

		
		room.setRoomNumber("A102");
		room.setFloor(1);
		room.setRoomType("Double");
		room.setCapacity(2);
		room.setRent(7000);
		room.setStatus("Occupied");
		
		RoomDAO dao = new RoomDAO();

		dao.updateRoom(room);
	}

}
