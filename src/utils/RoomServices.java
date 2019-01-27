package utils;

import java.util.ArrayList;
import java.util.List;

import exceptions.HotelException;

public enum RoomServices {
   TV, BALCONY, BEDDOUBLE, JACUZZI, MINIBAR, PHONE, SATELLITE, SWEET;
   
	

	public static List<RoomServices> getServicesList(String[] list) throws HotelException {
		List<RoomServices> services = new ArrayList<>();
		for(String s: list) {
		  switch(s.toLowerCase()) {
		     case "tv":
		    	 services.add(RoomServices.TV);
		    	 break;
		     case "balcony":
		    	 services.add(RoomServices.BALCONY);
		    	 break;
		     case "beddouble":
		    	 services.add(RoomServices.BEDDOUBLE);
		    	 break;
		     case "jacuzzi":
		    	 services.add(RoomServices.JACUZZI);
		    	 break;	 
		     case "minibar":
		    	 services.add(RoomServices.MINIBAR);
		    	 break;
		     case "phone":
		    	 services.add(RoomServices.PHONE);
		    	 break;
		     case "satellite":
		    	 services.add(RoomServices.SATELLITE);
		    	 break;	
		     case "sweet":
		    	 services.add(RoomServices.SWEET);
		    	 break;
		     default:
		    	 System.out.println(s);
		    	 throw new HotelException(HotelException.WRONG_SERVICE);  
		  }
		}
		return services;
	}
}
