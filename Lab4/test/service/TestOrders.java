package service;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import model.Route;
import model.vehicle.Car;
import model.vehicle.Vehicle;

import org.junit.Test;

public class TestOrders {

	@Test
	public void newOders() {
		Vehicle vehicle = new Car(50, 40, 4);
		Route route = new Route("Route: 666", 100);

		ArrayList<Orders> orders = new ArrayList<Orders>();
		Orders order = new Orders();

		orders.add(order.addNewOrders(vehicle, route));

		for (Orders orders2 : orders) {
			System.out.println(((Car) orders2.getVehicle()).getFuel());
			System.out.println(orders2.getRoute().getDistance());
		}

		fail("Not implemented yet");
	}

}
