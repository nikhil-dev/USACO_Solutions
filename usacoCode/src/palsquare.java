/*
ID: nikhil_3
LANG: JAVA
TASK: palsquare
FILE: palsquare.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class palsquare {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
		int base = Integer.parseInt(in.readLine());
		in.close();

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"palsquare.out")));

		// loop through each number between 1 and 300. If its square, expressed
		// in the given base is a palindrome, print it. Else continue looping.
		for (int i = 1; i <= 300; i++) {
			int squareInDecimal = (int) Math.pow(i, 2);
			String square = toBaseB(base, squareInDecimal);
			if (isPalindrome(square)) {
				out.println(toBaseB(base,i) + " " + square);
			}
		}

		out.close();
	}

	private static boolean isPalindrome(String pattern) {
		boolean isPalindrome = true;
		int halfLength = pattern.length() / 2;
		int j = pattern.length() - 1;
		for (int i = 0; i < halfLength; i++) {
			if (pattern.charAt(i) != pattern.charAt(j)) {
				isPalindrome = false;
				break;
			}
			j--;
		}
		return isPalindrome;
	}

	private static String toBaseB(int base, int number) {
		LinkedList<Character> list = new LinkedList<Character>();
		char current;
		int modulus;
		while (number != 0){
			modulus = number % base;
			current = (modulus > 9 ? (char)(modulus + (int) 'A' - 10) : (char)(modulus + (int) '0') );
			list.addFirst(current);
			number = number/base;
		}
		return listToString(list);
	}
	
	private static String listToString(List<Character> charSequence){
		StringBuilder string = new StringBuilder();
		for (char c : charSequence){
			string.append(c);
		}
		return string.toString();
	}
}