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
		try {
            System.out.print("Enter duration: ");
            int duration = Integer.parseInt(sc.nextLine());
            if (duration <= 0) throw new Exception("Duration must be > 0");

            System.out.print("Enter movie title: ");
            String title = sc.nextLine();
            if (title.isEmpty()) throw new Exception("Title cannot be empty");

            System.out.print("Enter year: ");
            int year = Integer.parseInt(sc.nextLine());
            if (year <= 0) throw new Exception("Year must be > 0");

            Movie newMovie = new Movie(duration, title, year);
            movies.add(newMovie);
            System.out.println("Added movie: " + newMovie);
        } catch (Exception e) {
            System.out.println("Invalid input! " + e.getMessage());
        }
	}
	
	public static void generateMovieListInYear() {
		try {
            System.out.print("Enter in year: ");
            int year = Integer.parseInt(sc.nextLine());
            int totalDuration = 0;

            System.out.println("\nMovie List");
            System.out.println("Duration\tYear\tTitle");
            for (Movie m : movies) {
                if (m.getYear() == year) {
                    System.out.println(m);
                    totalDuration += m.getDuration();
                }
            }
            System.out.println("Total duration: " + totalDuration + " minutes");
        } catch (Exception e) {
            System.out.println("Invalid year!");
        }
	}
	
	public static void generateRandomMovieList() {
		try {
            System.out.print("Enter number of movies: ");
            int n = Integer.parseInt(sc.nextLine());
            if (n <= 0 || n > movies.size()) {
                System.out.println("Invalid number!");
                return;
            }

            List<Movie> shuffled = new ArrayList<>(movies);
            Collections.shuffle(shuffled);

            int totalDuration = 0;
            System.out.println("\nMovie List");
            System.out.println("Duration\tYear\tTitle");
            for (int i = 0; i < n; i++) {
                Movie m = shuffled.get(i);
                System.out.println(m);
                totalDuration += m.getDuration();
            }
            System.out.println("Total duration: " + totalDuration + " minutes");
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
	}
	
	public static void saveMovieListToFile() {
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("res/movies.txt"));
            for (Movie m : movies) {
                bw.write(m.getDuration() + "," + m.getTitle() + "," + m.getYear());
                bw.newLine();
            }
            bw.close();
            System.out.println("Movies saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing movies.txt: " + e.getMessage());
        }
	}

}
