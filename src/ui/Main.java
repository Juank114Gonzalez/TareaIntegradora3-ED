package ui;

import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Home;
import screens.ScreenA;

public class Main extends Application {
	private static Scanner sc = new Scanner(System.in);
	public static int G;
	public static int K;
	public static ScreenA screenA;
	public static List<Home> homes;
	
	/**
	 * This is the main class of the program, it will ask the user for the number of
	 * enemies that the program will generate.
	 */
	public static void main(String[] args) {

		launch(args);
	}


	/**
	 * This method initializes the init window
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/InitWindow.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
