package model;

import java.util.ArrayList;
import java.util.List;

import utils.RoomServices;
import utils.RoomState;

public class Room implements Comparable<Room> {

	private String number;
	private int maxCapacity;
	private List<RoomServices> roomServices;
	private RoomState state;
	private Customer customer;
	private List<Worker> worker;
	private List<RoomServices> pendingRequests;
	
	public Room() {}
	public Room(String n, int maxCap, List<RoomServices> rS) {
		
		this.number = n;
		this.maxCapacity = maxCap;
		this.roomServices = rS;
		this.state = RoomState.CLEAN;
		this.customer = null;
		this.worker = new ArrayList<>();
		this.pendingRequests = new ArrayList<>();
	
	}
	
	public void setState(RoomState newState) {
		this.state = newState;
	}
	
	public RoomState getState() {
		return state;
	}
	
	public String getRoomNumber() {
		return number;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	public List<RoomServices> getRoomServices() {
		return roomServices;
	}
	public void setRoomServices(List<RoomServices> roomServices) {
		this.roomServices = roomServices;
	}
	public Customer getCustomer() {
		return customer;
	}
	
	
	public List<Worker> getWorker() {
		return worker;
	}
	public void setWorker(List<Worker> worker) {
		this.worker = worker;
	}
	
	public String getWorkerNames() {
		StringBuilder sb = new StringBuilder();
		for(Worker w: worker) {
			sb.append(w.getName() + ",");
		}
		return sb.toString();
	}
	
	public boolean containsService(RoomServices roomService) {
		for(RoomServices rs: roomServices) {
			if(roomService.equals(rs)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	public List<RoomServices> getPendingRequests() {
		return pendingRequests;
	}
	public void setPendingRequests(List<RoomServices> pendingRequests) {
		this.pendingRequests = pendingRequests;
	}
	public String customerToString() {
		if(customer != null) {
			return"CUSTOMER:"+ customer.getDni()+"("+customer.getNumPeople()+")";
		}
		return "EMPTY";
	}
	

	/*
	@Override
	public String toString() {
		return "Room [number=" + number + ", maxCapacity=" + maxCapacity + ", roomServices=" + roomServices + ", state="
				+ state + ", customer=" + customer + "]";
	}
	*/
	@Override
	public String toString() {
		return "== ROOM:" +  number + "   " + customerToString() + "  ==";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Room other = (Room) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Room o) {
		return maxCapacity - o.getMaxCapacity();
	}
	
	
	
	
	 
	    
	
	
}
