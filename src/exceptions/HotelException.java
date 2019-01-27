package exceptions;

import java.util.Arrays;
import java.util.List;

public class HotelException extends Exception {
	 public static final int INCORRECT_NUM_ARGS = 0;
	    public static final int OPTION_UNALLOWED = 1;
	    public static final int WRONG_SERVICE = 2;
	    public static final int WRONG_SKILL = 3;
	    public static final int WITHOUT_SKILLS = 4;
	    public static final int DNI_INCORRECT_SIZE = 5;
	    public static final int DNI_INCORRECT_NUM = 6;
	    public static final int ROOM_NUMBER_EXISTS = 7;
	    
	    public static final int WRONG_ROOM_NUM_FORMAT = 8;
	    public static final int INCORRECT_OCCUPANTS_NUM = 9;
	    public static final int INCORRECT_MEDITATION = 10;
	    public static final int INCORRECT_STRENGTH = 11;
	    
	    private int code;
	    
	    private final List<String> messages = Arrays.asList(
	            "<ERROR 001 : Incorrect number of arguments>",
	            "<ERROR 002 : Option unallowed>",
	            "<ERROR 003 : This service is not offered in any room>",
	            "<ERROR 004 : Skill for worker not recognized>",
	            "<ERROR 005 : This worker has no skills>",
	            "<ERROR 006 : Problem with DNI: incorrect size>",
	            "<ERROR 007 : Problem with DNI: only numbers accepted>",
	            "<ERROR 008 : A room with that number already exists>",
	            "<ERROR 009 : The room number must have 3 digits>",
	            "<ERROR 010 : Incorrect number of ocupants>",
	            "<ERROR 011 : Incorrect meditation level>",
	            "<ERROR 012 : Incorrect strength value>");
	           
	            
	    public HotelException(int code) {
	        this.code = code;
	    }

	    @Override
	    public String getMessage() {
	        return messages.get(code);
	    }
	    
	}

