package main;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.HotelManager;
import exceptions.HotelException;

public class HotelStucom {
	
	//private static HotelManager hotel;
	//private static String[] input;

	public static void main(String[] args) {
		
		
		HotelManager hotel = HotelManager.getInstance();
		//here starts the input data from standard input. After will be loaded with FileReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("Enter data:");
       
	       do {
	    	   try { 
        	     line = br.readLine();
        	     System.out.println(line);
	             String[] data = line.split(" ");
	             switch(data[0].toLowerCase()){
	                 case "speed":   //velocidad del thread size = 2
		                 testLength(data.length, 2);
		                 break;
		             case "room":   //new room size = 4
		                 testLength(data.length, 4);
		                 hotel.setRoom(data);
		                 break;
		             case "worker":  //new worker, size = 4
		            	 testLength(data.length, 4);
		            	 hotel.setWorker(data);
		            	 break;
		             case "reservation": //new reservation, sieze = 4
		            	 testLength(data.length, 4);
		            	 hotel.setReservation(data);
		            	 break;
		             default:
	                    throw new HotelException(HotelException.OPTION_UNALLOWED);   
		             }
                   
	            }catch(IOException | HotelException e) {
	    	       System.out.println(e.getMessage());
	            }      
	    }while(1 == 1);
	}
	
                 
     /**
      * Test the required length for the input order.
      * @param length
     * @throws HotelException 
      * @throws MarvelException - Incorrect number of arguments.
      */
     public static void testLength(int l, int length) throws HotelException{
         if(l != length){
             throw new HotelException(HotelException.INCORRECT_NUM_ARGS);
         }
     }

	

}
