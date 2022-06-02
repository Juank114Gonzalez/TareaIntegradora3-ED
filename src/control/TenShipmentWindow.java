package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import model.Home;
import ui.Main;

public class TenShipmentWindow implements Initializable {

	@FXML
	private ChoiceBox<String> destinationCB1;

	@FXML
	private ChoiceBox<String> destinationCB10;

	@FXML
	private ChoiceBox<String> destinationCB2;

	@FXML
	private ChoiceBox<String> destinationCB3;

	@FXML
	private ChoiceBox<String> destinationCB4;

	@FXML
	private ChoiceBox<String> destinationCB5;

	@FXML
	private ChoiceBox<String> destinationCB6;

	@FXML
	private ChoiceBox<String> destinationCB7;

	@FXML
	private ChoiceBox<String> destinationCB8;

	@FXML
	private ChoiceBox<String> destinationCB9;

	@FXML
	private AnchorPane mainAP;

	@FXML
	private ChoiceBox<String> sourceCB1;

	@FXML
	private ChoiceBox<String> sourceCB10;

	@FXML
	private ChoiceBox<String> sourceCB2;

	@FXML
	private ChoiceBox<String> sourceCB3;

	@FXML
	private ChoiceBox<String> sourceCB4;

	@FXML
	private ChoiceBox<String> sourceCB5;

	@FXML
	private ChoiceBox<String> sourceCB6;

	@FXML
	private ChoiceBox<String> sourceCB7;

	@FXML
	private ChoiceBox<String> sourceCB8;

	@FXML
	private ChoiceBox<String> sourceCB9;

	@FXML
	private Button startSimulationBTN;

	private ArrayList<ChoiceBox<String>> cbxs;

	@FXML
	void startSimulation(ActionEvent event) {
		int aux = 0;
		boolean flag1 = true;
		boolean flag2 = true;

		for (ChoiceBox<String> cb : cbxs) {
			if (cb.getValue() == null) {
				aux++;
			}
		}
		if (aux == cbxs.size()) {
			flag1 = false;
			AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error!", "Shipping route can not be null!",
					"You must select at least one source and one destination!");
		}
		if(flag1) {
			for (ChoiceBox<String> cb : cbxs) {
				for (ChoiceBox<String> cb2 : cbxs) {
					if (cb != cb2 && cb.getValue() != null && cb2.getValue() != null) {
						if (cb.getValue() == cb2.getValue()) {
							AlertsCreator.loadAlert(Alert.AlertType.ERROR, "Error!",
									"The destination cannot be the same as the source!",
									"The places of delivery must be different from each other!");
							flag2 = false;
						}
					}
				}
			}
		}
		
		if(flag1 && flag2) {
			
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCbxs();
		fillCB();
	}

	private void initCbxs() {
		// TODO Auto-generated method stub
		cbxs.add(sourceCB1);
		cbxs.add(destinationCB1);

		cbxs.add(sourceCB2);
		cbxs.add(destinationCB3);

		cbxs.add(sourceCB3);
		cbxs.add(destinationCB3);

		cbxs.add(sourceCB4);
		cbxs.add(destinationCB4);

		cbxs.add(sourceCB5);
		cbxs.add(destinationCB5);

		cbxs.add(sourceCB6);
		cbxs.add(destinationCB6);

		cbxs.add(sourceCB7);
		cbxs.add(destinationCB7);

		cbxs.add(sourceCB8);
		cbxs.add(destinationCB8);

		cbxs.add(sourceCB9);
		cbxs.add(destinationCB9);

		cbxs.add(sourceCB10);
		cbxs.add(destinationCB10);

	}

	private void fillCB() {

		ObservableList<String> homes = FXCollections.observableArrayList();

		for (Home h : Main.homes) {
			homes.add(h.getId());
		}

		for (ChoiceBox<String> cb : cbxs) {
			cb.setItems(homes);
		}
	}

}
