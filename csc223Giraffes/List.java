// Fig. 21.3: List.java
// ListNode and List class declarations.
package csc223Giraffes;

import java.util.NoSuchElementException;

// class to represent one node in a list
class ListNode<E> {
	// package access members; List can access these directly
	Customer cust; // data for this node
	ListNode<Customer> nextNode; // reference to the next node in the list

	// constructor creates a ListNode that refers to object
	ListNode(Customer cust) {
		this(cust, null);
	}

	// constructor creates ListNode that refers to the specified
	// object and to the next ListNode
	ListNode(Customer cust, ListNode<Customer> node) {
		this.cust = cust;
		nextNode = node;
	}

	// return reference to data in node
	Customer getData() {
		return cust;
	}

	// return reference to next node in list
	ListNode<Customer> getNext() {
		return nextNode;
	}
}

// class List definition
public class List<E> {
	private ListNode<Customer> firstNode;
	private ListNode<Customer> lastNode;
	private String lineName; // string like "list" used in printing
	private int timeNotUsed;
	private VisualSimMenu visualSimMenu;

	// constructor creates empty List with "list" as the name
	public List() {
		this("list");
	}

	// constructor creates an empty List with a name
	public List(String lineName) {
		this.lineName = lineName;
		firstNode = lastNode = null;
		this.timeNotUsed = 0;
	}
	
	// ADDED ANOTHER CONSTRUCTOR SO WE CAN OUTPUT TO GUI
	public List(String lineName, VisualSimMenu visualSimMenu) {
	    this.lineName = lineName;
	    firstNode = lastNode = null;
	    this.timeNotUsed = 0;
	    this.visualSimMenu = visualSimMenu;
	}

	// insert item at front of List
	public void insertAtFront(Customer insertCust) {
		if (isEmpty()) { // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<Customer>(insertCust);
		} else { // firstNode refers to new node
			firstNode = new ListNode<Customer>(insertCust, firstNode);
		}
	}

	// insert item at end of List
	public void insertAtBack(Customer insertCust) {
		if (isEmpty()) { // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<Customer>(insertCust);
		} else { // lastNode's nextNode refers to new node
			lastNode = lastNode.nextNode = new ListNode<Customer>(insertCust);
		}
	}

	// remove first node from List
	public Customer removeFromFront() throws NoSuchElementException {
		if (isEmpty()) { // throw exception if List is empty
			throw new NoSuchElementException(lineName + " is empty");
		}

		Customer removedCust = firstNode.cust; // retrieve data being removed

		// update references firstNode and lastNode
		if (firstNode == lastNode) {
			firstNode = lastNode = null;
		} else {
			firstNode = firstNode.nextNode;
		}

		return removedCust; // return removed node data
	}

	// remove last node from List
	public Customer removeFromBack() throws NoSuchElementException {
		if (isEmpty()) { // throw exception if List is empty
			throw new NoSuchElementException(lineName + " is empty");
		}

		Customer removedCust = lastNode.cust; // retrieve data being removed

		// update references firstNode and lastNode
		if (firstNode == lastNode) {
			firstNode = lastNode = null;
		} else { // locate new last node
			ListNode<Customer> current = firstNode;

			// loop while current node does not refer to lastNode
			while (current.nextNode != lastNode) {
				current = current.nextNode;
			}

			lastNode = current; // current is new lastNode
			current.nextNode = null;
		}

		return removedCust; // return removed node data
	}

	// determine whether list is empty; returns true if so
	public boolean isEmpty() {
		return firstNode == null;
	}

	public int size() {
		int count = 0;
		ListNode<Customer> current = firstNode;
		while (current != null) {
			count++;
			current = current.nextNode;
		}
		return count;
	}

	public String getLineName() {
		return lineName;
	}
	
	public Customer getFirstNode() { //necessary for the peek method in the Queue class
		if(!isEmpty()) {
			return firstNode.getData();
		}
		return null;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public Customer updateQueue(int time) {
		if (isEmpty()) { // if the queue is empty
			System.out.println(" free");
			timeNotUsed++;
		} else if (firstNode.cust.getStartTime() == -1) { // if the next customer in the queue has not started
			firstNode.cust.setStartTime(time);
			System.out.println("Customer " + firstNode.cust.getCustId() + " starts service");
			visualSimMenu.updateQueueStatus("Customer " + firstNode.cust.getCustId() + " starts service");
		} else if (firstNode.cust.getStartTime() + firstNode.cust.getServiceTime() == time) { // if the customer is in the queue and their time is done
			firstNode.cust.setEndTime(time);
			System.out.print("Customer " + firstNode.cust.getCustId() + " leaves");
			visualSimMenu.updateQueueStatus("Customer " + firstNode.cust.getCustId() + " leaves");
			Customer customerRemoved = removeFromFront();
			if (isEmpty()) { // if the queue is not empty
				timeNotUsed++;
			} else {
				firstNode.cust.setStartTime(time);
				System.out.print(", Customer " + firstNode.cust.getCustId() + " starts service");
				visualSimMenu.updateQueueStatus(", Customer " + firstNode.cust.getCustId() + " starts service");
			}
			System.out.println();

			return customerRemoved;
		} else {
			System.out.println("Customer " + firstNode.cust.getCustId());
		}
		return null;
	}

	public int getTimeNotUsed() {
		return timeNotUsed;
	}

	/**
	 * public Customer updateQueue(int time) { if(isEmpty()) { //if the queue is
	 * empty System.out.println("free"); timeNotUsed++; } else
	 * if(first.customer.getStartTime()==-1) { //if the next customer in the queue
	 * has not started first.customer.setStartTime(time);
	 * first.customer.setUsedLine(lineName); System.out.println("Customer
	 * "+first.customer.getCustId()+" starts service"); } else
	 * if(first.customer.getStartTime()+first.customer.getServiceTime()==time) {
	 * //if the customer is in the queue and their time is done
	 * first.customer.setEndTime(time); System.out.print("Customer
	 * "+first.customer.getCustId()+" leaves"); Customer customerRemoved =
	 * remove(0); if(isEmpty()) { //if the queue is not empty timeNotUsed++; } else
	 * { first.customer.setStartTime(time); first.customer.setUsedLine(lineName);
	 * 
	 * System.out.print(", Customer "+first.customer.getCustId()+" starts service");
	 * } System.out.println();
	 * 
	 * return customerRemoved; } else { System.out.println("Customer
	 * "+first.customer.getCustId()); } return null; }
	 * 
	 * // output list contents /**public void print() { if (isEmpty()) {
	 * System.out.printf("Empty %s%n", name); return; }
	 * 
	 * System.out.printf("The %s is: ", name); ListNode<E> current = firstNode;
	 * 
	 * // while not at end of list, output current node's data while (current !=
	 * null) { System.out.printf("%s ", current.data); current = current.nextNode; }
	 * 
	 * System.out.println(); }
	 **/
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
