package main;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.HotelManager;
import exceptions.HotelException;
import model.Room;
import model.Customer;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import concurrence.ThreadManager;

public class HotelStucom {
	
	private static final String SEPARATOR = File.separator;
    private static final File INPUT_FILE = new File("loadHotel1.txt");
	private static final String LINE = "\\u001B31;1mh========================";
	
	private static HotelManager hotel;
	private static Runnable myRunnable;
	private static Thread myThread;
	public static void main(String[] args) {
		
		BufferedReader br = null;
		hotel = HotelManager.getInstance();
		
		  //loading hotel from input file, using FileReader
          //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
          try {
			br = new BufferedReader(new FileReader(INPUT_FILE));
		  } catch (FileNotFoundException e) {
			e.getMessage();
		  }
          
          String line;
          double money = hotel.getMoney();
          try {
            while ((line = br.readLine()) != null || money > 0) {
            	System.out.println("      " + line);
        	  try {
        		  
	              String[] data = line.split(" ");
	              checkOrders(data);
	             
               }catch(HotelException e) {
    	          System.out.println(e.getMessage());
	           }
        	  money = hotel.getMoney();
          }
          }catch(IOException e) {
        	  System.out.println(e.getMessage());
          }
          
          
          initConsole();
            
	}

    public static void checkOrders(String[] data) throws HotelException {
    	switch(data[0].toLowerCase()){
        
        case "speed":   //velocidad del thread size = 2
            testLength(data.length, 2, 2);
            int milis = testNumeric(data[1]);
            myRunnable = new ThreadManager(milis);
            myThread = new Thread(myRunnable);
            myThread.start();
            break;
            
        case "room":   //new room size = 3 o 4
            testLength(data.length, 3, 4);
            System.out.println(hotel.setRoom(data));
            break;
            
        case "worker":  //new worker, size = 4
       	 testLength(data.length, 4, 4);
       	 System.out.println(hotel.setWorker(data));
       	 break;
       	 
        case "reservation": //new reservation, size = 3 o 4
       	 testLength(data.length, 3, 4);
       	 System.out.println(hotel.setReservation(data));
       	 //mejor con una exception, que se verifique en un método cuando toque
       	 if(hotel.getMoney() < 1) {
       		 System.out.println(LINE);
       		 System.out.println("YOU'VE LOST ALL YOUR MONEY");
       		 System.out.println(LINE);
       	 }
       	 break;
       	 
        case "hotel":
       	 testLength(data.length, 1, 1);
       	 System.out.println(LINE);
       	 System.out.println(hotel.showRooms());
       	 System.out.println(LINE);
       	 break;
       	   
        default:
           throw new HotelException(HotelException.OPTION_UNALLOWED);   
        }       
    }
    
    
    
	public static void initConsole() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("Enter data:");
        
	       do {
	    	   try { 
        	     line = br.readLine();
	             String[] data = line.split(" ");
	             //checkOrders(data);
	             switch(data[0].toLowerCase()){
                 case "speed":   //velocidad del thread size = 2
	                 testLength(data.length, 2, 2);
	                 break;
	                 
	             case "room":   //new room size = 3 o 4
	                 testLength(data.length, 3, 4);
	                 System.out.println(hotel.setRoom(data));
	                 break;
	                 
	             case "worker":  //new worker, size = 4
	            	 testLength(data.length, 4, 4);
	            	 System.out.println(hotel.setWorker(data));
	            	 break;
	            	 
	             case "reservation": //new reservation, size = 3 o 4
	            	 testLength(data.length, 3, 4);
	            	 System.out.println(hotel.setReservation(data));
	            	 //mejor con una exception, que se verifique en un método cuando toque
	            	 if(hotel.getMoney() < 1) {
	            		 System.out.println(LINE);
	            		 System.out.println("YOU'VE LOST ALL YOUR MONEY");
	            		 System.out.println(LINE);
	            	 }
	            	 break;
	            	 
	             case "hotel":
	            	 testLength(data.length, 1, 1);
	            	 System.out.println(LINE);
	            	 System.out.println(hotel.showRooms());
	            	 System.out.println(LINE);
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

     public static int testNumeric(String milisec) throws HotelException {
    	 int milis;
    	 try {
    		milis = Integer.parseInt(milisec); 
    	 }catch(NumberFormatException e) {
    		 throw new HotelException(HotelException.MILIS_NUMERIC);
    	 }
    	 return milis;
     }
	

}

/*TODO THINGS:
 * 
 * ¿Que las rooms esten ordenadas?
 * Una vez funcione todo más o menos, pasar los comandos a filereader y las peticiones al thread
 * Poner en color rojo al mostrar el hotel
 * 
 * 
 */
