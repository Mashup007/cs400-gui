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

    public static ArrayList<String> loadChallenger(String file) {
		ArrayList<String> result = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(file));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				
				if (line.length() > 0)
					result.add(new String(line));
					// set every teams' rank at 0, modify the top 3 teams later
			}		
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}