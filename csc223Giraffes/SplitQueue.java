package csc223Giraffes;

public class SplitQueue{ //This is like a Queue, but it has multiple values 
	private Queue mainQueue;
	private Queue[] queues;
	
	public SplitQueue(int length) {
		this.mainQueue = new Queue();
		this.queues = new Queue[length];
		for(int i=0;i<length;i++) {
			queues[i] = new Queue();
		}
	}
	
	public SplitQueue(int length, String[] names) {
		this.mainQueue = new Queue();
		this.queues = new Queue[length];
		for(int i=0;i<length;i++) {
			queues[i] = new Queue(names[i]);
		}
	}
	
	public void enqueue(Customer cust) {
		if(openQueue()==null) {
			mainQueue.enqueue(cust);
		}
		else {
			cust.setUsedLine(openQueue().getLineName());
			openQueue().enqueue(cust);
		}
	}
	
	public Customer dequeue(int index){
		return queues[index].dequeue(); 
	} 
	
	public Customer[] updateQueues(int time) {
		Customer[] customers = new Customer[queues.length];
		for(int i=0;i<queues.length;i++) { //adds to Queues that are empty from the mainQueue
			if(queues[i].isEmpty() && !mainQueue.isEmpty()) {
				queues[i].enqueue(mainQueue.dequeue());
			}
		}
		for(int i=0;i<queues.length;i++) {
			System.out.print("\tCheckout "+queues[i].getLineName()+": ");
			if(queues[i].peek()!=null) {
				if(queues[i].peek().getStartTime()+queues[i].peek().getServiceTime()==time && !mainQueue.isEmpty()){
					queues[i].enqueue(mainQueue.dequeue());
				}
			}
			customers[i] = queues[i].updateQueue(time);
		}
		return customers;
	}
	
	public int size() {
		int subCount = 0;
		for(int i=0;i<queues.length;i++) {
			subCount+=queues[i].size();
		}
		return mainQueue.size()+subCount;
	}
	
	public void setLineName(int index, String string) {
		queues[index].setLineName(string);
	}

	public String getLineName(int index) {
		return queues[index].getLineName();
	}

	public int getTimeNotUsed(int index) {
		int time = queues[index].getTimeNotUsed();
		return time;
	}
	
	public int getTotalTimeNotUsed() {
		int totalTime = 0;
		for(int i=0;i<queues.length;i++) {
			totalTime += getTimeNotUsed(i);
		}
		return totalTime;
	}
	
	public Queue openQueue() {
		for(int i=0;i<queues.length;i++) {
			if(queues[i].isEmpty()) {
				return queues[i];
			}
		}
		return null;
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
