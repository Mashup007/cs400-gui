package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * creating the file manager
 *
 */
public class FileManager {
	
	/**
	 *load the challenger into the arraylist
	 */
    public static ArrayList<Challenger> loadChallenger(String file) {
		ArrayList<Challenger> result = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(file));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.length() > 0)
					result.add(new Challenger(line));
			}		
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
