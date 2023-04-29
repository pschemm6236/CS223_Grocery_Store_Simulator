package csc223Giraffes;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class DataTableMenu {
	private MenuManager menuManager;
	private String[][] data;
	private  JTable table;
	private  JScrollPane scrollPane;
	private  JButton backButton;
	private  JButton dataButton;
	private  JButton setupButton;
	private  JButton simulationButton;
	private  JFrame menu;
	
	public DataTableMenu(MenuManager mm) {
		//Declares the menuManager
		menuManager = mm;
		
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
		String newPath = defaultPath.substring(0,defaultPath.length()-logoName.length())+"csc223Giraffes\\"+defaultPath.substring(defaultPath.length()-logoName.length());
		ImageIcon icon = new ImageIcon(newPath);
		menu.setIconImage(icon.getImage());
		
		
		//TWEAK THIS:
		//ADD MAKE OPEN METHOD UPDATE THE TABLE
		
		//Table
	    this.data = new String[][]{ {"N/A","N/A","N/A","N/A","N/A","N/A"}};    
	    String column[]={"Cust#","Arrival Time(abs)","Service Time","Depart Time(abs)","Wait Time", "Queue Location"};  
	    
	    table = new JTable(this.data,column);    
	    table.setBounds(25,40,625,465);  
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(25,40,625,465);   

		
		//Buttons
		Border border = BorderFactory.createLineBorder(Color.black,2);

		backButton = new JButton();
		backButton.setBounds(5,5,30,30);
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
	
	public void open(ArrayList<Customer> customers) {
		//This updates the table for possible new data
		menu.remove(scrollPane);
		
		String column[]={"Cust#","Arrival Time(abs)","Service Time","Depart Time(abs)","Wait Time", "Queue Location"}; 
		table = new JTable(toArray(customers),column);    
	    table.setBounds(25,30,625,475);  
	    scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(25,30,625,475); 
	    
	    menu.add(scrollPane);
	    
		menu.setVisible(true);
	}
	
	public void close() {
		menu.setVisible(false);
	}
	
	private static String[][] toArray(ArrayList<Customer> customers){
		String[][] textData = new String[customers.size()][6];
		
		for(int i=0;i<customers.size();i++) {
			textData[i][0] = customers.get(i).getCustId()+"";
			textData[i][1] = customers.get(i).getArrivalTime()+"";
			textData[i][2] = customers.get(i).getServiceTime()+"";
			textData[i][3] = customers.get(i).getEndTime()+"";
			textData[i][4] = (customers.get(i).getStartTime()-customers.get(i).getArrivalTime())+"";
			textData[i][5] = customers.get(i).getUsedLine()+"";
		}
		
		return textData;
		
		
	}
	
	private void dataFunction() {
		menuManager.toMenu(4);
		this.close();
	}
	
	private void setupFunction() {
		menuManager.toMenu(1);
		this.close();
	}
	
	private void simulationFunction() {
		menuManager.toMenu(2);
		this.close();
	}
	
	private void backFunction() {
		menuManager.toMenu(0);
		this.close();
	}
}

