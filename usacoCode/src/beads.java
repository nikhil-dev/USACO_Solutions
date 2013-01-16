import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/*
ID: nikhil_3
LANG: JAVA
TASK: beads
FILE: beads.java
 */



public class beads {
    
    public static void main (String[] args) throws IOException{
    	
    	// initialize the file reader and the file writer
    	BufferedReader in = new BufferedReader(new FileReader("beads.in"));
    	PrintWriter out = new PrintWriter (new BufferedWriter (new FileWriter("beads.out")));
    	
    	// read in the string
    	in.readLine(); // ignore the first line of input which is the length of the string
    	String str = in.readLine();
    	in.close();
    	
    	if (str.length() == 0){
    		out.println(0);
    		out.close();
    		// return
    	}
    	
    	// initialize some variables needed to keep track of the details
    	int currentStartPosition = 0, currentFirstLength = 0, currentSecondLength = 0;
    	int longestFirstLength = 0, longestSecondLength = 0;
    	int currentWLength = 0;
    	int i; // i has been declared outside the for loop because it needs to be in scope after the for loop has completed execution
    	boolean isFirstString = true;
    	char current = str.charAt(0);
    	
    	for (int j = 0; j < str.length(); j++){
    		if (str.charAt(j) != 'w')
    			current = str.charAt(j);
    	}
    	
    	
    	// traverse to find the longest pair of sequences (whose sum of lengths will give the answer)
    	for (i = 0; i < str.length(); i++){
    		if (isFirstString){
    			if (str.charAt(i) == current){
    				currentFirstLength++;
    				currentWLength = 0;
    			}
    			else if (str.charAt(i) == 'w'){
    				currentWLength++;
    				currentFirstLength++;
    			}
    			else{
    				isFirstString = false;
    				current = str.charAt(i);
    				currentFirstLength -= currentWLength;
    				currentSecondLength = 1;
    				currentSecondLength += currentWLength;
    				currentWLength = 0;
    			}
    		}
    		
    		else {
    			if (str.charAt(i) == current){
    				currentSecondLength++;
    				currentWLength = 0;
    			}
    			else if (str.charAt(i) == 'w'){
    				currentWLength++;
    				currentSecondLength++;
    			}
    			else{
    				if (currentFirstLength + currentSecondLength > longestFirstLength + longestSecondLength) {
    					longestFirstLength = currentFirstLength;
    					longestSecondLength = currentSecondLength;
    				}
    				currentStartPosition = i - currentSecondLength - currentWLength;
    				current = str.charAt(i);     // JUST CHANGED FROM FIRST TO SECOND. AFTER THIS CONSIDER, CHANGE FROM SECOND TO SECOND. CONSIDER WHEN TO CHECK FOR NEW LONGEST AND UPDATE.
    				currentFirstLength = currentSecondLength;
    				currentFirstLength -= currentWLength;
    				currentSecondLength = 1;
    				currentSecondLength += currentWLength;
    				currentWLength = 0;
    			}
    		}
//    		System.out.println("First: " + currentFirstLength + "   Second: " + currentSecondLength);
//    		System.out.println(str.charAt(i));
//    		System.out.println();
    	}
    	
    	boolean isFirstOnWrap = true;
    	// another for loop to wrap around the string to check if the longest string begins at the tail of the sting and ends near the head.
    	for (int j = 0; j < currentStartPosition; j++){
    		if (str.charAt(j) == current || str.charAt(j) == 'w'){
    			currentSecondLength++;
    		}
    		else {
    			if (isFirstOnWrap){
    				if (currentFirstLength + currentSecondLength > longestFirstLength + longestSecondLength) {
    					longestFirstLength = currentFirstLength;
    					longestSecondLength = currentSecondLength;
    				}
    				currentFirstLength = currentSecondLength;
    				currentSecondLength = 1;
    				current = str.charAt(j);
    			}
    			else{
    				break;
    			}
    		}
    	}
    	
    	if (currentFirstLength + currentSecondLength > longestFirstLength + longestSecondLength) {
			longestFirstLength = currentFirstLength;
			longestSecondLength = currentSecondLength;
		}
    	
    	// print the results and close the opened files
    	out.println(longestFirstLength + longestSecondLength);
    	out.close();
    }
    
}
