package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import exceptions.HotelException;
import model.Customer;
import model.Room;
import model.Worker;
import utils.RoomServices;
import utils.WorkersSkills;


public class HotelManager {

	
	private static int money = 1000;
	
	private static RoomManager roomMng;
	private static WorkerManager workerMng;
	
	private static Map<String, Room> rooms = new HashMap<>();
	
  
	
	private HotelManager() {
		roomMng = new RoomManager();
		workerMng = new WorkerManager();
	}
	private static final HotelManager instance = new HotelManager();
	public static HotelManager getInstance() { return instance; }
	
	
	/**
	 * Exceptions to check: the room exists; exists a customer.
	 * The customer must be moved to another one. If not possible, the customer is cancelled
	 * Room must be setted to BROKEN
	 * @param data
	 * @return String
	 */
	public String manageProblem(String [] data) {
		
		return "h";
	}
	
	/**
	 * Exceptions to check: the room exists.
	 * Find the worker with skills needed. If a petition is not accomplished, save for later.
	 * @param data
	 * @return String
	 */
    public String manageRequest(String [] data) {
		
		return "h";
	}
    
    /**
     * Exceptions to check: room exists, customer exists inside.
     * Room pass to UNCLEAN
     * @param data
     * @return String
     */
    public String manageLeave(String [] data) {
		
		return "h";
	}
	
    /**
     * Condiciones: num habitación de 3 digitos. No se puede repetir. Num maximo ocupantes obligatorio.
     * Puede no tener campo servicios incluidos. 
     * Las guardamos en un TreeMap para que esten ordenados los numeros de habitación.
     * @param data String[]
     * @throws HotelException
     */
    public String setRoom(String[] data) throws HotelException {
    	
    	String number = data[1];
    	if(number.length() != 3) {
    		throw new HotelException(HotelException.WRONG_ROOM_NUM_FORMAT);
    	}
    	if(rooms.containsKey(number)) {
    		throw new HotelException(HotelException.ROOM_NUMBER_EXISTS);
    	}
    	
        int maxCapacity;
    	try {
    	  maxCapacity = Integer.parseInt(data[2]);
    	}catch(NumberFormatException e) {
    		throw new HotelException(HotelException.INCORRECT_OCCUPANTS_NUM);
    	}
    	if(maxCapacity < 1 || maxCapacity > 8) {
    		throw new HotelException(HotelException.INCORRECT_OCCUPANTS_NUM);
    	}
    	
    	String[] services;
    	List<RoomServices> roomServices;
    	if(data.length == 4) {
    	  services = data[3].split(",");
    	  roomServices = RoomServices.getServicesList(services);
    	}
    	else {
    		roomServices = new ArrayList<>();
    	}
    	Room room = new Room(number, maxCapacity, roomServices);
    	rooms.put(number, room);
    	return "--> new Room added " + room.getRoomNumber() + " <-- ";
    }
    
    /**
     * Condiciones: numero dni válido. Nombre. Minimo una habilidad de las requeridas.
     * @param data String[]
     * @throws HotelException
     */
    public String setWorker(String[] data) throws HotelException {
    	int dniNumeric;
    	try {
    	  dniNumeric = Integer.parseInt(data[1]);
    	  if(data[1].length() != 8) {
    		  throw new HotelException(HotelException.DNI_INCORRECT_SIZE);
    	  }
    	}catch(NumberFormatException e) {
    		throw new HotelException(HotelException.DNI_INCORRECT_NUM);
    	}
    	String name = data[2];
    	String[] skills = data[3].split(",");
    	List<WorkersSkills> workerSkills = WorkersSkills.getSkillsList(skills);
    	if(workerSkills.isEmpty()) {
    		throw new HotelException(HotelException.WITHOUT_SKILLS);
    	}
    	Worker worker = new Worker(dniNumeric, name, workerSkills);	
    	return "--> new Worker added " + getDNIfull(data[1]) + " <-- ";
    }
    
    /**
     * Condiciones: numero dni valido. Numero personas. Requisitos habitación no obligados.
     * @param data
     * @throws HotelException 
     */
    public String setReservation(String[] data) throws HotelException {
    	int dniNumeric;
    	try {
    	  dniNumeric = Integer.parseInt(data[1]);
    	  if(data[1].length() != 8) {
    		  throw new HotelException(HotelException.DNI_INCORRECT_SIZE);
    	  }
    	}catch(NumberFormatException e) {
    		throw new HotelException(HotelException.DNI_INCORRECT_NUM);
    	}
    	
    	int numOcupants;
     	try {
     	  numOcupants = Integer.parseInt(data[2]);
     	}catch(NumberFormatException e) {
     		throw new HotelException(HotelException.INCORRECT_OCCUPANTS_NUM);
     	}
     	if(numOcupants < 1 || numOcupants > 8) {
     		throw new HotelException(HotelException.INCORRECT_OCCUPANTS_NUM);
     	}
     	
     	String[] services;
    	List<RoomServices> roomServices;
    	if(data.length == 4) {
    	  services = data[3].split(",");
    	  roomServices = RoomServices.getServicesList(services);
    	}
    	else {
    		roomServices = new ArrayList<>();
    	}
     	
     	Customer customer = new Customer(dniNumeric, numOcupants, roomServices);
     	Room room = roomMng.findRoomForReservation(customer);
     	
     	if(room == null) {
     		money -= 100;
     		return "--> Customer not assigned. You loose 100 <--";
     		
     	}
     	
     	
     	return "--> Assigned " + getDNIfull(data[1]) + " to Room " + customer.getRoom()+ " <-- ";
     	
     	
    }
    
    
    public static String getDNIfull(String dni) {
        String characters="TRWAGMYFPDXBNJZSQVHLCKE";
        int modulo= Integer.parseInt(dni) % 23;
        char letra = characters.charAt(modulo);
        return dni + letra; 
        } 
    
    public Map getRooms() {
    	return rooms;
    }
    
    public int getMoney() {
    	return money;
    }
	
}
