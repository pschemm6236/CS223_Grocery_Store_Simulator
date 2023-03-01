package csc223Giraffes;

/*
 * 
 * The CheckoutLL class represents the collection of all checkout lanes, 
 * and it determines which lane a customer should enter based on the size of the queues in each lane. 
 * Once a lane has been chosen, the customer is added to the queue associated with that lane.
 *
 */

public class CheckoutLL {
	
	private class Node {
		
        private Customer customer;
        private Node next;

        public Node(Customer customer) {
            this.customer = customer;
            this.next = null;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
        
    } // end class Node

    private Node front;
    private Node rear;

    public CheckoutLL() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void insert(Customer customer) {
        Node newNode = new Node(customer);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public Customer remove() {
        if (isEmpty()) {
            return null;
        }
        Customer customer = front.getCustomer();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return customer;
    }
    
    // Fields
    private Queue[] checkoutLines;
    private int numCheckouts;

    // Constructor
    public CheckoutLL(int numCheckouts) {
        this.numCheckouts = numCheckouts;
        checkoutLines = new Queue[numCheckouts];
        for (int i = 0; i < numCheckouts; i++) {
            checkoutLines[i] = new Queue();
        }
    }

    // Methods
    public void add(Customer customer) {
    	
        // Find the checkout with the smallest queue
        int smallestQueueIndex = 0;
        int smallestQueueSize = checkoutLines[0].size();
        for (int i = 1; i < numCheckouts; i++) {
            if (checkoutLines[i].size() < smallestQueueSize) {
                smallestQueueIndex = i;
                smallestQueueSize = checkoutLines[i].size();
            }
        }

        // Calculate the customer's finish time
        int customerFinishTime;
        if (checkoutLines[smallestQueueIndex].isEmpty()) {
            // The queue is empty, so the customer's finish time is its arrival time plus service time
            customerFinishTime = customer.getArrivalTime() + customer.getServiceTime();
        } else {
            // The queue is not empty, so the customer's finish time is the finish time of the customer at the rear of the queue plus its own service time
            Customer rearCustomer = checkoutLines[smallestQueueIndex].rearPeek();
            customerFinishTime = rearCustomer.getFinishTime() + customer.getServiceTime();
        }

        // Set the customer's finish time and enqueue it into the correct queue
        customer.setFinishTime(customerFinishTime);
        checkoutLines[smallestQueueIndex].enqueue(customer);
    }

   
} // end class CheckoutLL