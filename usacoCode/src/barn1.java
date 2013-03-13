/*
ID: nikhil_3
LANG: JAVA
TASK: barn1
FILE: barn1.java
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class barn1 {

	public static void main(String[] args) throws Exception {
		// read in the input
		BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
		StringTokenizer tokenizer = new StringTokenizer(in.readLine());
		int nBoards = Integer.parseInt(tokenizer.nextToken());
		int nStalls = Integer.parseInt(tokenizer.nextToken());
		int nCows = Integer.parseInt(tokenizer.nextToken());
		int[] occupiedStalls = new int[nCows];
		for (int i = 0; i < nCows; i++) {
			occupiedStalls[i] = Integer.parseInt(in.readLine());
		}
		in.close();
		
		// sort the occupied stalls in order of stall numbers
		Arrays.sort(occupiedStalls);

		// pick out nBoards sets of consecutive empty stalls ranked by number of
		// empty stalls. Each of these sets will contain unblocked stalls.
		int[][] unblockedStalls = nLargestBreaks(occupiedStalls, nBoards - 1);

		// find the number of blocked stalls. This number is the length of the
		// complement of the set of stalls picked in the above step.
		int nBlockedStalls = getNBlockedStalls(unblockedStalls, occupiedStalls,
				nStalls);

		// write the output to a file
		PrintWriter out = new PrintWriter(new FileWriter("barn1.out"));
		out.println(nBlockedStalls);
		out.close();
	}

	/*
	 * This method finds the locations of the n largest breaks in a series of
	 * numbers and returns these numbers.
	 */
	private static int[][] nLargestBreaks(int[] numbers, int nBreaks)
			throws Exception {
		/*
		 * This class encapsulates the abstract concept of a break in the
		 * numbers
		 */
		class Gap implements Comparable<Gap> {
			int start, end, length;

			Gap(int start, int end, int length) {
				this.start = start;
				this.end = end;
				this.length = length;
			}

			public int compareTo(Gap gap2) {
				return this.length - gap2.length;
			}
		}
		if (nBreaks == 0){
			return new int[2][0];
		}
		
		if (nBreaks > numbers.length - 1)
			nBreaks = numbers.length - 1;

		if (nBreaks < 0)
			throw new Exception("Parameter nBreaks is negative");

		// go through the numbers and pick the nBreaks largest gaps
		PriorityQueue<Gap> pq = new PriorityQueue<Gap>();
		for (int i = 0; i < numbers.length - 1; i++) {
			int length = numbers[i + 1] - numbers[i] - 1;
			int start = numbers[i] + 1;
			int end = numbers[i + 1] - 1;
			System.out.println("hi");
			if (pq.size() < nBreaks) {
				pq.add(new Gap(start, end, length));
			} else {
				if (length > pq.peek().length) {
					pq.poll();
					pq.add(new Gap(start, end, length));
				}
			}
		}

		// convert the list of Gap objects into int[][].
		// Return type of int[][] is preferred over List<Gap> in the interest of
		// re-usability of this method
		int[][] breaks = new int[2][pq.size()];
		Gap currentGap;
		int length = pq.size();
		for (int i = 0; i < length; i++) {
			currentGap = pq.poll();
			breaks[0][i] = currentGap.start;
			breaks[1][i] = currentGap.end;
		}

		return breaks;
	}

	/*
	 * This method returns the number of stalls that are blocked. Note: This
	 * method does not handle invalid inputs since the method is specific to
	 * this problem and the caller ensures the correctness of inputs.
	 */
	private static int getNBlockedStalls(int[][] unblockedStalls,
			int[] occupiedStalls, int nStalls) {
		// subtract the stalls that have not been blocked from the total number
		// of stalls
		int nBlockedStalls = nStalls;
		for (int i = 0; i < unblockedStalls[0].length; i++) {
			nBlockedStalls -= unblockedStalls[1][i] - unblockedStalls[0][i] + 1;
		}

		// subtract all stalls uptil the first occupied stall and after the last
		// occupied stall since these will never need to be covered
		if (occupiedStalls.length != 0) {
			nBlockedStalls -= occupiedStalls[0] - 1;
			nBlockedStalls -= nStalls
					- occupiedStalls[occupiedStalls.length - 1];
		}

		return nBlockedStalls;
	}

}