package csc223Giraffes;

import java.util.ArrayList;

public class GUISimulator {

	// fields
		private ArrayList<Customer> customers;
		private ArrayList<Queue> fullCheckouts;
		private SplitQueue selfCheckout;
		private double percentSlower; 
		private GUISimDriver gui; 

		// full constructor
		public GUISimulator(ArrayList<Customer> customers, ArrayList<Queue> fullCheckouts, SplitQueue selfCheckout, double percentSlower) {
			this.customers = customers;
			this.fullCheckouts = fullCheckouts;
			this.selfCheckout = selfCheckout;
			this.percentSlower = percentSlower;
			this.gui = gui; // save reference to the GUI elements
		}

		public void runSimulation() {
			int currentTime = 0;
			int customersServed = 0;
			while (customersServed < customers.size()) {
				gui.setCurrentTime(currentTime); // update current time label in the GUI
				for(int i=0;i<customers.size();i++) { //this for loop adds the customers that just arrived
					if(customers.get(i).getArrivalTime()==currentTime) {
						int serviceType = customers.get(i).serviceTypePreference();

						if(serviceType==0){
							System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " LANDED TAILS SO THEY ENTER FULL CHECKOUT LINE");
							System.out.println();
							customers.get(i).setUsedLine(shortestFullQueue().getLineName()); //sets the line used for the customer to the shortest line name
							shortestFullQueue().enqueue(customers.get(i)); //adds the customer to this line
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
				System.out.println("[FULL SERVICE]");
				for(int i=0;i<fullCheckouts.size();i++) {
					System.out.print("Checkout "+fullCheckouts.get(i).getLineName() + ": ");
					if(fullCheckouts.get(i).updateQueue(currentTime)!=null) {
						customersServed++;
					}
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

		public Queue shortestFullQueue() {
			Queue shortest = fullCheckouts.get(0);
			for(int i=1;i<fullCheckouts.size();i++) {
				if (shortest.size()>fullCheckouts.get(i).size()) {
					shortest = fullCheckouts.get(i);
				}
			}
			return shortest;
		}

		public ArrayList<Queue> getFullCheckouts() {
			return fullCheckouts;
		}

		public void setFullCheckouts(ArrayList<Queue> fullCheckouts) {
			this.fullCheckouts = fullCheckouts;
		}
		
		public SplitQueue getSelfCheckout() {
			return selfCheckout;
		}

		public void setSelfCheckout(SplitQueue selfCheckout) {
			this.selfCheckout = selfCheckout;
		}
	}
