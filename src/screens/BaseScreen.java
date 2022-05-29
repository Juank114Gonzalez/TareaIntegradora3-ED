package screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * This abstract class is the base of a screen with the methods <b>paint</b>,
 * <b>onClick</b> and <b>onKey</b>. This class will be the elements container of
 * a visual interface.
 * 
 * @author Jessica Santander
 * @author Gabriel Restrepo
 * @author Camilo Gonzalez
 */
public abstract class BaseScreen {

	protected Canvas canvas;
	protected GraphicsContext gc;
	
	/**
	 * This is the constructor method of the BaseScreen
	 * @param canvas, Canvas, this is the canvas of the program.
	 */
	public BaseScreen(Canvas canvas) {
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
	}
	
	/**
	 * This method paints the screen
	 */
	public abstract void paint();
	
	/**
	 * This method follows a set of instructions defined by the logic of the program. 
	 * @param e, MouseEvent, this is the event extracted from some change on any mouse input
	 */
	public abstract void onClick(MouseEvent e);
	
	/**
	 * This method follows a set of instructions defined by the logic of the program.
	 * @param e, KeyEvent, this is the event extracted from some change on any keyboard input. 
	 */
	public abstract void onKey(KeyEvent e);

}
