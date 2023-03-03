package csc223Giraffes;

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

		populateLL(numCustomers, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
		
		// Create a CustomerCreator object with minimum and maximum interarrival/service times
		CustomerCreator creator = new CustomerCreator(numCustomers,  minArrivalTime,  maxArrivalTime,  minServiceTime,
				 maxServiceTime, 0,  0);

		// Create three queues (lines) for checkout
		Queue checkoutA = new Queue();
		Queue checkoutB = new Queue();
		Queue checkoutC = new Queue();

		// Create a Simulator object with the number of customers to simulate
		Simulator sim = new Simulator(20, creator, checkoutA, checkoutB, checkoutC);

		// Run the simulation and get the average waiting time
		double avgWaitTime = sim.run();

		// Print the average waiting time
		System.out.println("Average waiting time: " + avgWaitTime);

		// Print the details for each customer
		sim.printCustomerDetails();

		// Print the total time that checkout lanes were not in use
		System.out.println("Total idle time: " + sim.getIdleTime());

		// Print the number of satisfied and dissatisfied customers
		int satisfied = sim.getSatisfiedCustomers();
		int dissatisfied = sim.getDissatisfiedCustomers();
		System.out.println("Satisfied customers: " + satisfied);
		System.out.println("Dissatisfied customers: " + dissatisfied);


	} // end main

	public static void populateLL(int numCustomers, int minArrivalTime, int maxArrivalTime, int minServiceTime,
			int maxServiceTime) {

		// create an instance of the CostumerCreator class
		CustomerCreator cc = new CustomerCreator(numCustomers, minArrivalTime, maxArrivalTime, minServiceTime,
				maxServiceTime, 0, 0);

		cc.populateCustomers(); // populate the ArrayList of Customers method

		CheckoutLL ll = new CheckoutLL();

		// loop thru generated ArrayList of Customers and add it
		// to the linked list
		for (int i = 0; i < cc.getCustomers().size(); i++) {
			ll.add(cc.getCustomers().get(i));
		}
	}
	
	public static void runSim() {
		
	}

}
