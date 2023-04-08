package csc223Giraffes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import java.text.DecimalFormat;

/**
 * 
 * @author Troy F.
 * @author Parker S.
 *
 */

public class SimulatorDriver {

	public static void main(String[] args) { // begin main

		Scanner scan = new Scanner(System.in);

		int fullServiceLines;
		int selfServiceLines;
		int minArrivalTime;
		int maxArrivalTime;
		int minServiceTime;
		int maxServiceTime;
		int numCustomers;

		printRecSet();

		// GET USER SIMULATION SETTINGS
		System.out.println("Number of full-service lines:");
		fullServiceLines = scan.nextInt();

		System.out.println("Numberr of self-service lines:");
		selfServiceLines = scan.nextInt();

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

		System.out.println("Percentage slower for SELF: ");
		double percentSlower = scan.nextDouble();

		// ArrayList to Store Customer Objects that is defined here in main AND passed
		// by reference to wherever
		ArrayList<Customer> customers = new ArrayList<Customer>();

		// CustomerCreator object which takes all USER SIMULATION SETTINGS
		CustomerCreator creator = new CustomerCreator(numCustomers, minArrivalTime, maxArrivalTime, minServiceTime,
				maxServiceTime);

		// call populateCustomers method within our creator to fill customers ArrayList
		// with Customer objects
		creator.populateCustomers(customers);

		ArrayList<Queue> fullQueues = new ArrayList<Queue>();
		String[ ] selfQueues = new String[selfServiceLines];

		fullQueues = createFullQueues(fullServiceLines);

		selfQueues = createSelfQueues(selfServiceLines);

		/**Queue checkoutAQueue = new Queue("A");
		Queue checkoutBQueue = new Queue("B");
		Queue checkoutCQueue = new Queue("C");
		**/
		SplitQueue selfCheckoutQueue = new SplitQueue(selfQueues);
		

		// Create a Simulator object with the number of customers to simulate
		// and pass it our Customer ArrayList and Queue objects
		Simulator sim = new Simulator(customers, fullQueues, selfCheckoutQueue, percentSlower);

		System.out.println("\n----- Starting Simulation -----\n");
		sim.runSimulation();

		scan.close();

		// call static methods to print data from simulation
		printSimResults(customers, fullQueues, selfCheckoutQueue);
		printSimResultsTable(customers);

	} // end main

	// - - - - - - - - - - - - - static methods - - - - - - - - - - - - -

	public static ArrayList<Queue> createFullQueues(int full) {
		ArrayList<Queue> fullQ = new ArrayList<Queue>();

		int counter = 0;
		
		
		for (int i = 0; i < full; i++) {
			String queueName = "";
			if (counter < 26) {
				queueName = Character.toString((char) ('A' + counter ));
	        } else {
	            int suffix = ((counter - 26) / 26) + 1;
	            queueName = Character.toString((char) ('A' + (counter - 26) % 26)) + suffix;
	        }
	        Queue newQueue = new Queue(queueName);
	        fullQ.add(newQueue);
	        counter++;
	    }

		return fullQ;
	}

	public static String[] createSelfQueues(int self) {

		String[] selfQueues = new String[self];

	   
	
	    int counter = 0;
	    
	    for (int i = 0; i < self; i++) {
	    	String queueName = "SC -";
	    	
	    	if(counter < 26) {
	    		queueName += Character.toString((char) ('A' + counter));
	    		counter++;
	    		
	    	}
	    	else {
	            int suffix = ((counter - 26) / 26) + 1;
	            String tempName =  Character.toString((char) ('A' + (counter - 26) % 26)) + suffix;
	    		queueName += tempName;
	    		counter++;
	    	}
	    	selfQueues[i] = queueName;
	    }
	    return selfQueues;
	}

	// Takes the original customers ArrayList and Queue objects to gather all
	// simulation results and print
	public static void printSimResults(ArrayList<Customer> customers, ArrayList<Queue> fullQueues, SplitQueue self) {

		int satisfiedCust = 0;
		int dissatisfiedCust = 0;
		DecimalFormat df = new DecimalFormat("##.#");

		System.out.println("\n----- Simulation Data -----\n");

		// for loop all the customers to get each of their wait times and number
		// satisfied
		for (int i = 0; i < customers.size(); i++) {

			int ithCustWaitTime = (customers.get(i).waitingTime());

			// check for satisfied/non
			if (ithCustWaitTime < 5) {
				satisfiedCust++;
			} else
				dissatisfiedCust++;
		}

		double totalWaitTimeFull = 0;
		int numCustomersFull = 0;
		double totalWaitTimeSelf = 0;
		int numCustomersSelf = 0;

		for (Customer customer : customers) {
			double waitTime = customer.waitingTime();
			String line = customer.getUsedLine();

			if (line.contains("SC")) { // SELF line
				totalWaitTimeSelf += waitTime;
				numCustomersSelf++;
			} else { // FULL line
				totalWaitTimeFull += waitTime;
				numCustomersFull++;
			}
		}

		double avgWaitTimeFull = totalWaitTimeFull / numCustomersFull;
		double avgWaitTimeSelf = totalWaitTimeSelf / numCustomersSelf;

		System.out.println("Average wait time for FULL: " + df.format(avgWaitTimeFull)
				+ " min. With FULL serving a total of " + numCustomersFull + " for the day.");
		System.out.println("Average wait time for SELF: " + df.format(avgWaitTimeSelf)
				+ " min. With SELF serving a total of " + numCustomersSelf + " for the day.");

		
		int totalTimeNotUsedFull = 0;
		//loop thru ALL of fullChecouts, accumulate timenotused to variable
		for(int i = 0; i<fullQueues.size(); i++) {
			
			totalTimeNotUsedFull += fullQueues.get(i).getTimeNotUsed();
		}
		int totalTimeNotUsedSelf = self.getTotalTimeNotUsed();
		
		// print results
		System.out.println();
		System.out.println("Total time checkouts (FULL) were not in use: " + totalTimeNotUsedFull + " minutes");
		System.out.println("Total time checkouts (SELF) were not in use: " + self.getTotalTimeNotUsed() + " minutes");
		System.out.println("Customer satisfaction: " + satisfiedCust + " satisfied (<5 minutes) " + dissatisfiedCust
				+ " dissatisfied (>=5 minutes)");
		
		// output current line suggestions
	    System.out.println("\n----- Line Suggestions -----\n");
	   
	    
	    double idleWaitTimeThreshold = 2.0; // our ideal wait time for lines (can change as needed)
	    double idleTimeThreshold = 30.0; // our ideal idle time for lines (can change as needed)

	    if (isSignificantDifference(avgWaitTimeFull, avgWaitTimeSelf, idleWaitTimeThreshold)) {
	        if (avgWaitTimeFull > avgWaitTimeSelf) {
	            System.out.println("Consider adding a FULL checkout line to reduce wait time.");
	        } else {
	            System.out.println("Consider adding a SELF checkout line to reduce wait time.");
	        }
	    } else {
	        System.out.println("No need to add any lines.");
	    }

	    if (isSignificantDifference(totalTimeNotUsedFull, totalTimeNotUsedSelf, idleTimeThreshold)) {
	        if (totalTimeNotUsedFull > totalTimeNotUsedSelf && fullQueues.size() != 1) {
	            System.out.println("Consider removing a FULL checkout line to reduce idle time.");
	        } else if (self.size() != 1){
	            System.out.println("Consider removing a SELF checkout line to reduce idle time.");
	        }
	    } else {
	        System.out.println("No need to remove any lines.");
	    }

	} // end printSimResults	
	
	// evaluates if there's a significant difference in the average wait times and idle times for FULL and SELF checkouts.
	private static boolean isSignificantDifference(double value1, double value2, double threshold) {
	    return Math.abs(value1 - value2) >= threshold;
	}

	// Takes the data and prints out formatted table
	public static void printSimResultsTable(ArrayList<Customer> customers) {

		System.out.println();
		System.out.println(
				"|-------|------------------------|--------------|----------------------------|-------------|-----------------|");
		System.out.println(
				"| Cust #| Arrival Time (absolute)| Service Time |  Departure Time (absolute) |  Wait Time  | Queue Location  |");
		System.out.println(
				"|-------|------------------------|--------------|----------------------------|-------------|-----------------|");

		// for loop each row of table to print data
		for (int i = 0; i < customers.size(); i++) {

			Customer ithCustomer = customers.get(i);

			System.out.printf("| %5d | %22d | %12d | %26d | %11d | %15s |\n", ithCustomer.getCustId(),
					ithCustomer.getArrivalTime(), ithCustomer.getServiceTime(), ithCustomer.getEndTime(),
					ithCustomer.waitingTime(), ithCustomer.getUsedLine());
			System.out.println(
					"|-------|------------------------|--------------|----------------------------|-------------|-----------------|");

		}

	} // end printSimResultsTable

	// Takes the original customers ArrayList and prints out all the Customer
	// objects inside (to see all their time values)
	public static void printDebug(ArrayList<Customer> customers) {

		for (int i = 0; i < customers.size(); i++) {
			System.out.println(customers.get(i).toString());
		}

	} // end printDebug

	// prints recommended settings on call
	public static void printRecSet() { // begin printRecSet

		System.out.println("Recommended Settings:");
		System.out.println("Number of full-service lines = 2");
		System.out.println("Numberr of self-service lines = 6");
		System.out.println("minimum arrival time = 1");
		System.out.println("maximum arrival time = 4");
		System.out.println("minimum service time = 3");
		System.out.println("maximum service time = 4");
		System.out.println("number of customers  = 3 to 12");
		System.out.println("Percentage slower for SELF = 25%");

		System.out.println();

	} // end printRecSet

} // end class SimulatorDriver
