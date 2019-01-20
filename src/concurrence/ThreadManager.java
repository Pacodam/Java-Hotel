package concurrence;

import controller.HotelManager;

public class ThreadManager implements Runnable {
	
	private HotelManager hotelManager;
	private int milis;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}



/* public class ThreadManager implements runnable
private HotelManager;
private int milis;
En esta clase es donde estará la lógica del thread. 

luego cuando en main hago threadManager.run() y lo instancio. Podria instanciar
25 threads.

enum
public enum CrewSercices{
MANTENIMIENTO, ETC
public static CrewServices selectService(String extra){
switch(extra.toUppercase(){
   case("MANTENIMIENTO"):
          RETURN crewServices.MANTENIMIENTO;
case ...
*/