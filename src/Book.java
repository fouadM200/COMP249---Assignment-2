// ------------------------------------------------
// Assignment 3
// Question: Part II
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

import java.io.Serializable;

/**
 * @author Rami Al Najem (40242034) and Fouad Meida (40249310)
 * COMP249
 * Assignment #3
 * Due date: March 29th, 2023
 */

public class Book implements Serializable {
	
	/**
	 * The price of the book
	 */
	private double price;
	/**
	 * The year of publication for the book
	 */
	private int year;
	/**
	 * The title of the book
	 */
	private String title;
	/**
	 * The author(s) of the book
	 */
	private String authors;
	/**
	 * The genre of the book
	 */
	private String genre;
	/**
	 * The ISBN of the book.
	 */
	private String isbn;  
	
	/**
	Constructs a Book object with the given price, year, title, authors, genre, and ISBN.
	@param price the price of the book.
	@param year the year the book was published.
	@param title the title of the book.
	@param authors the authors of the book.
	@param genre the genre of the book.
	@param isbn the ISBN of the book.
	*/
	public Book(String title, String authors, double price, String isbn, String genre, int year)
	{
		this.price = price;
		this.year = year;
		this.title = title;
		this.authors = authors;
		this.genre = genre;
		this.isbn = isbn;
	}
	
	/**

	Returns the price of the book.
	@return the price of the book
	*/
	public double getPrice() {
		return price;
	}
	/**

	Sets the price of the book.
	@param price the price of the book to be set
	*/
	public void setPrice(double price) {
		this.price = price;
	}
	/**

	Returns the year of publication of the book.
	@return the year of publication of the book.
	*/
	public int getYear() {
		return year;
	}
	/**

	Sets the year of publication of the book.
	@param year the year of publication to set
	*/
	public void setYear(int year) {
		this.year = year;
	}
	/**

	Returns the title of the book.
	@return the title of the book.
	*/
	public String getTitle() {
		return title;
	}
	/**

	Sets the title of the book.
	@param title the title of the book.
	*/
	public void setTitle(String title) {
		this.title = title;
	}
	/**

	Returns the string representation of the authors of this book.
	@return the authors of the book as a string
	*/
	public String getAuthors() {
		return authors;
	}
	/**

	Sets the author(s) of the book.
	@param authors the name(s) of the author(s)
	*/
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	/**

	Returns the genre of this book.
	@return the genre of this book
	*/
	public String getGenre() {
		return genre;
	}
	/**

	Sets the genre of the book.
	@param genre the genre to set
	*/
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**

	Returns the ISBN of the book.
	@return the ISBN of the book
	*/
	public String getIsbn() {
		return isbn;
	}
	/**

	Sets the ISBN of the book.
	@param isbn the ISBN of the book to set
	*/
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	//Overridden equals method.
	/**
	Compares this Book object with another object for equality.
	Returns true if and only if the given object is a non-null instance of Book class
	and has the same price, year, title, authors, genre, and ISBN as this Book object.
	@param x the object to compare to this Book object
	@return true if the given object is equal to this Book object, false otherwise
	*/
	public boolean equals(Object x)
	{
		if (x==null || this.getClass()==x.getClass())
			return false;
		Book book = (Book) x;
		return (this.price == book.getPrice() && this.year == book.year && this.title.equals(book.title) && this.authors.equals(book.authors)
				&& this.genre.equals(book.genre) && this.isbn.equals(book.isbn));
	}
	
	//Overridden toString method.
	/**

	Returns a string representation of the Book object in the format:
	"The title of the book is [title], written by the author(s) [authors]. This book belongs to the [genre] genre.
	This book has the following ISBN number: [isbn]. It was published since [year] and it costs $[price]."
	@return a string representation of the Book object
	*/
	public String toString()
	{
		return "The title of the book is "+title+", written by the author(s) "+authors+". This book belongs to the "+genre+" genre. "
				+ "This book has the following ISBN number: "+isbn+". It was published since "+year+" and it costs $"+price+".";
	}
}