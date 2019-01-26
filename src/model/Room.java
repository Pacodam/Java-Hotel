package model;

import java.util.List;

import utils.RoomServices;

public class Room {

	private String number;
	private int maxCapacity;
	private List<RoomServices> roomServices;
	
	public Room(String n, int maxCap, List<RoomServices> rS) {
		
		this.number = n;
		this.maxCapacity = maxCap;
		this.roomServices = rS;
	
	}
}
