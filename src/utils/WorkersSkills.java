package utils;

import java.util.ArrayList;
import java.util.List;

import exceptions.HotelException;

public enum WorkersSkills {
     MANTENIMIENTO, LIMPIEZA, PISCINA, SPA, BAR, COMIDA, LAVANDERIA;

	public static List<WorkersSkills> getSkillsList(String[] list) throws HotelException {
		List<WorkersSkills> skills = new ArrayList<>();
		for(String s: list) {
		  switch(s.toLowerCase()) {
		     case "mantenimiento":
		    	 if(!skills.contains(WorkersSkills.MANTENIMIENTO)) {
		    	   skills.add(WorkersSkills.MANTENIMIENTO);
		    	 }
		    	 break;
		     case "limpieza":
		    	 if(!skills.contains(WorkersSkills.LIMPIEZA)) {
			    	   skills.add(WorkersSkills.LIMPIEZA);
			    	 }
		    	 break;
		     case "piscina":
		    	 if(!skills.contains(WorkersSkills.PISCINA)) {
			    	   skills.add(WorkersSkills.PISCINA);
			    	 }
		    	 break;
		     case "spa":
		    	 if(!skills.contains(WorkersSkills.SPA)) {
			    	   skills.add(WorkersSkills.SPA);
			    	 }
		    	 break;	 
		     case "bar":
		    	 if(!skills.contains(WorkersSkills.BAR)) {
			    	   skills.add(WorkersSkills.BAR);
			    	 }
		    	 break;
		     case "comida":
		    	 if(!skills.contains(WorkersSkills.COMIDA)) {
			    	   skills.add(WorkersSkills.COMIDA);
			    	 }
		    	 break;
		     case "lavanderia":
		    	 if(!skills.contains(WorkersSkills.LAVANDERIA)) {
			    	   skills.add(WorkersSkills.LAVANDERIA);
			    	 }
		    	 break; 
		  }  
		}
		return skills;
	}


}
