package screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import model.Edge;
import model.Graph;
import model.Home;
import model.ListGraph;
import model.MatrixGraph;
import ui.Main;

public class ScreenA extends BaseScreen {

	// Los objetos sobre el escenario
	private Graph<Home> housesGraph;

	/**
	 * This is the constructor method of the ScreenA
	 * 
	 * @param canvas
	 */
	public ScreenA(Canvas canvas) {
		super(canvas);
		if (Main.G == 0) {
			housesGraph = new ListGraph<>();
		} else {
			housesGraph = new MatrixGraph<>(Main.K);
		}

	}

	@Override
	public void paint() {
		randomHouses(Main.K);
		gc.setFill(Color.BEIGE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// linea horizontal

		for (Home house : housesGraph.getListOfVertices()) {
			house.paint();
		}
		paintEdges();

	}

	private void paintEdges() {
		gc.setStroke(Color.rgb(0, 0, 0));
		gc.setFill(Color.GREEN);
		gc.setLineWidth(1);

		for (Home sourceHouse : housesGraph.getListOfVertices()) {
			int k = (int) (1 + Math.random() * housesGraph.getListOfVertices().size());
			int stop = 0;
			boolean flag = false;
			for (Home destinationHouse : housesGraph.getListOfVertices()) {
				if (stop == k) {
					if (!sourceHouse.equals(destinationHouse)) {
						housesGraph.addEdge(sourceHouse, destinationHouse,
								calculateDistance(sourceHouse, destinationHouse), true);
					}
				}
				stop++;
			}
		}

		for (Home sourceHouse : housesGraph.getListOfVertices()) {
			double[] sourceCentroid = getCentroid(sourceHouse);
			for (Home destinationHouse : housesGraph.getAdjacency(sourceHouse)) {
				double[] destinationCentroid = getCentroid(destinationHouse);
				gc.moveTo(sourceCentroid[0], sourceCentroid[1]);
				gc.lineTo(destinationCentroid[0], destinationCentroid[1]);
				//drawArrow(sourceCentroid[0], sourceCentroid[1], destinationCentroid[0], destinationCentroid[1]);
			}
		}
		gc.stroke();

	}
	
	/**
	 * This method draws a line with an arrow at the end
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void drawArrow(double x1, double y1, double x2, double y2) {
		gc.setFill(Color.RED);

		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx * dx + dy * dy);

		Transform transform = Transform.translate(x1, y1);
		transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
		gc.setTransform(new Affine(transform));

		int arrSize = 9;

		gc.strokeLine(0, 0, len, 0);
		gc.fillPolygon(new double[] { len, len - arrSize, len - arrSize, len },
				new double[] { 0, -arrSize, arrSize, 0 }, 4);
	}

	private double[] getCentroid(Home house1) {
		double topMid = house1.getX() + house1.getImage().getWidth() / 2;
		double sideMid = house1.getY() + house1.getImage().getHeight() / 2;
		return new double[] { topMid, sideMid };
	}

	private double calculateDistance(Home house1, Home house2) {
		return Math.sqrt(Math.pow(house2.getY() - house1.getY(), 2) + Math.pow(house2.getX() - house1.getX(), 2));
	}

	/**
	 * This method divides the canvas in "n" rectangles, and assigns a house in each
	 * one
	 * 
	 * @param n, int, this is the number of rectangles
	 */
	private void randomHouses(int n) {
		// TODO Auto-generated method stub
		int rectangles = n;
		ArrayList<Integer> columns = new ArrayList<>();
		ArrayList<Integer> rows = new ArrayList<>();
		int even = 0;
		do {
			int partitions = defPartitions(rectangles);
			rectangles = rectangles / partitions;
			if (even % 2 == 0) {
				columns.add(partitions);
			} else {
				rows.add(partitions);
			}
			even++;
		} while (recalculateNumOfHouses(columns, rows)[2] != n);

		int[] housesContained = recalculateNumOfHouses(columns, rows);

		int numberOfColumns = housesContained[0] > housesContained[1] ? housesContained[0] : housesContained[1];
		int numberOfRows = housesContained[0] < housesContained[1] ? housesContained[0] : housesContained[1];

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {

				String id = "";
				id = "" + ((char) (65 + i)) + ((char) (65 + j));

				housesGraph.addVertex(new Home(canvas, (int) (j * (canvas.getWidth() / numberOfColumns)),
						(int) (i * (canvas.getHeight() / numberOfRows)), (int) (canvas.getWidth() / numberOfColumns),
						(int) (canvas.getHeight() / numberOfRows), id));
			}
		}
	}

	/**
	 * This method calculates the number of houses contained, and returns an array
	 * list with the related information
	 * 
	 * @param columns, ArrayList (Integer), this is an arraylist with the factors
	 *                 that compose the number of columns. The product resulting of
	 *                 multiplying all the elements on this array will be the number
	 *                 of columns
	 * @param rows,    ArrayList (Integer), this is an arraylist with the factors
	 *                 that compose the number of rows. The product resulting of
	 *                 multiplying all the elements on this array will be the number
	 *                 of rows
	 * @return int [], this is an array of 3 elements. The element on the index 0,
	 *         will be the number of columns. The element on the index 1 will be the
	 *         number of rows. The element on the index 2 will be the amount of
	 *         rectangles (resulting of the product between the columns and the
	 *         rows).
	 */
	private int[] recalculateNumOfHouses(ArrayList<Integer> columns, ArrayList<Integer> rows) {
		if (columns.size() == 0 || rows.size() == 0) {
			return new int[] { 0, 0, 0 };
		}
		int columnAcc = columns.get(0);
		if (columns.size() > 1) {
			for (int i = 1; i < columns.size(); i++) {
				columnAcc = columnAcc * columns.get(i);
			}
		}
		int rowAcc = rows.get(0);

		if (rows.size() > 1) {
			for (int i = 1; i < rows.size(); i++) {
				rowAcc = rowAcc * rows.get(i);
			}
		}

		return new int[] { columnAcc, rowAcc, columnAcc * rowAcc };
	}

	/**
	 * This method calculates the amount of equal partitions that a rectangle can
	 * have
	 * 
	 * @param num, int, this is the expected number of rectangles
	 * @return int, this variable will represent the amount of equal partitions
	 *         (rectangles)
	 */
	private int defPartitions(int num) {
		if (num == 4) {
			return 2;
		}
		for (int x = 2; x < num / 2; x++) {
			if (num % x == 0) {
				return x;
			}

		}
		return num;
	}

	/**
	 * This an implemented method of the BaseScreen
	 */
	@Override
	public void onClick(MouseEvent e) {

	}

	@Override
	public void onKey(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
