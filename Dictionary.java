package assignment4;

import java.util.*;

public class Dictionary {
	private ArrayList<String> dictionary;
	
	Dictionary(){
		dictionary = new ArrayList<String>();
	}
	
	public void addWord(String word){
		dictionary.add(word);
	}

}
