package csc223Giraffes;

import java.util.ArrayList;
import java.util.Collections;

public class CustomerCreator { // begin Customer creator class

	/**
	 *          Class outline (required class)
	 * For each customer our program generates an
	 * arrival time and a service time. Through the simulation it then determines
	 * the finish time and, based on those values, calculates the waiting time. You
	 * will need a CustomerCreator class. An object of this class is passed the
	 * minimum and maximum interarrival and service times of the customers upon
	 * instantiation. Its primary responsibility is to generate and return the
	 * �next� customer when requested.
	 */
	
	/**
	 * @author Parker S. 
	 */

	private int numCustomers;
	// arrival time fields
	private int minArrivalTime;
	private int maxArrivalTime;
	// service time fields
	private int minServiceTime;
	private int maxServiceTime;	

	public CustomerCreator(int numCustomers, int minArrivalTime, int maxArrivalTime, int minServiceTime,
			int maxServiceTime) {
		this.numCustomers = numCustomers;
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minServiceTime = minServiceTime;
		this.maxServiceTime = maxServiceTime;
	}

	public int randomArrival(int minArrivalTime, int maxArrivalTime) { // begin randomGenerator
		// Calculate the range of values
		int range = maxArrivalTime - minArrivalTime + 1;
		
		// Generate a random number within the range
		int randomArrivalTime = (int) (Math.random() * range) + minArrivalTime;
		
		// Return the random number
		return randomArrivalTime;

	} // end randomGenerator

	public int randomService(int minService, int maxService) { // begin randomGenerator

		// calculate the range of values
		int range = maxService - minService + 1;

		// generate random number within the range
		int randomServiceTime = (int) (Math.random() * range) + minService;

		// return the random number
		return randomServiceTime;

	} // end randomService

	public void populateCustomers (ArrayList<Customer> customers){ // begin populate Customers
		int arrivalTime = 0;
	    for (int i = 0; i < numCustomers; i++) {
	        int interarrivalTime = randomArrival(minArrivalTime, maxArrivalTime);
	        arrivalTime += interarrivalTime;

	        int serviceTime = randomService(minServiceTime, maxServiceTime);

	        // create customer object
	        Customer customer = new Customer(arrivalTime, serviceTime);

	        customers.add(customer);
	    }

	    Collections.sort(customers); //I used this to sort the customers by arrival time
		
	    // then assign Customer objects Id now that they are sorted by arrival
		for (int i = 0; i < numCustomers; i++) {
			customers.get(i).assignId(i+1);
		}
	} // end populateCustomers	

	public int getNumCustomers() {
		return numCustomers;
	}

	public void setNumCustomers(int numCustomers) {
		this.numCustomers = numCustomers;
	}

	public int getMinArrivalTime() {
		return minArrivalTime;
	}

	public void setMinArrivalTime(int minArrivalTime) {
		this.minArrivalTime = minArrivalTime;
	}

	public int getMaxArrivalTime() {
		return maxArrivalTime;
	}

	public void setMaxArrivalTime(int maxArrivalTime) {
		this.maxArrivalTime = maxArrivalTime;
	}

	public int getMinServiceTime() {
		return minServiceTime;
	}

	public void setMinServiceTime(int minServiceTime) {
		this.minServiceTime = minServiceTime;
	}

	public int getMaxServiceTime() {
		return maxServiceTime;
	}

	public void setMaxServiceTime(int maxServiceTime) {
		this.maxServiceTime = maxServiceTime;
	}
} // end Customer creator class
