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

/**
 * @author liamj
 *
 */
public class QueueLL {
	
	private class Node {
		
        private Customer customer;
        private Node next;

        Node(Customer customer, Node n) {
            this.customer = customer;
            this.next = n;
        }
        
        Node(Customer customer){
        	this(customer,null);
        }
    }
	
	private Node first;
    private Node last;
    private int timeNotUsed;

    public QueueLL() {
        first = null;
        last = null;
        timeNotUsed = 0;
    }

    public int getTimeNotUsed() {
		return timeNotUsed;
	}

	public void setTimeNotUsed(int timeNotUsed) {
		this.timeNotUsed = timeNotUsed;
	}

	public boolean isEmpty() {
        return first == null;
    }

    public int size() {
    	int count = 0;
        Node p = first;     
        while (p != null)
        {
            // There is an element at p
            count ++;
            p = p.next;
        }
        return count;
    }
    
    public void add(Customer customer)
    {
      if (isEmpty()) 
      {
          first = new Node(customer);
          last = first;
      }
      else
      {
          // Add to end of existing list
          last.next = new Node(customer);
          last = last.next;
      }      
    }
    
    public void add(int index, Customer customer)
    {
         if (index < 0  || index > size()) 
         {
             String message = String.valueOf(index);
             throw new IndexOutOfBoundsException(message);
         }
         
         // Index is at least 0
         if (index == 0)
         {
             // New element goes at beginning
             first = new Node(customer, first);
             if (last == null)
                 last = first;
             return;
         }
         
         // Set a reference pred to point to the node that
         // will be the predecessor of the new node
         Node pred = first;        
         for (int k = 1; k <= index - 1; k++)        
         {
            pred = pred.next;           
         }
         
         // Splice in a node containing the new element
         pred.next = new Node(customer, pred.next);  
         
         // Is there a new last element ?
         if (pred.next.next == null)
             last = pred.next;         
    }
    
    public String toString()
    {
      StringBuilder strBuilder = new StringBuilder();
      
      // Use p to walk down the linked list
      Node p = first;
      while (p != null)
      {
         strBuilder.append(p.customer + "\n"); 
         p = p.next;
      }      
      return strBuilder.toString(); 
    }
    
    public Customer remove(int index)
    {
       if (index < 0 || index >= size())
       {  
           String message = String.valueOf(index);
           throw new IndexOutOfBoundsException(message);
       }
       
       Customer element;  // The element to return     
       if (index == 0)
       {
          // Removal of first item in the list
          element = first.customer;    
          first = first.next;
          if (first == null)
              last = null;             
       }
       else
       {
          // To remove an element other than the first,
          // find the predecessor of the element to
          // be removed.
          Node pred = first;
          
          // Move pred forward index - 1 times
          for (int k = 1; k <= index -1; k++)
              pred = pred.next;
          
          // Store the value to return
          element = pred.next.customer;
          
          // Route link around the node to be removed
          pred.next = pred.next.next;  
          
          // Check if pred is now last
          if (pred.next == null)
              last = pred;              
       }
       return element;        
    }  
    
    public Customer updateQueue(int time) {
    	if(isEmpty()) { //if the queue is empty
    		System.out.println("free");
    		timeNotUsed++;
    	}
    	else if(first.customer.getStartTime()==-1) { //if the next customer in the queue has not started
    		first.customer.setStartTime(time);
    		System.out.println("Customer "+first.customer.getCustId()+" starts service");
    	}
    	else if(first.customer.getStartTime()+first.customer.getServiceTime()==time) { //if the customer is in the queue and their time is done
    		first.customer.setEndTime(time);
    		System.out.print("Customer "+first.customer.getCustId()+" leaves");
    		Customer customerRemoved = remove(0);
    		if(isEmpty()) { //if the queue is not empty
        		timeNotUsed++;
        	}
    		else {
    			first.customer.setStartTime(time);
        		System.out.print(", Customer "+first.customer.getCustId()+" starts service");
    		}
    		System.out.println();
    		return customerRemoved;
    	}
    	else {
    		System.out.println("Customer "+first.customer.getCustId());
    	}
    	return null;
    }
} // end class Queue

