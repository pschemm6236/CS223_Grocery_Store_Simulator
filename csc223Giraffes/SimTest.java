package csc223Giraffes;

public class SimTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public static void runSimulation(CheckoutQueue queueA, CheckoutQueue queueB, CheckoutQueue queueC,
			LinkedList<Customer> checkoutLL) {
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

	public static void populateLL(int numCustomers, int minArrivalTime, int maxArrivalTime, int minServiceTime,
			int maxServiceTime) {

		// create an instance of the CustomerCreator class
		CustomerCreator cc = new CustomerCreator(numCustomers, minArrivalTime, maxArrivalTime, minServiceTime,
				maxServiceTime, 0, 0);

		cc.populateCustomers(); // populate the ArrayList of Customers method
		
		QueueLL a = new QueueLL();
		QueueLL b = new QueueLL(); 
		QueueLL c = new QueueLL();

		CheckoutLL ll = new CheckoutLL();
		/**
		 * Node a = ll.getA();
		Node b = ll.getB();
		Node c = ll.getC();

		 */
		
		
		
		int time = 0;
		while (true) {
			// Check if all customers have been served
			if (ll.size() == 0 && cc.getCustomers().size() == 0) {
				break;
			}

			// Check if any customers have arrived
			if (cc.getCustomers().size() > 0 && cc.getCustomers().get(0).getArrivalTime() == time) {
				ll.add(cc.getCustomers().get(0));
				cc.getCustomers().remove(0);
			}

			// Serve customers at checkout A
			if (a.frontPeek() != null) {
				cc.getNextCustomer().setServiceTime(cc.getNextCustomer().getServiceTime() - 1);
				if(cc.getNextCustomer().getServiceTime() == 0) {
					cc.getNextCustomer().setEndTime(time);
					cc.setCustomers(null);
				}
				/**
				 * a.getCustomer().setServiceTime(a.getCustomer().getServiceTime() - 1);
				if (a.getCustomer().getServiceTime() == 0) {
					a.getCustomer().setEndTime(time);
					a.setCustomer(null);
				}
				 */
				
			}

			// Serve customers at checkout B
			if (b.frontPeek() != null) {
				cc.getNextCustomer().setServiceTime(cc.getNextCustomer().getServiceTime() - 1);
				if(cc.getNextCustomer().getServiceTime() == 0) {
					cc.getNextCustomer().setEndTime(time);
					cc.setCustomers(null);
				}
				/**
				 * b.getCustomer().setServiceTime(b.getCustomer().getServiceTime() - 1);
				if (b.getCustomer().getServiceTime() == 0) {
					b.getCustomer().setEndTime(time);
					b.setCustomer(null);
				}
				 */
				
			}

			// Serve customers at checkout C
			if (c.frontPeek() != null) {
				cc.getNextCustomer().setServiceTime(cc.getNextCustomer().getServiceTime() - 1);
				if(cc.getNextCustomer().getServiceTime() == 0) {
					cc.getNextCustomer().setEndTime(time);
					cc.setCustomers(null);
				}
				/**c.getCustomer().setServiceTime(c.getCustomer().getServiceTime() - 1);
				if (c.getCustomer().getServiceTime() == 0) {
					c.getCustomer().setEndTime(time);
					c.setCustomer(null);
				}
				**/
			}

			// Assign customers to checkout A if it's free
			/**if (a.getCustomer() == null && ll.size() > 0) {
				a.setCustomer(ll.remove());
				a.getCustomer().setStartTime(time);
			}
			**/
			
			// Assign customers to checkout A if it's free
			if(cc.getNextCustomer() == null && ll.size() > 0) {
				Customer current = cc.getNextCustomer();
				
				//since assigning to checkout a use index 0
				//cc.setCustomers(0, ll.remove());
				
				//ll.setCustomer(0, current);
				a.enqueue(current);
				current.setStartTime(time);
				
			}

			// Assign customers to checkout B if it's free
			/**if (b.getCustomer() == null && ll.size() > 0) {
				b.setCustomer(ll.remove());
				b.getCustomer().setStartTime(time);
			}
			**/
			
			// Assign customers to checkout B if it's free
			if(cc.getNextCustomer() == null && ll.size() > 0) {
				Customer current = cc.getNextCustomer();
				
				//since assigning to checkout a use index 0
				//cc.setCustomers(0, ll.remove());
				
				//ll.setCustomer(1, current);
				b.enqueue(current);
				current.setStartTime(time);
				
			}

			// Assign customers to checkout C if it's free
			/**
			if (c.getCustomer() == null && ll.size() > 0) {
				c.setCustomer(ll.remove());
				c.getCustomer().setStartTime(time);
			}
			**/
			// Assign customers to checkout C if it's free
			if(cc.getNextCustomer() == null && ll.size() > 0) {
				Customer current = cc.getNextCustomer();
				
				//since assigning to checkout a use index 0
				//cc.setCustomers(0, ll.remove());
				
				//ll.setCustomer(1, current);
				c.enqueue(current);
				current.setStartTime(time);
				
			}
			

			// Print status of the checkout counters at each time interval
			System.out.println("Time:" + time);
			System.out.println("\" ");
			// Check status of Checkout A
			System.out.print("\tCheckout A: ");
			
			/**if (a.getCustomer() != null) {
				System.out.println("Customer " + a.getCustomer().getId() + " (service time left: "
						+ a.getCustomer().getServiceTime() + ")");
			} **/
			if (cc.getNextCustomer() != null) {
				System.out.println("Customer " + cc.getNextCustomer().getCustID() + " (service time left: "
						+ cc.getNextCustomer().getServiceTime() + ")");
			}
			else {
				System.out.println("free");
			}

			// Check status of Checkout B
			System.out.print("\tCheckout B: ");
			if (b.getCustomer() != null) {
				System.out.println("Customer " + b.getCustomer().getId() + " (service time left: "
						+ b.getCustomer().getServiceTime() + ")");
			} else {
				System.out.println("free");
			}

			// Check status of Checkout C
			System.out.print("\tCheckout C: ");
			if (c.getCustomer() != null) {
				System.out.println("Customer " + c.getCustomer().getId() + " (service time left: "
						+ c.getCustomer().getServiceTime() + ")");
			} else {
				System.out.println("free");
			}

			time++;
		}

	}
}
