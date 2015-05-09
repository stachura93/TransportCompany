package model.vehicle;

import java.util.ArrayList;
import java.util.Random;

public class VehicleBuilder {

	private ArrayList<Vehicle> allVehicle = new ArrayList<Vehicle>();

	public Car createNewCar(String carName, Car car) {
		Vehicle newVehicle = car;
		newVehicle.modelName = carName;
		allVehicle.add(newVehicle);
		return (Car) newVehicle;
	}

	public void motorcycleNewCar(Motorcycle motorcycle) {
		Vehicle name = motorcycle;
		allVehicle.add(name);
	}

	@Override
	public String toString() {
		for (Vehicle vehicle : allVehicle) {
			System.out.println("Name:" + vehicle.modelName + " Fuel:"
					+ ((Car) vehicle).getFuel() + " Combustion:"
					+ ((Car) vehicle).getCombustion() + " Capacity of tank:"
					+ ((Car) vehicle).getCapacityOfTank());
		}
		return super.toString();
	}

	public ArrayList<Vehicle> getAllVehicle() {
		return allVehicle;
	}

	public void setAllVehicle(ArrayList<Vehicle> allVehicle) {
		this.allVehicle = allVehicle;
	}

	/*
	 * Fleet a new Vehicle only TEST
	 */
	public ArrayList<Vehicle> createAFleetVehicle() {
		Integer howManyVehicleInCompany = randomNumber(5, 7);
		VehicleBuilder vehicleBuilder = new VehicleBuilder();

		for (int i = 0; i < howManyVehicleInCompany; i++) {
			Integer copacityOfTank = randomNumber(40, 60);
			Integer fuel = randomNumber(10, 30);
			Integer combustion = randomNumber(4, 10);

			vehicleBuilder.createNewCar("number" + (i + 1), new Car(
					copacityOfTank, fuel, combustion));
		}
		return vehicleBuilder.getAllVehicle();
	}

	private Integer randomNumber(Integer beginRange, Integer endRange) {
		Random random = new Random();
		Integer nevValue = random.nextInt(endRange - beginRange) + beginRange;
		return nevValue;
	}

}