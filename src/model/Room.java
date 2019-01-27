package model;

import java.util.List;

import utils.RoomServices;
import utils.RoomState;

public class Room {

	private String number;
	private int maxCapacity;
	private List<RoomServices> roomServices;
	private RoomState state;
	private Customer customer;
	
	public Room() {}
	public Room(String n, int maxCap, List<RoomServices> rS) {
		
		this.number = n;
		this.maxCapacity = maxCap;
		this.roomServices = rS;
		this.state = RoomState.CLEAN;
	
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
	
	public boolean containsService(RoomServices roomService) {
		for(RoomServices rs: roomServices) {
			if(roomService.equals(rs)) {
				return true;
			}
		}
		return false;
	}
	
	public String customerToString() {
		if(customer != null) {
			return"CUSTOMER:"+ customer.getDni_numeric()+"("+customer.getNumPeople()+")";
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
	
	
}
