package exceptions;

import java.util.Arrays;
import java.util.List;

public class HotelException extends Exception {
	 public static final int INCORRECT_NUM_ARGS = 0;
	    public static final int OPTION_UNALLOWED = 1;
	    public static final int WRONG_SERVICE = 2;
	    public static final int INCORRECT_DATA = 3;
	    public static final int SER_UNALLOWED_ON_PLANET = 4;
	    public static final int SER_EXISTS = 5;
	    public static final int SER_NO_EXISTS = 6;
	    public static final int SER_UNMODIFICABLE = 7;
	    public static final int OPTION_UNALLOWEDd = 8;
	    public static final int INCORRECT_AGE = 9;
	    public static final int INCORRECT_MEDITATION = 10;
	    public static final int INCORRECT_STRENGTH = 11;
	    
	    private int code;
	    
	    private final List<String> messages = Arrays.asList(
	            "<ERROR 001 : Incorrect number of arguments>",
	            "<ERROR 002 : Option unallowed>",
	            "<ERROR 003 : Wrong service>",
	            "<ERROR 004 : Incorrect data>",
	            "<ERROR 005 : Ser cannot be registered on that planet>",
	            "<ERROR 006 : A Ser with that name already exists>",
	            "<ERROR 007 : There is no any Ser with that name>",
	            "<ERROR 008 : Ser cannot be modified>",
	            "<ERROR 009 : Option not allowed>",
	            "<ERROR 010 : Incorrect age>",
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

