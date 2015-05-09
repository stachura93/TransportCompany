package service;

import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import model.vehicle.Car;
import service.Orders.Status;
import controler.MapController;

public class CarsMove implements Runnable {
	private MapController mapController;

	private LinkedBlockingQueue<CopyOnWriteArrayList<Point>> roadQueue = new LinkedBlockingQueue<CopyOnWriteArrayList<Point>>();
	private ThreadLocal<Integer> image = new ThreadLocal<Integer>();
	private Orders.Order order;
	private Car vehicle;

	public CarsMove(Orders.Order order, MapController mapController) {
		this.setOrder(order);
		this.setMapController(mapController);
		this.setVehicle((Car) order.getVehicle());
	}

	private ThreadLocal<Point> actualPosition = new ThreadLocal<Point>() {
		protected Point initialValue() {
			return new Point(3, 16);
		};
	};

	private ThreadLocal<Point> fromPoint = new ThreadLocal<Point>() {
		protected Point initialValue() {
			return new Point(3, 16);
		};
	};
	private ThreadLocal<Point> toPoint = new ThreadLocal<Point>() {
		protected Point initialValue() {
			return order.getCompany().getPositionOnMap();
		};
	};

	@Override
	public void run() {
		synchronized (FuelStationThreads.getThisFuelStation()) {
			if (getVehicle().doImustGoToFuelStation(getVehicle()) == true) {
				FuelStationThreads.getCarsInStation().add(this);
				newDestinyThisCar(actualPosition.get(),
						FuelStationThreads.getPositionOnMap());
				System.out.println(Thread.currentThread().getName()
						+ " I'm go to fuel station");
				try {
					FuelStationThreads.getThisFuelStation().wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		getRoadQueue().add(findNewRoad());
		informationAboutCar();

		while (!getRoadQueue().isEmpty()) {

			moveCar(getRoadQueue().poll());
			if (getOrder().getCompany().packageToSend(this) == true) {
				newDestinyThisCar(getActualPosition().get(), getOrder()
						.getCompany().packageDesiny(getOrder().getCompany()));
				System.out.println(Thread.currentThread().getName()
						+ " I have new destiny " + "from "
						+ getActualPosition().get() + " to "
						+ getToPoint().get());
				getRoadQueue().add(findNewRoad());
			}
		}

		getVehicle().fuelCombusion(getVehicle());

		if (getRoadQueue().isEmpty()) {
			synchronized (this) {
				newDestinyThisCar(actualPosition.get(), new Point(3, 16));
				moveCar(findNewRoad());
				getOrder().setStatus(Status.FINISH);
			}
		}
	}

	public void informationAboutCar() {
		String threadName = Thread.currentThread().getName();
		System.out.print(threadName + " ");
		getVehicle().toString();
		System.out.println();
		System.out.println(threadName + " First I go to company "
				+ getOrder().getCompany().toString() + " from "
				+ getFromPoint().get() + " to "
				+ getOrder().getCompany().getPositionOnMap());
	}

	public synchronized boolean newDestinyThisCar(Point from, Point to) {
		getFromPoint().set(from);
		getToPoint().set(to);
		return true;
	}

	public synchronized CopyOnWriteArrayList<Point> findNewRoad() {
		return mapController.getBestWay(getFromPoint().get(), getToPoint()
				.get());
	}

	public void moveCar(CopyOnWriteArrayList<Point> bestWay) {
		while (!bestWay.isEmpty()) {
			synchronized (this) {

				getActualPosition().set(
						new Point(bestWay.get(0).x, bestWay.get(0).y));
				image.set(1);
				getMapController().setPostionOnMap(2, bestWay.get(0).x,
						bestWay.get(0).y);
				getMapController().repainGraphics();
				try {
					if (bestWay.size() > 1) {
						Thread.sleep(100);
					} else if (bestWay.size() <= 1) {
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getMapController().setPostionOnMap(image.get(),
						bestWay.get(0).x, bestWay.get(0).y);

				bestWay.remove(bestWay.get(0));
				getMapController().repainGraphics();
			}
		}
	}

	public void fuelWear() {

	}

	public MapController getMapController() {
		return mapController;
	}

	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}

	public Orders.Order getOrder() {
		return order;
	}

	public void setOrder(Orders.Order order) {
		this.order = order;
	}

	public Car getVehicle() {
		return vehicle;
	}

	public void setVehicle(Car vehicle) {
		this.vehicle = vehicle;
	}

	public LinkedBlockingQueue<CopyOnWriteArrayList<Point>> getRoadQueue() {
		return roadQueue;
	}

	public void setRoadQueue(
			LinkedBlockingQueue<CopyOnWriteArrayList<Point>> roadQueue) {
		this.roadQueue = roadQueue;
	}

	public ThreadLocal<Point> getActualPosition() {
		return actualPosition;
	}

	public void setActualPosition(ThreadLocal<Point> actualPosition) {
		this.actualPosition = actualPosition;
	}

	public ThreadLocal<Integer> getImage() {
		return image;
	}

	public void setImage(ThreadLocal<Integer> image) {
		this.image = image;
	}

	public ThreadLocal<Point> getFromPoint() {
		return fromPoint;
	}

	public void setFromPoint(ThreadLocal<Point> fromPoint) {
		this.fromPoint = fromPoint;
	}

	public ThreadLocal<Point> getToPoint() {
		return toPoint;
	}

	public void setToPoint(ThreadLocal<Point> toPoint) {
		this.toPoint = toPoint;
	}

}
