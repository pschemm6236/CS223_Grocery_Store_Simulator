package csc223Giraffes;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

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
				 maxServiceTime);
		
		// call populateCustomers method within our creator to fill customers ArrayList with Customer objects 
		creator.populateCustomers(customers);
		
		// (DEBUGGER IGNORE)
		//printDebug(customers);
		
		// Create three queues (lines) for checkout
		QueueLL checkoutA = new QueueLL();
		QueueLL checkoutB = new QueueLL();
		QueueLL checkoutC = new QueueLL();

		// Create a Simulator object with the number of customers to simulate 
		// and pass it our Customer ArrayList and Queue objects
		Simulator sim = new Simulator(customers, checkoutA, checkoutB, checkoutC);
		
		sim.runSimulation();
		
		scan.close();
		
		// call static methods to print data from simulation 
		printSimResults(numCustomers, customers, checkoutA, checkoutB, checkoutC);
		printSimResultsTable(numCustomers, customers, checkoutA, checkoutB, checkoutC);

		
		// (DEBUGGER IGNORE)
		// printDebug(customers);
		
	} // end main
	
	// - - - - - - - - - - - - - static methods - - - - - - - - - - - - -
	
	// Takes the original customers ArrayList and QueueLL objects to gather all simulation results and print
	public static void printSimResults(int numCust,ArrayList<Customer> customers, QueueLL cha, QueueLL chb, QueueLL chc) {
		
		int totalCustWaitTime = 0;
		double averageCustWaitTime = 0;
		int totalChkoutNoUseTime = 0;
		int satisfiedCust = 0;
		int dissatisfiedCust = 0;
		DecimalFormat df = new DecimalFormat("##.#");
		
		System.out.println("\n----- Simulation Data -----\n");
		
		// for loop all the customers to get each of their wait times and number satisfied
		for(int i=0; i < customers.size(); i++) {
			
			int ithCustWaitTime = (customers.get(i).waitingTime());
			totalCustWaitTime = totalCustWaitTime + ithCustWaitTime;
			
			// check for satisfied/non
			if(ithCustWaitTime < 5) {
				satisfiedCust++;
			}
			else
				dissatisfiedCust++;
		}
		
		// calculate the average wait time
		averageCustWaitTime = (double) totalCustWaitTime / numCust;
		
		// calculate the total time for the three lines 
		totalChkoutNoUseTime = (cha.getTimeNotUsed()+chb.getTimeNotUsed()+chc.getTimeNotUsed());
		
		// print results
		System.out.println("Average wait: " + df.format(averageCustWaitTime) + " min.");
		System.out.println("Total time checkouts were not in use: " + totalChkoutNoUseTime + " minutes");
		System.out.println("Customer satisfaction: " + satisfiedCust + " satisfied (<5 minutes) " 
				+ dissatisfiedCust + " dissatisfied (>=5 minutes)");
		
	} // end printSimResults
	
	// Takes the data and prints out formatted table  
	public static void printSimResultsTable(int numCust,ArrayList<Customer> customers, QueueLL cha, 
			QueueLL chb, QueueLL chc) {
		
		System.out.println();
		System.out.println("|-------|------------------------|--------------|----------------------------|-------------|");
		System.out.println("| Cust #| Arrival Time (absolute)| Service Time |  Departure Time (absolute) |  Wait Time  |");
		System.out.println("|-------|------------------------|--------------|----------------------------|-------------|");


		// for loop each row of table to print data
		for (int i=0; i < customers.size(); i++) {
			
			Customer ithCustomer = customers.get(i);
					
		    System.out.printf("| %5d | %22d | %12d | %26d | %11d |\n", 
		    		ithCustomer.getCustId(), ithCustomer.getArrivalTime(), 
		    		ithCustomer.getServiceTime(), ithCustomer.getEndTime(), 
		    		ithCustomer.waitingTime());
		    System.out.println("|-------|------------------------|--------------|----------------------------|-------------|");
		}
	
	} // end printSimResultsTable
	
	// Takes the original customers ArrayList and prints out all the Customer objects inside (to see all their time values)
	public static void printDebug(ArrayList<Customer> customers) {
		
		for(int i=0;i<customers.size();i++) {
				System.out.println(customers.get(i).toString());
		}
		
	} // end printDebug
	
	public static void printRecSet() {
		
		System.out.println("Recommeded Settings:");
		
	} // end printRecSet
	
} // end class SimulatorDriver
