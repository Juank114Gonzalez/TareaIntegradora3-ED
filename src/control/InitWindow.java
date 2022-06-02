package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.Main;

public class InitWindow {

	@FXML
	private ImageView iconIMG;

	@FXML
	private AnchorPane mainAP;

	@FXML
	private TextField quantityOfHousesTF;

	@FXML
	private Button startBTN;
	
	@FXML
	private CheckBox matrixCB;

	@FXML
	private CheckBox listCB;

	@FXML
	void start(ActionEvent event) {
		int nHouses = 0;

		boolean flag = true;

		try {
			nHouses = Integer.parseInt(quantityOfHousesTF.getText());

		} catch (NumberFormatException e) {
			quantityOfHousesTF.setText("");
			AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error", "Invalid input!",
					"The input must be an integer number");
			flag = false;
		}

		Main.K = nHouses;

		if (flag) {
			if (!matrixCB.isSelected() && !listCB.isSelected()) {
				AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error", "Invalid type of graph!",
						"You must select any type of graph (Matrix or List).");
			} else if (matrixCB.isSelected() && listCB.isSelected()) {
				AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error", "Invalid type of graph!",
						"You cannot select both type of graph");
			} else if (matrixCB.isSelected()) {
				AlertsCreator.loadAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Matrix of adjacency!",
						"You´ve selected \"Matrix of adjacency\" as your type of graph");
				Main.G = 0;
				launchMainWindow(event);
			} else {
				AlertsCreator.loadAlert(Alert.AlertType.CONFIRMATION, "Confirmation", "Matrix of adjacency!",
						"You´ve selected \"List of adjacency\" as your type of graph");
				Main.G = 1;
				launchMainWindow(event);
			}
		}

		
	}

	void launchMainWindow(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/MainWindow.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

}