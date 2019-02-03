package utils;

import java.util.ArrayList;
import java.util.List;

import exceptions.HotelException;

public enum WorkerSkillsV2 {
	 MAINTENANCE, CLEANING, SWIMMING_POOL, SPA, BAR, FOOD, LAUNDERY;

		public static List<WorkerSkillsV2> getSkillsList(String[] list) throws HotelException {
			List<WorkerSkillsV2> skills = new ArrayList<>();
			for(String s: list) {
			  switch(s.toLowerCase()) {
			     case "maintenance":
			    	 if(!skills.contains(WorkerSkillsV2.MAINTENANCE)) {
			    	   skills.add(WorkerSkillsV2.MAINTENANCE);
			    	 }
			    	 break;
			     case "cleaning":
			    	 if(!skills.contains(WorkerSkillsV2.CLEANING)) {
				    	   skills.add(WorkerSkillsV2.CLEANING);
				    	 }
			    	 break;
			     case "swimming_pool":
			    	 if(!skills.contains(WorkerSkillsV2.SWIMMING_POOL)) {
				    	   skills.add(WorkerSkillsV2.SWIMMING_POOL);
				    	 }
			    	 break;
			     case "spa":
			    	 if(!skills.contains(WorkerSkillsV2.SPA)) {
				    	   skills.add(WorkerSkillsV2.SPA);
				    	 }
			    	 break;	 
			     case "bar":
			    	 if(!skills.contains(WorkerSkillsV2.BAR)) {
				    	   skills.add(WorkerSkillsV2.BAR);
				    	 }
			    	 break;
			     case "food":
			    	 if(!skills.contains(WorkerSkillsV2.FOOD)) {
				    	   skills.add(WorkerSkillsV2.FOOD);
				    	 }
			    	 break;
			     case "laundery":
			    	 if(!skills.contains(WorkerSkillsV2.LAUNDERY)) {
				    	   skills.add(WorkerSkillsV2.LAUNDERY);
				    	 }
			    	 break; 
			  }  
			}
			return skills;
		}
}
