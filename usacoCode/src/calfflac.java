/*
 ID: nikhil_3
 LANG: JAVA
 TASK: calfflac
 FILE: calfflac.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class calfflac {
	public static void main(String[] args) throws IOException {

		
		/*
		 * read the input from the file to a string
		 */
		BufferedReader in = new BufferedReader(new FileReader("calfflac.in"));
		StringBuilder inputText = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			inputText.append(line);
			inputText.append('\n');
		}
		in.close();
		String text = inputText.toString();		


		/*
		 * loop though the each character of the string and find the length of
		 * the longest palindrome centered at that character. Keep track of
		 * which character that is the center of the longest palindrome.
		 */
		int index = -1; // this will be the index of the character that is the
						// center of the longest palindrome
		int longestLength = 0;
		for (int i = 0; i < text.length(); i++) {
			if (isAlphabet(text.charAt(i))) {
				int currentLength = lengthOfPalindrome(text, i);
				if (currentLength > longestLength) {
					longestLength = currentLength;
					index = i;
				}
			}
		}

		/*
		 * write output to file
		 */
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"calfflac.out")));
		out.println(longestLength);
		out.println(extractString(text, index, longestLength));
		out.close();

	}

	/*
	 * Returns the length of the longest palindrome in text centered at center
	 */
	private static int lengthOfPalindrome(String text, int center) {
		int evenPalindromeLength = lengthOfEvenPalindrome(text, center);
		int oddPalindromeLength = lengthOfOddPalindrome(text, center);
		return Math.max(evenPalindromeLength, oddPalindromeLength);
	}

	/*
	 * Returns the length of the longest even length palindrome in text centered
	 * at center
	 */
	private static int lengthOfEvenPalindrome(String text, int center) {
		if (center < 0 || center >= text.length())
			throw new IndexOutOfBoundsException();
		if (text.length() == 0 || center == text.length() - 1)
			return 0;
		int ptr1 = center, ptr2 = center + 1;
		int length = 0;
		while (true) {
			while (ptr1 > 0 && !isAlphabet(text.charAt(ptr1))) {
				ptr1--;
			}
			while (ptr2 < text.length() - 1 && !isAlphabet(text.charAt(ptr2))) {
				ptr2++;
			}
			if (!(ptr1 >= 0 && ptr2 < text.length())) {
				break;
			}
			if (!areSameLetters(text.charAt(ptr1), text.charAt(ptr2)))
				break;
			else {
				length = length + 2;
				ptr2++;
				ptr1--;
			}
		}

		return length;
	}

	/*
	 * Returns the length of the longest odd length palindrome in text centered
	 * at center
	 */
	private static int lengthOfOddPalindrome(String text, int center) {
		if (center < 0 || center >= text.length())
			throw new IndexOutOfBoundsException();
		if (text.length() == 0) {
			return 0;
		}
		if (center == 0 || center == text.length() - 1)
			return 1;

		int ptr1 = center - 1, ptr2 = center + 1;
		int length = 1;
		while (true) {
			while (ptr1 >= 0 && !isAlphabet(text.charAt(ptr1))) {
				ptr1--;
			}
			while (ptr2 < text.length() && !isAlphabet(text.charAt(ptr2))) {
				ptr2++;
			}
			if (!(ptr1 >= 0 && ptr2 < text.length())) {
				break;
			}
			if (!areSameLetters(text.charAt(ptr1), text.charAt(ptr2))) {
				break;
			} else {
				length = length + 2;
				ptr2++;
				ptr1--;
			}
		}
		return length;
	}

	/*
	 * returns if the character is a letter of the English alphabet
	 */
	private static boolean isAlphabet(char character) {
		return (character >= 'a' && character <= 'z')
				|| (character >= 'A' && character <= 'Z');
	}

	/*
	 * extracts the string centered at center of size length. (or centered at
	 * center and center + 1 if length is even).
	 */
	private static String extractString(String text, int center, int length) {
		String palindrome;
		if (length % 2 == 0) {
			palindrome = extractEvenString(text, center, length);
		} else
			palindrome = extractOddString(text, center, length);
		return palindrome;
	}

	/*
	 * extracts the string centered at center with of size length. length should
	 * be even.
	 */
	private static String extractEvenString(String text, int center, int length)
			throws IndexOutOfBoundsException {
		StringBuilder palindrome = new StringBuilder();
		int count = 0;
		int ptr1 = center, ptr2 = center + 1;
		while (count < length) {
			while (!isAlphabet(text.charAt(ptr1))) {
				palindrome.insert(0, text.charAt(ptr1));
				ptr1--;
			}
			while (!isAlphabet(text.charAt(ptr2))) {
				palindrome.append(text.charAt(ptr2));
				ptr2++;
			}
			if (areSameLetters(text.charAt(ptr2), text.charAt(ptr1))) {
				palindrome.insert(0, text.charAt(ptr1));
				palindrome.append(text.charAt(ptr2));
				count = count + 2;
				ptr1--;
				ptr2++;

			}
		}
		return palindrome.toString();
	}

	/*
	 * extracts the string centered at center with of size length. length should
	 * be odd.
	 */
	private static String extractOddString(String text, int center, int length)
			throws IndexOutOfBoundsException {
		StringBuilder palindrome = new StringBuilder();
		palindrome.append(text.charAt(center));
		int count = 1;
		int ptr1 = center - 1, ptr2 = center + 1;
		while (count < length) {
			while (!isAlphabet(text.charAt(ptr1))) {
				palindrome.insert(0, text.charAt(ptr1));
				ptr1--;
			}
			while (!isAlphabet(text.charAt(ptr2))) {
				palindrome.append(text.charAt(ptr2));
				ptr2++;
			}
			if (areSameLetters(text.charAt(ptr2), text.charAt(ptr1))) {
				palindrome.insert(0, text.charAt(ptr1));
				palindrome.append(text.charAt(ptr2));
				count = count + 2;
				ptr1--;
				ptr2++;
			}
		}
		return palindrome.toString();
	}

	/*
	 * ignores the case of the characters passed in and checks for equality.
	 */
	private static boolean areSameLetters(char char1, char char2) {
		return (char1 == char2 || char1 + 'A' - 'a' == char2 || char2 + 'A' - 'a' == char1);
	}

}