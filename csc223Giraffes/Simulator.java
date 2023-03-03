package csc223Giraffes;

import java.util.ArrayList;

public class Simulator {

	// fields
	private int numCustomers;
	private CustomerCreator cc;
	private Queue checkoutA;
	private Queue checkoutB;
	private Queue checkoutC;
	private ArrayList<Customer> costumerList;

	// full constructor
	public Simulator(int numCustomers, CustomerCreator cc, Queue checkoutA, Queue checkoutB, Queue checkoutC) {
		this.numCustomers = numCustomers;
		this.cc = cc;
		this.checkoutA = checkoutA;
		this.checkoutB = checkoutB;
		this.checkoutC = checkoutC;
	}

	public double run(ArrayList<Customer> customers) {
		int currentTime = 0;
		double totalWaitTime = 0;
		int customersServed = 0;
		boolean checkoutAFree = true;
		boolean checkoutBFree = true;
		boolean checkoutCFree = true;

		// Run the simulation for each customer
		while (customersServed < numCustomers) {
			// Check if there are any customers waiting in the queues
			if (!checkoutA.isEmpty()) {
				Customer c = checkoutA.frontPeek();
				if (currentTime >= c.getStartTime() + c.getServiceTime()) {
					// Customer has finished checkout at checkout A
					totalWaitTime += currentTime - c.getArrivalTime() - c.getServiceTime();
					checkoutA.dequeue();
					c.setEndTime(currentTime);
					customersServed++;
					checkoutAFree = true;
					System.out.println("Time:" + currentTime);
			        System.out.println("Checkout A: Customer " + c.getCustId() + " leaves");
			        if (!checkoutA.isEmpty()) {
	                    System.out.println("Checkout A: Customer " + checkoutA.frontPeek().getCustId() + " (cont)");
	                }
				}
				//else if( ) {
					
				//}
				
			}
			if (!checkoutB.isEmpty()) {
				Customer c = checkoutB.frontPeek();
				if (currentTime >= c.getStartTime() + c.getServiceTime()) {
					// Customer has finished checkout at checkout B
					totalWaitTime += currentTime - c.getArrivalTime() - c.getServiceTime();
					checkoutB.dequeue();
					c.setEndTime(currentTime);
					customersServed++;
					checkoutBFree = true;
					System.out.println("Time:" + currentTime);
			        System.out.println("Checkout B: Customer " + c.getCustId() + " leaves");
			        if (!checkoutB.isEmpty()) {
	                    System.out.println("Checkout B: Customer " + checkoutB.frontPeek().getCustId() + " (cont)");
	                }
				}
			}
			if (!checkoutC.isEmpty()) {
				Customer c = checkoutC.frontPeek();
				if (currentTime >= c.getStartTime() + c.getServiceTime()) {
					// Customer has finished checkout at checkout C
					totalWaitTime += currentTime - c.getArrivalTime() - c.getServiceTime();
					checkoutC.dequeue();
					c.setEndTime(currentTime);
					customersServed++;
					checkoutCFree = true;
					System.out.println("Time:" + currentTime);
		            System.out.println("Checkout C: Customer " + c.getCustId() + " leaves");
		            if (!checkoutC.isEmpty()) {
	                    System.out.println("Checkout C: Customer " + checkoutC.frontPeek().getCustId() + " (cont)");
	                }
				}
			}

			boolean empty = checkoutA.isEmpty();
			boolean full = checkoutA.isFull(1);
			// Check if any checkout lanes are available
			if (checkoutAFree && checkoutA.isEmpty() && !checkoutA.isFull(1)) {
				// Assign the next customer to checkout A
				Customer c = cc.getNextCustomer(customers);
				checkoutA.enqueue(c);
				c.setStartTime(currentTime);
				checkoutAFree = false;
				System.out.println("Time:" + currentTime);
		        System.out.println("Checkout A: Customer " + c.getCustId() + " starts service");
			}
			if (checkoutBFree && checkoutB.isEmpty() && !checkoutB.isFull(1)) {
				// Assign the next customer to checkout B
				Customer c = cc.getNextCustomer(customers);
				checkoutB.enqueue(c);
				c.setStartTime(currentTime);
				checkoutBFree = false;
				System.out.println("Time:" + currentTime);
		        System.out.println("Checkout B: Customer " + c.getCustId() + " starts service");

			}
			if (checkoutCFree && checkoutC.isEmpty() && !checkoutC.isFull(1)) {
				// Assign the next customer to checkout C
				Customer c = cc.getNextCustomer(customers);
				checkoutC.enqueue(c);
				c.setStartTime(currentTime);
				checkoutCFree = false;
				System.out.println("Time:" + currentTime);
		        System.out.println("Checkout C: Customer " + c.getCustId() + " starts service");
			}
			currentTime++;
		}

				// Calculate and return the average wait time
		return totalWaitTime / numCustomers;
		
		
	}

	public void printCustomerDetails() {

	}

	// getters and setters
	public int getNumCustomers() {
		return numCustomers;
	}

	public void setNumCustomers(int numCustomers) {
		this.numCustomers = numCustomers;
	}

	public CustomerCreator getCc() {
		return cc;
	}

	public void setCc(CustomerCreator cc) {
		this.cc = cc;
	}

	public Queue getCheckoutA() {
		return checkoutA;
	}

	public void setCheckoutA(Queue checkoutA) {
		this.checkoutA = checkoutA;
	}

	public Queue getCheckoutB() {
		return checkoutB;
	}

	public void setCheckoutB(Queue checkoutB) {
		this.checkoutB = checkoutB;
	}

	public Queue getCheckoutC() {
		return checkoutC;
	}

	public void setCheckoutC(Queue checkoutC) {
		this.checkoutC = checkoutC;
	}

}
