This offers EITHER a GUI or a database user experience. Unfortunately, due to time constraints, we were unable to implement 
the database functionality with the GUI. If the user elects to use just the GUI, then they need to run the class "runGUIVersionDriver". 
After the user runs this version of the driver class, the Graphical User Interface window pops up, and the 
 There are 7 integer values that the user must enter, a double value for the desired percent slower for the self checkout,
and then the program simulation automatically runs. A user must first enter the desired number of full-checkout and self-checkout lines. Then,
the user must enter the time values, in order, for the minimum arrival, maximum arrival, minimum service, maximum service, number of customers, and percent slower for the program to simulate. 
After the user enters the desired input number they want to run the program with time values and checkout line statuses
will print along the screen, simulating customers entering the store, getting in line, getting serviced, and then
leaving. The structure of the program has changed from having a static amount of Queues for each checkout line, to being dynamically changed through
the user input. This way, for example, the user can test having a higher or lower amount of checkout lines for either full or self checkout. 
The percent slower feature is designed to simulate the self-checkout lines with how much less efficient customers are at checking out 
compared to trained cashiers. After the simulation is done serving all the customers, the average wait time, total accumulated idle time for all checkouts, 
number of satisfied and dissatisfied customers, addition/removal line suggestions, and a table summarizing the whole simulation are all are displayed. 

Lastly, at the top of the console output for the beginning of the program, a batch of recommended settings 
are displayed to the user. These are merely recommendations and the settings were used to best simulate customers
entering and leaving the queues.   