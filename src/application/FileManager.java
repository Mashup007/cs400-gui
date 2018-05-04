///////////////////////////////////////////////////////////////////////////////
// Title:            Team Project, Milestone 3
// This file:        Main.java
// Other Files:      Challenger.java   FileManager.java   
// Semester:         CS 400 Spring 2018
//
// Authors:          Matt Zimmers
//					 Tarun Mandalapu 
//					 Zidong Zhang
//					 Shuyan Zhang
//					 Chao Wang
// Email:            mzimmers@wisc.edu
//					 tmandalapu@wisc.edu 
//                   zzhang773@wisc.edu
//					 szhang399@wisc.edu
//                   cwang573@wisc.edu
// Lecturer's Name:  Debra Deppeler
// Source Credits:   StackOverflow
// Known Bugs:       Final round of competition has weird gap between team
//                   name and their associated text field 
///////////////////////////////////////////////////////////////////////////////
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