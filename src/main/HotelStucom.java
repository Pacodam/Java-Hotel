package main;

import java.io.IOException;

import controller.HotelManager;

public class HotelStucom {
	
	private static HotelManager hotel;
	private static String[] input;

	public static void main(String[] args) {
		
		hotel = HotelManager.getInstance();
		//here starts the input data from standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println(       "***MARVEL STUCOM JAVA***");
        System.out.println("Please, paste data and press return:");
        do{
           try{
             line = br.readLine();
             input = line.split(" ");
             if(input.length == 0){
                 System.out.println("You entered no data");
             }
             switch(input[0].toLowerCase()){
             case "speed":   //registro. length 4, no login
                 testLength(1);
                 
                 break;
             case "room":   //ver superheroes, length 1 , no login
                 testLength(4);
                 //hotel.setRoom(input);
                 break;
             case "worker":
            	 //hotel.setWorker(input);
            	 break;
             case "hotel":
            	 
		
		
                 
     /**
      * Test the required length for the input order.
      * @param length
      * @throws MarvelException - Incorrect number of arguments.
      */
     public static void testLength(int length){
         if(input.length != length){
            // throw new MarvelException(MarvelException.INCORRECT_NUM_ARGS);
         }
     }

	

}
