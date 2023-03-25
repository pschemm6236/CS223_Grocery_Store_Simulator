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
	private Queue<Customer>[] selfCheckout = new Queue[2];
	private double percentSlower; 
	
	// full constructor
	public Simulator(ArrayList<Customer> customers, Queue<Customer> checkoutA, Queue<Customer> checkoutB, 
			Queue<Customer> checkoutC, Queue<Customer> selfCheckout, double percentSlower) {
		this.customers = customers;
		this.checkoutA = checkoutA;
		this.checkoutB = checkoutB;
		this.checkoutC = checkoutC;
		this.selfCheckout[0] = new Queue<Customer>();
		this.selfCheckout[1] = new Queue<Customer>();
		this.percentSlower = percentSlower;

	}
	
	public void runSimulation() {
		int currentTime = 0;
		int customersServed = 0;
		while (customersServed < customers.size()) {
			System.out.println("Time "+currentTime+": ");
			for(int i=0;i<customers.size();i++) { //this for loop adds the customers that just arrived
				if(customers.get(i).getArrivalTime()==currentTime) {
					coinToss(customers.get(i));
					
					if(customers.get(i).getCoinToss()==0){
						System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " LANDED TAILS SO THEY ENTER FULL CHECKOUT LINE");
					}
					else {
						System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " LANDED HEADS SO THEY ENTER SELF-CHECKOUT LINE");
						System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " IS " + percentSlower + "% SLOWER B/C OF SELF-CHECKOUT");

					}
					
					// store the current iteration customer coin
					int custCoin = customers.get(i).getCoinToss();
					
					if(custCoin==1) {
						System.out.println();
						if(selfCheckout[0].size() <= selfCheckout[1].size()	) {
							selfCheckout[0].enqueue(customers.get(i));
							//customers.get(i).setUsedLine("D");
							selfCheckout[0].setLineName("D");
						
						}
						else {
							selfCheckout[1].enqueue(customers.get(i));
							//customers.get(i).setUsedLine("E");
							selfCheckout[1].setLineName("E");
						}
					}
					
				
					// let 0 be equal to tails, so they get full service 
					else {
						System.out.println();
						if(shortestQueue(custCoin) == checkoutA) {
							shortestQueue(custCoin).setLineName("A");
						}
						else if(shortestQueue(custCoin) == checkoutB) {
							shortestQueue(custCoin).setLineName("B");
						}
						else if(shortestQueue(custCoin) == checkoutC){
							shortestQueue(custCoin).setLineName("C");
						}
						shortestQueue(custCoin).enqueue(customers.get(i)); //adds the customer to the end of the shortest queue
					}
					
					// else the customer landed heads and is self checking out 
					
						
						
					
					
				}
			}
			System.out.print("	Checkout A: ");
			if(checkoutA.updateQueue(currentTime)!=null) {
				customersServed++;
			}
			System.out.print("	Checkout B: ");
			if(checkoutB.updateQueue(currentTime)!=null) {
				customersServed++;
			}
			System.out.print("	Checkout C: ");
			if(checkoutC.updateQueue (currentTime)!=null) {
				customersServed++;
			}
			System.out.print("	Checkout D: ");
			if(selfCheckout[0].updateQueue(currentTime) != null) {
				customersServed++;
			}
			System.out.print("	Checkout E: ");
			if(selfCheckout[1].updateQueue(currentTime) != null) {
				customersServed++;
			}
			currentTime++;
			System.out.println();
		}
		
	}
	
	//change this for the queue structure 
	public Queue<Customer> shortestQueue(int custCoin) {
		
		//if(custCoin == 0) {
			if(checkoutA.size()<=checkoutB.size()&&checkoutA.size()<=checkoutC.size()) {
				return checkoutA;
			}
			else if(checkoutB.size()<=checkoutA.size()&&checkoutB.size()<=checkoutC.size()) {
				return checkoutB;
			}
			else {
				return checkoutC;
			}
		//}
		// // else the customer landed heads and is self checking out  
		//prolly delete
		/*else {
			if(checkoutD.size()<=checkoutE.size()) {
				return checkoutD;
			}
			else {
				return checkoutE;
			}
			
		}
		*/
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
