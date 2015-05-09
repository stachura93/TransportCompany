package model.vehicle;

public class Car extends Vehicle implements IEngineVehicle {

	private Integer capacityOfTank;
	private Integer combustion;
	private Integer fuel;

	public Car() {
	}

	public Car(Integer copacityOfTank, Integer fuel, Integer combustion) {
		this.setCapacityOfTank(copacityOfTank);
		this.setFuel(fuel);
		this.setCombustion(combustion);
	}

	public Car(Integer copacityOfTank, Integer fuel, Integer combustion,
			Integer weight) {
		this.setCapacityOfTank(copacityOfTank);
		this.setFuel(fuel);
		this.setCombustion(combustion);
		setWeight(weight);
	}

	public void fuelCombusion(Car car) {
		car.setFuel(car.getFuel() - car.getCombustion());
	}

	public boolean doImustGoToFuelStation(Car car) {
		if (car.getFuel() < (2 * car.getCombustion()))
			return true;
		return false;
	}

	public Integer getCapacityOfTank() {
		return capacityOfTank;
	}

	public void setCapacityOfTank(Integer capacityOfTank) {
		this.capacityOfTank = capacityOfTank;
	}

	public Integer getCombustion() {
		return combustion;
	}

	public void setCombustion(Integer combustion) {
		this.combustion = combustion;
	}

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
		return new String("");
	}

	@Override
	public Integer getTheCapacityOfTheTank() {
		return this.getCapacityOfTank();
	}

	@Override
	public String toString() {
		System.out.println("Name:" + this.modelName + " Fuel:" + ((Car)this).getFuel() + " Combustion:" + 
				((Car)this).getCombustion() + " Capacity of tank:" + ((Car)this).getCapacityOfTank());
		return super.toString();
	}

}