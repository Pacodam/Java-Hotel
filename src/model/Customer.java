package model;

import java.util.List;

import utils.RoomServices;

public class Customer {
   
	private String dni_numeric;
	private int numPeople;
	private List<RoomServices> roomServices;
	
	public Customer(String dN, int nP, List<RoomServices> rS) {
		
		this.dni_numeric = dN;
		this.numPeople = nP;
		this.roomServices = rS;
	}
}
