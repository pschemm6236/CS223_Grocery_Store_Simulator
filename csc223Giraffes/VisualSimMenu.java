package csc223Giraffes;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
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
	public static JButton backButton;
	public static JButton dataButton;
	public static JButton setupButton;
	public static JButton simulationButton;
	public static JButton tableButton;
	public static int counter;
	public boolean simIsDone = false;
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
		outputLabelOne.setFont(new Font(Font.SERIF,Font.BOLD,40));
		outputLabelOne.setIconTextGap(5);
		outputLabelOne.setOpaque(true);
		outputLabelOne.setHorizontalAlignment(JLabel.CENTER);
		outputLabelOne.setVerticalAlignment(JLabel.CENTER);
		outputLabelOne.setBounds(0, 34, 700, 40);

		
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
		bar.setBounds(185,350,350,50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli",Font.BOLD,25));
		bar.setForeground(Color.red);
		bar.setBackground(Color.black);
		
		// method test call to fill up bar
		
		
		menu.add(dataButton);
		menu.add(backButton);
		menu.add(setupButton);
		menu.add(tableButton);
		menu.add(mainLabel);
		menu.add(bar);
		menu.add(outputLabelOne);
		
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
		
		// call to fill the variables from User's settings
		pullUserSettings();
		
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

		// Create a Simulator object with the number of customers to simulate
		// and pass it our Customer ArrayList and Queue objects
		// also pass VisualSimMenu instance (this) so we can update the output to the GUI
		Simulator sim = new Simulator(customers, fullQueues, selfCheckoutQueue, percentSlower, this);

		System.out.println("\n----- Starting Simulation -----\n");
		sim.runSimulation();

		// force adjust progress bar to 100% if sim is done
		simIsDone = true;
		fill();
		
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
		}
		else {
			counter += 1;
			bar.setValue(counter);	
		}
		

		
	}

	// method to fill bar when called (still needs simulation implementation)
	public void oldFill() {
		int counter =0;
		
		while(counter<=8) {
			
			bar.setValue(counter);
			outputLabelOne.setText("counter = " + counter);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter +=1;
		}
		bar.setString("Done! :)");
	}
	
	public void setOutputLabelOneText(String text) {
	    outputLabelOne.setText(text);
	}
}
