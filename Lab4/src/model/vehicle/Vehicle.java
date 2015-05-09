package model.vehicle;

public abstract class Vehicle {

	protected String productionYear;
	protected String modelName;
	protected Integer weight;
	protected String color;
	private Integer speed;

	public Integer getSpeed() {
		return this.speed;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

}