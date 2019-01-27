package model;

import java.util.List;

import utils.RoomServices;

public class Customer {
   
	private int dni_numeric;
	private int numPeople;
	private List<RoomServices> roomServices;
	private Room room;
	
	public Customer(int dN, int nP, List<RoomServices> rS) {
		
		this.dni_numeric = dN;
		this.numPeople = nP;
		this.roomServices = rS;
	}
	
	 public void setRoom(Room room) {
    	 this.room = room;
     }
     
     public Room getRoom() {
    	 return room;
     }

	public int getDni_numeric() {
		return dni_numeric;
	}

	public void setDni_numeric(int dni_numeric) {
		this.dni_numeric = dni_numeric;
	}

	public int getNumPeople() {
		return numPeople;
	}

	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}

	public List<RoomServices> getRoomServices() {
		return roomServices;
	}

	public void setRoomServices(List<RoomServices> roomServices) {
		this.roomServices = roomServices;
	}
     
     
}
