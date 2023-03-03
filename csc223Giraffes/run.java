package csc223Giraffes;

public class run {

	public double run() {
	    double totalWaitTime = 0;
	    int numCustomers = creator.getNumCustomers();

	    for (int time = 0; !checkoutA.isEmpty() || !checkoutB.isEmpty() || !checkoutC.isEmpty(); time++) {
	        // Check if there are any customers arriving at this time
	        while (!customerQueue.isEmpty() && customerQueue.peek().getArrivalTime() == time) {
	            Customer c = customerQueue.poll();
	            int serviceTime = c.getServiceTime();
	            int shortestSize = Math.min(Math.min(checkoutA.size(), checkoutB.size()), checkoutC.size());
	            if (shortestSize == checkoutA.size()) {
	                checkoutA.enqueue(c);
	                c.setFinishTime(time + serviceTime);
	            } else if (shortestSize == checkoutB.size()) {
	                checkoutB.enqueue(c);
	                if (checkoutB.size() == 1) {
	                    c.setFinishTime(time + serviceTime);
	                }
	            } else {
	                checkoutC.enqueue(c);
	                if (checkoutC.size() == 1) {
	                    c.setFinishTime(time + serviceTime);
	                }
	            }
	        }

	        // Check if any customers are finished and can be removed from the checkout lanes
	        if (!checkoutA.isEmpty() && checkoutA.peek().getFinishTime() == time) {
	            totalWaitTime += checkoutA.dequeue().getWaitTime();
	        }
	        if (!checkoutB.isEmpty() && checkoutB.peek().getFinishTime() == time) {
	            totalWaitTime += checkoutB.dequeue().getWaitTime();
	            if (!checkoutB.isEmpty()) {
	                checkoutB.peek().setFinishTime(time + checkoutB.peek().getServiceTime());
	            }
	        }
	        if (!checkoutC.isEmpty() && checkoutC.peek().getFinishTime() == time) {
	            totalWaitTime += checkoutC.dequeue().getWaitTime();
	            if (!checkoutC.isEmpty()) {
	                checkoutC.peek().setFinishTime(time + checkoutC.peek().getServiceTime());
	            }
	        }
	    }

	    return totalWaitTime / numCustomers;
	}

}
