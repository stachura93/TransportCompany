package service;

import java.awt.Point;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.SwingWorker;

public class Company extends SwingWorker<Void, Void> {

	private static CopyOnWriteArrayList<Company> allCompanies = new CopyOnWriteArrayList<Company>();
	private final AtomicInteger informationAboutSendCar = new AtomicInteger();
	private AtomicBoolean waitFromCar = new AtomicBoolean(false);
	private Point positionOnMap;
	private long threadTime;
	private Orders orders;

	public Company(Point posPoint, Orders orders, long threadTime) {
		setOrders(orders);
		setPositionOnMap(posPoint);
		setThreadTime(threadTime);
	}

	@Override
	protected Void doInBackground() {
		while (true) {
			try {
				newTransportRequest();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(getThreadTime());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void newTransportRequest() throws InterruptedException {
		while (getWaitFromCar().get() == false) {
			if (getOrders().getOrderInProgress().compareAndSet(false, true)) {
				getWaitFromCar().set(true);
				getOrders().acceptingNewOrders(this);
				/*
				 * Get Information about send car
				 */
				System.out.println(Thread.currentThread().getName()
						+ " Ok, I hava acceptation ");
			}
			Thread.sleep(1000);
		}
	}

	public synchronized boolean packageToSend(CarsMove carsMove) {
		if (getInformationAboutSendCar().get() == carsMove.getVehicle()
				.hashCode()) {
			System.out.println(Thread.currentThread().getName()
					+ " Package received ");
			getInformationAboutSendCar().set(0);
			return true;
		}
		return false;
	}

	public synchronized Point packageDesiny(Company company) {
		Integer n = ThreadLocalRandom.current().nextInt(
				getAllCompanies().size());
		if (getAllCompanies().get(n).getPositionOnMap() == company
				.getPositionOnMap())
			return new Point(3, 16);
		return getAllCompanies().get(n).getPositionOnMap();
	}

	public synchronized void packageReception() {

	}

	public Point getPositionOnMap() {
		return positionOnMap;
	}

	public void setPositionOnMap(Point positionOnMap) {
		this.positionOnMap = positionOnMap;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public AtomicInteger getInformationAboutSendCar() {
		return informationAboutSendCar;
	}

	@Override
	public String toString() {
		return Thread.currentThread().getName();
	}

	public AtomicBoolean getWaitFromCar() {
		return waitFromCar;
	}

	public void setWaitFromCar(AtomicBoolean waitFromCar) {
		this.waitFromCar = waitFromCar;
	}

	public long getThreadTime() {
		return threadTime;
	}

	public void setThreadTime(long threadTime) {
		this.threadTime = threadTime;
	}

	public static CopyOnWriteArrayList<Company> getAllCompanies() {
		return allCompanies;
	}

	public static void setAllCompanies(
			CopyOnWriteArrayList<Company> allCompanies) {
		Company.allCompanies = allCompanies;
	}

}
