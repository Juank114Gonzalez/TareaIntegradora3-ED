package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {
	
	// This is the set of edges. Every vertex <T> is taken as a key. Every key
	// contains a linked list of vertices (This will represent the adjacency list)
	private Map<T, List<Edge<T>>> vertices = new HashMap<>();


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
}
