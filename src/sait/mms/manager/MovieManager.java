package sait.mms.manager;
import sait.mms.problemdomain.Movie;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieManager {
	static ArrayList<Movie> movies;
	public static void loadMovieList() {
		String line = null;
		String[] fields;
		Movie newMovie;
		try (BufferedReader r = new BufferedReader(new FileReader("res//movies.txt"))){
			
            while(line != null) {
                fields = line.split(",");
                newMovie = new Movie(Integer.parseInt(fields[0]),
               		(fields[1]),
               		Integer.parseInt(fields[2])
                	);
                movies.add(newMovie);
            }
		}
		catch(FileNotFoundException e){
			System.out.println("Starting with empty file");
		}
		catch(IOException e){
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void displayMenu() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Movie Management system");
		System.out.println("1	Add New Movie and Save");
		System.out.println("2	Generate List of Movies Released in a Year");
		System.out.println("3	Generate List of Random Movies");
		System.out.println("4	Exit \n");
		System.out.println("Enter an option: ");

	    String choice = sc.next();
	    
	    switch(choice) {
		case "1":
			MovieManager.addMovie();
			break;
		case "2":
			MovieManager.generateMovieListInYear();
			break;
		case "3":
			MovieManager.generateRandomMovieList();
			break;
		case "4":
			MovieManager.saveMovieListToFile();
			break;
		default:
			System.out.println("Invalid option!");
			MovieManager.displayMenu();
	    }
	}
	
	public static void addMovie() {
		
	}
	
	public static void generateMovieListInYear() {
		
	}
	
	public static void generateRandomMovieList() {
		
	}
	
	public static void saveMovieListToFile() {
		
	}

}