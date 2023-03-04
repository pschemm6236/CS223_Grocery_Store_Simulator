package csc223Giraffes;

public class Customer implements Comparable<Customer> { //begin Customer class 

	//most likely the fields we will need for the customer object
	private int custID; 
	private int arrivalTime; 
	private int serviceTime; 
	private int startTime; 
	private int endTime;
	private boolean served = false; 
	
	
	//no argument constructor 
	public Customer() { 
		
	}
	
	//full constructor 
	public Customer( int arrivalTime, int serviceTime, 
			int startTime, int endTime) { //begin Customer Constructor 
		this.arrivalTime = arrivalTime; 
		this.serviceTime = serviceTime; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		
		this.served = served;
		
	} //end Customer Constructor 
	
	
	

	public String toString() { //begin toString
		return "Customer ID of: " + custID + ". Arrival time: " + arrivalTime + ". "
				+ " Service time: " + serviceTime + ". Start time: " + startTime + ". End time: ";
	} //end toString 
	
	@Override
	public int compareTo(Customer c) {
		
		if(c.arrivalTime > this.arrivalTime)
			return -1; 
		else if(c.arrivalTime < this.arrivalTime)
			return 1; 
		else
			return 0;
	}
	
	public void assignId(int id) {
		this.custID = id; 
	}
	
	//method to calculate how long a customer was waiting in line 
	public int waitingTime() {
		return startTime - arrivalTime; 
	}
	
	//method to calculate a customer's turn-around time 
	//or the total time they took waiting and being serviced 
	public int tunraroundTime() {
		return endTime - arrivalTime; 
	}
	
	
	public void startService(int currentTime) { 
	    this.startTime = currentTime;
	    this.endTime = this.startTime + this.serviceTime;
	    this.served = true;
	}

	//getters and setters for fields 

	public boolean isServed() {
		return served;
	}

	public void setServed(boolean served) {
		this.served = served;
	}
	public int getCustId() {
		return custID;
	}

	public void setCustId(int custId) {
		this.custID = custId;
	}

	

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	
	
} //end Customer class

