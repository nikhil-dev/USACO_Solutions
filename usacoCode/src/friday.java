/*
ID: nikhil_3
LANG: JAVA
TASK: friday
FILE: friday.java
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class friday {
	public static void main(String[] args) throws Exception{
		
		// giving information about number of days in a year
		HashMap<Integer, Integer> daysIn = new HashMap<Integer,Integer>();
		daysIn.put(1, 31);
		daysIn.put(2, 28);
		daysIn.put(3, 31);
		daysIn.put(4, 30);
		daysIn.put(5, 31);
		daysIn.put(6, 30);
		daysIn.put(7, 31);
		daysIn.put(8, 31);
		daysIn.put(9, 30);
		daysIn.put(10, 31);
		daysIn.put(11, 30);
		daysIn.put(12, 31);
		daysIn.put(13, 29);
		
		// read in the number of years over which the study is done
		BufferedReader in = new BufferedReader(new FileReader("friday.in"));
		int n = Integer.parseInt(in.readLine());
		
		// create an array, that keeps track of the frequency of 13th of a month happening on different days of the week
		int [] frequencies = new int [7]; // all places initialized to 0 on instantiation
		
		// iterate over the years and update the array
		int finalYear = 1900 + n;
		
		// 0 - Monday, 1 - Tuesday, 2 - Wednesday .. etc and the problem says that the first day is a Monday
		int dayOf13th = 2; // the last 13th that occured (December 13, 1899) was a Wednesday (=2)
		
		// for each month, calculate what day the 13th was and store the results in the int [] frequencies
		for (int year = 1900; year < finalYear; year++){
			for (int month = 1; month <= 12; month++){
				dayOf13th = whatDayIs13th(daysIn, month, dayOf13th, year);
				frequencies[dayOf13th]++;
			}
		}
		
		// print the results to a file
		PrintWriter out = new PrintWriter (new BufferedWriter (new FileWriter("friday.out")));
		print (frequencies, out);
		
		in.close();
		out.close();

	}
	
	private static int whatDayIs13th (Map<Integer, Integer> daysIn, int month, int dayOf13th, int year){
		if (month == 1){ // if its january
			int daysBetween13ths = daysIn.get(12);
			dayOf13th = (dayOf13th + daysBetween13ths) % 7;
		}
		else if (month != 3){ // if month is not march
			int daysBetween13ths = daysIn.get((month - 1 + 12)%12);
			dayOf13th = (dayOf13th + daysBetween13ths) % 7;
		}
		else{ // if the month is march, leap year or not needs to be considered
			if (!isLeapYear(year)){
				int daysBetween13ths = daysIn.get((month - 1)%12);
				dayOf13th = (dayOf13th + daysBetween13ths) % 7;
			}
			else{
				int daysBetween13ths = daysIn.get(13);
				dayOf13th = (dayOf13th + daysBetween13ths) % 7;
			}
		}
		
		return dayOf13th;
		
	}
	
	private static boolean isLeapYear(int year){
		if (((year >> 2) << 2) != year){
			return false;
		}
		else if (year % 400 == 0){
			return true;
		}
		else if (year % 100 == 0){
			return false;
		}
		else {
			return true;
		}
	}
	
	private static void print (int [] array, PrintWriter out){
		int i = 5;
		for (int count = 0; count < array.length; count++){
			out.print(array[(i++)%7] + ((count != 6) ? " " : ""));
		}
		out.println();
	}
	
	private static void print (int [] array){
		int i = 5;
		for (int count = 0; count < array.length; count++){
			System.out.print((i)%7 + ": " + array[(i++)%7] + "      ");
		}
	}
}


