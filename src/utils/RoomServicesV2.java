package utils;

import java.util.ArrayList;
import java.util.List;

import exceptions.HotelException;

public enum RoomServicesV2 {
	 TV, BALCONY, SPA, BEDDOUBLE, JACUZZI, MINIBAR, PHONE, SATELLITE, SWEET;
	
	public static List<RoomServicesV2> getServicesList(String[] list) throws HotelException {
		List<RoomServicesV2> services = new ArrayList<>();
		for(String s: list) {
		  switch(s.toLowerCase()) {
		     case "tv":
		    	 if(!services.contains(RoomServicesV2.TV)) {
		    	    services.add(RoomServicesV2.TV);
		    	 }
		    	 break;
		     case "balcony":
		    	 if(!services.contains(RoomServicesV2.BALCONY)) {
			    	    services.add(RoomServicesV2.BALCONY);
			     }
		    	 break;
		     case "beddouble":
		    	 if(!services.contains(RoomServicesV2.BEDDOUBLE)) {
			    	    services.add(RoomServicesV2.BEDDOUBLE);
			     }
		    	 break;
		     case "jacuzzi":
		    	 if(!services.contains(RoomServicesV2.JACUZZI)) {
			    	    services.add(RoomServicesV2.JACUZZI);
			     }
		    	 break;	 
		     case "minibar":
		    	 if(!services.contains(RoomServicesV2.MINIBAR)) {
			    	    services.add(RoomServicesV2.MINIBAR);
			     }
		    	 break;
		     case "phone":
		    	 if(!services.contains(RoomServicesV2.PHONE)) {
			    	    services.add(RoomServicesV2.PHONE);
			     }
		    	 break;
		     case "satellite":
		    	 if(!services.contains(RoomServicesV2.SATELLITE)) {
			    	    services.add(RoomServicesV2.SATELLITE);
			     }
		    	 break;	
		     case "sweet":
		    	 if(!services.contains(RoomServicesV2.SWEET)) {
			    	    services.add(RoomServicesV2.SWEET);
			     }
		    	 break;
		     case "spa":
		    	 if(!services.contains(RoomServicesV2.SPA)) {
			    	    services.add(RoomServicesV2.SPA);
			     }
		    	 break;
		    	 /*
		     default:
		    	 System.out.println(s);
		    	 throw new HotelException(HotelException.WRONG_SERVICE);  
		     */
		  }
		}
		return services;
	}
}
