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

public class BadYearException extends Exception {

	/**

	This exception is thrown when a book's publication year is not within a valid range.
	@param message the error message associated with the exception
	*/
	public BadYearException(String message) {
		super(message);
	}
	
	/**
     * String getter method which return the message stored in super class for the specific error.
     */
    public String getMessage() {
    	return super.getMessage();
    }
}