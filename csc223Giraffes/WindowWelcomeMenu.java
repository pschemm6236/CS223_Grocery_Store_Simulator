package csc223Giraffes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.text.DecimalFormat;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.*;

import java.net.*;

public class WindowWelcomeMenu implements ActionListener {

	JFrame frame = new JFrame();
	JLabel labelWelcome = new JLabel("Welcome to JavaRUs!");
	JButton btntSimSetUp = new JButton("Simulation Setup");

	WindowWelcomeMenu() {

		labelWelcome.setBounds(0, 0, 5000, 50);
		labelWelcome.setFont(new Font(null, Font.PLAIN, 25));
		frame.add(labelWelcome);

		btntSimSetUp.setBounds(100, 160, 200, 40);
		btntSimSetUp.setFocusable(false);
		btntSimSetUp.addActionListener(this);

		frame.add(btntSimSetUp);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btntSimSetUp) {
			frame.dispose();
			WindowSimulatorDriverWithGUI simSettingsWindow = new WindowSimulatorDriverWithGUI();
		}
	}
}
