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
import colors.ConsoleColors;

import concurrence.ThreadManager;

public class HotelStucom {
	
	private static final String SEPARATOR = File.separator;
    private static final File INPUT_FILE = new File("loadHotel1.txt");
	private static final String LINE = "========================";
	
	private static ConsoleColors color;
	
	private static HotelManager hotel;
	private static Runnable myRunnable;
	private static Thread myThread;
	public static void main(String[] args) {
		
		
	
		
    
		
		BufferedReader br = null;
		hotel = HotelManager.getInstance();
		
		  /*
		   * Al iniciarse la aplicación, leemos el fichero de comandos de carga del hotel.
		   * Vamos recorriendo todas las lineas del fichero y llamando a los métodos del
		   * HotelManager encargados de gestionar los comandos. 
		   * No tenemos en cuenta en esta parte los resultados de dinero en el Hotel (ello se hará
		   * en el thread).
		   * Cuando finaliza la lectura del fichero, se inicia la posibilidad de introducir comandos
		   * por teclado.
		   * El thread está programado para que se inicie en unos 5 segundos, para que de tiempo
		   * de cargarse los comandos iniciales.
		   */
         
		
		  //Lectura del fichero
          try {
			br = new BufferedReader(new FileReader(INPUT_FILE));
		  } catch (FileNotFoundException e) {
			e.getMessage();
		  }
          
          //Lectura de las lineas del fichero
          String line;
  
          try {
            while ((line = br.readLine()) != null ) {
            	
        	  try {
	              String[] data = line.split(" ");
	              checkOrders(data);
	             
               }catch(HotelException e) {
    	          System.out.println(e.getMessage());
	           }
        	 
          }
          }catch(IOException e) {
        	  System.out.println(e.getMessage());
          }
          
          
          initConsole();
            
	}

	/**
	 * El metodo recibe un String[] y ejecuta el comando de Hotel contenido en la posición 0
	 * @param data String[]
	 * @throws HotelException
	 */
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
       	 System.out.println(color.RED_BOLD_BRIGHT + LINE + color.RESET);
       	 System.out.println(hotel.showRooms());
       	 System.out.println(color.RED_BOLD + LINE + color.RESET);
       	 break;
       	   
        default:
           throw new HotelException(HotelException.OPTION_UNALLOWED);   
        }       
    }
    
    
    /**
     *Los comandos se pueden introducir por teclado, una vez se han cargado los datos
     *del fichero de entrada
     */
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


