// ------------------------------------------------
// Assignment 3
// Question: Part I, II and III
// Written by: Fouad Meida (40249310) and Rami Al Najem (40242034)
// ------------------------------------------------

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Rami Al Najem (40242034) and Fouad Meida (40249310)
 * COMP249
 * Assignment #3
 * Due date: March 29th, 2023
 */

public class Driver {
	
	private static int manyFields = 0;
	private static int fewFields = 0;
	private static int missingTitle = 0;
	private static int missingAuthors = 0;
	private static int missingPrice = 0;
	private static int missingIsbn = 0;
	private static int missingGenre = 0;
	private static int missingYear = 0;
	private static int unknownGenre = 0;
	private static int invalidISBN = 0;
	private static int invalidYear = 0;
	private static int invalidPrice = 0;
	private static int totalBooks = 0;
	
	/**
	The main method of the Book Processor program. This method processes input files of book data,
	identifies errors in the data, and outputs a summary of the errors. It also creates a new file
	containing a list of valid book objects, and allows the user to search for books by genre or
	author name.
	@param args An array of command line arguments (not used in this program).
	*/
    public static void main(String[] args) {
        System.out.println("Welcome to the Book Processor program!");
        do_part1();
        System.out.println("\nMany fields errors: "+manyFields);
        System.out.println("Few fields errors: "+fewFields);
        System.out.println("missing title errors: "+missingTitle);
        System.out.println("missing authors errors: "+missingAuthors);
        System.out.println("missing price errors: "+missingPrice);
        System.out.println("missing ISBN errors: "+missingIsbn);
        System.out.println("missing genre errors: "+missingGenre);
        System.out.println("missing year errors: "+missingYear);
        System.out.println("unknown genre errors: "+unknownGenre);
        int total_syntax_errors = manyFields + fewFields + missingTitle + missingAuthors + missingPrice + missingIsbn + missingGenre + missingYear + unknownGenre;
        System.out.println("Total syntax errors: "+total_syntax_errors);
        
        do_part2();
        

        //do_part3();

        System.out.println("Thank you for using the Book Processor!");
    }
    
    /**
    This method takes a string containing comma-separated values (CSV) and splits it into an array of strings,
    where each element in the array corresponds to one field in the CSV. If the CSV contains a field that
    is enclosed in double quotes, this method will treat the entire field as a single element in the array,
    rather than splitting it at the commas.
    @param record A string containing CSV data to be split into an array.
    @return An array of strings, where each element corresponds to one field in the CSV.
    */
    public static String[] splitter(String record)
	{
		String[] Array1;
		String[] Array2;
		if(record.charAt(0) == '\"')
		{
			Array1 = record.split("\"");
			Array2 = Array1[2].split(",");
			Array2[0] = Array1[1];
			return Array2;
		}
		else
		{
			Array2 = record.split(",");
			return Array2;
		}
	}
    
    /**
    This method checks if a given string is a valid book genre. The method compares the input genre to
    a list of known genres, and returns true if the input matches one of the valid genres, and false otherwise.
    @param genre A string representing the genre of a book, to be checked for validity.
    @return A boolean value indicating whether the input genre is a valid book genre.
    */
    public static boolean genreChecker(String genre)
    {
        String[] genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
        for (String g : genres) {
            if (g.equals(genre)) {
                return true;
            }
        }
        return false;

    }
    
    /**
    Reads a list of input file names from "Part1_input_file_names.txt" and processes each file according to the
    given specifications, writing valid records to output files and writing syntax errors to "syntax_error_file.txt".
    @throws TooFewFieldsException if a record contains fewer than 6 fields
    @throws TooManyFieldsException if a record contains more than 6 fields
    @throws MissingFieldException if a required field is missing in a record
    @throws UnknownGenreException if a record contains an unknown genre
    @throws FileNotFoundException if an input file is not found
    @throws IOException if there is an error while reading or writing files
    */
    public static void do_part1() {
    	
    	// Create genres Array of String variable and store 8 genre codes.
        String[] genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
        
        // create outputFiles Array of String variable and store 8 file names to be created which will contains later
        // the correct record names based on their syntax. Every record will be written in the correct file based on their genre.
        String[] outputFiles = {
                "Cartoons_Comics_Books.csv.txt",
                "Hobbies_Collectibles_Books.csv.txt",
                "Movies_TV.csv.txt",
                "Music_Radio_Books.csv.txt",
                "Nostalgia_Eclectic_Books.csv.txt",
                "Old_Time_Radio_Books.csv.txt",
                "Sports_Sports_Memorabilia.csv.txt",
                "Trains_Planes_Automobiles.csv.txt"
        };
        
        // Try to open "Part1_input_file_names.txt" using BufferedReader class and create a BufferedReader file called
        // "syntax_error_file.txt" in order to writer records that contains syntax errors.
        try (BufferedReader inputFileReader = new BufferedReader(new FileReader("Part1_input_file_names.txt"));
             BufferedWriter errorWriter = new BufferedWriter(new FileWriter("syntax_error_file.txt")))
        {
        	
        	// Reading the first line of the file, which is the number of booksYYYY.txt files from 1995 to 2010.
            int numberOfFiles = Integer.parseInt(inputFileReader.readLine());
            
            // Using for loop to iterate through the 16 booksYYYY.txt files in order to open them one by one and read line by line
            // for every opened file.
            for (int i = 0; i < numberOfFiles; i++) {
            	
            	// Storing the name of booksYYYY.txt file in a String pointer.
                String inputFileName = inputFileReader.readLine();
                
                // Using inputFileName pointer as a parameter to open the file and read its content.
                try (BufferedReader bookFileReader = new BufferedReader(new FileReader(inputFileName))) 
                {
                	// Define a line String variable and initialize it to null.
                    String line;
                    
                    // Use while loop to read all booksYYYY.txt file lines.
                    // Read the line, store it in line variable and check if it's not equal to null, then do the following instructions.
                    while ((line = bookFileReader.readLine()) != null) 
                    {
                        try {
                        		// Split the record using splitter static method and store it in an array of Strings called fields. 
	                            String[] fields = splitter(line);
	                            
	                            // Check if we have too many fields or too few fields, then throw the appropriate exception.
	                            if (fields.length < 6) {
	                                fewFields++;
	                                throw new TooFewFieldsException("Too few fields");
	                            }
	                            else if (fields.length > 6) {
	                                manyFields++;
	                                throw new TooManyFieldsException("Too many fields");
	                            }
	                            
	                            // Check if we have a missing field, then throw the appropriate exception.
	                           	if (fields[0].isEmpty())
	                           	{
	                           		missingTitle++;
	                           		throw new MissingFieldException("Missing title");
	                           	}
	                           	if (fields[1].isEmpty())
	                           	{
	                           		missingAuthors++;
		                           	throw new MissingFieldException("Missing authors");
	                           	}
	                           	if (fields[2].isEmpty())
	                           	{
	                           		missingPrice++;
		                           	throw new MissingFieldException("Missing price");
	                           	}
	                           	if (fields[3].isEmpty())
	                           	{
	                           		missingIsbn++;
		                           	throw new MissingFieldException("Missing ISBN");
	                           	}
	                           	if (fields[4].isEmpty())
	                           	{
	                           		missingGenre++;
		                           	throw new MissingFieldException("Missing genre");
	                           	}
	                           	if (fields[5].isEmpty())
	                           	{
	                           		missingYear++;
		                           	throw new MissingFieldException("Missing year");
	                           	}
	                            	
	                            // Check if we have an unknown genre, then throw the appropriate exception.
	                            String genre = fields[4];
	                            if(!(genreChecker(genre)))
	                            {
	                            	unknownGenre++;
	                                throw new UnknownGenreException("Unknown genre");
	                            }
	                            
	                            // If we reach this line, that means the record is syntactically correct.
	                            // Write the record to the correct FileWriter name based on the genre.
	                            int genreIndex = Arrays.asList(genres).indexOf(genre);
	                            try (BufferedWriter outputFileWriter = new BufferedWriter(new FileWriter(outputFiles[genreIndex],true)))
	                            {
	                                outputFileWriter.write(line);
	                                outputFileWriter.newLine();
	                                
	                            }
                        	}
                        
                        // If an error occurs, catch the four types of exceptions in a catch block and write the record in the "syntax_error_file.txt"
                        // and display the type of the occurred error.
                        catch (TooManyFieldsException | TooFewFieldsException | MissingFieldException | UnknownGenreException e)
                        {
                            errorWriter.write("syntax error in file: " + inputFileName);
                            errorWriter.newLine();
                            errorWriter.write("====================");
                            errorWriter.newLine();
                            errorWriter.write("Error: " + e.getMessage());
                            errorWriter.newLine();
                            errorWriter.write("Record: " + line);
                            errorWriter.newLine();
                            errorWriter.newLine();
                        }
                        
                    }
                    
                }
                
                // If the file name to be opened does not exist, catch the FileNotFoundException and display a message.
                catch (FileNotFoundException e) {
                    System.out.println("File not found: " + inputFileName);
                }
            }
        }
        
        // If a problem occurs while reading from a file or writing to a file, catch the IOexception and display a message.
        catch (IOException e) {
            System.out.println("Error while reading or writing files: " + e.getMessage());
        }
    }
    
    
    /**
    This method reads book records from input files specified in "Part2_input_file_names.txt",
    validates the price, year and ISBN of each record, creates Book objects and stores them in a
    two-dimensional array based on their genre. The method then writes the Book objects to binary
    files based on their genre. Finally, the method prints the total number of semantic errors
    encountered and the total number of books processed. This method also calls the do_part3 method
    to generate a report of the books written to binary files.
    */
    public static void do_part2() {
    	
    	// Create a 2d array of book. The first dimension has a length of 8, which are the 8 created files in do_part1()
    	// and every created file (an element in 1D array) points to another array which has a length of 1000 in order to 
    	// store book objects based on their genre.
    	Book[][] book = new Book[8][1000];
    	
    	// Create a bookArrayCounter integer variable and initialize it to 0. This will be used as an index to store book objects
    	// in 2D array based on their genre.
    	int bookArrayCounter = 0;
    	
    	// Create genres Array of String variable and store 8 genre codes.
        String[] genres = {"CCB", "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
    	
        // create outputFiles Array of String variable and store 8 serializable file names to be created which will contains later
        // the correct record names based on their syntax. Every record will be written in the correct file based on their genre.
        String[] serFiles = {
                "Cartoons_Comics_Books.csv.ser",
                "Hobbies_Collectibles_Books.csv.ser",
                "Movies_TV.csv.ser",
                "Music_Radio_Books.csv.ser",
                "Nostalgia_Eclectic_Books.csv.ser",
                "Old_Time_Radio.csv.ser",
                "Sports_Sports_Memorabilia.csv.ser",
                "Trains_Planes_Automobiles.csv.ser"
        };
    	
        // Initialize six pointers to null (variables which are the book's attributes)
    	String title = null;
    	String authors = null;
    	double price = 0;
    	String isbn = null;
    	String genre = null;
    	int year = 0;
    	
    	// Try to open "Part2_input_file_names.txt" using BufferedReader class and create a BufferedReader file called
        // "semantic_error_file.txt" in order to writer records that contains syntax errors.
    	try (BufferedReader inputFileReader1 = new BufferedReader(new FileReader("Part2_input_file_names.txt"));
    			BufferedWriter errorWriter1 = new BufferedWriter(new FileWriter("semantic_error_file.txt"));)
        {
        	// Reading the first line of the file, which is the number of the 8 created files in do_part1() that contains
    		// records names based on their genre.
            int numberOfFiles = Integer.parseInt(inputFileReader1.readLine());
            
            // Using for loop to iterate through the 8 created files in do_part1() in order to open them one by one and read line by line
            // for every opened file.
            for (int i = 0; i < numberOfFiles; i++){
            	
            	// Storing the name of the genre created file in a String pointer.
                String inputFileName = inputFileReader1.readLine();
                
                // Using inputFileName pointer as a parameter to open the file and read its content.
                try (BufferedReader bookFileReader1 = new BufferedReader(new FileReader(inputFileName));)
                {
                	// Define a line String variable and initialize it to null.
                    String line1;
                    
                    // Use while loop to read all booksYYYY.txt file lines.
                    // Read the line, store it in line variable and check if it's not equal to null, then do the following instructions.
                    while ((line1 = bookFileReader1.readLine()) != null) 
                    {
                        try {
                    			// Split the record using splitter static method and store it in an array of Strings called fields. 
	                            String[] fields1 = splitter(line1);
	                            
	                            // Assign every element of fields1 array of String to the correct type of variable.
	                            title = fields1[0];
	                            authors = fields1[1];
	                            price = Double.parseDouble(fields1[2]);
	                            isbn = fields1[3];
	                            genre = fields1[4];
	                            year = Integer.parseInt(fields1[5]);
	                            
	                            // Validate price, year and ISBN for every record using the created static methods below.
	                            validatePrice(price);
	                            validateIsbn(isbn);
	                            validateYear(year);
	                            
	                            // Create a book object using Book parameterized constructor and store it in a book 2D array
	                            // based on its genre.
	                            if (bookArrayCounter < book[i].length)
	                            {
	                            totalBooks++;
	                            book[i][bookArrayCounter] = new Book(title, authors, price, isbn, genre, year);
	                            bookArrayCounter++;
	                            }
	                           
	                            
                        	}
                        
                        // If an error occurs, catch the four types of exceptions in a catch block and write the record in the "semantic_error_file.txt"
                        // and display the type of the occurred error.
                        catch (BadIsbn10Exception | BadIsbn13Exception | BadPriceException | BadYearException | IllegalArgumentException e)
                        {
                            errorWriter1.write("Semantic error in file: " + inputFileName);
                            errorWriter1.newLine();
                            errorWriter1.write("====================");
                            errorWriter1.newLine();
                            errorWriter1.write("Error: " + e.getMessage());
                            errorWriter1.newLine();
                            errorWriter1.write("Record: " + line1);
                            errorWriter1.newLine();
                            errorWriter1.newLine();
                        }
                    }
                    
                 // Reinitialize bookArrayCounter to 0 for the next index in 1D level of array.
                    bookArrayCounter = 0;
                    
                 // Write the record to the correct FileOutputStream based on the genre in binary language.
                    int genreIndex = Arrays.asList(genres).indexOf(genre);
                    try (ObjectOutputStream bookWriter = new ObjectOutputStream(new FileOutputStream(serFiles[genreIndex]));
)
                    {
                    	for (int p=0; p<book.length; p++)
                    		for (int q=0; q<book[p].length && !(book[p][q] == null); q++)
                    			bookWriter.writeObject(book[p][q]);
                    	bookWriter.close();
                    }
                    
                    // If the genre file name cannot be opened, catch the FileNotFoundException and display a message.
                    catch(FileNotFoundException n)
                    {
                        System.out.println("Impossible to write the record to the binary file " + serFiles[genreIndex]);
                    }
                   
                }
                
                // If the file name to be opened does not exist, catch the FileNotFoundException and display a message.
                catch (FileNotFoundException e)
                {
                    System.out.println("File not found: " + inputFileName);
                }
               
            }
         
            
        }
        
        // If a problem occurs while reading from a file or writing to a file, catch the IOexception and display a message.
        catch (IOException e)
        {
            System.out.println("Error while reading or writing files: " + e.getMessage());
        }
    	
        System.out.println("\ninvalid ISBN error "+invalidISBN);
        System.out.println("invalid year error "+invalidYear);
        System.out.println("invalid price error "+invalidPrice);
        int total_semantic_errors = invalidISBN + invalidYear + invalidPrice;
        System.out.println("Total semantic errors: "+total_semantic_errors);
        System.out.println("\nTotal books: "+totalBooks);
        
        // Call do_part3() static method which takes a 2D array of book which will be used in the third part of the program.
    	do_part3(book);
    }
    
    
    /**
     * Allows the user to view and navigate through a list of book files, selected from a sub-menu. 
     * Displays a main menu with options to view the currently selected file, select a new file to view,
     * or exit the program. Displays a sub-menu of available book files to choose from. 
     * @param books a 2D array of Book objects containing data from CSV files
     */
    public static void do_part3(Book[][] books)
    {
    	// Create a choice String variable and initialize it to null;
    	String choice = null;
    	
    	// Create outputFiles Array of String variable and store 8 serializable file names so the binary data will be read from.
    	String[] serFileNames = {
                "Cartoons_Comics_Books.csv.ser",
                "Hobbies_Collectibles_Books.csv.ser",
                "Movies_TV.csv.ser",
                "Music_Radio_Books.csv.ser",
                "Nostalgia_Eclectic_Books.csv.ser",
                "Old_Time_Radio.csv.ser",
                "Sports_Sports_Memorabilia.csv.ser",
                "Trains_Planes_Automobiles.csv.ser"
        };
    	
    	// Create a Scanner object
    	Scanner keyboard = new Scanner(System.in);
    	
    	// Create two integer variables and initialize them to 0;
    	int currentFileIndex = 0;
    	int currentIndex = 0;
    	
    	// Use while loop in order to display Main menu to the user and create a navigation system.
    	while(true)
    	{
    		// Display the Main menu.
    		System.out.println("----------------------------------------------------------------------");
    		System.out.println("                              Main Menu                               ");
    		System.out.println("----------------------------------------------------------------------");
    		System.out.println("v   View the selected file: " + serFileNames[currentFileIndex] + "\t(" + getLengthOfExistingElements(books[currentFileIndex]) + " records)");
    		System.out.println("s   Select a file to view");
    		System.out.println("x   Exit");
    		System.out.println("----------------------------------------------------------------------");
    		System.out.print("Enter Your Choice: ");    		
    		
    		// Prompt the user to input a letter.
    	    choice = keyboard.nextLine();
    		
    		// Compare user's input with three input cases.
    		switch(choice)
    		{
    		
    		// If the user inputs v, do the following instructions.
    		case "v":
    		{
    			System.out.println("viewing: " + serFileNames[currentFileIndex] + "\t(" + getLengthOfExistingElements(books[currentFileIndex]) + " records)");
    			
    			// Display the current record and n-1 records below it.
    			int n = getNumber("Enter the number of records to display (0 to exit): ");
    			//keyboard.nextLine();
    			
    			if (n == 0)
    				continue;
    			
    			// Create 2 integer variables which are the starting and the ending index.
    			int start = currentIndex;
    			int end = Math.min((currentIndex + n), books[currentFileIndex].length);
    			
    			// Display book names based on the user's input.
    			for (int i=start; i<end; i++)
    				System.out.println(books[currentFileIndex][i]);
    			
    			// If the user reaches the end of the array, display the following message.
    			if (end == books[currentFileIndex].length)
    				System.out.println("EOF has been reached.");
    			
    			currentIndex = end - 1;
    			choice = null;
    			break;
    		}
    		
    		// If the user inputs v, do the following instructions.
    		case "s":
    		{
    			// Display the file selection menu
        		System.out.println("----------------------------------------------------------------------");
                System.out.println("                            File Sub-Menu                             ");
        		System.out.println("----------------------------------------------------------------------");
        		
        		// Display the 8 serializable file names in the File Sub-Menu with the corresponding number of records in every file.
        		for (int i = 0; i < serFileNames.length; i++)
        		{
                    try
                    {
                        FileInputStream fileIn = new FileInputStream(serFileNames[i]);
                        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                        books[i] = (Book[]) objectIn.readObject();
                        objectIn.close();
                        fileIn.close();
                        System.out.println((i+1) + "  " + serFileNames[i] + "\t\t(" + getLengthOfExistingElements(books[i]) + " records)");
                    }
                    catch (Exception e)
                    {
                        System.out.println((i+1) + "  " + serFileNames[i] + "\t\t(" + getLengthOfExistingElements(books[i]) + " records)");
                    }
                }
        		
        		// The choice number 9 is the Exit from the file sub-menu.
        		System.out.println("9  Exit");
        		System.out.println("----------------------------------------------------------------------");
        		
        		// Get the user's choice
                int fileIndex = getNumberInRange(1, serFileNames.length + 1, "Please enter a " +
                			"number between 1 and " + (serFileNames.length + 1) + ": ");
                
                // If the user inputs 9, exit from the file sub-menu.
                if(fileIndex == 9)
                {
                	// Exit the selection mode.
                	continue;
                }
                
                currentFileIndex = fileIndex - 1;
                currentIndex = 0;
                choice = null;
        		break;
    		}
    		
    		// If the user inputs v, do the following instructions.
    		case "x":
    		{
    			//Exit the program.
    			System.out.println("Program will terminate.");
    			System.out.println("Thank you for using our Book Processor program!");
    			//keyboard.close();
    			System.exit(0);
    		}
    		
    		// If the user inputs a letter different from the expected cases, prompt him again to insert the correct letter.
    		default:
    		{
    			// Display an error message which prompt the user to reenter a choice since the inputed character is invalid.
    			System.out.println("Invalid choice, please try again");
    			choice = null;
    			break;
    		}
    	}
    	}
    }
    
    /**
    Method to be used in do_part3(book) static method. It returns the number of non-null elements in the given array.
    @param arr the array to count non-null elements in
    @return the number of non-null elements in the array
    */
    public static int getLengthOfExistingElements(Object[] arr) {
        int count = 0;
        for (Object obj : arr) {
            if (obj != null) {
                count++;
            }
        }
        return count;
    }
    
    /**
    Method to be used in do_part3(book) static method. It prompts the user to enter a number within the given range (inclusive) and returns it.
    If the input is not a valid integer or outside of the range, an error message is displayed and the user is prompted to enter a new input.
    @param min The minimum value of the range (inclusive)
    @param max The maximum value of the range (inclusive)
    @param prompt The message to prompt the user for input
    @return The valid integer input within the range
    */
    public static int getNumberInRange(int min, int max, String prompt) {
        Scanner kb = new Scanner(System.in);

        while (true) {
            try
            {
                System.out.print(prompt);
                int number = kb.nextInt();
                
                if (number >= min && number < max) 
                {
                	//kb.close();
                    return number;
                }
            }
            
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input, please try again");
            }
        }
    }
    
    /**
    Method to be used in do_part3(book) static method. It prompts the user for an integer input and returns the inputted integer value.
    @param prompt the message to display to the user to prompt for input
    @return the integer value entered by the user
    @throws InputMismatchException if the user enters a non-integer value
    */
    public static int getNumber(String prompt)
    {
    	Scanner key = new Scanner(System.in);
    	while (true)
    	{
    		try
    		{
    			System.out.print(prompt);
    			int num = key.nextInt();
    			//key.close();
    			return num;
    		}
    		catch(InputMismatchException e)
    		{
    			System.out.println("Invalid input, please try again.");
    		}
    	}
    }
    
    /**
    Method to be used in do_part2() static method. It validates the given price and throws a BadPriceException if it is negative.
    @param price the price to validate
    @throws BadPriceException if the price is negative
    */
    public static void validatePrice(double price) throws BadPriceException {
        if (price < 0)
        {
        	invalidPrice++;
            throw new BadPriceException("Price cannot be negative");
        }
    }

    /**
    Method to be used in do_part2() static method. It validates if the given year is within the range of 1995 to 2010 (inclusive).
    If the year is outside of the range, a BadYearException is thrown with an error message.
    @param year the year to be validated
    @throws BadYearException if the year is not within the range of 1995 to 2010 (inclusive)
    */
    public static void validateYear(int year) throws BadYearException {
        if (year < 1995 || year > 2010)
        {
        	invalidYear++;
            throw new BadYearException("Invalid year: " + year);
        }
    }

    /**
    Method to be used in do_part2() static method. It validates if the given ISBN is valid based on its length.
    If the length is 10, it calls validateIsbn10() method to validate the ISBN-10 format.
    If the length is 13, it calls validateIsbn13() method to validate the ISBN-13 format.
    If the length is neither 10 nor 13, it throws an IllegalArgumentException.
    @param isbn the ISBN to be validated
    @throws BadIsbn10Exception if the ISBN-10 format is invalid
    @throws BadIsbn13Exception if the ISBN-13 format is invalid
    @throws IllegalArgumentException if the length of the ISBN is neither 10 nor 13
    */
    public static void validateIsbn(String isbn) throws BadIsbn10Exception, BadIsbn13Exception {
        if (isbn.length() == 10) {
            validateIsbn10(isbn);
        } else if (isbn.length() == 13) {
            validateIsbn13(isbn);
        } else {
        	invalidISBN++;
            throw new IllegalArgumentException("Invalid ISBN length: " + isbn.length());
        }
    }
    
    /**
    Method to be used in do_part2() static method. It validates whether an ISBN-10 is valid or not.
    @param isbn the ISBN-10 to be validated
    @throws BadIsbn10Exception if the ISBN-10 is invalid
    */
    public static void validateIsbn10(String isbn) throws BadIsbn10Exception {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (10 - i) * digit;
        }

        int lastDigit = isbn.charAt(9) == 'X' ? 10 : Character.getNumericValue(isbn.charAt(9));
        sum += lastDigit;

        if (sum % 11 != 0)
        {
        	invalidISBN++;
            throw new BadIsbn10Exception("Invalid ISBN-10: " + isbn);
        }
    }
    
    /**
    Method to be used in do_part2() static method. This method validates the ISBN-13 of a book.
    @param isbn The ISBN-13 to validate.
    @throws BadIsbn13Exception if the ISBN-13 is invalid.
    */
    public static void validateIsbn13(String isbn) throws BadIsbn13Exception {
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0 ? 1 : 3) * digit;
        }

        if (sum % 10 != 0)
        {
        	invalidISBN++;
            throw new BadIsbn13Exception("Invalid ISBN-13: " + isbn);
        }
    }
}