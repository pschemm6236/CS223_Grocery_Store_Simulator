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

		//Customer c = new Customer();
		// Run the simulation for each customer
		while (customersServed <= numCustomers) {
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
				
				if(cc.getNextCustomer(customers) == null) {
					break;
				}
				
				// Assign the next customer to checkout A
				Customer c = cc.getNextCustomer(customers);
				checkoutA.enqueue(c);
				c.setStartTime(currentTime);
				checkoutAFree = false;
				System.out.println("Time:" + currentTime);
		        //System.out.println("Checkout A: Customer " + c.getCustId() + " starts service");
		        System.out.println("Checkout A:" + checkoutA.checkStatus(c, currentTime));
		        System.out.println("Checkout B:" + checkoutB.checkStatus(c, currentTime));
		        System.out.println("Checkout C:" + checkoutC.checkStatus(c, currentTime));

			}
			if (checkoutBFree && checkoutB.isEmpty() && !checkoutB.isFull(1)) {
				
				if(cc.getNextCustomer(customers) == null) {
					break;
				}
				
				// Assign the next customer to checkout B
				Customer c = cc.getNextCustomer(customers);
				checkoutB.enqueue(c);
				c.setStartTime(currentTime);
				checkoutBFree = false;
				System.out.println("Time:" + currentTime);
		        //System.out.println("Checkout B: Customer " + c.getCustId() + " starts service");
		        System.out.println("Checkout A:" + checkoutA.checkStatus(c, currentTime));

		        System.out.println("Checkout B:" + checkoutB.checkStatus(c, currentTime));
		        System.out.println("Checkout C:" + checkoutC.checkStatus(c, currentTime));


			}
			if (checkoutCFree && checkoutC.isEmpty() && !checkoutC.isFull(1)) {
				
				if(cc.getNextCustomer(customers) == null) {
					break;
				}
				
				// Assign the next customer to checkout C
				Customer c = cc.getNextCustomer(customers);
				
				checkoutC.enqueue(c);
				c.setStartTime(currentTime);
				checkoutCFree = false;
				System.out.println("Time:" + currentTime);
		       // System.out.println("Checkout C: Customer " + c.getCustId() + " starts service");
		        System.out.println("Checkout A:" + checkoutA.checkStatus(c, currentTime));

				System.out.println("Checkout B:" + checkoutB.checkStatus(c, currentTime));
		        System.out.println("Checkout C:" + checkoutC.checkStatus(c, currentTime));

			}
			currentTime++;
		}

				// Calculate and return the average wait time
		return totalWaitTime / numCustomers;
		
		
	}
	
	public double run2(ArrayList<Customer> customers ) {
		 int currentTime = 0;
		    double totalWaitTime = 0;
		    int customersServed = 0;

		    System.out.println("Time: " + currentTime);
		    System.out.println("\tStart");
		    while (customersServed < numCustomers) {
		        // Check for new arrivals and add them to shortest checkout queue
		        for (Customer c : customers) {
		            if (c.getArrivalTime() == currentTime) {
		                Queue shortestQueue = getShortestQueue();
		                shortestQueue.enqueue(c);
		                System.out.println("Time:" + currentTime);
		                System.out.println("Customer " + c.getCustId() + " arrives and goes into Checkout " + shortestQueue.getName() + " queue");
		            }
		          
		        }

		        // Check each checkout queue for free servers
		        for (int i = 0; i < 3; i++) {
		            Queue checkout = null;
		            if (i == 0) {
		                checkout = checkoutA;
		            } else if (i == 1) {
		                checkout = checkoutB;
		            } else {
		                checkout = checkoutC;
		            }

		            if (checkout.isFree() && !checkout.isEmpty()) {
		                Customer c = checkout.frontPeek();
		                c.setStartTime(currentTime);
		                checkout.dequeue();
		                checkout.startService(currentTime);
		                System.out.println("Time:" + currentTime);
		                System.out.println(checkout.getName() + ": Customer " + c.getCustId() + " starts service");
		                System.out.println("Current queues: " + checkoutA.getName() + ": " + checkoutA.toString() + " " + checkoutB.getName() + ": " + checkoutB.toString() + " " + checkoutC.getName() + ": " + checkoutC.toString());

		            }
		        }

		        // Increment current time and check for customers leaving
		        currentTime++;
		        for (int i = 0; i < 3; i++) {
		            Queue checkout = null;
		            if (i == 0) {
		                checkout = checkoutA;
		            } else if (i == 1) {
		                checkout = checkoutB;
		            } else {
		                checkout = checkoutC;
		            }

		            if (checkout.isFree() && !checkout.isEmpty()) {
		                Customer c = checkout.frontPeek();
		                c.setStartTime(currentTime);
		                checkout.dequeue();
		                checkout.startService(currentTime);
		                System.out.println("Time:" + currentTime);
		                System.out.println(checkout.getName() + ": Customer " + c.getCustId() + " starts service");
		                System.out.println("Current queues: " + checkoutA.getName() + ": " + checkoutA.toString() + " " + checkoutB.getName() + ": " + checkoutB.toString() + " " + checkoutC.getName() + ": " + checkoutC.toString());

		            }
		        }
		    }

		    return totalWaitTime / numCustomers;
		
	}
	
	public double run3(ArrayList<Customer> customers ) {
		
		
		int currentTime = 0;
	    double totalWaitTime = 0;
	    int customersServed = 0;
	    boolean checkoutAFree = true;
		boolean checkoutBFree = true;
		boolean checkoutCFree = true;

	    
	    System.out.println("Time: 0");
 	    System.out.println("\tStart");
 	    Customer nextCust = null;
	   
	    while (customersServed < numCustomers) { //begin while 
	    	
	    	// only assign a customer if currentTime is > 1
	    	if (currentTime >= 1) {
	    		nextCust = cc.getNextCustomer(customers);
	    	}
	    	
	    	// 3 if statements that represent the 3 lines
	    	if(nextCust.getArrivalTime() == currentTime && nextCust != null) {
	    		System.out.println("Customer: " + nextCust.getCustId() + ". Queue: " + checkoutA.getName()); 
	            Queue shortestQueue = getShortestQueue();
	            shortestQueue.enqueue(nextCust);
                System.out.println("Time:" + currentTime);
                System.out.println("Customer " + nextCust.getCustId() + " arrives and goes into Checkout " + shortestQueue.getName() + " queue");

	    	}
	    	if(nextCust.getArrivalTime() == currentTime && nextCust != null) {
	            Queue shortestQueue = getShortestQueue();
	            shortestQueue.enqueue(nextCust);
                System.out.println("Time:" + currentTime);
                System.out.println("Customer " + nextCust.getCustId() + " arrives and goes into Checkout " + shortestQueue.getName() + " queue");

	    	}
	    	if(nextCust.getArrivalTime() == currentTime && nextCust != null) {
	            Queue shortestQueue = getShortestQueue();
	            shortestQueue.enqueue(nextCust);
                System.out.println("Time:" + currentTime);
                System.out.println("Customer " + nextCust.getCustId() + " arrives and goes into Checkout " + shortestQueue.getName() + " queue");

	    	}
	    	
     
	    	
	    	if (currentTime == 0) {
	    		System.out.println("START");
	    	}
	    	// Increment the time
	    	currentTime++;
	    	
	    	// catch for end of ArrayList (all customers have been empty for this ith loop)
	    	// if(cc.getNextCustomer(customers) == null)
	    	//	break;
	    	

	    }//end while 
	    System.out.println("Simulation Complete.");
	    
	    return 0; 
		
	}
	
	private Queue getShortestQueue() {
	    int sizeA = checkoutA.size();
	    int sizeB = checkoutB.size();
	    int sizeC = checkoutC.size();
 
	    if (sizeA <= sizeB && sizeA <= sizeC) {
	        return checkoutA;
	    } else if (sizeB <= sizeA && sizeB <= sizeC) {
	        return checkoutB;
	    } else {
	        return checkoutC;
	    }
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
