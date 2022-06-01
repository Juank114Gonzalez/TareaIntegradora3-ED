package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MatrixGraph<T> extends Graph<T> implements GraphInterface<T> {

	private double[][] adjacencyMatrix;
	private List<T> vertices;

	public MatrixGraph(int n) {
		adjacencyMatrix = new double[n][n];
		vertices = new LinkedList<>();
	}

	/**
	 * @return the vertices
	 */
	public List<T> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(List<T> vertices) {
		this.vertices = vertices;
	}

	/**
	 * This method adds a new vertex to the graph
	 * 
	 * @param newVertex, T, this is the value of the new vertex
	 */
	public void addVertex(T newVertex) {
		vertices.add(newVertex);
	}

	/**
	 * This method adds a directed edge from an source vertex to a destination
	 * vertex
	 * 
	 * @param source
	 * @param destination
	 * @param bidirectional
	 */
	public void addEdge(T source, T destination, double weight, boolean bidirectional) {
		if (!vertices.contains(source))
			addVertex(source);

		if (!vertices.contains(destination))
			addVertex(destination);

		setAdjacency(source, destination, weight);
		if (bidirectional) {
			setAdjacency(source, destination, weight);
			setAdjacency(destination, source, weight);
		}
	}

	private void setAdjacency(T source, T destination, double weight) {
		// TODO Auto-generated method stub
		int i = vertices.indexOf(source);
		int j = vertices.indexOf(destination);

		adjacencyMatrix[i][j] = weight;

	}

	/**
	 * This method returns the count of vertices
	 * 
	 * @return int, this is the number of vertices
	 */
	public int getVertexCount() {
		System.out.println("The graph has " + vertices.size() + " vertices");
		return vertices.size();
	}

	/**
	 * This method verifies if the graph contains a vertex or not
	 * 
	 * @param vertex, T, this is the vertex to be searched on the graph
	 * @return boolean, true if the graph contains the searched vertex, false
	 *         otherwise.
	 */
	public boolean hasVertex(T vertex) {
		if (vertices.contains(vertex)) {
			System.out.println("The graph contains " + vertex + " as a vertex.");
			return true;
		} else {
			System.out.println("The graph does not contain " + vertex + " as a vertex.");
			return false;
		}
	}

	/**
	 * This method verifies if the graph contains an edge or not
	 * 
	 * @param source,      T, this is the source vertex
	 * @param destination, T, this is the destination vertex
	 * @return boolean, true if the graph contains the searched edge, false
	 *         otherwise.
	 */
	public boolean hasEdge(T source, T destination) {
		int i = vertices.indexOf(source);
		int j = vertices.indexOf(destination);

		if (adjacencyMatrix[i][j] != 0) {
			System.out.println("The graph has an edge between " + source + " and " + destination + ".");
			return true;
		} else {
			System.out.println("The graph has no edge between " + source + " and " + destination + ".");
			return false;
		}
	}

	/**
	 * This method prints the adjacency list of each vertex on the graph.
	 * 
	 * @return String, this is a String with the information of the adjacency list
	 *         of each vertex
	 */
	@Override
	public String toString() {
		String output = "";

		for (int i = 0; i < vertices.size(); i++) {
			T v = vertices.get(i);
			for (int j = 0; i < vertices.size(); i++) {
				if (adjacencyMatrix[i][j] != 0) {
					output += v.toString() + ": " + adjacencyMatrix[i][j] + "\n";
				}
			}
		}
		return output;
	}

	/**
	 * This method finds the shortest path between a source and the other vertices.
	 * This method implements the adjacency list
	 * 
	 * @param src, T, this is the searched source
	 */
	@Override
	public HashMap<T, Integer> dijkstra(T src) {
		// TODO Auto-generated method stub
		HashMap<T, Integer> shortestPath = new HashMap<>();

		HashMap<T, Boolean> reached = new HashMap<>();

		for (T node : vertices) {
			shortestPath.put(node, Integer.MAX_VALUE);
			reached.put(node, false);
		}

		shortestPath.put(src, 0);

		for (int count = 0; count < vertices.size() - 1; count++) {

			T u = minDistance(shortestPath, reached);
			int uIndex = vertices.indexOf(u);
			reached.put(minDistance(shortestPath, reached), true);

			for (T v : vertices) {

				int vIndex = vertices.indexOf(v);
				int distance = (int) (shortestPath.get(u) + adjacencyMatrix[uIndex][vIndex]);
				if (!reached.get(v) && adjacencyMatrix[uIndex][vIndex] != 0 && shortestPath.get(u) != Integer.MAX_VALUE
						&& distance < shortestPath.get(v)) {
					shortestPath.put(v, distance);
				}
			}
		}
		printSolution(shortestPath);

		return shortestPath;
	}

	public void showAdjacencyMatrix() {
		System.out.print("  ");
		for (T v : vertices) {
			System.out.print(v + "   ");
		}
		System.out.println("");
		for (int i = 0; i < vertices.size(); i++) {
			System.out.print(vertices.get(i) + " ");
			for (int j = 0; j < vertices.size(); j++) {
				System.out.print(adjacencyMatrix[i][j] + " ");
			}
			System.out.println("\n");
		}
	}

	private void printSolution(HashMap<T, Integer> shortestPath) {
		System.out.println("Vertex \t\t Distance from Source");
		for (Map.Entry<T, Integer> out : shortestPath.entrySet()) {
			System.out.println(out.getKey() + "\t\t" + out.getValue());
		}
	}

	/**
	 * This method finds the T adjacent vertex with the minimum incident edge
	 * between the source and the T vertex
	 * 
	 * @param (T, Integer) HashMap, distances, this is the map that relates every
	 *            vertex T on the graph with the weight of the edge that relates it
	 *            with the source
	 * @param (T, Boolean) HashMap, reached, this is the map that relates every
	 *            vertex T on the graph with a boolean that is true if the vertex
	 *            has been reached on the dijkstra method, and it is false otherwise
	 * @return T, minKey, this is the T adjacent vertex with the minimum incident
	 *         edge between the source and the T vertex
	 */
	private T minDistance(HashMap<T, Integer> distances, HashMap<T, Boolean> reached) {
		// Initialize min value
		int min = Integer.MAX_VALUE;
		T minKey = null;

		for (Map.Entry<T, Integer> node : distances.entrySet()) {
			if (!reached.get(node.getKey()) && node.getValue() <= min) {
				min = node.getValue();
				minKey = node.getKey();
			}
		}

		return minKey;
	}

	/**
	 * @return the adjacencyMatrix
	 */
	public double[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	@Override
	public void bfs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dfs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dfsVisit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void floydWarshall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void kruskal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void prim() {
		// TODO Auto-generated method stub

	}

	/**
	 * This method gets the list of the vertices
	 * 
	 * @return adjacencyList, (T) List, this is the list of the vertices
	 */
	@Override
	public List<T> getListOfVertices() {
		// TODO Auto-generated method stub
		return vertices;
	}

	/**
	 * This method gets a list of the adjacency of a source
	 * 
	 * @param source, T, this is the source which adjacency will be searched
	 * @return adjacency, (T) List, this is the adjacency list of the source entered
	 *         as a parameter
	 */
	@Override
	public List<T> getAdjacency(T source) {
		// TODO Auto-generated method stub
		int i = vertices.indexOf(source);
		ArrayList<T> adjacencyList  = new ArrayList<>();
		for (int j = 0; j < adjacencyMatrix[i].length; j++) {
			if(adjacencyMatrix[i][j] != 0) {
				adjacencyList.add(vertices.get(j));
			}
		}
		return adjacencyList;
	}

}