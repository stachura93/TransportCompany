package service;

import java.awt.Point;
import java.util.concurrent.ConcurrentLinkedQueue;

import model.station.FuelStation;

public class FuelStationThreads implements Runnable {
	private static ConcurrentLinkedQueue<CarsMove> carsInStation = new ConcurrentLinkedQueue<CarsMove>();
	private static FuelStationThreads thisFuelStation;
	private static Point positionOnMap;
	private FuelStation fuelStation;

	public FuelStationThreads(Point positionOnMap) {
		this.setFuelStation(new FuelStation());
		setPositionOnMap(positionOnMap);
		setThisFuelStation(this);
	}
	
	@Override
	public void run() {
		while(true) {
			if(!carsInStation.isEmpty()) {
				synchronized(this) {					
					CarsMove v = carsInStation.poll();
					int newValue = v.getVehicle().getTheCapacityOfTheTank() - 2*(v.getVehicle().getCombustion());
					System.out.print(Thread.currentThread().getName() + " more fuel auto, before " + v.getVehicle().getFuel() + " now " + newValue + " ");
					v.getVehicle().setFuel(newValue);
					v.getVehicle().toString();
					this.notify();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void moreFuel() {
		CarsMove v = carsInStation.poll();
		int newValue = v.getVehicle().getTheCapacityOfTheTank() - 2*(v.getVehicle().getCombustion());
		System.out.println(Thread.currentThread().getName() + " refuels auto, before " + v.getVehicle().getFuel() + " now " + newValue);
		v.getVehicle().setFuel(newValue);
		v.notify();
	}

	public static ConcurrentLinkedQueue<CarsMove> getCarsInStation() {
		return carsInStation;
	}

	public static void setCarsInStation(ConcurrentLinkedQueue<CarsMove> carsInStation) {
		FuelStationThreads.carsInStation = carsInStation;
	}

	public static Point getPositionOnMap() {
		return positionOnMap;
	}

	public static void setPositionOnMap(Point positionOnMap) {
		FuelStationThreads.positionOnMap = positionOnMap;
	}

	public static FuelStationThreads getThisFuelStation() {
		return thisFuelStation;
	}

	public static void setThisFuelStation(FuelStationThreads thisFuelStation) {
		FuelStationThreads.thisFuelStation = thisFuelStation;
	}

	public FuelStation getFuelStation() {
		return fuelStation;
	}

	public void setFuelStation(FuelStation fuelStation) {
		this.fuelStation = fuelStation;
	}
}
