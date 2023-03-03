package csc223Giraffes;

public class CheckoutNonLL {
	
    private ArrayList<Queue> checkoutLines;
    private int numCheckouts;

    public CheckoutNonLL(int numCheckouts) {
        this.numCheckouts = numCheckouts;
        checkoutLines = new ArrayList<Queue>(numCheckouts);
        for (int i = 0; i < numCheckouts; i++) {
            Queue q = new Queue();
            checkoutLines.add(q);
        }
    }

    public void add(Customer customer, int currentTime) {
        // Find the checkout with the smallest queue
        int shortestQueueIndex = getShortestQueueIndex();
        
        // Calculate the customer's finish time
        int customerFinishTime;
        if (checkoutLines.get(shortestQueueIndex).isEmpty()) {
            // The queue is empty, so the customer's finish time is its arrival time plus service time
            customerFinishTime = currentTime + customer.getServiceTime();
        } else {
            // The queue is not empty, so the customer's finish time is the finish time of the customer 
            // at the rear of the queue plus its own service time
            Customer rearCustomer = checkoutLines.get(shortestQueueIndex).rearPeek();
            customerFinishTime = rearCustomer.getEndTime() + customer.getServiceTime();
        }

        // Set the customer's finish time and enqueue it into the correct queue
        customer.setEndTime(customerFinishTime);
        checkoutLines.get(shortestQueueIndex).enqueue(customer);
    }
    
    private int getShortestQueueIndex() {
        int shortestQueueIndex = 0;
        int shortestQueueSize = checkoutLines.get(0).size();
        for (int i = 1; i < numCheckouts; i++) {
            if (checkoutLines.get(i).size() < shortestQueueSize) {
                shortestQueueIndex = i;
                shortestQueueSize = checkoutLines.get(i).size();
            }
        }
        return shortestQueueIndex;
    }
    
    public int getNumCheckouts() {
        return numCheckouts;
    }
    
    public int getQueueSize(int queueIndex) {
        return checkoutLines.get(queueIndex).size();
    }
    
    public Customer getNextCustomer(int queueIndex) {
        return checkoutLines.get(queueIndex).dequeue();
    }
}

