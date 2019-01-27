package controller;

import java.util.Map;

import model.Customer;
import model.Room;

public class RoomManager {

	private static HotelManager hotel = HotelManager.getInstance();
	public RoomManager() {}
	
	public Room findRoomForReservation(Customer customer) {
		Room room = new Room();
		Map<String, Room> rooms = hotel.getRooms();
		
		
		return room;
		
	}
}
