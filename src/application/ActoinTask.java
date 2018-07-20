package application;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ActoinTask extends Service<Void> {

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				return null;
			}


		};
	}

}
