package csc223Giraffes;

public class Customer { //begin Customer class 

	//most likely the fields we will need for the customer object
	private int custID; 
	private static int nextNum = 1;
	private int arrivalTime; 
	private int serviceTime; 
	private int startTime; 
	private int endTime; 
	
	
	//no argument constructor 
	public Customer() { 
		custID = nextNum; 
		nextNum ++;
	}
	
	//full constructor 
	public Customer( int arrivalTime, int serviceTime, 
			int startTime, int endTime) { //begin Customer Constructor 
		this.arrivalTime = arrivalTime; 
		this.serviceTime = serviceTime; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.custID = nextNum;
		nextNum++;
	} //end Customer Constructor 
	
	
	public String toString() { //begin toString
		return "Customer ID of: " + custID + ". Arrival time: " + arrivalTime + ". "
				+ " Service time: " + serviceTime + ". Start time: " + startTime + ". End time: ";
	} //end toString 
	
	//method to calculate how long a customer was waiting in line 
	public int waitingTime() {
		return startTime - arrivalTime; 
	}
	
	//method to calculate a customer's turn-around time 
	//or the total time they took waiting and being serviced 
	public int tunraroundTime() {
		return endTime - arrivalTime; 
	}

	//getters and setters for fields 

	public int getCustId() {
		return custID;
	}

	public void setCustId(int custId) {
		this.custID = custId;
	}

	public static int getNextNum() {
		return nextNum;
	}

	public static void setNextNum(int nextNum) {
		Customer.nextNum = nextNum;
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

