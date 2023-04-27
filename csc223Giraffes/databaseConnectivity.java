package csc223Giraffes;

import java.sql.*;

public class databaseConnectivity { //begin databaseConnectivity
	
	public static void main(String args[]) {
	

	System.out.println("---*** ----MYSQLJDBC Connection Testing----***----");

	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	catch (ClassNotFoundException e){
		System.out.println("Where is your mySQL JDBC driver??");
		e.printStackTrace();
		return; 
	}
	
	System.out.println("MY SQL JDBC Driver has been succesffuly registered");
	Connection connection = null;
	
	
	try {
		connection = DriverManager
		.getConnection("jdbc:mysql://localhost:3306/csc223Giraffes","root","");
		
	} catch (SQLException e) {
		System.out.println("Connection failed! Please check and try again");
		e.printStackTrace();
		return; 
	}
	
	if(connection != null) {
		System.out.println("Success! You can take control of your databse");
		
	}
	else {
		System.out.println("Failed to make the required connection");
	}
}
}
