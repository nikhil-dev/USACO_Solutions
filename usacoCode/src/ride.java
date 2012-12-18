/*
ID: nikhil_3
LANG: JAVA
TASK: ride
FILE: ride.java
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ride {

	
	public static void main(String[] args) throws IOException {
		
		// setup input stream
		BufferedReader in = new BufferedReader(new FileReader("ride.in"));
		
		// read in the names of the comet and the group
		String comet, group;
		comet = in.readLine();
		group = in.readLine();
		
		// determine the result
		String result = willPickUp(comet, group) ? "GO" : "STAY";
		
		// print the result
		PrintWriter out = new PrintWriter (new BufferedWriter(new FileWriter("ride.out")));
		out.println(result);
		
		// close the files in use		
		out.close();
		in.close();
		
	}
	
	private static boolean willPickUp (String comet, String group){
		int cometProduct = 1, groupProduct = 1;
		
		// find product for comet
		for (int i = 0; i < comet.length(); i++){
			cometProduct = (cometProduct * ((int)(comet.charAt(i) - 'A') + 1)) % 47;
			System.out.println("i: " + cometProduct);
		}
		
		// find product for group
		for (int i = 0; i < group.length(); i++){
			groupProduct = (groupProduct * ((int)(group.charAt(i) - 'A') + 1)) % 47;
			System.out.println("j: " + groupProduct);
		}
		
		return (groupProduct == cometProduct);
	}

}
