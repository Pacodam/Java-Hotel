package utils;

import java.util.ArrayList;
import java.util.List;

import exceptions.HotelException;

public enum WorkersSkills {
     MAINTENANCE, CLEANING, SWIMMING_POOL, SPA, BAR, FOOD, LAUNDERY;

	public static List<WorkersSkills> getSkillsList(String[] list) throws HotelException {
		List<WorkersSkills> skills = new ArrayList<>();
		for(String s: list) {
		  switch(s.toLowerCase()) {
		     case "maintenance":
		    	 skills.add(WorkersSkills.MAINTENANCE);
		    	 break;
		     case "cleaning":
		    	 skills.add(WorkersSkills.CLEANING);
		    	 break;
		     case "swimming_pool":
		    	 skills.add(WorkersSkills.SWIMMING_POOL);
		    	 break;
		     case "spa":
		    	 skills.add(WorkersSkills.SPA);
		    	 break;	 
		     case "bar":
		    	 skills.add(WorkersSkills.BAR);
		    	 break;
		     case "food":
		    	 skills.add(WorkersSkills.FOOD);
		    	 break;
		     case "laundery":
		    	 skills.add(WorkersSkills.LAUNDERY);
		    	 break;
		     default:
		    	 System.out.println(s);
		    	 throw new HotelException(HotelException.WRONG_SKILL);  
		  }
		}
		return skills;
	}


}
