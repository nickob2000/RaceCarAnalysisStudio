package rcas.controller;

import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.GridPane;
import rcas.model.MagicFormulaTireModel;
import rcas.model.RaceCar;
import rcas.model.TireModel;
import rcas.util.CorneringAnalyserUtil;

public class RCASMainViewController {

	@FXML
	private GridPane mainPane;
	@FXML
	private LineChart<Number, Number> mainChart;
	@FXML
	private ResourceBundle resources;

	@FXML
	public void initialize() {
		// create race cars and calculate a chart.
		RaceCar myRaceCar_1 = new RaceCar(420, 420, 370, 370);
		TireModel myTireModel_1 = new MagicFormulaTireModel();
		myRaceCar_1.setFrontRollDist(0.55);
		myRaceCar_1.setFrontAxleTireModel(myTireModel_1);
		myRaceCar_1.setRearAxleTireModel(myTireModel_1);
		myRaceCar_1.setName("Car STD (blue)");

		RaceCar myRaceCar_2 = new RaceCar(420, 420, 370, 370);
		TireModel myTireModel_2_Fr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.6, 0.000075);
		TireModel myTireModel_2_Rr = new MagicFormulaTireModel(1.3, 15.2, -1.6, 1.8, 0.000075);
		myRaceCar_2.setFrontRollDist(0.55);
		myRaceCar_2.setFrontAxleTireModel(myTireModel_2_Fr);
		myRaceCar_2.setRearAxleTireModel(myTireModel_2_Rr);
		myRaceCar_2.setName("Car MOD (red)");

		CorneringAnalyserUtil corneringUtil = new CorneringAnalyserUtil();

		// show what the toString() methods print out.
		System.out.println(myRaceCar_1.toString());
		System.out.println(myRaceCar_2.toString());
		// show balance, grip, control and stability values of the cars.
		this.printRaceCarCorneringValues(myRaceCar_1, corneringUtil);
		this.printRaceCarCorneringValues(myRaceCar_2, corneringUtil);

		ObservableList<Series<Number, Number>> dataList_1 = corneringUtil.generateMMMChartData(myRaceCar_1);
		mainChart.getData().addAll(dataList_1);
		// Set the style of the series after adding the data to the chart.
		// Otherwise no there is no reference "Node" which leads to a
		// NullPointerException.
		this.setSeriesStyle(dataList_1, ".chart-series-line", "-fx-stroke: blue; -fx-stroke-width: 1px;");

		ObservableList<Series<Number, Number>> dataList_2 = corneringUtil.generateMMMChartData(myRaceCar_2);
		mainChart.getData().addAll(dataList_2);
		this.setSeriesStyle(dataList_2, ".chart-series-line", "-fx-stroke: red; -fx-stroke-width: 1px;");
	}

	private void setSeriesStyle(ObservableList<Series<Number, Number>> dataList_1, String styleSelector,
			String lineStyle) {
		for (Iterator<Series<Number, Number>> iterator = dataList_1.iterator(); iterator.hasNext();) {
			Series<Number, Number> curve = (Series<Number, Number>) iterator.next();
			curve.getNode().lookup(styleSelector).setStyle(lineStyle);
		}
	}

	private void printRaceCarCorneringValues(RaceCar raceCar, CorneringAnalyserUtil util) {
		System.out.println(String.format(
				"%s: Grip = %.2f G\tBalance = %.2f Nm\tControl(entry) = %.2f Nm/deg\t"
						+ "Control(middle) = %.2f Nm/deg\tStability(entry) = %.2f Nm/deg\t"
						+ "Stability(middle) = %.2f Nm/deg",
				raceCar.getName(), util.getMMMGripValue(raceCar) / 9.81, util.getMMMBalanceValue(raceCar),
				util.getMMMControlValue(raceCar, 0.0, 0.0, 10.0), util.getMMMControlValue(raceCar, -5.0, 20.0, 30.0),
				util.getMMMStabilityValue(raceCar, 0.0, 0.0, 1.0),
				util.getMMMStabilityValue(raceCar, 20.0, -5.0, -4.0)));
	}
}
