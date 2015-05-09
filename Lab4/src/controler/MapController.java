package controler;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Maps;
import service.Possibility;
import view.ViewFrame;

public class MapController {
	private Possibility possibility;
	private ViewFrame mapViewFrame;
	private Maps mapsServiceModel;

	public MapController(ViewFrame viewFrame, Maps mapsModel) {
		this.mapViewFrame = viewFrame;
		this.mapsServiceModel = mapsModel;

		ini();
		possibility = new Possibility(mapsServiceModel.getMap());
	}

	public void repainGraphics() {
		mapViewFrame.repaint();
	}

	public CopyOnWriteArrayList<Point> getBestWay(Point from, Point to) {
		return possibility.findWayToPoint(from, to);
	}

	public int getInformationAboutPhotoOnMap(Point pointOnMap) {
		return mapsServiceModel.getInformationAboutValueOnPosition(pointOnMap);
	}

	public Dimension getMapSize() {
		return new Dimension(mapsServiceModel.getVerticalSize(),
				mapsServiceModel.getHorizontalSize());
	}

	public Integer whatImageChoose(int i, int j) {

		if (mapsServiceModel.getMap()[i][j] == 0)
			return new Integer(0);
		else if (mapsServiceModel.getMap()[i][j] == 1)
			return new Integer(1);
		else if (mapsServiceModel.getMap()[i][j] == 2)
			return new Integer(2);
		else if (mapsServiceModel.getMap()[i][j] == 3)
			return new Integer(3);
		else if (mapsServiceModel.getMap()[i][j] == 4)
			return new Integer(4);
		else if (mapsServiceModel.getMap()[i][j] == 5)
			return new Integer(5);
		return new Integer(6);
	}

	public void setPostionOnMap(Integer newValueInFields, Integer positionX,
			Integer positionY) {
		mapsServiceModel.changeValueOnTheMap(newValueInFields, positionX,
				positionY);
	}

	private void ini() {

		mapsServiceModel.addRouteToMap("Route1", 48, Arrays.asList(3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
				30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45,
				46, 47, 48));
		mapsServiceModel
				.addRouteToMap("Route2", 14, Arrays.asList(4, 5, 6, 7, 8, 9,
						10, 11, 12, 13, 14, 14, 14, 14), Arrays.asList(1, 1, 1,
						1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4));
		mapsServiceModel.addRouteToMap("Route3", 23, Arrays.asList(4, 5, 6, 7,
				8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 15, 16, 17, 18, 19,
				20, 21, 22), Arrays.asList(9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8,
				7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5));

		mapsServiceModel.addRouteToMap("Route1", 48, Arrays.asList(3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
				3, 3), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
				30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45,
				46, 47, 48));
		mapsServiceModel
				.addRouteToMap("Route2", 14, Arrays.asList(4, 5, 6, 7, 8, 9,
						10, 11, 12, 13, 14, 14, 14, 14), Arrays.asList(1, 1, 1,
						1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 4));
		mapsServiceModel.addRouteToMap("Route3", 23, Arrays.asList(4, 5, 6, 7,
				8, 9, 10, 11, 12, 13, 14, 14, 14, 14, 14, 15, 16, 17, 18, 19,
				20, 21, 22), Arrays.asList(9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 8,
				7, 6, 5, 5, 5, 5, 5, 5, 5, 5, 5));

		mapsServiceModel.addRouteToMap("Route4", 8,
				Arrays.asList(11, 11, 11, 11, 11, 11, 11, 11),
				Arrays.asList(10, 11, 12, 13, 14, 15, 16, 17));
		mapsServiceModel.addRouteToMap("Route5", 12,
				Arrays.asList(26, 25, 24, 23, 23, 23, 23, 23, 23, 23, 23, 23),
				Arrays.asList(2, 2, 2, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		mapsServiceModel.addRouteToMap("Route6", 7,
				Arrays.asList(23, 23, 23, 23, 23, 23, 23),
				Arrays.asList(11, 12, 13, 14, 15, 16, 17));
		mapsServiceModel.addRouteToMap("Route7", 34, Arrays.asList(27, 27, 27,
				27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27,
				27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27),
				Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
						16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
						30, 31, 32, 33, 34, 35));
		mapsServiceModel.addRouteToMap("Route8", 23, Arrays.asList(4, 5, 6, 7,
				8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
				24, 25, 26), Arrays.asList(18, 18, 18, 18, 18, 18, 18, 18, 18,
				18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18));
		mapsServiceModel.addRouteToMap("Route9", 18, Arrays.asList(14, 14, 14,
				14, 14, 14, 14, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4), Arrays
				.asList(19, 20, 21, 22, 23, 24, 25, 26, 26, 26, 26, 26, 26, 26,
						26, 26, 26, 26));
		mapsServiceModel.addRouteToMap("Route10", 12,
				Arrays.asList(15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26),
				Arrays.asList(23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23));
		mapsServiceModel.addRouteToMap("Route11", 21, Arrays.asList(7, 7, 7, 7,
				7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 10, 11, 12, 13, 14), Arrays
				.asList(27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
						40, 40, 40, 40, 40, 40, 40));
		mapsServiceModel.addRouteToMap("Route12", 19,
				Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
						20, 20, 20, 20, 20, 20), Arrays.asList(28, 28, 28, 28,
						28, 28, 28, 28, 28, 28, 28, 28, 28, 29, 30, 31, 32, 33,
						34));
		mapsServiceModel.addRouteToMap("Route13", 23, Arrays.asList(25, 24, 23,
				22, 21, 20, 19, 18, 17, 16, 15, 15, 15, 15, 15, 15, 15, 15, 15,
				15, 15, 15, 15), Arrays.asList(35, 35, 35, 35, 35, 35, 35, 35,
				35, 35, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47));
		mapsServiceModel.addRouteToMap("Route14", 36, Arrays.asList(26, 26, 26,
				26, 26, 26, 26, 26, 26, 26, 25, 24, 23, 23, 23, 23, 23, 22, 21,
				20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4),
				Arrays.asList(35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 44, 44,
						44, 45, 46, 47, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48,
						48, 48, 48, 48, 48, 48, 48, 48, 48, 48));

		mapsServiceModel.changeValueOnTheMap(3, 2, 16);

		mapsServiceModel.changeValueOnTheMap(4, 2, 32);

		mapsServiceModel.changeValueOnTheMap(5, 22, 3);
		mapsServiceModel.changeValueOnTheMap(5, 9, 8);
		mapsServiceModel.changeValueOnTheMap(5, 26, 11);
		mapsServiceModel.changeValueOnTheMap(5, 22, 13);
		mapsServiceModel.changeValueOnTheMap(5, 12, 15);
		mapsServiceModel.changeValueOnTheMap(5, 22, 19);
		mapsServiceModel.changeValueOnTheMap(5, 21, 29);
		mapsServiceModel.changeValueOnTheMap(5, 28, 30);
		mapsServiceModel.changeValueOnTheMap(5, 6, 31);
		mapsServiceModel.changeValueOnTheMap(5, 18, 36);
		mapsServiceModel.changeValueOnTheMap(5, 9, 41);
		mapsServiceModel.changeValueOnTheMap(5, 26, 45);
		mapsServiceModel.changeValueOnTheMap(5, 8, 47);

	}

}
