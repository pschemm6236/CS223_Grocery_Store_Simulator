package csc223Giraffes;

import java.util.ArrayList;

public class Simulator {
	//it works now :)
	// fields
	private ArrayList<Customer> customers;
	private QueueLL checkoutA;
	private QueueLL checkoutB;
	private QueueLL checkoutC;
	
	// full constructor
	public Simulator(ArrayList<Customer> customers, QueueLL checkoutA, QueueLL checkoutB, QueueLL checkoutC) {
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
					shortestQueue().add(shortestQueue().size(),customers.get(i)); //adds the customer to the end of the shortest queue
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
	
	public QueueLL shortestQueue() {
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

	public QueueLL getCheckoutA() {
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

}
