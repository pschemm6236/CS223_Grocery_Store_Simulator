package csc223Giraffes;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

// NOTE THIS CLASS BEHAVES LIKE A HYBRID OF BOTH SimulatorDriver and Simulator
public class VisualSimMenu {
	
	private MenuManager menuManager;
	private SimulationSettings simSettings;
	
	// variables used to store the user settings (will assign values via pullUserSettings method call)
	private int fullServiceLines;
	private int selfServiceLines;
	private int minArrivalTime;
	private int maxArrivalTime;
	private int minServiceTime;
	private int maxServiceTime;
	private int numCustomers;
	private double percentSlower;
	
	public static JLabel mainLabel;
	public static JLabel outputLabelOne;
	public static JLabel queueStatusTextArea;

	public static JTextArea simulationResultsArea;
	

	public static JButton backButton;
	public static JButton dataButton;
	public static JButton setupButton;
	public static JButton simulationButton;
	public static JButton tableButton;
	public static int counter;
	public boolean simIsDone = false;
	
	public ArrayList<Customer> customers;
	public ArrayList<Queue> fullQueues;
	public String[] selfQueues;
	public SplitQueue selfCheckoutQueue;
	
	private static JFrame menu;
	
	
	// declare JProgressBar for when simulation is loading
	private JProgressBar bar;
    

	public VisualSimMenu(MenuManager mm, SimulationSettings settings) {
		
		menuManager = mm;
		simSettings = settings;
		
		//Setting up the frame
		menu = new JFrame();
		menu.setTitle("Visual Simulation");
		
		
		
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setResizable(false);
		menu.setSize(700,700);
		menu.setLayout(null);
		menu.getContentPane().setBackground(Color.white);
		
		//Creating the icon for the frame
		String logoName = "JavaRUsLogo.png";
		String defaultPath = new File(logoName).getAbsolutePath().replace("\\", "\\\\");
		String newPath = defaultPath.substring(0,defaultPath.length()-logoName.length())+"csc223Giraffes\\"+defaultPath.substring(defaultPath.length()-logoName.length());
		ImageIcon icon = new ImageIcon(newPath);
		menu.setIconImage(icon.getImage());
		
		//Making the main label for this menu
		mainLabel = new JLabel();
		mainLabel.setText("Simulation In Progress");
		mainLabel.setHorizontalTextPosition(JLabel.CENTER);
		mainLabel.setVerticalTextPosition(JLabel.TOP);
		mainLabel.setForeground(Color.blue);
		mainLabel.setBackground(Color.white);
		mainLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));
		mainLabel.setIconTextGap(5);
		mainLabel.setOpaque(true);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setVerticalAlignment(JLabel.CENTER);
		mainLabel.setBounds(0,4,700,20);
		

		//Making the and output label
		outputLabelOne = new JLabel();
		outputLabelOne.setText("Nothing new...");
		outputLabelOne.setHorizontalTextPosition(JLabel.CENTER);
		outputLabelOne.setVerticalTextPosition(JLabel.TOP);
		outputLabelOne.setForeground(Color.darkGray);
		outputLabelOne.setBackground(Color.white);
		outputLabelOne.setFont(new Font(Font.SERIF,Font.BOLD,25));
		outputLabelOne.setIconTextGap(5);
		outputLabelOne.setOpaque(true);
		outputLabelOne.setHorizontalAlignment(JLabel.CENTER);
		outputLabelOne.setVerticalAlignment(JLabel.CENTER);
		outputLabelOne.setBounds(0, 34, 700, 40);
		
		// Making the and output label
		queueStatusTextArea = new JLabel();
		queueStatusTextArea.setText("No line status updates...");
		queueStatusTextArea.setHorizontalTextPosition(JLabel.CENTER);
		queueStatusTextArea.setVerticalTextPosition(JLabel.TOP);
		queueStatusTextArea.setForeground(Color.darkGray);
		queueStatusTextArea.setBackground(Color.white);
		queueStatusTextArea.setFont(new Font(Font.SERIF, Font.BOLD, 15));
		queueStatusTextArea.setIconTextGap(5);
		queueStatusTextArea.setOpaque(true);
		queueStatusTextArea.setHorizontalAlignment(JLabel.CENTER);
		queueStatusTextArea.setVerticalAlignment(JLabel.CENTER);
		queueStatusTextArea.setBounds(0, 180, 700, 40);

		// JTextArea for end sim results stats
		simulationResultsArea = new JTextArea(150, 250);
		simulationResultsArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(simulationResultsArea);
		scrollPane.setBounds(50, 220, 600, 200); // Set the position and size for the scrollPane
		menu.add(scrollPane);
		
		//Buttons
		Border border = BorderFactory.createLineBorder(Color.black,2);

		dataButton = new JButton();
		dataButton.setBounds(20,525,200,100);
		dataButton.setText("Database Functions");
		dataButton.setBackground(Color.white);
		dataButton.setBorder(border);
		dataButton.setFocusable(false);
		dataButton.addActionListener(e -> dataFunction());
		
		backButton = new JButton();
		backButton.setBounds(5,5,30,30);
		backButton.setText("<=");
		backButton.setBackground(Color.white);
		backButton.setBorder(border);
		backButton.setFocusable(false);
		backButton.addActionListener(e -> backFunction());
		
		setupButton = new JButton();
		setupButton.setBounds(245, 525, 200, 100);
		setupButton.setText("Simulation Setup");
		setupButton.setBackground(Color.white);
		setupButton.setBorder(border);
		setupButton.setFocusable(false);
		setupButton.addActionListener(e -> setupFunction());
		
		tableButton = new JButton();
		tableButton.setBounds(470, 525, 200, 100);
		tableButton.setText("Simulation Data");
		tableButton.setBackground(Color.white);
		tableButton.setBorder(border);
		tableButton.setFocusable(false);
		tableButton.addActionListener(e -> tableFunction());
		
		// all bar settings
		bar = new JProgressBar();
		bar.setValue(0);
		bar.setBounds(185,420,350,50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli",Font.BOLD,25));
		bar.setForeground(Color.red);
		bar.setBackground(Color.black);
		
		menu.add(dataButton);
		menu.add(backButton);
		menu.add(setupButton);
		menu.add(tableButton);
		menu.add(mainLabel);
		menu.add(bar);
		menu.add(outputLabelOne);
		menu.add(queueStatusTextArea);
		
		
       // need for debug
       // menu.setVisible(true);
       // fill();
	}
	
	public void open() {
		menu.setVisible(true);
	}
	
	public void close() {
		menu.setVisible(false);
	}
	
	private void dataFunction() {
		menuManager.toMenu(4);
		this.close();
	}
	
	private void setupFunction() {
		menuManager.toMenu(1);
		this.close();
	}
	
	private void tableFunction() {
		menuManager.toMenu(3);
		this.close();
	}
	
	private void backFunction() {
		menuManager.toMenu(0);
		this.close();
	}
	// when called assigns User's settings to there respected variables 
	public void pullUserSettings() {
		
		fullServiceLines = simSettings.getFullService();
		selfServiceLines = simSettings.getSelfService();
		minArrivalTime = simSettings.getMinArrival();
		maxArrivalTime = simSettings.getMaxArrival();
		minServiceTime = simSettings.getMinService();
		maxServiceTime = simSettings.getMaxService();
		numCustomers = simSettings.getNumCust();
		percentSlower = simSettings.getPercentSlower();
	}
	
	// begins the when called
	public ArrayList <Customer> startSim() {
		
		// ensure bar is not filled before running
		resetBar();
		// call to fill the variables from User's settings
		pullUserSettings();
		
		// ArrayList to Store Customer Objects that is defined here in main AND passed
		// by reference to wherever
		customers = new ArrayList<Customer>();

		// CustomerCreator object which takes all USER SIMULATION SETTINGS
		CustomerCreator creator = new CustomerCreator(numCustomers, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);

		// call populateCustomers method within our creator to fill customers ArrayList
		// with Customer objects
		creator.populateCustomers(customers);

		fullQueues = new ArrayList<Queue>();
		selfQueues = new String[selfServiceLines];

		fullQueues = createFullQueues(fullServiceLines);
		selfQueues = createSelfQueues(selfServiceLines);
		
		selfCheckoutQueue = new SplitQueue(selfQueues);

		// Create a Simulator object with the number of customers to simulate
		// and pass it our Customer ArrayList and Queue objects
		// also pass VisualSimMenu instance (this) so we can update the output to the GUI
		Simulator sim = new Simulator(customers, fullQueues, selfCheckoutQueue, percentSlower, this);

		System.out.println("\n----- Starting Simulation -----\n");
		sim.runSimulationGUI();
		
				
		return customers;
	}
	
	public static ArrayList<Queue> createFullQueues(int full) {
		ArrayList<Queue> fullQ = new ArrayList<Queue>();

		int counter = 0;

		for (int i = 0; i < full; i++) {
			String queueName = "";
			if (counter < 26) {
				queueName = Character.toString((char) ('A' + counter));
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

			if (counter < 26) {
				queueName += Character.toString((char) ('A' + counter));
				counter++;

			} else {
				int suffix = ((counter - 26) / 26) + 1;
				String tempName = Character.toString((char) ('A' + (counter - 26) % 26)) + suffix;
				queueName += tempName;
				counter++;
			}
			selfQueues[i] = queueName;
		}
		return selfQueues;
	}
	
	// method to fill bar when called (still needs simulation implementation)
	public void fill() {
		
		if (simIsDone == true) {
			
			bar.setValue(100);
			bar.setString("All Customers Served!");
			// call static methods to print data from simulation
			printSimResults(customers, fullQueues, selfCheckoutQueue, selfServiceLines, simulationResultsArea);
		}
		else {
			counter += 1;
			bar.setValue(counter);	
		}
		

		
	}
	
	public void setOutputLabelOneText(String text) {
	    outputLabelOne.setText(text);
	}
	
	public static void updateQueueStatus(String status) {
		queueStatusTextArea.setText(status + "\n");
	}
	
	// reset the progress bar when called (also the JTextArea and JLabels)
	public void resetBar() {
		simulationResultsArea.setText("");
		outputLabelOne.setText("Nothing new...");
		queueStatusTextArea.setText("No line status updates...");
		bar.setValue(0);
		bar.setString(null);
		bar.setStringPainted(true);
		simIsDone = false;
		
		
	}
	
	public void setSimIsDone(boolean simIsDone) {
		this.simIsDone = simIsDone;
	}
	
	// Takes the original customers ArrayList and Queue objects to gather all
	// simulation results and print
	public static void printSimResults(ArrayList<Customer> customers, ArrayList<Queue> fullQueues, SplitQueue self,
			int numOfSelfLines, JTextArea simulationResultsArea) {

		int satisfiedCust = 0;
		int dissatisfiedCust = 0;
		DecimalFormat df = new DecimalFormat("##.#");

		simulationResultsArea.append("\n----- Simulation Data -----\n");

		// for loop all the customers to get each of their wait times and number
		// satisfied
		for (int i = 0; i < customers.size(); i++) {
			Customer c = customers.get(i);

			int ithCustWaitTime = (customers.get(i).waitingTime());

			// check for satisfied/non
			if (ithCustWaitTime < 5) {
				satisfiedCust++;

			}  else {
				dissatisfiedCust++;

			}
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

		simulationResultsArea.append("Average wait time for FULL: " + df.format(avgWaitTimeFull)
				+ " min. With FULL serving a total of " + numCustomersFull + " for the day.\n");
		simulationResultsArea.append("Average wait time for SELF: " + df.format(avgWaitTimeSelf)
				+ " min. With SELF serving a total of " + numCustomersSelf + " for the day.\n");

		int totalTimeNotUsedFull = 0;
		// loop thru ALL of fullChecouts, accumulate timenotused to variable
		for (int i = 0; i < fullQueues.size(); i++) {

			Queue q = fullQueues.get(i);

			totalTimeNotUsedFull += fullQueues.get(i).getTimeNotUsed();

		}

		for (int i = 0; i < self.getQueues().length - 1; i++) {

		}

		int totalTimeNotUsedSelf = self.getTotalTimeNotUsed();

		// print results
		simulationResultsArea.append("\n");
		simulationResultsArea.append("Total accumulated idle time for all checkouts (FULL): " + totalTimeNotUsedFull + " minutes\n");
		simulationResultsArea.append("Total accumulated idle time for all checkouts (SELF): " + self.getTotalTimeNotUsed() + " minutes\n");
		simulationResultsArea.append("Customer satisfaction: " + satisfiedCust + " satisfied (<5 minutes) " + dissatisfiedCust + " dissatisfied (>=5 minutes)\n");

		// output current line suggestions
		simulationResultsArea.append("\n----- Line Suggestions -----\n");

		double idealWaitTimeThreshold = 2.0; // our ideal wait time for lines (can change as needed)
		double idealIdleTimeThreshold = 30.0; // our ideal idle time for lines (can change as needed)

		// checking if wait times are high 
		if (isSignificantDifference(avgWaitTimeFull, avgWaitTimeSelf, idealWaitTimeThreshold)) {
		    if (avgWaitTimeFull > avgWaitTimeSelf) {
		        simulationResultsArea.append("Consider adding a FULL checkout line to reduce wait time.\n");
		    } else {
		        simulationResultsArea.append("Consider adding a SELF checkout line to reduce wait time.\n");
		    }
		} else {
		    simulationResultsArea.append("No need to add any lines.\n");
		}

		// checking if idle times are high 
		if (totalTimeNotUsedFull > idealIdleTimeThreshold || totalTimeNotUsedSelf > idealIdleTimeThreshold) {
		    if (totalTimeNotUsedFull > idealIdleTimeThreshold && totalTimeNotUsedSelf > idealIdleTimeThreshold) {
		        if (fullQueues.size() != 1 && numOfSelfLines != 1) {
		            simulationResultsArea.append("Consider removing a FULL checkout line and a SELF checkout line to reduce idle time.\n");
		        }
		        else {
				    simulationResultsArea.append("We cannot remove anymore lines, we MUST ATLEAST have 1 of each.\n");
				}
		    } else if (totalTimeNotUsedFull > totalTimeNotUsedSelf && fullQueues.size() != 1) {
		        simulationResultsArea.append("Consider removing a FULL checkout line to reduce idle time.\n");
		    } else if (numOfSelfLines != 1) {
		        simulationResultsArea.append("Consider removing a SELF checkout line to reduce idle time.\n");
		    } else {
		        simulationResultsArea.append("No need to remove any lines.\n");
		    }
		} else {
		    simulationResultsArea.append("No need to remove any lines.\n");
		}

	} // end printSimResults

	// evaluates if there's a significant difference in the average wait times and
	// idle times for FULL and SELF checkouts.
	private static boolean isSignificantDifference(double value1, double value2, double threshold) {
		return Math.abs(value1 - value2) >= threshold;
	}
}
