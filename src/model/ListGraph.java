package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ListGraph<T> extends Graph<T> implements GraphInterface<T> {

	// This is the set of edges. Every vertex <T> is taken as a key. Every key
	// contains a linked list of vertices (This will represent the adjacency list)
	private Map<T, List<Edge<T>>> vertices = new HashMap<>();

	public ListGraph() {

	}

	/**
	 * @return the vertices
	 */
	public Map<T, List<Edge<T>>> getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(Map<T, List<Edge<T>>> vertices) {
		this.vertices = vertices;
	}

	/**
	 * This method adds a new vertex to the graph
	 * 
	 * @param newVertex, T, this is the value of the new vertex
	 */
	@Override
	public void addVertex(T newVertex) {
		vertices.put(newVertex, new LinkedList<Edge<T>>());
	}

	/**
	 * This method adds a directed edge from an source vertex to a destination
	 * vertex
	 * 
	 * @param source
	 * @param destination
	 * @param bidirectional
	 */
	@Override
	public void addEdge(T source, T destination, double weight, boolean bidirectional) {

		Edge<T> sourceToDestination = new Edge<>(weight, destination);
		Edge<T> destinationToSource = new Edge<>(weight, source);

		if (!vertices.containsKey(source))
			addVertex(source);

		if (!vertices.containsKey(destination))
			addVertex(destination);

		vertices.get(source).add(sourceToDestination);
		if (bidirectional) {
			vertices.get(destination).add(destinationToSource);
		}
	}

	/**
	 * This method returns the count of vertices
	 * 
	 * @return int, this is the number of vertices
	 */
	public int getVertexCount() {
		System.out.println("The graph has " + vertices.keySet().size() + " vertices");
		return vertices.keySet().size();
	}

	/**
	 * This method shows the count of edges
	 * 
	 * @param bidirection, boolean, if this is true, the counter will consider the
	 *                     bidirectional relations as a single edge. Otherwise, it
	 *                     will count every edge as a single
	 * @return int, this is the number of edges
	 */
	public int getEdgesCount(boolean bidirection) {
		int count = 0;
		for (T vertex : vertices.keySet()) {
			count += vertices.get(vertex).size();
		}
		if (bidirection == true) {
			count = count / 2;
		}
		System.out.println("The graph has " + count + " edges.");
		return count;
	}

	/**
	 * This method verifies if the graph contains a vertex or not
	 * 
	 * @param vertex, T, this is the vertex to be searched on the graph
	 * @return boolean, true if the graph contains the searched vertex, false
	 *         otherwise.
	 */
	public boolean hasVertex(T vertex) {
		if (vertices.containsKey(vertex)) {
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
		if (vertices.get(source).contains(destination)) {
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

		for (T v : vertices.keySet()) {
			output += v.toString() + ": ";
			for (Edge<T> w : vertices.get(v)) {
				output += w.toString() + " ";
			}
			output += "\n";
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
		HashMap<T, Integer> shortestPath = new HashMap<>();

		HashMap<T, Boolean> reached = new HashMap<>();

		for (Entry<T, List<Edge<T>>> node : vertices.entrySet()) {
			shortestPath.put(node.getKey(), Integer.MAX_VALUE);
			reached.put(node.getKey(), false);
		}

		shortestPath.put(src, 0);

		for (int i = 0; i < vertices.size() - 1; i++) {

			T u = minDistance(shortestPath, reached);
			reached.put(u, true);

			for (Edge<T> edge : vertices.get(u)) {
				T v = edge.getVertex();
				int distance = (int) (shortestPath.get(u) + edge.getWeight());

				if (!reached.get(v) && shortestPath.get(u) != Integer.MAX_VALUE && distance < shortestPath.get(v)) {
					shortestPath.put(v, distance);
				}
			}
		}
		printSolution(shortestPath);
		return shortestPath;
	}

	// A utility function to print the constructed distance array
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
		Set<T> keySet = vertices.keySet();
		List<T> adjacencyList = new ArrayList<>();
		adjacencyList.addAll(keySet);

		return adjacencyList;
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
		ArrayList<T> adjacency = new ArrayList<>();
		for (Edge<T> e : vertices.get(source)) {
			adjacency.add(e.getVertex());
		}

		return adjacency;
	}

}
