package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;

public class ChartController implements Initializable{
	@FXML
	LineChart<Integer, Integer> chart;
	Series<Integer, Integer> plantNumSeries = new Series<Integer, Integer>();
	Series<Integer, Integer> animalNumSeries = new Series<Integer, Integer>();
	Series<Integer, Integer> allNumSeries = new Series<Integer, Integer>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 plantNumSeries.setName("plant");
		 animalNumSeries.setName("animal");
		 allNumSeries.setName("all");

		 chart.getData().add(plantNumSeries);
		 chart.getData().add(animalNumSeries);
		 chart.getData().add(allNumSeries);
		 chart.setCreateSymbols(false);
		 chart.setCache(true);
		 chart.setCacheHint(CacheHint.SPEED);
	 }
}