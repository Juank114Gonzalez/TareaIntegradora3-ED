package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Home {

	private Canvas canvas;
	private GraphicsContext gc;
	private double x;
	private double y;
	private Image image;
	private String id;

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	public Home(Canvas canvas, int x, int y, int columns, int rows, String id) {
		
		this.id = id;
		// Searches for the image of the house
		File file = new File("src/image/home2.png");
		try {
			image = new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
		this.x = x + (int) (1 + Math.random() * (columns-image.getWidth()));
		this.y = y + (int) (1 + Math.random() * (rows-image.getHeight()));
		gc.strokeOval(x, y, image.getWidth(), image.getHeight());
		//this.x = x;
		//this.y = y;
	}

	/**
	 * This method paints the home on the screen
	 */
	public void paint() {
		gc.drawImage(image, x, y);
		gc.setFill(Color.BLACK);
		gc.fillText(id, x+7,y+45);
	}

	public Home(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	

}
