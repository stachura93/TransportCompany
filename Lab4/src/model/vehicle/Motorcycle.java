package model.vehicle;

public class Motorcycle extends Vehicle implements IEngineVehicle {

	private Integer capacityOfTank;
	private Integer fuel;

	@Override
	public void setFuel(Integer newFuelValue) {
		this.fuel = newFuelValue;
	}

	@Override
	public Integer getFuel() {
		return this.fuel;
	}

	@Override
	public String getTypeOfFuel() {
		return null;
	}

	@Override
	public Integer getCombustion() {
		return null;
	}

	@Override
	public Integer getTheCapacityOfTheTank() {
		return capacityOfTank;

	}

	public Integer getCapacityOfTank() {
		return capacityOfTank;
	}

	public void setCapacityOfTank(Integer capacityOfTank) {
		this.capacityOfTank = capacityOfTank;
	}

}