package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InitializeProgramWindow implements Initializable {

	@FXML
	private Button startBTN;

	@FXML
	private TextField housesTF;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
    @FXML
    void startApplication(ActionEvent event) {
    	 int k = (int) Integer.parseInt(housesTF.getText());
    	 
    	 
    }


}
