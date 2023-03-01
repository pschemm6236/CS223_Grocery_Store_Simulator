package csc223Giraffes;

public class Customer { //begin Customer class 

	//most likely the fields we will need for the customer object
	private static int custID; 
	private static int nextNum = 1;
	private int arrivalTime; 
	private int serviceTime; 
	private int startTime; 
	private int endTime; 
	private String queue;
	
	//no argument constructor 
	public Customer() { 
		custID = nextNum; 
		nextNum ++;
	}
	
	//full constructor 
	public Customer( int arrivalTime, int serviceTime, 
			int startTime, int endTime, String queue) { //begin Customer Constructor 
		this.arrivalTime = arrivalTime; 
		this.serviceTime = serviceTime; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.queue = queue; 
	} //end Customer Constructor 
	
	
	public String toString() { //begin toString
		return "Customer ID of: " + custID + ". Arrival time: " + arrivalTime + ". "
				+ " Service time: " + serviceTime + ". Start time: " + startTime + ". End time: "
				+ ". Serviced in queue: " + queue;
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

	public static int getCustID() {
		return custID;
	}

	public static void setCustID(int custID) {
		Customer.custID = custID;
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

	public String getQueue() {
		return queue;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}
	
	
	
} //end Customer class

