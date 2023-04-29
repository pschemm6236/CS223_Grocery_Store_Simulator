package csc223Giraffes;

import java.awt.Color;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class DataTableMenu {
	public String[][] data;
	public  JTable table;
	public  JScrollPane scrollPane;
	public  JButton backButton;
	public  JButton dataButton;
	public  JButton setupButton;
	public  JButton simulationButton;
	private  JFrame menu;
	
	public DataTableMenu() {
		//Setting up the frame
		menu = new JFrame();
		menu.setTitle("Data Table Menu");
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
		
		
		//TWEAK THIS:
		//ADD MAKE OPEN METHOD UPDATE THE TABLE
		
		//Table
	    this.data = new String[][]{ {"101","1","2","N/A","N/A","N/A"},    
	                          {"102","3","4","N/A","N/A","N/A"},    
	                          {"101","5","6","N/A","N/A","N/A"}};    
	    String column[]={"Cust#","Arrival Time(abs)","Service Time","Depart Time(abs)","Wait Time", "Queue Location"};  
	    
	    table = new JTable(this.data,column);    
	    table.setBounds(25,30,625,475);  
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(25,30,625,475);   

		
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
		dataButton.setBounds(20,525,200,100);
		dataButton.setText("Database Functions");
		dataButton.setBackground(Color.white);
		dataButton.setBorder(border);
		dataButton.setFocusable(false);
		dataButton.addActionListener(e -> dataFunction());
		
		setupButton = new JButton();
		setupButton.setBounds(245, 525, 200, 100);
		setupButton.setText("Simulation Setup");
		setupButton.setBackground(Color.white);
		setupButton.setBorder(border);
		setupButton.setFocusable(false);
		setupButton.addActionListener(e -> setupFunction());
		
		simulationButton = new JButton();
		simulationButton.setBounds(470, 525, 200, 100);
		simulationButton.setText("Simulation Viewer");
		simulationButton.setBackground(Color.white);
		simulationButton.setBorder(border);
		simulationButton.setFocusable(false);
		simulationButton.addActionListener(e -> simulationFunction());
		
	    menu.add(scrollPane);
	    menu.add(backButton);
	    menu.add(dataButton);
	    menu.add(setupButton);
		menu.add(simulationButton);
	}
	
	public void open() {
		menu.setVisible(true);
	}
	
	public void close() {
		menu.setVisible(false);
	}
	
	private void dataFunction() {
		System.out.println("Openning database menu...");
	}
	
	private void setupFunction() {
		System.out.println("Openning setup menu...");
	}
	
	private void simulationFunction() {
		System.out.println("Openning simulation menu...");
	}
	
	private void backFunction() {
		System.out.println("Openning main menu...");
	}
}
