package application;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lives.Animal;
import lives.Field;
import lives.Life;
import lives.Plant;

public class Controller {
	@FXML Canvas canvas;
	@FXML LineChart<Integer, Integer> chart;
	GraphicsContext gc;
	Timeline tl;
	Series<Integer, Integer> plantNumSeries = new Series<Integer, Integer>();
	Series<Integer, Integer> animalNumSeries = new Series<Integer, Integer>();
	Series<Integer, Integer> allNumSeries = new Series<Integer, Integer>();
	int t = 0;
	List<Life> lifeList = new ArrayList<Life>();

	void ini() {
		tl = new Timeline(new KeyFrame(Duration.millis(100), e -> action()));
		tl.setCycleCount(Animation.INDEFINITE);

		for (int i = 0; i < 200; i++) lifeList.add(new Plant());
		for (int i = 0; i < 100; i++) lifeList.add(new Animal());
		Field.update(lifeList);
	}

	void action() {
		if (t == 0) iniChart();
		double startTime = System.currentTimeMillis();

		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 600, 600);

		Service<Void> servis = new Service<Void>() {
			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						List<Life> next = new ArrayList<Life>();
						for (Life life : lifeList) {
							life.act(lifeList, next);
							if (life.isDead()) {
								if (life.getClass() == Animal.class && Math.random() < 0.1) {
									double X = life.x;
									double Y = life.y;
									int R = life.r + (int) ((Math.random()-0.5)*10 );
									Plant p = new Plant(X, Y, R);
									next.add(p);
								}
								continue;
							}
							next.add(life);
						}
						lifeList.clear();
						lifeList.addAll(next);
						Field.update(lifeList);

						return null;
					}


				};
			}
		};
		servis.start();
		int a = 0;
		int p = 0;
		for (Life life : lifeList) {
			if (life.getClass() == Plant.class) {gc.setFill(Color.GREEN); p++;}
			if (life.getClass() == Animal.class) {gc.setFill(Color.BLUE) ;a++;}
			gc.fillOval(life.x-5, life.y-5, life.r, life.r);
		}
		Plant.NUM = p;
		Animal.NUM = a;
		if (t%10 == 0) {
			plantNumSeries.getData().add(new XYChart.Data<Integer, Integer>(t, p));
			animalNumSeries.getData().add(new XYChart.Data<Integer, Integer>(t, a));
			allNumSeries.getData().add(new XYChart.Data<Integer, Integer>(t, a+p));
			if (t > 1000) {
				plantNumSeries.getData().remove(0);
				animalNumSeries.getData().remove(0);
				allNumSeries.getData().remove(0);
			}
		}
		t++;
		double endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

	@FXML
	void onStartButton() {
		tl.play();
	}

	@FXML
	void onStopButton() {
		tl.stop();
	}

	 void iniChart() {
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
