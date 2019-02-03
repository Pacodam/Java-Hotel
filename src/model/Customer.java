package model;

import java.util.List;

import utils.RoomServices;

public class Customer {
   
	private String dni;
	private int numPeople;
	private List<RoomServices> roomServices;
	private Room room;
	
	public Customer() {}
	public Customer(String dni, int nP, List<RoomServices> rS) {
		
		this.dni = dni;
		this.numPeople = nP;
		this.roomServices = rS;
	}
	
	 public void setRoom(Room room) {
    	 this.room = room;
     }
     
     public Room getRoom() {
    	 return room;
     }

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	
	
	
     
     
}
