package controller;

import java.util.List;

import model.Customer;
import model.Room;
import model.Worker;

public class HotelManager {

	
	private static double money;
	//hay que poner el tipo de Collection adecuado a cada caso
	private static List<Customer> customers;
	private static List<Worker> workers;
	private static List<Room> rooms;
	
	private HotelManager() {}
	
	private static final HotelManager instance = new HotelManager();
	
	public static HotelManager getInstance() { return instance; }
	
	public void loadHotelData() {
		
	}
	
}
