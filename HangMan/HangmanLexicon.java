/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.IOException;

import acmx.export.java.io.FileReader;
import acmx.export.java.util.ArrayList;


public class HangmanLexicon {
	private ArrayList arrayOfLines;

/*
 * This method is reading the data(in this case the words) from the "HangmanLexicon.txt" file,
 * it reads the word line by line until the line is null and if something does not going well,
 * it will catch an exception.
 */
	public HangmanLexicon(){
		try{
			arrayOfLines = new ArrayList();
			BufferedReader reader = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			String line;
			while((line = reader.readLine()) != null){
				arrayOfLines.add(line);
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return arrayOfLines.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return (String)arrayOfLines.get(index);
	}
}