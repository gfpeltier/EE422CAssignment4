/**
 * EE422C-Assignment-4
 * Grant Peltier & John Nelson
 * gfp237 & jkn387
 */

package assignment4;

import java.util.*;

public class Dictionary {
	ArrayList<String> dictionary;
	
	/**
	 * Default constructor for Dictionary class
	 */
	Dictionary(){
		dictionary = new ArrayList<String>();
	}
	
	/**
	 * Method to add words into dictionary ArrayList
	 * @param word
	 */
	public void addWord(String word){
		dictionary.add(word);
	}
	
	/**
	 * Check to make sure words are equal except for character at determined index
	 * @param base
	 * @param newWord
	 * @param change
	 * @return boolean determining if the newWord is valid (e.g. 1 letter different from base word)
	 */
	public boolean equalExcept(String base, String newWord, int change){
		int k = 0;
		while(k < base.length()){
			if(base.charAt(k) != newWord.charAt(k) && k != change){return false;}
			k++;
		}
		return true;
	}
	
	/**
	 * Find words in dictionary based on difference of letter at determined offset and find
	 * best one to suit needs of target word
	 * @param base
	 * @param change
	 * @param attempt
	 * @param numDiff
	 * @param to
	 * @return newWord which is to be added to the ladder or a noWord error that 
	 * means no valid word was found based on parameters
	 */
	public String findWord(String base, int change, ArrayList<String> attempt, int numDiff, String to){
		Iterator<String> i = dictionary.iterator();
		ArrayList<String> possible = new ArrayList<String>();
		while(i.hasNext()){
			String x = i.next();
			if(equalExcept(base, x, change) && !attempt.contains(x)){
				String word = x;
				word = A4Driver.NumDiffLetters(word, to);
				possible.add(word);
				}
		}
		Iterator<String> j = possible.iterator();
		while(j.hasNext()){
			String y = j.next();
			int z = Integer.parseInt(y.substring(0,1));
			if(z <= numDiff){return y.substring(1,6);}
		}
		return "noWord";
	}

}
