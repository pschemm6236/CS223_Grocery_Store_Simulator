package application;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;


/**
 * 
 * @author Liam J. 
 * @author Parker S. 
 *
 */
public class SimulatorFX extends SceneController {

	// fields
	private ArrayList<Customer> customers;
	private ArrayList<Queue> fullCheckouts;
	private SplitQueue selfCheckout;
	private double percentSlower; 
	
	
	// FXML fields for running sim
		@FXML
		private ProgressBar myProgressBar;
		@FXML
		private Label myRunLabel;
		

	// full constructor
	public SimulatorFX(ArrayList<Customer> customers, ArrayList<Queue> fullCheckouts, SplitQueue selfCheckout, double percentSlower) {
		this.customers = customers;
		this.fullCheckouts = fullCheckouts;
		this.selfCheckout = selfCheckout;
		this.percentSlower = percentSlower;
	}

	public void runSimulation() {
	    Thread simulationThread = new Thread(() -> {
	        int currentTime = 0;
	        int customersServed = 0;

	        while (customersServed < customers.size()) {
	            final int current = currentTime;
	            final int currentCust = customersServed;

	            Platform.runLater(() -> {
	                static_Label2.setText("Time " + current + ": ");
	                
	                // formatting cases for the amount of customers served in GUI
	                if (currentCust+1 == customers.size()) {
	                	int lastIntCust = currentCust+1;
	                	static_Label.setText("Customers Served: " + lastIntCust + "/" + customers.size());
	                }
	                else {
	                	static_Label.setText("Customers Served: " + currentCust + "/" + customers.size());
	                }
	                
	            });

	            
	    			System.out.println("Time "+currentTime+": ");
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
	    		

	            pause(120);
	            
	        }
	        
	        static_Label.setText("Customers Served: " + customersServed + "/" + customers.size());
	    });

	    simulationThread.start();
	}

	private void pause(int milliseconds) {
	    try {
	        Thread.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
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
