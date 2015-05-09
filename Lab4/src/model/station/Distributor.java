package model.station;

import model.IFuel;

public class Distributor implements IFuel {

	private Integer fuelAmount;
	private boolean isReady;
	private long performance;

	@Override
	public void setFuel(Integer newFuelValue) {
		this.fuelAmount = newFuelValue;
	}

	@Override
	public Integer getFuel() {
		return this.fuelAmount;
	}

	@Override
	public String getTypeOfFuel() {
		return null;
	}

	public long getPerformance() {
		return performance;
	}

	public void setPerformance(long performance) {
		this.performance = performance;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public Integer getFuelAmount() {
		return fuelAmount;
	}

	public void setFuelAmount(Integer fuelAmount) {
		this.fuelAmount = fuelAmount;
	}

}