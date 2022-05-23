package generics;

import java.util.ArrayList;

public class Vertex <T extends Comparable<T>> implements Comparable<Vertex<T>>{
	private T value;
	private ArrayList<Vertex<T>> adjacencyList;
	private Color color;
	private int distance;
	private int finalTime;
	private Vertex<T> father;
	private int index;
	
	/**
	 * @return the father
	 */
	public Vertex<T> getFather() {
		return father;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(Vertex<T> father) {
		this.father = father;
	}

	public Vertex (T value) {
		this.value = value;
		this.color = Color.GRAY;
		this.adjacencyList = new ArrayList<>();
	}
	
	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}


	/**
	 * @return the adjacencyList
	 */
	public ArrayList<Vertex<T>> getAdjacencyList() {
		return adjacencyList;
	}


	/**
	 * @param adjacencyList the adjacencyList to set
	 */
	public void setAdjacencyList(ArrayList<Vertex<T>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * @return the finalTime
	 */
	public int getFinalTime() {
		return finalTime;
	}

	/**
	 * @param finalTime the finalTime to set
	 */
	public void setFinalTime(int finalTime) {
		this.finalTime = finalTime;
	}


	@Override
	public int compareTo(Vertex<T> other) {
		int comp = this.getValue().compareTo(other.getValue());
		return comp;
	}
	
	
}
