// Fig. 21.11: Queue.java
// Queue uses class List.
package csc223Giraffes;

import java.util.NoSuchElementException;



public class Queue{
	private List<Customer> queueList;

	// constructor
	public Queue() {
		queueList = new List<Customer>();
	}

	public Queue(String name) {
		queueList = new List<Customer>(name);
	}

	// add object to queue1
	public void enqueue(Customer cust) {queueList.insertAtBack(cust);}

	// remove object from queue
	public Customer dequeue() throws NoSuchElementException {
		return queueList.removeFromFront(); 
	} 

	// determine if queue is empty
	public boolean isEmpty() {return queueList.isEmpty();}

	// output queue contents

	public Customer updateQueue(int time) {
		Customer cust  = queueList.updateQueue(time); 
		return cust;
	}

	public int size() {int size = queueList.size(); return size;}

	public void setLineName(String string) {
		queueList.setLineName(string);
	}

	public String getLineName() {
		return queueList.getLineName();
	}

	public int getTimeNotUsed() {
		int time = queueList.getTimeNotUsed();
		return time;
	}
} 	



/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
