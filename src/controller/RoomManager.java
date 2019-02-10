package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.HotelException;
import model.Customer;
import model.Room;
import model.Worker;
import utils.RoomState;
import utils.RoomServices;

public class RoomManager {

	public RoomManager() {}
	
	private static HotelManager hotel;
	
	/**
	 * The method receives a customer and returns a room that:
	 * 1) Is on CLEAN state
	 * 2) Has, at least, one service from demanded ones (only if customer wants some service)
	 * 3) The nearest capacity to the one demanded by customer
	 * 4) Returns null if no room is found
	 * @param customer Customer
	 * @return Room
	 * @throws HotelException 
	 */
	public Room findRoomForReservation(Customer customer) throws HotelException {
		
		hotel = HotelManager.getInstance();

		List<Room> roomsWithServices = new ArrayList<>();
		
		Map<String, Room> rooms = hotel.getRooms();
		for (String key : rooms.keySet()) {
			if(rooms.get(key).getState().equals(RoomState.CLEAN)) {
			  if(rooms.get(key).getRoomServices().containsAll(customer.getRoomServices()) 
					  || customer.getRoomServices().isEmpty()) {
				if(rooms.get(key).getMaxCapacity() >= customer.getNumPeople()) {
				   roomsWithServices.add(rooms.get(key));
			    }
		      }
			}
		}
		
		if(!roomsWithServices.isEmpty()){
		  Collections.sort(roomsWithServices);
		  return roomsWithServices.get(0);
		}
		
		return null;	
	}
	
	
	/**
	 * Gestion de la petición PROBLEM
	 * @param room Room
	 * @return String
	 * @throws HotelException
	 */
	public String solveRoomProblem(Room room) throws HotelException {
		
		//guardamos el customer y lo quitamos de esa habitación
		Customer customer = room.getCustomer();
		room.setCustomer(null);
		
		//setteamos a estado BROKEN y eliminamos los servicios pendientes y empleados que hubiera
		room.setState(RoomState.BROKEN);
		room.getPendingRequests().clear();
		room.getWorker().clear();
		
		//faltaria asignarle un empleado, pero a quien? O assigned antonio to room 003 se refiere a un customer?
		
		//buscamos otra habitación para el customer
		Room newRoom = findRoomForReservation(customer);
		
		StringBuilder sb = new StringBuilder();
		sb.append("--> Room set as BROKEN <-- \n");
		//si es null, diremos que no hay ninguna habitación para el customer, en caso contrario lo asignamos a la nueva
		if(newRoom == null) {
			sb.append("<-- No rooms found for the current customer, dessasigned -->\n");
            //¿teoricamente, se deberia eliminar tambien de la collection Reservations?
		}
		else {
			newRoom.setCustomer(customer);
			newRoom.setState(RoomState.RESERVED);
			sb.append("<-- Customer "+ customer.getDni()+ " assigned to room " + newRoom.getNumber() + " -->\n");
		}
		
		return sb.toString();
		
	}
	
	/**
	 * Gestion de la petición REQUEST
	 * @param room Room
	 * @param services List RoomServices
	 * @return String
	 */
	public String solveRequest(Room room, List<RoomServices> services) {
		//aqui construimos el String para devolver
		StringBuilder sb = new StringBuilder();
		hotel = HotelManager.getInstance();
		
		//obtenemos los workers que ya estuvieran en room, si los hay
		List<Worker> workersInRoom = room.getWorker();
		//obtenemos todos los workers
		Set<Worker> workers = hotel.getWorkers();
		for(RoomServices rs: services) {             //iteración en los servicios a resolver
			boolean found = false;
			for(Worker w: workers) {                 //iteración en los workers disponibles
				if(w.getSkills().contains(rs)) {     //si el worker tiene esa skill...
					found = true;
					if(!workersInRoom.contains(w)) { //si el worker no está asignado ya a esa room
						workersInRoom.add(w);        //lo asignamos
						sb.append("--> Worker " + w.getName() + " assigned to Room "+ room.getNumber()+ "\n");
					}
				}
			}
			if(!found) {
				List<RoomServices> pendingServices = room.getPendingRequests();
				pendingServices.add(rs);
				room.setPendingRequests(pendingServices);
				sb.append("No worker available for this Service. Added to Customer pending Requests <--\n");
			}
		}
		
		return sb.toString();
	}
}
