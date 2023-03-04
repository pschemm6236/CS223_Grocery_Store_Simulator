package csc223Giraffes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SimulatorDriver {

	public static void main(String[] args) { // begin main

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
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		CustomerCreator creator = new CustomerCreator(numCustomers,  minArrivalTime,  maxArrivalTime,  minServiceTime,
				 maxServiceTime, 0,  0);
		
		creator.populateCustomers(customers);

		
		sortByAT(customers);
		
		// Create a CustomerCreator object with minimum and maximum interarrival/service times
		

		// Create three queues (lines) for checkout
		Queue checkoutA = new Queue();
		Queue checkoutB = new Queue();
		Queue checkoutC = new Queue();

		// Create a Simulator object with the number of customers to simulate
		Simulator sim = new Simulator(numCustomers, creator, checkoutA, checkoutB, checkoutC);

		// Run the simulation and get the average waiting time
		double avgWaitTime = sim.run3(customers);
		

		// Print the average waiting time
		System.out.println("Average waiting time: " + avgWaitTime);

		// Print the details for each customer
		sim.printCustomerDetails();

		// Print the total time that checkout lanes were not in use
		//System.out.println("Total idle time: " + sim.getIdleTime());

		// Print the number of satisfied and dissatisfied customers
		//int satisfied = sim.getSatisfiedCustomers();
		//int dissatisfied = sim.getDissatisfiedCustomers();
		//System.out.println("Satisfied customers: " + satisfied);
		//System.out.println("Dissatisfied customers: " + dissatisfied);


	} // end main

	
	public static void sortByAT(ArrayList<Customer> c) {
		
		Collections.sort(c);
		
		int id = 1;
		for(int i = 0; i < c.size(); i++) {
			//for each sorted customer, after sorted assign them their ID number 
			c.get(i).assignId( id);
			id++;
			System.out.println(c.get(i).toString());
		}	
	}
}
