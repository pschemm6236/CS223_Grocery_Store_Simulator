package csc223Giraffes;
/* 
 * 
 * The Queue class, is used to model the line of customers waiting to check out. 
 * It represents the sequence of customers waiting in line to be served at the 
 * checkout lanes. Each customer is a node in the Queue linked list. The Queue class 
 * stores information about the customers in line, such as their arrival time and the time they 
 * begin and end service.
 *
 */
 
public class Queue {
	
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
    private int size;
    public static Queue checkoutA = new Queue();
    public static Queue checkoutB = new Queue();
    public static Queue checkoutC = new Queue();

    public Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void enqueue(Customer customer) {
        Node newNode = new Node(customer);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        rear = newNode;
        size++;
    }

    
    //need to change the following methods for exceptions 
    //we should probably make a costume exception class 
    public Customer dequeue() {
        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }
        Customer customer = front.getCustomer();
        front = front.getNext();
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return customer;
    }

    public Customer frontPeek() {
        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }
        return front.getCustomer();
    }

    public Customer rearPeek() {
        if (isEmpty()) {
            throw new QueueException("Queue is empty");
        }
        return rear.getCustomer();
    }
    
    public boolean isFull(int maxCapacity) {
        return (size == maxCapacity);
    }
    
    public String checkStatus(Customer customer, int currentTime) {
    	if (!customer.isServed()) {
    		
    		customer.setServed(true);
            // Customer has not started service yet
            return "Customer " + customer.getCustId() + " starts service";            
        } else if (customer.getServiceTime() + customer.getStartTime() > currentTime) {
            // Customer is still being served
            return "Customer " + customer.getCustId() + " (cont)";
        } else {
            // Queue is empty and no customers are being served
            return "free";
        }
    }
    
    // New methods for isFree and startService
    public boolean isFree() {
        if (front == null) {
            return true;
        } else {
            return front.getCustomer().getServiceTime() == 0;
        }
    }

    public void startService(int currentTime) {
        if (front == null) {
            throw new QueueException("Queue is empty");
        } else {
            front.getCustomer().startService(currentTime);
        }
    }
    
    public String getName() {
        // Check if queue is one of the three checkout lanes, and return the corresponding name
        if (this == checkoutA) {
            return "Checkout A";
        } else if (this == checkoutB) {
            return "Checkout B";
        } else if (this == checkoutC) {
            return "Checkout C";
        } else {
            return "Unknown queue";
        }
    }

    public void endService() {
        // Set the service time for the customer at the front of the queue to 0
        if (!isEmpty()) {
            front.getCustomer().setServiceTime(0);
        }
    }
} // end class Queue

