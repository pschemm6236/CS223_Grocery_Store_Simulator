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
	private Queue<Customer> checkoutA;
	private Queue<Customer> checkoutB;
	private Queue<Customer> checkoutC;
	private Queue<Customer> checkoutD; 
	private Queue<Customer> checkoutE;
	
	// full constructor
	public Simulator(ArrayList<Customer> customers, Queue<Customer> checkoutA, Queue<Customer> checkoutB, 
			Queue<Customer> checkoutC, Queue<Customer> checkoutD, Queue<Customer> checkoutE) {
		this.customers = customers;
		this.checkoutA = checkoutA;
		this.checkoutB = checkoutB;
		this.checkoutC = checkoutC;
		this.checkoutD = checkoutD;
		this.checkoutE = checkoutE;
	}
	
	public void runSimulation() {
		int currentTime = 0;
		int customersServed = 0;
		while (customersServed < customers.size()) {
			System.out.println("Time "+currentTime+": ");
			for(int i=0;i<customers.size();i++) { //this for loop adds the customers that just arrived
				if(customers.get(i).getArrivalTime()==currentTime) {
					
					coinToss(customers.get(i));
					System.out.println("CUSTOMER " + customers.get(i).getCustId() + "LANDED " +  customers.get(i).getCoinToss());
					// store the current iteration customer coin
					int custCoin = customers.get(i).getCoinToss();
					
					// let 0 be equal to tails, so they get full service 
					if(custCoin == 0) {
						if(shortestQueue(custCoin) == checkoutA) {
							shortestQueue(custCoin).setLineName("A");
						}
						else if(shortestQueue(custCoin) == checkoutB) {
							shortestQueue(custCoin).setLineName("B");
						}
						else if(shortestQueue(custCoin) == checkoutC){
							shortestQueue(custCoin).setLineName("C");
						}
					}
					
					// else the customer landed heads and is self checking out 
					else {
						if(shortestQueue(custCoin) == checkoutD) {
							shortestQueue(custCoin).setLineName("D");
						}
						else {
							shortestQueue(custCoin).setLineName("E");
						}
						
					}
					shortestQueue(custCoin).enqueue(customers.get(i)); //adds the customer to the end of the shortest queue
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
			if(checkoutC.updateQueue (currentTime)!=null) {
				customersServed++;
			}
			System.out.println("Checkout D: ");
			if(checkoutD.updateQueue(currentTime)!=null) {
				customersServed++;
			}
			System.out.println("Checkout E: ");
			if(checkoutE.updateQueue(currentTime)!=null) {
				customersServed++;
			}
			currentTime++;
		}
	}
	
	//change this for the queue structure 
	public Queue<Customer> shortestQueue(int custCoin) {
		
		if(custCoin == 0) {
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
		// // else the customer landed heads and is self checking out  
		else {
			if(checkoutD.size()<=checkoutE.size()) {
				return checkoutD;
			}
			else {
				return checkoutE;
			}
			
		}
	}
	
	public void coinToss(Customer c) {
		 int randomNum = (int) (Math.random() * 2); // Generate a random number between 0 and 1
		 c.setCoinToss(randomNum); // Assign the random number to the customer's coinToss field
	}

	public Queue<Customer> getCheckoutA() {
		return checkoutA;
	}

	public void setCheckoutA(Queue<Customer> checkoutA) {
		this.checkoutA = checkoutA;
	}

	public Queue<Customer> getCheckoutB() {
		return checkoutB;
	}

	public void setCheckoutB(Queue<Customer> checkoutB) {
		this.checkoutB = checkoutB;
	}

	public Queue<Customer> getCheckoutC() {
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
