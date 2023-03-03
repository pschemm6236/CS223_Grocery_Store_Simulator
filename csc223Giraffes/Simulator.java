package csc223Giraffes;

public class Simulator {

	//fields 
	private int numCustomers; 
	private CustomerCreator cc;
	private Queue checkoutA;
	private Queue checkoutB; 
	private Queue checkoutC;
	
	//full constructor 
	public Simulator(int numCustomers, CustomerCreator cc, Queue checkoutA, 
			Queue checkoutB, Queue checkoutC) {
		this.numCustomers = numCustomers; 
		this.cc = cc; 
		this.checkoutA = checkoutA; 
		this.checkoutB = checkoutB; 
		this.checkoutC = checkoutC;
	}

	
	public double run() {
		
		
		return 0; 
	}
	
	public void printCustomerDetails() {
		
	}
	
	
	
	
	//getters and setters 
	public int getNumCustomers() {
		return numCustomers;
	}

	public void setNumCustomers(int numCustomers) {
		this.numCustomers = numCustomers;
	}

	public CustomerCreator getCc() {
		return cc;
	}

	public void setCc(CustomerCreator cc) {
		this.cc = cc;
	}

	public Queue getCheckoutA() {
		return checkoutA;
	}

	public void setCheckoutA(Queue checkoutA) {
		this.checkoutA = checkoutA;
	}

	public Queue getCheckoutB() {
		return checkoutB;
	}

	public void setCheckoutB(Queue checkoutB) {
		this.checkoutB = checkoutB;
	}

	public Queue getCheckoutC() {
		return checkoutC;
	}

	public void setCheckoutC(Queue checkoutC) {
		this.checkoutC = checkoutC;
	}
	
	
	
}


