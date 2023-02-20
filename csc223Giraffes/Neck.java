package csc223Giraffes;

import java.util.Scanner;

public class Neck {

	public static void main(String[] args) {
		
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
		
		
	}
}
