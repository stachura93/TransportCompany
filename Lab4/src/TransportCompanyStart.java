import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import model.Maps;
import service.Company;
import service.FuelStationThreads;
import service.Orders;
import view.ViewFrame;
import controler.MapController;

public class TransportCompanyStart {

	public static void main(String[] args) throws IOException,
			InvocationTargetException, InterruptedException {

		/*
		 * Inicialization MVP (value Controller don't be change)
		 */
		Runnable aRunnable = new Runnable() {

			@Override
			public void run() {
				ViewFrame viewFrame = new ViewFrame("Firma transportowa");
				viewFrame.setMapController(new MapController(viewFrame,
						new Maps(30, 50)));
				viewFrame.setVisible(true);

				FuelStationThreads fuelStationThreads = new FuelStationThreads(
						new Point(3, 32));
				Thread fThread = new Thread(fuelStationThreads);
				fThread.start();

				MapController controller = viewFrame.getMapController();
				Orders orders = new Orders(new Point(3, 4), controller);
				viewFrame.showMap();

				Company company1 = new Company(new Point(23, 3), orders, 200);
				Company company2 = new Company(new Point(9, 9), orders, 1000);
				Company company3 = new Company(new Point(27, 11), orders, 1200);
				Company company4 = new Company(new Point(23, 13), orders, 600);
				Company company5 = new Company(new Point(20, 29), orders, 1300);
				Company company6 = new Company(new Point(27, 30), orders, 400);
				Company company7 = new Company(new Point(7, 31), orders, 620);

				Company company8 = new Company(new Point(18, 35), orders, 860);
				Company company9 = new Company(new Point(9, 40), orders, 550);
				Company company10 = new Company(new Point(26, 44), orders, 590);
				Company company11 = new Company(new Point(8, 48), orders, 1300);

				Company.getAllCompanies().add(company1);
				Company.getAllCompanies().add(company2);
				Company.getAllCompanies().add(company3);
				Company.getAllCompanies().add(company4);
				Company.getAllCompanies().add(company5);
				Company.getAllCompanies().add(company6);
				Company.getAllCompanies().add(company7);
				Company.getAllCompanies().add(company8);
				Company.getAllCompanies().add(company9);
				Company.getAllCompanies().add(company10);
				Company.getAllCompanies().add(company11);

				orders.execute();

				company1.execute();
				company2.execute();
				company3.execute();
				company4.execute();
				company5.execute();
				company6.execute();
				company7.execute();
				company8.execute();
				company9.execute();
				company10.execute();
				company11.execute();

			}
		};

		SwingUtilities.invokeLater(aRunnable);

	}
}
