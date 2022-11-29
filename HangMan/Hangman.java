/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
//I create new instance of a HangmanLexicon class
	HangmanLexicon hangman = new HangmanLexicon();
	RandomGenerator random = RandomGenerator.getInstance();
//This is a variable which stores a word randomly chosen from file
	private String randomWord;
//This is a randomWord, but it if hidden at the start
	private String dotts;
//With this guesses primitive variable we can find out how many guesses the user left,
//in order to lose the game.
	public int guesses = 8;
	private HangmanCanvas canvas;
	
/*This init method is responsible to form the right side of the canvas to
 * draw the rope, hang man, word, incorrect characters and so on.
 */
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas);
	}
	

	public void run(){
		canvas.reset();
		guessWord();
		canvas.drawHopelessMan();
		canvas.displayWord(dotts);
	}
	
	
/*This method is responsible for the left side of the canvas, it prints the content
 * of the game, such as greeting, updating user how many guesses left, updating word's
 * guessing progress, updating the user whether he win or lose the game,
 * letting user to input character and so on.
 */
	private void guessWord(){
		randomWord = selectWord();
		dotts = dottedWord(randomWord);
		println("Welcome to Hangman!");
		while(guesses > 0){
			println("The word now looks like this: " + dotts);
			println("You have " + guesses + " guesses left");
			String input = readLine("Your guess: ");
			input = input.toUpperCase();
			while(isValidInput(input)){
				println("Invalid input");
				input = readLine("Your guess: ");
				input = input.toUpperCase();
			}
			inputChecker(input);
			if(winOrLose()){
				println("You guessed the word " + randomWord);
				println("You win.");
				break;
			}else if (guesses == 0){
				println("You're completly hung");
				println("The word was " + randomWord);
				println("You Lose.");
			}
		}
	}
	
/*This method checks if user's inputed character is valid or not. it is valid if the
 * character, which has to be the first char of t
 * he String parameter "input", is letter
 * and its length is less than 2.
 */
	private boolean isValidInput(String input){
		char firstLetter = input.charAt(0);
		if(!Character.isLetter(firstLetter) || input.length() >= 2){
			return true;
		}else {
			return false;
		}
	}
	
	
/*This method is responsible to find out the user win or lose the game. User win if
 * hidden word is guessed correctly, so hidden word is equal to randomWord, which
 * was randomly chosen from a file.
 */
	private boolean winOrLose(){
		if(dotts.equals(randomWord)){
			return true;
		}else{
			return false;
		}
	
	}
	
	
/*
 * This method is responsible for find out if user's input is correct or not.
 * It not, it prints the relevant information and decreases the guesses. Also, it
 * prints the incorrect character on the left side of the canvas, below hang man.
 * If it is correct and inputed character exists in the i-nth member in the randomWord,
 * it prints the relevant message and so on.
 */
	private void inputChecker(String input){
		char firstCh = input.charAt(0);
		if(randomWord.indexOf(input) == -1){
			println("There is no " + input + "'s in the word");
			guesses--;
			canvas.noteIncorrectGuess(firstCh);
		}else{
			println("That guess is correct");
			for(int i = 0; i < randomWord.length(); i++){
				if(randomWord.charAt(i) == input.charAt(0)){
					dotts = dotts.substring(0, i) + input + dotts.substring(i + 1);
				}
			}
			canvas.displayWord(dotts);
		}
		
	}
	
/*
 * This method is choosing a word randomly from the hang man lexicon file,
 * it choosing word from 0 to maximum word that is on the file, which is hangman.getWordCount.
 */
	private String selectWord(){
		String word = hangman.getWord(random.nextInt(0, hangman.getWordCount()));
		return word;
	}
	
/*
 * This method is responsible to hide the word, which is returned by the selectWord() method,
 * At start, word is hidden by "-", and displayed on the canvas.
 */
	private String dottedWord(String str){
		String result = "";
		for(int i = 0; i < str.length(); i++){
			result += "-";
		}
		canvas.displayWord(result);
		return result;
	}
}