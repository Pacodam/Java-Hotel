package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import exceptions.HotelException;
import model.Customer;
import model.Room;
import model.Worker;
import utils.RoomServices;
import utils.RoomState;
import utils.WorkersSkills;
import colors.ConsoleColors;

public class HotelManager {

	private static ConsoleColors color;
	private static double money = 1000;
	
	private static RoomManager roomMng;
	
	//las habitaciones se deben poder buscar por número, usaremos HashMap
	private static Map<String, Room> rooms = new HashMap<>();
	//con HashSet controlamos que no haya ningun trabajador repetido
	private static Set<Worker> workers = new HashSet<>();
	//HashSet para que no haya ningun customer repetido
	private static Set<Customer> reservations = new HashSet<>();
	
  
	
	private HotelManager() {
		roomMng = new RoomManager();
	}
	private static final HotelManager instance = new HotelManager();
	public static HotelManager getInstance() { return instance; }
	
	
	/**
	 * Exceptions to check: the room exists; exists a customer.
	 * The customer must be moved to another one. If not possible, the customer is cancelled
	 * Room must be setted to BROKEN
	 * @param data String[]
	 * @return String
	 * @throws HotelException 
	 */
	public String manageProblem(String[] data) throws HotelException {
		
		//if room not exists or exists but without customer, exception is thrown
		checkRoom(data[1]);
		Room room = rooms.get(data[1]);
		String result = roomMng.solveRoomProblem(room);
		
		return result;
	}
	
	/**
	 * Exceptions to check: the room exists.
	 * Find the worker with skills needed. If a petition is not accomplished, save for later.
	 * @param data
	 * @return String
	 * @throws HotelException 
	 */
    public String manageRequest(String[] data) throws HotelException {
    	
    	String confirmation = "";
    	//if room not exists or exists without customer, exception is thrown
    	checkRoom(data[1]);
    	//the services to resolve must belong to that room, elsewhere an exception is thrown
    	String[] services = data[2].split(",");
    	List<RoomServices>  brokenServices = RoomServices.getServicesList(services);
    	if(!rooms.get(data[1]).getRoomServices().containsAll(brokenServices)){
    		throw new HotelException(HotelException.INCORRECT_BROKEN_SERVICES);
    	}
    	else {
    		confirmation = roomMng.solveRequest(rooms.get(data[1]), brokenServices);
    	}
		return confirmation;
	}
    
    /**
     * Exceptions to check: room exists, customer exists inside.
     * Room pass to UNCLEAN
     * @param data
     * @return String
     * @throws HotelException 
     */
    public String manageLeave(String[] data) throws HotelException {
    	
    	//if room not exists or exists but without customer, exception is throwed
    	checkRoom(data[1]);
    	Room room = rooms.get(data[1]);
    	room.setCustomer(null);
    	room.setState(RoomState.UNCLEAN);
    	//los dos String siguientes serviran para construir el mensaje de salida.
    	String workerDesasignation = "";
    	String customerSatisfaction = "";
    	//controlamos la excepción para el caso de que nos metan un valor de dinero erróneo (sin la E final)
    	try {
    		String deleteE = data[2].substring(0, data[2].length()-1);
    		double m = Double.parseDouble(deleteE);
    		//se evalua si quedan servicios pendiente de realizar o no, en su caso se descuenta el 50% del pago
    		if(room.getPendingRequests().isEmpty()) {
    			customerSatisfaction = " Satisfied clients. You win " + m + " E";
    			this.money += m;
    		}
    		else {
    			customerSatisfaction = " Unsatisfied clients. You lose " + m/2 + " E";
    			this.money += m/2;
    		}	
    	}
    	catch(IndexOutOfBoundsException | NumberFormatException e) {
    		throw new HotelException(HotelException.INCORRECT_MONEY);
    	}
    	
    	//Miramos si había o no trabajadores en la Room, para quitarlos y para construir el mensaje adecuado
    	List<Worker> roomWorkers = room.getWorker();
    	if(roomWorkers.isEmpty()) {
    		workerDesasignation = " No workers on that room";
    	}
    	else {
    		workerDesasignation = " Worker " + room.getWorkerNames() + " desasigned";
    		room.getWorker().clear();
    	}
    	//Construcción del mensaje de salida final:
    	StringBuilder sb = new StringBuilder();
    	sb.append("--> Room " + room.getNumber() + " free and set to " + room.getState() + " <--\n");
    	sb.append("--> " + workerDesasignation + " <--\n");
    	sb.append("--> " + customerSatisfaction + " <--");
    	
		return sb.toString();
    	
	}
	
    /**
     * Condiciones: num habitación de 3 digitos. No se puede repetir. Num maximo ocupantes obligatorio.
     * Puede no tener campo servicios incluidos. 
     * Las guardamos en un TreeMap para que esten ordenados los numeros de habitación.
     * @param data String[]
     * @throws HotelException
     */
    public String setRoom(String[] data) throws HotelException {
    	
    	//control del formato numérico (debe ser de tamaño 3 caracteres)
    	String number = data[1];
    	if(number.length() != 3) {
    		throw new HotelException(HotelException.WRONG_ROOM_NUM_FORMAT);
    	}
    	//checking of number 13 (not allowed)
    	checkNum13(data[1]);
    	
    	//guardamos la capacidad máxima, verificamos que sea numérico con el metodo
    	int maxCapacity = checkOccupants(data[2]);
    	
    	//Si se han pasado servicios de la habitación, se guardan en el objeto room
    	String[] services;
    	List<RoomServices> roomServices;
    	if(data.length == 4) {
    	  services = data[3].split(",");
    	  roomServices = RoomServices.getServicesList(services);
    	}
    	else {
    		roomServices = new ArrayList<>();
    	}
    	//creación del objeto room
    	Room room = new Room(number, maxCapacity, roomServices);
    	
    	//añadimos la habitación al hashMap, verificando que no haya una key con ese número
    	if(!rooms.containsKey(number)){
    	  rooms.put(number, room);
    	}else {
    		throw new HotelException(HotelException.ROOM_NUMBER_EXISTS);
    	}
    	//devolvemos la confirmación de que se ha incorporado la nueva habitación
    	return "--> new Room added " + room.getRoomNumber() + " <-- ";
    }
    
    /**
     * Condiciones: numero dni válido. Nombre. Minimo una habilidad de las requeridas.
     * @param data String[]
     * @throws HotelException
     */
    public String setWorker(String[] data) throws HotelException {
    	
    	//guardamos el dni (con el método checkDNI verificamos que sea de 8 cifras y numerico)
    	String dni = checkDNI(data[1]);
    	
    	//guardamos el nombre
    	String name = data[2];
    	
    	//guardamos sus habilidades
    	String[] skills = data[3].split(",");
    	List<WorkersSkills> workerSkills = WorkersSkills.getSkillsList(skills);
    	if(workerSkills.isEmpty()) {
    		throw new HotelException(HotelException.WITHOUT_SKILLS);
    	}
    	//creación del objeto worker 
    	Worker worker = new Worker(dni, name, workerSkills);	
    	
    	//lo guardamos en un HashSet (no DNI duplicados)
    	 if(!workers.add(worker)) {
    		 throw new HotelException(HotelException.DUPLICATE_WORKER);
    	 }
    	
    	//mensaje de confirmación
    	return "--> new Worker added " + worker.getDni() + " <-- ";
    }
    
    /**
     * Nueva reserva.
     * Condiciones entrada: numero dni valido. Numero personas. Requisitos habitación no obligados.
     * @param data
     * @throws HotelException 
     */
    public String setReservation(String[] data) throws HotelException {
    	//guardamos el dni (con el método checkDNI verificamos que sea de 8 cifras y numerico)
    	String dni = checkDNI(data[1]);
    	
    	//guardamos el numero de ocupantes, verificamos que sea correcto
    	int numOcupants = checkOccupants(data[2]);
     	
     	//guardamos los servicios que pide el customer
     	String[] services;
    	List<RoomServices> roomServices;
    	if(data.length == 4) {
    	  services = data[3].split(",");
    	  roomServices = RoomServices.getServicesList(services);
    	}
    	else {
    		roomServices = new ArrayList<>();
    	}
     	
    	//creamos el objeto Customer
     	Customer customer = new Customer(dni, numOcupants, roomServices);
     	
     	//buscamos la habitación idonea para el customer (devuelve null si no hay ninguna)
     	Room room = roomMng.findRoomForReservation(customer);
     	
     	if(room == null) {
     		money -= 100;
     		return "--> Customer not assigned. You loose 100 <--";	
     	}
     	
     	//guardamos la nueva reserva
   	    if(!reservations.add(customer)) {
   		   throw new HotelException(HotelException.DUPLICATE_CUSTOMER);
   	    }
     	//update of objects involved
     	room.setState(RoomState.RESERVED);
     	room.setCustomer(customer);
     	
     	return "--> Assigned " + customer.getDni() + " to Room " + room.getNumber()+ " <-- ";
     		
    }
    
    /**
     * Dado un String correspondiente al número de una habitación, verificamos si en esa 
     * habitación hay alguien o no, así como si la habitación existe, lanzando la excepción
     * pertinente
     * @param room String
     * @throws HotelException
     */
    public static void checkRoom(String room) throws HotelException {
    	if(rooms.containsKey(room)) {
    		Customer customer = rooms.get(room).getCustomer();
			if(customer == null) {
			   throw new HotelException(HotelException.NOBODY_ON_ROOM);
			}
		}
		else {
			throw new HotelException(HotelException.NO_SUCH_ROOM);
		}
    }
    
    
    /**
     * Recibe un String y se verifica que tenga el tamaño de 8 caracteres y que sea numérico,
     * al resultado se le incorpora la letra correspondiente y se devuelve el String resultante.
     * @param dn String
     * @return String
     * @throws HotelException
     */
    public static String checkDNI(String dn) throws HotelException {
    	int dni;
    	try {
    		dni = Integer.parseInt(dn);
      	    if(dn.length() != 8) {
      		  throw new HotelException(HotelException.DNI_INCORRECT_SIZE);
      	    }
      	}catch(NumberFormatException e) {
      		throw new HotelException(HotelException.DNI_INCORRECT_NUM);
      	}
    	return getDNIfull(dn);	
    }
    
    /**
     * Verificamos que el dato número de ocupantes sea correcto, es decir numérico, y lo
     * devolvemos en formato int o lanzamos la excepción.
     * @param num String
     * @return int
     * @throws HotelException
     */
    public static int checkOccupants(String num) throws HotelException {
    	int occupants;
    	try {
       	  occupants = Integer.parseInt(num);
       	}catch(NumberFormatException e) {
       		throw new HotelException(HotelException.INCORRECT_OCCUPANTS_NUM);
       	}
    	return occupants;
    }
    
    /**
     * Chequeo de que el número de habitación no termine en 13
     * @param num String
     * @throws HotelException
     */
    public static void checkNum13(String num) throws HotelException {
    	
    	String sub = num.substring(1, num.length());
    	if(sub.equals("13")){
    		throw new HotelException(HotelException.NUM_13);
    	}
    	
    }
    
    
    /**
     * Recibe un String y devuelve un dni completo con su letra correspondiente
     * @param dni String
     * @return String
     */
    public static String getDNIfull(String dni) {
        String characters="TRWAGMYFPDXBNJZSQVHLCKE";
        int modulo= Integer.parseInt(dni) % 23;
        char letra = characters.charAt(modulo);
        return dni + letra; 
        } 
    
    /**
     * Método para mostrar las habitaciones del hotel
     * @return String
     * @throws HotelException
     */
    public String showRooms() throws HotelException {
    	if(rooms.isEmpty()) {
    		throw new HotelException(HotelException.NO_ROOMS);
    	}
    	StringBuilder sb = new StringBuilder();
    	
    	for (String key : rooms.keySet()) {
    	    sb.append(color.RED_BOLD_BRIGHT + rooms.get(key) + color.RESET);
    	    sb.append("\n");
    	}
    	return sb.toString();
    }
    
    public double getMoney() {
    	return money;
    }
    
    public Map getRooms() {
    	return rooms;
    }
    
    public Set getWorkers() {
    	return workers;
    }
	
}
