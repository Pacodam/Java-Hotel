package main;

import java.io.IOException;

import controller.HotelManager;

public class HotelStucom {
	
	private static HotelManager hotel;

	public static void main(String[] args) {
		
		hotel = HotelManager.getInstance();
		hotel.loadHotelData();
		

	}

}
