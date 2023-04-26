package csc223Giraffes;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * 
 * @author Troy F.
 * @author Parker S.
 *
 */

public class GUISimDriver extends JFrame implements ActionListener {
	 private JTextField fullServiceLinesField;
	    private JTextField selfServiceLinesField;
	    private JTextField minArrivalTimeField;
	    private JTextField maxArrivalTimeField;
	    private JTextField minServiceTimeField;
	    private JTextField maxServiceTimeField;
	    private JTextField numCustomersField;
	    private JTextField percentSlowerField;
	    
	    private JTextArea outputArea;
	    


	public GUISimDriver() {
		super("Simulation Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the JFrame
        setSize(800, 800); // Width: 800, Height: 800
        JPanel panel = new JPanel(new GridLayout(9, 2));

        panel.add(new JLabel("Number of full-service lines:"));
        fullServiceLinesField = new JTextField();
        panel.add(fullServiceLinesField);

        panel.add(new JLabel("Number of self-service lines:"));
        selfServiceLinesField = new JTextField();
        panel.add(selfServiceLinesField);

        panel.add(new JLabel("Minimum arrival time between customers:"));
        minArrivalTimeField = new JTextField();
        panel.add(minArrivalTimeField);

        panel.add(new JLabel("Maximum arrival time between customers:"));
        maxArrivalTimeField = new JTextField();
        panel.add(maxArrivalTimeField);

        panel.add(new JLabel("Minimum service time:"));
        minServiceTimeField = new JTextField();
        panel.add(minServiceTimeField);

        panel.add(new JLabel("Maximum service time:"));
        maxServiceTimeField = new JTextField();
        panel.add(maxServiceTimeField);

        panel.add(new JLabel("Number of customers to serve:"));
        numCustomersField = new JTextField();
        panel.add(numCustomersField);

        panel.add(new JLabel("Percentage slower for self-service:"));
        percentSlowerField = new JTextField();
        panel.add(percentSlowerField);

        JButton startButton = new JButton("Start Simulation");
        startButton.addActionListener(this);
        panel.add(startButton);

        getContentPane().add(panel);
        // Set the preferred size of the panel
        panel.setPreferredSize(new Dimension(800, 800)); // Width: 800, Height: 800

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);
	} // end GUISimDriver
	
	public void printToOutputArea(String text) {
	    outputArea.append(text + "\n");
	    outputArea.setCaretPosition(outputArea.getDocument().getLength());
	} // end 


	public static void main(String[] args) { // begin main

		new GUISimDriver();

	} // end main

	// - - - - - - - - - - - - - static methods - - - - - - - - - - - - -

	public static ArrayList<Queue> createFullQueues(int full) {
		ArrayList<Queue> fullQ = new ArrayList<Queue>();

		int counter = 0;
		
		
		for (int i = 0; i < full; i++) {
			String queueName = "";
			if (counter < 26) {
				queueName = Character.toString((char) ('A' + counter ));
	        } else {
	            int suffix = ((counter - 26) / 26) + 1;
	            queueName = Character.toString((char) ('A' + (counter - 26) % 26)) + suffix;
	        }
	        Queue newQueue = new Queue(queueName);
	        fullQ.add(newQueue);
	        counter++;
	    }

		return fullQ;
	}

	public static String[] createSelfQueues(int self) {

		String[] selfQueues = new String[self];

	   
	
	    int counter = 0;
	    
	    for (int i = 0; i < self; i++) {
	    	String queueName = "SC -";
	    	
	    	if(counter < 26) {
	    		queueName += Character.toString((char) ('A' + counter));
	    		counter++;
	    		
	    	}
	    	else {
	            int suffix = ((counter - 26) / 26) + 1;
	            String tempName =  Character.toString((char) ('A' + (counter - 26) % 26)) + suffix;
	    		queueName += tempName;
	    		counter++;
	    	}
	    	selfQueues[i] = queueName;
	    }
	    return selfQueues;
	}

	// Takes the original customers ArrayList and Queue objects to gather all
	// simulation results and print
	public static void printSimResults(ArrayList<Customer> customers, ArrayList<Queue> fullQueues, SplitQueue self, int numOfSelfLines, JTextArea outputArea) {

		int satisfiedCust = 0;
		int dissatisfiedCust = 0;
		DecimalFormat df = new DecimalFormat("##.#");

		System.out.println("\n----- Simulation Data -----\n");

		// for loop all the customers to get each of their wait times and number
		// satisfied
		for (int i = 0; i < customers.size(); i++) {

			int ithCustWaitTime = (customers.get(i).waitingTime());

			// check for satisfied/non
			if (ithCustWaitTime < 5) {
				satisfiedCust++;
			} else
				dissatisfiedCust++;
		}

		double totalWaitTimeFull = 0;
		int numCustomersFull = 0;
		double totalWaitTimeSelf = 0;
		int numCustomersSelf = 0;

		for (Customer customer : customers) {
			double waitTime = customer.waitingTime();
			String line = customer.getUsedLine();

			if (line.contains("SC")) { // SELF line
				totalWaitTimeSelf += waitTime;
				numCustomersSelf++;
			} else { // FULL line
				totalWaitTimeFull += waitTime;
				numCustomersFull++;
			}
		}

		double avgWaitTimeFull = totalWaitTimeFull / numCustomersFull;
		double avgWaitTimeSelf = totalWaitTimeSelf / numCustomersSelf;

		System.out.println("Average wait time for FULL: " + df.format(avgWaitTimeFull)
				+ " min. With FULL serving a total of " + numCustomersFull + " for the day.");
		System.out.println("Average wait time for SELF: " + df.format(avgWaitTimeSelf)
				+ " min. With SELF serving a total of " + numCustomersSelf + " for the day.");

		
		int totalTimeNotUsedFull = 0;
		//loop thru ALL of fullChecouts, accumulate timenotused to variable
		for(int i = 0; i<fullQueues.size(); i++) {
			
			totalTimeNotUsedFull += fullQueues.get(i).getTimeNotUsed();
		}
		int totalTimeNotUsedSelf = self.getTotalTimeNotUsed();
		
		// print results
		System.out.println();
		System.out.println("Total accumulated idle time for all checkouts (FULL): " + totalTimeNotUsedFull + " minutes");
		System.out.println("Total accumulated idle time for all checkouts (SELF): " + self.getTotalTimeNotUsed() + " minutes");
		System.out.println("Customer satisfaction: " + satisfiedCust + " satisfied (<5 minutes) " + dissatisfiedCust
				+ " dissatisfied (>=5 minutes)");
		
		// output current line suggestions
	    System.out.println("\n----- Line Suggestions -----\n");
	   
	    
	    double idleWaitTimeThreshold = 2.0; // our ideal wait time for lines (can change as needed)
	    double idleTimeThreshold = 30.0; // our ideal idle time for lines (can change as needed)

	    //create another nested if/elseif statement for if idle time is too high 
	    
	    if (isSignificantDifference(avgWaitTimeFull, avgWaitTimeSelf, idleWaitTimeThreshold)) {
	        if (avgWaitTimeFull > avgWaitTimeSelf) {
	            System.out.println("Consider adding a FULL checkout line to reduce wait time.");
	        } else {
	            System.out.println("Consider adding a SELF checkout line to reduce wait time.");
	        }
	    } else {
	        System.out.println("No need to add any lines.");
	    }

	    if (isSignificantDifference(totalTimeNotUsedFull, totalTimeNotUsedSelf, idleTimeThreshold)) {
	        if (totalTimeNotUsedFull > totalTimeNotUsedSelf && fullQueues.size() != 1) {
	            System.out.println("Consider removing a FULL checkout line to reduce idle time.");
	        } 
	        else if (numOfSelfLines != 1){
	            System.out.println("Consider removing a SELF checkout line to reduce idle time.");
	        }
	        else {
		        System.out.println("No need to remove any lines.");
		    }
	    } else {
	        System.out.println("No need to remove any lines.");
	    }

	} // end printSimResults	
	
	// evaluates if there's a significant difference in the average wait times and idle times for FULL and SELF checkouts.
	private static boolean isSignificantDifference(double value1, double value2, double threshold) {
	    return Math.abs(value1 - value2) >= threshold;
	}

	// Takes the data and prints out formatted table
	public static void printSimResultsTable(ArrayList<Customer> customers, JTextArea outputArea) {

		 // Set up the frame
	    JFrame frame = new JFrame("Simulation Results");
	    frame.setSize(800, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // Create the table model
	    String[] columnNames = {"Customer ID", "Arrival Time (absolute)", "Service Time","Departure time (absolute)",  "Wait Time", "Checkout Line", };
	    Object[][] rowData = new Object[customers.size()][columnNames.length];
	    DecimalFormat df = new DecimalFormat("##.#");
	    for (int i = 0; i < customers.size(); i++) {
	        Customer customer = customers.get(i);
	        rowData[i][0] = customer.getCustId();
	        rowData[i][1] = customer.getArrivalTime();
	        rowData[i][2] = customer.getServiceTime();
	        rowData[i][3] = customer.getEndTime();
	        rowData[i][4] = customer.waitingTime();
	        rowData[i][5] = customer.getUsedLine();
	    }
	    DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
	    
	    // Create the table and add it to a scroll pane
	    JTable table = new JTable(model);
	    JScrollPane scrollPane = new JScrollPane(table);
	    
	    // Add the scroll pane to a panel and add the panel to the frame
	    JPanel panel = new JPanel();
	    panel.add(scrollPane);
	    frame.getContentPane().add(panel);
	    
	    // Show the frame
	    frame.setVisible(true);

	} // end printSimResultsTable

	// Takes the original customers ArrayList and prints out all the Customer
	// objects inside (to see all their time values)
	public static void printDebug(ArrayList<Customer> customers) {

		for (int i = 0; i < customers.size(); i++) {
			System.out.println(customers.get(i).toString());
		}

	} // end printDebug

	// prints recommended settings on call
	public static void printRecSet() { // begin printRecSet

		System.out.println("Recommended Settings:");
		System.out.println("Number of full-service lines = 2");
		System.out.println("Numberr of self-service lines = 6");
		System.out.println("minimum arrival time = 1");
		System.out.println("maximum arrival time = 4");
		System.out.println("minimum service time = 3");
		System.out.println("maximum service time = 4");
		System.out.println("number of customers  = 3 to 12");
		System.out.println("Percentage slower for SELF = 25%");

		System.out.println();

	} // end printRecSet

	@Override
	public void actionPerformed(ActionEvent e) {
		runSimulationWithGUIInputs();
	}
	
	// method to run the simulation using GUI 
	public void runSimulationWithGUIInputs() {
		// TODO Auto-generated method stub
		
		// GET USER SIMULATION SETTINGS
		int fullServiceLines = Integer.parseInt(fullServiceLinesField.getText());
		int selfServiceLines = Integer.parseInt(selfServiceLinesField.getText());
		int minArrivalTime = Integer.parseInt(minArrivalTimeField.getText());
		int maxArrivalTime = Integer.parseInt(maxArrivalTimeField.getText());
		int minServiceTime = Integer.parseInt(minServiceTimeField.getText());
		int maxServiceTime = Integer.parseInt(maxServiceTimeField.getText());
		int numCustomers = Integer.parseInt(numCustomersField.getText());
		double percentSlower = Double.parseDouble(percentSlowerField.getText());

		Scanner scan = new Scanner(System.in);
		// ArrayList to Store Customer Objects that is defined here in main AND passed
		// by reference to wherever
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		// CustomerCreator object which takes all USER SIMULATION SETTINGS
		CustomerCreator creator = new CustomerCreator(numCustomers, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
		
		// call populateCustomers method within our creator to fill customers ArrayList
		// with Customer objects
		creator.populateCustomers(customers);
		
		ArrayList<Queue> fullQueues = new ArrayList<Queue>();
		String[] selfQueues = new String[selfServiceLines];
		
		fullQueues = createFullQueues(fullServiceLines);
		
		selfQueues = createSelfQueues(selfServiceLines);
		
		SplitQueue selfCheckoutQueue = new SplitQueue(selfQueues);
		
		Simulator sim = new Simulator(customers, fullQueues, selfCheckoutQueue, percentSlower);
		
		sim.runSimulation();
		scan.close();
		
		//printSimResults(customers, fullQueues, selfCheckoutQueue, selfServiceLines);
		printSimResultsTable(customers, outputArea);
	} // end runSimulationWithGUIInputs

} // end class SimulatorDriver
