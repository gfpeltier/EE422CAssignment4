package assignment4;

import java.util.*;

public class Dictionary {
	ArrayList<String> dictionary;
	
	/**
	 * 
	 */
	Dictionary(){
		dictionary = new ArrayList<String>();
	}
	
	/**
	 * 
	 * @param word
	 */
	public void addWord(String word){
		dictionary.add(word);
	}
	
	/**
	 * 
	 * @param base
	 * @param newWord
	 * @param change
	 * @return
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
	 * 
	 * @param base
	 * @param change
	 * @param attempt
	 * @param numDiff
	 * @param to
	 * @return
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
