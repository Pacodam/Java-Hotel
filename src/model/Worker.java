package model;

import java.util.List;

import utils.WorkersSkills;

public class Worker {
     private int dni_numeric;
     private String name;
     private List<WorkersSkills> skills;
     private Room room;
     
     public Worker(int dni_numeric, String name, List<WorkersSkills> skills) {
    	 this.dni_numeric = dni_numeric;
    	 this.name = name;
    	 this.skills = skills;
     }
     
     public void setRoom(Room room) {
    	 this.room = room;
     }
     
     public Room getRoom() {
    	 return room;
     }
     
}
