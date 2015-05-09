package view;

import javax.swing.JFrame;

import controler.MapController;

public class ViewFrame extends JFrame {
	private static final Integer DEFAULT_SCREEN_WIDTH = 1200;
	private static final Integer DEFAUlT_SCREEN_HEIGHT = 700;

	private MapController mapController;
	private MapPanel mapPanel;

	public ViewFrame(String title) {
		setTitle(title);
		setSize(DEFAULT_SCREEN_WIDTH, DEFAUlT_SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void showMap() {
		mapPanel = new MapPanel(mapController);
		getContentPane().add(mapPanel).getFont();
		setVisible(true);
	}

	public MapController getMapController() {
		return mapController;
	}

	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

}
