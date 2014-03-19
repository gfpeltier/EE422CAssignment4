package assignment4;

import java.util.*;
import java.io.*;


public class A4Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		File dictionary = new File(args[0]);
		File input = new File(args[1]);
		Dictionary words = new Dictionary();
		try{
			FileReader dictRead = new FileReader(dictionary);
			BufferedReader dReader = new BufferedReader(dictRead);
			FileReader inputRead = new FileReader(input);
			BufferedReader inRead = new BufferedReader(inputRead);
			  
			for (String s = dReader.readLine(); s != null; s = dReader.readLine()){
					if(s.charAt(0) != '*'){
						words.addWord(s.substring(0,5));
					}
				}
		}catch (FileNotFoundException e) {
            // File not found
            e.printStackTrace();

		}catch (IOException e) {
            // Not able to read line
            e.printStackTrace();
      }

	}

	public static String MakeLadder(String fromWord, String toWord, int lastChange){
		return "";  	// Just to prevent errors for now. Must remove later. Consider returning array/arraylist instead
	}
	
}
