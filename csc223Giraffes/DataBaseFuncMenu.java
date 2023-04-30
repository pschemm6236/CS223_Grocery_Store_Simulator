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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class DataBaseFuncMenu {
	private MenuManager menuManager;
	private  JButton backButton;
	private  JButton tableButton;
	private  JButton setupButton;
	private  JButton simulationButton;
	private  JFrame menu;
	public static JLabel mainLabel;
	public static JLabel mainLabel2;
	
	public DataBaseFuncMenu(MenuManager mm) {
		//Declares the menuManager
		menuManager = mm;
		
		//Setting up the frame
		menu = new JFrame();
		menu.setTitle("Database Functions");
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
		mainLabel.setText("Database Functions not availible in GUI,");
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
		
		mainLabel2 = new JLabel();
		mainLabel2.setText("to access them run the simulatorDriver class.");
		mainLabel2.setHorizontalTextPosition(JLabel.CENTER);
		mainLabel2.setVerticalTextPosition(JLabel.TOP);
		mainLabel2.setForeground(Color.blue);
		mainLabel2.setBackground(Color.white);
		mainLabel2.setFont(new Font(Font.SERIF,Font.BOLD,20));
		mainLabel2.setIconTextGap(5);
		mainLabel2.setOpaque(true);
		mainLabel2.setHorizontalAlignment(JLabel.CENTER);
		mainLabel2.setVerticalAlignment(JLabel.CENTER);
		mainLabel2.setBounds(0,25,700,20);
		
		//Buttons
		Border border = BorderFactory.createLineBorder(Color.black,2);

		backButton = new JButton();
		backButton.setBounds(5,5,30,30);
		backButton.setText("<=");
		backButton.setBackground(Color.white);
		backButton.setBorder(border);
		backButton.setFocusable(false);
		backButton.addActionListener(e -> backFunction());
		
		tableButton = new JButton();
		tableButton.setBounds(20,525,200,100);
		tableButton.setText("Simulation Data");
		tableButton.setBackground(Color.white);
		tableButton.setBorder(border);
		tableButton.setFocusable(false);
		tableButton.addActionListener(e -> tableFunction());
		
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
		

	    menu.add(backButton);
	    menu.add(tableButton);
	    menu.add(setupButton);
		menu.add(simulationButton);
		menu.add(mainLabel);
		menu.add(mainLabel2);
		
	}
	
	public void open() {	    
		menu.setVisible(true);
	}
	
	public void close() {
		menu.setVisible(false);
	}
		
	private void tableFunction() {
		menuManager.toMenu(3);
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

