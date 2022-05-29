package ui;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Graph;
import model.Home;
import screens.ScreenA;

public class Main extends Application {
	private static Scanner sc = new Scanner(System.in);
	public static int K;
	public static ScreenA screenA;

	/**
	 * This is the main class of the program, it will ask the user for the number of
	 * enemies that the program will generate.
	 */
	public static void main(String[] args) {

		System.out.println("Enter the number of graphs");
		K = sc.nextInt();

		launch(args);
	}


	/**
	 * This method initializes the main window
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../view/MainWindow.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		stage.setHeight(760);
		stage.setWidth(1295);
	}
}
