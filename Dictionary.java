package assignment4;

import java.util.*;

public class Dictionary {
	ArrayList<String> dictionary;
	
	Dictionary(){
		dictionary = new ArrayList<String>();
	}
	
	public void addWord(String word){
		dictionary.add(word);
	}
	
	public boolean equalExcept(String base, String newWord, int change){
		int k = 0;
		while(k < base.length()){
			if(base.charAt(k) != newWord.charAt(k) && k != change){return false;}
		}
		return true;
	}
	
	public String findWord(String base, int change, ArrayList<String> attempt){
		Iterator<String> i = dictionary.iterator();
		while(i.hasNext()){
			if(equalExcept(base, i.next(), change) && !attempt.contains(i.next())){return i.next();}
		}
		return "noWord";
	}

}
