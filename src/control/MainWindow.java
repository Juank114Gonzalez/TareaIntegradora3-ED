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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import model.Home;
import screens.BaseScreen;
import screens.ScreenA;

public class MainWindow implements Initializable {
	
	//Canvas
	@FXML
	private Canvas canvas;
	private GraphicsContext gc;
	
	//Choice Boxes
	@FXML
    private ChoiceBox<String> destinationCB;
	@FXML
	private ChoiceBox<String> sourceCB;
    
	//Buttons
	@FXML
    private Button sendBTN;
    @FXML
    private Button simulateADayBTN;

    //Screens
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
		
		for(Home h : screens.get(0).getHousesGraph().getListOfVertices()) {
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
		//Exceptions and launch dijkstra
    }

    @FXML
    void simulateADay(ActionEvent event) {

    }

}
