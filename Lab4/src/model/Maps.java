package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public class Maps {

	private Integer horizontalSize;
	private Integer verticalSize;
	private Integer[][] map;

	private ArrayList<Route> roads = new ArrayList<Route>();

	public Maps(Integer horizontalSize, Integer verticalSize) {
		this.setHorizontalSize(horizontalSize);
		this.setVerticalSize(verticalSize);
		setMap(new Integer[horizontalSize][verticalSize]);
		initializationMap(this.getVerticalSize(), this.getHorizontalSize());
	}

	private void initializationMap(Integer verticalSize, Integer horizontalSize) {
		for (int i = 0; i < getMap().length; i++) {
			for (int j = 0; j < getMap()[i].length; j++) {
				getMap()[i][j] = 0;
			}
		}
	}

	public void addRouteToMap(String name, Integer distance,
			Collection<? extends Integer> yPosytion,
			Collection<? extends Integer> xPosytion) {
		Route route = new Route(name, distance);
		/*
		 * X and Y must by that same size
		 */
		route.newXPostitionRouteOnMatrix(yPosytion);
		route.newYPostitionRouteOnMatrix(xPosytion);
		roads.add(route);
		integrateAllRouteOnMap();
	}

	private void integrateAllRouteOnMap() {
		for (Route road : roads) {
			for (int i = 0; i < road.getDistance(); i++) {
				changeValueOnTheMap(1, road.getXRoutePositionOnMatrix().get(i),
						road.getYRoutePositionOnMatrix().get(i));
			}
		}
	}

	public void changeValueOnTheMap(Integer newValueInFields,
			Integer positionX, Integer positionY) {
		getMap()[positionX][positionY] = newValueInFields;
	}

	public int getInformationAboutValueOnPosition(Point pointOnMap) {
		return getMap()[pointOnMap.x][pointOnMap.y];
	}

	@Override
	public String toString() {
		for (int i = 0; i < getHorizontalSize(); i++) {
			for (int j = 0; j < getVerticalSize(); j++) {
				System.out.print(getMap()[i][j] + " ");
			}
			System.out.println();
		}
		return super.toString();
	}

	public ArrayList<Route> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Route> roads) {
		this.roads = roads;
	}

	public Integer[][] getMap() {
		return map;
	}

	public void setMap(Integer[][] map) {
		this.map = map;
	}

	public Integer getHorizontalSize() {
		return horizontalSize;
	}

	public void setHorizontalSize(Integer horizontalSize) {
		this.horizontalSize = horizontalSize;
	}

	public Integer getVerticalSize() {
		return verticalSize;
	}

	public void setVerticalSize(Integer verticalSize) {
		this.verticalSize = verticalSize;
	}
}
