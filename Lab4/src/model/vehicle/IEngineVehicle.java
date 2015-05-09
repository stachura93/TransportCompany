package model.vehicle;

import model.IFuel;

public interface IEngineVehicle extends IFuel {

	public Integer getCombustion();

	public Integer getTheCapacityOfTheTank();

}