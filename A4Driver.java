package assignment4;

import java.util.*;
import java.io.*;


public class A4Driver {
	
	public final static int WORDLENGTH = 5;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final StopWatch clock = new StopWatch();
		clock.start();
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
					words.addWord(s.substring(0,WORDLENGTH));
				}
			}
			for(String s = inRead.readLine(); s != null; s = inRead.readLine()){
				if(s.length() < 11){
					System.out.println("One or more of the words is less than 5 characters in length.\n\n");
				}else{
				String from = new String(s.substring(0,WORDLENGTH));
				int off = 5;
				while(s.charAt(off) == ' '){
					off++;
				}
				int offEnd = off + WORDLENGTH;
				String to = new String(s.substring(off, offEnd));
				from = NumDiffLetters(from, to);
				ArrayList<String> empty = new ArrayList<String>();
				ArrayList<String> attempt = new ArrayList<String>();
				empty.add(from);
				ArrayList<String> output = MakeLadder(from, to, 0, empty, 1, words, attempt);
				if(output.contains("0error")){
					System.out.println("There is no word ladder from " + from.substring(1,6) + " to " + to);
				}else{
					Iterator<String> i = output.iterator();
					while(i.hasNext()){
						System.out.println(i.next().substring(1,6));
						}
					}
				System.out.print("\n\n");
				}
			}
			clock.stop();
			System.out.println("Elapsed time: " + clock.getElapsedTime() + " milliseconds");
		}catch (FileNotFoundException e) {
            // File not found
            e.printStackTrace();

		}catch (IOException e) {
            // Not able to read line
            e.printStackTrace();
      }

	}
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static String NumDiffLetters(String from, String to){
		int numDiff = 0;
		for(int k = 0; k < from.length(); k++){
			if(from.charAt(k) != to.charAt(k)){
				numDiff++;
			}
		}
		return numDiff + from;
	}

	/**
	 * 
	 * @param fromWord
	 * @param toWord
	 * @param lastChange
	 * @param ladder
	 * @param marker
	 * @param words
	 * @param attempted
	 * @return
	 */
	public static ArrayList<String> MakeLadder(String fromWord, String toWord, int lastChange, ArrayList<String> ladder, int marker, Dictionary words, ArrayList<String> attempted){
		if(Integer.parseInt(ladder.get(ladder.size()-1).substring(0,1)) == 0){ return ladder;}
		if(Integer.parseInt(ladder.get(ladder.size()-1).substring(0,1)) == 1){
			fromWord = NumDiffLetters(toWord, toWord);
			ladder.add(fromWord);
			return ladder;
		}
		if(marker == lastChange){			
			ladder.remove(ladder.size()-1);
			if(ladder.isEmpty()){
				ladder.add("0error");
				return ladder;
			}else{
			MakeLadder((ladder.get(ladder.size()-1)), toWord, lastChange, ladder, (marker+1)%6, words, attempted);
				}
			}
		int realChange;
		if(marker == 0){realChange = marker;
		}else{realChange = marker - 1; }
		int numDiff = Integer.parseInt(fromWord.substring(0,1));
		String newWord = words.findWord(fromWord.substring(1,6), realChange, attempted, numDiff, toWord);
		if(newWord.equals("noWord")){									// the logic of this if/else is more iterative than recursive
			MakeLadder(fromWord, toWord, lastChange, ladder, (marker+1)%6, words, attempted); 	// this wont work here. It will just want to return
			return ladder;
		}else{
			attempted.add(newWord);
			newWord = NumDiffLetters(newWord, toWord);
			ladder.add(newWord);
			MakeLadder(newWord, toWord, marker, ladder, (marker+1)%6, words, attempted);
			return ladder;
		}
	}
	
}
