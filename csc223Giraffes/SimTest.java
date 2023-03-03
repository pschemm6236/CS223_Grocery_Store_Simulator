package csc223Giraffes;

public class SimTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	public static void runSimulation(CheckoutQueue queueA, CheckoutQueue queueB, CheckoutQueue queueC, LinkedList<Customer> checkoutLL) {
	    int time = 0;
	    Customer currentA = null, currentB = null, currentC = null;
	    while (!checkoutLL.isEmpty() || currentA != null || currentB != null || currentC != null) {
	        System.out.println("Time:" + time);
	        if (currentA == null && !queueA.isEmpty()) {
	            currentA = queueA.dequeue();
	            System.out.println("Checkout A: Customer " + currentA.getId() + " starts service");
	        }
	        if (currentB == null && !queueB.isEmpty()) {
	            currentB = queueB.dequeue();
	            System.out.println("Checkout B: Customer " + currentB.getId() + " starts service");
	        }
	        if (currentC == null && !queueC.isEmpty()) {
	            currentC = queueC.dequeue();
	            System.out.println("Checkout C: Customer " + currentC.getId() + " starts service");
	        }
	        if (currentA != null) {
	            currentA.decServiceTime();
	            if (currentA.getServiceTime() == 0) {
	                currentA = null;
	                System.out.println("Checkout A: Customer " + currentA.getId() + " leaves");
	            } else {
	                System.out.println("Checkout A: Customer " + currentA.getId() + " (cont)");
	            }
	        }
	        if (currentB != null) {
	            currentB.decServiceTime();
	            if (currentB.getServiceTime() == 0) {
	                currentB = null;
	                System.out.println("Checkout B: Customer " + currentB.getId() + " leaves");
	            } else {
	                System.out.println("Checkout B: Customer " + currentB.getId() + " (cont)");
	            }
	        }
	        if (currentC != null) {
	            currentC.decServiceTime();
	            if (currentC.getServiceTime() == 0) {
	                currentC = null;
	                System.out.println("Checkout C: Customer " + currentC.getId() + " leaves");
	            } else {
	                System.out.println("Checkout C: Customer " + currentC.getId() + " (cont)");
	            }
	        }
	        if (!checkoutLL.isEmpty() && (currentA == null || currentB == null || currentC == null)) {
	            Customer nextCustomer = checkoutLL.removeFirst();
	            if (currentA == null) {
	                currentA = nextCustomer;
	                System.out.println("Checkout A: Customer " + currentA.getId() + " starts service");
	            } else if (currentB == null) {
	                currentB = nextCustomer;
	                System.out.println("Checkout B: Customer " + currentB.getId() + " starts service");
	            } else if (currentC == null) {
	                currentC = nextCustomer;
	                System.out.println("Checkout C: Customer " + currentC.getId() + " starts service");
	            } else {
	                switch (Math.floorMod(nextCustomer.getId(), 3)) {
	                    case 0:
	                        queueA.enqueue(nextCustomer);
	                        break;
	                    case 1:
	                        queueB.enqueue(nextCustomer);
	                        break;
	                    case 2:
	                        queueC.enqueue(nextCustomer);
	                        break;
	                }
	                System.out.println("Customer " + nextCustomer.getId() + " joins a queue");
	            }
	        }
	        time++;
	    }
	    System.out.println("Simulation complete.");
	}
}


	
