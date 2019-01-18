package model;

import java.util.List;

import utils.WorkersSkills;

public class Worker {
     private String dni_numeric;
     private String name;
     private List<WorkersSkills> skills;
     
     public Worker(String dni_numeric, String name, List<WorkersSkills> skills) {
    	 this.dni_numeric = dni_numeric;
    	 this.name = name;
    	 this.skills = skills;
     }
     
}
