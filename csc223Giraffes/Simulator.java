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
	private SplitQueue selfCheckout;
	private double percentSlower; 

	// full constructor
	public Simulator(ArrayList<Customer> customers, Queue checkoutA, Queue checkoutB, Queue checkoutC,
			SplitQueue selfCheckout, double percentSlower) {
		this.customers = customers;
		this.checkoutA = checkoutA;
		this.checkoutB = checkoutB;
		this.checkoutC = checkoutC;
		this.selfCheckout = selfCheckout;
		this.percentSlower = percentSlower;
	}

	public void runSimulation() {
		int currentTime = 0;
		int customersServed = 0;
		while (customersServed < customers.size()) {
			System.out.println("Time "+currentTime+": ");
			for(int i=0;i<customers.size();i++) { //this for loop adds the customers that just arrived
				if(customers.get(i).getArrivalTime()==currentTime) {
					int serviceType = customers.get(i).serviceTypePreference();

					if(serviceType==0){
						System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " LANDED TAILS SO THEY ENTER FULL CHECKOUT LINE");
						System.out.println();
						customers.get(i).setUsedLine(shortestQueue().getLineName()); //sets the line used for the customer to the shortest line name
						shortestQueue().enqueue(customers.get(i)); //adds the customer to this line
					}
					else {
						System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " LANDED HEADS SO THEY ENTER SELF-CHECKOUT LINE");
						System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " IS " + percentSlower + "% SLOWER B/C OF SELF-CHECKOUT");
						customers.get(i).setServiceTime((int)Math.round(customers.get(i).getServiceTime()*(1.0+percentSlower/100))); //This adds the percent slower to the service time
						System.out.println();
						selfCheckout.enqueue(customers.get(i)); //adds the customer to the selfCheckout
					}
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
			Customer[] returnedCustomers = selfCheckout.updateQueues(currentTime);
			for(int i=0;i<returnedCustomers.length;i++) {
				if (returnedCustomers[i] != null) { //if a customer was finished being served for this queue
					customersServed++; //adds to the customers served
				}
			}

			currentTime++;
			System.out.println();
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
	
	public SplitQueue getSelfCheckout() {
		return selfCheckout;
	}

	public void setSelfCheckout(SplitQueue selfCheckout) {
		this.selfCheckout = selfCheckout;
	}
}
