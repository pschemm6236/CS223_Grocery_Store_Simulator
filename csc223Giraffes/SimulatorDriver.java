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

		// GET USER SIMULATION SETTINGS 
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
		
		// ArrayList to Store Customer Objects that is defined here in main AND passed by reference to wherever 
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		// CustomerCreator object which takes all USER SIMULATION SETTINGS
		CustomerCreator creator = new CustomerCreator(numCustomers,  minArrivalTime,  maxArrivalTime,  minServiceTime,
				 maxServiceTime, 0,  0);
		
		// call populateCustomers method within our creator to fill customers ArrayList with Customer objects 
		creator.populateCustomers(customers);

		// call static method to sort the filled ArrayList (By ascending arrival time)
		sortByAT(customers);
		
		// Create three queues (lines) for checkout
		Queue checkoutA = new Queue();
		Queue checkoutB = new Queue();
		Queue checkoutC = new Queue();

		// Create a Simulator object with the number of customers to simulate 
		// and pass it our Customer ArrayList and Queue objects
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
	
	// - - - - - - - - - - - - - static methods - - - - - - - - - - - - -
	
	/* When called takes a pass by reference ArrayList of Customer,
	   sorts it by ascending arrival time and assigns id's based on where
	   Customer object is in ArrayList
	*/
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
	
} // end class SimulatorDriver
