import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
ID: nikhil_3
LANG: JAVA
TASK: milk2
FILE: milk2.java
 */


public class milk2 {

	public static void main (String [] args) throws IOException{
		// setup the input reader and output writer objects
		BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter (new BufferedWriter (new FileWriter("milk2.out")));
		int nCows = Integer.parseInt(in.readLine());
		
		// if empty, quit program
		if (nCows == 0){
			System.out.println(0 + " " + 0);
			out.close();
			in.close();
			return;
		}
		
		// make objects out of every time interval and put these in an array
		TimeInterval[] times = new TimeInterval[nCows]; 
		StringTokenizer line;
		for (int i = 0; i < times.length; i++){
			line = new StringTokenizer(in.readLine());
			int startTime = Integer.parseInt(line.nextToken());
			int endTime = Integer.parseInt(line.nextToken()); 
			times[i] = new TimeInterval(startTime, endTime);
		}
		
		// sort the array
		Arrays.sort(times);
		
		// setup and initialize variables to keep track of current and max values
		int longestMilkingTime = times[0].interval(), longestIdleTime = 0, currentEndTime = times[0].endTime;
		int currentMilkingTime = times[0].interval(), currentIdleTime = 0;
		
		// loop to find the longestMilkingTime
		for (int i = 1; i < times.length; i++){ 
			if (times[i].endTime <= currentEndTime){
				continue;
			}
			else if (times[i].startTime > currentEndTime){
				currentIdleTime = times[i].startTime - currentEndTime;
				currentMilkingTime = times[i].interval();
				currentEndTime = times[i].endTime;
				if (currentMilkingTime > longestMilkingTime)
					longestMilkingTime = currentMilkingTime;
				if (currentIdleTime > longestIdleTime)
					longestIdleTime = currentIdleTime;
			}
			else{
				currentMilkingTime += times[i].endTime - currentEndTime;
				currentEndTime = times[i].endTime;
				if (currentMilkingTime > longestMilkingTime)
					longestMilkingTime = currentMilkingTime;
			}
		}
		
		// write the results to the opened file and exit
		out.println(longestMilkingTime + " " + longestIdleTime);
		out.close();
		in.close();
			
		
	}
	
	
	public static class TimeInterval implements Comparable<TimeInterval> {
		int startTime;
		int endTime;
		
		public TimeInterval(int startTime, int endTime){
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		public int interval(){
			return endTime - startTime;
		}
		
		public int compareTo(TimeInterval interval) {
			return startTime - interval.startTime;
		}	
	}
	
}
