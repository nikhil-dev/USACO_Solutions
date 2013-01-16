/*
ID: nikhil_3
LANG: JAVA
TASK: transform
FILE: transform.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class transform {

	public static void main(String[] args) throws IOException {
		
		// read in source and target
		BufferedReader in = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"transform.out")));
		int side = Integer.parseInt(in.readLine());
		String line;

		char[][] square = new char[side][side];
		// reads in starting square
		for (int i = 0; i < side; i++) { 
			line = in.readLine();
			for (int j = 0; j < side; j++) {
				square[i][j] = line.charAt(j);
			}
		}
		
		
		char[][] finalSquare = new char[side][side];
		// reads in final square
		for (int i = 0; i < side; i++) { 
			line = in.readLine();
			for (int j = 0; j < side; j++) {
				finalSquare[i][j] = line.charAt(j);
			}
		}


		// perform the each transformation and check if it gives the target
		if (areEqual(rotate(square), finalSquare))
			out.println(1);
		else if (areEqual(rotate(rotate(square)), finalSquare))
			out.println(2);
		else if (areEqual(rotate(rotate(rotate(square))), finalSquare))
			out.println(3);
		else if (areEqual(reflect(square), finalSquare))
			out.println(4);
		else if (areEqual(rotate(reflect(square)), finalSquare)
				|| areEqual(rotate(rotate(reflect(square))), finalSquare)
				|| areEqual(rotate(rotate(rotate(reflect(square)))),
						finalSquare))
			out.println(5);
		else if (areEqual(square,finalSquare))
			out.println(6);
		else
			out.println(7);
		
		in.close();
		out.close();
	}
	
	/*
	 * rotates a given character square by 90 degrees in the clockwise direction and returns the rotated array
	 */
	private static char[][] rotate(char[][] square) {
		char[][] rotatedSquare = new char[square.length][square.length];
		for (int i = 0; i < square.length; i++) {
			for (int j = 0; j < square.length; j++) {
				rotatedSquare[j][square.length - i - 1] = square[i][j];
			}
		}
		return rotatedSquare;
	}

	/*
	 * reflects about a vertical line down the middle of the square
	 */
	private static char[][] reflect(char[][] square) {
		char[][] reflectedSquare = new char[square.length][square.length];
		for (int i = 0; i < square.length; i++) {
			for (int j = 0; j < square.length; j++) {
				reflectedSquare[i][j] = square[i][square.length - j - 1];
			}
		}
		return reflectedSquare;
	}

	/*
	 * compares two squares and returns if they are equal
	 */
	private static boolean areEqual(char[][] array1, char[][] array2) {
		boolean isEqual = true;
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array1.length; j++) {
				if (array1[i][j] != array2[i][j]) {
					isEqual = false;
					break;
				}
			}
			if (!isEqual)
				break;
		}
		return isEqual;
	}
	
}
