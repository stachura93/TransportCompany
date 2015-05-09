package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.Timer;

import controler.MapController;

public class MapPanel extends JPanel {
	BufferedImage grassImage = null, roadImage = null, carImage = null,
			parking = null, company = null, petrolStation = null;

	private MapController mapController;
	Dimension mapSize;
	Graphics g;

	private Timer timerAddPhoto;
	private Timer timerDeletePhoto;

	public MapPanel(MapController mapController) {
		this.mapController = mapController;
		setFocusable(true);
		loadMapImage();
	}

	class Modyfication {
		private ArrayList<Point> roadStartEnd;
		volatile Integer howImageChoose;

		public Modyfication(ArrayList<Point> roadStartEnd) {
			this.setRoadStartEnd(roadStartEnd);
			howImageChoose = new Integer(mapController.whatImageChoose(
					roadStartEnd.get(0).x, roadStartEnd.get(0).y));
		}

		public ArrayList<Point> getRoadStartEnd() {
			return roadStartEnd;
		}

		public void setRoadStartEnd(ArrayList<Point> roadStartEnd) {
			this.roadStartEnd = roadStartEnd;
		}

		Action createPointToMap = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (getRoadStartEnd().size() > 0) {
					drawRoadInMap(2, getRoadStartEnd().get(0).x,
							getRoadStartEnd().get(0).y);
					repaint();
				}
			}
		};

		Action deletePointToMap = new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (getRoadStartEnd().size() > 0) {
					drawRoadInMap(howImageChoose, getRoadStartEnd().get(0).x,
							getRoadStartEnd().get(0).y);
					getRoadStartEnd().remove(getRoadStartEnd().get(0));
					if (getRoadStartEnd().size() > 0)
						howImageChoose = new Integer(
								mapController.whatImageChoose(getRoadStartEnd()
										.get(0).x, getRoadStartEnd().get(0).y));
					repaint();
				}
			}
		};
	}

	private void loadMapImage() {
		try {
			roadImage = ImageIO
					.read(new File(
							"/Users/bartlomiejstachura/eclipse/workspace/ProWsp&Rozp/Lab4/image/gray-box.png"));
			grassImage = ImageIO
					.read(new File(
							"/Users/bartlomiejstachura/eclipse/workspace/ProWsp&Rozp/Lab4/image/grass_tiled.png"));
			carImage = ImageIO
					.read(new File(
							"/Users/bartlomiejstachura/eclipse/workspace/ProWsp&Rozp/Lab4/image/red_point.png"));

			parking = ImageIO
					.read(new File(
							"/Users/bartlomiejstachura/eclipse/workspace/ProWsp&Rozp/Lab4/image/parking.png"));

			petrolStation = ImageIO
					.read(new File(
							"/Users/bartlomiejstachura/eclipse/workspace/ProWsp&Rozp/Lab4/image/petrol.png"));

			company = ImageIO
					.read(new File(
							"/Users/bartlomiejstachura/eclipse/workspace/ProWsp&Rozp/Lab4/image/company.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Toolkit.getDefaultToolkit().sync();
		drawMartrixInFrame(g);
		g.dispose();
	}

	public void drawMartrixInFrame(Graphics g) {
		/*
		 * Draw Matrix
		 */
		Dimension spaceBettwenBox = new Dimension(10, 10);
		Dimension roadBoxSize = new Dimension(20, 20);
		int i = 0;
		int j = 0;
		for (i = 0; i < mapController.getMapSize().height; i++) {
			for (j = 0; j < mapController.getMapSize().width; j++) {
				if (mapController.whatImageChoose(i, j) == 0)
					g.drawImage(grassImage, spaceBettwenBox.width,
							spaceBettwenBox.height, roadBoxSize.width,
							roadBoxSize.height, null);
				else if (mapController.whatImageChoose(i, j) == 1)
					g.drawImage(roadImage, spaceBettwenBox.width,
							spaceBettwenBox.height, roadBoxSize.width,
							roadBoxSize.height, null);
				else if (mapController.whatImageChoose(i, j) == 2)
					g.drawImage(carImage, spaceBettwenBox.width,
							spaceBettwenBox.height, roadBoxSize.width,
							roadBoxSize.height, null);
				else if (mapController.whatImageChoose(i, j) == 3)
					g.drawImage(parking, spaceBettwenBox.width,
							spaceBettwenBox.height, roadBoxSize.width,
							roadBoxSize.height, null);
				else if (mapController.whatImageChoose(i, j) == 4)
					g.drawImage(petrolStation, spaceBettwenBox.width,
							spaceBettwenBox.height, roadBoxSize.width,
							roadBoxSize.height, null);
				else if (mapController.whatImageChoose(i, j) == 5)
					g.drawImage(company, spaceBettwenBox.width,
							spaceBettwenBox.height, roadBoxSize.width,
							roadBoxSize.height, null);

				spaceBettwenBox.width += 20;
			}
			spaceBettwenBox.width = 10;
			spaceBettwenBox.height += 20;
		}
	}

	public void drawRoadInMap(Integer value, Integer positionX,
			Integer positionY) {
		mapController.setPostionOnMap(value, positionX, positionY);
	}

	public MapController getMapController() {
		return mapController;
	}

	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}

	public Timer getTimerAddPhoto() {
		return timerAddPhoto;
	}

	public void setTimerAddPhoto(Timer timerAddPhoto) {
		this.timerAddPhoto = timerAddPhoto;
	}

	public Timer getTimerDeletePhoto() {
		return timerDeletePhoto;
	}

	public void setTimerDeletePhoto(Timer timerDeletePhoto) {
		this.timerDeletePhoto = timerDeletePhoto;
	}
}