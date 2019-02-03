package model;

import java.util.List;

import utils.WorkersSkills;

public class Worker {
     private String dni;
     private String name;
     private List<WorkersSkills> skills;
     private Room room;
     
     public Worker() {}
     public Worker(String dni, String name, List<WorkersSkills> skills) {
    	 this.dni = dni;
    	 this.name = name;
    	 this.skills = skills;
     }
     
     public void setRoom(Room room) {
    	 this.room = room;
     }
     
     public Room getRoom() {
    	 return room;
     }
     
     
     
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<WorkersSkills> getSkills() {
		return skills;
	}
	public void setSkills(List<WorkersSkills> skills) {
		this.skills = skills;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	
     
     
     
}
