package control;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertsCreator {
	 public static void loadAlert(AlertType type, String title, String middle, String bot) {
			Alert alert = new Alert(type);
			alert.setContentText(bot);
			alert.setHeaderText(middle);
			alert.setTitle(title);
			alert.show();
		}
}