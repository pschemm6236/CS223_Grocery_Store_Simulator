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

    public Customer dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
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
            throw new NoSuchElementException("Queue is empty");
        }
        return front.getCustomer();
    }

    public Customer rearPeek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.getCustomer();
    }

} // end class Queue

