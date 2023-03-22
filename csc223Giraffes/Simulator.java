package csc223Giraffes;

import java.util.ArrayList;


/**
 * 
 * @author Liam J. 
 * @author Parker S. 
 *
 */
public class Simulator {
	
	// fields
	private ArrayList<Customer> customers;
	private Queue checkoutA;
	private Queue checkoutB;
	private Queue checkoutC;
	
	// full constructor
	public Simulator(ArrayList<Customer> customers, Queue checkoutA, Queue checkoutB, Queue checkoutC) {
		this.customers = customers;
		this.checkoutA = checkoutA;
		this.checkoutB = checkoutB;
		this.checkoutC = checkoutC;
	}
	
	public void runSimulation() {
		int currentTime = 0;
		int customersServed = 0;
		while (customersServed < customers.size()) {
			System.out.println("Time "+currentTime+": ");
			for(int i=0;i<customers.size();i++) { //this for loop adds the customers that just arrived
				if(customers.get(i).getArrivalTime()==currentTime) {
					if(shortestQueue() == checkoutA) {
						shortestQueue().setLineName("A");
					}
					else if(shortestQueue() == checkoutB) {
						shortestQueue().setLineName("B");
					}
					else {
						shortestQueue().setLineName("C");
					}
					shortestQueue().enqueue(customers.get(i)); //adds the customer to the end of the shortest queue
				}
			}
			System.out.print("Checkout A: ");
			if(checkoutA.updateQueue(currentTime)!=null) {
				customersServed++;
			}
			System.out.print("Checkout B: ");
			if(checkoutB.updateQueue(currentTime)!=null) {
				customersServed++;
			}
			System.out.print("Checkout C: ");
			if(checkoutC.updateQueue(currentTime)!=null) {
				customersServed++;
			}
			currentTime++;
		}
	}
	
	//change this for the queue structure 
	public Queue shortestQueue() {
		if(checkoutA.size()<=checkoutB.size()&&checkoutA.size()<=checkoutC.size()) {
			return checkoutA;
		}
		else if(checkoutB.size()<=checkoutA.size()&&checkoutB.size()<=checkoutC.size()) {
			return checkoutB;
		}
		else {
			return checkoutC;
		}
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
	

	/**public QueueLL getCheckoutA() {
		return checkoutA;
	}

	public void setCheckoutA(QueueLL checkoutA) {
		this.checkoutA = checkoutA;
	}

	public QueueLL getCheckoutB() {
		return checkoutB;
	}

	public void setCheckoutB(QueueLL checkoutB) {
		this.checkoutB = checkoutB;
	}

	public QueueLL getCheckoutC() {
		return checkoutC;
	}

	public void setCheckoutC(QueueLL checkoutC) {
		this.checkoutC = checkoutC;
	}
**/
}
