package model;

/**
 * This class represents an edge, but it only contains the destination vertex
 * and the weight of the edge.
 * 
 * @author Gabriel Restrepo Giraldo
 * @author Juan Camilo Gonz?lez Torres
 *
 * @param <T> This is the type of the vertex
 */
public class Edge<T> implements Comparable<Edge<T>>{

	private double weight;
	private T vertex;



	public Edge(double weight, T vertex) {
		this.weight = weight;
		this.vertex = vertex;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the vertex
	 */
	public T getVertex() {
		return vertex;
	}

	/**
	 * @param vertex the vertex to set
	 */
	public void setVertex(T vertex) {
		this.vertex = vertex;
	}

	@Override
	public String toString() {
		return vertex.toString() + " weight: " + weight;
	}

	@Override
	public int compareTo(Edge<T> e) {
		if(e.getWeight()>this.weight) {
			return -1;
		}else if(e.getWeight()<this.weight) {
			return 1;
		}
		return 0;
	}

}