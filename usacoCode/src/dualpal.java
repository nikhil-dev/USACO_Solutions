/*
ID: nikhil_3
LANG: JAVA
TASK: dualpal
FILE: dualpal.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class dualpal {

	public static void main(String[] args) throws IOException {
		// read in the two numbers N and S
		BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
		StringTokenizer input = new StringTokenizer(in.readLine());
		in.close();

		final int N = Integer.parseInt(input.nextToken());
		int S = Integer.parseInt(input.nextToken());

		S++; // because the N numbers need to be strictly greater than S, they
				// can't be equal to S
		int nGoodNumbers = 0;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"dualpal.out")));

		// start iterating at S+1 and continue until N numbers are found that
		// are palindromes in atleast two bases (2 <=base <= 10)
		while (nGoodNumbers < N) {
			if (isDualPalindrome(S)){
				nGoodNumbers++;
				out.println(S);
			}
			S++;
		}
		out.close();
	}
	
	/*
	 * checks if the number n is a dual palindrome when expressed in a integer base in [2,10]
	 */
	private static boolean isDualPalindrome(int n){
		int count = 0;
		for (int i = 2; i <= 10; i++) {
			if (isPalindrome(toBaseB(i, n))) {
				count++;
				if (count == 2) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * checks if the input String pattern is a palindrome
	 */
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

	/*
	 * converts a number to a new base representation and returns the number in
	 * the new representation
	 */
	private static String toBaseB(int base, int number) {
		LinkedList<Character> list = new LinkedList<Character>();
		char current;
		int modulus;
		while (number != 0) {
			modulus = number % base;
			current = (modulus > 9 ? (char) (modulus + (int) 'A' - 10)
					: (char) (modulus + (int) '0'));
			list.addFirst(current);
			number = number / base;
		}
		return listToString(list);
	}

	/*
	 * converts a List<Character> type to the equivalent String and returns the
	 * String
	 */
	private static String listToString(List<Character> charSequence) {
		StringBuilder string = new StringBuilder();
		for (char c : charSequence) {
			string.append(c);
		}
		return string.toString();
	}

}
