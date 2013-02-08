/*
ID: nikhil_3
LANG: JAVA
TASK: milk
FILE: milk.java
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class milk {

	public static void main(String[] args) throws IOException {
		// read in the price and quantity of each farmer store in an array.
		BufferedReader in = new BufferedReader(new FileReader("milk.in"));
		StringTokenizer line = new StringTokenizer (in.readLine());
		
		int nMilkRequired = Integer.parseInt(line.nextToken());
		int nFarmers = Integer.parseInt(line.nextToken());
		milk problem = new milk();
		int minAmountNeeded = 0, filledUpQuantity = 0;

		// compute the price per gallon of each farmer and sort them in
		// increasing order of price per gallon
		MilkSource[] sources = new MilkSource[nFarmers];
		for (int i = 0; i < sources.length; i++){
			line = new StringTokenizer (in.readLine());
			int price = Integer.parseInt(line.nextToken()), quantity = Integer.parseInt(line.nextToken());
			sources[i] = problem.new MilkSource(quantity, price);
		}
		Arrays.sort(sources);
		
		// buy as much as possible from each farmer until all the required milk has been acquired
		
		// this loop doesn't make safety checks because of the guarantee that there are enough milk sources
		int arrayIndex = 0;
		int nCurrentQuantity;
		while (filledUpQuantity != nMilkRequired){ // note that this handles the nMilkRequired = 0 case.
			nCurrentQuantity = Math.min(sources[arrayIndex].quantity, nMilkRequired - filledUpQuantity);
			filledUpQuantity += nCurrentQuantity;
			minAmountNeeded += nCurrentQuantity*sources[arrayIndex].pricePerUnit;
			arrayIndex++;
		}

		// close all I/O files and return the computed price
		PrintWriter out = new PrintWriter (new FileWriter("milk.out"));
		out.println(minAmountNeeded);
		in.close();
		out.close();
		
	}

	/*
	 * Each object of this class represents the details of buying milk from a
	 * single farmer
	 */
	public class MilkSource implements Comparable<MilkSource>{
		public final int quantity, pricePerUnit;
		
		public MilkSource(int quantity, int price) {
			this.quantity = quantity;
			this.pricePerUnit = price;
		}	
		
		public int compareTo(MilkSource source2){
			return this.pricePerUnit - source2.pricePerUnit;
		}
	}
}
