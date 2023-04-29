package csc223Giraffes;

public class MenuManager {
	
	private MainMenu main;
	private DataTableMenu dataT;
	
	public MenuManager() {
		main = new MainMenu(this);
		dataT = new DataTableMenu(this);
	}
	
	public void toMenu(int menuChoice) {
		if(menuChoice == 0) { //main
			main.open();
		}
		else if(menuChoice == 1){ //setup
			
		}
		else if(menuChoice == 2){ //simulation
			
		}
		else if(menuChoice == 3){ //data table
			dataT.open();
		}
		else if(menuChoice == 4){ //data base
			
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
}
