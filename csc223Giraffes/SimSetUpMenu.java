package csc223Giraffes;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SimSetUpMenu {
	
	private MenuManager menuManager;
	
	private static JLabel mainLabel;
	private static JButton backButton;
	private static JButton dataButton;
	private static JButton simulationButton;
	private static JButton tableButton;
	private static JFrame menu;
	
	// declare JLabels and JTextFields
	private JLabel fullServiceLinesLabel;
    private JTextField fullServiceLines;
    private JLabel selfServiceLinesLabel;
    private JTextField selfServiceLines;
    private JLabel minArrivalTimeLabel;
    private JTextField minArrivalTime;
    private JLabel maxArrivalTimeLabel;
    private JTextField maxArrivalTime;
    private JLabel minServiceTimeLabel;
    private JTextField minServiceTime;
    private JLabel maxServiceTimeLabel;
    private JTextField maxServiceTime;
    private JLabel numCustomersLabel;
    private JTextField numCustomers;
    private JLabel percentSlowerLabel;
    private JTextField percentSlower;
    

	public SimSetUpMenu(MenuManager mm) {
		menuManager = mm;
		
		//Setting up the frame
		menu = new JFrame();
		menu.setTitle("Simulation Setup");
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setResizable(false);
		menu.setSize(700,700);
		menu.setLayout(null);
		menu.getContentPane().setBackground(Color.white);
		
		//Creating the icon for the frame
		String logoName = "JavaRUsLogo.png";
		String defaultPath = new File(logoName).getAbsolutePath().replace("\\", "\\\\");
		String newPath = defaultPath.substring(0,defaultPath.length()-logoName.length())+"src\\testing\\"+defaultPath.substring(defaultPath.length()-logoName.length());
		ImageIcon icon = new ImageIcon(newPath);
		menu.setIconImage(icon.getImage());
		
		//Making the main label for this menu
		logoName = "JavaRUsMainMenu.png";
		defaultPath = new File(logoName).getAbsolutePath().replace("\\", "\\\\");
		newPath = defaultPath.substring(0,defaultPath.length()-logoName.length())+"src\\testing\\"+defaultPath.substring(defaultPath.length()-logoName.length());

		//Making the main label for this menu
		mainLabel = new JLabel();
		mainLabel.setText("Enter Your Sim Settings:");
		mainLabel.setHorizontalTextPosition(JLabel.CENTER);
		mainLabel.setVerticalTextPosition(JLabel.TOP);
		mainLabel.setForeground(Color.blue);
		mainLabel.setBackground(Color.white);
		mainLabel.setFont(new Font(Font.SERIF,Font.BOLD,20));
		mainLabel.setIconTextGap(5);
		mainLabel.setOpaque(true);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setVerticalAlignment(JLabel.CENTER);
		mainLabel.setBounds(25,4,675,20);
		

		
		//Buttons
		Border border = BorderFactory.createLineBorder(Color.black,2);

		backButton = new JButton();
		backButton.setBounds(5,5,20,20);
		backButton.setText("<=");
		backButton.setBackground(Color.white);
		backButton.setBorder(border);
		backButton.setFocusable(false);
		backButton.addActionListener(e -> backFunction());
		
		dataButton = new JButton();
		dataButton.setBounds(20,500,200,100);
		dataButton.setText("Database Functions");
		dataButton.setBackground(Color.white);
		dataButton.setBorder(border);
		dataButton.setFocusable(false);
		dataButton.addActionListener(e -> dataFunction());
		
		simulationButton = new JButton();
		simulationButton.setBounds(245, 500, 200, 100);
		simulationButton.setText("Simulation Viewer");
		simulationButton.setBackground(Color.white);
		simulationButton.setBorder(border);
		simulationButton.setFocusable(false);
		simulationButton.addActionListener(e -> simulationFunction());
		
		tableButton = new JButton();
		tableButton.setBounds(470, 500, 200, 100);
		tableButton.setText("Simulation Data");
		tableButton.setBackground(Color.white);
		tableButton.setBorder(border);
		tableButton.setFocusable(false);
		tableButton.addActionListener(e -> tableFunction());
		
		menu.add(dataButton);
		menu.add(simulationButton);
		menu.add(tableButton);
		menu.add(mainLabel);
		menu.add(backButton);
		
		// JLabels for the prompts
        fullServiceLinesLabel = new JLabel("Number of full-service lines:");
        fullServiceLinesLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        fullServiceLinesLabel.setBounds(50, 80, 300, 30);

        selfServiceLinesLabel = new JLabel("Number of self-service lines:");
        selfServiceLinesLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        selfServiceLinesLabel.setBounds(50, 120, 300, 30);

        minArrivalTimeLabel = new JLabel("Enter minimum arrival time between customers:");
        minArrivalTimeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        minArrivalTimeLabel.setBounds(50, 160, 400, 30);

        maxArrivalTimeLabel = new JLabel("Enter maximum arrival time between customers:");
        maxArrivalTimeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        maxArrivalTimeLabel.setBounds(50, 200, 400, 30);

        minServiceTimeLabel = new JLabel("Enter minimum service time:");
        minServiceTimeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        minServiceTimeLabel.setBounds(50, 240, 300, 30);

        maxServiceTimeLabel = new JLabel("Enter maximum service time:");
        maxServiceTimeLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        maxServiceTimeLabel.setBounds(50, 280, 300, 30);

        numCustomersLabel = new JLabel("Enter number of customers to serve:");
        numCustomersLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        numCustomersLabel.setBounds(50, 320, 300, 30);

        percentSlowerLabel = new JLabel("Percentage slower for SELF:");
        percentSlowerLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        percentSlowerLabel.setBounds(50, 360, 300, 30);

        // JTextFields for user input
        fullServiceLines = new JTextField();
        fullServiceLines.setBounds(450, 80, 150, 30);
        fullServiceLines.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        selfServiceLines = new JTextField();
        selfServiceLines.setBounds(450, 120, 150, 30);
        selfServiceLines.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        minArrivalTime = new JTextField();
        minArrivalTime.setBounds(450, 160, 150, 30);
        minArrivalTime.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
        
        maxArrivalTime = new JTextField();
        maxArrivalTime.setBounds(450, 200, 150, 30);
        maxArrivalTime.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        minServiceTime = new JTextField();
        minServiceTime.setBounds(450, 240, 150, 30);
        minServiceTime.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        maxServiceTime = new JTextField();
        maxServiceTime.setBounds(450, 280, 150, 30);
        maxServiceTime.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        numCustomers = new JTextField();
        numCustomers.setBounds(450, 320, 150, 30);
        numCustomers.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        percentSlower = new JTextField();
        percentSlower.setBounds(450, 360, 150, 30);
        percentSlower.setFont(new Font(Font.SERIF, Font.PLAIN, 15));

        

        // Add the JLabels and JTextFields objects
        menu.add(fullServiceLinesLabel);
        menu.add(fullServiceLines);
        menu.add(selfServiceLinesLabel);
        menu.add(selfServiceLines);
        menu.add(minArrivalTimeLabel);
        menu.add(minArrivalTime);
        menu.add(maxArrivalTimeLabel);
        menu.add(maxArrivalTime);
        menu.add(minServiceTimeLabel);
        menu.add(minServiceTime);
        menu.add(maxServiceTimeLabel);
        menu.add(maxServiceTime);
        menu.add(numCustomersLabel);
        menu.add(numCustomers);
        menu.add(percentSlowerLabel);
        menu.add(percentSlower);
	}
	
	public void open() {
		menu.setVisible(true);
	}
	
	public void close() {
		menu.setVisible(false);
	}
	
	private void backFunction() {
		menuManager.toMenu(0);
		this.close();
	}
	
	private void dataFunction() {
		menuManager.toMenu(4);
		this.close();
	}
	
	
	private void simulationFunction() {
		menuManager.toMenu(2);
		this.close();
	}
	
	private void tableFunction() {
		menuManager.toMenu(3);
		this.close();
	}
}
