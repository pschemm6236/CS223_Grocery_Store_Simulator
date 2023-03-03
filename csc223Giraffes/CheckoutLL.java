package csc223Giraffes;

import java.util.ArrayList;


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
    private ArrayList<Queue> checkoutLines;
    private int numCheckouts;

    // Constructor
    public CheckoutLL(int numCheckouts) {
        this.numCheckouts = numCheckouts;
        //checkoutLines = new Queue[numCheckouts];
        checkoutLines = new ArrayList<Queue>(numCheckouts);
        for (int i = 0; i < numCheckouts; i++) {
        	Queue q = new Queue();
            checkoutLines.add(q);
        }
    }

    // Methods
    public void add(Customer customer) {
    	
        // Find the checkout with the smallest queue
        int smallestQueueIndex = 0;
        int smallestQueueSize = checkoutLines.get(0).size();
        for (int i = 1; i < numCheckouts; i++) {
            if (checkoutLines.get(i).size() < smallestQueueSize) {
                smallestQueueIndex = i;
                smallestQueueSize = checkoutLines.get(i).size();
            }
        }

        // Calculate the customer's finish time
        int customerFinishTime;
        if (checkoutLines.get(0).isEmpty()) {
            // The queue is empty, so the customer's finish time is its arrival time plus service time
            customerFinishTime = customer.getArrivalTime() + customer.getServiceTime();
        } else {
            // The queue is not empty, so the customer's finish time is the finish time of the customer 
        	//at the rear of the queue plus its own service time
        	Queue index = checkoutLines.get(smallestQueueIndex);
            Customer rearCustomer = checkoutLines.get(smallestQueueIndex).rearPeek();
            customerFinishTime = rearCustomer.getEndTime() + customer.getServiceTime();
        }

        // Set the customer's finish time and enqueue it into the correct queue
        customer.setEndTime(customerFinishTime);
        //checkoutLines[smallestQueueIndex].enqueue(customer);
        checkoutLines.get(smallestQueueIndex).enqueue(customer);
    }
    
    public int size()
    {
       int count = 0;
       Node p = front;     
       while (p != null)
       {
           // There is an element at p
           count ++;
           p = p.next;
       }
       return count;
    }

    public void setCustomer(int laneIndex, Customer customer) {
        checkoutLines.get(laneIndex).enqueue(customer);
        
    }
   
} // end class CheckoutLL