This program is simple to run. There are 5 integer values that the user must enter, a double value for the desired percent slower for the self checkout,
and then the program simulation automatically runs. A user must enter the time values, in order, for the minimum 
arrival, maximum arrival, minimum service, maximum service, number of customers, and percent slower for the program to simulate. 
After the user enters the desired input number they want to run the program with time values and checkout line statuses
will print along the screen, simulating customers entering the store, getting in line, getting serviced, and then
leaving. The structure of the program has changed from a LinkedList data structure to a Queue data structure for each checkout line. 
Additionally, along with the three full service queues, another queue representing two self-checkout lines has been added. 
The percent slower feature is designed to simulate the self-checkout lines with how much less efficient customers are at checking out 
compared to trained cashiers. After the simulation is done serving all the customers, the average wait time, total time checkouts were not 
being used, number of satisfied and dissatisfied customers, and a table summarizing the whole simulation are all 
displayed. 

Lastly, at the top of the console output for the beginning of the program, a batch of recommended settings 
are displayed to the user. These are merely recommendations and the settings were used to best simulate customers
entering and leaving the queues.   