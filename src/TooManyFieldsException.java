// ------------------------------------------------
// Assignment 3
// Question: Part I
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

/**
 * @author Rami Al Najem (40242034) and Fouad Meida (40249310)
 * COMP249
 * Assignment #3
 * Due date: March 29th, 2023
 */

public class TooManyFieldsException extends Exception {
	/**

	Exception thrown when a record has too many fields.
	@param message error message describing the exception
	*/
    public TooManyFieldsException(String message) {
        super(message);
    }
    
    /**
     * String getter method which return the message stored in super class for the specific error.
     */
    public String getMessage() {
    	return super.getMessage();
    }
}