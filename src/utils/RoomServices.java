package utils;

import java.util.ArrayList;
import java.util.List;

import exceptions.HotelException;

public enum RoomServices {
   TV, BALCON, SPA, CAMADOBLE, JACUZZI, MINIBAR, TELEFONO, SATELITE, SWEET;
   
	

	public static List<RoomServices> getServicesList(String[] list) throws HotelException {
		List<RoomServices> services = new ArrayList<>();
		for(String s: list) {
		  switch(s.toLowerCase()) {
		     case "tv":
		    	 if(!services.contains(RoomServices.TV)) {
		    	    services.add(RoomServices.TV);
		    	 }
		    	 break;
		     case "balcon":
		    	 if(!services.contains(RoomServices.BALCON)) {
			    	    services.add(RoomServices.BALCON);
			     }
		    	 break;
		     case "camadoble":
		    	 if(!services.contains(RoomServices.CAMADOBLE)) {
			    	    services.add(RoomServices.CAMADOBLE);
			     }
		    	 break;
		     case "jacuzzi":
		    	 if(!services.contains(RoomServices.JACUZZI)) {
			    	    services.add(RoomServices.JACUZZI);
			     }
		    	 break;	 
		     case "minibar":
		    	 if(!services.contains(RoomServices.MINIBAR)) {
			    	    services.add(RoomServices.MINIBAR);
			     }
		    	 break;
		     case "telefono":
		    	 if(!services.contains(RoomServices.TELEFONO)) {
			    	    services.add(RoomServices.TELEFONO);
			     }
		    	 break;
		     case "satelite":
		    	 if(!services.contains(RoomServices.SATELITE)) {
			    	    services.add(RoomServices.SATELITE);
			     }
		    	 break;	
		     case "sweet":
		    	 if(!services.contains(RoomServices.SWEET)) {
			    	    services.add(RoomServices.SWEET);
			     }
		    	 break;
		     case "spa":
		    	 if(!services.contains(RoomServices.SPA)) {
			    	    services.add(RoomServices.SPA);
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
