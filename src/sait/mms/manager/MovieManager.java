package sait.mms.manager;
import sait.mms.problemdomain.Movie;
import java.io.*;
import java.util.*;

public class MovieManager {
	private ArrayList<Movie> movies;
	private Scanner sc;

	public MovieManager() {
		movies = new ArrayList<>();
		sc = new Scanner(System.in);
	}
	
	// --- 2.1 Load movies from file ---
	public void loadMovieList() {
		String line;
		String[] fields;
		
		try (BufferedReader r = new BufferedReader(new FileReader("res//movies.txt"))){
			
            while((line =r.readLine())!= null) {
                fields = line.split(",");
                Movie newMovie = new Movie(
                		Integer.parseInt(fields[0]),
                		fields[1],
                		Integer.parseInt(fields[2])
                	);
                movies.add(newMovie);
            }
            System.out.println("Movies loaded: " + movies.size());
		}
		catch(FileNotFoundException e){
			System.out.println("Starting with empty file");
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	// --- 2.2 Save movies in file ---
	public void saveMovieListToFile() {
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
	
	// --- 2.3 Menu ---
	public void displayMenu() {
		while(true) {
			System.out.println("Movie Management system");
			System.out.println("1	Add New Movie and Save");
			System.out.println("2	Generate List of Movies Released in a Year");
			System.out.println("3	Generate List of Random Movies");
			System.out.println("4	Exit \n");
			System.out.println("Enter an option: ");

		    String choice = sc.nextLine();
		    switch(choice) {
			case "1":
				addMovie();
				break;
			case "2":
				generateMovieListInYear();
				break;
			case "3":
				generateRandomMovieList();
				break;
			case "4":
				saveMovieListToFile();
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid option!");
		    }
		}
	}
	
	//  --- 2.4 Add movie ---
	public void addMovie() {
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

            movies.add(new Movie(duration, title, year));
//            movies.add(newMovie);
            System.out.println("Saving movies...");
            saveMovieListToFile();
            System.out.println("Added movie to the data file.");
//            System.out.println("Added movie: " + newMovie);
        } catch (Exception e) {
            System.out.println("Invalid input! " + e.getMessage());
        }
	}
	// --- 2.5 List movies in year ---
	public void generateMovieListInYear() {
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
	
	// --- 2.6 Random movies ---
	public void generateRandomMovieList() {
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
	
	

}
