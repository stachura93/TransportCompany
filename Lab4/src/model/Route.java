package model;

import java.util.ArrayList;
import java.util.Collection;

public class Route {

	private ArrayList<Integer> YRoutePositionOnMatrix = new ArrayList<Integer>();
	private ArrayList<Integer> XRoutePositionOnMatrix = new ArrayList<Integer>();
	
	private Integer distance;
	private String name;

	public Route(String name, Integer distance) {
		this.setName(name);
		this.setDistance(distance);
	}
	
	public void newYPostitionRouteOnMatrix(Collection<? extends Integer> yPosytion) {	
		this.getYRoutePositionOnMatrix().addAll(yPosytion);
	}
	
	public void newXPostitionRouteOnMatrix(Collection<? extends Integer> xPosytion ){
		this.getXRoutePositionOnMatrix().addAll(xPosytion);
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getXRoutePositionOnMatrix() {
		return XRoutePositionOnMatrix;
	}

	public void setXRoutePositionOnMatrix(ArrayList<Integer> xRoutePositionOnMatrix) {
		XRoutePositionOnMatrix = xRoutePositionOnMatrix;
	}

	public ArrayList<Integer> getYRoutePositionOnMatrix() {
		return YRoutePositionOnMatrix;
	}

	public void setYRoutePositionOnMatrix(ArrayList<Integer> yRoutePositionOnMatrix) {
		YRoutePositionOnMatrix = yRoutePositionOnMatrix;
	}

}