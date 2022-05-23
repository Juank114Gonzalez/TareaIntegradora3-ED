package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<T extends Comparable<T>> {
	private ArrayList<Vertex<T>> vertices;// Set of vertex that belong to Graph
	private ArrayList<Edge<T>> edges;// Set of edges that belong to Graph
	private int time; // Attribute time to do DFS algorithm

	/**
	 * Constructor from Graph Class
	 * 
	 * @param value, T, Value of the first node of the graph
	 */
	public Graph(T value) {
		this.vertices = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.vertices.add(new Vertex<T>(value));
	}

	/**
	 * This method returns a numeric matrix with the minimum weight of the path
	 * between all possible pairs of vertices.
	 * 
	 * @return adjacecncyMatrix, int[][], it´s numeric matrix with the minimum
	 *         weight of the path between all possible pairs of vertices.
	 */
	public int[][] floydWarshall() {

		vertices.sort(new Comparator<Vertex<T>>() {
			@Override
			public int compare(Vertex<T> o1, Vertex<T> o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		});
		
		for(Vertex<T> v : vertices) {
			System.out.println(v.getValue());
		}
		
		int n = vertices.size();

		int[][] adjacecncyMatrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				adjacecncyMatrix[i][j] = Integer.MAX_VALUE;
			}
		}

		System.out.println("\n\n\n----------Adjacency Matrix----------\n\n\n");
		System.out.println("Infinite values");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print("[" + adjacecncyMatrix[i][j] + "]");
			}
			System.out.println("\n");
		}

		for (int i = 0; i < n; i++) {
			adjacecncyMatrix[i][i] = 0;
		}

		System.out.println("\n\n\n----------Adjacency Matrix----------\n\n\n");
		System.out.println("Zeros diagonal");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print("[" + adjacecncyMatrix[i][j] + "]");
			}
			System.out.println("\n");
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				Vertex<T> a = vertices.get(i);

				Vertex<T> b = vertices.get(j);

				Edge<T> edge = null;

				for (Edge<T> e : edges) {
					edge = e.areEquals(a, b, i, j);
					if (edge != null) {
						adjacecncyMatrix[i][j] = edge.getWeight();
					}
				}

			}

		}
		System.out.println("\n\n\n----------Adjacency Matrix----------\n\n\n");
		System.out.println("Any direct edge?");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print("[" + adjacecncyMatrix[i][j] + "]");
			}
			System.out.println("\n");
		}

		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					if (!(adjacecncyMatrix[i][k] == Integer.MAX_VALUE || adjacecncyMatrix[k][j] == Integer.MAX_VALUE)) {
						if (adjacecncyMatrix[i][j] > adjacecncyMatrix[i][k] + adjacecncyMatrix[k][j]) {
							adjacecncyMatrix[i][j] = adjacecncyMatrix[i][k] + adjacecncyMatrix[k][j];
						}
					}
				}
			}
		}

		System.out.println("\n\n\n----------Adjacency Matrix----------\n\n\n");

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print("[" + adjacecncyMatrix[i][j] + "]");
			}
			System.out.println("\n");
		}

		return adjacecncyMatrix;
	}

	
	public boolean BFS(T sValue) {
		int i = getIndexOf(sValue);
		for (Vertex<T> u : vertices) {
			u.setColor(Color.WHITE);
			u.setDistance(Integer.MAX_VALUE);
			u.setFather(null);
		}
		vertices.get(i).setColor(Color.GRAY);
		vertices.get(i).setDistance(0);
		vertices.get(i).setFather(null);		
		Queue<Vertex<T>> q = new LinkedList<>();
		
		q.add(vertices.get(i)); //Enqueue
		while (!q.isEmpty()) {
			Vertex<T> u = q.remove(); //Dequeue
			for (Vertex<T> v : u.getAdjacencyList()) {
				if (v.getColor().equals(Color.WHITE)) {
					v.setColor(Color.GRAY);
					v.setDistance(u.getDistance() + 1);
					v.setFather(u);
					q.add(v);
				}
			}
			u.setColor(Color.BLACK);
		}
		for (Vertex<T> u : vertices) {
			if (!u.getColor().equals(Color.BLACK)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method gets the index of the vertex that contains a value
	 * @param sValue
	 * @return
	 */
	private int getIndexOf(T sValue) {
		// TODO Auto-generated method stub
		int n = 0;
		for(Vertex<T> v : vertices) {
			if(v.getValue().equals(sValue)) {
				break;
			}
			n++;
		}
		return n;
	}

	
	public boolean DFS() {
		for (Vertex<T> u : vertices) {
			u.setColor(Color.WHITE);
			u.setFather(null);
		}
		setTime(0);
		for (Vertex<T> u : vertices) {
			if (u.getColor().equals(Color.WHITE)) {
				DFSVisit(u);
			}
		}

		int c = 0;

		for (Vertex<T> u : vertices) {
			if (u.getFather() == null) {
				c++;
			}
		}

		return (c > 1) ? false : true;
	}

	private void DFSVisit(Vertex<T> u) {
		// TODO Auto-generated method stub
		time++;
		u.setDistance(time);
		u.setColor(Color.GRAY);
		for (Vertex<T> v : u.getAdjacencyList()) {
			if (v.getColor() != null && v.getColor().equals(Color.WHITE)) {
				v.setFather(u);
				DFSVisit(v);
			}
		}
		u.setColor(Color.BLACK);
		time++;
		u.setFinalTime(time);
	}



	public Vertex<T> search(T value) {
		return findVertex(value);
	}

	public void printColors() {
		for (Vertex<T> v : vertices) {
			System.out.println(v.getColor());
		}
	}

	public void printAdjacentVertex() {
		for (Vertex<T> v : vertices) {
			System.out.println("Adjacency list of " + v.getValue());
			for (Vertex<T> u : v.getAdjacencyList()) {
				System.out.println(u.getValue());
			}
		}
	}

	/**
	 * @return the vertices
	 */
	public ArrayList<Vertex<T>> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(ArrayList<Vertex<T>> vertices) {
		this.vertices = vertices;
	}

	/**
	 * @return the edges
	 */
	public ArrayList<Edge<T>> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(ArrayList<Edge<T>> edges) {
		this.edges = edges;
	}

	public void newEdge(T inputVertex, T outputVertex, Integer weight) {
		System.out.println("adding " + inputVertex + " -> " + outputVertex + " | " + weight);
		Vertex<T> a = new Vertex<>(inputVertex);
		Vertex<T> b = new Vertex<>(outputVertex);
		if (existsVertex(a)) {
			if (existsVertex(b)) {
				if (!existsEdge(a, b)) {
					updateAdjacencyList(a, b);
					a.getAdjacencyList().add(b);
					edges.add(new Edge<T>(a, b, weight));
				}
			} else {
				edges.add(new Edge<T>(a, b, weight));
				updateAdjacencyList(a, b);
				a.getAdjacencyList().add(b);
				vertices.add(b);
			}
		} else {
			a.getAdjacencyList().add(b);
			if (existsVertex(b)) {
				vertices.add(a);
				edges.add(new Edge<T>(a, b, weight));
			} else {
				vertices.add(a);
				vertices.add(b);
				edges.add(new Edge<T>(a, b, weight));
			}
		}
	}
	
	/**
	 * This is the value of the first vertex
	 * @return
	 */
	public T getFirstValue() {
		return vertices.get(0).getValue();
	}

	/**
	 * This method checks if a vertex with a value exists
	 * 
	 * @param a, vertex to be checked
	 * @return true, if the vertex exists, false otherwise
	 */
	public boolean existsVertex(Vertex<T> a) {
		for (Vertex<T> v : vertices) {
			if (v.getValue() != null)
				if (v.getValue().equals(a.getValue())) {
					return true;
				}
		}
		return false;
	}

	/**
	 * This method checks if an edge exists
	 * 
	 * @param a, this is the input vertex
	 * @param b, this is the output vertex
	 * @return true, if the edge exists, false otherwise
	 */
	public boolean existsEdge(Vertex<T> a, Vertex<T> b) {
		for (Edge<T> e : edges) {
			if (e.getInputVertex().getValue().equals(a.getValue())
					&& e.getOutputVertex().getValue().equals(b.getValue())) {
				return true;
			}
		}
		return false;
	}

	public void addVertex(T value) {
		if(findVertex(value)==null) {
			this.vertices.add(new Vertex<T>(value));
		}
	}

	public void updateAdjacencyList(Vertex<T> inputVertex, Vertex<T> outputVertex) {
		for (Vertex<T> v : vertices) {
			if (v.getValue().equals(inputVertex.getValue())) {
				v.getAdjacencyList().add(outputVertex);
				return;
			}
		}
	}

	
	/**
	 * This method returns the exact vertex of the vertices array by searching its
	 * value
	 * 
	 * @param value, this is the value of the vertex
	 * @return (T) Vertex, this is the vertex
	 */
	public Vertex<T> findVertex(T value) {
		for (Vertex<T> v : vertices) {
			if (v.getValue().equals(value)) {
				return v;
			}
		}
		return null;
	}

	/**
	 * This method gets an output vertex given the value of its input vertex
	 * 
	 * @param inputValue, T, this is the Value of the input vertex
	 * @return (T) Vertex, this is the output vertex according to the input vertex
	 */
	public Vertex<T> get(T inputValue) {
		for (Edge<T> edges : this.edges) {
			if (inputValue.equals(edges.getInputVertex().getValue())) {
				return edges.getOutputVertex();
			}
		}
		return null;
	}

	/**
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}
}