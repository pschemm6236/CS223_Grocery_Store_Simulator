package csc223Giraffes;

import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Liam J. 
 * @author Parker S. 
 *
 */
public class Simulator {

	// fields
	private ArrayList<Customer> customers;
	private ArrayList<Queue> fullCheckouts;
	private SplitQueue selfCheckout;
	private double percentSlower; 
	
	private VisualSimMenu visualSimMenu;
	private Timer timer;
	
	// WILL BE USED TO HOLD THE CURRENT ITH OUTPUT OF THE FULL LINE STATUS 
	public String ithFULLStringStatus;
	// STORES THE OUTPUT FOR BOTH LINES AS ITS SEEN IN THE CONSOLE
	public String allLineOutputString;
		
	// full constructor
	public Simulator(ArrayList<Customer> customers, ArrayList<Queue> fullCheckouts, SplitQueue selfCheckout, double percentSlower, VisualSimMenu visualSimMen) {
		this.customers = customers;
		this.fullCheckouts = fullCheckouts;
		this.selfCheckout = selfCheckout;
		this.percentSlower = percentSlower;
		
		this.visualSimMenu = visualSimMen;
	}

	public void runSimulation() {
		int currentTime = 0;
		int customersServed = 0;
		int delay = 1000; // The delay in milliseconds between each step
		
		while (customersServed < customers.size()) {
			
			System.out.println("Time "+currentTime+": ");
			// update the JLabel (outputLabelOne) back inside visualSimMenu so GUI can display current simulation Time
			visualSimMenu.setOutputLabelOneText("Time "+currentTime+": ");
			
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
					
					// call method to adjust progress bar
					visualSimMenu.fill();
				}
			}
			Customer[] returnedCustomers = selfCheckout.updateQueues(currentTime);
			for(int i=0;i<returnedCustomers.length;i++) {
				if (returnedCustomers[i] != null) { //if a customer was finished being served for this queue
					customersServed++; //adds to the customers served
					
					// call method to adjust progress bar
					visualSimMenu.fill();
				}
			}

			currentTime++;
			System.out.println();
			
		}
	} // end runSimulation
	
	public void runSimulationGUI() {
	    int delay = 200; // The delay in milliseconds between each step

	    timer = new Timer(delay, new ActionListener() {
	        int currentTime = 0;
	        int customersServed = 0;

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (customersServed >= customers.size()) {
	                timer.stop();
	                
	                // force adjust progress bar to 100% if sim is done
	                visualSimMenu.setSimIsDone(true);
	                visualSimMenu.fill();
	                
	                return;
	            }

	            System.out.println("Time " + currentTime + ": ");
	            // update the JLabel (outputLabelOne) back inside visualSimMenu so GUI can display current simulation Time
	            visualSimMenu.setOutputLabelOneText("Time " + currentTime + ": ");

	            for (int i = 0; i < customers.size(); i++) { // this for loop adds the customers that just arrived
	                if (customers.get(i).getArrivalTime() == currentTime) {
	                    int serviceType = customers.get(i).serviceTypePreference();

	                    if (serviceType == 0) {
	                        System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " LANDED TAILS SO THEY ENTER FULL CHECKOUT LINE");
	                        System.out.println();
	                        customers.get(i).setUsedLine(shortestFullQueue().getLineName()); // sets the line used for the customer to the shortest line name
	                        shortestFullQueue().enqueue(customers.get(i)); // adds the customer to this line
	                    } else {
	                        System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " LANDED HEADS SO THEY ENTER SELF-CHECKOUT LINE");
	                        System.out.println("	CUSTOMER " + customers.get(i).getCustId() + " IS " + percentSlower + "% SLOWER B/C OF SELF-CHECKOUT");
	                        customers.get(i).setServiceTime((int) Math.round(customers.get(i).getServiceTime() * (1.0 + percentSlower / 100))); // This adds the percent slower to the service time
	                        System.out.println();
	                        selfCheckout.enqueue(customers.get(i)); // adds the customer to the selfCheckout
	                    }
	                }
	            }
	            ithFULLStringStatus = "";
	            
	            System.out.println("[FULL SERVICE]");
	            ithFULLStringStatus += "\n[FULL SERVICE]\n";
	            
	            for (int i = 0; i < fullCheckouts.size(); i++) {
	                System.out.print("Checkout " + fullCheckouts.get(i).getLineName() + ": ");
	                
	                ithFULLStringStatus += ("Checkout " + fullCheckouts.get(i).getLineName() + ": ");
	                
	                if (fullCheckouts.get(i).updateQueue(currentTime) != null) {
	                    customersServed++;

	                    // call method to adjust progress bar
	                    visualSimMenu.fill();
	                }
	            }
	            Customer[] returnedCustomers = selfCheckout.updateQueues(currentTime);
	            for (int i = 0; i < returnedCustomers.length; i++) {
	                if (returnedCustomers[i] != null) { // if a customer was finished being served for this queue
	                    customersServed++; // adds to the customers served

	                    // call method to adjust progress bar
	                    visualSimMenu.fill();
	                }
	            }
	            
	            ithFULLStringStatus += "\n";
	            
	            // output the line status in GUI
	    	    allLineOutputString = ithFULLStringStatus + selfCheckout.getIthSELFStringStatus();
	    	    visualSimMenu.setOutputAllLineStatus(allLineOutputString);
	    	    
	            currentTime++;
	            System.out.println();

	        }
	    });
	    
	    
	    
	    
	    timer.start();
	} // end runSimulationGUI

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
	// method to pause the program for a brief moment
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {}
	}
}
