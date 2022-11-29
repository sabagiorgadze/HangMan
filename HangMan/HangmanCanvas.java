/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
//Width is 380 because I print canvas.getWidth() and it returned 380.
	private static final int WIDTH = 380;
//Height is 500 because I print canvas.getHeight() and it returned 500.
	private static final int HEIGHT = 500;
	public double xCoordinate = WIDTH / 2 - BEAM_LENGTH;
	public double yCoordinate = HEIGHT / 2 + BEAM_LENGTH;
	GLabel text = null;
	GLabel incorrectLabel;
	String incorrectLetter = "";
	public int guess = 0;


/** Resets the display so that only the scaffold appears */
	public void reset() {
		drawScaffold();
	}

/*
 * This method draws a line, beam and a rope at the left side of the canvas, it is appeared
 * on the canvas once the game is launched.
 */
	private void drawScaffold(){
		GLine line = new GLine(xCoordinate,yCoordinate,xCoordinate,yCoordinate - SCAFFOLD_HEIGHT);
		add(line);
		GLine beam = new GLine(xCoordinate,yCoordinate - SCAFFOLD_HEIGHT,WIDTH / 2,yCoordinate - SCAFFOLD_HEIGHT);
		add(beam);
		GLine rope = new GLine(WIDTH / 2, yCoordinate - SCAFFOLD_HEIGHT,WIDTH / 2, yCoordinate - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		add(rope);
		
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		if(text != null){
			remove(text);
		}
		text = new GLabel(word,50,450);
		text.setFont("TIMES_ROMAN-40");
		add(text);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrectLetter += letter;
		guess++;
		drawHopelessMan();
		incorrectLabel = new GLabel(incorrectLetter,50,500);
		incorrectLabel.setFont("TIMES_ROMAN-40");
		add(incorrectLabel);
	}
	
	
/*
 * This method is drawing hang man step by step, The parts such as left foot, right hand
 * and so on is been drawing on the canvas according to the quantity of guesses.
 */
	public void drawHopelessMan(){
		if(guess == 1){
			GOval head = new GOval(HEAD_RADIUS * 2,HEAD_RADIUS * 2);
			add(head,WIDTH / 2 - HEAD_RADIUS, yCoordinate - SCAFFOLD_HEIGHT + ROPE_LENGTH);
		}else if(guess == 2){
			GLine body = new GLine(WIDTH / 2,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH, WIDTH / 2,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH);
			add(body);
		}else if(guess == 3){
			GLine rightArm = new GLine(WIDTH / 2,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,WIDTH / 2+ UPPER_ARM_LENGTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD);
			add(rightArm);
			GLine rightHand = new GLine(WIDTH / 2+ UPPER_ARM_LENGTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,WIDTH / 2+ UPPER_ARM_LENGTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			add(rightHand);
		}else if(guess == 4){
			GLine leftArm = new GLine(WIDTH / 2,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,WIDTH/2 -UPPER_ARM_LENGTH, yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD);
			add(leftArm);
			GLine leftHand = new GLine(WIDTH/2 -UPPER_ARM_LENGTH, yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH + ARM_OFFSET_FROM_HEAD,WIDTH / 2 - UPPER_ARM_LENGTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS * 2 + ROPE_LENGTH +ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			add(leftHand);
		}else if(guess == 5){
			GLine rightHip = new GLine(WIDTH / 2,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH, WIDTH / 2 + HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH);
			add(rightHip);
			GLine rightLeg = new GLine(WIDTH / 2 + HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH, WIDTH / 2 + HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH + LEG_LENGTH);
			add(rightLeg);
		}else if(guess == 6){
			GLine leftHip = new GLine(WIDTH / 2,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH, WIDTH / 2 - HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH);
			add(leftHip);
			GLine leftLeg = new GLine(WIDTH / 2 - HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH, WIDTH / 2 - HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH + LEG_LENGTH);
			add(leftLeg);
		}else if(guess == 7){
			GLine rightFoot = new GLine(WIDTH / 2 + HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH + LEG_LENGTH,WIDTH / 2 + HIP_WIDTH + FOOT_LENGTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH + LEG_LENGTH);
			add(rightFoot);
		}else if(guess == 8){
			GLine leftFoot = new GLine(WIDTH / 2 - HIP_WIDTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH + LEG_LENGTH, WIDTH / 2 - HIP_WIDTH - FOOT_LENGTH,yCoordinate - SCAFFOLD_HEIGHT + HEAD_RADIUS *2 + BODY_LENGTH + LEG_LENGTH);
			add(leftFoot);
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}