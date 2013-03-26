/*
ID: nikhil_3
LANG: JAVA
TASK: crypt1
FILE: crypt1.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class crypt1 {

	public static void main(String[] args) throws IOException {

		// read in the input
		int nDigits;
		HashSet<Integer> digitSet = new HashSet<Integer>();
		BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
		nDigits = Integer.parseInt(in.readLine());
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		for (int i = 0; i < nDigits; i++) {
			int nextDigit = Integer.parseInt(tokenizer.nextToken());
			digitSet.add(nextDigit);
		}
		in.close();

		// find the number of valid cryptarithms
		if (digitSet.size() == 0)
			return;

		int nCryptarithms = nValidCryptarithms(3, 2, 3, 4, digitSet);

		// write the output to the specified file
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"crypt1.out")));
		out.println(nCryptarithms);
		out.close();

	}

	/*
	 * finds the total number of valid cryptarithms
	 */
	private static int nValidCryptarithms(int length1, int length2,
			int partialProductLength, int productLength,
			HashSet<Integer> digitSet) {
		if (length1 <= 0 || length2 <= 0) {
			throw new InvalidParameterException(
					"Negative length parameter passed in");
		}

		int count = 0;

		// get all possible arrangements of length1 + length2 elements from
		// digitSet with repitition allowed
		ArrayList<String> arrangements = getCombinations(digitSet, length1
				+ length2);

		// go thru each each combination that was generated above and check if
		// the two numbers specified generate a valid cryptarithm
		for (String str : arrangements) {
			int firstNumber = Integer.parseInt(str.substring(0, length1));
			int secondNumber = Integer.parseInt(str.substring(length1));
			if (isValidCryptarithm(firstNumber, secondNumber,
					partialProductLength, productLength, digitSet))
				count++;
		}
		return count;
	}

	/*
	 * generates all possible arrangements of integer strings and returns them
	 * as a list. While choosing digits, repitition is allowed.
	 */
	private static ArrayList<String> getCombinations(Set<Integer> set,
			int length) {

		if (length < 0)
			throw new InvalidParameterException("Negative length parameter: "
					+ length);

		ArrayList<String> list = new ArrayList<String>();

		// base case
		if (length == 0) {
			list.add("");
			return list;
		}

		for (Integer validDigit : set) {
			ArrayList<String> subCombinations = getCombinations(set, length - 1);
			for (String subCombination : subCombinations) {
				list.add(validDigit.toString() + subCombination);
			}
		}
		return list;
	}

	/*
	 * checks if a cryptarithm is valid
	 */
	private static boolean isValidCryptarithm(int firstNumber,
			int secondNumber, int partialProductLength, int productLength,
			HashSet<Integer> set) {
		if (!hasValidDigits(firstNumber, set)) {
			return false;
		}

		if (!hasValidDigits(secondNumber, set)) {
			return false;
		}

		if (!hasValidDigits(firstNumber * secondNumber, set)) {
			return false;
		}

		if (!(String.valueOf(firstNumber * secondNumber).length() == productLength)) {
			return false;
		}

		while (secondNumber != 0) {
			int lastDigit = secondNumber % 10;
			secondNumber = secondNumber / 10;
			if (!hasValidDigits(lastDigit * firstNumber, set))
				return false;
			if (!(String.valueOf(lastDigit * firstNumber).length() == partialProductLength)) {
				return false;
			}
		}

		return true;
	}

	/*
	 * checks if all the digits of the given number are contained in the set
	 */
	private static boolean hasValidDigits(int number, HashSet<Integer> set) {
		while (number != 0) {
			int lastDigit = number % 10;
			number = number / 10;
			if (!set.contains(lastDigit))
				return false;
		}
		return true;
	}

}
