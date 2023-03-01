package csc223Giraffes;

import java.util.Scanner;

public class Simulator {

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
		
		 
		//create an instance of the CostumerCreator class 
		CustomerCreator cc = new CustomerCreator(numCustomers, minArrivalTime, maxArrivalTime, 
				minServiceTime, maxServiceTime, 0, 0);
		
		cc.populateCustomers(); //populate the ArrayList of Customers method 
		
		
		
			
	} //end main 
	
	
	
	
	

}
