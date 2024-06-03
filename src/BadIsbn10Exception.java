// ------------------------------------------------
// Assignment 3
// Question: Part II
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

/**
 * @author Rami Al Najem (40242034) and Fouad Meida (40249310)
 * COMP249
 * Assignment #3
 * Due date: March 29th, 2023
 */

public class BadIsbn10Exception extends Exception {
	/**

	Constructs a new BadIsbn10Exception with the specified detail message.
	@param message the detail message.
	*/
	public BadIsbn10Exception(String message) {
		super(message);
	}
	
	/**
     * String getter method which return the message stored in super class for the specific error.
     */
    public String getMessage() {
    	return super.getMessage();
    }
}