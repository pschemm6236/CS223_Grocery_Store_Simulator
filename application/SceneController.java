package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController implements Initializable{

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	// FXML fields for start sim button
	@FXML
	private TextField fullLines;
	@FXML
	private TextField selfLines;
	@FXML
	private TextField minArrival;
	@FXML
	private TextField maxArrival;
	@FXML
	private TextField minService;
	@FXML
	private TextField maxService;
	@FXML
	private TextField numCust;
	@FXML
	private TextField percentSlowSelf;
	@FXML
	Button simButton;
	
	// FXML fields for running sim
	@FXML
	private ProgressBar myProgressBar;
	@FXML
	private Label myRunLabel, myTimeLabel;
	
	public static Label static_Label;
	
	public static Label static_Label2;
	
	
	// fields for simulatorDriver
	int fullServiceLines;
	int selfServiceLines;
	int minArrivalTime;
	int maxArrivalTime;
	int minServiceTime;
	int maxServiceTime;
	int numCustomers;
	double percentSlower;
	
	

	public void switchToWelcomeScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("WelcomeScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToRecSettingScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RecSettingScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToStartSimScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("StartSimScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToRunningSimsScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RunningSimsScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	/* 
	
	created method overload for when user actually enters data, the simulation will start 
	
	(NOTE This is a bad design but I have a method overload because I am assuming that the user will not switch
	between menus once they are sent to the Running Sims tab. I did this since I am not sure how to keep the simulation running
	if the user decides to switch while the simulation is going. 
	
	 */
	public void switchToRunningSimsScene(ActionEvent event, boolean runSim) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("RunningSimsScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		// - - - - - - - - - - - - - This is basically Simulator Driver - - - - - - - - - - - - -
		
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
		
		SplitQueue selfCheckoutQueue = new SplitQueue(selfQueues);

		// Create a Simulator object with the number of customers to simulate
		// and pass it our Customer ArrayList and Queue objects
		SimulatorFX sim = new SimulatorFX(customers, fullQueues, selfCheckoutQueue, percentSlower);

		System.out.println("\n----- Starting Simulation -----\n");
		

		sim.runSimulation();

		
	}
	


	
	public void switchToSimResultsScene(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SimResultsScene.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	public void startSim(ActionEvent event) {
		
		fullServiceLines = Integer.parseInt(fullLines.getText());
		selfServiceLines = Integer.parseInt(selfLines.getText());
		minArrivalTime = Integer.parseInt(minArrival.getText());
		maxArrivalTime = Integer.parseInt(maxArrival.getText());
		minServiceTime = Integer.parseInt(minService.getText());
		maxServiceTime = Integer.parseInt(maxService.getText());
		numCustomers = Integer.parseInt(numCust.getText());
		percentSlower = Double.parseDouble(percentSlowSelf.getText());
		boolean startSim = true;
		// debug
		/*
		System.out.println("fullServiceLines = " + fullServiceLines);
		System.out.println("selfServiceLines = " + selfServiceLines);
	    System.out.println("minArrivalTime = " + minArrivalTime);
	    System.out.println("maxArrivalTime = " + maxArrivalTime);
	    System.out.println("minServiceTime = " + minServiceTime);
	    System.out.println("maxServiceTime = " + maxServiceTime);
	    System.out.println("numCustomers = " + numCustomers);
	    System.out.println("percentSlower = " + percentSlower);
	    */
		
		try {
	        switchToRunningSimsScene(event, startSim);
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		static_Label=myRunLabel; 
		static_Label2=myTimeLabel;
	}
	
	public void increaseProgress() {
		
	}
	
	// - - - - - - - - - - - - - SIMULATION static methods - - - - - - - - - - - - -
	
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
}
