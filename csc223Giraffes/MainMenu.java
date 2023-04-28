package csc223Giraffes;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;

public class MainMenu {
	
	public static JLabel mainLabel;
	public static JButton backButton;
	public static JButton dataButton;
	public static JButton setupButton;
	public static JButton simulationButton;
	public static JButton tableButton;
	private static JFrame menu;
	
	public MainMenu() {
		//Setting up the frame
		menu = new JFrame();
		menu.setTitle("Main Menu");
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
		
		ImageIcon image = new ImageIcon(newPath);
				
		mainLabel = new JLabel();
		mainLabel.setText("Welcome to CSC223 Giraffes!");
		mainLabel.setIcon(image);
		mainLabel.setHorizontalTextPosition(JLabel.CENTER);
		mainLabel.setVerticalTextPosition(JLabel.TOP);
		mainLabel.setForeground(Color.blue);
		mainLabel.setBackground(Color.white);
		mainLabel.setFont(new Font(Font.SERIF,Font.BOLD,40));
		mainLabel.setIconTextGap(5);
		mainLabel.setOpaque(true);
		mainLabel.setHorizontalAlignment(JLabel.CENTER);
		mainLabel.setVerticalAlignment(JLabel.CENTER);
		mainLabel.setBounds(0,0,700,350);
		
		//Buttons
		Border border = BorderFactory.createLineBorder(Color.black,2);

		dataButton = new JButton();
		dataButton.setBounds(125,400,200,100);
		dataButton.setText("Database Functions");
		dataButton.setBackground(Color.white);
		dataButton.setBorder(border);
		dataButton.setFocusable(false);
		dataButton.addActionListener(e -> dataFunction());
		
		setupButton = new JButton();
		setupButton.setBounds(375, 400, 200, 100);
		setupButton.setText("Simulation Setup");
		setupButton.setBackground(Color.white);
		setupButton.setBorder(border);
		setupButton.setFocusable(false);
		setupButton.addActionListener(e -> setupFunction());
		
		simulationButton = new JButton();
		simulationButton.setBounds(375, 525, 200, 100);
		simulationButton.setText("Simulation Viewer");
		simulationButton.setBackground(Color.white);
		simulationButton.setBorder(border);
		simulationButton.setFocusable(false);
		simulationButton.addActionListener(e -> simulationFunction());
		
		tableButton = new JButton();
		tableButton.setBounds(125, 525, 200, 100);
		tableButton.setText("Simulation Data");
		tableButton.setBackground(Color.white);
		tableButton.setBorder(border);
		tableButton.setFocusable(false);
		tableButton.addActionListener(e -> tableFunction());
		
		menu.add(dataButton);
		menu.add(setupButton);
		menu.add(simulationButton);
		menu.add(tableButton);
		menu.add(mainLabel);
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
	
	private void tableFunction() {
		System.out.println("Openning table menu...");
	}
}
