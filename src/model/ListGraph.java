package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class ListGraph<T> extends Graph<T> implements GraphInterface<T> {

	// This is the set of edges. Every vertex <T> is taken as a key. Every key
	// contains a linked list of vertices (This will represent the adjacency list)
	private Map<T, List<Edge<T>>> vertices = new HashMap<>();
	private Graph<T> minimumSpanningTree = null;
	private HashMap<T, Color> color;
	private HashMap<T, Integer> d;
	private HashMap<T, T> pi;
	private HashMap<T, Integer> f;
	private int time;

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
			return true;
		} else {
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
			return true;
		} else {
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
	public List<T> dijkstra(T src, T destination) {
		HashMap<T, Integer> shortestPath = new HashMap<>();
		HashMap<T, List<T>> finalPath = new HashMap<>();
		HashMap<T, Boolean> reached = new HashMap<>();

		for (Entry<T, List<Edge<T>>> node : vertices.entrySet()) {
			finalPath.put(node.getKey(), new LinkedList<>());
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
					finalPath.get(v).add(u);
					shortestPath.put(v, distance);
				}
			}
		}
		ArrayList<T> path = new ArrayList<>();
		path.add(destination);
		return getPath(finalPath, destination, path);
	}

	/**
	 * This method gets the path of nodes from the source to the destination
	 * 
	 * @param finalPath,   (T, (T) List) HashMap, this map relates each node of the
	 *                     graph with the previous nodes to reach itself
	 * @param destination, T, this is the destination of the path
	 * @param path,        (T) ArrayList, this list contains the set of nodes that
	 *                     conform the path
	 * @return
	 */
	private List<T> getPath(HashMap<T, List<T>> finalPath, T destination, ArrayList<T> path) {
		if (finalPath.get(destination).size() == 0) {
			return path;
		}

		for (int i = finalPath.get(destination).size() - 1; i >= 0; i--) {
			path.add(finalPath.get(destination).get(i));
		}

		return getPath(finalPath, finalPath.get(destination).get(0), path);

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
	public void floydWarshall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void kruskal() {
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

	@Override
	public T findVertex(T vertex) {
		List<T> vertices = this.getListOfVertices();

		for (T v : vertices) {
			if (v.equals(vertex)) {
				return v;
			}
		}

		return null;
	}

	@Override
	public boolean bfs(T s) {
		color = new HashMap<>();
		d = new HashMap<>();
		pi = new HashMap<>();

		for (T u : this.getListOfVertices()) {
			color.put(u, Color.WHITE);
			d.put(u, Integer.MAX_VALUE);
			pi.put(u, null);
		}

		color.put(s, Color.GRAY);
		d.put(s, 0);
		pi.put(s, null);

		Queue<T> Q = new LinkedList<>();

		Q.add(s);

		while (!Q.isEmpty()) {
			T u = Q.peek();
			if (!Q.isEmpty()) {
				Q.remove();
			}
			for (T v : this.getAdjacency(u)) {
				if (color.get(v).equals(Color.WHITE)) {
					color.put(v, Color.GRAY);
					d.put(v, d.get(u) + 1);
					pi.put(v, u);
					Q.add(v);
				}
			}
			color.put(u, Color.BLACK);
		}

		for (T u : this.getListOfVertices()) {
			if (!color.get(u).equals(Color.BLACK)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean dfs() {
		color = new HashMap<>();
		pi = new HashMap<>();
		d = new HashMap<>();
		f = new HashMap<>();

		for (T u : this.getListOfVertices()) {
			color.put(u, Color.WHITE);
			d.put(u, Integer.MAX_VALUE);
			pi.put(u, null);
		}

		time = 0;

		for (T u : this.getListOfVertices()) {
			if (color.get(u).equals(Color.WHITE)) {
				dfsVisit(u);
			}
		}

		int aux = 0;

		for (T u : this.getListOfVertices()) {
			if (pi.get(u) == null) {
				aux++;
			}

			if (aux > 1) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void dfsVisit(T u) {
		time += 1;

		d.put(u, time);

		color.put(u, Color.GRAY);

		for (T v : this.getAdjacency(u)) {
			if (color.get(v).equals(Color.WHITE)) {
				pi.put(v, u);
				dfsVisit(v);
			}
		}

		color.put(u, Color.BLACK);

		time += 1;

		f.put(u, time);
	}

	/**
	 * This method gets the minimum spanning tree
	 * @return 
	 */
	@Override
	public ListGraph<T> prim() {
		List<T> vertices = getListOfVertices();
		HashMap<T, T> nodeParent = new HashMap<>();
		HashMap<T, Integer> nodeKey = new HashMap<>();
		HashMap<T, Boolean> included = new HashMap<>();

		for (T vertex : vertices) {
			nodeKey.put(vertex, Integer.MAX_VALUE);
			included.put(vertex, false);
		}

		nodeKey.put(getListOfVertices().get(0), 0);

		nodeParent.put(getListOfVertices().get(0), null);

		for (int count = 0; count < vertices.size() - 1; count++) {
			T u = minKey(nodeKey, included);

			included.put(u, true);

			for (Edge<T> edge : this.vertices.get(u)) {
				T v = edge.getVertex();
				if (!included.get(v) && nodeKey.get(u) < nodeKey.get(v)) {
					nodeParent.put(v, u);
					nodeKey.put(v, nodeKey.get(u));
				}
			}
		}
			
		ListGraph<T> MST = new ListGraph<>();
		for (T v : getListOfVertices()) {
			double weight = 0;
			for (Edge<T> edge : this.vertices.get(v)) {
				if (edge.getVertex().equals(nodeParent.get(v))) {
					weight = edge.getWeight();
					break;
				}
			}
			if (nodeParent.get(v) != null) {
				MST.addEdge(nodeParent.get(v), v, weight, false);
			}
		}
		
		return MST;
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
	private T minKey(HashMap<T, Integer> keys, HashMap<T, Boolean> included) {
		// Initialize min value
		int min = Integer.MAX_VALUE;
		T minKey = null;

		for (Map.Entry<T, Integer> node : keys.entrySet()) {
			if (!included.get(node.getKey()) && node.getValue() <= min) {
				min = node.getValue();
				minKey = node.getKey();
			}
		}

		return minKey;
	}


}
