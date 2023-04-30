package csc223Giraffes;

/**
 * 
 * @author Liam J. 
 *
 */

//This is like a Queue, but it has one queue going into multiple queues, and these queues only hold one value
//This was necessary to be able to continue to use the UpdateQueue in the List class

public class SplitQueue{
	private Queue mainQueue; //this is the main queue where items wait for open spots in the queues
	private String mainQueueName; //This is the name of the queue
	private Queue[] queues;  //array of queues. this is where the main queue drops into once one of the queues is open, these hold one value in most cases
	
	public SplitQueue(int length) { //one parameter, length. Use this when the name of each queue and the main queue don't matter
		this.mainQueue = new Queue();
		this.queues = new Queue[length];
		for(int i=0;i<length;i++) {
			queues[i] = new Queue();
		}
	}
	
	public SplitQueue(String mainQueueName, int length) { //two parameters, the main queue name and length. Use this when only the name of the main queue matters
		this.mainQueue = new Queue(mainQueueName);
		this.queues = new Queue[length];
		for(int i=0;i<length;i++) {
			queues[i] = new Queue();
		}
	}
	
	public SplitQueue(String[] names) { //one parameter, the names for each queue. Use this when only the names of the individual queues matter
		this.mainQueue = new Queue();
		this.queues = new Queue[names.length];
		for(int i=0;i<names.length;i++) {
			queues[i] = new Queue(names[i]);
		}
	}
	
	public SplitQueue(String mainQueueName, String[] names) { //two parameters, the main queue name and the names for each queue. Use this when the names of all the queues matter
		this.mainQueue = new Queue(mainQueueName);
		this.queues = new Queue[names.length];
		for(int i=0;i<names.length;i++) {
			queues[i] = new Queue(names[i]);
		}
	}
	
	public void enqueue(Customer cust) {
		if(openQueue()==null) { //if there is not an open queue available
			mainQueue.enqueue(cust); //it is added to the main queue
		}
		else { //if there is an open queue available
			cust.setUsedLine(openQueue().getLineName()); //it sets the line used field of the customer to this line name
			openQueue().enqueue(cust); //and adds it to the queue
		}
	}
	
	public Customer dequeue(int index){
		return queues[index].dequeue(); 
	} 
	
	public Customer[] updateQueues(int time) {		
		Customer[] customers = new Customer[queues.length]; //this will represent the customers that are returned to the simulator for each queue during an update
		for(int i=0;i<queues.length;i++) { //adds to Queues that are empty from the mainQueue
			if(queues[i].isEmpty() && !mainQueue.isEmpty()) { //if the queue is empty and the mainQueue has items
				mainQueue.peek().setUsedLine(queues[i].getLineName()); //sets the line used of the customer to the line name
				queues[i].enqueue(mainQueue.dequeue()); //adds the customer to the specific queue from the main queue
			}
		}
		System.out.println("\n[SELF SERVICE]");
		
		
		for(int i=0;i<queues.length;i++) { //updates each queue
			System.out.print("Checkout "+queues[i].getLineName()+": "); //prints which queue is being updated
			
			
			
			if(queues[i].peek()!=null) { //if the queue is not empty
				if(queues[i].peek().getStartTime()+queues[i].peek().getServiceTime()==time && !mainQueue.isEmpty()){ //then if the customer is done being served and the mainQueue has items
					mainQueue.peek().setUsedLine(queues[i].getLineName());  //sets the line used of the customer to the line name
					queues[i].enqueue(mainQueue.dequeue()); //adds the customer to the specific queue from the main queue, so that the updateQueue method can work properly, with two items in the queue
				}
			}
			customers[i] = queues[i].updateQueue(time); //updates the queue
			
		}
		return customers;
	}
	
	public int size() { //returns the size of the main queue and all the sub queues combined
		int subCount = 0;
		for(int i=0;i<queues.length;i++) {
			subCount+=queues[i].size();
		}
		return mainQueue.size()+subCount;
	}
	
	public String getMainQueueName() {
		return mainQueueName;
	}

	public void setMainQueueName(String mainQueueName) {
		this.mainQueueName = mainQueueName;
	}

	public String getLineName(int index) {
		return queues[index].getLineName();
	}
	
	public void setLineName(int index, String string) {
		queues[index].setLineName(string);
	}

	public int getTimeNotUsed(int index) { //queue specific
		int time = queues[index].getTimeNotUsed();
		return time;
	}
	
	public int getTotalTimeNotUsed() { //all queues combined
		int totalTime = 0;
		for(int i=0;i<queues.length;i++) {
			totalTime += getTimeNotUsed(i);
		}
		return totalTime;
	}
	
	public Queue openQueue() { //this returns the first open queue in the list of queues. If it doesn't, it returns null
		for(int i=0;i<queues.length;i++) {
			if(queues[i].isEmpty()) {
				return queues[i];
			}
		}
		return null;
	}

	public Queue[] getQueues() {
		return queues;
	}

	public void setQueues(Queue[] queues) {
		this.queues = queues;
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
