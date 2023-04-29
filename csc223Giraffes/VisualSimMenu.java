package csc223Giraffes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class VisualSimMenu {
	
	public static JLabel mainLabel;
	public static JButton backButton;
	public static JButton dataButton;
	public static JButton setupButton;
	public static JButton simulationButton;
	public static JButton tableButton;
	private static JFrame menu;
	
	
	// declare JProgressBar for when simulation is loading
	private JProgressBar bar;
    

	public VisualSimMenu() {
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
		String newPath = defaultPath.substring(0,defaultPath.length()-logoName.length())+"src\\testing\\"+defaultPath.substring(defaultPath.length()-logoName.length());
		ImageIcon icon = new ImageIcon(newPath);
		menu.setIconImage(icon.getImage());
		
		//Making the main label for this menu
		logoName = "JavaRUsMainMenu.png";
		defaultPath = new File(logoName).getAbsolutePath().replace("\\", "\\\\");
		newPath = defaultPath.substring(0,defaultPath.length()-logoName.length())+"src\\testing\\"+defaultPath.substring(defaultPath.length()-logoName.length());

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
		

		
		//Buttons
		Border border = BorderFactory.createLineBorder(Color.black,2);

		dataButton = new JButton();
		dataButton.setBounds(125,400,200,100);
		dataButton.setText("Database Functions");
		dataButton.setBackground(Color.white);
		dataButton.setBorder(border);
		dataButton.setFocusable(false);
		//dataButton.addActionListener(e -> dataFunction());
		
		setupButton = new JButton();
		setupButton.setBounds(375, 400, 200, 100);
		setupButton.setText("Main Menu");
		setupButton.setBackground(Color.white);
		setupButton.setBorder(border);
		setupButton.setFocusable(false);
		//setupButton.addActionListener(e -> setupFunction());
		
		simulationButton = new JButton();
		simulationButton.setBounds(375, 525, 200, 100);
		simulationButton.setText("Simulation Viewer");
		simulationButton.setBackground(Color.white);
		simulationButton.setBorder(border);
		simulationButton.setFocusable(false);
		//simulationButton.addActionListener(e -> simulationFunction());
		
		tableButton = new JButton();
		tableButton.setBounds(125, 525, 200, 100);
		tableButton.setText("Simulation Data");
		tableButton.setBackground(Color.white);
		tableButton.setBorder(border);
		tableButton.setFocusable(false);
		//tableButton.addActionListener(e -> tableFunction());
		
		// all bar settings
		bar = new JProgressBar();
		bar.setValue(0);
		bar.setBounds(20,20,420,50);
		bar.setStringPainted(true);
		bar.setFont(new Font("MV Boli",Font.BOLD,25));
		bar.setForeground(Color.red);
		bar.setBackground(Color.black);
		// method test call to fill up bar
		
		
		menu.add(dataButton);
		menu.add(setupButton);
		menu.add(simulationButton);
		menu.add(tableButton);
		menu.add(mainLabel);
		menu.add(bar);
        	
        menu.setVisible(true);
        fill();
	}
	
	public void open() {
		menu.setVisible(true);
	}
	
	public void close() {
		menu.setVisible(false);
	}
	
	public void fill() {
		int counter =0;
		
		while(counter<=100) {
			
			bar.setValue(counter);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter +=1;
		}
		bar.setString("Done! :)");
	}
}
