package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import model.vehicle.Car;
import model.vehicle.Vehicle;
import model.vehicle.VehicleBuilder;

import org.junit.Test;

public class TestVehicle {
	
	@Test
	public void testCreateNewCar() {
		/* Test basic information about car
		 * Capacity of tank, fuel and combustion
		 */
		Car car = new Car(0, 0, 0);
		
		if ((car.getCapacityOfTank() <= 0) ) {			
			fail("Missing capacity of tank");
		}
		if ((car.getFuel() < 0) ) {			
			fail("You don't have fuel in car");
		}
		if ((car.getCombustion() <= 0) ) {			
			fail("Don't forget about combustion");
		}
	}
	
	@Test
	public void checkAddVehicleToListWhenCreate() {
		
		VehicleBuilder vehiclebuilder = new VehicleBuilder();
		Car car = new Car(0, 0, 0, 0);
		vehiclebuilder.createNewCar("314",car);
		
		/* Check add vehicle in list */
		assertEquals(1, vehiclebuilder.getAllVehicle().size());
	}
	
	public void showAllInformationAboutVehivle() {
		VehicleBuilder vehicle = new VehicleBuilder();
		Car car = new Car(50, 30, 5, 1200);
		
		for (Vehicle vehicleTest : vehicle.getAllVehicle()) {
			System.out.println(((Car)vehicleTest).getFuel());
		}
	}

	@Test
	public void testCreateNewMotorcycle() {
		fail("Not yet implemented");
	}

}
