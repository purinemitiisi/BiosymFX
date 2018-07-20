package application;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import lives.Animal;
import lives.Field;
import lives.Life;
import lives.Plant;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Bio.fxml"));
			Parent root = loader.load();
			Controller ctrl = loader.getController();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

			List<Life> list = new ArrayList<Life>();
			for (int i = 0; i < 200; i++) list.add(new Plant());
			for (int i = 0; i < 100; i++) list.add(new Animal());
			Field.update(list);

			Timeline tl = new Timeline(new KeyFrame(Duration.millis(100), e -> ctrl.action(list)));
			tl.setCycleCount(1000);
			ctrl.setTimeLine(tl);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
