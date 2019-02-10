package concurrence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import controller.HotelManager;
import exceptions.HotelException;


public class ThreadManager implements Runnable {
	
	private static final String SEPARATOR = File.separator;
    private static final File INPUT_FILE = new File("inputOrders1.txt");
	private HotelManager hotel;
	private int milis;
	
	public ThreadManager(int milis) {
		this.milis = milis;
	}

	@Override
	public void run() {
		
		BufferedReader br = null;
        try {
		   br = new BufferedReader(new FileReader(INPUT_FILE));
		} catch (FileNotFoundException e) {
		   e.getMessage();
	    }
        
        hotel = HotelManager.getInstance();
        String line;
        try {
          //dejamos pasar unos segundos hasta que se inicie el thread
          Thread.sleep(5000);
          while ((line = br.readLine()) != null) {
          	//System.out.println(" thread!!     " + line);
      	    try {
	             String[] data = line.split(" ");
	             switch(data[0].toLowerCase()){
	             
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
		            	 
		             case "reservation": //new reservation, size = 3 o 4
		            	 testLength(data.length, 3, 4);
		            	 System.out.println(hotel.setReservation(data));
		            	 //mejor con una exception, que se verifique en un método cuando toque
		            	 if(hotel.getMoney() < 1) {
		            		 //System.out.println(LINE);
		            		 System.out.println("YOU'VE LOST ALL YOUR MONEY");
		            		// System.out.println(LINE);
		            	 }
		            	 break;
		            	    
		             default:
	                    throw new HotelException(HotelException.OPTION_UNALLOWED);   
		             }       
             }catch(HotelException e) {
  	          System.out.println(e.getMessage());
	         }
      	    Thread.sleep(milis);
           } 
        }catch(IOException | InterruptedException e) {
      	  System.out.println(e.getMessage());
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



