package main;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.HotelManager;
import exceptions.HotelException;
import model.Room;
import model.Customer;

import java.util.Iterator;
import java.util.Map;

public class HotelStucom {
	
	public static final String LINE = "========================";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String RESET = "\033[0m";  // Text Reset
	
	private static HotelManager hotel;

	public static void main(String[] args) {
		
		
		hotel = HotelManager.getInstance();
		//here starts the input data from standard input. After will be loaded with FileReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("Enter data:");
        System.out.println(ANSI_RED + "This text has red text but a default background!" + RESET);
        System.out.println("normal");
        
       
	       do {
	    	   try { 
        	     line = br.readLine();
	             String[] data = line.split(" ");
	             switch(data[0].toLowerCase()){
	                 case "speed":   //velocidad del thread size = 2
		                 testLength(data.length, 2, 2);
		                 break;
		                 
		             case "room":   //new room size = 4
		                 testLength(data.length, 3, 4);
		                 System.out.println(hotel.setRoom(data));
		                 break;
		                 
		             case "worker":  //new worker, size = 4
		            	 testLength(data.length, 4, 4);
		            	 System.out.println(hotel.setWorker(data));
		            	 break;
		            	 
		             case "reservation": //new reservation, size = 4
		            	 testLength(data.length, 3, 4);
		            	 System.out.println(hotel.setReservation(data));
		            	 if(hotel.getMoney() <= 1) {
		            		 System.out.println(LINE);
		            		 System.out.println("YOU'VE LOST ALL YOUR MONEY");
		            		 System.out.println(LINE);
		            	 }
		            	 break;
		            	 
		             case "hotel":
		            	 testLength(data.length, 1, 1);
		            	 showRooms();
		            	 break;
		            	 
		             //petitions to transfer to the thread manager later
		             case "problem":
		            	 testLength(data.length, 2, 2);
		            	 System.out.println(hotel.manageProblem(data));
		            	 break;
		            	 
		             case "request":
		            	 testLength(data.length, 3, 3);
		            	 System.out.println(hotel.manageRequest(data));
		            	 break;
		            	 
		             case "leave":
		            	 testLength(data.length, 3, 3);
		            	 System.out.println(hotel.manageLeave(data));
		            	 break;
		            	 
		             //case "reservation":
		            	 
		             default:
	                    throw new HotelException(HotelException.OPTION_UNALLOWED);   
		             }
                   
	            }catch(IOException | HotelException e) {
	    	       System.out.println(e.getMessage());
	            }      
	    }while(1 == 1);
	}
	
	
	public static void showRooms() {
		
		Map<String, Room> rooms = hotel.getRooms();
		System.out.println(rooms.size());
		if(rooms.isEmpty()) {
			System.out.println("There are no rooms at that moment");
		}
		else {
			System.out.println(LINE);
		    Iterator it = rooms.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getValue().toString());
		        
		    }
		    System.out.println(LINE);
		}
		
	} 
	
	
     /**
      * Test the required length for the input order.
      * @param length
     * @throws HotelException 
      * @throws MarvelException - Incorrect number of arguments.
      */
     public static void testLength(int l, int min, int max) throws HotelException{
         if(l < min || l > max){
             throw new HotelException(HotelException.INCORRECT_NUM_ARGS);
         }
     }

	

}
