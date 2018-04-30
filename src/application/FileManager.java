package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * the class of file manager
 *
 */
public class FileManager {
	 static int rank= 0;
    /**
     * load the challenger into the arraylist
     * read the list file
     * @param file
     * @return
     */
    public static ArrayList<Challenger> loadChallenger(String file) {
		ArrayList<Challenger> result = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(file));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();

				if (sc.hasNextLine()) {rank++;} // get the line number of a file
				
				if (line.length() > 0)
					result.add(new Challenger(line,rank));
			}		
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}