package csc223Giraffes;

import java.util.Scanner;

public class Neck {

	public static void main(String[] args) { //begin main 
		
		Scanner scan = new Scanner(System.in);
		int minArrivalTime; 
		int maxArrivalTime; 
		int minServiceTime; 
		int maxServiceTime; 
		int numCustomers;

		System.out.println("Enter minimum arrival time between customers:");
		minArrivalTime = scan.nextInt();

		System.out.println("Enter maximum arrival time between customers:");
		maxArrivalTime = scan.nextInt();

		System.out.println("Enter minimum service time:");
		minServiceTime = scan.nextInt();

		System.out.println("Enter maximum service time:");
		maxServiceTime = scan.nextInt();

		System.out.println("Enter number of customers to serve:");
		numCustomers = scan.nextInt();
		
		
	} //end main 
	
	public static int randomGenerator(int minArrivalTime, int maxArrivalTime) { //begin randomGenerator  
		
		 // Calculate the range of values
	    int range = maxArrivalTime - minArrivalTime + 1;
	    // Generate a random number within the range
	    int randomArrivalTime = (int)(Math.random() * range) + minArrivalTime;
	    // Return the random number
	    return randomArrivalTime;
		
	} //end randomGenerator 
	
}
