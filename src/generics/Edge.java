package generics;

public class Edge<T extends Comparable<T>> {
	private Vertex<T> inputVertex;
	private Vertex<T> outputVertex;
	private Integer weight;

	public Edge(Vertex<T> inputVertex, Vertex<T> outputVertex, Integer weight) {
		this.inputVertex = inputVertex;
		this.outputVertex = outputVertex;
		this.weight = weight;
	}

	public Edge(Vertex<T> inputVertex, Vertex<T> outputVertex) {
		this.inputVertex = inputVertex;
		this.outputVertex = outputVertex;
	}

	public Edge<T> areEquals(Vertex<T> a, Vertex<T> b, int i, int j) {
	
		if (this.getInputVertex().getValue().equals(a.getValue()) && this.getOutputVertex().getValue().equals(b.getValue())) {
			System.out.println("Si, "+ "Matrix position : [" + (i+1) +"," + (j+1) + "]");
			System.out.println("Peso entre "+ a.getValue() + " Y "+ b.getValue() + " = " + this.getWeight());
			return this;
		} else {
			return null;
		}

	}

	/**
	 * @return the inputVertex
	 */
	public Vertex<T> getInputVertex() {
		return inputVertex;
	}

	/**
	 * @param inputVertex the inputVertex to set
	 */
	public void setInputVertex(Vertex<T> inputVertex) {
		this.inputVertex = inputVertex;
	}

	/**
	 * @return the outputVertex
	 */
	public Vertex<T> getOutputVertex() {
		return outputVertex;
	}

	/**
	 * @param outputVertex the outputVertex to set
	 */
	public void setOutputVertex(Vertex<T> outputVertex) {
		this.outputVertex = outputVertex;
	}

	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}
