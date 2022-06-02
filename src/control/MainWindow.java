package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import model.Home;
import screens.ScreenA;

public class MainWindow implements Initializable {

	// Canvas
	@FXML
	private Canvas canvas;
	private GraphicsContext gc;

	// Choice Boxes
	@FXML
	private ChoiceBox<String> destinationCB;
	@FXML
	private ChoiceBox<String> sourceCB;

	// Buttons
	@FXML
	private Button sendBTN;
	@FXML
	private Button simulateADayBTN;

	// Screens
	private ArrayList<ScreenA> screens;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		screens = new ArrayList<>();
		screens.add(new ScreenA(canvas));
		gc = canvas.getGraphicsContext2D();

		canvas.setFocusTraversable(true);

		paint();

		fillCB();
	}

	private void fillCB() {
		ObservableList<String> homes = FXCollections.observableArrayList();

		for (Home h : screens.get(0).getHousesGraph().getListOfVertices()) {
			homes.add(h.getId());
		}

		sourceCB.setItems(homes);
		destinationCB.setItems(homes);
	}

	private void paint() {
		screens.get(0).paint();
	}

	@FXML
	void send(ActionEvent event) {
		if (sourceCB.getValue() == null && (destinationCB.getValue() == null)) {
			AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error!", "Sorurce and destination doesn't choiced!",
					"You must select the sourche and destination place!");
		} else if (sourceCB.getValue() == (null)) {
			AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error!", "Sorurce doesn't choiced!",
					"You must select the sourche place!");
		} else if (destinationCB.getValue() == (null)) {
			AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error!", "Destination doesn't choiced!",
					"You must select the destination place!");
		} else if (sourceCB.getValue().equals(destinationCB.getValue())) {
			AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error!", "You alredy are in the destination place!",
					"Try choosing a source and a destination that are not the same as each other!");
		} else {
			screens.get(0).dijkstra(sourceCB.getValue(), destinationCB.getValue());
			AlertsCreator.loadAlert(Alert.AlertType.CONFIRMATION, "Confirmation!", "The tour has ended!",
	                "The total distance traveled is: "+ screens.get(0).getTotalDistance(sourceCB.getValue(), destinationCB.getValue()) + " mts");
		}
	}

	@FXML
	void simulateADay(ActionEvent event) {
		screens.get(0).prim();
		AlertsCreator.loadAlert(Alert.AlertType.CONFIRMATION, "Confirmation!", "The tour has ended!",
                "The total distance traveled is: "+ screens.get(0).getTotalDistance() + " mts");
	}

}
