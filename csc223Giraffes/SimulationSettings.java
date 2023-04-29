package csc223Giraffes;

/**
 * @author troyfitzgerald
 *
 * This class will be used as the middle man object to carry the User inputted settings in SimSetUpMenu to VisualSimMenu so the simulation can start
 */
public class SimulationSettings {
    private int fullService;
    private int selfService;
    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;
    private int numCust;
    private double percentSlower;

    public SimulationSettings() {
        
    }

    public SimulationSettings(int fullService, int selfService, int minArrival, int maxArrival, int minService, int maxService, int numCust, double percentSlower) {
        this.fullService = fullService;
        this.selfService = selfService;
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        this.minService = minService;
        this.maxService = maxService;
        this.numCust = numCust;
        this.percentSlower = percentSlower;
    }

	public int getFullService() {
		return fullService;
	}

	public void setFullService(int fullService) {
		this.fullService = fullService;
	}

	public int getSelfService() {
		return selfService;
	}

	public void setSelfService(int selfService) {
		this.selfService = selfService;
	}

	public int getMinArrival() {
		return minArrival;
	}

	public void setMinArrival(int minArrival) {
		this.minArrival = minArrival;
	}

	public int getMaxArrival() {
		return maxArrival;
	}

	public void setMaxArrival(int maxArrival) {
		this.maxArrival = maxArrival;
	}

	public int getMinService() {
		return minService;
	}

	public void setMinService(int minService) {
		this.minService = minService;
	}

	public int getMaxService() {
		return maxService;
	}

	public void setMaxService(int maxService) {
		this.maxService = maxService;
	}

	public int getNumCust() {
		return numCust;
	}

	public void setNumCust(int numCust) {
		this.numCust = numCust;
	}

	public double getPercentSlower() {
		return percentSlower;
	}

	public void setPercentSlower(double percentSlower) {
		this.percentSlower = percentSlower;
	}

}

