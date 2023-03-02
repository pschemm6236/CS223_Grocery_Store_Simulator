package csc223Giraffes;

import java.util.ArrayList;

public class CustomerCreator { // begin Customer creator class

	/**
	 *          Class outline (required class)
	 * For each customer our program generates an
	 * arrival time and a service time. Through the simulation it then determines
	 * the finish time and, based on those values, calculates the waiting time. You
	 * will need a CustomerCreator class. An object of this class is passed the
	 * minimum and maximum interarrival and service times of the customers upon
	 * instantiation. Its primary responsibility is to generate and return the
	 * “next” customer when requested.
	 */

	private int numCustomers;
	// arrival time fields
	private int minArrivalTime;
	private int maxArrivalTime;
	// service time fields
	private int minServiceTime;
	private int maxServiceTime;
	// interArrivalTime fields
	private int minInterarrivalTime;
	private int maxInterarrivalTime;
	// AL of customers field
	private ArrayList<Customer> customers;

	public CustomerCreator(int numCustomers, int minArrivalTime, int maxArrivalTime, int minServiceTime,
			int maxServiceTime, int minInterarrivalTime, int maxInterarrivalTime) {
		this.numCustomers = numCustomers;
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minServiceTime = minServiceTime;
		this.maxServiceTime = maxServiceTime;
		this.minInterarrivalTime = minInterarrivalTime;
		this.maxInterarrivalTime = maxInterarrivalTime;
		this.customers = new ArrayList<>();
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

	public void populateCustomers() { // begin populate Customers
		int arrivalTime = 0;

		for (int i = 0; i < numCustomers; i++) { // begin for

			int interarrivalTime = randomArrival(minInterarrivalTime, maxInterarrivalTime);

			int serviceTime = randomService(minServiceTime, maxServiceTime);

			arrivalTime += interarrivalTime;

			// create customer object
			Customer customer = new Customer(arrivalTime, serviceTime, 0, 0, "");

			customers.add(customer);

		} // end for
	} // end populateCustomers

	public Customer getNextCustomer() { // begin getNextCustomer

		if (customers.isEmpty()) {
			return null;
		}
		Customer nextCustomer = customers.get(0);
		customers.remove(0);

		return nextCustomer;

	} // end getNextCustomer

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

	public int getMinInterarrivalTime() {
		return minInterarrivalTime;
	}

	public void setMinInterarrivalTime(int minInterarrivalTime) {
		this.minInterarrivalTime = minInterarrivalTime;
	}

	public int getMaxInterarrivalTime() {
		return maxInterarrivalTime;
	}

	public void setMaxInterarrivalTime(int maxInterarrivalTime) {
		this.maxInterarrivalTime = maxInterarrivalTime;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

} // end Customer creator class
