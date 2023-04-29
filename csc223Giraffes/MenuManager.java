package csc223Giraffes;

public class MenuManager {
	
	private MainMenu main;
	private DataTableMenu dataT;
	private SimSetUpMenu setup;
	
	private VisualSimMenu visualSim; // New VisualSimMenu variable
	private SimulationSettings simSettings; // New SimulationSettings variable
	
	public MenuManager() {
		simSettings = new SimulationSettings(); // Initialize SimulationSettings
		main = new MainMenu(this);
		dataT = new DataTableMenu(this);
		setup = new SimSetUpMenu(this, simSettings);
		setup = new SimSetUpMenu(this, simSettings); // Pass simSettings to SimSetUpMenu
		visualSim = new VisualSimMenu(this, simSettings); // Pass simSettings to VisualSimMenu
	}
	
	public void toMenu(int menuChoice) {
		if(menuChoice == 0) { //main
			main.open();
			// debugger call for when user enters settings in SimSetUpMenu (ensure settings are passed)
			debugAreSettingsPassed();
		}
		else if(menuChoice == 1){ //setup
			setup.open();
		}
		else if(menuChoice == 2){ //visual simulation (just view)
			visualSim.open();
		}
		else if(menuChoice == 3){ //data table
			dataT.open();
		}
		else if(menuChoice == 4){ //data base
			
		}
		else if(menuChoice == 5){ //visual simulation (just view)
			visualSim.open();
			visualSim.startSim();
		}
	}
	
	public void start() {
		main.open();
	}

	public MainMenu getMain() {
		return main;
	}

	public void setMain(MainMenu main) {
		this.main = main;
	}

	public DataTableMenu getDataT() {
		return dataT;
	}

	public void setDataT(DataTableMenu dataT) {
		this.dataT = dataT;
	}	
	
	public void debugAreSettingsPassed() {
		System.out.println("- - - - - - - - - - - - - - - - - - - -");
		System.out.println("The number of full-service lines entered was: " + simSettings.getFullService());
	    System.out.println("The number of self-service lines entered was: " + simSettings.getSelfService());
	    System.out.println("The minimum arrival time entered was: " + simSettings.getMinArrival());
	    System.out.println("The maximum arrival time entered was: " + simSettings.getMaxArrival());
	    System.out.println("The minimum service time entered was: " + simSettings.getMinService());
	    System.out.println("The maximum service time entered was: " + simSettings.getMaxService());
	    System.out.println("The number of customers entered was: " + simSettings.getNumCust());
	    System.out.println("The percent slower entered was: " + simSettings.getPercentSlower() + "%");
	}
	

}

