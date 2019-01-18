package model;

import java.util.List;

import utils.RoomServices;

public class Room {

	private int number;
	private int maxCap;
	private List<RoomServices> roomServices;
	
	public Room(int n, int mC, List<RoomServices> rS) {
		
		this.number = n;
		this.maxCap = mC;
		this.roomServices = rS;
	
	}
}
