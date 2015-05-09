package service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.SwingWorker;

import model.vehicle.Vehicle;
import model.vehicle.VehicleBuilder;
import controler.MapController;

public class Orders extends SwingWorker<String, Object> {

	private CopyOnWriteArrayList<Order> allOrderInExecution = new CopyOnWriteArrayList<Orders.Order>();
	private AtomicReference<Order> order = new AtomicReference<Orders.Order>();
	private final AtomicBoolean orderInProgress = new AtomicBoolean(false);
	private MapController mapController;
	private ArrayList<Vehicle> cars;
	private Point positionOnMap;

	public Orders(Point mapPosition, MapController mapController) {
		this.positionOnMap = mapPosition;
		this.setMapController(mapController);
		VehicleBuilder vehicleBuilder = new VehicleBuilder();
		setCars(vehicleBuilder.createAFleetVehicle());
		cars.toString();
	}

	@Override
	protected String doInBackground() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (getCars().size() > 0)
				if (getOrderInProgress().get() == true) {
					waitingForNewOrder();
				}

			/*
			 * We check status information from all Order
			 */
			if (!allOrderInExecution.isEmpty()) {
				for (Order order : allOrderInExecution) {
					synchronized (order) {
						if (order.getStatus() == Status.FINISH) {
							order.setStatus(Status.START);
							System.out.print(Thread.currentThread().getName()
									+ " Auto back to parking ");
							order.getVehicle().toString();
							cars.add(getOrder().get().getVehicle());
							order.getCompany().getWaitFromCar().set(false);
							allOrderInExecution.remove(order);
						}
					}
				}
			}

		}
	}

	public synchronized void waitingForNewOrder() {
		CarsMove carsMove = new CarsMove(getOrder().get(), getMapController());
		Thread thread = new Thread(carsMove);
		thread.start();
		allOrderInExecution.add(getOrder().get());
		getOrderInProgress().set(false);
	}

	public synchronized boolean acceptingNewOrders(Company company) {
		if (getOrderInProgress().compareAndSet(true, true)) {
			Vehicle chVehicle = chooseBestCar();
			getOrder().set(new Order().addNewOrders(chVehicle, company));
			company.getInformationAboutSendCar().set(chVehicle.hashCode());
			return true;
		}
		return false;
	}

	public boolean checOrderFromCompany(Company company) {
		for (Order order : allOrderInExecution) {
			if (order.getCompany() == company) {
				return true;
			}
		}
		return false;
	}

	public Vehicle chooseBestCar() {
		Vehicle selectedCar = cars.get(0);
		cars.get(0).setSpeed(100);
		cars.remove(0);
		return selectedCar;
	}

	public AtomicBoolean getOrderInProgress() {
		return orderInProgress;
	}

	public Point getPositionOnMap() {
		return positionOnMap;
	}

	public void setPositionOnMap(Point positionOnMap) {
		this.positionOnMap = positionOnMap;
	}

	public MapController getMapController() {
		return mapController;
	}

	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}

	public ArrayList<Vehicle> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Vehicle> cars) {
		this.cars = cars;
	}

	public CopyOnWriteArrayList<Order> getAllOrderInExecution() {
		return allOrderInExecution;
	}

	public void setAllOrderInExecution(
			CopyOnWriteArrayList<Order> allOrderInExecution) {
		this.allOrderInExecution = allOrderInExecution;
	}

	public AtomicReference<Order> getOrder() {
		return order;
	}

	public void setOrder(AtomicReference<Order> order) {
		this.order = order;
	}

	public enum Status {
		START(true), FINISH(true);

		boolean checkStatus;

		private Status(boolean checkStatus) {
			this.checkStatus = checkStatus;
		}

		private boolean getStatus() {
			return this.checkStatus;
		}
	}

	public class Order {
		private Status status;
		private Vehicle vehicle;
		private Company company;

		/*
		 * One orders keep information about Vehicle and Route
		 */
		public Order addNewOrders(Vehicle vehicle, Company company) {
			this.setCompany(company);
			this.setVehicle(vehicle);
			return this;
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}

		public Vehicle getVehicle() {
			return vehicle;
		}

		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
		}

		public Status getStatus() {
			return status;
		}

		public void setStatus(Status status) {
			this.status = status;
		}

	}

}
