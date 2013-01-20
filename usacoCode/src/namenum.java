/*
ID: nikhil_3
LANG: JAVA
TASK: namenum
FILE: namenum.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
 * Note about the solution:
 * Although this solution takes a lot of preprocessing time (it indexes every word in the dictionary) and space, the preprocessing happens just once.
 * Thus, if the program is repeatedly used, it runs in O(1) time every time except the first time, because the preprocessing needs to be done only in the first run.
 */

public class namenum {

	public static void main(String[] args) throws IOException {

		// build a mapping from a number to its integer
		HashMap<Long, PriorityQueue<String>> map = getMap();

		// read in the integer
		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		long code = Long.parseLong(in.readLine());
		if (code == 234643)
			System.out.println(code);
		in.close();

		// print all the possible names for the code that was read in
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"namenum.out")));
		PriorityQueue<String> list = map.get(code);
		if (list == null)
			out.println("NONE");
		else {
			String name;
			while (list.size() != 0) {
				name = list.poll();
				out.println(name);
			}
		}

		out.close();

	}

	public static HashMap<Long, PriorityQueue<String>> getMap()
			throws IOException {

		// the map that will be populated and returned at the end of this method
		HashMap<Long, PriorityQueue<String>> map = new HashMap<Long, PriorityQueue<String>>();

		HashMap<Character, Character> characterToDigit = getCharToDigitMap();

		BufferedReader in = new BufferedReader(new FileReader("dict.txt"));
		String name;
		StringBuilder number = new StringBuilder(12); // this variable holds the
														// number that a name
														// corresponds to. A
														// StringBuilder is
														// chosen so that adding
														// a new digit as we
														// iterate through the
														// name is inexpensive.

		// read in a name from the dictionary, find the code that the name
		// corresponds to and add that <code, name> pair to the map
		while ((name = in.readLine()) != null) {
			// System.out.println("hi");
			number.delete(0, number.length()); // clear number to make space for
												// a new number
			for (int i = 0; i < name.length(); i++) {
				// System.out.println("Character: " + name.charAt(i));
				// System.out.println("digit: " +
				// characterToDigit.get(name.charAt(i)));
				// System.out.println();
				if (characterToDigit.get(name.charAt(i)) == null) {
					System.out.println("null shit: " + name.charAt(i));
				}
				number.append(characterToDigit.get(name.charAt(i)));

			}
			long cowCode = Long.parseLong(number.toString());
			if (map.containsKey(cowCode)) {
				map.get(cowCode).add(name);
			} else {
				PriorityQueue<String> q = new PriorityQueue<String>();
				q.add(name);
				map.put(cowCode, q);
			}
		}

		in.close();
		return map;
	}

	public static HashMap<Character, Character> getCharToDigitMap() {
		HashMap<Character, Character> map = new HashMap<Character, Character>();

		map.put('A', '2');
		map.put('B', '2');
		map.put('C', '2');
		map.put('D', '3');
		map.put('E', '3');
		map.put('F', '3');
		map.put('G', '4');
		map.put('H', '4');
		map.put('I', '4');
		map.put('J', '5');
		map.put('K', '5');
		map.put('L', '5');
		map.put('M', '6');
		map.put('N', '6');
		map.put('O', '6');
		map.put('P', '7');
		map.put('R', '7');
		map.put('S', '7');
		map.put('T', '8');
		map.put('U', '8');
		map.put('V', '8');
		map.put('W', '9');
		map.put('X', '9');
		map.put('Y', '9');
		map.put('Z', '0');
		map.put('Q', '0');

		return map;

	}

}