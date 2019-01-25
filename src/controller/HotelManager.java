package controller;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Room;
import model.Worker;
import utils.RoomServices;

public class HotelManager {

	
	private static double money;
	
	private static List<Room> rooms;
	private static List<Worker> workers;
	private static List<Customer> customers;
	
	private HotelManager() {}
	
	private static final HotelManager instance = new HotelManager();
	
	public static HotelManager getInstance() { return instance; }
	

    public static void setRoom(String[] data) {
    	String number = data[1];
    	int maxCapacity = Integer.parseInt(data[2]);
    	String[] services = data[3].split(",");
    	List<RoomServices> roomServices = RoomServices.getServicesList(services);
    }
    
    public void setWorker(String[] data) {
    	System.out.println(RoomServices.JACUZZI);
    }
    
    public void setReservation(String[] data) {
    	
    }
	
}
