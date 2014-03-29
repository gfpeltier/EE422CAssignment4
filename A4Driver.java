/**
 * EE422C-Assignment-4
 * Grant Peltier & John Nelson
 * gfp237 & jkn387
 */

package assignment4;

import java.util.*;
import java.io.*;


public class A4Driver {
	
	public final static int WORDLENGTH = 5;
	
	/**
	 * Main method for project. Opens necessary txt files for dictionary and input and 
	 * calls recursive method.
	 * @param args
	 */
	public static void main(String[] args) {
		final StopWatch clock = new StopWatch();
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
				int off = 0;
				String from = new String();
				String to = new String();
				while(s.charAt(off) != ' '){
					from += s.charAt(off);
					off++;
				}while(s.charAt(off) == ' '){off++;}
				while(off < s.length()){to += s.charAt(off); off++;}
				if(from.length() != 5 || to.length() != 5){
					System.out.println("One or both of the words \"" + from + "\" or \"" + to + "\" is not 5 letters in length.\n\n" );
				}else{
					if(!WordsInDictionary(from, to, words)){
						System.out.println("One or both of the words \"" + from + "\" or \"" + to + "\" is not in the dictionary.\n\n");
					}else{
					clock.start();
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
	 * Determine if the words from input are valid 5-letter words from the dictionary
	 * @param from
	 * @param to
	 * @param words
	 * @return boolean determining validity of input
	 */
	public static boolean WordsInDictionary(String from, String to, Dictionary words){
		if(words.dictionary.contains(from) && words.dictionary.contains(to)){return true;}
		else{return false;}
	}
	
	/**
	 * Determines number of different letters between 2 5-letter words
	 * @param from
	 * @param to
	 * @return from word with number of different chacters between it and the to word 
	 * prepended to the front
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
	 * Recursive method of the program that determines ladder between fromWord and toWord
	 * @param fromWord
	 * @param toWord
	 * @param lastChange
	 * @param ladder
	 * @param marker
	 * @param words
	 * @param attempted
	 * @return ArrayList that contains String elements. Each element is a different word in the ladder.
	 * If no ladder could be found. Returns ArrayList with single String "0error"
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
		if(newWord.equals("noWord")){									
			MakeLadder(fromWord, toWord, lastChange, ladder, (marker+1)%6, words, attempted); 	
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
