/*
ID: nikhil_3
LANG: JAVA
TASK: gift1
FILE: gift1.java
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.StringTokenizer;


public class gift1 {
	public static void main (String [] args) throws Exception{
		
//		PrintWriter outer = new PrintWriter (new BufferedWriter (new FileWriter("test.out")));
//		outer.println("hi");
//		outer.close();
//		
		// setup an input stream
		BufferedReader in = new BufferedReader (new FileReader ("gift1.in")); 
		
		// make a table
		int nPeople = Integer.parseInt(in.readLine());
		// the value stored in the map keeps track of the total given by each person (which is the result we ultimately want)
		LinkedHashMap<String, Integer> table = new LinkedHashMap<String, Integer> (nPeople);
			// A linked hashmap has been used because the problem requires us to print the values in the order they were added
		
		// populate it
		for (int i = 0; i < nPeople; i++){
			String current = in.readLine();
			table.put(current, 0);
		}
		
		//System.out.println("---------");
		
		StringTokenizer tokenizer;
		// do updates for each person
		for (int i = 0; i < nPeople; i++){
			String currentGiver = in.readLine();
			tokenizer = new StringTokenizer(in.readLine());
			
			// the initial amount the currentGiver has
			int initialAmount = Integer.parseInt(tokenizer.nextToken());
			
			// the number of people currentGiver gives to
			int nGifts = Integer.parseInt(tokenizer.nextToken());
						
			if (nGifts == 0)
				continue;
			int eachGift = initialAmount/nGifts;
			
			// update the net amount that the current guy has given uptil now.
			int totalGiven = table.get(currentGiver);
			totalGiven += eachGift*nGifts;
			table.put(currentGiver, totalGiven);
			
			// update the amounts of the people who receive from currentGiver
			for (int j = 0; j < nGifts; j++){
				String beneficiary = in.readLine();
				//System.out.println(beneficiary);
				int beneficiaryGiven = table.get(beneficiary);
				beneficiaryGiven -= eachGift;
				table.put(beneficiary, beneficiaryGiven);
			}
		}
		
		// print each person along with total amount given to a file
		PrintWriter out = new PrintWriter (new BufferedWriter(new FileWriter("gift1.out")));
		Set<String> keySet = table.keySet();
		for (String person : keySet){
			out.println(person + " " + -1*table.get(person));
		}
		
		
		// close the input and output streams
		out.close();
		in.close();
	}
}
