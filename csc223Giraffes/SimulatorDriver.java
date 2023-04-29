package csc223Giraffes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;

import java.net.*;

/**
 * 
 * @author Troy F.
 * @author Parker S.
 *
 */

public class SimulatorDriver {

	private static Connection conn = null;
	private static Statement stmt = null;

	public static void main(String[] args) { // begin main
		
		MenuManager menus = new MenuManager();
        menus.start();

		System.out.println("---*** ----MYSQLJDBC Connection Testing----***----\n");

		conn = createConnection();

		
		Scanner scan = new Scanner(System.in);
		int fullServiceLines;
		int selfServiceLines;
		int minArrivalTime;
		int maxArrivalTime;
		int minServiceTime;
		int maxServiceTime;
		int numCustomers;

		printRecSet();

		// GET USER SIMULATION SETTINGS
		System.out.println("Number of full-service lines:");
		fullServiceLines = scan.nextInt();

		System.out.println("Number of self-service lines:");
		selfServiceLines = scan.nextInt();

		System.out.println("Enter minimum arrival time between customers:");
		minArrivalTime = scan.nextInt();

		System.out.println("Enter maximum arrival time between customers:");
		maxArrivalTime = scan.nextInt();

		System.out.println("Enter minimum service time:");
		minServiceTime = scan.nextInt();

		System.out.println("Enter maximum service time:");
		maxServiceTime = scan.nextInt();

		System.out.println("Enter number of customers to serve:");
		numCustomers = scan.nextInt();

		System.out.println("Percentage slower for SELF: ");
		double percentSlower = scan.nextDouble();

		// ArrayList to Store Customer Objects that is defined here in main AND passed
		// by reference to wherever
		ArrayList<Customer> customers = new ArrayList<Customer>();

		// CustomerCreator object which takes all USER SIMULATION SETTINGS
		CustomerCreator creator = new CustomerCreator(numCustomers, minArrivalTime, maxArrivalTime, minServiceTime,
				maxServiceTime);

		// call populateCustomers method within our creator to fill customers ArrayList
		// with Customer objects
		creator.populateCustomers(customers);

		ArrayList<Queue> fullQueues = new ArrayList<Queue>();
		String[] selfQueues = new String[selfServiceLines];

		fullQueues = createFullQueues(fullServiceLines);

		selfQueues = createSelfQueues(selfServiceLines);

		/**
		 * Queue checkoutAQueue = new Queue("A"); Queue checkoutBQueue = new Queue("B");
		 * Queue checkoutCQueue = new Queue("C");
		 **/
		SplitQueue selfCheckoutQueue = new SplitQueue(selfQueues);

		// Create a Simulator object with the number of customers to simulate
		// and pass it our Customer ArrayList and Queue objects
		Simulator sim = new Simulator(customers, fullQueues, selfCheckoutQueue, percentSlower);

		System.out.println("\n----- Starting Simulation -----\n");
		sim.runSimulation();

		scan.close();

		// call static methods to print data from simulation
		printSimResults(customers, fullQueues, selfCheckoutQueue, selfServiceLines);
		printSimResultsTable(customers);

		openPhpMyAdmin();

		databaseMenu();

		closeConnection();

	} // end main

	// - - - - - - - - - - - - - static methods - - - - - - - - - - - - -

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

	// Takes the original customers ArrayList and Queue objects to gather all
	// simulation results and print
	public static void printSimResults(ArrayList<Customer> customers, ArrayList<Queue> fullQueues, SplitQueue self,
			int numOfSelfLines) {

		int satisfiedCust = 0;
		int dissatisfiedCust = 0;
		DecimalFormat df = new DecimalFormat("##.#");

		System.out.println("\n----- Simulation Data -----\n");

		// for loop all the customers to get each of their wait times and number
		// satisfied
		for (int i = 0; i < customers.size(); i++) {
			Customer c = customers.get(i);

			int ithCustWaitTime = (customers.get(i).waitingTime());

			// check for satisfied/non
			if (ithCustWaitTime < 5) {
				satisfiedCust++;
				
				
				 
				//code for inserting satisfied customers into database table
				try { // begin try
					PreparedStatement stmt = conn.prepareStatement("INSERT INTO satisfied_customer "
							+ "(customer_id, arrival_time, service_time, departure_time, wait_time, queue, satisfied) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
					stmt.setInt(1, c.getCustId());
					stmt.setInt(2, c.getArrivalTime());
					stmt.setInt(3, c.getServiceTime());
					stmt.setInt(4, c.getEndTime());
					stmt.setInt(5, c.waitingTime());
					stmt.setString(6, c.getUsedLine());
					stmt.setString(7, "TRUE");
					stmt.executeUpdate(); // Execute the insert statement
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} else {
				dissatisfiedCust++;
				
				//code for inserting dissatisfied customers into database table
				try { // begin try
					PreparedStatement stmt = conn.prepareStatement("INSERT INTO dissatisfied_customer "
							+ "(customer_id, arrival_time, service_time, departure_time, wait_time, queue, satisfied) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
					stmt.setInt(1, c.getCustId());
					stmt.setInt(2, c.getArrivalTime());
					stmt.setInt(3, c.getServiceTime());
					stmt.setInt(4, c.getEndTime());
					stmt.setInt(5, c.waitingTime());
					stmt.setString(6, c.getUsedLine());
					stmt.setString(7, "TRUE");
					stmt.executeUpdate(); // Execute the insert statement
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
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

		System.out.println("Average wait time for FULL: " + df.format(avgWaitTimeFull)
				+ " min. With FULL serving a total of " + numCustomersFull + " for the day.");
		System.out.println("Average wait time for SELF: " + df.format(avgWaitTimeSelf)
				+ " min. With SELF serving a total of " + numCustomersSelf + " for the day.");

		int totalTimeNotUsedFull = 0;
		// loop thru ALL of fullChecouts, accumulate timenotused to variable
		for (int i = 0; i < fullQueues.size(); i++) {

			totalTimeNotUsedFull += fullQueues.get(i).getTimeNotUsed();
		}
		int totalTimeNotUsedSelf = self.getTotalTimeNotUsed();

		// print results
		System.out.println();
		System.out
				.println("Total accumulated idle time for all checkouts (FULL): " + totalTimeNotUsedFull + " minutes");
		System.out.println(
				"Total accumulated idle time for all checkouts (SELF): " + self.getTotalTimeNotUsed() + " minutes");
		System.out.println("Customer satisfaction: " + satisfiedCust + " satisfied (<5 minutes) " + dissatisfiedCust
				+ " dissatisfied (>=5 minutes)");

		// output current line suggestions
		System.out.println("\n----- Line Suggestions -----\n");

		double idleWaitTimeThreshold = 2.0; // our ideal wait time for lines (can change as needed)
		double idleTimeThreshold = 30.0; // our ideal idle time for lines (can change as needed)

		// create another nested if/elseif statement for if idle time is too high

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
			} else if (numOfSelfLines != 1) {
				System.out.println("Consider removing a SELF checkout line to reduce idle time.");
			} else {
				System.out.println("No need to remove any lines.");
			}
		} else {
			System.out.println("No need to remove any lines.");
		}

	} // end printSimResults

	// evaluates if there's a significant difference in the average wait times and
	// idle times for FULL and SELF checkouts.
	private static boolean isSignificantDifference(double value1, double value2, double threshold) {
		return Math.abs(value1 - value2) >= threshold;
	}

	// Takes the data and prints out formatted table
	public static void printSimResultsTable(ArrayList<Customer> customers) {

		System.out.println();
		System.out.println(
				"|-------|------------------------|--------------|----------------------------|-------------|-----------------|");
		System.out.println(
				"| Cust #| Arrival Time (absolute)| Service Time |  Departure Time (absolute) |  Wait Time  | Queue Location  |");
		System.out.println(
				"|-------|------------------------|--------------|----------------------------|-------------|-----------------|");

		// for loop each row of table to print data
		for (int i = 0; i < customers.size(); i++) {

			Customer ithCustomer = customers.get(i);

			System.out.printf("| %5d | %22d | %12d | %26d | %11d | %15s |\n", ithCustomer.getCustId(),
					ithCustomer.getArrivalTime(), ithCustomer.getServiceTime(), ithCustomer.getEndTime(),
					ithCustomer.waitingTime(), ithCustomer.getUsedLine());
			System.out.println(
					"|-------|------------------------|--------------|----------------------------|-------------|-----------------|");
			try { // begin try
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO sim_results "
						+ "(customer_id, arrival_time, service_time, departure_time, wait_time, queue) "
						+ "VALUES (?, ?, ?, ?, ?, ?)");
				stmt.setInt(1, ithCustomer.getCustId());
				stmt.setInt(2, ithCustomer.getArrivalTime());
				stmt.setInt(3, ithCustomer.getServiceTime());
				stmt.setInt(4, ithCustomer.getEndTime());
				stmt.setInt(5, ithCustomer.waitingTime());
				stmt.setString(6, ithCustomer.getUsedLine());
				stmt.executeUpdate(); // Execute the insert statement
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} // end for

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

	public static Connection createConnection() {

		String user = "csc223";
		String pass = "csc223";
		String name = "giraffes"; // <-- put your team name here
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/csc223Giraffes";

		System.out.println(driver);
		System.out.println(url);

		PreparedStatement stmt;

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection really is from : " + conn.getClass().getName());
			System.out.println("Connection successful!");

			// clear the table
			stmt = conn.prepareStatement("DELETE FROM sim_results");
			stmt.executeUpdate();
			System.out.println("The table has been cleared.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void openPhpMyAdmin() {
		try {
			// Replace 'localhost' and 'phpmyadmin' with the appropriate values
			String url = "http://localhost/phpmyadmin/index.php?route=/sql&server=1&db=csc223giraffes&table=sim_results&pos=0";
			Desktop.getDesktop().browse(new URI(url));

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
				// stmt.close();
				System.out.println("The connectionwas successfully closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void checkConnect() {
		if (conn == null) {
			conn = createConnection();
		}
		if (stmt == null) {
			try {
				stmt = conn.createStatement();
			} catch (SQLException e) {
				System.out.println("Cannot create the statement");
			}

		}
	}

	public static void databaseMenu() {
		

		Scanner scan = new Scanner(System.in);
		// for selecting different queuries user can execute
		boolean more = true;

		while (more != false) { // begin while

			System.out.println("----***----DATABASE MENU OPTIONS----****-----\n");
			
			System.out.println("1.) print out all satisfied customers");
			System.out.println("2.) print out all dissatisfed customers");
			
			int choice = scan.nextInt();
			
			
			if(choice == 1) {
				custPrintDatabase();
			}
			else if(choice ==2) {
				custSatisfiedDatabase();
			}
			else {
				more = false;
			}

		} // begin while
		scan.close();

	} // end method 
	
	public static void custPrintDatabase() {
		checkConnect();
		
		

		
	}
	
	public static void custSatisfiedDatabase() {
		checkConnect();
		
		//String query
		String query = "SELECT * FROM satisfied_customers";
		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			System.out.println(" ");
			System.out.println("ID  customer_ID   arrival_time  service_time   departure_time   wait_time  queue   satisfied");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				
				int custId = rs.getInt("customer_id"); 
				int arrival = rs.getInt("arrival_time");
				int service = rs.getInt("service_time");
				int departure = rs.getInt("departure_time");
				int wait = rs.getInt("wait_time");
				String queue = rs.getString("queue");
				String satisfied = rs.getString("satisfied");

				System.out.println(custId + "     " + arrival + "       " + service + "      " + departure + "      " + wait + "      " + queue+ "      " + satisfied );
			}
		}

		catch (SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
		}

	}
	
	public static void custDissatisfiedDatabase() {
		checkConnect();
		
		//String query
		String query = "SELECT * FROM dissatisfied_customers";
		try {
			stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			System.out.println(" ");
			System.out.println("ID  customer_ID   arrival_time  service_time   departure_time   wait_time  queue   dissatisfied");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				
				int custId = rs.getInt("customer_id"); 
				int arrival = rs.getInt("arrival_time");
				int service = rs.getInt("service_time");
				int departure = rs.getInt("departure_time");
				int wait = rs.getInt("wait_time");
				String queue = rs.getString("queue");
				String dissatisfied = rs.getString("satisfied");

				System.out.println(custId + "     " + arrival + "       " + service + "      " + departure + "      " + wait + "      " + queue+ "      " + dissatisfied );
			}
		}

		catch (SQLException e) {
			System.out.println("SQL Exception");
			e.printStackTrace();
		}

	}
	

} // end class SimulatorDriver
